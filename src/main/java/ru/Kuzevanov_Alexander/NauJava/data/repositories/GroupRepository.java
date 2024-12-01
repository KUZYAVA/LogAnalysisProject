package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Kuzevanov_Alexander.NauJava.data.models.Group;

import java.util.Optional;

@RepositoryRestResource(path = "groups")
public interface GroupRepository extends CrudRepository<Group, Integer> {

    Optional<Group> findByTitle(String title);
}
