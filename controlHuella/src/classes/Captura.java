package classes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.digitalpersona.uareu.*;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTextField;

public class Captura 
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
	private JLabel txtManoDerecha;
	
	private Captura(Reader reader, boolean bStreaming){
		setBackground(Color.WHITE);
		m_reader = reader;
		m_bStreaming = bStreaming;
		
		m_capture = new CaptureThread(m_reader, m_bStreaming, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);

		final int vgap = 5;		
		panelAlumno = new JPanel();
		panelAlumno.setBackground(Color.WHITE);
		panelAlumno.setForeground(SystemColor.textHighlight);
		panelAlumno.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Dimension dm = new Dimension(380, 380);
								
								
								JButton btnBack = new JButton("Cancelar");
								btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								btnBack.setForeground(Color.WHITE);
								btnBack.setBackground(SystemColor.textHighlight);
								btnBack.setFont(new Font("Arial", Font.BOLD, 15));
								btnBack.setActionCommand(ACT_BACK);
								btnBack.addActionListener(this);
						
								m_image = new ImagePanel();
								m_image.setPreferredSize(dm);
								
								JButton btnGuardar = new JButton("Guardar");
								btnGuardar.setForeground(Color.WHITE);
								btnGuardar.setFont(new Font("Arial", Font.BOLD, 15));
								btnGuardar.setBackground(SystemColor.textHighlight);
								btnGuardar.setActionCommand("back");
								
								txtManoDerecha = new JLabel("Mano derecha - dedo \u00EDndice");
								txtManoDerecha.setHorizontalAlignment(SwingConstants.CENTER);
								txtManoDerecha.setText("Mano derecha - dedo \u00EDndice");
								txtManoDerecha.setFont(new Font("Arial", Font.BOLD, 13));
																
								
								GroupLayout groupLayout = new GroupLayout(this);
								groupLayout.setHorizontalGroup(
									groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(49)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
													.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
												.addComponent(m_image, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
												.addComponent(txtManoDerecha, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
											.addGap(47))
								);
								groupLayout.setVerticalGroup(
									groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(40)
											.addComponent(txtManoDerecha, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(m_image, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
											.addGap(26)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
											.addGap(21))
								);
								setLayout(groupLayout);
								
								JButton btnNewButton = new JButton("New button");
								btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
								
								GroupLayout gl_panelAlumno = new GroupLayout(panelAlumno);
								
								gl_panelAlumno.setVerticalGroup(
									gl_panelAlumno.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelAlumno.createSequentialGroup()
											.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(239, Short.MAX_VALUE))
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
		}
		else if(e.getActionCommand().equals(CaptureThread.ACT_CAPTURE)){
			//event from capture thread
			CaptureThread.CaptureEvent evt = (CaptureThread.CaptureEvent)e;
			boolean bCanceled = false;
			
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
			m_dlgParent.toFront();
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
	
	public static void Run(Reader reader, boolean bStreaming){
    	JDialog dlg = new JDialog((JDialog)null, "Captura de huellas dactilares.", true);
    	Captura capture = new Captura(reader, bStreaming);
    	capture.doModal(dlg);
	}
}
