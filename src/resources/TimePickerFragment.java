package resources;

import java.util.Calendar;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

	private int hour;
	private int minute;
	private Button buttonTime;
	
	public TimePickerFragment(Button buttonTime){
		this.buttonTime = buttonTime;
		 final Calendar c = Calendar.getInstance();
	     hour = c.get(Calendar.HOUR_OF_DAY);
	     minute = c.get(Calendar.MINUTE);
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		 final Calendar c = Calendar.getInstance();
	     hour = c.get(Calendar.HOUR_OF_DAY);
	     minute = c.get(Calendar.MINUTE);
	        // Create a new instance of TimePickerDialog and return it
	        return new TimePickerDialog(getActivity(), this, hour, minute,
	                DateFormat.is24HourFormat(getActivity()));
	}
	
	public void onTimeSet(TimePicker view, int selectedHour,
			int selectedMinute) {
		hour = selectedHour;
		minute = selectedMinute;
		// set current time into textview
		buttonTime.setText(new StringBuilder().append(pad(hour))
				.append(":").append(pad(minute)));


	}
	public void ModifierButton(){
		buttonTime.setText(new StringBuilder().append(pad(hour))
				.append(":").append(pad(minute)));
	}
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
}