package ru.Kuzevanov_Alexander.NauJava.data.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.Kuzevanov_Alexander.NauJava.data.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);
}
