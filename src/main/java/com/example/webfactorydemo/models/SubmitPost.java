package com.example.webfactorydemo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubmitPost {

    @NotBlank(message = "Title is mandatory")
    @Size(min = 6, max = 60)
    private String title;

    @NotBlank
    @Size(min = 10, max = 255)
    private String description;

    @NotNull
    private Long userId;

    public SubmitPost() {}

    public SubmitPost(String title, String description, Long userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
