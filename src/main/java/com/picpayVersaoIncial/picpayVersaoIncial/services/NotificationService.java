package com.picpayVersaoIncial.picpayVersaoIncial.services;

import com.picpayVersaoIncial.picpayVersaoIncial.domain.user.User;
import com.picpayVersaoIncial.picpayVersaoIncial.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;


    public void sendNotification(User user, String status) throws Exception{
        String email = user.getEmail();
        NotificationDTO notificationDTO = new NotificationDTO(email, status);

        System.out.println("Notification send with success");


    }
}
