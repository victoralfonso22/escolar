package classes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.digitalpersona.uareu.*;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.Cursor;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import conexion.Metodos;
import clases.Huellas;
import dao.HuellasDAO;

import javax.swing.LayoutStyle.ComponentPlacement;

public class Asistencia 
	extends JPanel
	implements ActionListener
{
	private static final long serialVersionUID = 2;
	private static final String ACT_BACK = "back";
	
	private JPanel panelAlumno;
	private JDialog       m_dlgParent;
	private CaptureThread m_capture;
	private Thread espera;
	private Reader        m_reader;
	private ImagePanel    m_image;
	private boolean       m_bStreaming;
	public List<Huellas> m_listDeHuellas = new ArrayList<Huellas>();
    public List<Fmd> m_fmdList = new ArrayList<Fmd>();
    public Fmd[] m_fmdArray = null;
    JLabel lblNombre;
    private Timer machineMoveTimer;
    private boolean corriendo;
    
	
	ImageIcon imagen1, imagenHuella; //declaro 3 variables del tipo iamgen
    
	private Asistencia(Reader reader, boolean bStreaming){
		corriendo = true;
		
		imagen1 = new ImageIcon(getClass().getResource("/imagenes/manos-blancas-colorea.jpg"));
		imagenHuella = new ImageIcon(getClass().getResource("/imagenes/huella.png"));
		
		
		setBorder(new LineBorder(Color.BLACK));
		setBackground(Color.WHITE);
		m_reader = reader;
		m_bStreaming = bStreaming;
	//	System.out.println("nombre1 "+m_image.getName());
		m_capture = new CaptureThread(m_reader, m_bStreaming, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);
		espera = new Thread();
		espera.start();

		final int vgap = 5;		
		panelAlumno = new JPanel();
		panelAlumno.setBackground(Color.WHITE);
		panelAlumno.setForeground(SystemColor.textHighlight);
		panelAlumno.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		Dimension dm = new Dimension(200, 200);
								
								
								JButton btnBack = new JButton("Regresar");
								btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
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
								
								JLabel lblNewLabel = new JLabel("Huella dactilar");
								lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
								lblNewLabel.setForeground(new Color(0, 102, 0));
								lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
								
								JPanel panel = new JPanel();
								panel.setBorder(new LineBorder(new Color(0, 102, 0), 3, true));
								panel.setBackground(Color.WHITE);
																
								
								GroupLayout groupLayout = new GroupLayout(this);
								groupLayout.setHorizontalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addContainerGap()
													.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(109)
													.addComponent(m_image, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE))
											.addGap(53))
								);
								groupLayout.setVerticalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(51)
													.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(m_image, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(40)
													.addComponent(panel, GroupLayout.PREFERRED_SIZE, 565, GroupLayout.PREFERRED_SIZE)))
											.addGap(39)
											.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(47, Short.MAX_VALUE))
								);
								
								lblNombre = new JLabel("");
								lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
								lblNombre.setForeground(SystemColor.textHighlight);
								lblNombre.setFont(new Font("Arial", Font.BOLD, 20));
								GroupLayout gl_panel = new GroupLayout(panel);
								gl_panel.setHorizontalGroup(
									gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(127)
											.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(97, Short.MAX_VALUE))
								);
								gl_panel.setVerticalGroup(
									gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(70)
											.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(217, Short.MAX_VALUE))
								);
								panel.setLayout(gl_panel);
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
		//time(this);
		
	}
	
	public void time(ActionListener action){
					
		machineMoveTimer = new Timer(2000,action );
		machineMoveTimer.start();
		hideImg();
		lblNombre.setText("");
	}
	

	private void StopCaptureThread(){
		if(null != m_capture) m_capture.cancel();
	}
	
	private void WaitForCaptureThread(){
		if(null != m_capture) m_capture.join(1000);
	}
	
	private void WaitBeforeCaptureThread(){
		if(null != m_capture && !corriendo)
			try {
				synchronized (m_image){
				m_capture.wait(2000);
				JOptionPane.showMessageDialog(this, "Espere..", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals(ACT_BACK)){
			//event from "back" button
			//cancel capture
			StopCaptureThread();
		}else if(e.getActionCommand().equals(CaptureThread.ACT_CAPTURE)){
			
			Engine engine = UareUGlobal.GetEngine();
			
			//event from capture thread
			
			CaptureThread.CaptureEvent evt = (CaptureThread.CaptureEvent)e;
			boolean bCanceled = false;
			
			if(null != evt.capture_result){
				if(null != evt.capture_result.image && Reader.CaptureQuality.GOOD == evt.capture_result.quality){
					
						try{
							Fmd fmdCapturado = engine.CreateFmd(evt.capture_result.image, Fmd.Format.ANSI_378_2004);
							
							int falsepositive_rate = Engine.PROBABILITY_ONE / 100000;  //target rate is 0.00001							
						//	MessageBox.Warning(fmdCapturado.toString());
							
	                        Engine.Candidate[] matches = engine.Identify(fmdCapturado, 0, m_fmdArray, falsepositive_rate, 1);
	                        if (matches.length == 1) {
	                          
	                        	lblNombre.setText(String.valueOf(m_listDeHuellas.get(matches[0].fmd_index).getId()));
	                        }else{
	                        	lblNombre.setText("No registrado");
	                        }
	                        m_image.showImage(evt.capture_result.image);
						//	JOptionPane.showMessageDialog(m_dlgParent,fmdCapturado.getData()+" "+fmdCapturado.getData().length, "Aviso", JOptionPane.WARNING_MESSAGE);
						//	ByteArrayInputStream fingerprintInfo = new ByteArrayInputStream(fmdCapturado.getData());
						//	Integer fingerprintSize = fmdCapturado.getData().length;
						//	Metodos met = new Metodos();
						//	met.guardarFDM(fingerprintInfo,fingerprintSize,m_dlgParent);
						//	byte[] result =	met.regresarFDM(m_dlgParent);
						//	JOptionPane.showMessageDialog(m_dlgParent,result, "Aviso", JOptionPane.WARNING_MESSAGE);
						//	Fmd fmdOtro = UareUGlobal.GetImporter().ImportFmd(result, com.digitalpersona.uareu.Fmd.Format.DP_REG_FEATURES, com.digitalpersona.uareu.Fmd.Format.DP_REG_FEATURES);
						//	JOptionPane.showMessageDialog(m_dlgParent,fmdOtro.getData()+" "+fmdOtro.getData().length, "Aviso", JOptionPane.WARNING_MESSAGE);
						}
						catch(UareUException ev){ MessageBox.DpError("Engine.CreateFmd()", ev); }
						
					//display image
					
					//System.out.println("Nombre2 "+m_image.getName());					
					corriendo =false;
					//WaitBeforeCaptureThread();
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
			m_dlgParent.setResizable(true);
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
	
	public void datosHuellas(){
		try {
            
			HuellasDAO huellasDao = new HuellasDAO();
            this.m_listDeHuellas = huellasDao.retornarTodasHuellas();             
            for (Huellas huellas : this.m_listDeHuellas) {
            	if(huellas.getIzq_menique() != null){
            	Fmd fmd = UareUGlobal.GetImporter().ImportFmd(huellas.getIzq_menique(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                this.m_fmdList.add(fmd);
            	}
            	
            	if(huellas.getIzq_anular()!= null){                
            	Fmd fmd1 = UareUGlobal.GetImporter().ImportFmd(huellas.getIzq_anular(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                this.m_fmdList.add(fmd1);
            	}
                
            	if(huellas.getIzq_medio()!= null){
            		Fmd fmd2 = UareUGlobal.GetImporter().ImportFmd(huellas.getIzq_medio(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                this.m_fmdList.add(fmd2);
            	}
            	
            	if(huellas.getIzq_indice()!= null){
            		Fmd fmd3 = UareUGlobal.GetImporter().ImportFmd(huellas.getIzq_indice(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                this.m_fmdList.add(fmd3);
            	}
                
            	if(huellas.getIzq_pulgar()!= null){
            		Fmd fmd4 = UareUGlobal.GetImporter().ImportFmd(huellas.getIzq_pulgar(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                this.m_fmdList.add(fmd4);
            	}
            	
            	if(huellas.getDer_menique()!= null){
            		Fmd fmd5 = UareUGlobal.GetImporter().ImportFmd(huellas.getDer_menique(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                this.m_fmdList.add(fmd5);
            	}
                
            	if(huellas.getDer_anular()!= null){
            		Fmd fmd6 = UareUGlobal.GetImporter().ImportFmd(huellas.getDer_anular(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                    this.m_fmdList.add(fmd6);
                }
            	
            	if(huellas.getDer_medio()!= null){
            		Fmd fmd7 = UareUGlobal.GetImporter().ImportFmd(huellas.getDer_medio(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                    this.m_fmdList.add(fmd7);
                }
            	
            	if(huellas.getDer_indice()!= null){
            		Fmd fmd8 = UareUGlobal.GetImporter().ImportFmd(huellas.getDer_indice(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                    this.m_fmdList.add(fmd8);
                }
            	
            	if(huellas.getDer_pulgar()!= null){
            		Fmd fmd9 = UareUGlobal.GetImporter().ImportFmd(huellas.getDer_pulgar(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                    this.m_fmdList.add(fmd9);
                }
            	
            }
            
            MessageBox.Warning(String.valueOf(this.m_fmdList.size()));
            
            m_fmdArray = new Fmd[this.m_fmdList.size()];
            
            this.m_fmdList.toArray(m_fmdArray);            
            //       loadDbinMem();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            MessageBox.DpError("Failed to load FMDs from database.  Please check connection string in code.", null);
            return;
        } catch (UareUException e1) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Error importing fmd data.");
            return;
        }
	}
	
	public void hideImg(){
		m_image.hideImage(imagenHuella);
	}
	
	
	public static void Run(Reader reader, boolean bStreaming, Image im){
    	JDialog dlg = new JDialog((JDialog)null, "Captura de huellas dactilares.", true);
    	dlg.setResizable(true);
    	dlg.setIconImage(im);
    	Asistencia capture = new Asistencia(reader, bStreaming);
    	capture.datosHuellas();
    	capture.hideImg();
    	capture.doModal(dlg);
	}
}
