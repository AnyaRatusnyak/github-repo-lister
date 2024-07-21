package com.example.githubrepolister.constants;

public class Constants {
    public static final String GITHUB_API_BASE_URL = "https://api.github.com";
    public static final String USER_REPOS_URI = "/users/{username}/repos";
    public static final String USER_REPOS_BRANCHES_URI = "/repos/{username}/{repo}/branches";

    private Constants() {
        // Prevent instantiation
    }
}
