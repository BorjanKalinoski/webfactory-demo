package com.example.webfactorydemo.repositories;

import com.example.webfactorydemo.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
