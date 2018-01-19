package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexion.Conexion;
import clases.Grados;

public class GradosDAO {
	
	public ArrayList<Grados> obtenerGrados() {
		   
		  ArrayList<Grados> estudiantesList=new ArrayList<Grados>();
		   
		  Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		  ResultSet result=null;
		   
		  Grados grados;
		   
		  connection=miConexion.getConnection();
		   
		  String consulta="SELECT id,grado FROM grados;";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);    
		    result=statement.executeQuery();
		     
		    while(result.next()==true){
		     grados=new Grados(result.getInt("id"),result.getString("grado"));	     
		           
		     estudiantesList.add(grados);
		    }  
		   }
		  } catch (SQLException e) {
			  JOptionPane.showMessageDialog(null, "Error en la consulta de grados: "+e.getMessage(), "Aviso", JOptionPane.PLAIN_MESSAGE);
		  }finally{
		   try {
		    connection.close();
		    miConexion.desconectar();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		  return estudiantesList;
		 }
		 
}