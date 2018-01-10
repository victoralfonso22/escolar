package classes;
import java.awt.Color;
import java.awt.Dimension;
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
	private Reader        m_reader;
	private ImagePanel    m_image;
	private boolean       m_bStreaming;
	public List<Huellas> m_listDeHuellas = new ArrayList<Huellas>();
    public List<Fmd> m_fmdList = new ArrayList<Fmd>();
    public Fmd[] m_fmdArray = null;
    JLabel lblNombre;
    private Timer machineMoveTimer;
    
	
	ImageIcon imagen1, imagenHuella; //declaro 3 variables del tipo iamgen
    
	private Asistencia(Reader reader, boolean bStreaming){
		
		imagen1 = new ImageIcon(getClass().getResource("/imagenes/manos-blancas-colorea.jpg"));
		imagenHuella = new ImageIcon(getClass().getResource("/imagenes/huella.png"));
		
		
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
								
								
								JButton btnBack = new JButton("Regresar");
								btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								btnBack.setForeground(Color.WHITE);
								btnBack.setBackground(SystemColor.textHighlight);
								btnBack.setFont(new Font("Arial", Font.BOLD, 15));
								btnBack.setActionCommand(ACT_BACK);
								btnBack.addActionListener(this);
						
								m_image = new ImagePanel();
								m_image.setBackground(SystemColor.textHighlight);
								m_image.setForeground(new Color(255, 0, 0));
								m_image.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
								
								JLabel lblNewLabel = new JLabel("Huella dactilar");
								lblNewLabel.setForeground(SystemColor.textHighlight);
								lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
								
								JPanel panel = new JPanel();
								panel.setBorder(new LineBorder(SystemColor.textHighlight, 3, true));
								panel.setBackground(Color.WHITE);
																
								
								GroupLayout groupLayout = new GroupLayout(this);
								groupLayout.setHorizontalGroup(
									groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(m_image, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
													.addGap(157)))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(panel, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
											.addGap(53))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addGap(467)
											.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(471, Short.MAX_VALUE))
								);
								groupLayout.setVerticalGroup(
									groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(40)
													.addComponent(panel, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(51)
													.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(m_image, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
											.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addGap(24))
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
	}
	
	public void time(){
					
		machineMoveTimer = new Timer(2000, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
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
	                          
	                        	lblNombre.setText(m_listDeHuellas.get(matches[0].fmd_index).getNombre());
	                        }else{
	                        	lblNombre.setText("No encontrado");
	                        }
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
                Fmd fmd = UareUGlobal.GetImporter().ImportFmd(huellas.getFMD(), com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004, com.digitalpersona.uareu.Fmd.Format.ANSI_378_2004);
                this.m_fmdList.add(fmd);
            }
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
	
	
	public static void Run(Reader reader, boolean bStreaming){
    	JDialog dlg = new JDialog((JDialog)null, "Captura de huellas dactilares.", true);
    	dlg.setResizable(true);
    	Asistencia capture = new Asistencia(reader, bStreaming);
    	capture.datosHuellas();
    	capture.hideImg();
    	capture.doModal(dlg);
	}
}
