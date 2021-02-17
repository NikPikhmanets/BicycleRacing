package com.bicycle.racing.events.data;

import com.bicycle.racing.events.data.model.Event;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.bicycle.racing.events.data.EventConstants.*;

public class DemoEventsDataBuilder {

//    public List<Event> getEvents() {
//        return IntStream.range(0, 10).mapToObj(this::getEvent).collect(Collectors.toList());
//    }

//    private Event getEvent(int i) {
//        return Event.builder()
//                .id(i)
//                .type(TYPE)
//                .title(TITLE + (i * 31))
//                .sampleTrackId(i * 10)
//                .timeStart(LocalDateTime.now())
//                .timeLimit(LocalTime.now())
//                .distance(100 * i)
//                .content(CONTENT + i)
//                .createdAt(LocalDateTime.now())
//                .build();
//    }
}
