package com.codegene.femicodes.drugreminder.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.codegene.femicodes.drugreminder.database.DatabaseHelper;
import com.codegene.femicodes.drugreminder.utils.AlarmUtil;
import com.codegene.femicodes.drugreminder.models.Reminder;
import com.codegene.femicodes.drugreminder.utils.DateAndTimeUtil;
import com.codegene.femicodes.drugreminder.receivers.AlarmReceiver;

import java.util.Calendar;
import java.util.List;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseHelper database = DatabaseHelper.getInstance(context);
        List<Reminder> reminderList = database.getNotificationList(Reminder.ACTIVE);
        database.close();
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);

        for (Reminder reminder : reminderList) {
            Calendar calendar = DateAndTimeUtil.parseDateAndTime(reminder.getDateAndTime());
            calendar.set(Calendar.SECOND, 0);
            AlarmUtil.setAlarm(context, alarmIntent, reminder.getId(), calendar);
        }
    }
}