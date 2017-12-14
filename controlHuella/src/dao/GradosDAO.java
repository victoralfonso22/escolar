package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		   
		  String consulta="SELECT id,grado,id_escuela FROM grados where id_escuela = 2";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);    
		    result=statement.executeQuery();
		     
		    while(result.next()==true){
		     grados=new Grados();
		     grados.setId(result.getInt("id"));
		     grados.setGrado(result.getString("grado"));
		     grados.setId_escuela(result.getInt("id_escuela"));		     
		           
		     estudiantesList.add(grados);
		    }  
		   }
		  } catch (SQLException e) {
		   System.out.println("Error en la consulta de grados: "+e.getMessage());
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