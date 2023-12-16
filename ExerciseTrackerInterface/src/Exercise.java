import java.util.Date;

public class Exercise {
	
	private int exercise_id;
	private String exercise_name;
	private String exercise_type;
	private int exercise_distance;
	private String exercise_description;
	private int user_id;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
			String exercise_description, int user_id, Date exercise_date, int exercise_time, String exercise_intensity) {
		this.exercise_id = exercise_id;
		this.exercise_name = exercise_name;
		this.exercise_type = exercise_type;
		this.exercise_distance = exercise_distance;
		this.exercise_description = exercise_description;
		this.user_id = user_id;
		this.exercise_date = exercise_date;
		this.exercise_time = exercise_time;
		this.exercise_intensity = exercise_intensity;
	}
	
	public Exercise() {
		this.exercise_name = "";
		this.exercise_type = "";
		this.exercise_distance = 0;
		this.exercise_description = "";
		this.user_id = 0;
		this.exercise_date = null;
		this.exercise_time = 0;
	}

	
	
	
	
}
