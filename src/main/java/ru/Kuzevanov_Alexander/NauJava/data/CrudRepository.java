package ru.Kuzevanov_Alexander.NauJava.data;

import ru.Kuzevanov_Alexander.NauJava.data.exception.CrudRepositoryException;

import java.util.Optional;

public interface CrudRepository<T, ID> {
    void create(String message, String tag) throws CrudRepositoryException;

    Optional<T> read(ID id) throws CrudRepositoryException;

    void update(T entity) throws CrudRepositoryException;

    void delete(ID id) throws CrudRepositoryException;
}
