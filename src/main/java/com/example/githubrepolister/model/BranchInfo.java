package com.example.githubrepolister.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchInfo {
    private String name;
    private String lastCommitSha;
}
