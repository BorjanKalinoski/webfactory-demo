package com.example.webfactorydemo.repositories;

import com.example.webfactorydemo.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Post> findAllByUserIdOrderByCreatedAtDesc(Long user_id, Pageable pageable);
}
