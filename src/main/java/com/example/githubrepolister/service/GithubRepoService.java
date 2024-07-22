package com.example.githubrepolister.service;

import static com.example.githubrepolister.constants.Constants.USER_REPOS_BRANCHES_URI;
import static com.example.githubrepolister.constants.Constants.USER_REPOS_URI;

import com.example.githubrepolister.exception.UserNotFoundException;
import com.example.githubrepolister.model.Branch;
import com.example.githubrepolister.model.BranchInfo;
import com.example.githubrepolister.model.Repository;
import com.example.githubrepolister.model.RepositoryInfo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GithubRepoService {

    private final WebClient webClient;

    @Autowired
    public GithubRepoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<RepositoryInfo> getRepositories(String username) throws UserNotFoundException {
        try {
            List<Repository> repositories = webClient.get()
                    .uri(USER_REPOS_URI, username)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                            Mono.error(new UserNotFoundException("User "
                                    + username + " not found "))
                    )
                    .bodyToFlux(Repository.class)
                    .filter(repo -> !repo.isFork())
                    .collectList()
                    .block();
            return Optional.ofNullable(repositories)
                    .map(repos -> repos.stream().map(repo -> {
                        List<BranchInfo> branches = getBranches(username, repo.getName());
                        return new RepositoryInfo(
                                repo.getName(),
                                repo.getOwner().getLogin(),
                                branches
                        );
                    }).collect(Collectors.toList()))
                    .orElse(Collections.emptyList());

        } catch (WebClientResponseException e) {
            log.error("Error fetching repositories for user {}: {}", username, e.getMessage());
            throw new UserNotFoundException("User "
                    + username + " not found ");
        }
    }

    private List<BranchInfo> getBranches(String username, String repository) {
        return webClient.get()
                .uri(USER_REPOS_BRANCHES_URI, username, repository)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(Branch.class)
                .map(branch -> new BranchInfo(branch.getName(), branch.getCommit().getSha()))
                .collectList()
                .block();
    }
}
