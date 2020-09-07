package selectiveReveal;

public class Timestamp {
	int years; 
	int months;
	int days; 
	int hours; 
	int minutes;
	
	public Timestamp(int years, int months, int days, int hours, int minutes) {
		setYears(years); 
		setMonths(months); 
		setDays(days); 
		setHours(hours); 
		setMinutes(minutes); 		
	}
	
	

	public int getYears() {
		return years;
	}



	public void setYears(int years) {
		this.years = years;
	}



	public int getMonths() {
		return months;
	}



	public void setMonths(int months) {
		this.months = months;
	}



	public int getDays() {
		return days;
	}



	public void setDays(int days) {
		this.days = days;
	}



	public int getHours() {
		return hours;
	}



	public void setHours(int hours) {
		this.hours = hours;
	}



	public int getMinutes() {
		return minutes;
	}



	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}



	@Override
	public String toString() {
		return ""+days+"-"+months+"-"+years+" "+hours+":"+minutes;
	} 
	
	public int difference(Timestamp differentTimestamp) {
		int years = this.years-differentTimestamp.years;
		int months = this.months-differentTimestamp.months;
		int days = this.days-differentTimestamp.days;
		int hours = this.hours-differentTimestamp.hours;	
		int minutes = this.minutes-differentTimestamp.minutes;
		
		if((years+months+days+hours+minutes)<0) {System.out.println("NEGATIVE TIME");}
		return minutes+hours*60+days*24*60+months*30*24*60+years*365*24*60;
	}

}
