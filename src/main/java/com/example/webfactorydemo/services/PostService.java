package com.example.webfactorydemo.services;

import com.example.webfactorydemo.exceptions.ErrorKey;
import com.example.webfactorydemo.exceptions.PostNotFoundException;
import com.example.webfactorydemo.exceptions.UserNotFoundException;
import com.example.webfactorydemo.models.*;
import com.example.webfactorydemo.repositories.PostRepository;
import com.example.webfactorydemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPosts(Pageable pageable) {//TODO pageable
        return postRepository.findAll(pageable).toList();
    }

    public GetPost getPost(String id) throws Exception {
        Optional<Post> post = postRepository.findById(Long.valueOf(id));
        if (post.isPresent()) {
            Post p = post.get();
            return new GetPost(p.getId(), p.getTitle(), p.getDescription(), p.getCreatedAt(), p.getUser().getId());
        }

        throw new PostNotFoundException(ErrorKey.PostNotFound);
    }

    public GetPost submitPost(SubmitPost post) throws Exception {
        Optional<User> u = userRepository.findById(post.getUserId());
        if (u.isEmpty()) {
            throw new UserNotFoundException(ErrorKey.UserNotFound);
        }
        Post newPost = new Post(post.getTitle(), post.getDescription(), new Date(), u.get());
        postRepository.save(newPost);
        return new GetPost(newPost.getId(), newPost.getTitle(), newPost.getDescription(), newPost.getCreatedAt(), newPost.getUser().getId());
    }

    public Post deletePost(String id) throws Exception {
        Long pId = Long.valueOf(id);
        Optional<Post> post = postRepository.findById(pId);
        if (post.isEmpty()) {
            throw new PostNotFoundException(ErrorKey.PostNotFound);
        }
        postRepository.deleteById(pId);
        return post.get();
    }

}
