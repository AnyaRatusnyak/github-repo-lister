package com.example.githubrepolister.model;

import lombok.Data;

@Data
public class Branch {
    private String name;
    private Commit commit;
}
