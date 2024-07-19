package com.example.githubrepolister.controller;

import static com.example.githubrepolister.constants.Constants.APPLICATION_JSON;

import com.example.githubrepolister.exception.UserNotFoundException;
import com.example.githubrepolister.model.RepositoryInfo;
import com.example.githubrepolister.service.GithubRepoService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/github")
public class GitHubRepoController {
    private final GithubRepoService githubRepoService;

    @GetMapping("/repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username,
                                             @RequestHeader("Accept") String acceptHeader) {
        if (!acceptHeader.equals(APPLICATION_JSON)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of(
                    "status", HttpStatus.NOT_ACCEPTABLE.value(),
                    "message", "Accept header must be 'application/json'"
            ));
        }

        try {
            List<RepositoryInfo> repositories = githubRepoService.getRepositories(username);
            return ResponseEntity.ok(repositories);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", HttpStatus.NOT_FOUND.value(),
                    "message", e.getMessage()
            ));
        }
    }
}
