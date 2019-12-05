
public class Product {
private String title;
private String price;
private String moq;
private String discription;
private String imageUrl;

public Product() {
	
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getMoq() {
	return moq;
}

public void setMoq(String moq) {
	this.moq = moq;
}

public String getDiscription() {
	return discription;
}

public void setDiscription(String discription) {
	this.discription = discription;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public String toString() {
	String temp="";
	temp=temp+"\n"+this.getTitle()+"\n"+this.getPrice()+"\n"+this.getMoq()+"\n"+this.getDiscription()+"\n"+this.getImageUrl()+"\n";
	return temp;
}
}
