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
        
        String sqlStmt = "SELECT * from users_in_fmd;";
        
        try {
        	connection=miConexion.getConnection();
 		   if (connection!=null) {
 		    statement=connection.prepareStatement(sqlStmt);    
 		   System.out.println("Executing Query...");
 	        System.out.println(sqlStmt);

 	        
 	        ResultSet rs = statement.executeQuery(sqlStmt);
 	        while (rs.next()) {
 	            if (rs.getBytes("FMD") != null) {
 	            	listaHuellas.add(new Huellas(rs.getInt("UsersInFmd"),rs.getInt("SucursalId"), rs.getInt("UserId"), rs.getBytes("FMD"),rs.getInt("FingerIndex"),rs.getString("nombre")));

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
