package clientview;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clientdata.Cliente;
import database.ClientesDAO;
import database.LogsDAO;

public class ViewSacar extends JFrame{
	
	private JTextField recValorSacar  = new JTextField();
	private JLabel 	   labelValor 	  = new JLabel("Valor: ");;
	private ImageIcon spriteBtnVoltar = new ImageIcon(getClass().getResource("spriteBtnVoltar.png"));
	private JButton    btnPesquisar   = new JButton("Sacar"),
					   btnVoltar 	  = new JButton(spriteBtnVoltar);
	private Cliente    objCliente;
	
	public ViewSacar(Cliente objCliente){
		
		this.setBounds(100, 100, 450, 300);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.objCliente = objCliente;
		
		componentes();
		
	}
	
	private void componentes() {
		
		labelValor.setBounds(58, 109, 130, 23);
		recValorSacar.setBounds(158, 109, 130, 23);
		
		btnPesquisar.setBounds(158, 139, 130, 23);
		btnPesquisar.setForeground(new Color(230, 230, 250));
		btnPesquisar.setFont(new Font("Courier New", Font.BOLD, 14));
		btnPesquisar.setBackground(new Color(148, 0, 211));
		
		btnVoltar.setBounds(10, 10, 50, 50);
		
		this.add(labelValor);
		this.add(recValorSacar);
		this.add(btnPesquisar);
		this.add(btnVoltar);
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sacar();
			}
		});
		
	}
	
	private void sacar() {
		
		
		ClientesDAO jdbcCliente = new ClientesDAO();
		LogsDAO jdbcLog = new LogsDAO();
		
		float getValor = Float.parseFloat(recValorSacar.getText());
		
		if(getValor > 0) {
			if(objCliente.getSaldo() >= getValor) {
				try {
					
					objCliente.sacar(getValor);
					jdbcCliente.alterar_cliente(objCliente);
					jdbcLog.logSacar(objCliente, getValor);
					JOptionPane.showMessageDialog(null, "Saque efetuado!");
					recValorSacar.setText(null);
					
					
				}catch(Exception e) {
					
				}
						
			}else {
				JOptionPane.showMessageDialog(null, "Não possui saldo suficiente.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Valor obrigatoriamente maior que zero");
		}
	}

}
