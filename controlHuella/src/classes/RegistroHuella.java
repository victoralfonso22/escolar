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

import clases.Alumno;
import clases.Grados;
import clases.Personal;

import com.jgoodies.forms.layout.FormLayout;
import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
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
import dao.PersonalDAO;

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
import java.awt.Dialog.ModalExclusionType;

import javax.swing.ImageIcon;

import java.awt.Cursor;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RegistroHuella implements ActionListener {
	
	

	private JDialog frmRegistroHuella;
	private JPanel panelAlumno;
	private CaptureThread m_capture;
	private Reader        m_reader;
	private boolean       m_bStreaming;
	
	private static final String ACT_SALIR = "exit";
	private static final String ACT_PERSONAL = "personal";
	private static final String ACT_ALUMNO = "alumno";
	private static final String ACT_CAPTURA = "guardar";
	
	private JComboBox comboGrado;
	private JLabel labelGrado;
	String fechaConFormato;
	private int bandera = 1;
	
	ButtonGroup radios;
	JRadioButton rdbtnPersonal;
	JRadioButton rdbtnAlumno;
	JLabel lblDatosDelPadre;
	ImageIcon imagen1, icono;
	JComboBox comboBoxNombre;
	Alumno alumnoSeleccionado;
	JLabel lblNombre;
	
	Personal personalSeleccionado;
	Grados gradoSeleccionado;
	
	int idGrado;
	private JLabel lblCurpDato;
	private JLabel lblFechaNacimentoDato;
	private JLabel lblEdadDato;
	private JLabel labelCurp;
	private JLabel labelFechaNacimiento;
	private JLabel labelEdad;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuella window = new RegistroHuella(Reader reader);
					window.frmRegistroHuella.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public RegistroHuella(Reader reader, boolean bStreaming) {
		initialize(reader, bStreaming);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Reader reader, boolean bStreaming) {
		
		imagen1 = new ImageIcon(getClass().getResource("/imagenes/huella-dactilar.jpg"));
		icono = new ImageIcon(getClass().getResource("/imagenes/logo2.png"));
		
		m_reader = reader;
		m_bStreaming = bStreaming;
		
		m_capture = new CaptureThread(m_reader, m_bStreaming, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);
				
		frmRegistroHuella = new JDialog((JDialog)null,null, true);
		frmRegistroHuella.setAlwaysOnTop(true);
		frmRegistroHuella.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);;
		frmRegistroHuella.setBackground(Color.WHITE);
		frmRegistroHuella.setResizable(false);
		frmRegistroHuella.setTitle("Sistema de resgistro y administraci\u00F3n escolar");
		frmRegistroHuella.getContentPane().setBackground(Color.WHITE);
		frmRegistroHuella.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuella.class.getResource("/imagenes/logo2.png")));
		frmRegistroHuella.setBounds(100, 100, 1143, 675);
		frmRegistroHuella.setLocationRelativeTo(null);

		JLabel lblRegistro = new JLabel("Registro de huella para estudiantes y personal");
		lblRegistro.setForeground(new Color(0, 102, 0));
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel labelTipo = new JLabel("Tipo de registro");
		labelTipo.setForeground(new Color(0, 102, 0));
		labelTipo.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		// Boton para salir de la ventana
		JButton btnCancelar = new JButton("Regresar");		
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(0, 102, 0));
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancelar.setActionCommand(ACT_SALIR);
		btnCancelar.addActionListener(this);
		
		JButton btnCapturaHuella = new JButton("");
		btnCapturaHuella.setBorder(new LineBorder(new Color(0, 102, 0)));
		btnCapturaHuella.setToolTipText("Capturar huellas dactilares");
		btnCapturaHuella.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCapturaHuella.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		btnCapturaHuella.setActionCommand(ACT_CAPTURA);
		btnCapturaHuella.addActionListener(this);
		btnCapturaHuella.setIcon(imagen1);
		
		// radioButtons
		
		rdbtnPersonal = new JRadioButton("Personal");		
		rdbtnPersonal.setForeground(Color.BLACK);
		rdbtnPersonal.setBackground(Color.WHITE);
		rdbtnPersonal.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnPersonal.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnPersonal.setActionCommand(ACT_PERSONAL);
		rdbtnPersonal.addActionListener(this);
		
		rdbtnAlumno = new JRadioButton("Alumno");
		rdbtnAlumno.setSelected(true);
		rdbtnAlumno.setBackground(Color.WHITE);
		rdbtnAlumno.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnAlumno.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbtnAlumno.setActionCommand(ACT_ALUMNO);
		rdbtnAlumno.addActionListener(this);
				
		// Agrupamos los radioButtons
		
		radios = new ButtonGroup();
		
		radios.add(rdbtnAlumno);
		radios.add(rdbtnPersonal);
		
		panelAlumno = new JPanel();
		panelAlumno.setBackground(Color.WHITE);
		panelAlumno.setForeground(SystemColor.textHighlight);
		panelAlumno.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		lblDatosDelPadre = new JLabel("Datos del alumno");
		lblDatosDelPadre.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDelPadre.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblNombreDato = new JLabel("Nombre");
		lblNombreDato.setForeground(new Color(0, 102, 0));
		lblNombreDato.setFont(new Font("Arial", Font.PLAIN, 17));
		
		lblNombre = new JLabel("");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 17));
		lblNombre.setForeground(new Color(0, 0, 0));
		
		lblCurpDato = new JLabel("Curp");
		lblCurpDato.setForeground(new Color(0, 102, 0));
		lblCurpDato.setFont(new Font("Arial", Font.PLAIN, 17));
		
		lblFechaNacimentoDato = new JLabel("Fecha nacimento");
		lblFechaNacimentoDato.setForeground(new Color(0, 102, 0));
		lblFechaNacimentoDato.setFont(new Font("Arial", Font.PLAIN, 17));
		
		lblEdadDato = new JLabel("Edad");
		lblEdadDato.setForeground(new Color(0, 102, 0));
		lblEdadDato.setFont(new Font("Arial", Font.PLAIN, 17));
		
		labelCurp = new JLabel("");
		labelCurp.setForeground(Color.BLACK);
		labelCurp.setFont(new Font("Arial", Font.BOLD, 17));
		
		labelFechaNacimiento = new JLabel("");
		labelFechaNacimiento.setForeground(Color.BLACK);
		labelFechaNacimiento.setFont(new Font("Arial", Font.BOLD, 17));
		
		labelEdad = new JLabel("");
		labelEdad.setForeground(Color.BLACK);
		labelEdad.setFont(new Font("Arial", Font.BOLD, 17));
		GroupLayout gl_panelAlumno = new GroupLayout(panelAlumno);
		gl_panelAlumno.setHorizontalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDatosDelPadre, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFechaNacimentoDato, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEdadDato, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombreDato, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCurpDato, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING, false)
								.addComponent(labelEdad, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
								.addComponent(labelFechaNacimiento, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(labelCurp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panelAlumno.setVerticalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addComponent(lblDatosDelPadre, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addComponent(lblNombreDato)
							.addGap(18)
							.addComponent(lblCurpDato, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(9))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(labelCurp, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(6)))
					.addGroup(gl_panelAlumno.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(9)
							.addComponent(lblFechaNacimentoDato, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEdadDato, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAlumno.createSequentialGroup()
							.addGap(5)
							.addComponent(labelFechaNacimiento, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelEdad, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(71))
		);
		panelAlumno.setLayout(gl_panelAlumno);
		
		
		comboBoxNombre = new JComboBox();
		comboBoxNombre.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBoxNombre.setBackground(Color.WHITE);
		comboBoxNombre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					

					if(bandera == 1){
					alumnoSeleccionado = (Alumno) e.getItem();
					if(alumnoSeleccionado.getId()!=0){
					lblNombre.setText(alumnoSeleccionado.getNombre_completo());
					labelCurp.setText(alumnoSeleccionado.getCurp());
					labelFechaNacimiento.setText(alumnoSeleccionado.getFecha_nacimiento());
					labelEdad.setText(alumnoSeleccionado.getEdad());
					}else{
						lblNombre.setText(null);
						labelCurp.setText(null);
						labelFechaNacimiento.setText(null);
						labelEdad.setText(null);
					}
					
					
					
					}else if (bandera == 2){
						personalSeleccionado = (Personal) e.getItem();
						if(personalSeleccionado.getId()!=0){
						lblNombre.setText(personalSeleccionado.getNombre_completo());
						labelCurp.setText(personalSeleccionado.getCurp());
						labelFechaNacimiento.setText(personalSeleccionado.getFecha_nacimiento());
						labelEdad.setText(personalSeleccionado.getEdad());
						}else{
							lblNombre.setText(null);
							labelCurp.setText(null);
							labelFechaNacimiento.setText(null);
							labelEdad.setText(null);
						}
						
						
					}
					
					
					}
				}
			});
		
		
		comboGrado = new JComboBox();
		comboGrado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					lblNombre.setText(null);
					labelCurp.setText(null);
					labelFechaNacimiento.setText(null);
					labelEdad.setText(null);
					
					
					gradoSeleccionado = (Grados) e.getItem();
					
					Vector<Alumno> vectorAlumno = new Vector<>();
					if(gradoSeleccionado.getId() != 0){
					AlumnoDAO alumnoDao = new AlumnoDAO();
					ArrayList < Alumno > listaAlumnos = alumnoDao.retornaAlumno(gradoSeleccionado.getId(), frmRegistroHuella);	
					
					vectorAlumno.addElement(new Alumno(0,"Selecciona","","","",0,"","","",0,"","","",0));
					for (int i = 0; i < listaAlumnos.size(); i++) {
						vectorAlumno.addElement(new Alumno(listaAlumnos.get(i).getId(),listaAlumnos.get(i).getNombre_completo(),listaAlumnos.get(i).getCurp(),listaAlumnos.get(i).getFecha_nacimiento(),
								listaAlumnos.get(i).getEdad(),listaAlumnos.get(i).getId_grado(),listaAlumnos.get(i).getNombre_tutor(),listaAlumnos.get(i).getOcupacion_tutor(),listaAlumnos.get(i).getCalle_tutor(),
								listaAlumnos.get(i).getNumero_calle_tutor(),listaAlumnos.get(i).getColonia_tutor(),listaAlumnos.get(i).getCp_tutor(),listaAlumnos.get(i).getTel_tutor(),listaAlumnos.get(i).getEstatus()));		
					}
					
					}else{
						comboBoxNombre.removeAllItems();
						vectorAlumno.addElement(new Alumno(0,"Selecciona","","","",0,"","","",0,"","","",0));	
					}
					comboBoxNombre.setModel(new DefaultComboBoxModel(vectorAlumno));
					alumnoSeleccionado = (Alumno) comboBoxNombre.getSelectedItem();
					
					//idGrado = gradoSeleccionado.getId() ;	
					
			          //System.out.println(gradoSeleccionado.getId());
			          // do something with object
			       }
			}
		});
		comboGrado.setFont(new Font("Arial", Font.PLAIN, 14));
		comboGrado.setBackground(Color.WHITE);
		
		//comboGrado.addItem("Selecciona");
		GradosDAO gradoDao = new GradosDAO();
		ArrayList < Grados > listaGrados = gradoDao.obtenerGrados();	
		Vector<Grados> vector = new Vector<>();
		vector.addElement(new Grados(0,"Selecciona"));
		for (int i = 0; i < listaGrados.size(); i++) {
			vector.addElement(new Grados(listaGrados.get(i).getId(),listaGrados.get(i).getGrado()));		
		}
		comboGrado.setModel(new DefaultComboBoxModel(vector));
		gradoSeleccionado = (Grados) comboGrado.getSelectedItem();
		idGrado = gradoSeleccionado.getId() ;		
		
		
		
		labelGrado = new JLabel("Grado y grupo");
		labelGrado.setForeground(new Color(0, 102, 0));
		labelGrado.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblNombreCombo = new JLabel("Nombre");
		lblNombreCombo.setForeground(new Color(0, 102, 0));
		lblNombreCombo.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		//comboBoxNombre.addItem("Selecciona");
		
		Dimension dm = new Dimension(380, 380);
		
		
		GroupLayout groupLayout = new GroupLayout(frmRegistroHuella.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(328)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(labelGrado)
									.addGap(317))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNombreCombo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboGrado, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
										.addComponent(comboBoxNombre, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))))
							.addGap(392))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(413)
							.addComponent(labelTipo, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(rdbtnPersonal)
							.addGap(48)
							.addComponent(rdbtnAlumno)
							.addGap(402))
						.addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 1137, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(84)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 714, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 389, Short.MAX_VALUE)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
									.addGap(195)))
							.addGap(48)
							.addComponent(btnCapturaHuella, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addGap(99)))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistro)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(labelTipo))
						.addComponent(rdbtnPersonal)
						.addComponent(rdbtnAlumno))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboGrado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelGrado))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
							.addComponent(lblNombreCombo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBoxNombre, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCapturaHuella, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelAlumno, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);
		frmRegistroHuella.getContentPane().setLayout(groupLayout);
	}
	
	// Acciones de botones
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals(ACT_SALIR)){
			
		//	JOptionPane.showMessageDialog(null, comboGrado.getSelectedIndex(), "Yay, java", JOptionPane.PLAIN_MESSAGE);
			
			frmRegistroHuella.setVisible(false);
			
			frmRegistroHuella.dispose();
		}else if(e.getActionCommand().equals(ACT_PERSONAL)){
			bandera = 2;
			panelAlumno.setVisible(true);
			comboGrado.setVisible(false);
			labelGrado.setVisible(false);	
			
			lblNombre.setText(null);
			labelCurp.setText(null);
			labelFechaNacimiento.setText(null);
			labelEdad.setText(null);
			
			Vector<Grados> vectorGrados = new Vector<>();
			comboGrado.removeAllItems();		
			comboGrado.setModel(new DefaultComboBoxModel(vectorGrados));
			
			Vector<Personal> vectorPersonal = new Vector<>();
			comboBoxNombre.removeAllItems();		
			comboBoxNombre.setModel(new DefaultComboBoxModel(vectorPersonal));
			
				PersonalDAO personalDao = new PersonalDAO();
				ArrayList < Personal > listaPersonal = personalDao.retornaPersonal(frmRegistroHuella);		
				Vector<Personal> vectorPer = new Vector<>();
				vectorPer.addElement(new Personal(0,"Selecciona",null,null,null));
				for (int i = 0; i < listaPersonal.size(); i++) {
					vectorPer.addElement(new Personal(listaPersonal.get(i).getId(),listaPersonal.get(i).getNombre_completo(),listaPersonal.get(i).getCurp(),listaPersonal.get(i).getFecha_nacimiento(),
							listaPersonal.get(i).getEdad()));		
				}
				comboBoxNombre.setModel(new DefaultComboBoxModel(vectorPer));
				personalSeleccionado =(Personal)comboBoxNombre.getSelectedItem();
			
			//labelGrado.setText("Personal");
			lblDatosDelPadre.setText("Datos del personal");
		}else if(e.getActionCommand().equals(ACT_ALUMNO)){
			bandera = 1;
			panelAlumno.setVisible(true);
			comboGrado.setVisible(true);
			labelGrado.setVisible(true);
			labelGrado.setText("Grupo y grado");
			lblDatosDelPadre.setText("Datos del alumno");
			
			lblNombre.setText(null);
			labelCurp.setText(null);
			labelFechaNacimiento.setText(null);
			labelEdad.setText(null);
			
			
			if(idGrado ==0){
			GradosDAO gradoDao = new GradosDAO();
			ArrayList < Grados > listaGrados = gradoDao.obtenerGrados();			
			Vector<Grados> vector = new Vector<>();
			vector.addElement(new Grados(0,"Selecciona"));
			for (int i = 0; i < listaGrados.size(); i++) {
				vector.addElement(new Grados(listaGrados.get(i).getId(),listaGrados.get(i).getGrado()));		
			}
			comboGrado.setModel(new DefaultComboBoxModel(vector));
			}
			
			
			Vector<Alumno> vectorAlumno = new Vector<>();
			comboBoxNombre.removeAllItems();		
			comboBoxNombre.setModel(new DefaultComboBoxModel(vectorAlumno));
			
		}else if(e.getActionCommand().equals(ACT_CAPTURA)){
			if(null == m_reader){
				MessageBox.Warning("No hay lector de huellas conectado");
			}
			else{
				if(rdbtnAlumno.isSelected()){
					if(gradoSeleccionado.getId() == 0){
						JOptionPane.showMessageDialog(frmRegistroHuella, "Debe seleccionar grado y grupo", "Aviso", JOptionPane.WARNING_MESSAGE);
					}else if(alumnoSeleccionado.getId() == 0){
						JOptionPane.showMessageDialog(frmRegistroHuella, "Debe seleccionar un alumno", "Aviso", JOptionPane.WARNING_MESSAGE);
					}else{
						Captura.Run(m_reader, false, icono.getImage(),alumnoSeleccionado,null);
					}
					
				
				}else if(rdbtnPersonal.isSelected()){
					if(personalSeleccionado.getId() == 0){
						JOptionPane.showMessageDialog(frmRegistroHuella, "Debe seleccionar un personal", "Aviso", JOptionPane.WARNING_MESSAGE);
					}else{
						Captura.Run(m_reader, false, icono.getImage(),null, personalSeleccionado);
					}
				}
			}
		}
	}
	
	
	
	
	public static void run(Reader reader, boolean bStreaming) {
		RegistroHuella window = new RegistroHuella(reader, bStreaming);
		window.frmRegistroHuella.setVisible(true);
	}
}
