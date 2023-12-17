import java.text.SimpleDateFormat;
import java.util.Date;

public class Exercise {
	
	private int exercise_id;
	private String exercise_name;
	private String exercise_type;
	private int exercise_distance;
	private String exercise_description;
	private User user;
	private Date exercise_date;
	private int exercise_time;
	private String exercise_intensity;
	
	public int getExercise_id() {
		return exercise_id;
	}
	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}
	public String getExercise_name() {
		return exercise_name;
	}
	public void setExercise_name(String exercise_name) {
		this.exercise_name = exercise_name;
	}
	public String getExercise_type() {
		return exercise_type;
	}
	public void setExercise_type(String exercise_type) {
		this.exercise_type = exercise_type;
	}
	public int getExercise_distance() {
		return exercise_distance;
	}
	public void setExercise_distance(int exercise_distance) {
		this.exercise_distance = exercise_distance;
	}
	public String getExercise_description() {
		return exercise_description;
	}
	public void setExercise_description(String exercise_description) {
		this.exercise_description = exercise_description;
	}
	public User getUser() {
		return user;
	}
	public void setUser_id(User user) {
		this.user = user;
	}
	public Date getExercise_date() {
		return exercise_date;
	}
	public void setExercise_date(Date exercise_date) {
		this.exercise_date = exercise_date;
	}
	public int getExercise_time() {
		return exercise_time;
	}
	public void setExercise_time(int exercise_time) {
		this.exercise_time = exercise_time;
	}
	
	public String getExercise_intensity() {
		return exercise_intensity;
	}
	public void setExercise_intensity(String exercise_intensity) {
		this.exercise_intensity = exercise_intensity;
	}
	
	public Exercise(int exercise_id, String exercise_name, String exercise_type, int exercise_distance,
			String exercise_description, User user, Date exercise_date, int exercise_time, String exercise_intensity) {
		this.exercise_id = exercise_id;
		this.exercise_name = exercise_name;
		this.exercise_type = exercise_type;
		this.exercise_distance = exercise_distance;
		this.exercise_description = exercise_description;
		this.user = user;
		this.exercise_date = exercise_date;
		this.exercise_time = exercise_time;
		this.exercise_intensity = exercise_intensity;
	}
	
	public Exercise() {
		this.exercise_name = "";
		this.exercise_type = "";
		this.exercise_distance = 0;
		this.exercise_description = "";
		this.user = null;
		this.exercise_date = null;
		this.exercise_time = 0;
		this.exercise_intensity = "";
	}
	
	public double caloriesBurned() {
	    double weight = user.getUser_weight();
	    double time = (double) getExercise_time();
	    if (exercise_type.equals("Run")) {
	        if (exercise_intensity.equals("Easy")) {
	            return 8 * (weight / 2.204) * (time / 60);
	        } else if (exercise_intensity.equals("Medium")) {
	            return 11.5 * (weight / 2.204) * (time / 60);
	        } else if (this.exercise_intensity.equals("Hard")) {
	            return 16.0 * (weight / 2.204) * (time / 60);
	        } else {
	            return 3.5 * (weight / 2.204) * (time / 60);
	        }
	    } else if (this.exercise_type.equals("Swim")) {
	        if (this.exercise_intensity.equals("Easy")) {
	            return  7 * (weight / 2.204) * (time / 60);
	        } else if (this.exercise_intensity.equals("Medium")) {
	            return 10 * (weight / 2.204) * (exercise_time / 60);
	        } else if (this.exercise_intensity.equals("Hard")) {
	            return 16 * (weight / 2.204) * (time / 60);
	        } else {
	            return 3.5 * (weight / 2.204) * (time / 60);
	        }
	    } else if (this.exercise_type.equals("Bike")) {
	        if (this.exercise_intensity.equals("Easy")) {
	            return 4 * (weight / 2.204) * (time / 60);
	        } else if (this.exercise_intensity.equals("Medium")) {
	            return 8 * (weight / 2.204) * (time / 60);
	        } else if (this.exercise_intensity.equals("Hard")) {
	            return 12 * (weight / 2.204) * (time / 60);
	        } else {
	            return 3.5 * (weight / 2.204) * (time / 60);
	        }
	    } else {
	        if (this.exercise_intensity.equals("Easy")) {
	            return 8 * (weight / 2.204) * (time / 60);
	        } else if (this.exercise_intensity.equals("Medium")) {
	            return 11.5 * (weight / 2.204) * (time / 60);
	        } else if (this.exercise_intensity.equals("Hard")) {
	            return 16.0 * (weight / 2.204) * (time / 60);
	        } else {
	            return 3.5 * (weight / 2.204) * (time / 60);
	        }
	    }
	}
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("Date: %s\t %s\tType: %s\t Distance: %d\n",df.format(exercise_date),exercise_name,exercise_type,exercise_distance);
	}
	
	public String detailString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("%s\n %s\n Distance: %d\n %s\n Completed by: %s on %s\nTime: %d minutes \n %s intensity which burned %.2f calories\n", 
				exercise_name,exercise_type,exercise_distance,exercise_description,
				(user.getFirst_name() +" " + user.getLast_name()),df.format(exercise_date),exercise_time,exercise_intensity,caloriesBurned());
	}
}


