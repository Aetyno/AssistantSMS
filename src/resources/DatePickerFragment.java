package resources;

import java.util.Calendar;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	private int year ;
	
	private int month ;
	private int day ;
	private Button buttonDate;
	public DatePickerFragment (Button buttonDate){
		this.buttonDate = buttonDate;
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	public void ModifierButton(){
		buttonDate.setText(new StringBuilder().append(month + 1)
				   .append("-").append(day).append("-").append(year)
				   .append(" "));
	}
	public void onDateSet(DatePicker view, int selectedYear,
			int selectedMonth, int selectedDay) {
		year = selectedYear;
		month = selectedMonth;
		day = selectedDay;

		// set selected date into textview
		buttonDate.setText(new StringBuilder().append(month + 1)
		   .append("-").append(day).append("-").append(year)
		   .append(" "));
	}
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
}
