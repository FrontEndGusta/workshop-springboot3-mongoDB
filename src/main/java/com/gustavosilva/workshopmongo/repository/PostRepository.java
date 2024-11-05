package com.gustavosilva.workshopmongo.repository;

import com.gustavosilva.workshopmongo.domain.Post;
import com.gustavosilva.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    //faz a mesma busca que o findByTitleContainingIgnoreCase porém com Query
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);

    //método do Mongo para buscar
    List<Post> findByTitleContainingIgnoreCase(String text);


}
