package com.example.githubrepolister.constants;

public class Constants {
    public static final String APPLICATION_JSON = "application/json";
    public static final String USER_REPOS_URI = "/users/{username}/repos";
    public static final String USER_REPOS_BRANCHES_URI = "/repos/{username}/{repo}/branches";

    private Constants() {
        // Prevent instantiation
    }
}
