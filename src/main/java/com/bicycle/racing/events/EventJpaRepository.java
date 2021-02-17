package com.bicycle.racing.events;

import com.bicycle.racing.events.data.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventJpaRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();
}
