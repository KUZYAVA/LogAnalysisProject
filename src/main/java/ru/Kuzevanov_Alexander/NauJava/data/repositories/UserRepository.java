package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.models.User;

import java.util.Optional;

/**
 * Repository for managing {@link User} entities.
 * This interface extends Spring Data's {@link CrudRepository} providing basic CRUD operations.
 * It also uses {@link RepositoryRestResource} to expose the repository's functionality via REST.
 */
@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a {@link User} by their username.
     *
     * @param username The username to search for.
     * @return An {@link Optional} containing the {@link User} if found, or an empty {@link Optional} if not.
     */
    Optional<User> findByUsername(String username);
}
