package com.eosa.web.firebase.pushnoti.entity;

import lombok.Data;

@Data
public class PushMessageRequestEntity {
    private String token;
    private String title;
    private String body;
    private String url;
    private String device;
}
