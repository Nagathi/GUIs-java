package clientview;

import java.awt.Color;
import java.util.regex.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clientdata.Cliente;
import clientdata.Corrente;
import clientdata.Poupanca;
import database.ClientesDAO;

public class ViewCadastro extends JFrame{
	
	public JLabel 	lblNome				= new JLabel("Nome: "), 
					lblAno				= new JLabel("Data de Nasc.: "), 
					lblCpf 				= new JLabel("CPF: "), 
					lblUsuario 			= new JLabel("Usuário: "),
					lblSenha 			= new JLabel("Senha: "),
					lblConfirmarSenha 	= new JLabel("Confirmar: ");
					
	
	private JPasswordField jtxtSenha 			= new JPasswordField(), 
						   jtxtConfirmarSenha	= new JPasswordField();
	
	private JTextField jtxtNome 	= new JTextField(),
					   jtxtAno 		= new JTextField(),
					   jtxtCpf 		= new JTextField(),
					   jtxtUsuario	= new JTextField();
			
	private ImageIcon spriteBtnVoltar = new ImageIcon(getClass().getResource("spriteBtnVoltar.png"));
	
	private JButton btnConfirmar = new JButton("Confirmar"),
					btnVoltar 	 = new JButton(spriteBtnVoltar);
	
	private JCheckBox checkCorrente = new JCheckBox("Corrente"),
					  checkPoupanca = new JCheckBox("Poupança");
	
	public ViewCadastro() {
		
		this.setBounds(100, 100, 550, 450);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		componentes();
		
	}
	
	private void componentes() {
		
		lblNome.setBounds(100, 60, 100, 20);
		jtxtNome.setBounds(200, 60, 100, 20);
		this.add(lblNome);
		this.add(jtxtNome);
		
		lblAno.setBounds(100, 90, 100, 20);
		jtxtAno.setBounds(200, 90, 100, 20);
		this.add(lblAno);
		this.add(jtxtAno);
		
		lblCpf.setBounds(100, 120, 100, 20);
		jtxtCpf.setBounds(200, 120, 100, 20);
		this.add(lblCpf);
		this.add(jtxtCpf);
		
		lblUsuario.setBounds(100, 150, 100, 20);
		jtxtUsuario.setBounds(200, 150, 100, 20);
		this.add(lblUsuario);
		this.add(jtxtUsuario);
		
		lblSenha.setBounds(100, 180, 100, 20);
		jtxtSenha.setBounds(200, 180, 100, 20);
		this.add(lblSenha);
		this.add(jtxtSenha);
		
		lblConfirmarSenha.setBounds(100, 210, 100, 20);
		jtxtConfirmarSenha.setBounds(200, 210, 100, 20);
		this.add(lblConfirmarSenha);
		this.add(jtxtConfirmarSenha);
		
		checkCorrente.setBounds(100, 250, 100, 20);
		checkPoupanca.setBounds(200, 250, 100, 20);
		
		this.add(checkCorrente);
		this.add(checkPoupanca);
		
		btnConfirmar.setBounds(200, 290, 100, 25);
		btnConfirmar.setBackground(new Color(0, 128, 128));
		btnConfirmar.setForeground(new Color(240, 248, 255));
		btnConfirmar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		this.add(btnConfirmar);
		
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
				
				if(checkCorrente.isSelected() && checkPoupanca.isSelected()) {
					JOptionPane.showMessageDialog(null, "Selecione apenas um tipo de conta");
				}else {
					String getTxtNome = jtxtNome.getText();
					String getTxtAno = jtxtAno.getText();
					String getTxtCpf = jtxtCpf.getText();
					String getTxtUsuario = jtxtUsuario.getText();
					String getTxtSenha = jtxtSenha.getText();
					String getTxtConfirmarSenha = jtxtConfirmarSenha.getText();
					
					boolean verifyName = false;
					boolean verifyDate = false;
					boolean verifyCpf = false;
					boolean verifyUser = false;
					boolean verifyPass = false;
					boolean verifyConfirmPass = false;
					
					
					if(Pattern.matches("\\D*", getTxtNome)) {
						verifyName = true;
					}
					if(Pattern.matches("([0-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/(2[0-2][0-2][0-9]|19[0-9][0-9])", getTxtAno)) {
						verifyDate = true;
					}
					
					if(Pattern.matches("[0-9][0-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]-[0-9][0-9]", getTxtCpf)) {
						verifyCpf = true;
					}
					if(Pattern.matches(".{5,}", getTxtUsuario)) {
						verifyUser = true;
					}
					if(Pattern.matches(".{5,}+", getTxtSenha)) {
						verifyPass = true;
					}
					if(getTxtSenha.equals(getTxtConfirmarSenha)) {
						verifyConfirmPass = true;
					}
					
					
					if(verifyName && verifyDate && verifyCpf && verifyUser && verifyPass && verifyConfirmPass) {
						
						ClientesDAO dbCliente = new ClientesDAO();
						ArrayList lista_clientes = new ArrayList();
						
						lista_clientes = dbCliente.listar_cliente();
						
						Iterator<Cliente> it = lista_clientes.iterator();
						
						boolean aprovado = true;
						
						while(it.hasNext()) {
							Cliente c = (Cliente) it.next();
							
							if(getTxtUsuario.equals(c.getUsuario())) {
								aprovado = false;
							}
							
							
						}
						
						if(aprovado) {
							if(checkCorrente.isSelected()) {
								
								Cliente cli = new Corrente(getTxtNome, getTxtAno, getTxtCpf, getTxtUsuario, getTxtSenha);
								dbCliente.cadastrar_cliente(cli);
								JOptionPane.showMessageDialog(null, "Cadastrado. Login automático");
								dispose();
								ViewMenu menu = new ViewMenu(cli);
								menu.setVisible(true);
							}
							if(checkPoupanca.isSelected()) {
								
								Cliente cli = new Poupanca(getTxtNome, getTxtAno, getTxtCpf, getTxtUsuario, getTxtSenha);
								dbCliente.cadastrar_cliente(cli);
								
								JOptionPane.showMessageDialog(null, "Cadastrado. Login automático");
								dispose();
								ViewMenu menu = new ViewMenu(cli);
								menu.setVisible(true);
								
							}
							
			
							
						}else {
							JOptionPane.showMessageDialog(null, "Usuário já existe. Tente outro");
						}
						
						
					}
					
					if(!verifyName) {
						JOptionPane.showMessageDialog(null, "Nome deve conter apenas letras");
					}
					if(!verifyDate) {
						JOptionPane.showMessageDialog(null, "Siga os padrões de data: DD/MM/AAAA e certifique-se que seja uma data válida");
					}
					if(!verifyCpf) {
						JOptionPane.showMessageDialog(null, "Siga os padrões de CPF: NNN.NNN.NNN-NN");
					}if(!verifyUser) {
						JOptionPane.showMessageDialog(null, "Usuário com no mínimo 5 dígitos");
					}
					if(!verifyPass) {
						JOptionPane.showMessageDialog(null, "Senha com no mínimo 5 dígitos");
					}
					if(!verifyConfirmPass) {
						JOptionPane.showMessageDialog(null, "Senhas não coincidem");
					}

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
