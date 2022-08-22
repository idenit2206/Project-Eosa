package com.eosa.admin.util.random;

import java.time.LocalDateTime;

public class RandomDate {
    
    public int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public LocalDateTime createRandomLocalDateTime(int startYear, int endYear) {
        int year = createRandomIntBetween(startYear, endYear);
        int month = createRandomIntBetween(1, 12);
        int day = createRandomIntBetween(1, 28);
        int hour = createRandomIntBetween(0, 23);
        int minute = createRandomIntBetween(0, 59);
        int second = createRandomIntBetween(0, 59);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
