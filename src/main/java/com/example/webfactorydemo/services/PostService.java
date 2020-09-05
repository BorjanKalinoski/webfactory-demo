package com.example.webfactorydemo.services;

import com.example.webfactorydemo.models.Post;
import com.example.webfactorydemo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    final
    PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void submitPost(Post post) {
        postRepository.save(post);
    }
}
