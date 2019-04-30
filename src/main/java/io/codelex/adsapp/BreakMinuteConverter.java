package io.codelex.adsapp;

import java.text.DecimalFormat;

class BreakMinuteConverter {
    
    String breakTimeInMinutes(String time){
        String pattern = "00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        int seconds = Integer.parseInt(time);
        int minutes = seconds / 60;
        int secondsLeft = seconds % 60;
        String secondsFormatted = decimalFormat.format(secondsLeft);
        String minutesFormatted = decimalFormat.format(minutes);
        return minutesFormatted + "_" + secondsFormatted;
    }
}