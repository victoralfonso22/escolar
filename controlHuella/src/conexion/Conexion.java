package conexion;

import java.sql.*;

public class Conexion {
	 private String nombreBd="fundaci1_prueba";
	 private String usuario="fundaci1_pruebas";
	 private String password="Pruebas123";
	 private String url="jdbc:mysql://50.28.15.189/"+nombreBd;
	 
	 Connection conn=null;
	 //constructor de la clase
	 public Conexion(){
	  try {
	   //obtener el driver
	   Class.forName("com.mysql.jdbc.Driver");
	   //obtener la conexion
	   conn=DriverManager.getConnection(url,usuario,password);
	   if (conn!=null) {
	   }
	  } catch (ClassNotFoundException e) {
	   System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
	  } catch (SQLException e) {
	   System.out.println("ocurre una SQLException: "+e.getMessage());
	  }
	 }
	 public Connection getConnection(){
	  return conn;
	 }
	 public void desconectar(){
	  try {
	   conn.close();
	   conn=null;
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	   
	 }
	}