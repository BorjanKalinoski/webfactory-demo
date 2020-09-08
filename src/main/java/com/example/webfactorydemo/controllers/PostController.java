package com.example.webfactorydemo.controllers;


import com.example.webfactorydemo.exceptions.UserNotFoundException;
import com.example.webfactorydemo.models.GetPost;
import com.example.webfactorydemo.models.Post;
import com.example.webfactorydemo.models.SubmitPost;
import com.example.webfactorydemo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<GetPost> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping(value = "/{id}")
    public GetPost getPostById(@PathVariable("id") String id) throws Exception {
        return postService.getPost(id);
    }

    @GetMapping(value = "/user/{userId}")
    public List<GetPost> getPostsByUserId(@PathVariable("userId") String userId,Pageable pageable) throws UserNotFoundException {
        return postService.getPostsByUserId(userId, pageable);
    }

    @PostMapping
    public GetPost submitPost(@Valid @RequestBody SubmitPost post) throws Exception {
        return postService.submitPost(post);
    }

    @DeleteMapping(value = "/{id}")
    public GetPost deletePostById(@PathVariable("id") String id) throws Exception {
        return postService.deletePost(id);
    }

}
