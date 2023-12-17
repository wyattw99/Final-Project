import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExerciseTrackerFrame extends JFrame {

    private JPanel mainPanel;
    private ArrayList<User> users;
    private ArrayList<Exercise> exercises;
    private User activeUser;
    private JButton getExercisesButton;
    private JButton newExerciseButton;
    private JButton getYourExercisesButton;
    private JButton getUserDetailsButton;

    public ExerciseTrackerFrame() {
        super("Exercise Tracker");

        createMenuBar();
        
        users = APIConnector.getUsers(); 
        exercises = APIConnector.getExercises(users);
        //call api to get lists of what is in the database

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // main panel where different sets of information will be displayed

        this.getExercisesButton = new JButton("Get Exercises");
        getExercisesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayExercises();
            }
        });
        
        this.getYourExercisesButton = new JButton("Your Exercises");
        getYourExercisesButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		displayYourExercises();
        	}
        });

        this.newExerciseButton = new JButton("New Exercise");
        newExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewExercise();
            }
        });
        

        
        this.getUserDetailsButton = new JButton("User Details");
        getUserDetailsButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		displayUserDetails();
        	}
        });
        // adding action listeners to the buttons that display different things
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(getExercisesButton);
        buttonPanel.add(getYourExercisesButton);
        buttonPanel.add(newExerciseButton);
        buttonPanel.add(getUserDetailsButton);
        enableControls(false);

        JScrollPane scrollPane = new JScrollPane(mainPanel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //create menu with login and exit commands
    private void createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();
    	JMenu systemMenu = new JMenu("System");
    	JMenuItem loginItem = new JMenuItem("Login");
    	loginItem.addActionListener(new LoginAction());
    	JMenuItem logoutItem = new JMenuItem("Logout");
    	logoutItem.addActionListener(new LogoutAction());
    	JMenuItem exitItem = new JMenuItem("Exit");
    	exitItem.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.exit(0);
    		}
    	});
    	JMenuItem createUserItem = new JMenuItem("Create User");
    	createUserItem.addActionListener(new CreateUserAction());
    	systemMenu.add(loginItem);
    	systemMenu.add(logoutItem);
    	systemMenu.add(exitItem);
    	systemMenu.add(createUserItem);
    	menuBar.add(systemMenu);
    	setJMenuBar(menuBar);
    }

    private void login() {
    	JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] fields = {
                "Username:", usernameField,
                "Password:", passwordField
        };


        
        int result = JOptionPane.showConfirmDialog(
                this, fields, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String knownPassword = "";
            User user = null;
            for (User u : users) {
            	if (u.getUser_name().equals(username)) {
            		knownPassword = u.getUser_password();
            		user = u;
            		break;
            	}
            }

            if (knownPassword.equals(password)) {
               activeUser = user;
                if (activeUser != null) {
                	enableControls(true);
                    displayExercises();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to retrieve user information.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void logout() {
    	activeUser = null;
    	clearMainPanel();
    	enableControls(false);
    }
    
    // changes when buttons can be used 
    private void enableControls(boolean status) {
    		getExercisesButton.setEnabled(status);
    	    newExerciseButton.setEnabled(status);
    	    getYourExercisesButton.setEnabled(status);
    	    getUserDetailsButton.setEnabled(status);
    }
    
    private void createNewUser() {
    	JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField birthdayField = new JTextField();
        JTextField heightField = new JTextField();
        JTextField weightField = new JTextField();
        
        Object[] fields = {
                "Username:", usernameField,
                "Password:", passwordField,
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Birthday (yyyy-MM-dd):", birthdayField,
                "Height (inches):", heightField,
                "Weight (lbs):", weightField
        };
        
        int result = JOptionPane.showConfirmDialog(this, fields, "Edit User", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
        	try {
        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        		Date date = df.parse(birthdayField.getText());
        		int height = Integer.parseInt(heightField.getText());
        		int weight = Integer.parseInt(weightField.getText());
        		
        		User newUser = new User(
        			0,
        			usernameField.getText(),
        			passwordField.getText(),
        			firstNameField.getText(),
        			lastNameField.getText(),
        			date,
        			height,
        			weight
        		);
        		
        		
        		if (APIConnector.newUser(newUser)) {
        			users = APIConnector.getUsers();
        			login();
        		} else {
        			JOptionPane.showMessageDialog(this, "Failed to create user");
        		}
        	} catch (Exception ex) {
        		JOptionPane.showMessageDialog(this, "Invalid input. Please try again");
        	}
        }
    }
    
    private void displayExercises() {
        exercises = APIConnector.getExercises(users);
        clearMainPanel();
        addTextToMainPanel("Exercises:\n");
        for (Exercise exercise : exercises) {
            addTextToMainPanel(exercise.toString());
            mainPanel.add(new JButton(new ViewExerciseAction(exercise)));
            if(activeUser.getUser_id() == exercise.getUser().getUser_id()) {
            	mainPanel.add(new JButton(new EditExerciseAction(exercise)));
            }
            addTextToMainPanel("\n");
        }
    }
    
    private void displayYourExercises() {
    	exercises = APIConnector.getExercises(users);
    	clearMainPanel();
    	addTextToMainPanel("Exercises: \n");
    	for (Exercise exercise : exercises) {
    		if(activeUser.getUser_id() == exercise.getUser().getUser_id()) {
    			addTextToMainPanel(exercise.toString());
                mainPanel.add(new JButton(new ViewExerciseAction(exercise)));
                mainPanel.add(new JButton(new EditExerciseAction(exercise)));
                mainPanel.add(new JButton(new DeleteExerciseAction(exercise)));
                addTextToMainPanel("\n");
    		}
    	}
    }
    
    private void displayUserDetails() {
    	clearMainPanel();
    	addTextToMainPanel("User Details: \n");
    	addTextToMainPanel(activeUser.toString());
    	mainPanel.add(new JButton(new EditUserAction()));
    	mainPanel.add(new JButton(new DeleteUserAction()));
    }
    
    private void displayExerciseDetails(Exercise exercise) {
        JOptionPane.showMessageDialog(this, exercise.detailString());
    }

    private void createNewExercise() {
        JTextField nameField = new JTextField();
        JSpinner typeSpinner = new JSpinner(new SpinnerListModel(new String[] {"Run","Swim","Bike","Other"}));
        JTextField distanceField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();
        JSpinner intensitySpinner = new JSpinner(new SpinnerListModel( new String[] {"Easy","Medium","Hard"}));

        Object[] fields = {
                "Name:", nameField,
                "Type:", typeSpinner,
                "Distance:", distanceField,
                "Description:", descriptionField,
                "Date (yyyy-MM-dd):", dateField,
                "Time (minutes):", timeField,
                "Intensity:", intensitySpinner
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "New Exercise", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(dateField.getText());
                int distance = Integer.parseInt(distanceField.getText());
                int time = Integer.parseInt(timeField.getText());

                Exercise newExercise = new Exercise(
        				0,
        				nameField.getText(),
        				typeSpinner.getValue().toString(),
        				distance,
        				descriptionField.getText(),
        				activeUser,
        				date,
        				time,
        				intensitySpinner.getValue().toString()
        		);

                exercises = APIConnector.newExercise(exercises, newExercise, activeUser);
                displayExercises();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editExercise(Exercise exercise) {
    	JTextField nameField = new JTextField(exercise.getExercise_name());
        JSpinner typeSpinner = new JSpinner(new SpinnerListModel(new String[] {"Run","Swim","Bike","Other"}));
        typeSpinner.setValue(exercise.getExercise_type());
        JTextField distanceField = new JTextField(String.valueOf(exercise.getExercise_distance()));
        JTextField descriptionField = new JTextField(exercise.getExercise_description());
        JTextField dateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(exercise.getExercise_date()));
        JTextField timeField = new JTextField(String.valueOf(exercise.getExercise_time()));
        JSpinner intensitySpinner = new JSpinner(new SpinnerListModel( new String[] {"Easy","Medium","Hard"}));
        intensitySpinner.setValue(exercise.getExercise_intensity());
        
        Object[] fields = {
                "Name:", nameField,
                "Type:", typeSpinner,
                "Distance:", distanceField,
                "Description:", descriptionField,
                "Date (yyyy-MM-dd):", dateField,
                "Time (minutes):", timeField,
                "Intensity:", intensitySpinner
        };
        
        int confirm = JOptionPane.showConfirmDialog(this, fields, "Edit Exercise", JOptionPane.OK_CANCEL_OPTION);
        
        if (confirm == JOptionPane.OK_OPTION) {
        	try {
        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        		Date date = df.parse(dateField.getText());
        		int distance = Integer.parseInt(distanceField.getText());
        		int time = Integer.parseInt(timeField.getText());
        		
        		Exercise editedExercise = new Exercise(
        				exercise.getExercise_id(),
        				nameField.getText(),
        				typeSpinner.getValue().toString(),
        				distance,
        				descriptionField.getText(),
        				exercise.getUser(),
        				date,
        				time,
        				intensitySpinner.getValue().toString()
        		);
        		
        		if (APIConnector.updateExercise(editedExercise,exercise)) {
        			displayExercises();
        		} else {
        			JOptionPane.showMessageDialog(this, "Failed to update exercise");
        		}
        	} catch (Exception ex) {
        		JOptionPane.showMessageDialog(this, "Invalid input. Please try again");
        	}
        }
    }

    private void editUser() {
    	JTextField usernameField = new JTextField(activeUser.getUser_name());
        JTextField passwordField = new JTextField(activeUser.getUser_password());
        JTextField firstNameField = new JTextField(activeUser.getFirst_name());
        JTextField lastNameField = new JTextField(activeUser.getLast_name());
        JTextField birthdayField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(activeUser.getUser_birthday()));
        JTextField heightField = new JTextField(String.valueOf(activeUser.getUser_height()));
        JTextField weightField = new JTextField(String.valueOf(activeUser.getUser_weight()));
        
        Object[] fields = {
                "Username:", usernameField,
                "Password:", passwordField,
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Birthday (yyyy-MM-dd):", birthdayField,
                "Height (inches):", heightField,
                "Weight (lbs):", weightField
        };
        
        int result = JOptionPane.showConfirmDialog(this, fields, "Edit User", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
        	try {
        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        		Date date = df.parse(birthdayField.getText());
        		int height = Integer.parseInt(heightField.getText());
        		int weight = Integer.parseInt(weightField.getText());
        		
        		User editedUser = new User(
        			activeUser.getUser_id(),
        			usernameField.getText(),
        			passwordField.getText(),
        			firstNameField.getText(),
        			lastNameField.getText(),
        			date,
        			height,
        			weight
        		);
        		
        		
        		if (APIConnector.updateUser(editedUser,activeUser)) {
        			users = APIConnector.getUsers();
        			for (User u: users) {
        				if (activeUser.getUser_id() == u.getUser_id()) {
        					activeUser = u;
        					break;
        				}
        			}
        			
        			displayUserDetails();
        		} else {
        			JOptionPane.showMessageDialog(this, "Failed to update user");
        		}
        	} catch (Exception ex) {
        		JOptionPane.showMessageDialog(this, "Invalid input. Please try again");
        	}
        }
    }

    private void deleteUser() {
    	try {
    		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Delete",JOptionPane.OK_CANCEL_OPTION);
			if (confirm == JOptionPane.OK_OPTION) {
	    		if (APIConnector.deleteUser(activeUser)) {
	    			JOptionPane.showMessageDialog(this,"User deleted");
	    			logout();		
	    		} else {
	    			JOptionPane.showMessageDialog(this,"Action failed please try again");
	    		}
			}
    	} catch (Exception ex) {
    		JOptionPane.showMessageDialog(this,"Action failed please try again");
    	}
    }
    
    private void deleteExercise(Exercise exercise) {
    	try {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this exercise?", "Confirm Delete",JOptionPane.OK_CANCEL_OPTION);
			if (confirm == JOptionPane.OK_OPTION) {
				if (APIConnector.deleteExercise(exercise)) {
					displayYourExercises();
	    		} else {
	    			JOptionPane.showMessageDialog(this,"Action failed please try again");
	    		}
    		}
    	} catch (Exception ex) {
    		JOptionPane.showMessageDialog(this,"Action failed please try again");
    	}
    }
    
    private void clearMainPanel() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void addTextToMainPanel(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        mainPanel.add(textArea);
    }

    
    // different actions that are usesd by buttons
    private class LoginAction extends AbstractAction{
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		login();
    	}
    }
    
    private class LogoutAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			logout();
		}
    }
    
    private class CreateUserAction extends AbstractAction{

    	public CreateUserAction() {
    		super("Create User");
    	}
    	
		@Override
		public void actionPerformed(ActionEvent e) {
			createNewUser();
		}
	}
    
    private class ViewExerciseAction extends AbstractAction {
        private Exercise exercise;

        public ViewExerciseAction(Exercise exercise) {
            super("View Details");
            this.exercise = exercise;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            displayExerciseDetails(exercise);
        }
    }
    
    private class EditExerciseAction extends AbstractAction {
    	private Exercise exercise;
    	
    	public EditExerciseAction(Exercise exercise) {
    		super("Edit Exercise");
    		this.exercise = exercise;
    	}

		@Override
		public void actionPerformed(ActionEvent e) {
			editExercise(exercise);
			
		}
    	
    	
    }
    
    private class EditUserAction extends AbstractAction{
    	private User user;
    	
    	public EditUserAction() {
    		super("Edit User");
    		this.user = activeUser;
    	}

		@Override
		public void actionPerformed(ActionEvent e) {
			editUser();
			
		}
    }
    
    private class DeleteUserAction extends AbstractAction{
    	private User user;
    	
    	public DeleteUserAction() {
    		super("Delete User");
    		this.user = activeUser;
    	}

		@Override
		public void actionPerformed(ActionEvent e) {
			deleteUser();
			
		} 	
    }
    
    private class DeleteExerciseAction extends AbstractAction{
    	private Exercise exercise;
    	
    	public DeleteExerciseAction(Exercise exercise) {
    		super("Delete Exercise");
    		this.exercise = exercise;
    	}

		@Override
		public void actionPerformed(ActionEvent e) {
			deleteExercise(exercise);
			
		}
    }

}

