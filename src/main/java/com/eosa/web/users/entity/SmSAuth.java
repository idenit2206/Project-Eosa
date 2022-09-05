package com.eosa.web.users.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmSAuth {

    private String usersPhone;
    private String passKey;

}
