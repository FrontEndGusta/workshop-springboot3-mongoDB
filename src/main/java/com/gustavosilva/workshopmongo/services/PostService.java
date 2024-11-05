package com.gustavosilva.workshopmongo.services;

import com.gustavosilva.workshopmongo.domain.Post;
import com.gustavosilva.workshopmongo.domain.User;
import com.gustavosilva.workshopmongo.dto.UserDTO;
import com.gustavosilva.workshopmongo.repository.PostRepository;
import com.gustavosilva.workshopmongo.repository.UserRepository;
import com.gustavosilva.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;



    public Post findById(String id) {
        Optional<Post> user = postRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
    }

    //usa a consulta do mongo
    public List<Post> findByTitle(String text) {
//        return postRepository.findByTitleContainingIgnoreCase(text);
        return postRepository.searchTitle(text);
    }


}
