package com.consultoras.app.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CalendarUtils {

	public static Calendar trimDateFromEpoch(String epoch) {

		if (epoch != null) {
			Long fechaEpoch = Long.valueOf(epoch);
			Date fechaRaw = new Date(fechaEpoch);
			Calendar fechaCalendar = Calendar.getInstance();

			fechaCalendar.setTime(fechaRaw);

			fechaCalendar.set(Calendar.HOUR, 12);
			fechaCalendar.set(Calendar.MINUTE, 0);
			fechaCalendar.set(Calendar.SECOND, 0);
			fechaCalendar.set(Calendar.MILLISECOND, 0);

			return fechaCalendar;
		} else {
			return null;
		}

	}

	public static String epochFromCalendar(Calendar calendar) {

		if (calendar != null) {

			Date fechaRaw = calendar.getTime();
			String fechaEpoch = Objects.toString(fechaRaw, null);

			return fechaEpoch;
		} else {
			return null;
		}

	}

}
