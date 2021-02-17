package com.bicycle.racing.events.temp;

import java.util.List;
import java.util.Optional;

public interface EventRepository<T> {
    Optional<List<T>> findAll();

    void insertList(List<T> list);
}