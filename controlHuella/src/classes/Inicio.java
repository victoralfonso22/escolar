package classes;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.digitalpersona.uareu.*;

public class Inicio 
	extends JPanel
	implements ActionListener
{
	private static final long serialVersionUID=1;
	
	private static final String ACT_SELECTION = "selection";
	private static final String ACT_CAPTURE = "capture";
	private static final String ACT_STREAMING = "streaming";
	private static final String ACT_VERIFICATION = "verification";
	private static final String ACT_IDENTIFICATION = "identification";
	private static final String ACT_ENROLLMENT = "enrollment";
	private static final String ACT_EXIT = "exit";
	
	private JDialog   m_dlgParent;
	private JTextArea m_textReader;
	
	private ReaderCollection m_collection;
	private Reader           m_reader;
	
	private Inicio(){
		final int vgap = 1;
		final int width = 1000;
		final int height = 150;
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);
		
		add(Box.createVerticalStrut(vgap));
		JLabel lblReader = new JLabel("Seleccione lector:");
		add(lblReader);
		add(Box.createVerticalStrut(vgap));
		Dimension dm = lblReader.getPreferredSize();
		dm.width = width;
		dm.height = height;
		lblReader.setPreferredSize(dm);
		
		
		m_textReader = new JTextArea(2, 1);
		m_textReader.setEditable(false);		
		JScrollPane paneReader = new JScrollPane(m_textReader);
		//paneReader.setSize(50, 20);
		add(paneReader);
		add(Box.createVerticalStrut(vgap));
		add(Box.createVerticalStrut(vgap));

		JButton btnCapture = new JButton("Capturar huella");
		btnCapture.setActionCommand(ACT_CAPTURE);
		btnCapture.addActionListener(this);
		
		JButton btnSelect = new JButton("Selecionar nuevo lector de huellas");
		btnSelect.setActionCommand(ACT_SELECTION);
		btnSelect.addActionListener(this);
		add(btnSelect);
		add(btnCapture);
		add(Box.createVerticalStrut(vgap));
		
		/*JButton btnStreaming = new JButton("Run streaming");
		btnStreaming.setActionCommand(ACT_STREAMING);
		btnStreaming.addActionListener(this);
		add(btnStreaming);
		add(Box.createVerticalStrut(vgap));*/
		
		JButton btnVerification = new JButton("Verificación de huella");
		btnVerification.setActionCommand(ACT_VERIFICATION);
		btnVerification.addActionListener(this);
		add(btnVerification);
		add(Box.createVerticalStrut(vgap));
		
		JButton btnIdentification = new JButton("Verificar identidad");
		btnIdentification.setActionCommand(ACT_IDENTIFICATION);
		btnIdentification.addActionListener(this);
		add(btnIdentification);
		add(Box.createVerticalStrut(vgap));

		JButton btnEnrollment = new JButton("Registrar huella");
		btnEnrollment.setActionCommand(ACT_ENROLLMENT);
		btnEnrollment.addActionListener(this);
		add(btnEnrollment);
		add(Box.createVerticalStrut(vgap));

		add(Box.createVerticalStrut(vgap));
		JButton btnExit = new JButton("Salir");
		btnExit.setActionCommand(ACT_EXIT);
		btnExit.addActionListener(this);
		add(btnExit);
		add(Box.createVerticalStrut(vgap));

		setOpaque(true);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals(ACT_SELECTION)){
			m_reader = Selection.Select(m_collection);
			if(null != m_reader){
				m_textReader.setText(m_reader.GetDescription().name);
			}
			else{
				m_textReader.setText("");
			}
		}
		else if(e.getActionCommand().equals(ACT_CAPTURE)){
			if(null == m_reader){
				MessageBox.Warning("Reader is not selected");
			}
			else{
				Capture.Run(m_reader, false);
			}
		}
		else if(e.getActionCommand().equals(ACT_STREAMING)){
			if(null == m_reader){
				MessageBox.Warning("Reader is not selected");
			}
			else{
				Capture.Run(m_reader, true);
			}
		}
		else if(e.getActionCommand().equals(ACT_VERIFICATION)){
			if(null == m_reader){
				MessageBox.Warning("Reader is not selected");
			}
			else{
				Verification.Run(m_reader);
			}
		}
		else if(e.getActionCommand().equals(ACT_IDENTIFICATION)){
			if(null == m_reader){
				MessageBox.Warning("Reader is not selected");
			}
			else{
				Identification.Run(m_reader);
			}
		}
		else if(e.getActionCommand().equals(ACT_ENROLLMENT)){
			if(null == m_reader){
				MessageBox.Warning("Reader is not selected");
			}
			else{
				Enrollment.Run(m_reader);
			}
		}
		else if(e.getActionCommand().equals(ACT_EXIT)){
			m_dlgParent.setVisible(false);
		}
	}
	
	private void doModal(JDialog dlgParent){
		m_dlgParent = dlgParent;
		m_dlgParent.setContentPane(this);
		m_dlgParent.pack();
		m_dlgParent.setLocationRelativeTo(null);
		m_dlgParent.setVisible(true);
		m_dlgParent.dispose();
	}

	private static void createAndShowGUI() {
		Inicio paneContent = new Inicio();
		
		//initialize capture library by acquiring reader collection
		try{
			paneContent.m_collection = UareUGlobal.GetReaderCollection();
		}
		catch(UareUException e) {
			MessageBox.DpError("UareUGlobal.getReaderCollection()", e);
			return;
		}

		//run dialog
		JDialog dlg = new JDialog((JDialog)null, "Control de ingreso escolar por huella dactilar", true);
		paneContent.doModal(dlg);
		
		//release capture library by destroying reader collection
		try{
			UareUGlobal.DestroyReaderCollection();
		}
		catch(UareUException e) {
			MessageBox.DpError("UareUGlobal.destroyReaderCollection()", e);
		}
    }


	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}
