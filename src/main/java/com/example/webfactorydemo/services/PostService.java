package com.example.webfactorydemo.services;

import com.example.webfactorydemo.models.Post;
import com.example.webfactorydemo.models.SubmitPost;
import com.example.webfactorydemo.models.User;
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
        Iterable<Post> a = postRepository.findAll(pageable);
        return (List<Post>) a;
    }

    public Post getPost(String id) throws Exception {
        Optional<Post> post = postRepository.findById(Long.valueOf(id));
        if (post.isPresent()) {
            return post.get();
        }
        throw new Exception("WAAW");
    }

    public Post submitPost(SubmitPost post) throws Exception {
        Optional<User> u = userRepository.findById(post.getUserId());
        if (u.isEmpty()) {
            throw new Exception("WTF");
        }
        Post newPost = new Post(post.getTitle(), post.getDescription(), new Date(), u.get());
        //TODO getpost or use the same SubmitPost class for retrieving posts, NEXT->
        return postRepository.save(newPost);
    }

    public Post deletePost(String id) throws Exception {
        Long lId = Long.valueOf(id);
        Optional<Post> post = postRepository.findById(lId);
        if (post.isEmpty()) {
            throw new Exception("Post not found");
        }
        postRepository.deleteById(lId);
        return post.get();
    }

}
