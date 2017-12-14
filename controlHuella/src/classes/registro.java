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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JFormattedTextField;

public class registro implements ActionListener {

	private JFrame frmSistemaDeResgistro;
	private JTextField textCurp;
	private JTextField textNombre;
	private JTextField textTutorNom;
	private JTextField textTutorOcupa;
	private JDateChooser dateFechaNacimiento;
	private JPanel panelAlumno;
	
	private static final String ACT_SALIR = "exit";
	private static final String ACT_PERSONAL = "personal";
	private static final String ACT_ALUMNO = "alumno";
	private JTextField textEdad;
	private JTextField textCalle;
	private JTextField textNumCasa;
	private JTextField textColonia;
	private JTextField textCp;
	private JTextField textNumeroTel;
	
	private JComboBox comboGrado;
	private JLabel labelGrado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro window = new registro();
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
	public registro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeResgistro = new JFrame();
		frmSistemaDeResgistro.setResizable(false);
		frmSistemaDeResgistro.setTitle("Sistema de resgistro y administraci\u00F3n escolar");
		frmSistemaDeResgistro.getContentPane().setBackground(Color.WHITE);
		frmSistemaDeResgistro.setIconImage(Toolkit.getDefaultToolkit().getImage(registro.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Underline@2x.png")));
		frmSistemaDeResgistro.setBounds(100, 100, 1092, 644);
		frmSistemaDeResgistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblRegistro = new JLabel("Registro de estudiantes y personal");
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCurp = new JLabel("Curp");
		lblCurp.setForeground(SystemColor.textHighlight);
		lblCurp.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel labelNombre = new JLabel("Nombre completo");
		labelNombre.setForeground(SystemColor.textHighlight);
		labelNombre.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel labelTipo = new JLabel("Tipo de usuario");
		labelTipo.setForeground(SystemColor.textHighlight);
		labelTipo.setFont(new Font("Arial", Font.BOLD, 14));
		
		textCurp = new JTextField();
		textCurp.setFont(new Font("Arial", Font.PLAIN, 14));
		textCurp.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		textNombre.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGuardar.setForeground(new Color(30, 144, 255));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 15));
		
		
		// Boton para salir de la ventana
		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setForeground(new Color(30, 144, 255));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancelar.setActionCommand(ACT_SALIR);
		btnCancelar.addActionListener(this);
		
		// radioButtons
		
		JRadioButton rdbtnPersonal = new JRadioButton("Personal");
		rdbtnPersonal.setBackground(Color.WHITE);
		rdbtnPersonal.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnPersonal.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnPersonal.setActionCommand(ACT_PERSONAL);
		rdbtnPersonal.addActionListener(this);
		
		JRadioButton rdbtnAlumno = new JRadioButton("Alumno");
		rdbtnAlumno.setBackground(Color.WHITE);
		rdbtnAlumno.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnAlumno.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnAlumno.setActionCommand(ACT_ALUMNO);
		rdbtnAlumno.addActionListener(this);
		
		// Agrupamos los radioButtons
		
		ButtonGroup radios = new ButtonGroup();
		
		radios.add(rdbtnAlumno);
		radios.add(rdbtnPersonal);
		

		// label e input de edad
		
		JLabel labelEdad = new JLabel("Edad");
		labelEdad.setForeground(SystemColor.textHighlight);
		labelEdad.setFont(new Font("Arial", Font.BOLD, 14));
				
		textEdad = new JTextField();
		textEdad.setFont(new Font("Arial", Font.PLAIN, 14));
		textEdad.setColumns(10);
		
		
		// seleccion de fecha de nacimiento
		dateFechaNacimiento = new JDateChooser();
		dateFechaNacimiento.setToolTipText("dd/MM/YYYY");
		
		/* Evento que se lanza cuando cambia de valor la fecha de nacimiento  */
		dateFechaNacimiento.addPropertyChangeListener("date", new PropertyChangeListener() {
			
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate ahora = LocalDate.now();		
			
		    public void propertyChange(PropertyChangeEvent evt) {
		        Date date = (Date)evt.getNewValue();
		        
		        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
		        LocalDate fechaNac = LocalDate.parse(formatoFecha.format(date), fmt);
		        Period periodo = Period.between(fechaNac, ahora);
		        String valor = "Tu edad es:"+ periodo.getYears() +" años, "+ periodo.getMonths() +" meses y "+periodo.getDays()+" días";
		        textEdad.setText(Integer.toString(periodo.getYears()));		        
		    }
		});
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setForeground(SystemColor.textHighlight);
		lblFechaDeNacimiento.setFont(new Font("Arial", Font.BOLD, 14));
		
		panelAlumno = new JPanel();
		panelAlumno.setBackground(Color.WHITE);
		panelAlumno.setForeground(SystemColor.textHighlight);
		panelAlumno.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		textTutorNom = new JTextField();
		textTutorNom.setFont(new Font("Arial", Font.PLAIN, 14));
		textTutorNom.setColumns(10);
		
		JLabel labelTutor = new JLabel("Nombre de tutor");
		labelTutor.setForeground(SystemColor.textHighlight);
		labelTutor.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel labelTutorOcupa = new JLabel("Ocupaci\u00F3n");
		labelTutorOcupa.setForeground(SystemColor.textHighlight);
		labelTutorOcupa.setFont(new Font("Arial", Font.BOLD, 14));
		
