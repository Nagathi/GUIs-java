package clientview;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.mysql.cj.jdbc.JdbcConnection;

import clientdata.Cliente;
import database.ClientesDAO;

public class ViewMenu extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMenu 	= new JPanel(),
				   panelInfo = new JPanel();
	
	private JButton btnSacar = new JButton("Sacar"),
			 		btnSair = new JButton("Sair"),
					btnDepositar = new JButton("Depositar"),
					btnEmprestar = new JButton("Emprestar"),
					btnPagar = new JButton("Pagar");
	
	private ImageIcon minato_front  = new ImageIcon(getClass().getResource("minato_front.png")),
			          cifrao  		= new ImageIcon(getClass().getResource("cifraopng.png"));
	
	private JLabel labelMinato 		= new JLabel(minato_front),
				   labelCifrao 		= new JLabel(cifrao),
				   labelTime = new JLabel("00:00:00");
	
	private Cliente objCliente;
	
	private Timer tm1;
	
	private int seg, min, hour;
	
	public ViewMenu(Cliente objCliente) {
		
		this.setBounds(100, 100, 1366, 768);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(0, 128, 128));
		this.setResizable(false);
		this.setTitle("Mercury Bank");
		
		this.objCliente = objCliente;
		
		componentes();
		
	}
	
	public void componentes() {
		
		panelMenu.setBackground(new Color(255, 255, 255));
		panelMenu.setBounds(0, 0, 1366, 80);
		panelMenu.setLayout(null);
		this.add(panelMenu);
		
		JLabel labelTextMenu = new JLabel("Olá, " + objCliente.getNome() + ". Seu saldo é: R$ " + objCliente.getSaldo());
		labelTextMenu.setBounds(200, 5, 500, 35);
		labelTextMenu.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		panelMenu.add(labelTextMenu);
		
		panelInfo.setBackground(new Color(255, 255, 255));
		panelInfo.setBounds(10, 90, 400, 200);
		panelInfo.setLayout(null);
		this.add(panelInfo);
		
		JLabel labelNomeCliente = new JLabel(objCliente.getNome() + ", suas informações:");
		labelNomeCliente.setBounds(20, 30, 480, 25);
		labelNomeCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		panelInfo.add(labelNomeCliente);
		
		JLabel labelCpfCliente = new JLabel("CPF: " + objCliente.getCpf());
		labelCpfCliente.setBounds(20, 60, 200, 25);
		labelCpfCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		panelInfo.add(labelCpfCliente);
		
		JLabel labelSaldoCliente = new JLabel("Saldo: " + objCliente.getSaldo());
		labelSaldoCliente.setBounds(20, 90, 200, 25);
		labelSaldoCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		panelInfo.add(labelSaldoCliente);
		
		JLabel labelFaturaCliente = new JLabel("Fatura: " + objCliente.getFatura());
		labelFaturaCliente.setBounds(20, 120, 200, 30);
		labelFaturaCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		panelInfo.add(labelFaturaCliente);
		
		String situationEmprestimo = "";
		
		if(objCliente.getEmprestimo()) {
			situationEmprestimo = "ativo";
		}else {
			situationEmprestimo = "inativo";
		}
		
		JLabel labelEmprestimoCliente = new JLabel("Seu empréstimo está " + situationEmprestimo);
		labelEmprestimoCliente.setBounds(20, 150, 400, 30);
		labelEmprestimoCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		panelInfo.add(labelEmprestimoCliente);
		
		
		tm1 = new Timer();
		tm1.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				LocalTime time = LocalTime.now();
						
				seg = time.getSecond();
				min = time.getMinute();
				hour = time.getHour();

				labelTime.setText(String.format("%02d:%02d:%02d", hour, min, seg));
				
				String situationEmprestimo = "";
				
				if(objCliente.getEmprestimo()) {
					situationEmprestimo = "ativo";
				}else {
					situationEmprestimo = "inativo";
				}
				
				labelSaldoCliente.setText("Saldo: " + objCliente.getSaldo());
				labelFaturaCliente.setText("Fatura: " + objCliente.getFatura());
				labelEmprestimoCliente.setText("Seu empréstimo está " + situationEmprestimo);
				labelTextMenu.setText("Olá, " + objCliente.getNome() + ". Seu saldo é: R$ " + objCliente.getSaldo());
			}
		}, 1000, 1000);
		
		
		labelTime.setBounds(625, 130, 95, 30);
		labelTime.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		labelTime.setForeground(new Color(0, 0, 0));
		labelTime.setBackground(new Color(255, 255, 255));
		labelTime.setBorder(BorderFactory.createLineBorder(Color.RED, 4, true));
		this.add(labelTime);
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				ViewHome home = new ViewHome();
				home.setVisible(true);
				
			}
		});
		btnSair.setBackground(new Color(0, 128, 128));
		btnSair.setForeground(new Color(240, 248, 255));
		btnSair.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSair.setBounds(600, 10, 110, 23);
		panelMenu.add(btnSair);
		
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewSacar sacar = new ViewSacar(objCliente);
				sacar.setVisible(true);
				
			}
		});
		btnSacar.setBackground(new Color(0, 128, 128));
		btnSacar.setForeground(new Color(240, 248, 255));
		btnSacar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSacar.setBounds(750, 10, 110, 23);
		panelMenu.add(btnSacar);
		
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewDepositar depositar = new ViewDepositar(objCliente);
				depositar.setVisible(true);
				
			}
		});
		btnDepositar.setBackground(new Color(0, 128, 128));
		btnDepositar.setForeground(new Color(240, 248, 255));
		btnDepositar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnDepositar.setBounds(900, 10, 110, 23);
		panelMenu.add(btnDepositar);
		
		btnEmprestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewEmprestar emprestar = new ViewEmprestar(objCliente);
				emprestar.setVisible(true);
				
				
			}
		});
		btnEmprestar.setBackground(new Color(0, 128, 128));
		btnEmprestar.setForeground(new Color(240, 248, 255));
		btnEmprestar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnEmprestar.setBounds(1050, 10, 110, 23);
		panelMenu.add(btnEmprestar);
		
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewPagar pagar = new ViewPagar(objCliente);
				pagar.setVisible(true);
				
				
			}
		});
		btnPagar.setBackground(new Color(0, 128, 128));
		btnPagar.setForeground(new Color(240, 248, 255));
		btnPagar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnPagar.setBounds(1200, 10, 110, 23);
		panelMenu.add(btnPagar);
		
		labelMinato.setBounds(10, 10, 250, 100);
		labelCifrao.setBounds(400, -30, 250, 100);
		panelMenu.add(labelCifrao);
		panelMenu.add(labelMinato);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(1200, 90, 100, 65);
		panelButtons.setLayout(new FlowLayout());
		panelButtons.setBackground(new Color(0, 128, 128));
		this.add(panelButtons);
		
		JButton btnCloseAccount = new JButton("Fechar Conta");
		panelButtons.add(btnCloseAccount);
		
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(objCliente.getSaldo() <= 0) {
					
					ClientesDAO jdbcClientes = new ClientesDAO();
					jdbcClientes.fechar_cliente(objCliente);
					JOptionPane.showMessageDialog(null, "Conta deletada");
					dispose();
					ViewHome home = new ViewHome();
					home.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "Sua conta precisa estar sem saldo para permitir essa ação");
				}
				
				
			}
		});
		
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent evt) { 
				if (JOptionPane.showConfirmDialog(null,"Deseja sair")==JOptionPane.OK_OPTION){ 
					dispose();
				} 
			} 
		});
	}
}
