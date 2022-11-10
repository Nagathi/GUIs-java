package clientview;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clientdata.Cliente;
import database.ClientesDAO;

public class ViewLogin extends JFrame{
	
	public JLabel lblUsuario = new JLabel("Usuário: "),
				  lblSenha 	 = new JLabel("Senha: ");
			

	private JPasswordField jtxtSenha = new JPasswordField();
	
	private JTextField jtxtUsuario	= new JTextField();
	
	private ImageIcon spriteBtnVoltar = new ImageIcon(getClass().getResource("spriteBtnVoltar.png"));

	private JButton btnConfirmar = new JButton("Login"),
					btnVoltar 	 = new JButton(spriteBtnVoltar);
	
	public ViewLogin() {
		this.setBounds(100, 100, 450, 350);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		componentes();
	}
	
	private void componentes() {
		JLabel lblUsuario = new JLabel("Usuário: ");
		lblUsuario.setBounds(100, 50, 100, 20);
		jtxtUsuario = new JTextField();
		jtxtUsuario.setBounds(200, 50, 100, 20);
		this.getContentPane().add(lblUsuario);
		this.getContentPane().add(jtxtUsuario);
		
		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(100, 80, 100, 20);
		jtxtSenha = new JPasswordField();
		jtxtSenha.setBounds(200, 80, 100, 20);
		this.getContentPane().add(lblSenha);
		this.getContentPane().add(jtxtSenha);
	
		btnConfirmar.setBounds(200, 110, 100, 25);
		btnConfirmar.setBackground(new Color(0, 128, 128));
		btnConfirmar.setForeground(new Color(240, 248, 255));
		btnConfirmar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		this.getContentPane().add(btnConfirmar);

		btnVoltar.setBounds(10, 10, 50, 50);
		this.add(btnVoltar);
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				ViewHome home = new ViewHome();
				home.setVisible(true);
				
			}
		});
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String getTxtUsuario = jtxtUsuario.getText();
				String getTxtSenha = jtxtSenha.getText();
				
				ClientesDAO dbCliente = new ClientesDAO();
				ArrayList lista_clientes = new ArrayList();
				lista_clientes = dbCliente.listar_cliente();
				
				Iterator<Cliente> it = lista_clientes.iterator();
				
				boolean aprovado = false;
				
				while(it.hasNext()) {
					
					Cliente c = (Cliente) it.next();
					
					if(getTxtUsuario.equals(c.getUsuario()) && getTxtSenha.equals(c.getSenha())) {
						aprovado = true;
						dispose();
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								ViewMenu menu = new ViewMenu(c);
								menu.setVisible(true);	
							}
						});
						

					}
					
				}
				if(!aprovado) {
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta.");
				}
			}
		});
		
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent evt) { 
				ViewHome home = new ViewHome();
				home.setVisible(true);}
		});
		
	}
}
