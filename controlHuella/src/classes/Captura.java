package classes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clases.Alumno;
import clases.Personal;

import com.digitalpersona.uareu.*;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.Cursor;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import conexion.Metodos;

public class Captura 
	extends JPanel
	implements ActionListener
{
	private static final long serialVersionUID = 2;
	private static final String ACT_BACK = "back";
	private static final String ACT_GUARDAR = "guardar";
	private static final String ACT_IMEQ = "izquierda_menique";
	private static final String ACT_IANU = "izquierda_anular";
	private static final String ACT_IMED = "izquierda_medio";
	private static final String ACT_IIND = "izquierda_indice";
	private static final String ACT_IPUL = "izquierda_pulgar";
	
	private static final String ACT_DMEQ = "derecha_menique";
	private static final String ACT_DANU = "derecha_anular";
	private static final String ACT_DMED = "derecha_medio";
	private static final String ACT_DIND = "derecha_indice";
	private static final String ACT_DPUL = "derecha_pulgar";
	
	private JPanel panelAlumno;
	private JDialog       m_dlgParent;
	private CaptureThread m_capture;
	private Reader        m_reader;
	private ImagePanel    m_image;
	private boolean       m_bStreaming;
	private JLabel txtDedo;
	
	JRadioButton radioIMenique;
	JRadioButton radioIAnular;
	JRadioButton radioIMedio;
	JRadioButton radioIIndice;
	JRadioButton radioIPulgar;
	
	JRadioButton radioDMenique;
	JRadioButton radioDAnular;
	JRadioButton radioDMedio;
	JRadioButton radioDIndice;
	JRadioButton radioDPulgar;
	
	JLabel lblTipo;
	JLabel labelNombre;
	
	ByteArrayInputStream fingerprintInfo = null;
	Integer fingerprintSize = 0;
	
	ButtonGroup radios;
	
	int tipo_dedo, id_usuario, tipo_usario;
	
	ImageIcon imagen1, blanco,icono; //declaro 3 variables del tipo iamgen
    
	private Captura(Reader reader, boolean bStreaming, Alumno alumno, Personal personal){
		
		imagen1 = new ImageIcon(getClass().getResource("/imagenes/manos-blancas-colorea.jpg"));
		blanco = new ImageIcon(getClass().getResource("/imagenes/blanco.png"));
		
		
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.WHITE);
		m_reader = reader;
		m_bStreaming = bStreaming;
		
		m_capture = new CaptureThread(m_reader, m_bStreaming, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);

		final int vgap = 5;		
		panelAlumno = new JPanel();
		panelAlumno.setBackground(Color.WHITE);
		panelAlumno.setForeground(SystemColor.textHighlight);
		panelAlumno.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		Dimension dm = new Dimension(200, 200);
								
								
								JButton btnBack = new JButton("Cancelar");
								btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								btnBack.setForeground(Color.WHITE);
								btnBack.setBackground(new Color(0, 102, 0));
								btnBack.setFont(new Font("Arial", Font.BOLD, 15));
								btnBack.setActionCommand(ACT_BACK);
								btnBack.addActionListener(this);
						
								m_image = new ImagePanel();
								m_image.setBackground(SystemColor.textHighlight);
								m_image.setForeground(new Color(255, 0, 0));
								m_image.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
								
								//m_image.setPreferredSize(dm);
								
								JButton btnGuardar = new JButton("Guardar");
								btnGuardar.setForeground(Color.WHITE);
								btnGuardar.setFont(new Font("Arial", Font.BOLD, 15));
								btnGuardar.setBackground(new Color(0, 102, 0));
								btnGuardar.setActionCommand(ACT_GUARDAR);
								btnGuardar.addActionListener(this);
								
								txtDedo = new JLabel();
								txtDedo.setForeground(Color.RED);
								txtDedo.setHorizontalAlignment(SwingConstants.CENTER);
								//txtDedo.setText("Mano derecha - dedo \u00EDndice");
								txtDedo.setFont(new Font("Arial", Font.BOLD, 14));
								
								radioIMenique = new JRadioButton("");
								radioIMenique.setBackground(Color.WHITE);
								radioIMenique.setActionCommand(ACT_IMEQ);
								radioIMenique.addActionListener(this);
								
								radioIAnular = new JRadioButton("");
								radioIAnular.setBackground(Color.WHITE);
								radioIAnular.setActionCommand(ACT_IANU);
								radioIAnular.addActionListener(this);
								
								radioIMedio = new JRadioButton("");
								radioIMedio.setBackground(Color.WHITE);
								radioIMedio.setActionCommand(ACT_IMED);
								radioIMedio.addActionListener(this);
								
								radioIIndice = new JRadioButton("");
								radioIIndice.setBackground(Color.WHITE);
								radioIIndice.setActionCommand(ACT_IIND);
								radioIIndice.addActionListener(this);
								
								radioIPulgar = new JRadioButton("");
								radioIPulgar.setBackground(Color.WHITE);
								radioIPulgar.setActionCommand(ACT_IPUL);
								radioIPulgar.addActionListener(this);
								
								radioDPulgar = new JRadioButton("");
								radioDPulgar.setBackground(Color.WHITE);
								radioDPulgar.setActionCommand(ACT_DPUL);
								radioDPulgar.addActionListener(this);
								
								radioDIndice = new JRadioButton("");
								radioDIndice.setBackground(Color.WHITE);
								radioDIndice.setActionCommand(ACT_DIND);
								radioDIndice.addActionListener(this);
								
								radioDMedio = new JRadioButton("");
								radioDMedio.setBackground(Color.WHITE);
								radioDMedio.setActionCommand(ACT_DMED);
								radioDMedio.addActionListener(this);
								
								radioDAnular = new JRadioButton("");
								radioDAnular.setBackground(Color.WHITE);
								radioDAnular.setActionCommand(ACT_DANU);
								radioDAnular.addActionListener(this);
								
								radioDMenique = new JRadioButton("");
								radioDMenique.setBackground(Color.WHITE);
								radioDMenique.setActionCommand(ACT_DMEQ);
								radioDMenique.addActionListener(this);
								
								radios = new ButtonGroup();
								radios.add(radioDMenique);
								radios.add(radioDAnular);
								radios.add(radioDMedio);
								radios.add(radioDIndice);
								radios.add(radioDPulgar);
								radios.add(radioIAnular);
								radios.add(radioIMenique);
								radios.add(radioIMedio);
								radios.add(radioIIndice);
								radios.add(radioIPulgar);
								
								JLabel lblManoIzquierda = new JLabel("Mano izquierda");
								lblManoIzquierda.setForeground(new Color(0, 102, 0));
								lblManoIzquierda.setHorizontalAlignment(SwingConstants.CENTER);
								lblManoIzquierda.setFont(new Font("Arial", Font.BOLD, 12));
								
								JLabel lblManoDerecha = new JLabel("Mano derecha");
								lblManoDerecha.setForeground(new Color(0, 102, 0));
								lblManoDerecha.setHorizontalAlignment(SwingConstants.CENTER);
								lblManoDerecha.setFont(new Font("Arial", Font.BOLD, 12));
								
								JLabel lblNewLabel = new JLabel("");
								lblNewLabel.setIcon(new ImageIcon(Captura.class.getResource("/imagenes/mano-izquierda.png")));
								
								JLabel lblNewLabel_1 = new JLabel("");
								lblNewLabel_1.setIcon(new ImageIcon(Captura.class.getResource("/imagenes/mano-derecha.png")));
								
								lblTipo = new JLabel("");
								lblTipo.setForeground(new Color(0, 102, 0));
								lblTipo.setFont(new Font("Arial", Font.PLAIN, 22));
								
								labelNombre = new JLabel("");
								labelNombre.setForeground(Color.BLACK);
								labelNombre.setFont(new Font("Arial", Font.PLAIN, 22));
								
								if(personal == null){
									lblTipo.setText("Alumno :");
									labelNombre.setText(alumno.getNombre_completo());
									id_usuario = alumno.getId();
									tipo_usario = 1;
								}else if(alumno == null){
									lblTipo.setText("Personal :");
									labelNombre.setText(personal.getNombre_completo());
									id_usuario = personal.getId();
									tipo_usario = 2;
								}
																
								
								GroupLayout groupLayout = new GroupLayout(this);
								groupLayout.setHorizontalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(218)
											.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
											.addGap(179)
											.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(256, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(29)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(48)
													.addComponent(lblManoIzquierda, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
													.addGap(198)
													.addComponent(lblManoDerecha, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
													.addGap(42))
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(52)
															.addComponent(radioIAnular, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addGap(34)
															.addComponent(radioIMedio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addGap(39)
															.addComponent(radioIIndice, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(radioIMenique)
															.addPreferredGap(ComponentPlacement.UNRELATED)
															.addComponent(lblNewLabel)))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(radioIPulgar, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
															.addComponent(radioDPulgar, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(radioDMenique, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(radioDIndice, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addGap(39)
															.addComponent(radioDMedio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addGap(33)
															.addComponent(radioDAnular, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
															.addGap(49)))))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtDedo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(m_image, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
											.addGap(19))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addContainerGap(168, Short.MAX_VALUE)
											.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(labelNombre, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
											.addGap(253))
								);
								groupLayout.setVerticalGroup(
									groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(28)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTipo, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
												.addComponent(labelNombre, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(41)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
															.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																	.addGroup(groupLayout.createSequentialGroup()
																		.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																			.addComponent(radioIIndice, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
																			.addComponent(radioIAnular, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(ComponentPlacement.RELATED))
																	.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(radioIMedio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
																		.addGap(11))
																	.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(radioDAnular, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED))
																	.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(radioDMedio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.UNRELATED))
																	.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(radioDIndice, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)))
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																	.addComponent(lblNewLabel)
																	.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
																.addGap(20)
																.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																	.addComponent(lblManoDerecha, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
																	.addComponent(lblManoIzquierda, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
															.addGroup(groupLayout.createSequentialGroup()
																.addGap(75)
																.addComponent(radioDMenique, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
														.addGroup(groupLayout.createSequentialGroup()
															.addGap(78)
															.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addComponent(radioDPulgar, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(radioIMenique)
																	.addGap(40)
																	.addComponent(radioIPulgar, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))))
													.addGap(31))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(txtDedo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
													.addGap(6)
													.addComponent(m_image, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)))
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
											.addGap(23))
								);
								setLayout(groupLayout);
								
								JButton btnNewButton = new JButton("New button");
								btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
								
								GroupLayout gl_panelAlumno = new GroupLayout(panelAlumno);
								
								gl_panelAlumno.setVerticalGroup(
									gl_panelAlumno.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelAlumno.createSequentialGroup()
											.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(200, Short.MAX_VALUE))
								);
								panelAlumno.setLayout(gl_panelAlumno);
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
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals(ACT_BACK)){
			//event from "back" button
			//cancel capture
			StopCaptureThread();
		}else if(e.getActionCommand().equals(ACT_GUARDAR)){
			if(fingerprintInfo!=null){
				Metodos met = new Metodos();
				
				if(radioIMenique.isSelected()){
					tipo_dedo = 1;
				}else if(radioIAnular.isSelected()){
					tipo_dedo = 2;
				}else if(radioIMedio.isSelected()){
					tipo_dedo = 3;
				}else if(radioIIndice.isSelected()){
					tipo_dedo = 4;
				}else if(radioIPulgar.isSelected()){
					tipo_dedo = 5;
				}else if(radioDMenique.isSelected()){
					tipo_dedo = 6;
				}else if(radioDAnular.isSelected()){
					tipo_dedo = 7;
				}else if(radioDMedio.isSelected()){
					tipo_dedo = 8;
				}else if(radioDIndice.isSelected()){
					tipo_dedo = 9;
				}else if(radioDPulgar.isSelected()){
					tipo_dedo = 10;
				}
				
				
				if(met.guardarFDM(fingerprintInfo,fingerprintSize,m_dlgParent,tipo_dedo,id_usuario,tipo_usario)){
					JOptionPane.showMessageDialog(m_dlgParent,"Huella guardada!", "Exito", JOptionPane.INFORMATION_MESSAGE);	
				}
			//	byte[] result =	met.regresarFDM(m_dlgParent);
					
			}else{
				JOptionPane.showMessageDialog(m_dlgParent,"Necesita introducir una huella", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getActionCommand().equals(ACT_IMEQ)) {			
			txtDedo.setText("Mano izquierda - dedo meñique");
			m_image.hideImage(blanco);
			
			
		}else if(e.getActionCommand().equals(ACT_IANU)) {
		
			txtDedo.setText("Mano izquierda - dedo anular");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(ACT_IMED)) {
		
			txtDedo.setText("Mano izquierda - dedo medio");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(ACT_IIND)) {
		
			txtDedo.setText("Mano izquierda - dedo índice");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(ACT_IPUL)) {
		
			txtDedo.setText("Mano izquierda - dedo pulgar");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(ACT_DMEQ)) {			
			txtDedo.setText("Mano derecha - dedo meñique");
			m_image.hideImage(blanco);
			
			
		}else if(e.getActionCommand().equals(ACT_DANU)) {
		
			txtDedo.setText("Mano derecha - dedo anular");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(ACT_DMED)) {
		
			txtDedo.setText("Mano derecha - dedo medio");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(ACT_DIND)) {
		
			txtDedo.setText("Mano derecha - dedo índice");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(ACT_DPUL)) {
		
			txtDedo.setText("Mano derecha - dedo pulgar");
			m_image.hideImage(blanco);
			
		}else if(e.getActionCommand().equals(CaptureThread.ACT_CAPTURE)){
			
			Engine engine = UareUGlobal.GetEngine();
			
			//event from capture thread
			CaptureThread.CaptureEvent evt = (CaptureThread.CaptureEvent)e;
			boolean bCanceled = false;
			
			if(null != evt.capture_result){
				if(null != evt.capture_result.image && Reader.CaptureQuality.GOOD == evt.capture_result.quality){
					if(!radioDMenique.isSelected() && !radioDAnular.isSelected() && !radioDMedio.isSelected() && !radioDIndice.isSelected() && !radioDPulgar.isSelected()
							&& !radioIAnular.isSelected() && !radioIMenique.isSelected() && !radioIMedio.isSelected() && !radioIIndice.isSelected() && !radioIPulgar.isSelected()){
						
						
						JOptionPane.showMessageDialog(m_dlgParent, "Debe seleccionar un dedo a capturar", "Aviso", JOptionPane.WARNING_MESSAGE);
					}else {
					
						try{
							Fmd fmdCapturado = engine.CreateFmd(evt.capture_result.image, Fmd.Format.ANSI_378_2004);
						//	JOptionPane.showMessageDialog(m_dlgParent,fmdCapturado.getData()+" "+fmdCapturado.getData().length, "Aviso", JOptionPane.WARNING_MESSAGE);
							fingerprintInfo = new ByteArrayInputStream(fmdCapturado.getData());
							fingerprintSize = fmdCapturado.getData().length;
							
						//	Fmd fmdOtro = UareUGlobal.GetImporter().ImportFmd(result, com.digitalpersona.uareu.Fmd.Format.DP_REG_FEATURES, com.digitalpersona.uareu.Fmd.Format.DP_REG_FEATURES);
						//	JOptionPane.showMessageDialog(m_dlgParent,fmdOtro.getData()+" "+fmdOtro.getData().length, "Aviso", JOptionPane.WARNING_MESSAGE);
						}
						catch(UareUException ev){ MessageBox.DpError("Engine.CreateFmd()", ev); }
						
					//display image
					m_image.showImage(evt.capture_result.image);
					}
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
			else{
				//destroy dialog
				m_dlgParent.setVisible(false);
			}
			
		}
		
	}

	private void doModal(JDialog dlgParent){
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
				MessageBox.Warning("This reader does not support streaming");
				bOk = false;
			}
		}
		
		if(bOk){
			//start capture thread
			StartCaptureThread();
	
			//bring up modal dialog
			m_dlgParent = dlgParent;
			m_dlgParent.setContentPane(this);
			m_dlgParent.pack();
			m_dlgParent.setLocationRelativeTo(null);
			//m_dlgParent.toFront();
			m_dlgParent.setAlwaysOnTop(true);
			m_dlgParent.setVisible(true);
			m_dlgParent.dispose();
			
			
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
	
	public static void Run(Reader reader, boolean bStreaming, Image im, Alumno alumno, Personal personal){
    	JDialog dlg = new JDialog((JDialog)null, "Captura de huellas dactilares.", true);   
    	dlg.setIconImage(im);
    	Captura capture = new Captura(reader, bStreaming, alumno, personal);
    	capture.doModal(dlg);
	}
}
