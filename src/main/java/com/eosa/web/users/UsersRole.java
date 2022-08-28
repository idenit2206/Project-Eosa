package com.eosa.web.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UsersRole {
    CLIENT("CLIENT", "CLIENT"), 
    DETECTIVE("DETECTIVE", "DETECTIVE"),
    ADMIN("ADMIN", "ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN", "SUPER_ADMIN");

    private final String key;
    private final String value;

}