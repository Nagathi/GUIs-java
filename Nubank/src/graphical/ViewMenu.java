package graphical;

import java.awt.Color;

import javax.swing.JFrame;

public class ViewMenu extends JFrame{
	public ViewMenu() {
		this.setBackground(new Color(230, 230, 250));
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
	}
}
