package com.bicycle.racing.events;

import com.bicycle.racing.events.data.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventJpaRepository extends CrudRepository<Event, Long> {
    @Override
    <T extends Event> T save(T entity);

    @Override
    <T extends Event> Iterable<T> saveAll(Iterable<T> entities);

    @Override
    Optional<Event> findById(Long aLong);

    @Override
    List<Event> findAll();
}
