package com.example.githubrepolister.controller;

import com.example.githubrepolister.model.RepositoryInfo;
import com.example.githubrepolister.service.GithubRepoService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github")
public class GitHubRepoController {
    private final GithubRepoService githubRepoService;

    @GetMapping("/repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username,
                                             @RequestHeader("Accept") String acceptHeader) {
        log.info("Received request for user: {}, Accept header: {}", username, acceptHeader);
        if (!MediaType.APPLICATION_JSON_VALUE.equals(acceptHeader)) {
            log.error("Unsupported Accept header: {}", acceptHeader);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of(
                    "status", HttpStatus.NOT_ACCEPTABLE.value(),
                    "message", "Accept header must be 'application/json'"
            ));
        }

        List<RepositoryInfo> repositories = githubRepoService.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }
}
