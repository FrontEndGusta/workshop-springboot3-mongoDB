package com.gustavosilva.workshopmongo.resources;

import com.gustavosilva.workshopmongo.domain.Post;
import com.gustavosilva.workshopmongo.domain.User;
import com.gustavosilva.workshopmongo.dto.UserDTO;
import com.gustavosilva.workshopmongo.resources.util.URL;
import com.gustavosilva.workshopmongo.services.PostService;
import com.gustavosilva.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET) //ou @GetMapping
    public ResponseEntity<Post> findById(@PathVariable String id) {

        Post obj = postService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    //busca informações pelo texto no banco de dados
    @RequestMapping(value = "titlesearch", method = RequestMethod.GET) //ou @GetMapping
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {

        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }


}
