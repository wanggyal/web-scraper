
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserRegistration extends JFrame {

	public JPanel contentPane;
	public JTextField fullName;
	public JTextField userName;
	public JTextField email;
	public JPasswordField passwordField;
	public JPasswordField passwordField_1;
	private String name;
	private String uName;
	private String password;
	private String rePassword;
	private String emailid;
	public UserList userList;
	public User user;
	static UserRegistration sframe;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sframe = new UserRegistration();
					sframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserRegistration() {
		setBounds(100, 100, 383, 414);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New User Registration");
		lblNewLabel.setFocusable(false);
		lblNewLabel.setFocusTraversalKeysEnabled(false);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setBackground(new Color(245, 255, 250));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 383, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setForeground(new Color(255, 140, 0));
		lblFullName.setBounds(32, 61, 75, 16);
		contentPane.add(lblFullName);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setForeground(new Color(255, 140, 0));
		lblUserName.setBounds(32, 107, 75, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 140, 0));
		lblPassword.setBounds(32, 152, 75, 16);
		contentPane.add(lblPassword);
		
		JLabel lblEmailId = new JLabel("Re-Type Password:");
		lblEmailId.setForeground(new Color(255, 140, 0));
		lblEmailId.setBounds(32, 200, 120, 16);
		contentPane.add(lblEmailId);
		
		fullName = new JTextField();
		fullName.setBorder(UIManager.getBorder("PasswordField.border"));
		fullName.setBounds(146, 56, 150, 26);
		contentPane.add(fullName);
		fullName.setColumns(10);
		
		userName = new JTextField();
		userName.setColumns(10);
		userName.setBounds(146, 102, 150, 26);
		contentPane.add(userName);
		
		JLabel label = new JLabel("E-mail id:");
		label.setForeground(new Color(255, 140, 0));
		label.setBounds(32, 248, 75, 16);
		contentPane.add(label);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(122, 243, 150, 26);
		contentPane.add(email);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uName=userName.getText();
				name=fullName.getText();
				password=passwordField.getText();
				rePassword=passwordField_1.getText();
				emailid=email.getText();
//TO DO:				//check for the validation of the information
				
				
				
				String temp=name+"\n"+uName+"\n"+password+"\n"+emailid+" ";
				System.out.println(temp);
				// add user to the user list
				user=new User();
				user.setName(name);
				user.setUserName(uName);
				user.setPassword(password);
				user.setEmail(emailid);
				user.setHistory("History");
				
				userList=new UserList();
				userList.addUser(user);
				
				JOptionPane.showMessageDialog(null,"Account Created successfully.");
				dispose();
				
			}
		});
		btnCreateAccount.setForeground(new Color(0, 128, 0));
		btnCreateAccount.setBackground(new Color(224, 255, 255));
		btnCreateAccount.setBounds(119, 289, 125, 29);
		contentPane.add(btnCreateAccount);
		
		JButton btnTermsAndConditions = new JButton("Terms and Conditions");
		btnTermsAndConditions.setBounds(0, 335, 383, 29);
		contentPane.add(btnTermsAndConditions);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 147, 161, 26);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(166, 195, 161, 26);
		contentPane.add(passwordField_1);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
