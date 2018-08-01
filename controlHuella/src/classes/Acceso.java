package classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Component;

import javax.swing.JTextField;

import java.awt.Rectangle;

import org.eclipse.wb.swing.FocusTraversalOnArray;



import com.jgoodies.forms.layout.FormLayout;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.beans.FeatureDescriptor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import dao.GradosDAO;

import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Cursor;

import conexion.MetodosLogin;

public class Acceso implements ActionListener {
	
	private ReaderCollection m_collection;
	private Reader           m_reader;

	private JFrame frmSistemaDeResgistro;
	
	private static final String ACT_SALIR = "exit";	
	private static final String ACT_INGRESO = "ingreso";
	
	String fechaConFormato;
	
	ButtonGroup radios;
	private JPasswordField textContrasena;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceso window = new Acceso();
					window.m_collection = UareUGlobal.GetReaderCollection();
					window.frmSistemaDeResgistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Acceso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeResgistro = new JFrame();		
		frmSistemaDeResgistro.setBackground(Color.WHITE);
		frmSistemaDeResgistro.setResizable(false);
		frmSistemaDeResgistro.setTitle("Sistema de resgistro y administraci\u00F3n escolar");
		frmSistemaDeResgistro.getContentPane().setBackground(Color.WHITE);
		frmSistemaDeResgistro.setIconImage(Toolkit.getDefaultToolkit().getImage(Acceso.class.getResource("/imagenes/logo2.png")));
		frmSistemaDeResgistro.setBounds(100, 100, 648, 433);
		frmSistemaDeResgistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeResgistro.setLocationRelativeTo(null);
		
		JLabel lblRegistro = new JLabel("Acceso a usuarios registrados");
		lblRegistro.setForeground(new Color(0, 102, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIngresar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 16));
		btnIngresar.setBackground(new Color(0, 102, 0));
		btnIngresar.setActionCommand(ACT_INGRESO);
		btnIngresar.addActionListener(this);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
		btnSalir.setBackground(new Color(0, 102, 0));
		btnSalir.setActionCommand(ACT_SALIR);
		btnSalir.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setForeground(new Color(0, 102, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setForeground(new Color(0, 102, 0));
		lblContrasea.setFont(new Font("Arial", Font.BOLD, 18));
		
		textContrasena = new JPasswordField();
		textContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		textContrasena.setForeground(new Color(0, 102, 0));
		textContrasena.setFont(new Font("Arial", Font.BOLD, 20));
		textContrasena.setColumns(10);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(0, 102, 0));
		textField.setFont(new Font("Arial", Font.BOLD, 20));
		textField.setColumns(10);
		
		
		GroupLayout groupLayout = new GroupLayout(frmSistemaDeResgistro.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblRegistro, GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(116)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textContrasena, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnIngresar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(102)
							.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
					.addGap(148))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistro)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(61)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(textContrasena, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalir, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addComponent(btnIngresar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(83))
		);
		frmSistemaDeResgistro.getContentPane().setLayout(groupLayout);
	}
		
	// Acciones de botones
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals(ACT_SALIR)){		
			frmSistemaDeResgistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
			frmSistemaDeResgistro.dispose();
		}else if(e.getActionCommand().equals(ACT_INGRESO)){
			if(textField.getText().isEmpty() && textContrasena.getText().isEmpty()){
				textField.setBorder(new LineBorder(Color.RED));
				textContrasena.setBorder(new LineBorder(Color.RED));
				JOptionPane.showMessageDialog(frmSistemaDeResgistro, "No debes dejar campos vacios.", "Aviso", JOptionPane.ERROR_MESSAGE);				
			}else if(textField.getText().isEmpty() && !textContrasena.getText().isEmpty()){
				textField.setBorder(new LineBorder(Color.RED));
				textContrasena.setBorder(new LineBorder(Color.decode("#7A8A99")));
				JOptionPane.showMessageDialog(frmSistemaDeResgistro, "Debes introducir un usuario.", "Aviso", JOptionPane.ERROR_MESSAGE);
			}else if(!textField.getText().isEmpty() && textContrasena.getText().isEmpty()){
				textContrasena.setBorder(new LineBorder(Color.RED));
				textField.setBorder(new LineBorder(Color.decode("#7A8A99")));
				JOptionPane.showMessageDialog(frmSistemaDeResgistro, "Debes introducir una contraseña.", "Aviso", JOptionPane.ERROR_MESSAGE);
			}else{
				MetodosLogin metLogin = new MetodosLogin();			
				textField.setBorder(new LineBorder(Color.decode("#7A8A99")));
				textContrasena.setBorder(new LineBorder(Color.decode("#7A8A99")));
				if(metLogin.regresarUsuario(textField.getText(), textContrasena.getText(), frmSistemaDeResgistro)){					
					Principal.run();
					frmSistemaDeResgistro.dispose();
					frmSistemaDeResgistro.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(frmSistemaDeResgistro, "Usuario o contraseña incorrectos.", "Aviso", JOptionPane.ERROR_MESSAGE);		
				}
			}
			
		}
			
	}
	}
