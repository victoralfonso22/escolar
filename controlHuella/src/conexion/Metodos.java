package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilerias.*;
import javax.swing.JComboBox;

import classes.registro;
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
}