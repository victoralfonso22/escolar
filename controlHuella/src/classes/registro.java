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
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
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
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class registro implements ActionListener {

	private JFrame frame;
	private JTextField textCurp;
	private JTextField textNombre;
	private JTextField textTutor;
	private JTextField textTutorTel;
	private JDateChooser dateFechaNacimiento;
	private JPanel panelAlumno;
	
	private static final String ACT_SALIR = "exit";
	private static final String ACT_PERSONAL = "personal";
	private static final String ACT_ALUMNO = "alumno";
	private JTextField textEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro window = new registro();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(registro.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Underline@2x.png")));
		frame.setBounds(100, 100, 1092, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblRegistro = new JLabel("Registro de estudiantes y personal administrativo");
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCurp = new JLabel("Curp");
		lblCurp.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelNombre = new JLabel("Nombre completo");
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelTipo = new JLabel("Tipo de usuario");
		labelTipo.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textCurp = new JTextField();
		textCurp.setFont(new Font("Arial", Font.PLAIN, 14));
		textCurp.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		textNombre.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		// Boton para salir de la ventana
		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setActionCommand(ACT_SALIR);
		btnCancelar.addActionListener(this);
		
		// radioButtons
		
		JRadioButton rdbtnPersonal = new JRadioButton("Personal");
		rdbtnPersonal.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnPersonal.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnPersonal.setActionCommand(ACT_PERSONAL);
		rdbtnPersonal.addActionListener(this);
		
		JRadioButton rdbtnAlumno = new JRadioButton("Alumno");
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
		labelEdad.setFont(new Font("Arial", Font.PLAIN, 14));
				
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
		lblFechaDeNacimiento.setFont(new Font("Arial", Font.PLAIN, 14));
		
		panelAlumno = new JPanel();
		panelAlumno.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel labelGrado = new JLabel("Grado");
		labelGrado.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textTutor = new JTextField();
		textTutor.setFont(new Font("Arial", Font.PLAIN, 14));
		textTutor.setColumns(10);
		
		JLabel labelTutor = new JLabel("Nombre de tutor");
		labelTutor.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelTutorTel = new JLabel("Telefono de tutor");
		labelTutorTel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textTutorTel = new JTextField();
		textTutorTel.setFont(new Font("Arial", Font.PLAIN, 14));
		textTutorTel.setColumns(10);
		
		JComboBox comboGrado = new JComboBox();
		GroupLayout gl_panelAlumno = new GroupLayout(panelAlumno);
		gl_panelAlumno.setHorizontalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addComponent(labelGrado)
							.addGap(18)
							.addComponent(comboGrado, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
							.addComponent(labelTutor, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textTutor, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addGap(76))
						.addGroup(Alignment.TRAILING, gl_panelAlumno.createSequentialGroup()
							.addComponent(labelTutorTel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textTutorTel, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
							.addGap(250))))
		);
		gl_panelAlumno.setVerticalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createParallelGroup(Alignment.BASELINE)
							.addComponent(labelGrado)
							.addComponent(comboGrado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(3)
							.addComponent(labelTutor))
						.addComponent(textTutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(3)
							.addComponent(labelTutorTel))
						.addComponent(textTutorTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelAlumno.setLayout(gl_panelAlumno);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
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
							.addGap(157)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(471)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 959, GroupLayout.PREFERRED_SIZE)
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
									.addComponent(rdbtnAlumno))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(177)
							.addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 665, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(64, Short.MAX_VALUE))
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
					.addGap(55)
					.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
	// Acciones de botones
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ACT_SALIR)){
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
			frame.dispose();
		}else if(e.getActionCommand().equals(ACT_PERSONAL)){
			panelAlumno.setVisible(false);
		}else if(e.getActionCommand().equals(ACT_ALUMNO)){
			panelAlumno.setVisible(true);
		}
	}
}
