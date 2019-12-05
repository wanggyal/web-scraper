

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.*;

public class HtmlParser {
	public Product product;
	public String search;
	public String shortHtmlCode="";     
	public ArrayList<Product> productList=new ArrayList<>();
	public ArrayList<String> price=new ArrayList<>();
	public ArrayList<String> minOrder=new ArrayList<>();
	public ArrayList<String> details=new ArrayList<>();
	public ArrayList<String> title=new ArrayList<>();
	public ArrayList<String> image=new ArrayList<>();
	
	//method that will take the search keyword and opens the url
public void HtmlParser(String s) {
		// TODO Auto-generated method stub
		this.search=s;
		try {
			getSourceCode("https://www.alibaba.com/products/"+search+".html?spm=a2700.galleryofferlist.galleryFilter.41.3b263f43itZUBh&IndexArea=product_en&viewtype=L");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error while openeing the url!!!");
			e.printStackTrace();
		}
	}	
	
	
public   String getSourceCode(String url) throws IOException {
	
	URL urlObject=new URL(url);
	URLConnection urlConnection=urlObject.openConnection();
	urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) "
			+ "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.1 Safari/605.1.150");
	
	
	return run(urlConnection.getInputStream());
}

//it reads the html from the connected url and write it to memory using the BufferedReader
public   String run(InputStream inputStream) throws UnsupportedEncodingException, IOException {
	try(BufferedReader buffReader= new BufferedReader( new InputStreamReader(inputStream))){
		String temp;
		StringBuilder stringBuilder=new StringBuilder();
		
		
		while((temp=buffReader.readLine())!=null) {
			
			//System.out.println(temp);
			stringBuilder.append(temp);
		}//while
		
		String bodyString=filterText(stringBuilder.toString());
		
		//************************** beyound this is where all the matching starts ****************************
		findbyClass(bodyString);
		return bodyString;
		
	}
}

//to cut the body tag of html and write it in file as txt
public   String filterText(String htmlCode) throws IOException {
	//x and y are the boundary which gives the html till the last search result in one page
	String x="<body",y="</body>";
	int startIndex=htmlCode.indexOf(x);
	int endIndex=htmlCode.lastIndexOf(y);
	
	//System.out.println("Strar index of body is: "+startIndex+" and end index of body tag is: "+endIndex);
	String newHtmlCode=htmlCode.substring(startIndex, endIndex+y.length());
	shortHtmlCode=newHtmlCode;
	//System.out.println(newHtmlCode);
	try(BufferedWriter buffWriter=new BufferedWriter(new FileWriter("searchResult.txt"))){
		buffWriter.write(newHtmlCode);
		buffWriter.close();
		//check if the bufferedwriter worked
//		System.out.println("Start index of body is: "+startIndex+" and end index of body tag is: "+endIndex);
	}
	
	return newHtmlCode;
	
}

//find the main wrap class
public   void findbyClass(String string2match) {
	
	final String regex = "(?i)(<div\\s+class=\\\"\\s*m-product-item\\s+list-item__v2\\s*searchitem-new-theme\\s*multi-imgs\\s*\\\")(.*?)(\\n)*(.*?)(<div\\s+class=\\\"item-extra\\\"\\s*>)";
	final String string =string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
	int countMatches=0;
		
	
		
		while(matcher.find()) {
			getTitle(matcher.group());
			getPrice(matcher.group());
			getMinClass(matcher.group());
			getDiscriptionClass(matcher.group());
			getImage(matcher.group());
			//add product to the productlist as long as we find a match
			product=new Product();
			productList.add(product);
			countMatches++;
				}//while
	//System.out.println("Total products matches:  "+countMatches);
	toStrings();

}


//price starts
public   void getPrice(String string2match) {
	final String regex = "(class=\\\"price\\\")(.*?)((\\$[0-9]*\\.?[0-9]*)(-\\$[0-9]*\\.?[0-9]*)?)";
	final String string =string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
	if(matcher.find()) {
	
		price.add(matcher.group(3));
		//System.out.println("matches from the class " +matcher.group(3));
				}//if
	
	else {
		price.add("price not availabel.");
	}
}

