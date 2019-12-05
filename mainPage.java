import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import javax.swing.JFormattedTextField;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Panel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;

public class mainPage {
//to do : create another class for HtmlParsering and then when search button is clicked it will pass the item name and HtmlParser will search for the detail of the item.
	//it will then save to an array and it will be stored to a hash table form which can be accessed offline.
	//this array will be accessed by the jtextpane to display it to the frame.
	private JFrame frame;
	private JTextField searchTxt;
	private String search;
	private String[] productDisc;
	public ArrayList<ProductPanel> productPanelList=new ArrayList<>();
	HtmlParser parser=new HtmlParser();
	ProductPanel productPanel;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPage window = new mainPage();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainPage() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame code starts
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JTextPane txtpnYourBuniness = new JTextPane();
		txtpnYourBuniness.setBounds(267, 102, 331, 48);
		txtpnYourBuniness.setText("& your buniness needs");
		txtpnYourBuniness.setForeground(new Color(154, 205, 50));
		txtpnYourBuniness.setFont(new Font("Iowan Old Style", Font.BOLD, 24));
		txtpnYourBuniness.setEditable(false);
		txtpnYourBuniness.setBackground(SystemColor.window);
		frame.getContentPane().add(txtpnYourBuniness);
		
		
		
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(46, 79, 282, 48);
		textPane_3.setText("Find anything you");
		textPane_3.setForeground(new Color(255, 140, 0));
		textPane_3.setFont(new Font("Iowan Old Style", Font.BOLD, 24));
		textPane_3.setEditable(false);
		textPane_3.setBackground(SystemColor.window);
		frame.getContentPane().add(textPane_3);
		//panel
		JFormattedTextField middlePanel = new JFormattedTextField();
		middlePanel.setBounds(25, 162, 815, 481);
		
		
		middlePanel.setBorder(null);
		middlePanel.setEditable(false);
		middlePanel.setFont(new Font("Lucida Grande", Font.PLAIN, 90));
		middlePanel.setBackground(SystemColor.window);
		middlePanel.setForeground(new Color(244, 164, 96));
		middlePanel.setText("            Alibaba ");
		middlePanel.setHorizontalAlignment(SwingConstants.CENTER);
		
		frame.getContentPane().add(middlePanel);
		
		searchTxt = new JTextField();
		searchTxt.setText("Type here");
		searchTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTxt.setText("");
				
			}
		});
		searchTxt.setBounds(817, 49, 130, 26);
		frame.getContentPane().add(searchTxt);
		searchTxt.setColumns(10);
		
		// search button
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(956, 46, 91, 34);
		frame.getContentPane().add(btnSearch);
		btnSearch.setSelectedIcon(new ImageIcon("/Users/wanggyalsherpa/Downloads/Unknown-4"));
		btnSearch.setBackground(new Color(255, 250, 250));
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
						parser=new HtmlParser();
						middlePanel.setVisible(false);
						String s=searchTxt.getText();
						parser.HtmlParser(s);
						int size=parser.getProduct().size();
						
						//clears the panel if search is successful
						if(parser.getProduct().size()!=0) {
							for(int i=0;i<productPanelList.size();i++) {
								
								if(productPanelList.size()!=0) {	
									
									productPanelList.get(i).setVisible(false);	
								}//if
								frame.repaint(); 
							}
						}
						else {
							JOptionPane.showMessageDialog(frame, "No result found, Check your Internet connection and try again!!!");
						}
						//for starting point of the panel
						int initialX=25, initialY=180;
						
						int x=initialX;
						int y=initialY; //starting point of panel
						
						//loop to create a panel that contains the products details
						int k=0;
						System.out.println("Code ran up to line 148");
						for(int i=0;i<2;i++){
							
							for(int j=0;j<3;j++) {
								productPanel=new ProductPanel();
								productPanel.setLocation(x, y);
								
								productPanel.setTitle(parser.getProduct(k).getTitle());
								productPanel.setPrice(parser.getProduct(k).getPrice());
								productPanel.setMoq(parser.getProduct(k).getMoq());
								productPanel.setDiscription(parser.getProduct(k).getDiscription());
								productPanel.setImage(parser.getProduct(k).getImageUrl());
								
								productPanelList.add(productPanel);
								
								//mainPanel.setBounds(27,110,1053,y);
								frame.getContentPane().add(productPanel);
								productPanel.revalidate();
								productPanel.repaint();
								frame.revalidate();
								frame.repaint();
								x=x+productPanel.getWidth()+20;
								k++;
								
							}
							x=initialX;
							y=y+productPanel.getHeight()+20;
							productPanel.revalidate();
							productPanel.repaint();
							frame.revalidate();
							frame.repaint();
						}
			}
		});
		btnSearch.setForeground(new Color(255, 153, 51));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(801, 112, 91, 29);
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			//
			public void actionPerformed(ActionEvent e) {
				if(parser.getProduct().size()!=0) {
					for(int i=0;i<productPanelList.size();i++) {
						
						if(productPanelList.size()!=0) {	
							
							productPanelList.get(i).setVisible(false);	
						}//if
						frame.repaint(); 
					}
				}
				
				Login login=new Login();
				middlePanel.setVisible(false);
				frame.getContentPane().add(login);
				login.toFront();
				login.setVisible(true);
			}
		});
		btnLogin.setBackground(new Color(192, 192, 192));
		btnLogin.setForeground(new Color(205, 133, 63));
		
		JButton btnSignup = new JButton("Sign up");
		btnSignup.setBounds(933, 116, 86, 29);
		frame.getContentPane().add(btnSignup);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistration ur=new UserRegistration();
				ur.setVisible(true);
			}
		});
		btnSignup.setBackground(new Color(192, 192, 192));
		btnSignup.setForeground(new Color(210, 105, 30));
		
		JTextPane txtpnLoginOrCreate = new JTextPane();
		txtpnLoginOrCreate.setBounds(785, 89, 287, 22);
		frame.getContentPane().add(txtpnLoginOrCreate);
		txtpnLoginOrCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtpnLoginOrCreate.setForeground(new Color(128, 0, 0));
		txtpnLoginOrCreate.setBackground(SystemColor.window);
		txtpnLoginOrCreate.setText("Login or create account for better experience.");
		
				
				JTextPane textPane_2 = new JTextPane();
				textPane_2.setBounds(0, 1, 1100, 33);
				frame.getContentPane().add(textPane_2);
				textPane_2.setMargin(new Insets(0, 515, 0, 0));
				textPane_2.setText("Alibaba");
				textPane_2.setForeground(new Color(255, 153, 51));
				textPane_2.setFont(new Font("Lucida Grande", Font.BOLD, 24));
				textPane_2.setEditable(false);
				textPane_2.setBackground(Color.WHITE);
				//menuBar.setVisible(false);
		//frame code ends
	}
	
public void login() {
	
//	menuBar.setVisible(true);
//	menuBar.revalidate();
//	menuBar.repaint();
	
	
	System.out.println("login successful.");
	
}
}

