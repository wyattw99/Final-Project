import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	
	private int user_id;
	private String user_name;
	private String user_password;
	private String first_name;
	private String last_name;
	private Date user_birthday;
	private int user_height;
	private int user_weight;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}
	public int getUser_height() {
		return user_height;
	}
	public void setUser_height(int user_height) {
		this.user_height = user_height;
	}
	public int getUser_weight() {
		return user_weight;
	}
	public void setUser_weight(int user_weight) {
		this.user_weight = user_weight;
	}
	public User(int user_id, String user_name, String user_password, String first_name, String last_name,
			Date user_birthday, int user_height, int user_weight) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_birthday = user_birthday;
		this.user_height = user_height;
		this.user_weight = user_weight;
	}
	
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return String.format("Username: %s\n password: %s\n First Name: %s\n Last Name: %s\n Birthday: %s\n Height: %d inches \n Weight: %d lbs\n", user_name,user_password,first_name,last_name,df.format(user_birthday),user_height,user_weight);
	}
	
	
}
