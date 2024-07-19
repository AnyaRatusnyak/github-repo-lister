package com.example.githubrepolister.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryInfo {
    private String name;
    private String ownerLogin;
    private List<BranchInfo> branches;

}
