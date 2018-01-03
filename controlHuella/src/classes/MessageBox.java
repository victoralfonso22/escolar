package classes;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.UareUException;


public class MessageBox {
	public static void BadQuality(Reader.CaptureQuality q){
		JOptionPane.showMessageDialog(null, q.toString(), "Mala calidad", JOptionPane.WARNING_MESSAGE);
	}
	
	public static void BadStatus(Reader.Status s){
		String str = String.format("Reader status: %s", s.toString());
		JOptionPane.showMessageDialog(null, str, "Reader status", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void DpError(String strFunctionName, UareUException e){
		UIManager UI=new UIManager();
		 UI.put("OptionPane.background", Color.white);
		 UI.put("Panel.background", Color.white);
		String str = String.format("%s returned DP error %d \n%s", strFunctionName, (e.getCode() & 0xffff), e.toString());
		JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void Warning(String strText){
		UIManager UI=new UIManager();
		 UI.put("OptionPane.background", Color.white);
		 UI.put("Panel.background", Color.white);
		JOptionPane.showMessageDialog(null, strText, "Alerta", JOptionPane.WARNING_MESSAGE);
	}
}
