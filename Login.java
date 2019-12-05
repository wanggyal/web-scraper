import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JInternalFrame {
	private JTextField userNameField;
	private JPasswordField passwordField;
	private String userName;
	private String password;
	static Login iframe ;
	UserView uFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					iframe= new Login();
					iframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(true);
		setClosable(true);
		setBounds(new Rectangle(300, 200,417, 300));
		//setBounds(100, 100, 417, 300);
		getContentPane().setLayout(null);
		
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(55, 50, 77, 16);
		getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(55, 103, 77, 16);
		getContentPane().add(lblPassword);
		
		userNameField = new JTextField();
		userNameField.setBounds(161, 45, 140, 26);
		getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String USER=userNameField.getText();//get username from the login window
				final String PASS=passwordField.getText();//get password from the login window

				UserList userList=new UserList();
				int userIndex=-1;
				if((userIndex=userList.checkUser(USER))!=-1) {
					if(userList.checkPassword(userIndex, PASS)) {
						uFrame=new UserView();
						
						//pass the user information
						uFrame.setUser(userList.get(userIndex));
						dispose();
						uFrame.setVisible(true);
					
					}//password if
					
					else JOptionPane.showMessageDialog(null, "Password does not match, Please  Re Enter password.");
				}//username if
				
				else{
				JOptionPane.showMessageDialog(null, "This user does not exist, Please  Re Enter user name or create a new Accouunt.");
				}
			}
		});
		btnLogin.setForeground(new Color(255, 140, 0));
		btnLogin.setBounds(161, 148, 96, 29);
		getContentPane().add(btnLogin);
		
		JButton btnForgotPassword = new JButton("forgot Password");
		btnForgotPassword.setBounds(55, 192, 130, 29);
		getContentPane().add(btnForgotPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 98, 140, 26);
		getContentPane().add(passwordField);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistration userRegistration=new UserRegistration();
				userRegistration.setVisible(true);
				
			}
		});
		btnSignUp.setBounds(206, 192, 117, 29);
		getContentPane().add(btnSignUp);

	}
}
