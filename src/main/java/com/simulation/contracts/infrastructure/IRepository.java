package com.simulation.contracts.infrastructure;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    // Create
    T create(T entity);

    // Read
    Optional<T> readById(long id);
    List<T> readAll();
    
    // Update
    T update(T entity);

    // Delete
    void deleteById(long id);
}