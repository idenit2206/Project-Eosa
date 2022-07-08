package com.sherlockk.demo.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UsersRole {
    CLIENT("CLIENT", "의뢰인"), 
    DETECTIVE("DETECTIVE", "탐정");

    private final String key;
    private final String value;

}