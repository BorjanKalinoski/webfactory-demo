package com.example.webfactorydemo.services;

import com.example.webfactorydemo.exceptions.ErrorKey;
import com.example.webfactorydemo.exceptions.PostNotFoundException;
import com.example.webfactorydemo.exceptions.UserNotFoundException;
import com.example.webfactorydemo.models.*;
import com.example.webfactorydemo.repositories.PostRepository;
import com.example.webfactorydemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    public List<GetPost> getPosts(Pageable pageable) {//TODO pageable
        Page<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);

        List<GetPost> getPosts = new ArrayList<>();

        posts.forEach(post -> {
            getPosts.add(new GetPost(post.getId(), post.getTitle(), post.getDescription(), post.getCreatedAt(), post.getUser().getId()));
        });

        return getPosts;
    }

    public GetPost getPost(String id) throws Exception {
        Optional<Post> post = postRepository.findById(Long.valueOf(id));

        if (post.isEmpty()) {
            throw new PostNotFoundException(ErrorKey.PostNotFound);
        }

        Post p = post.get();
        return new GetPost(p.getId(), p.getTitle(), p.getDescription(), p.getCreatedAt(), p.getUser().getId());
    }

    public GetPost submitPost(SubmitPost post) throws Exception {
        Optional<User> u = userRepository.findById(post.getUserId());
        if (u.isEmpty()) {
            throw new UserNotFoundException(ErrorKey.UserNotFound);
        }
        Post newPost = new Post(post.getTitle(), post.getDescription(), new Date(), u.get());
        postRepository.save(newPost);
        return new GetPost(newPost.getId(), newPost.getTitle(), newPost.getDescription(), new Date(), newPost.getUser().getId());
    }

    public GetPost deletePost(String id) throws Exception {
        Long pId = Long.valueOf(id);
        Optional<Post> post = postRepository.findById(pId);
        if (post.isEmpty()) {
            throw new PostNotFoundException(ErrorKey.PostNotFound);
        }
        postRepository.deleteById(pId);
        Post p = post.get();
        return new GetPost(p.getId(), p.getTitle(), p.getDescription(), p.getCreatedAt(), p.getUser().getId());
    }

    public List<GetPost> getPostsByUserId(String userId, Pageable pageable) throws UserNotFoundException {
        Long uId = Long.valueOf(userId);
        Optional<User> u = userRepository.findById(uId);
        if (u.isEmpty()) {
            throw new UserNotFoundException(ErrorKey.UserNotFound);
        }
        Page<Post> posts = postRepository.findAllByUserIdOrderByCreatedAtDesc(uId, pageable);
        List<GetPost> getPosts = new ArrayList<>();
        posts.forEach(post -> {
            getPosts.add(new GetPost(post.getId(), post.getTitle(), post.getDescription(), post.getCreatedAt(), post.getUser().getId()));
        });
        return getPosts;
    }
}
