package com.bicycle.racing.service;

import com.bicycle.racing.model.EventRegistration;
import com.bicycle.racing.model.form.UserForm;
import com.bicycle.racing.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRegistrationService {

    private final EventRegistrationRepository eventRegistrationRepository;
    private final UserService userService;

    @Autowired
    public EventRegistrationService(EventRegistrationRepository eventRegistrationRepository,
                                    UserService userService) {
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.userService = userService;
    }

    public void addUserToEvent(EventRegistration eventRegistration) {
        if (eventRegistrationRepository.getEventByUser(eventRegistration) == null) {
            eventRegistrationRepository.addUserToEvent(eventRegistration);
        }
    }

    public int getTrackIdByEventId(int eventId, String username) {
        return eventRegistrationRepository.getTrackIdByEventId(eventId, username);
    }

    public void saveTrackId(UserForm userForm) {
        int userId = userService.getUserIdByUserName(userForm.getUsername());
        userForm.setUserId(userId);
        eventRegistrationRepository.saveTrackId(userForm);
    }

//    public Page<UserEvent> getUserEventsPage(PageRequest pr, String username) {
//        int startItem = (pr.getPageNumber() - 1) * pr.getPageSize();
//        int totalCount = eventRegistrationRepository.getTotalCount(username);
//        List<UserEvent> list;
//
//        if (totalCount < (pr.getPageSize() * (pr.getPageNumber() - 1))) {
//            list = Collections.emptyList();
//        } else {
//            list = eventRegistrationRepository.getUserEventsPage(username, startItem, pr.getPageSize());
//        }
//        return new PageImpl<>(list, PageRequest.of((pr.getPageNumber() - 1), pr.getPageSize()), totalCount);
//    }
}
