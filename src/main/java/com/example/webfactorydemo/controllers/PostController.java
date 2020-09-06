package com.example.webfactorydemo.controllers;


import com.example.webfactorydemo.models.GetPost;
import com.example.webfactorydemo.models.GetUser;
import com.example.webfactorydemo.models.Post;
import com.example.webfactorydemo.models.SubmitPost;
import com.example.webfactorydemo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping(value = "/{id}")
    public GetPost getPost(@PathVariable("id") String id) throws Exception {
        return postService.getPost(id);
    }

    @PostMapping
    public GetPost submitPost(@Valid @RequestBody SubmitPost post) throws Exception {
        return postService.submitPost(post);
    }

    @DeleteMapping(value = "/{id}")
    public Post deletePost(@PathVariable("id") String id) throws Exception {
        return postService.deletePost(id);
    }

    //get posts by user id

}
