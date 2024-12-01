package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;

import java.util.Optional;

@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
