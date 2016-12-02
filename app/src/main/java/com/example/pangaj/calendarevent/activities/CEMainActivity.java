package com.example.pangaj.calendarevent.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.pangaj.calendarevent.R;
import com.example.pangaj.calendarevent.helpers.CEAppConstants;
import com.example.pangaj.calendarevent.helpers.CEDate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CEMainActivity extends AppCompatActivity {
    private int selectedDay, selectedMonth, selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etEnterDate = (EditText) findViewById(R.id.et_enter_date);
        Button btnAddEvent = (Button) findViewById(R.id.btn_add_event);

        Calendar calendar = Calendar.getInstance();
        etEnterDate.setOnClickListener(v -> {
            dismissKeyboard();
            etEnterDate.setError(null);
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePicker = new DatePickerDialog(this, R.style.PickerTheme, (view, year, monthOfYear, dayOfMonth) -> {
                selectedDay = dayOfMonth;
                selectedMonth = monthOfYear;
                selectedYear = year;
                String selectedDate = String.format(Locale.ENGLISH, CEAppConstants.dateFormat, year, (monthOfYear + 1), dayOfMonth);
                Date dob = CEDate.convertToDate(selectedDate);
                etEnterDate.setText(CEDate.getStringFromDate(dob));
            }, currentYear, currentMonth, currentDay);
            datePicker.getDatePicker().setMinDate(getMinDateForPicker());//To disable the dates that comes after the current date(including).
            if (selectedDay != 0 && selectedYear != 0) {
                datePicker.updateDate(selectedYear, selectedMonth, selectedDay);
            }
            datePicker.show();
        });

        btnAddEvent.setOnClickListener(view -> {
            String eventDateString = etEnterDate.getText().toString();
            Date eventDate = CEDate.getDateFromString(eventDateString);
            Calendar eventDateCalendar = Calendar.getInstance();
            eventDateCalendar.setTime(eventDate);
            Intent calIntent = new Intent(Intent.ACTION_INSERT);
            calIntent.setType("vnd.android.cursor.item/event");
            GregorianCalendar calDate = new GregorianCalendar(eventDateCalendar.get(Calendar.YEAR), eventDateCalendar.get(Calendar.MONTH), eventDateCalendar.get(Calendar.DAY_OF_MONTH));
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                    calDate.getTimeInMillis());
            calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                    calDate.getTimeInMillis());
            startActivity(calIntent);
        });
    }

    /**
     * Method to hide the window keyboard with out view
     */
    public void dismissKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Get the Maximum date to display in the date picker
     *
     * @return The time
     */
    public static long getMinDateForPicker() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, (cal.get(Calendar.YEAR)));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, (cal.get(Calendar.DAY_OF_MONTH)));
        return cal.getTime().getTime();
    }
}
