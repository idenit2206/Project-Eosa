package com.eosa.admin.usersmanage;

import java.time.LocalDateTime;

public interface GetUsersList {
    Long getUsersIdx();
    String getUsersAccount();
    String getUsersRole();
    LocalDateTime getUsersJoinDate();
}
