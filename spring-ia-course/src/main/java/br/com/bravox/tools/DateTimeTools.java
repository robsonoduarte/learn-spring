package br.com.bravox.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTools {

    @Tool(description = "Get the current date and time in the user's timezone")
    public String getCurrentDateTime(){
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    @Tool(description = "Set a user alarm for the given time, provided in ISO-8601 format")
    public String setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time) {
        var alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        var message = "Alarm set for: " + alarmTime;
        System.out.println(message); // save alarm to database...
        return message;
    }
}
