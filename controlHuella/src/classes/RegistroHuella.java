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

import java.awt.Dialog.ModalExclusionType;

public class RegistroHuella implements ActionListener {
	
	

	private JDialog frmRegistroHuella;
	private JPanel panelAlumno;
	private CaptureThread m_capture;
	private Reader        m_reader;
	private ImagePanel    m_image;
	private boolean       m_bStreaming;
	
	private static final String ACT_SALIR = "exit";
	private static final String ACT_PERSONAL = "personal";
	private static final String ACT_ALUMNO = "alumno";
	private static final String ACT_GUARDAR = "guardar";
	
	private JComboBox comboGrado;
	private JLabel labelGrado;
	String fechaConFormato;
	
	ButtonGroup radios;
	JRadioButton rdbtnPersonal;
	JRadioButton rdbtnAlumno;
	JLabel lblDatosDelPadre;
	JPanel panel_Ind_Izq;
	
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
		frmRegistroHuella.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuella.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Underline@2x.png")));
		frmRegistroHuella.setBounds(100, 100, 1143, 675);
		frmRegistroHuella.setLocationRelativeTo(null);

		JLabel lblRegistro = new JLabel("Registro de huella para estudiantes y personal");
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 26));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel labelTipo = new JLabel("Tipo de registro");
		labelTipo.setForeground(SystemColor.textHighlight);
		labelTipo.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);		
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 15));
		btnGuardar.setActionCommand(ACT_GUARDAR);
		btnGuardar.addActionListener(this);
		
		
		// Boton para salir de la ventana
		JButton btnCancelar = new JButton("Cancelar");		
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(SystemColor.textHighlight);
		btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
		btnCancelar.setActionCommand(ACT_SALIR);
		btnCancelar.addActionListener(this);
		
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
		lblDatosDelPadre.setFont(new Font("Arial", Font.BOLD, 12));
		GroupLayout gl_panelAlumno = new GroupLayout(panelAlumno);
		gl_panelAlumno.setHorizontalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelAlumno.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDelPadre, GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelAlumno.setVerticalGroup(
			gl_panelAlumno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAlumno.createSequentialGroup()
					.addComponent(lblDatosDelPadre, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
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
		
		
		labelGrado = new JLabel("Grado y grupo");
		labelGrado.setForeground(SystemColor.textHighlight);
		labelGrado.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel label = new JLabel("Grado y grupo");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBox.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.textHighlight);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la huella");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setFont(new Font("Arial", Font.BOLD, 12));
		
		panel_Ind_Izq = new JPanel();
		panel_Ind_Izq.setBorder(new LineBorder(new Color(255, 0, 0), 2, true));
		panel_Ind_Izq.setBackground(Color.WHITE);
		m_image = new ImagePanel();
		Dimension dm = new Dimension(380, 380);
		m_image.setPreferredSize(dm);
		panel_Ind_Izq.add(m_image);
		
		JPanel panel_Ind_Der = new JPanel();
		panel_Ind_Der.setBackground(Color.WHITE);
		panel_Ind_Der.setBorder(new LineBorder(new Color(255, 0, 0), 2, true));
		
		JLabel lblIndiceIzquierdo = new JLabel("\u00CDndice izquierdo");
		lblIndiceIzquierdo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndiceIzquierdo.setForeground(SystemColor.textHighlight);
		lblIndiceIzquierdo.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblndiceDerecho = new JLabel("\u00CDndice derecho");
		lblndiceDerecho.setHorizontalAlignment(SwingConstants.CENTER);
		lblndiceDerecho.setForeground(SystemColor.textHighlight);
		lblndiceDerecho.setFont(new Font("Arial", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDatosDeLa, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(108)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIndiceIzquierdo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_Ind_Izq, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
					.addGap(93)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Ind_Der, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblndiceDerecho, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
					.addGap(85))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblDatosDeLa, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIndiceIzquierdo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblndiceDerecho, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Ind_Izq, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_Ind_Der, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
					.addGap(31))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(frmRegistroHuella.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(panelAlumno, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(328)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelGrado)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboGrado, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addComponent(comboBox, 0, 299, Short.MAX_VALUE))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(413)
							.addComponent(labelTipo, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(rdbtnPersonal)
							.addGap(48)
							.addComponent(rdbtnAlumno))
						.addComponent(lblRegistro, GroupLayout.PREFERRED_SIZE, 1137, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(294, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(388, Short.MAX_VALUE)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(123)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(366))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelAlumno, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(46))
		);
		frmRegistroHuella.getContentPane().setLayout(groupLayout);
	}
	
	
	
	private void StartCaptureThread(){
		m_capture = new CaptureThread(m_reader, m_bStreaming, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);
		m_capture.start(this);
	}

	private void StopCaptureThread(){
		if(null != m_capture) m_capture.cancel();
	}
	
	private void WaitForCaptureThread(){
		if(null != m_capture) m_capture.join(1000);
	}
	
	
	// Acciones de botones
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals(ACT_SALIR)){
			
		//	JOptionPane.showMessageDialog(null, comboGrado.getSelectedIndex(), "Yay, java", JOptionPane.PLAIN_MESSAGE);
			
			frmRegistroHuella.setVisible(false);
			
			frmRegistroHuella.dispose();
		}else if(e.getActionCommand().equals(ACT_PERSONAL)){
			panelAlumno.setVisible(true);
			comboGrado.setVisible(true);
			labelGrado.setVisible(true);
			labelGrado.setText("Personal");
			lblDatosDelPadre.setText("Datos del personal");
		}else if(e.getActionCommand().equals(ACT_ALUMNO)){
			panelAlumno.setVisible(true);
			comboGrado.setVisible(true);
			labelGrado.setVisible(true);
			labelGrado.setText("Grupo y grado");
			lblDatosDelPadre.setText("Datos del alumno");
		}else if (e.getActionCommand().equals(ACT_GUARDAR)){
			
			
		/********************************** VALIDACIONES DE LOS CAMPOS A INSERTAR **********************************************/	
			boolean bandera = true;
			String mensaje = "No debe dejar ningun campo vacio\n";	
			
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
				
				if(comboGrado.getSelectedItem().equals("Selecciona")){
					comboGrado.setBorder(new LineBorder(Color.RED));
					
					bandera = false;
					mensaje = mensaje+" Grado \n";
					}else{
						comboGrado.setBorder(new LineBorder(Color.decode("#7A8A99")));
					}
			
			
			if(!bandera){
				JOptionPane.showMessageDialog(frmRegistroHuella, mensaje, "Aviso", JOptionPane.PLAIN_MESSAGE);
			
			}
		}else if(e.getActionCommand().equals(CaptureThread.ACT_CAPTURE)){
			//event from capture thread
			CaptureThread.CaptureEvent evt = (CaptureThread.CaptureEvent)e;
			boolean bCanceled = false;
			MessageBox.Warning("Entre");
			if(null != evt.capture_result){
				if(null != evt.capture_result.image && Reader.CaptureQuality.GOOD == evt.capture_result.quality){
					//display image
					m_image.showImage(evt.capture_result.image);
				}
				else if(Reader.CaptureQuality.CANCELED == evt.capture_result.quality){
					//capture or streaming was canceled, just quit
					bCanceled = true;
				}
				else{
					//bad quality
					MessageBox.BadQuality(evt.capture_result.quality);
				}
			}
			else if(null != evt.exception){
				//exception during capture
				MessageBox.DpError("Capture",  evt.exception);
				bCanceled = true;
			}
			else if(null != evt.reader_status){
				MessageBox.BadStatus(evt.reader_status);
				bCanceled = true;
			}
			
			if(!bCanceled){
				if(!m_bStreaming){
					//restart capture thread
					WaitForCaptureThread();
					StartCaptureThread();
				}
			}			
		}
	}
	
	
	
	private void modal(){
		//open reader
		try{
			m_reader.Open(Reader.Priority.COOPERATIVE);
		}
		catch(UareUException e){ MessageBox.DpError("Reader.Open()", e); }
		
		boolean bOk = true;
		if(m_bStreaming){
			//check if streaming supported
			Reader.Capabilities rc = m_reader.GetCapabilities();
			if(!rc.can_stream){
				MessageBox.Warning("El lector de huellas no soporta streaming");
				bOk = false;
			}
		}
		
		if(bOk){
			//start capture thread
			MessageBox.Warning("Entre modal");
			StartCaptureThread();

			//cancel capture
			StopCaptureThread();
			
			//wait for capture thread to finish
			WaitForCaptureThread();
		}
		
		//close reader
		try{
			m_reader.Close();
		}
		catch(UareUException e){ MessageBox.DpError("Reader.Close()", e); }
	}
	
	
	
	public static void run(Reader reader, boolean bStreaming) {
		RegistroHuella window = new RegistroHuella(reader, bStreaming);
		window.frmRegistroHuella.setVisible(true);
		window.modal();
		
	}
}
