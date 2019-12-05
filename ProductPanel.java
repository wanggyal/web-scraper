import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.border.LineBorder;

public class ProductPanel extends JPanel {
	JTextPane title ;
	JTextPane price;
	JTextPane moq ;
	JTextPane discription;
	JLabel imageLabel;
	String image;
	/**
	 * Create the panel.
	 */
	public ProductPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(new Rectangle(0, 0, 325, 200));
		setLayout(null);
		
		title = new JTextPane();
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		title.setMargin(new Insets(0, 3, 0, 3));
		title.setText("Personalized School Stationery Supplies Custom Logo Metal Ballpoint Pen Title: Personalized School Stationery Supplies.");
		title.setBorder(null);
		title.setForeground(new Color(65, 105, 225));
		title.setEditable(false);
		title.setBounds(0, 0, 325, 55);
		add(title);
		
		price = new JTextPane();
		price.setMargin(new Insets(0, 2, 0, 0));
		price.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		price.setText("Price: US $0.20-$0.50 / Piece");
		price.setForeground(new Color(255, 165, 0));
		price.setBounds(175, 55, 150, 16);
		add(price);
		
		moq = new JTextPane();
		moq.setText("3000 Pieces (Min. Order)");
		moq.setMargin(new Insets(0, 2, 0, 0));
		moq.setForeground(new Color(139, 69, 19));
		moq.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		moq.setBounds(175, 71, 150, 16);
		add(moq);
		
		discription = new JTextPane();
		discription.setMargin(new Insets(3, 3, 0, 0));
		discription.setForeground(new Color(112, 128, 144));
		discription.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		discription.setText("Color: Custom\n  Promotional Pen Body Type: New design\n  Material: Metal\n  Type: Ballpoint Pen\n  Use: Promotional Pen\n  Promotional Pen Type: Logo Pen,promotion pen");
		discription.setBounds(175, 86, 150, 114);
		add(discription);
		
		imageLabel = new JLabel("");
		imageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		imageLabel.setVerticalAlignment(SwingConstants.TOP);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);	
		imageLabel.setBounds(0, 55, 175, 145);
		add(imageLabel);

	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title.setText(title);
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price.setText(price); 
	}
	/**
	 * @param moq the moq to set
	 */
	public void setMoq(String moq) {
		this.moq.setText(moq);
	}
	/**
	 * @param discription the discription to set
	 */
	public void setDiscription(String discription) {
		this.discription.setText(discription); 
	}
	/**
	 * @param image the image to set
	 * @throws IOException 
	 */
	public void setImage(String image) {
		this.image=image;
		try {
			this.Image(this.image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void Image(String image) throws IOException {
		this.image=image;
		Image icon = null;
		URL url;
		try {
			url = new URL(this.image);
			icon=ImageIO.read(url);
			this.imageLabel.setIcon(new ImageIcon(icon)); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
