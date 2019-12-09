package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import conexion.Conexion;
import clases.Alumno;
import clases.Grados;

public class AlumnoDAO {
	
	public boolean guardarAlumno(String nombre_completo, String curp, String fecha_nacimiento, String edad, int id_grado, String nombre_tutor, String ocupacion_tutor, 
			String calle_tutor, String numero_calle_tutor, String colonia_tutor, String cp_tutor, String tel_tutor,String tipo_sangre, int estatus,JDialog comp) {
		   
		  Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		   
		  connection=miConexion.getConnection();
		   
		  String consulta="insert into alumnos "
		  		+ "(nombre_completo, curp, fecha_nacimiento, edad, id_grado, nombre_tutor, ocupacion_tutor, calle_tutor,numero_calle_tutor,colonia_tutor, cp_tutor,tel_tutor,tipo_sangre,estatus) "
		  		+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);  
		    statement.setString(1, nombre_completo);
		    statement.setString(2, curp);
		    statement.setString(3, fecha_nacimiento);
		    statement.setString(4, edad);
		    statement.setInt(5, id_grado);
		    statement.setString(6, nombre_tutor);
		    statement.setString(7, ocupacion_tutor);
		    statement.setString(8, calle_tutor);
		    statement.setString(9, numero_calle_tutor);
		    statement.setString(10, colonia_tutor);
		    statement.setString(11, cp_tutor);
		    statement.setString(12, tel_tutor);
		    statement.setString(13, tipo_sangre);
		    statement.setInt(14, estatus);
		    statement.executeUpdate();
		    
		    return true;

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
		  return false;
		 }
	
	
	public ArrayList<Alumno> retornaAlumno(int id_grado,JDialog comp) {
		   
		
		ArrayList<Alumno> alumnosList=new ArrayList<Alumno>();
			
		  Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		  ResultSet result=null;
		  
		  connection=miConexion.getConnection();
		  
		  Alumno alumno;
		   
		  String consulta="select * from alumnos where id_grado = ? and estatus = 1;";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);  
		    
		    statement.setInt(1, id_grado);
		    result=statement.executeQuery();		    
		    
		    while(result.next()==true){
			     alumno=new Alumno(result.getInt("id"),result.getString("nombre_completo"),result.getString("apellido_p"),result.getString("apellido_m"),result.getString("curp"),result.getString("fecha_nacimiento"),result.getString("edad"),id_grado,
			    		 result.getString("nombre_tutor"),result.getString("ocupacion_tutor"),result.getString("calle_tutor"),result.getInt("numero_calle_tutor"),result.getString("colonia_tutor"),
			    		 result.getString("cp_tutor"),result.getString("tel_tutor"),1);	     
			           
			     alumnosList.add(alumno);
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
		  return alumnosList;
		 }
		 
}