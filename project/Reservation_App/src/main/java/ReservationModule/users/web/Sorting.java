package ReservationModule.users.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ReservationModule.utils.models.Reservation;

public class Sorting {
	public static List<Reservation> sort(List<Reservation> reservations, int allignment, int role) {
		switch (allignment) {
		case 1:
			List<String> ids = new ArrayList<>();
			if(role == 2) {
				for (Reservation i : reservations) {
					ids.add(i.getProfessor());
				}
			} else {
				for (Reservation i : reservations) {
					ids.add(i.getStudent());
				}
			}
			for(int i=0; i<ids.size(); i++) {
				for(int j=ids.size()-1; j>i; j--) {
					if(ids.get(i).compareTo(ids.get(j)) > 0) {
						String temp1 = ids.get(i);
						ids.set(i, ids.get(j));
						ids.set(j, temp1);

						Reservation temp2 = reservations.get(i);
						reservations.set(i, reservations.get(j));
						reservations.set(j, temp2);
					}
				}
			}
			break;
		case 2:
			List<Integer> prio = new ArrayList<>();
			for (Reservation i : reservations) {
				prio.add(i.getPriority());
			}
			for(int i=0; i<prio.size(); i++) {
				for(int j=prio.size()-1; j>i; j--) {
					if(prio.get(i) > prio.get(j)) {
						Integer temp1 = prio.get(i);
						prio.set(i, prio.get(j));
						prio.set(j, temp1);

						Reservation temp2 = reservations.get(i);
						reservations.set(i, reservations.get(j));
						reservations.set(j, temp2);
					}
				}
			}
			break;
		case 3:
			List<LocalDate> dates = new ArrayList<>();
			for (Reservation i : reservations) {
				dates.add(i.getDate());
			}
			for(int i=0; i<dates.size(); i++) {
				for(int j=dates.size()-1; j>i; j--) {
					if(dates.get(i).isAfter(dates.get(j))) {
						LocalDate temp1 = dates.get(i);
						dates.set(i, dates.get(j));
						dates.set(j, temp1);

						Reservation temp2 = reservations.get(i);
						reservations.set(i, reservations.get(j));
						reservations.set(j, temp2);
					}
				}
			}
			int i = 0;
	        while (i < reservations.size()) {
	            LocalDate currentDate = reservations.get(i).getDate();
	            List<LocalTime> times = new ArrayList<>();
	            List<Reservation> sameDateReservations = new ArrayList<>();

	            // Collect all reservations with the same date
	            while (i < reservations.size() && reservations.get(i).getDate().equals(currentDate)) {
	                times.add(reservations.get(i).getTime());
	                sameDateReservations.add(reservations.get(i));
	                i++;
	            }

	            // Sort times and reorder sameDateReservations accordingly
	            for (int k = 0; k < times.size(); k++) {
	                for (int l = k + 1; l < times.size(); l++) {
	                    if (times.get(k).isAfter(times.get(l))) {
	                        // Swap times
	                        LocalTime tempTime = times.get(k);
	                        times.set(k, times.get(l));
	                        times.set(l, tempTime);

	                        // Swap reservations
	                        Reservation tempReservation = sameDateReservations.get(k);
	                        sameDateReservations.set(k, sameDateReservations.get(l));
	                        sameDateReservations.set(l, tempReservation);
	                    }
	                }
	            }

	            // Update reservations with sorted sameDateReservations
	            for (int index = 0; index < sameDateReservations.size(); index++) {
	                reservations.set(index, sameDateReservations.get(index));
	            }
	        }
			break;
		default:
			break;
		}
		return reservations;
	}
}
