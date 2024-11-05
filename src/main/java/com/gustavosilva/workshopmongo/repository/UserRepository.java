package com.gustavosilva.workshopmongo.repository;

import com.gustavosilva.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
