package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import clientdata.*;

public class ClientesDAO {
	Connection con;
	PreparedStatement pstm;
	ResultSet resSet;
	ArrayList <Cliente>lista_clientes = new ArrayList<Cliente>();
	
	public void cadastrar_cliente(Cliente objCliente) {
		
		String sql = "INSERT INTO clientes (nome, nascimento, cpf, usuario, senha, tipo, emprestimo, saldo, mensal, contaMensal, fatura) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		con = new Connect().connectDB();
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, objCliente.getNome());
			pstm.setString(2, objCliente.getNascimento());
			pstm.setString(3, objCliente.getCpf());
			pstm.setString(4, objCliente.getUsuario());
			pstm.setString(5, objCliente.getSenha());
			pstm.setString(6, objCliente.getTipo());
			pstm.setBoolean(7, objCliente.getEmprestimo());
			pstm.setFloat(8, objCliente.getSaldo());
			pstm.setFloat(9, objCliente.getMensal());
			pstm.setInt(10, objCliente.getContaMensal());
			pstm.setFloat(11, objCliente.getFatura());
			
			pstm.execute();
			pstm.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO: - Método cadastrar_conta: " + e);
		}
	}
	
	public void fechar_cliente(Cliente objCliente) {
		
		String delete = "INSERT INTO clientes_deletados (id, nome, nascimento, cpf, usuario, senha, tipo, emprestimo, saldo, mensal, contaMensal, fatura) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql = "DELETE FROM clientes WHERE usuario = ?";
		
		con = new Connect().connectDB();
		
		try {
			pstm = con.prepareStatement(delete);
			
			pstm.setInt(1, objCliente.getId());
			pstm.setString(2, objCliente.getNome());
			pstm.setString(3, objCliente.getNascimento());
			pstm.setString(4, objCliente.getCpf());
			pstm.setString(5, objCliente.getUsuario());
			pstm.setString(6, objCliente.getSenha());
			pstm.setString(7, objCliente.getTipo());
			pstm.setBoolean(8, objCliente.getEmprestimo());
			pstm.setFloat(9, objCliente.getSaldo());
			pstm.setFloat(10, objCliente.getMensal());
			pstm.setInt(11, objCliente.getContaMensal());
			pstm.setFloat(12, objCliente.getFatura());
			
			pstm.execute();
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, objCliente.getUsuario());
			
			pstm.execute();
			pstm.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO: - Método fechar_conta: " + e);
		}
	}
	
	public ArrayList<Cliente> listar_cliente() {
		
		String sql = "SELECT * FROM clientes";
		con = new Connect().connectDB();

		try {
			pstm = con.prepareStatement(sql);
			resSet = pstm.executeQuery();
			
			while(resSet.next()) {
				Cliente objCliente = new Cliente();
				objCliente.setId(resSet.getInt("id"));
				objCliente.setNome(resSet.getString("nome"));
				objCliente.setNascimento(resSet.getString("nascimento"));
				objCliente.setCpf(resSet.getString("cpf"));
				objCliente.setUsuario(resSet.getString("usuario"));
				objCliente.setSenha(resSet.getString("senha"));
				objCliente.setTipo(resSet.getString("tipo"));
				objCliente.setSaldo(resSet.getFloat("saldo"));
				objCliente.setFatura(resSet.getFloat("fatura"));
				objCliente.setMensal(resSet.getFloat("mensal"));
				objCliente.setEmprestimo(resSet.getBoolean("emprestimo"));
				lista_clientes.add(objCliente);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO - Método listar_conta: " + e);
		}
		
		return lista_clientes;
	}
	
public void alterar_cliente(Cliente objCliente) {
		
		String sql = "UPDATE clientes SET saldo = ?, fatura = ?, mensal = ?, emprestimo = ?  WHERE usuario = ?";
		
		con = new Connect().connectDB();
		
		try {
			pstm = con.prepareStatement(sql);

			pstm.setFloat(1, objCliente.getSaldo());
			pstm.setFloat(2, objCliente.getFatura());
			pstm.setFloat(3, objCliente.getMensal());
			pstm.setBoolean(4, objCliente.getEmprestimo());
			pstm.setString(5, objCliente.getUsuario());
			
			pstm.execute();
			pstm.close();
			
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Classe ClientesDAO: - Método alterar_conta: " + e);
		}
		
	}
}
