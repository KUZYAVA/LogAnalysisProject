package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;

import java.util.Optional;

/**
 * Repository for managing {@link Group} entities.
 * This interface extends Spring Data's {@link CrudRepository} providing basic CRUD operations.
 * It also uses {@link RepositoryRestResource} to expose the repository's functionality via REST.
 */
@RepositoryRestResource(path = "groups")
public interface GroupRepository extends CrudRepository<Group, Integer> {

    /**
     * Finds a {@link Group} by its title.
     *
     * @param title The title of the group to find.
     * @return An {@link Optional} containing the group if found, or an empty {@link Optional} if not.
     */
    Optional<Group> findByTitle(String title);
}
