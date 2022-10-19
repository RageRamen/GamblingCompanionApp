package com.honeybadger.gamblingbadger.timer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Timer {
    // Constructor

    public Timer(){

    }

    // Info Methods:

    public static LocalDateTime getCurrentTime() {
        Date date = Calendar.getInstance().getTime();
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
