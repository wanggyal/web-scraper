import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Panel;
import javax.swing.JMenuBar;
import javax.swing.border.LineBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

public class UserView extends JFrame {
	

	private JPanel contentPane;
	private  JTextPane txtpnAlibaba = new JTextPane();
	private String name;
	private String userName;
	private String password;
	private String email;
	private String historyString;
	//private ArrayList< ArrayList<Product>> history=new ArrayList<>(); 
	private Date date;
	private String temp;
	private String search;
	private ArrayList<ProductList> searchList=new ArrayList<>();
	private JTextField searchTxt;
	
	public History hFrame= new History(userName);
	public ArrayList<ProductPanel> productPanelList=new ArrayList<>();
	public Panel mainPanel;
	public ProductPanel productPanel=new ProductPanel(); 
	public HtmlParser parser;
	public JLabel welcome;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		contentPane.setForeground(new Color(255, 140, 0));
		contentPane.setBackground(SystemColor.window);
		contentPane.setRequestFocusEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txtpnAlibaba.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		txtpnAlibaba.setEditable(false);
		txtpnAlibaba.setBounds(57, 0, 1043, 30);
		txtpnAlibaba.setForeground(new Color(255, 140, 0));
		txtpnAlibaba.setText("  \t\t\t\t\tAlibaba");
		contentPane.add(txtpnAlibaba);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(255, 255, 255)));
		menuBar.setBounds(0, 0, 99, 30);
		contentPane.add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setSelected(true);
		mnMenu.setForeground(new Color(255, 140, 0));
		menuBar.add(mnMenu);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.setForeground(new Color(255, 140, 0));
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), "User Name: "+getUserName()+"\n"+"Name: "+getName()+"\n"+"Email: "+getEmail());
			}
		});
		mnMenu.add(mntmProfile);
		
		JSeparator separator = new JSeparator();
		mnMenu.add(separator);
		
		JMenuItem mntmHistory = new JMenuItem("History");
		mntmHistory.setForeground(new Color(255, 140, 0));
		mntmHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				hFrame.read(getUserName());
//				hFrame.revalidate();
				hFrame=new History(userName);
				hFrame.setVisible(true);
			}
		});
		
		mnMenu.add(mntmHistory);
		
		
		JSeparator separator_1 = new JSeparator();
		mnMenu.add(separator_1);
		
		JMenuItem mntmSetting = new JMenuItem("Setting");
		mntmSetting.setForeground(new Color(255, 140, 0));
		mnMenu.add(mntmSetting);
		
		JSeparator separator_2 = new JSeparator();
		mnMenu.add(separator_2);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmLogOut.setForeground(new Color(255, 140, 0));
		mnMenu.add(mntmLogOut);
		
		searchTxt = new JTextField();
		searchTxt.setText("Type here");
		searchTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTxt.setText("");
				
			}
		});
		searchTxt.setBounds(847, 42, 130, 26);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);
		
		mainPanel = new Panel();
		mainPanel.setBackground(SystemColor.window);
		mainPanel.setBounds(27, 110, 1053, 635);
		mainPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setPreferredSize(new Dimension(1000, 600));
		scrollPane.setDoubleBuffered(true);
		scrollPane.setAutoscrolls(true);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 110, 1053, 635);
		scrollPane.getViewport().setPreferredSize(new Dimension(1000,600));
		scrollPane.setViewportView(mainPanel);
		contentPane.add(scrollPane);
	
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parser=new HtmlParser();
				String s=searchTxt.getText();
				parser.HtmlParser(s);//opens the urls with the search keyword
				
				//clears the panel if search is successful
				if(parser.getProduct().size()!=0) {
					for(int i=0;i<productPanelList.size();i++) {
						if(productPanelList.size()!=0) {	
							productPanelList.get(i).setVisible(false);	
						}//if
						repaint(); 
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No result found, Check your Internet connection and try again!!!");
				}
				date=new Date();
				
				//save the history to secondary memory
				temp=s+ "    "+date.toString();
				
				//add history to the list
				//hFrame=new History(userName);
				hFrame.add(userName,temp,parser.getProduct());
				
				//System.out.println(temp);
				
				
				//for starting point of the panel
				int initialX=15, initialY=15;
				
				int x=initialX;
				int y=initialY; //starting point of panel
				
				//loop to create a panel that contains the products details
				int k=0;
				System.out.println("Code ran up to line 148");
				for(int i=0;i<15;i++){
					
					for(int j=0;j<3;j++) {
						productPanel=new ProductPanel();
						productPanel.setLocation(x, y);
						
						productPanel.setTitle(parser.getProduct(k).getTitle());
						productPanel.setPrice(parser.getProduct(k).getPrice());
						productPanel.setMoq(parser.getProduct(k).getMoq());
						productPanel.setDiscription(parser.getProduct(k).getDiscription());
						productPanel.setImage(parser.getProduct(k).getImageUrl());
						productPanelList.add(productPanel);
						scrollPane.revalidate();
						//mainPanel.setBounds(27,110,1053,y);
						mainPanel.add(productPanel);
						productPanel.revalidate();
						productPanel.repaint();
						mainPanel.revalidate();
						mainPanel.repaint();
						x=x+productPanel.getWidth()+20;
						k++;
						
					}
					x=initialX;
					y=y+productPanel.getHeight()+20;
					productPanel.revalidate();
					productPanel.repaint();
					mainPanel.revalidate();
					mainPanel.repaint();
				}
				System.out.println("Code ran up to line 170");
				scrollPane.revalidate();
				mainPanel.revalidate();
				mainPanel.repaint();
				
				
			}
		});
		btnSearch.setBounds(989, 42, 85, 29);
		contentPane.add(btnSearch);
		
		welcome = new JLabel("");
		welcome.setForeground(new Color(128, 0, 0));
		welcome.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		welcome.setBounds(291, 44, 400, 29);
		welcome.setText("welcome "+this.getUserName());
		contentPane.add(welcome);
		
		
		
		
		//*******************to do*******************
		//write the product as text file with search keyword on top
		// create an arraylist of the productpanel and display 6 at a time, if there are more than 6 then activate next button or ....678 depending on number of pages
	}
	//set the user information
	public void setUser(User user) {
		this.setUserName(user.getUserName());
		this.setName(user.getName());
		this.setPassword(user.getPassword());
		this.setEmail(user.getEmail());
		this.setHistoryString(user.getHistory());
		
		//show the welcome message
		welcome.setText("Welcome "+this.getUserName());
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the history
	 */
	public String getHistoryString() {
		return historyString;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistoryString(String history) {
		this.historyString = history;
	}
}
