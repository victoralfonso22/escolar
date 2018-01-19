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
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import java.util.Vector;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import dao.AlumnoDAO;
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
import java.awt.peer.PanelPeer;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Cursor;

import javax.swing.JLayeredPane;

import java.awt.Point;

public class RegistroPersonal implements ActionListener {
	
	

	private JDialog frmRegistro;
	private JTextField textCurp;
	private JTextField textNombre;
	private JTextField textTutorNom;
	private JTextField textTutorOcupa;
	private JDateChooser dateFechaNacimiento;
	private JPanel panelAlumno;
	
	private static final String ACT_SALIR = "exit";
	private static final String ACT_PERSONAL = "personal";
	private static final String ACT_ALUMNO = "alumno";
	private static final String ACT_GUARDAR = "guardar";
	
	private JTextField textEdad;
	private JTextField textCalle;
	private JTextField textNumCasa;
	private JTextField textColonia;
	private JTextField textCp;
	private JTextField textNumeroTel;
	
	private JComboBox comboGenero;
	private JLabel labelGrado;
	String fechaConFormato;
	String fechaConFormatoBase;
	private JLabel lblDatosGeneralesDel;
	private JTextField textRfc;
	private JLabel lblNmeroDePersonal;
	private JTextField textNumPersonal;
	private JLabel lblDatosPersonales;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroPersonal window = new RegistroPersonal();
					window.frmRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistroPersonal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistro = new JDialog((JDialog)null,null, true);
		frmRegistro.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);;
		frmRegistro.setBackground(Color.WHITE);
		frmRegistro.setResizable(false);
		frmRegistro.setTitle("Sistema de resgistro y administraci\u00F3n escolar");
		frmRegistro.getContentPane().setBackground(Color.WHITE);
		frmRegistro.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroPersonal.class.getResource("/imagenes/logo2.png")));
		frmRegistro.setBounds(100, 100, 1143, 727);
		frmRegistro.setLocationRelativeTo(null);

		JLabel lblRegistro = new JLabel("Registro de datos para personal");
		lblRegistro.setForeground(new Color(0, 102, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);		
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(new Color(0, 102, 0));
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 15));
		btnGuardar.setActionCommand(ACT_GUARDAR);
		btnGuardar.addActionListener(this);
		
		
		// Boton para salir de la ventana
		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(0, 102, 0));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancelar.setActionCommand(ACT_SALIR);
		btnCancelar.addActionListener(this);
		
		// Agrupamos los radioButtons
		
		//radios = new ButtonGroup();
		
		//radios.add(rdbtnAlumno);
		//radios.add(rdbtnPersonal);
		
		/***** SOLO PERMITE NÚMEROS**************/
		
		/* Evento que se lanza cuando cambia de valor la fecha de nacimiento  */
		
		panelAlumno = new JPanel();
		panelAlumno.setLocation(new Point(5, 5));
		panelAlumno.setBackground(Color.WHITE);
		panelAlumno.setForeground(SystemColor.textHighlight);
		panelAlumno.setBorder(new LineBorder(new Color(0, 153, 0), 2, true));;
		
		textTutorNom = new JTextField();
		textTutorNom.setFont(new Font("Arial", Font.BOLD, 14));
		textTutorNom.setColumns(10);
		
		JLabel labelTutor = new JLabel("Nombre");
		labelTutor.setHorizontalAlignment(SwingConstants.CENTER);
		labelTutor.setHorizontalTextPosition(SwingConstants.CENTER);
		labelTutor.setForeground(new Color(0, 102, 0));
		labelTutor.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel labelTutorOcupa = new JLabel("Ocupaci\u00F3n");
		labelTutorOcupa.setHorizontalAlignment(SwingConstants.CENTER);
		labelTutorOcupa.setHorizontalTextPosition(SwingConstants.CENTER);
		labelTutorOcupa.setForeground(new Color(0, 102, 0));
		labelTutorOcupa.setFont(new Font("Arial", Font.BOLD, 14));
		
		textTutorOcupa = new JTextField();
		textTutorOcupa.setFont(new Font("Arial", Font.BOLD, 14));
		textTutorOcupa.setColumns(10);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setForeground(new Color(0, 102, 0));
		lblCalle.setFont(new Font("Arial", Font.BOLD, 14));
		
		textCalle = new JTextField();
		textCalle.setFont(new Font("Arial", Font.BOLD, 14));
		textCalle.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setForeground(new Color(0, 102, 0));
		lblNmero.setFont(new Font("Arial", Font.BOLD, 14));
		
		textNumCasa = new JTextField();
		textNumCasa.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent evt) {
				try {
					char car = evt.getKeyChar();
			        if( textNumCasa.getText().length()>=8 ) evt.consume();
			        if(( car<'0' || car>'9' )) evt.consume();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo solo permite números.", "Aviso", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		textNumCasa.setFont(new Font("Arial", Font.BOLD, 14));
		textNumCasa.setColumns(10);
		
		JLabel lblColonia = new JLabel("Colonia");
		lblColonia.setForeground(new Color(0, 102, 0));
		lblColonia.setFont(new Font("Arial", Font.BOLD, 14));
		
		textColonia = new JTextField();
		textColonia.setFont(new Font("Arial", Font.BOLD, 14));
		textColonia.setColumns(10);
		
		JLabel lblCdigoPostal = new JLabel("C\u00F3digo postal");
		lblCdigoPostal.setForeground(new Color(0, 102, 0));
		lblCdigoPostal.setFont(new Font("Arial", Font.BOLD, 14));
		
		textCp = new JTextField();
		textCp.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent evt) {
				try {
					char car = evt.getKeyChar();
			        if( textCp.getText().length()>=5 ) evt.consume();
			        if(( car<'0' || car>'9' )) evt.consume();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo solo permite números.", "Aviso", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		textCp.setFont(new Font("Arial", Font.BOLD, 14));
		textCp.setColumns(10);
		
		JLabel lblNmeroTelefnico = new JLabel("N\u00FAmero telef\u00F3nico");
		lblNmeroTelefnico.setForeground(new Color(0, 102, 0));
		lblNmeroTelefnico.setFont(new Font("Arial", Font.BOLD, 14));
		
		textNumeroTel = new JTextField();
		textNumeroTel.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent evt) {
				try {
					char car = evt.getKeyChar();
			        if( textNumeroTel.getText().length()>=10 ) evt.consume();
			        if(( car<'0' || car>'9' )) evt.consume();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo solo permite números.", "Aviso", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		textNumeroTel.setFont(new Font("Arial", Font.BOLD, 14));
		textNumeroTel.setColumns(10);
		
		JLabel lblDatosDelPadre = new JLabel("Datos del padre o tutor");
		lblDatosDelPadre.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDelPadre.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setForeground(new Color(0, 102, 0));
		lblFechaDeNacimiento.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		// seleccion de fecha de nacimiento
		dateFechaNacimiento = new JDateChooser();
		dateFechaNacimiento.setFont(new Font("Arial", Font.BOLD, 14));
		dateFechaNacimiento.setToolTipText("dd/MM/YYYY");
		dateFechaNacimiento.setDateFormatString("dd/MM/YYYY");
		dateFechaNacimiento.addPropertyChangeListener("date", new PropertyChangeListener() {
			
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate ahora = LocalDate.now();		
			
		    public void propertyChange(PropertyChangeEvent evt) {
		        Date date = (Date)evt.getNewValue();
		        
		        if (date != null){
		        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
		        LocalDate fechaNac = LocalDate.parse(formatoFecha.format(date), fmt);
		        Period periodo = Period.between(fechaNac, ahora);
		        String valor = "Tu edad es:"+ periodo.getYears() +" años, "+ periodo.getMonths() +" meses y "+periodo.getDays()+" días";
		        textEdad.setText(Integer.toString(periodo.getYears()));
		        }
		    }
		});
		
		textEdad = new JTextField();		
		textEdad.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyTyped(KeyEvent evt) {
				try {
					char car = evt.getKeyChar();
			        if( textEdad.getText().length()>=8 ) evt.consume();
			        if(( car<'0' || car>'9' )) evt.consume();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmRegistro, "El campo solo permite números.", "Aviso", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		textEdad.setFont(new Font("Arial", Font.BOLD, 14));
		textEdad.setColumns(10);
		

		// label e input de edad
		
		JLabel labelEdad = new JLabel("Edad");
		labelEdad.setForeground(new Color(0, 102, 0));
		labelEdad.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		labelGrado = new JLabel("G\u00E9nero");
		labelGrado.setForeground(new Color(0, 102, 0));
		labelGrado.setFont(new Font("Arial", Font.BOLD, 14));
		
		comboGenero = new JComboBox();
		comboGenero.setFont(new Font("Arial", Font.BOLD, 14));
		comboGenero.setBackground(Color.WHITE);
		
		comboGenero.addItem("Selecciona");
		
		JLabel labelNombre = new JLabel("Nombre completo");
		labelNombre.setForeground(new Color(0, 102, 0));
		labelNombre.setFont(new Font("Arial", Font.BOLD, 14));
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Arial", Font.BOLD, 14));
		textNombre.setColumns(10);
		
		textCurp = new JTextField();
		textCurp.setBorder(UIManager.getBorder("TextField.border"));
		textCurp.setFont(new Font("Arial", Font.BOLD, 14));
		textCurp.setColumns(10);
		
		JLabel lblCurp = new JLabel("Curp");
		lblCurp.setForeground(new Color(0, 102, 0));
		lblCurp.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblDatosGeneralesDel = new JLabel("Datos generales");
		lblDatosGeneralesDel.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosGeneralesDel.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblRfc = new JLabel("RFC");
		lblRfc.setForeground(new Color(0, 102, 0));
		lblRfc.setFont(new Font("Arial", Font.BOLD, 14));
		
		textRfc = new JTextField();
		textRfc.setFont(new Font("Arial", Font.BOLD, 14));
		textRfc.setColumns(10);
		textRfc.setBorder(UIManager.getBorder("TextField.border"));
		
		lblNmeroDePersonal = new JLabel("N\u00FAmero de personal");
		lblNmeroDePersonal.setForeground(new Color(0, 102, 0));
		lblNmeroDePersonal.setFont(new Font("Arial", Font.BOLD, 14));
		
		textNumPersonal = new JTextField();
		textNumPersonal.setFont(new Font("Arial", Font.BOLD, 14));
		textNumPersonal.setColumns(10);
		textNumPersonal.setBorder(UIManager.getBorder("TextField.border"));
		
		lblDatosPersonales = new JLabel("Datos personales");
		lblDatosPersonales.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosPersonales.setFont(new Font("Arial", Font.BOLD, 15));
		GroupLayout gl_panelAlumno = new GroupLayout(panelAlumno);
		gl_panelAlumno.setHorizontalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(147)
							.addComponent(lblCdigoPostal, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textCp, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(79)
							.addComponent(lblNmeroTelefnico, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textNumeroTel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 223, Short.MAX_VALUE))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_panelAlumno.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelAlumno.createSequentialGroup()
									.addComponent(labelTutor, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textTutorNom, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
									.addGap(123)
									.addComponent(labelTutorOcupa, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textTutorOcupa, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelAlumno.createSequentialGroup()
									.addComponent(lblCalle, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textCalle, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
									.addComponent(lblNmero, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textNumCasa, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addGap(37)
									.addComponent(lblColonia, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textColonia, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)))
							.addGap(31))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(20)
							.addComponent(lblFechaDeNacimiento)
							.addGap(18)
							.addComponent(dateFechaNacimiento, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(labelEdad, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textEdad, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(labelGrado)
							.addGap(18)
							.addComponent(comboGenero, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addGap(27))
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addComponent(labelNombre)
							.addGap(18)
							.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(lblCurp, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textCurp, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNmeroDePersonal, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textNumPersonal, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(354)
							.addComponent(lblRfc, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textRfc, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
				.addComponent(lblDatosPersonales, GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
				.addComponent(lblDatosGeneralesDel, GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
				.addComponent(lblDatosDelPadre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
		);
		gl_panelAlumno.setVerticalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addComponent(lblDatosGeneralesDel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addComponent(labelNombre)
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
								.addComponent(textCurp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCurp))
							.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
								.addComponent(textNumPersonal, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelAlumno.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNmeroDePersonal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRfc, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textRfc, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(lblDatosPersonales, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFechaDeNacimiento, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(2)
							.addComponent(dateFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(textEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(labelEdad)
							.addComponent(labelGrado)
							.addComponent(comboGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addComponent(lblDatosDelPadre, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(textTutorOcupa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(labelTutorOcupa))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
								.addComponent(textTutorNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTutor))))
					.addGap(30)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCalle, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(textCalle, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(textNumCasa, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNmero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(textColonia, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblColonia, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGap(27)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNmeroTelefnico, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(textNumeroTel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCdigoPostal, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(textCp, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(58))
		);
		panelAlumno.setLayout(gl_panelAlumno);
		GradosDAO gradoDao = new GradosDAO();
		ArrayList < Grados > listaGrados = gradoDao.obtenerGrados();
		
		Vector<Grados> vector = new Vector<>();
		vector.addElement(new Grados(0,"Selecciona"));
		for (int i = 0; i < listaGrados.size(); i++) {
			vector.addElement(new Grados(listaGrados.get(i).getId(),listaGrados.get(i).getGrado()));		
		}
		comboGenero.setModel(new DefaultComboBoxModel(vector));
		GroupLayout groupLayout = new GroupLayout(frmRegistro.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(380)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(123)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
				.addComponent(lblRegistro, GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(67, Short.MAX_VALUE)
					.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 1006, GroupLayout.PREFERRED_SIZE)
					.addGap(64))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistro)
					.addGap(34)
					.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(31))
		);
		
		frmRegistro.getContentPane().setLayout(groupLayout);
	}
	
	
	// Acciones de botones
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals(ACT_SALIR)){
			
		//	JOptionPane.showMessageDialog(null, comboGrado.getSelectedIndex(), "Yay, java", JOptionPane.PLAIN_MESSAGE);			
			frmRegistro.setVisible(false);
			
			frmRegistro.dispose();
			
		}else if(e.getActionCommand().equals(ACT_PERSONAL)){			
			
			panelAlumno.setVisible(false);
			
		}else if(e.getActionCommand().equals(ACT_ALUMNO)){			
			panelAlumno.setVisible(true);
			
			
		}else if (e.getActionCommand().equals(ACT_GUARDAR)){
			
		/********************************** VALIDACIONES DE LOS CAMPOS A INSERTAR **********************************************/	
			boolean bandera = true;
			String mensaje = "No debe dejar ningun campo vacio\n";	
			
			// curp
			if(textCurp.getText().trim().isEmpty()){
				textCurp.setBorder(new LineBorder(Color.RED));
				bandera = false;
				mensaje = mensaje+"	- CURP\n";					
			}else{
				textCurp.setBorder(new LineBorder(Color.decode("#7A8A99")));
			}
			
			// nombre
			if(textNombre.getText().trim().isEmpty()){
			textNombre.setBorder(new LineBorder(Color.RED));
			bandera = false;
			mensaje = mensaje+"	- Nombre\n";
			}else{
				textNombre.setBorder(new LineBorder(Color.decode("#7A8A99")));
			}
			
			// fecha nacimiento
			if(dateFechaNacimiento.getDate()==null){
				dateFechaNacimiento.getDateEditor().getUiComponent().setBorder(new LineBorder(Color.RED));
				
				bandera = false;
				mensaje = mensaje+"	- Fecha de nacimiento\n";
				}else{
					
				//	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
				//	fechaConFormato = formatoFecha.format(dateFechaNacimiento.getDate());
					
				//	SimpleDateFormat formatoFechaBase = new SimpleDateFormat("YYYY/MM/dd");
				//	fechaConFormatoBase = formatoFechaBase.format(dateFechaNacimiento.getDate());
					
					dateFechaNacimiento.getDateEditor().getUiComponent().setBorder(new LineBorder(Color.decode("#7A8A99")));
				}

			// validacion de edad
			if(textEdad.getText().trim().isEmpty()){
			textEdad.setBorder(new LineBorder(Color.RED));
			
			bandera = false;
			mensaje = mensaje+"	- Edad\n";
			}else{
				textEdad.setBorder(new LineBorder(Color.decode("#7A8A99")));
			}
			/*
			// validacion de los radiobutons
			if(!rdbtnAlumno.isSelected() && !rdbtnPersonal.isSelected()  ){
				rdbtnAlumno.setBackground(Color.RED);
				rdbtnPersonal.setBackground(Color.RED);			
				bandera = false;
				mensaje = mensaje+" Tipo de registro\n";
			}else{
				rdbtnAlumno.setBackground(Color.WHITE);
				rdbtnPersonal.setBackground(Color.WHITE);
			}
			
			
			/************************** VALIDACIONES CUANDO ES TIPO ALUMNO *****************************************/
		//	if(rdbtnAlumno.isSelected()){*/
				if(textTutorNom.getText().trim().isEmpty()){
					textTutorNom.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Nombre del tutor\n";
					}else{
						textTutorNom.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
				
				if(textTutorOcupa.getText().trim().isEmpty()){
					textTutorOcupa.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Ocupación del tutor\n";
					}else{
						textTutorOcupa.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
				
				if(textCalle.getText().trim().isEmpty()){
					textCalle.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Calle\n";
					}else{
						textCalle.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
				
				if(textNumCasa.getText().trim().isEmpty()){
					textNumCasa.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Número \n";
					}else{
						textNumCasa.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
				
				if(textColonia.getText().trim().isEmpty()){
					textColonia.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Colonia \n";
					}else{
						textColonia.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
				
				if(textCp.getText().trim().isEmpty()){
					textCp.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Código postal \n";
					}else{
						textCp.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
				
				if(textNumeroTel.getText().trim().isEmpty()){
					textNumeroTel.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Número telefónico \n";
					}else{
						textNumeroTel.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
				
				Grados gradoSeleccionado = (Grados) comboGenero.getSelectedItem() ;
				int idGrado = gradoSeleccionado.getId() ;
				if(idGrado == 0){
					comboGenero.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+"	- Grado \n";
					}else{
						comboGenero.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
		//	}
			
			
			if(!bandera){
				JOptionPane.showMessageDialog(frmRegistro, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
			
			}else{
				AlumnoDAO alumnoDao = new AlumnoDAO();
				String anio = String.valueOf(dateFechaNacimiento.getDate().getYear());
				String mes = String.valueOf(dateFechaNacimiento.getDate().getMonth());
				String dia = String.valueOf(dateFechaNacimiento.getDate().getDay());
				String fechaNac = anio+"/"+mes+"/"+dia;
				if(alumnoDao.guardarAlumno(textNombre.getText(), textCurp.getText(), fechaNac, textEdad.getText(), 
						idGrado, textTutorNom.getText(), textTutorOcupa.getText(), textCalle.getText(), textNumCasa.getText(), textColonia.getText(), textCp.getText(), 
						textNumeroTel.getText(), 1, frmRegistro)){
					textNombre.setText(null);
					textCurp.setText(null);					
					dateFechaNacimiento.setDate(null);
					textEdad.setText(null);
					comboGenero.setSelectedIndex(0);
					textTutorNom.setText(null);
					textTutorOcupa.setText(null);
					textCalle.setText(null);
					textNumCasa.setText(null);
					textColonia.setText(null);
					textCp.setText(null);
					textNumeroTel.setText(null);
					JOptionPane.showMessageDialog(frmRegistro, "Alumno guardado!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(frmRegistro, "No se puede guardar el alumno!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public static void run() {
		RegistroPersonal window = new RegistroPersonal();
		window.frmRegistro.setVisible(true);
		
	}
}
