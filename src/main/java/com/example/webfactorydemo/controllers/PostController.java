package com.example.webfactorydemo.controllers;


import com.example.webfactorydemo.models.Post;
import com.example.webfactorydemo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController(value = "/api/posts")
public class PostController {

    final
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    void submitPost(@Valid Post post) {

        postService.submitPost(post);

    }

}
