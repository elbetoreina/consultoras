package com.consultoras.app.common.utils;

import java.util.Calendar;
import java.util.Objects;

public class CalendarUtils {

	public static Calendar trimDateFromEpoch(String epoch) {

		if (epoch != null) {
			Long fechaEpoch = Long.valueOf(epoch);
			
			Calendar fechaCalendar = Calendar.getInstance();

			fechaCalendar.setTimeInMillis(fechaEpoch * 1000);
			

			return fechaCalendar;
		} else {
			return null;
		}

	}

	public static String epochFromCalendar(Calendar calendar) {

		if (calendar != null) {

			Long fechaLong = calendar.getTimeInMillis()/1000;
			
			String fechaEpoch = Objects.toString(fechaLong, null);

			return fechaEpoch;
		} else {
			return null;
		}

	}

}
