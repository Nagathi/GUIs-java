package clientview;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clientdata.Cliente;
import database.ClientesDAO;
import database.LogsDAO;

public class ViewDepositar extends JFrame{
	
	private JTextField recValorDepositar = new JTextField();
	
	private JLabel 	   labelValor 	 = new JLabel("Valor: ");
	
	private ImageIcon spriteBtnVoltar = new ImageIcon(getClass().getResource("spriteBtnVoltar.png"));
	
	private JButton    btnPesquisar = new JButton("Depositar"),
					   btnVoltar 	  = new JButton(spriteBtnVoltar);
	
	private Cliente    objCliente;
	
	public ViewDepositar(Cliente objCliente){
		
		this.setBounds(100, 100, 450, 300);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.objCliente = objCliente;
		
		componentes();
		
	}
	
	private void componentes() {
		labelValor.setBounds(58, 109, 130, 23);
		recValorDepositar.setBounds(158, 109, 130, 23);
		
		btnPesquisar.setBounds(158, 139, 130, 23);
		btnPesquisar.setForeground(new Color(230, 230, 250));
		btnPesquisar.setFont(new Font("Courier New", Font.BOLD, 14));
		btnPesquisar.setBackground(new Color(148, 0, 211));
		
		btnVoltar.setBounds(10, 10, 50, 50);
		
		this.add(labelValor);
		this.add(recValorDepositar);
		this.add(btnPesquisar);
		this.add(btnVoltar);
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositar();
			}
		});
		
	}
	
	private void depositar() {
		
	
		ClientesDAO jdbcClientes = new ClientesDAO();
		LogsDAO jdbcLog = new LogsDAO();
		
		float getValor = Float.parseFloat(recValorDepositar.getText());
		
		if(getValor > 0) {
			objCliente.depositar(getValor);
			
			jdbcClientes.alterar_cliente(objCliente);
			jdbcLog.logDepositar(objCliente, getValor);
			
			JOptionPane.showMessageDialog(null, "Depósito efetuado!");
			recValorDepositar.setText(null);
		}else {
			JOptionPane.showMessageDialog(null, "Valor obrigatoriamente maior que zero");
		}
					
	}			
}
