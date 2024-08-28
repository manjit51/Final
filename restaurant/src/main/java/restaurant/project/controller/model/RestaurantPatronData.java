package restaurant.project.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.project.entity.Patron;

@Data
@NoArgsConstructor
public class RestaurantPatronData {
	private Set<PatronData> patrons = new HashSet<PatronData>();
	@Data
	@NoArgsConstructor
	public static class PatronData {
		private Long patronId;
		private String patronFirstName;
		private String patronLastName;
		private String patronFoodPreference;
		
		public PatronData(Patron patron) {
			this.patronId = patron.getPatronId();
			this.patronFirstName = patron.getPatronFirstName();
			this.patronLastName = patron.getPatronLastName();
			this.patronFoodPreference = patron.getPatronFoodPreference();
		}
		
		public Patron toPatron() {
			Patron patron = new Patron();
			patron.setPatronId(patronId);
			patron.setPatronFirstName(patronFirstName);
			patron.setPatronLastName(patronLastName);
			patron.setPatronFoodPreference(patronFoodPreference);
			
			return patron;
		}
	}
	
}
