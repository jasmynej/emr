package org.emr.patientservice.controllers;

import org.emr.patientservice.models.Notification;
import org.emr.patientservice.repos.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/read-notification/{id}")
    public String readNotification(@PathVariable("id") long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        assert notification != null;
        notification.setRead(true);
        notificationRepository.save(notification);
        return "Notification read";
    }
}
