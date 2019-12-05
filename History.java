import java.awt.EventQueue;
import java.util.ArrayList;


import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class History extends JFrame {
	//private ArrayList<String> historyList=new ArrayList<>();
	JList list ;
	private int selection;
	DefaultListModel<String> model = new DefaultListModel<>();
	ArrayList<String>history = new ArrayList<>();
	private ArrayList<ProductList> searchList=new ArrayList<>();
	private String userName;

	//********* to do list ***********
	// when user click on edit it should clear selection should activate under the method
	
	
	
	//add to the historyList
	public void add(String username,String keyword,ArrayList<Product> list) {
		
		userName=username;
		ProductList pList=new ProductList();
		pList.setProductName(keyword);
		pList.setProductList(list);
		searchList.add(pList);
		write();
		model=new DefaultListModel<>();
		for(int i=0;i<searchList.size();i++) {
			model.addElement(searchList.get(i).getProductName());
		}
		
	}
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History frame = new History("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//main
	
	//get the history list by the name and date
	public ArrayList<String> getHistory() {
		
		int size=searchList.size();
		System.out.println("size of the serarch is:"+size);
		ArrayList<String> temp=new ArrayList<>();
		
		for(int i=0;i<size;i++) {
			temp.add(searchList.get(i).getProductName());
		}//for
		
		return temp;
		
	}
	//delete histrory
	public void deleteHistory(int i) {
		searchList.remove(i);
		write();
	
	}
	//writes the data from the updated history list to the text file
	public void write() {
		if(searchList.size()==0) {
			try {
				FileWriter fw=new FileWriter(userName+"history.txt");
				BufferedWriter bf=new BufferedWriter(fw);
				bf.write("");
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//if
		else {
		try {
			FileWriter fw=new FileWriter(userName+"history.txt");
			BufferedWriter bf=new BufferedWriter(fw);
			bf.write(searchList.toString());
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	//read the data from the history file 
	public void read(String user) {
		userName=user;
		ProductList pList=new ProductList();
		try {
			FileReader fr=new FileReader(userName+"history.txt");
			BufferedReader br=new BufferedReader(fr);
			String temp="";
			String dis;
			while((temp=br.readLine())!=null){
				if(temp.contains("[")) {
					temp=temp.substring(1);
					pList=new ProductList();
					pList.setProductName(temp);
				}
				//search key word
				
				//set the prouduct and put them in the product list
				while(true) {
				Product p=new Product();
				String lastString=br.readLine();
				if(lastString==null) {
					break;
				}
				else if(lastString.contains("]")) {
					searchList.add(pList);
					break;
				}
				else if(lastString.contains(",")) {
					searchList.add(pList);
					pList=new ProductList();
					pList.setProductName(lastString.substring(1));
					break;
				}
				p.setTitle(br.readLine());
				p.setPrice(br.readLine());
				p.setMoq(br.readLine());
				br.readLine();
				
				StringBuilder sb=new StringBuilder();
				while(!(dis=br.readLine()).matches("^https.*+")) {
					sb.append(dis);
				}
				p.setDiscription(sb.toString());
				//br.readLine();
				if(dis.matches("^https.*+")) {
				p.setImageUrl(dis);
				pList.add(p);
				} else if(dis.contains(",")){
					searchList.add(pList);
					pList=new ProductList();
					dis=dis.substring(1);
					pList.setProductName(dis);//needed
					break;
				}
				System.out.println(pList.toString());
				}//while true
			} //while not null
		br.close();
		}//try
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		model= new DefaultListModel<>();
		for(int i=0;i<searchList.size();i++) {
			model.addElement(searchList.get(i).getProductName());
		}//for
		
	}

	/**
	 * Create the frame.
	 */
	public History(String s) {
		
		userName=s;
		read(userName);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 6, 353, 210);
		getContentPane().add(scrollPane);
		
		list=new JList(model);
		scrollPane.setViewportView(list);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(318, 219, 67, 29);
		getContentPane().add(btnBack);
		
		JButton btnEdit = new JButton("Delete");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selection=list.getSelectedIndex();
				if(selection>=0 && searchList.size()!=0) 
				{
					deleteHistory(selection);
					model.removeElementAt(selection);
					repaint();
				}
			}
		});
		btnEdit.setBounds(183, 219, 67, 29);
		getContentPane().add(btnEdit);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(49, 219, 61, 29);
		getContentPane().add(btnOpen);

	}

}
