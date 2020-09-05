package com.example.webfactorydemo.repositories;

import com.example.webfactorydemo.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Posts, Long> {

}
