package ReservationModule.users.web;

import java.util.Comparator;
import java.util.List;

import ReservationModule.utils.models.Reservation;

public class Sorting {
	public static List<Reservation> sort(List<Reservation> reservations, int alignment, int role) {
		switch (alignment) {
		case 1:
			if(role == 2) {
				reservations.sort(Comparator.comparing(Reservation::getProfessor));
			} else {
				reservations.sort(Comparator.comparing(Reservation::getStudent));
			}
			break;
		case 2:
			reservations.sort(Comparator.comparing(Reservation::getPriority));
			break;
		case 3:
			reservations.sort(Comparator.comparing(Reservation::getDate).thenComparing(Reservation::getTime));
			break;
		default:
			break;
		}
		return reservations;
	}
}
