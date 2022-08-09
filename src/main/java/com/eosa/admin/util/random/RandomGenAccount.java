package com.eosa.admin.util.random;

import lombok.Data;

@Data
public class RandomGenAccount {

    private String[] usersAccountKeyword1 = {
        "test", "local", "demo", "temp"
    };

    private String[] usersAccountKeyword2 = {
        "user", "account", "tester"
    };


    public String generateAccount() {
        String result = "";
        int rand1 = (int) Math.floor(Math.random() * usersAccountKeyword1.length);
        int rand2 = (int) Math.floor(Math.random() * usersAccountKeyword2.length);
        result = result + usersAccountKeyword1[rand1] + usersAccountKeyword2[rand2];
        result = result + String.valueOf(Math.round(Math.random() * 1000));
        return result;
    }
    
}
