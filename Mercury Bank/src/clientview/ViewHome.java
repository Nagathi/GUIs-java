package clientview;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewHome extends JFrame{
	
	private Panel panelHome = new Panel();
	
	private JLabel labelLogo = new JLabel("Mercury Bank");
	
	private JButton btnCadastro = new JButton("Cadastro"),
					btnLogin = new JButton("Login");
	
	public ViewHome() {
		this.setBounds(100, 100, 450, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		panelHome.setBackground(new Color(0, 128, 128));
		panelHome.setBounds(0, 0, 434, 82);
		this.add(panelHome);
		panelHome.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelLogo.setFont(new Font("Yu Gothic UI", Font.PLAIN, 40));
		labelLogo.setForeground(new Color(240, 248, 255));
		panelHome.add(labelLogo);
		
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewCadastro cadastro = new ViewCadastro();
				dispose();
				cadastro.setVisible(true);
				
			}
		});
		btnCadastro.setBackground(new Color(0, 128, 128));
		btnCadastro.setForeground(new Color(240, 248, 255));
		btnCadastro.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCadastro.setBounds(158, 107, 110, 23);
		this.getContentPane().add(btnCadastro);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				ViewLogin login = new ViewLogin();
				dispose();
				login.setVisible(true);
				
			}
		});
		
		btnLogin.setBackground(new Color(0, 128, 128));
		btnLogin.setForeground(new Color(240, 248, 255));
		btnLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnLogin.setBounds(158, 137, 110, 23);
		this.add(btnLogin);
	}
}
