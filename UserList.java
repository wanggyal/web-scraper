import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserList {
private ArrayList<User> userList=new ArrayList<>();
public FileWriter fw;
public FileReader fr;
User user;

public UserList() {
	try {
		fr=new FileReader(new File("userList.txt"));
		BufferedReader bf=new BufferedReader(fr);
		String temp;
		while((temp=bf.readLine())!=null) {
			user=new User();
			user.setName(temp);
			user.setUserName(bf.readLine());
			user.setPassword(bf.readLine());
			user.setEmail(bf.readLine());
			user.setHistory(bf.readLine());
			userList.add(user);
		}
		bf.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		try{
			fw=new FileWriter("userList.txt");
			BufferedWriter bf=new BufferedWriter(fw);
			if(userList.size()==0) {
			//bf.write("User List:\n");
			bf.close();
			}
		}
		catch(IOException ex) {
			System.out.println("Cannot create a file userlist"+e.getMessage());
		}
		System.out.println("There is no such file as userlist"+e.getMessage());
	}
}

//adds new user to the list
public void addUser(User user) {
	//check if there exist a user with same username and then ask to change if there is
	this.userList.add(user);
	write();
	
}

public void write() {
	String temp=toString();
	System.out.println(temp);
	try {
		fw=new FileWriter("userList.txt");
		BufferedWriter bf=new BufferedWriter(fw);
		//bf.write("User List:\n");
		bf.write(temp);
		bf.close();
	} catch (IOException e) {
		System.out.println("Cannot add new user this time, Please try again. \nIssue: "+e.getMessage());
	}
}

public String toString() {
	String temp="";
	for(int i=0;i<this.userList.size();i++) {
		temp=temp+userList.get(i).getName()+"\n"
				 +userList.get(i).getUserName()+"\n"
				 +userList.get(i).getPassword()+"\n"
				 +userList.get(i).getEmail()+"\n"
				 +userList.get(i).getHistory()+"\n";
	}
	
	return temp;
}

public int checkUser(String username) {
	for(int i=0;i<userList.size();i++) {
		if(userList.get(i).getUserName().equals(username)) return i;
	}
	return -1;
}

public boolean checkPassword(int i, String password) {

	if(i!=-1 && userList.get(i).getPassword().equals(password)) return true;
	
	return false;
}
public User get(int i) {
	return userList.get(i);
	
}

}
