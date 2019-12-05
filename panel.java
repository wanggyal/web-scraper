import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class panel extends JPanel {
	JPanel panel = new JPanel();
	/**
	 * Create the panel.
	 */
	public panel() {
		setLayout(null);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(227, 118, 196, 24);
		textPane_1.setText("by Wanggyal Sherpa");
		textPane_1.setForeground(SystemColor.controlHighlight);
		textPane_1.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		textPane_1.setEditable(false);
		textPane_1.setBackground(SystemColor.window);
		add(textPane_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(22, 95, 208, 59);
		textPane.setText("Alibaba ");
		textPane.setForeground(new Color(244, 164, 96));
		textPane.setFont(new Font("Lucida Bright", Font.BOLD, 50));
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.window);
		add(textPane);
		
		panel.setBounds(121, 199, 860, 416);
		panel.setLayout(null);
		panel.setVisible(false);

	}
	public void setVisivle(boolean t) {
		this.setVisible(t);
	}

}
