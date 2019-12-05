import java.util.ArrayList;
import java.util.Hashtable;

public class ProductList {
private String productName;
private ArrayList<Product> productList=new ArrayList<>();
private Hashtable searchList=new Hashtable();
/**
 * @return the productName
 */
public String getProductName() {
	return productName;
}
/**
 * @param productName the productName to set
 */
public void setProductName(String productName) {
	this.productName = productName;
}
/**
 * @return the productList
 */
public ArrayList<Product> getProductList() {
	return productList;
}
/**
 * @param productList the productList to set
 */
public void setProductList(ArrayList<Product> productList) {
	this.productList = productList;
}


public void add(Product p) {
	productList.add(p);

}
public String toString() {
	String temp=this.getProductName()+"\n";
	for(int i=0;i<productList.size();i++) {
		temp=temp+productList.get(i).toString();
	}
	return temp;
}
}
