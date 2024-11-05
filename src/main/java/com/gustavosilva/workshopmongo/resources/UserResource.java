package com.gustavosilva.workshopmongo.resources;

import com.gustavosilva.workshopmongo.domain.Post;
import com.gustavosilva.workshopmongo.domain.User;
import com.gustavosilva.workshopmongo.dto.UserDTO;
import com.gustavosilva.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET) //ou @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {

        List<User> list = userService.findAll();

        //transforma toda lista de User em um listDTO de User contendo apenas campos de UserDTO
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET) //ou @GetMapping
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {

        User obj = userService.findById(id);

        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST) //ou @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {

        User obj = userService.fromDTO(objDto);
        obj = userService.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) //ou @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable String id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT) //ou @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {

        User obj = userService.fromDTO(objDto);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();

    }

    //posts do usuário
    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET) //ou @GetMapping
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {

        User obj = userService.findById(id);

        return ResponseEntity.ok().body(obj.getPosts());
    }
}
