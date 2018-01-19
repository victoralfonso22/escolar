package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import conexion.Conexion;
import clases.Grados;
import clases.Huellas;

public class HuellasDAO {
	
	public List<Huellas> retornarTodasHuellas() throws SQLException{
		
		Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		  ResultSet result=null;
		
		int i = 0;
        List<Huellas> listaHuellas = new ArrayList<Huellas>();
        
        String sqlStmt = "SELECT * from huellas;";
        
        try {
        	connection=miConexion.getConnection();
 		   if (connection!=null) {
 		    statement=connection.prepareStatement(sqlStmt);    
 		   System.out.println("Executing Query...");
 	        System.out.println(sqlStmt);

 	        
 	        ResultSet rs = statement.executeQuery(sqlStmt);
 	        while (rs.next()) {
 	            if (rs.getBytes("izq_menique") != null || rs.getBytes("izq_anular") != null || rs.getBytes("izq_medio") != null || rs.getBytes("izq_indice") != null 
 	            		|| rs.getBytes("izq_pulgar") != null || rs.getBytes("der_menique") != null || rs.getBytes("der_anular") != null || rs.getBytes("der_medio") != null 
 	            		|| rs.getBytes("der_indice") != null || rs.getBytes("der_pulgar") != null) {
 	            	listaHuellas.add(new Huellas(rs.getInt("id"),rs.getInt("id_usuario"), rs.getBytes("izq_menique"), rs.getBytes("izq_anular"),rs.getBytes("izq_medio"),
 	            			rs.getBytes("izq_indice"),rs.getBytes("izq_pulgar"),rs.getBytes("der_menique"),rs.getBytes("der_anular"),rs.getBytes("der_medio"),
 	            			rs.getBytes("der_indice"),rs.getBytes("der_pulgar"),rs.getInt("tipo_usuario")));

 	                System.out.println("Obteniendo huellas..." + i++);
 	            }
 	        }  
 		   }
 		  } catch (SQLException e) {
 			  JOptionPane.showMessageDialog(null, "Error en la consulta de retorno de huellas: "+e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);
 		  }finally{
 		   try {
 		    connection.close();
 		    miConexion.desconectar();
 		   } catch (SQLException e) {
 		    // TODO Auto-generated catch block
 		    e.printStackTrace();
 		   }
 		  }
 		  return listaHuellas;
     
		
	}

}
