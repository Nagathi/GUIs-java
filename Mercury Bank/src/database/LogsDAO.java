package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import clientdata.*;

public class LogsDAO {
	
	Connection con;
	PreparedStatement pstm;
	ResultSet resSet;
	ArrayList <String>lista_logs = new ArrayList<String>();
	
	public void logSacar(Cliente objCliente, float valor) {
		
		String sql = "INSERT INTO logs (usuario, log) VALUES (?, ?)";
		String log = "sacou " + valor;
		con = new Connect().connectDB();
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, objCliente.getUsuario());
			pstm.setString(2, log);
			
			
			pstm.execute();
			pstm.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO: - Método logSacar: " + e);
		}
		
	}
	
	public void logDepositar(Cliente objCliente, float valor) {
		
		String sql = "INSERT INTO logs (usuario, log) VALUES (?, ?)";
		String log = "depositou " + valor;
		con = new Connect().connectDB();
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, objCliente.getUsuario());
			pstm.setString(2, log);
			
			
			pstm.execute();
			pstm.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO: - Método logSacar: " + e);
		}
		
	}

	public void logEmprestar(Cliente objCliente, float valor) {
	
		String sql = "INSERT INTO logs (usuario, log) VALUES (?, ?)";
		String log = "emprestou " + valor;
		con = new Connect().connectDB();
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, objCliente.getUsuario());
			pstm.setString(2, log);
			
			
			pstm.execute();
			pstm.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO: - Método logSacar: " + e);
		}
		
	}

	public void logPagar(Cliente objCliente, float valor) {
	
		String sql = "INSERT INTO logs (usuario, log) VALUES (?, ?)";
		String log = "pagou " + valor;
		con = new Connect().connectDB();
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, objCliente.getUsuario());
			pstm.setString(2, log);
			
			
			pstm.execute();
			pstm.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO: - Método logSacar: " + e);
		}
		
	}

}