		textTutorOcupa = new JTextField();
		textTutorOcupa.setFont(new Font("Arial", Font.PLAIN, 14));
		textTutorOcupa.setColumns(10);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setForeground(SystemColor.textHighlight);
		lblCalle.setFont(new Font("Arial", Font.BOLD, 14));
		
		textCalle = new JTextField();
		textCalle.setFont(new Font("Arial", Font.PLAIN, 14));
		textCalle.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setForeground(SystemColor.textHighlight);
		lblNmero.setFont(new Font("Arial", Font.BOLD, 14));
		
		textNumCasa = new JTextField();
		textNumCasa.setFont(new Font("Arial", Font.PLAIN, 14));
		textNumCasa.setColumns(10);
		
		JLabel lblColonia = new JLabel("Colonia");
		lblColonia.setForeground(SystemColor.textHighlight);
		lblColonia.setFont(new Font("Arial", Font.BOLD, 14));
		
		textColonia = new JTextField();
		textColonia.setFont(new Font("Arial", Font.PLAIN, 14));
		textColonia.setColumns(10);
		
		JLabel lblCdigoPostal = new JLabel("C\u00F3digo postal");
		lblCdigoPostal.setForeground(SystemColor.textHighlight);
		lblCdigoPostal.setFont(new Font("Arial", Font.BOLD, 14));
		
		textCp = new JTextField();
		textCp.setFont(new Font("Arial", Font.PLAIN, 14));
		textCp.setColumns(10);
		
		JLabel lblNmeroTelefnico = new JLabel("N\u00FAmero telef\u00F3nico");
		lblNmeroTelefnico.setForeground(SystemColor.textHighlight);
		lblNmeroTelefnico.setFont(new Font("Arial", Font.BOLD, 14));
		
		textNumeroTel = new JTextField();
		textNumeroTel.setFont(new Font("Arial", Font.PLAIN, 14));
		textNumeroTel.setColumns(10);
		
		JLabel lblDatosDelPadre = new JLabel("Datos del padre o tutor");
		lblDatosDelPadre.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDelPadre.setFont(new Font("Arial", Font.BOLD, 12));
		GroupLayout gl_panelAlumno = new GroupLayout(panelAlumno);
		gl_panelAlumno.setHorizontalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addComponent(labelTutor, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textTutorNom, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addGap(69)
							.addComponent(labelTutorOcupa, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textTutorOcupa, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addComponent(lblCalle, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textCalle, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNmero, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(textNumCasa, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(lblColonia, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textColonia, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
							.addGap(6)))
					.addGap(25))
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addGap(147)
					.addComponent(lblCdigoPostal, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textCp, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(79)
					.addComponent(lblNmeroTelefnico, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textNumeroTel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(234, Short.MAX_VALUE))
				.addComponent(lblDatosDelPadre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
		);
		gl_panelAlumno.setVerticalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDelPadre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(25)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(3)
							.addComponent(labelTutorOcupa))
						.addComponent(textTutorOcupa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(3)
							.addComponent(labelTutor))
						.addComponent(textTutorNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCalle, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(textCalle, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNmero, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(textNumCasa, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
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
					.addGap(32))
		);
		panelAlumno.setLayout(gl_panelAlumno);
		
		comboGrado = new JComboBox();
		comboGrado.setFont(new Font("Arial", Font.PLAIN, 14));
		comboGrado.setBackground(Color.WHITE);
		
		comboGrado.addItem("Selecciona");
		GradosDAO gradoDao = new GradosDAO();
		ArrayList < Grados > listaGrados = gradoDao.obtenerGrados();
		for (int i = 0; i < listaGrados.size(); i++) {
			comboGrado.addItem(listaGrados.get(i).getGrado());
		}
		
		
		labelGrado = new JLabel("Grado");
		labelGrado.setForeground(SystemColor.textHighlight);
		labelGrado.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(frmSistemaDeResgistro.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(lblCurp, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textCurp, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
							.addGap(114)
							.addComponent(labelNombre)
							.addGap(18)
							.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(338)
							.addComponent(labelGrado)
							.addGap(18)
							.addComponent(comboGrado, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 973, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFechaDeNacimiento)
									.addGap(18)
									.addComponent(dateFechaNacimiento, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelEdad, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textEdad, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addGap(110)
									.addComponent(labelTipo, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(rdbtnPersonal)
									.addGap(48)
									.addComponent(rdbtnAlumno)))))
					.addGap(50))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(349)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(123)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(354, Short.MAX_VALUE))
				.addComponent(lblRegistro, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistro)
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCurp))
						.addComponent(textCurp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(labelNombre)))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFechaDeNacimiento, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(dateFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(labelEdad))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(labelTipo))
						.addComponent(rdbtnPersonal)
						.addComponent(rdbtnAlumno))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboGrado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelGrado))
					.addGap(30)
					.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(41))
		);
		frmSistemaDeResgistro.getContentPane().setLayout(groupLayout);
	}
	
	
	// Acciones de botones
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals(ACT_SALIR)){
			
			JOptionPane.showMessageDialog(null, comboGrado.getSelectedIndex(), "Yay, java", JOptionPane.PLAIN_MESSAGE);
			
			frmSistemaDeResgistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
			frmSistemaDeResgistro.dispose();
		}else if(e.getActionCommand().equals(ACT_PERSONAL)){
			panelAlumno.setVisible(false);
			comboGrado.setVisible(false);
			labelGrado.setVisible(false);
		}else if(e.getActionCommand().equals(ACT_ALUMNO)){
			panelAlumno.setVisible(true);
			comboGrado.setVisible(true);
			labelGrado.setVisible(true);
		}
	}
}
