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

import clases.Grados;

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
import javax.swing.JProgressBar;

public class Principal implements ActionListener {
	
	private ReaderCollection m_collection;
	private Reader           m_reader;

	private JFrame frmSistemaDeResgistro;
	
	private static final String ACT_SALIR = "exit";
	private static final String ACT_REGISTRO = "registro";
	private static final String ACT_REGISTRO_HUELLA = "huella";
	private static final String ACT_GUARDAR = "guardar";
	private static final String ACT_ASISTENCIA = "asistencia";
	String fechaConFormato;
	
	ButtonGroup radios;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() {
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
		frmSistemaDeResgistro.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Underline@2x.png")));
		frmSistemaDeResgistro.setBounds(100, 100, 756, 482);
		frmSistemaDeResgistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeResgistro.setLocationRelativeTo(null);
		
		JLabel lblRegistro = new JLabel("Panel principal");
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnAsistencia = new JButton();
		btnAsistencia.setText("<html><p>Registro de asistencia por huella</p></html>");
		btnAsistencia.setVerticalTextPosition(SwingConstants.BOTTOM);		
		btnAsistencia.setForeground(Color.WHITE);
		btnAsistencia.setBackground(SystemColor.textHighlight);
		btnAsistencia.setFont(new Font("Arial", Font.BOLD, 16));
		btnAsistencia.setActionCommand(ACT_ASISTENCIA);
		btnAsistencia.addActionListener(this);
		
		
		// Boton para salir de la ventana
		JButton btnRegistro = new JButton();
		btnRegistro.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRegistro.setText("<html><p>Alta de estudiantes y personal</p></html>");
		btnRegistro.setForeground(Color.WHITE);
		btnRegistro.setBackground(SystemColor.textHighlight);
		btnRegistro.setFont(new Font("Arial", Font.BOLD, 16));
		btnRegistro.setActionCommand(ACT_REGISTRO);
		btnRegistro.addActionListener(this);
		
		JButton btnRegistroDeHuella = new JButton("Registro de huella");
		btnRegistroDeHuella.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegistroDeHuella.setForeground(Color.WHITE);
		btnRegistroDeHuella.setFont(new Font("Arial", Font.BOLD, 16));
		btnRegistroDeHuella.setBackground(SystemColor.textHighlight);
		btnRegistroDeHuella.setActionCommand(ACT_REGISTRO_HUELLA);
		btnRegistroDeHuella.addActionListener(this);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
		btnSalir.setBackground(SystemColor.textHighlight);
		btnSalir.setActionCommand(ACT_SALIR);
		btnSalir.addActionListener(this);
		
		
		GroupLayout groupLayout = new GroupLayout(frmSistemaDeResgistro.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblRegistro, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(83)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAsistencia, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRegistroDeHuella, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
					.addGap(112)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRegistro, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistro)
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnRegistro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAsistencia, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRegistroDeHuella, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		frmSistemaDeResgistro.getContentPane().setLayout(groupLayout);
	}
	
	
	// Acciones de botones
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals(ACT_SALIR)){		
			frmSistemaDeResgistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
			frmSistemaDeResgistro.dispose();
		}else if(e.getActionCommand().equals(ACT_REGISTRO)){		
			Registro.run();
			
		}else if(e.getActionCommand().equals(ACT_REGISTRO_HUELLA)){
			try{
				m_collection.GetReaders();
			} 
			catch(UareUException e2) { 
				MessageBox.DpError("ReaderCollection.GetReaders()", e2);
			}
			try {
				m_reader = m_collection.get(0);
				//Capabilities.Show(m_reader);
				RegistroHuella.run(m_reader, false);
			}catch (ArrayIndexOutOfBoundsException ee) {
				MessageBox.Warning("No hay lector de huellas conectado");
			} 
			
			}else if(e.getActionCommand().equals(ACT_ASISTENCIA)){
				try{
					m_collection.GetReaders();
				} 
				catch(UareUException e2) { 
					MessageBox.DpError("ReaderCollection.GetReaders()", e2);
				}
				try {
					m_reader = m_collection.get(0);
					//Capabilities.Show(m_reader);
					Asistencia.Run(m_reader, false);
				}catch (ArrayIndexOutOfBoundsException ee) {
					MessageBox.Warning("No hay lector de huellas conectado");
				} 
				
				}
			
	}
	}
