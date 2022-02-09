package ecommercGesture.defaultRepositoryImplementation;

import java.time.LocalDate;

import ecommercGesture.domain.Calendar;

public class SystemCalendar implements Calendar {
	public LocalDate currentDate() {
		return LocalDate.now();
	}
}
