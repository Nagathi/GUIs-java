package clientview;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clientdata.Cliente;
import clientdata.Corrente;
import clientdata.Poupanca;
import database.ClientesDAO;
import database.Connect;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.io.*;

public class Main {

	private JFrame home;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Main window = new Main();
				window.home.setVisible(true);	
			}
		});
				
	}

	/**
	 * Create the application.
	 */
	public Main() {

		home = new ViewHome();

	}
}