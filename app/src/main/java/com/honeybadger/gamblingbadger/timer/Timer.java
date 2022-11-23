package com.honeybadger.gamblingbadger.timer;

import android.os.CountDownTimer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Timer
{
    public boolean isRunning;
    public long msRemaining;
    public CountDownTimer attachedCDT;

    // Constructor
    public Timer()
    {

    }

    // Info Methods:

    public static LocalDateTime getCurrentTime()
    {
        Date date = Calendar.getInstance().getTime();
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
