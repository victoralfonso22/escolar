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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class registro implements ActionListener {

	private JFrame frame;
	private JTextField textClave;
	private JTextField textNombre;
	private JTextField textGrado;
	private JTextField textTutor;
	private JTextField textTutorTel;
	
	private static final String ACT_SALIR = "exit";
	private JTextField textField;

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
		frame.setBounds(100, 100, 1092, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblRegistro = new JLabel("Registro de estudiantes y administrativos");
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCurp = new JLabel("Curp");
		lblCurp.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelNombre = new JLabel("Nombre completo");
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelTipo = new JLabel("Tipo de usuario");
		labelTipo.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelGrado = new JLabel("Grado");
		labelGrado.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelTutor = new JLabel("Nombre de tutor");
		labelTutor.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelTutorTel = new JLabel("Telefono de tutor");
		labelTutorTel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textClave = new JTextField();
		textClave.setFont(new Font("Arial", Font.PLAIN, 14));
		textClave.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		textNombre.setColumns(10);
		
		textGrado = new JTextField();
		textGrado.setFont(new Font("Arial", Font.PLAIN, 14));
		textGrado.setColumns(10);
		
		textTutor = new JTextField();
		textTutor.setFont(new Font("Arial", Font.PLAIN, 14));
		textTutor.setColumns(10);
		
		textTutorTel = new JTextField();
		textTutorTel.setFont(new Font("Arial", Font.PLAIN, 14));
		textTutorTel.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setActionCommand(ACT_SALIR);
		btnCancelar.addActionListener(this);
		
		JRadioButton rdbtnPersonal = new JRadioButton("Personal");
		rdbtnPersonal.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JRadioButton rdbtnAlumno = new JRadioButton("Alumno");
		rdbtnAlumno.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel labelEdad = new JLabel("Edad");
		labelEdad.setFont(new Font("Arial", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JDateChooser dateFechaNacimiento = new JDateChooser();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(224)
					.addComponent(lblRegistro, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
					.addGap(249))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelGrado, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTutor, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTutorTel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTipo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textTutorTel, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
									.addComponent(textTutor, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
									.addComponent(textGrado, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(88)
									.addComponent(rdbtnPersonal)
									.addGap(48)
									.addComponent(rdbtnAlumno))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCurp, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textClave, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
									.addGap(114)
									.addComponent(labelNombre)
									.addGap(18)
									.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelEdad, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addGap(219)
									.addComponent(dateFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(82, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(157)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(188))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCurp))
						.addComponent(labelNombre)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(labelEdad, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addComponent(dateFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(95)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnPersonal)
							.addComponent(rdbtnAlumno))
						.addComponent(labelTipo))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelGrado)
						.addComponent(textGrado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTutor)
						.addComponent(textTutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTutorTel)
						.addComponent(textTutorTel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(93))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ACT_SALIR)){
			frame.setVisible(false);		
		}
	}
}
