package com.bicycle.racing.events;

import java.util.List;

public interface EventService<T> {
    List<T> getAll();

    T getById(long id);

    void save(T t);

    void saveList(List<T> list);

    int getSampleTrackId(long id);
}
