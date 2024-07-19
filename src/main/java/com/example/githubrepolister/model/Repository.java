package com.example.githubrepolister.model;

import lombok.Data;

@Data
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;
}
