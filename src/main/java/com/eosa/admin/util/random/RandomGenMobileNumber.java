package com.eosa.admin.util.random;

public class RandomGenMobileNumber {
    
    public String RandomGenMobileNumber() {
        String result = "010";
        result += Integer.toString((int) Math.floor(Math.random() * 9999));
        result += Integer.toString((int) Math.floor(Math.random() * 9999));
        return result;
    }

}
