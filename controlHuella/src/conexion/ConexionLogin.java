package conexion;

import java.sql.*;

public class ConexionLogin {
	 private String nombreBd="sirensco_login";
	 private String usuario="sirensco_sirens";
	 private String password="Proyecto2018";
	 private String url="jdbc:mysql://198.49.75.66/"+nombreBd;
	 
	 Connection conn=null;
	 //constructor de la clase
	 public ConexionLogin(){
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