package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import clases.Personal;
import conexion.Conexion;

public class PersonalDAO {
	
	public ArrayList<Personal> retornaPersonal(JDialog comp) {
		   
		
		ArrayList<Personal> personalList=new ArrayList<Personal>();
			
		  Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		  ResultSet result=null;
		  
		  connection=miConexion.getConnection();
		  
		  Personal personal;
		   
		  String consulta="select * from personal where estatus = 1;";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);  
		    		    
		    result=statement.executeQuery();		    
		    
		    while(result.next()==true){
			     personal=new Personal(result.getInt("id"),result.getString("nombre_completo"),result.getString("curp"),result.getString("fecha_nacimiento"),result.getString("edad"));	     
			           
			     personalList.add(personal);
			    }
		    
		   }
		  } catch (SQLException e) {
			  JOptionPane.showMessageDialog(comp, "Error en la consulta: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		  }finally{
		   try {
		    connection.close();
		    miConexion.desconectar();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		  return personalList;
		 }
		 
}
