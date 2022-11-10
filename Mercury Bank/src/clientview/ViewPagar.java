package clientview;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ViewPagar extends JFrame {
	
	private JTextField recValor = new JTextField();
	
	private JLabel 	   labelValor 	 = new JLabel("Valor: "),
					   labelFatura;
	
	private ImageIcon spriteBtnVoltar = new ImageIcon(getClass().getResource("spriteBtnVoltar.png"));
	
	private JButton    btnPesquisar = new JButton("Pagar"),
					   btnVoltar    = new JButton(spriteBtnVoltar);
	
	private Cliente    objCliente;
	
	public ViewPagar(Cliente objCliente){
		
		this.setBounds(100, 100, 450, 300);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.objCliente = objCliente;
		
		componentes();
		
	}
	
	private void componentes() {
		
		labelFatura = new JLabel("Fatura de " + objCliente.getNome() + ": R$ " + objCliente.getFatura());
		labelFatura.setBounds(58, 59, 350, 40);
		labelFatura.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		
		labelValor.setBounds(58, 109, 130, 23);
		recValor.setBounds(158, 109, 130, 23);
		
		btnPesquisar.setBounds(158, 139, 130, 23);
		btnPesquisar.setForeground(new Color(230, 230, 250));
		btnPesquisar.setFont(new Font("Courier New", Font.BOLD, 14));
		btnPesquisar.setBackground(new Color(148, 0, 211));
		
		btnVoltar.setBounds(10, 10, 50, 50);
		
		this.add(labelFatura);
		this.add(labelValor);
		this.add(recValor);
		this.add(btnPesquisar);
		this.add(btnVoltar);
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pagar();
			}
		});
		
	}
	
	private void pagar() {
		
		ClientesDAO jdbcClientes= new ClientesDAO();
		LogsDAO jdbcLog = new LogsDAO();
		
		float getValorFatura = Float.parseFloat(recValor.getText());
		if(getValorFatura > 0) {
			if(objCliente.getFatura() > 0) {
				
				if(objCliente.getSaldo() >= getValorFatura) {
					if(getValorFatura <= objCliente.getFatura()) {
						
						recValor.setText(null);
						objCliente.pagarFatura(getValorFatura);
						
						if(objCliente.getFatura() == 0) {
							objCliente.setEmprestimo(false);
						}
						
						jdbcClientes.alterar_cliente(objCliente);
						jdbcLog.logPagar(objCliente, getValorFatura);
						
						labelFatura.setText("Fatura de " + objCliente.getNome() + ": R$ " + objCliente.getFatura());
						JOptionPane.showMessageDialog(null, "Fatura paga!");
						
					}else {
						JOptionPane.showMessageDialog(null, "Valor digitado maior que o valor de fatura.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
				}
							
			}else {
				if(objCliente.getFatura() <= 0) {
					objCliente.setEmprestimo(false);
					jdbcClientes.alterar_cliente(objCliente);
				}
				JOptionPane.showMessageDialog(null, "Não possui fatura a ser paga");
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Valor obrigatoriamente maior que zero");
		}
	}

}
