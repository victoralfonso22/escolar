package conexion;

import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utilerias.*;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import clases.Grados;
import classes.RegistroAlumno;
public class Metodos {
   /*private final String tabla = "tareas";
   public void guardar(Connection conexion, Tarea tarea) throws SQLException{
      try{
         PreparedStatement consulta;
         if(tarea.getId_tarea() == null){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(titulo, descripcion, nivel_de_prioridad) VALUES(?, ?, ?)");
            consulta.setString(1, tarea.getTitulo());
            consulta.setString(2, tarea.getDescripcion());
            consulta.setInt(3, tarea.getNivel_de_prioridad());
         }else{
            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET titulo = ?, descripcion = ?, nivel_de_prioridad = ? WHERE id_tarea = ?");
            consulta.setString(1, tarea.getTitulo());
            consulta.setString(2, tarea.getDescripcion());
            consulta.setInt(3, tarea.getNivel_de_prioridad());
            consulta.setInt(4, tarea.getId_tarea());
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   public Tarea recuperarPorId(Connection conexion, int id_tarea) throws SQLException {
      Tarea tarea = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT titulo, descripcion, nivel_de_prioridad FROM " + this.tabla + " WHERE id_tarea = ?" );
         consulta.setInt(1, id_tarea);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            tarea = new Tarea(id_tarea, resultado.getString("titulo"), resultado.getString("descripcion"), resultado.getInt("nivel_de_prioridad"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return tarea;
   }
   public void eliminar(Connection conexion, Tarea tarea) throws SQLException{
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_tarea = ?");
         consulta.setInt(1, tarea.getId_tarea());
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   public List<Tarea> recuperarTodas(Connection conexion) throws SQLException{
      List<Tarea> tareas = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT id_tarea, titulo, descripcion, nivel_de_prioridad FROM " + this.tabla + " ORDER BY nivel_de_prioridad");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            tareas.add(new Tarea(resultado.getInt("id_tarea"), resultado.getString("titulo"), resultado.getString("descripcion"), resultado.getInt("nivel_de_prioridad")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return tareas;
   }
	
public void llenaCombo(Connection conexion, int id_escuela) throws SQLException{
	
	try{
		
		PreparedStatement consulta = conexion.prepareStatement("SELECT id,grado FROM grados WHERE id_escuela = ?" );
		consulta.setInt(1, id_escuela);
		ResultSet resultado = consulta.executeQuery();
        while(resultado.next()){
        	ObjetoCombo i = new ObjetoCombo(resultado.getInt(1), resultado.getString(2)); 
        	combo.addItem(i);
        }
        //return combo;
	}catch (SQLException e){
		throw new SQLException(e);
	}
} 
	*/
	
	public boolean guardarFDM(ByteArrayInputStream bArr, int longi, Component comp,int tipo_dedo, int id_usuario, int tipo_usuario) {
		   
		boolean guardo = false;
		   
		  Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		  ResultSet result=null;
		   
		  Grados grados;
		   
		  connection=miConexion.getConnection();
		   
		  //String consulta="insert into users_in_fmd (FMD) values (?);";
		  String consulta="call insertarHuella(?,?,?,?);";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);
		    statement.setInt(1, id_usuario);
		    statement.setInt(2, tipo_usuario);
		    statement.setInt(3, tipo_dedo);
		    statement.setBinaryStream(4, bArr,longi);
		    statement.executeUpdate();		    
		    guardo = true;
		   }
		  } catch (SQLException e) {
			  JOptionPane.showMessageDialog(comp, "Error en la consulta: "+e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);			  
		  }finally{
		   try {
		    connection.close();
		    miConexion.desconectar();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		  return guardo;
		 }
	
	public byte[] regresarFDM(Component comp) {
		   

		   
		  Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		  ResultSet result=null;
		   
		  Grados grados;
		   
		  connection=miConexion.getConnection();
		   
		  String consulta="select FMD from users_in_fmd where UsersInFmd = 1;";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);		    
		    result = statement.executeQuery();
		    if(result.next()){
		    return result.getBytes("FMD");
		    }
		   }
		  } catch (SQLException e) {
			  JOptionPane.showMessageDialog(comp, "Error en la consulta de grados: "+e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);			  
		  }finally{
		   try {
		    connection.close();
		    miConexion.desconectar();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		  return null;
		 }
	
	
	public boolean guardarAsistencia(int id_usuario, int tipo_usuario,Component comp){
		boolean guardo = false;
		   
		  Connection connection=null;
		  Conexion miConexion=new Conexion();
		  PreparedStatement statement=null;
		  ResultSet result=null;
		   
		  connection=miConexion.getConnection();
		   
		  //String consulta="insert into users_in_fmd (FMD) values (?);";
		  String consulta="insert into asistencia (id_usuario, tipo_usuario) values (?,?);";
		   
		  try {
		   if (connection!=null) {
		    statement=connection.prepareStatement(consulta);
		    statement.setInt(1, id_usuario);
		    statement.setInt(2, tipo_usuario);		    
		    statement.executeUpdate();		    
		    guardo = true;
		   }
		  } catch (SQLException e) {
			  JOptionPane.showMessageDialog(comp, "Error en la consulta: "+e.getMessage(), "Aviso", JOptionPane.ERROR_MESSAGE);			  
		  }finally{
		   try {
		    connection.close();
		    miConexion.desconectar();
		   } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		  return guardo;
	}
	
}