import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;
import org.json.JSONArray;


public class APIConnector {
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public static ArrayList<User> getUsers() {
		try {
			URL url = new URL("http://127.0.0.1:8000/activitymanager/users/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			
			JSONArray jResponse = new JSONArray(response.toString());
			ArrayList<User> users = new ArrayList<User>();

			for (int i = 0; i < jResponse.length(); i++) {
				JSONObject object = jResponse.getJSONObject(i);
				int user_id = object.getInt("user_id");
				String user_name = object.getString("user_name");
				String user_password = object.getString("user_password");
				String first_name = object.getString("first_name");
				String last_name = object.getString("last_name");
				Date user_birthday = df.parse(object.getString("user_birthday"));
				int user_height = object.getInt("user_height");
				int user_weight = object.getInt("user_weight");
				User user = new User(user_id,user_name,user_password,first_name,last_name,user_birthday,user_height,user_weight);
				users.add(user);
				
			}
			con.disconnect();
			return users;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public static ArrayList<Exercise> getExercises(ArrayList<User> users) {
		try {
			URL url = new URL("http://127.0.0.1:8000/activitymanager/exercises/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			//System.out.println(response.toString());
			JSONArray jResponse = new JSONArray(response.toString());
			ArrayList<Exercise> exercises = new ArrayList<Exercise>();

			for (int i = 0; i < jResponse.length(); i++) {
				JSONObject object = jResponse.getJSONObject(i);
				int id = object.getInt("exercise_id");
				String name = object.getString("exercise_name");
				String type = object.getString("exercise_type");
				int distance = object.getInt("exercise_distance");
				String description = object.getString("exercise_description");
				int user_id = object.getJSONObject("user").getInt("id");
				User user = null;
				for (User u : users) {
					if (u.getUser_id() == user_id){
						user = u;
						break;
					}
				}
				
				Date date = df.parse(object.getString("exercise_date"));
				int time = object.getInt("exercise_time");
				String intensity = object.getString("exercise_intensity");
				Exercise exercise = new Exercise(id,name,type,distance,description,user,date,time,intensity);
				exercises.add(exercise);
			}
			
			con.disconnect();
			return exercises;
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return null;
		}
		
	}
	
	public static boolean updateUser(User user, User originalUser) {
		try {
			String urlString = String.format("http://127.0.0.1:8000/activitymanager/users/%d/", user.getUser_id());
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			JSONObject request = new JSONObject();
			request.put("user_id", user.getUser_id());
			request.put("user_name", user.getUser_name());
			request.put("user_password", user.getUser_password());
			request.put("first_name", user.getFirst_name());
			request.put("last_name", user.getLast_name());
			request.put("user_birthday",df.format(user.getUser_birthday()));
			request.put("user_height", user.getUser_height());
			request.put("user_weight", user.getUser_weight());
			con.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream());
			output.writeBytes(request.toString());
			output.flush();
			int responseCode = con.getResponseCode();
			if(responseCode == 400) {
				return false;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			JSONObject object = new JSONObject(response.toString());
			int user_id = object.getInt("user_id");
			String user_name = object.getString("user_name");
			String user_password = object.getString("user_password");
			String first_name = object.getString("first_name");
			String last_name = object.getString("last_name");
			Date user_birthday = df.parse(object.getString("user_birthday"));
			int user_height = object.getInt("user_height");
			int user_weight = object.getInt("user_weight");
			User updatedUser = new User(user_id,user_name,user_password,first_name,last_name,user_birthday,user_height,user_weight);
			originalUser = updatedUser;
			return true;
		} catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public static boolean newUser(User user) {
		try {
			String urlString = String.format("http://127.0.0.1:8000/activitymanager/users/");
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			JSONObject request = new JSONObject();
			request.put("user_name", user.getUser_name());
			request.put("user_password", user.getUser_password());
			request.put("first_name", user.getFirst_name());
			request.put("last_name", user.getLast_name());
			request.put("user_birthday",df.format(user.getUser_birthday()));
			request.put("user_height", user.getUser_height());
			request.put("user_weight", user.getUser_weight());
			con.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream());
			output.writeBytes(request.toString());
			output.flush();
			int responseCode = con.getResponseCode();
			if(responseCode == 400) {
				return false;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			JSONObject object = new JSONObject(response.toString());
			int user_id = object.getInt("user_id");
			String user_name = object.getString("user_name");
			String user_password = object.getString("user_password");
			String first_name = object.getString("first_name");
			String last_name = object.getString("last_name");
			Date user_birthday = df.parse(object.getString("user_birthday"));
			int user_height = object.getInt("user_height");
			int user_weight = object.getInt("user_weight");
			User newUser = new User(user_id,user_name,user_password,first_name,last_name,user_birthday,user_height,user_weight);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteUser(User user){
		try {
			String urlString = String.format("http://127.0.0.1:8000/activitymanager/users/%s/",user.getUser_id());
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			JSONObject request = new JSONObject();
			con.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream());
			output.writeBytes(request.toString());
			output.flush();
			int responseCode = con.getResponseCode();
			if(responseCode == 204) {
				return true;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateExercise(Exercise exercise, Exercise originalExercise){
		try {
			String urlString = String.format("http://127.0.0.1:8000/activitymanager/exercises/%d/", originalExercise.getExercise_id());
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			JSONObject request = new JSONObject();
			request.put("exercise_id", exercise.getExercise_id());
			request.put("exercise_name", exercise.getExercise_name());
			request.put("exercise_type", exercise.getExercise_type());
			request.put("exercise_distance", exercise.getExercise_distance());
			request.put("exercise_description", exercise.getExercise_description());
			request.put("exercise_date", df.format(exercise.getExercise_date()));
			request.put("exercise_time", exercise.getExercise_time());
			request.put("exercise_intensity", exercise.getExercise_intensity());
			JSONObject userObject = new JSONObject();
			userObject.put("type","User");
			userObject.put("id", exercise.getUser().getUser_id());
			request.put("user", userObject);
			con.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream());
			output.writeBytes(request.toString());
			output.flush();
			int responseCode = con.getResponseCode();
			if(responseCode == 400) {
				return false;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			//System.out.println(response.toString());
			JSONObject object = new JSONObject(response.toString()); 
			int id = object.getInt("exercise_id");
			String name = object.getString("exercise_name");
			String type = object.getString("exercise_type");
			int distance = object.getInt("exercise_distance");
			String description = object.getString("exercise_description");
			//int user_id = object.getJSONObject("user").getInt("id");
			User user = exercise.getUser();
			Date date = df.parse(object.getString("exercise_date"));
			int time = object.getInt("exercise_time");
			String intensity = object.getString("exercise_intensity");
			Exercise editedExercise = new Exercise(id,name,type,distance,description,user,date,time,intensity);
			originalExercise = editedExercise;
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<Exercise> newExercise(ArrayList<Exercise> exercises, Exercise exercise, User user){
		try {
			String urlString = String.format("http://127.0.0.1:8000/activitymanager/exercises/");
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			JSONObject request = new JSONObject();
			request.put("exercise_name", exercise.getExercise_name());
			request.put("exercise_type", exercise.getExercise_type());
			request.put("exercise_distance", exercise.getExercise_distance());
			request.put("exercise_description", exercise.getExercise_description());
			request.put("exercise_date", df.format(exercise.getExercise_date()));
			request.put("exercise_time", exercise.getExercise_time());
			request.put("exercise_intensity", exercise.getExercise_intensity());
			JSONObject userObject = new JSONObject();
			userObject.put("type","User");
			userObject.put("id", user.getUser_id());
			request.put("user", userObject);
			con.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream());
			output.writeBytes(request.toString());
			output.flush();
			int responseCode = con.getResponseCode();
			if(responseCode == 400) {
				return exercises;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			JSONObject object = new JSONObject(response.toString());
			int id = object.getInt("exercise_id");
			String name = object.getString("exercise_name");
			String type = object.getString("exercise_type");
			int distance = object.getInt("exercise_distance");
			String description = object.getString("exercise_description");
			//int user_id = object.getJSONObject("user").getInt("id");
			Date date = df.parse(object.getString("exercise_date"));
			int time = object.getInt("exercise_time");
			String intensity = object.getString("exercise_intensity");
			Exercise newExercise = new Exercise(id,name,type,distance,description,user,date,time,intensity);
			exercises.add(newExercise);
			return exercises;
		} catch (Exception ex) {
			ex.printStackTrace();
			return exercises;
		}
	}
	
	public static boolean deleteExercise(Exercise exercise){
		try {
			String urlString = String.format("http://127.0.0.1:8000/activitymanager/exercises/%s/",exercise.getExercise_id());
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			JSONObject request = new JSONObject();
			con.setDoOutput(true);
			DataOutputStream output = new DataOutputStream(con.getOutputStream());
			output.writeBytes(request.toString());
			output.flush();
			int responseCode = con.getResponseCode();
			if(responseCode == 204) {
				return true;
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