//no need to use this method
public   void getAmount(String string2match) {
	final String regex = "=(\\$[0-9]*\\.?[0-9]*)(\\-\\$[0-9]*\\.[0-9]*)?";
	final String string =string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
	if(matcher.find()) {
		price.add(matcher.group());
		//System.out.println(" matching price found from amount: " + matcher.group());
		}
	else//need to add something to list if no match found
		System.out.println("No matching price found");
}//price ends

// minimum order starts
public   void getMinClass(String string2match) {
	final String regex = "(?i)<div\\s+(class=\\\"min\\-order\\\"\\s*)>(.*?)<b>\\s*([0-9]*\\s+pieces)(.*?)<\\/div>";
	final String string = string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);

	if(matcher.find()) {
		
		minOrder.add(matcher.group(3));
				
		}//if
	
	else {
		minOrder.add("Minimum amount not available");
	}//else
}
//this method should not be used
public   void getMin(String string2match) {
	final String regex = "(?i)([0-9]+)\\s+pieces";
	final String string = string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
	if(matcher.find()) {
	//System.out.println("from get min  "+matcher.group());
	minOrder.add(matcher.group());}
	//needs to add null or - if no match is found
	 else System.out.println("No matching min order found");
}
//minimum order ends

//Description starts
public   void getDiscriptionClass(String string2match) {
	final String regex = "(class=\\\"kv\\-prop\\\")(.+?)(<div\\s+class=\\\"item-sub\\\">)";
	final String string = string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
	int countMatches=0;
	
	if(matcher.find()) {
		getDetails(matcher.group());
	}
	 else {
		 details.add("No Details available.");
	 }
}
public   void getDetails(String string2match) {
	final String regex = "(class=\\\"kv\\\"\\s+title=)\\\"(.*?)\\\">";
	final String string = string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
	String detail="";
		while(matcher.find()) {
			detail=detail+"\n"+matcher.group(2);
			//System.out.println(matcher.group(2));
			}//while
		
		//System.out.println(detail);
		details.add(detail);
	
}//Description ends


//Titile starts
public   void getTitle(String string2match)
{
	final String regex = "(?i)(<div\\s+class=\\\"title-wrap\\\"\\s*>)(.*?)(information.activityTags=([a-z0-9]*)\">)(.*?)(<\\/a>)";
	final String string =string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
		
		while(matcher.find()) {
			get1Title(matcher.group(5));
				}//while
}
public   void get1Title(String string2match)
{
	final String regex = "(?>[\\w-,.]+)(?<!strong)";
	final String string =string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
	String temp="";
		while(matcher.find()) {
			temp=temp+matcher.group()+" ";
				}//whi
	title.add(temp);
}

public   void getImage(String string2match) {
	final String regex = "<div\\s+class=\\\"util-valign img-wrap\\\".*?jssrc=\\\"(.*?)\\\"(.*?)<\\/div>";
	final String string =string2match;
	Pattern pattern=Pattern.compile(regex);
	Matcher matcher=pattern.matcher(string);
		while(matcher.find()) {
			image.add("https:"+matcher.group(1));
				}//while
}
//get the strings from the arraylist price,minOrder,and details
public   void toStrings() {

	for(int i=0;i<price.size();i++) {
		productList.get(i).setTitle(title.get(i));
		productList.get(i).setPrice(price.get(i));
		productList.get(i).setMoq(minOrder.get(i));
		productList.get(i).setDiscription(details.get(i));
		productList.get(i).setImageUrl(image.get(i));
	}
	
	for(int i=0;i<productList.size();i++) {
		//System.out.println("price: "+productList.get(i).getPrice()+"Minimum quantity: "+productList.get(i).getMoq()+"\nImage url: "+image.get(i));
	}
	
}

//public   void main(String[] args) throws IOException {
//	
//	System.out.println("Type to Search: ");
//	Scanner input=new Scanner(System.in);
//	search=input.nextLine();
//	getSourceCode("https://www.alibaba.com/products/"+search+".html?spm=a2700.galleryofferlist.galleryFilter.41.3b263f43itZUBh&IndexArea=product_en&viewtype=L");
//	
//}

//get the product from product list
public   Product getProduct(int i) {
	if(i<productList.size())
	return productList.get(i);
	else return null;
}

//get the whole product list
public   ArrayList<Product> getProduct() {
	return productList;
}




}//end of class
