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
import clases.HuellasComparar;

public class HuellasDAO {

	public List<HuellasComparar> retornarTodasHuellas() throws SQLException {

		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		int i = 0;
		List<HuellasComparar> listaHuellas = new ArrayList<HuellasComparar>();

		String sqlStmt = "SELECT r.*, a.nombre_completo FROM retornaHuellas r\r\n" + 
				"join alumnos a on a.id = r.id_usuario\r\n" + 
				"where r.tipo_usuario = 1\r\n" + 
				"\r\n" + 
				"union\r\n" + 
				"SELECT r.*, p.nombre_completo FROM retornaHuellas r\r\n" + 
				"JOIN personal p on p.id = r.id_usuario\r\n" + 
				"where r.tipo_usuario = 2";

		try {
			connection = miConexion.getConnection();
			if (connection != null) {
				statement = connection.prepareStatement(sqlStmt);
				System.out.println("Executing Query...");
				System.out.println(sqlStmt);

				ResultSet rs = statement.executeQuery(sqlStmt);
				while (rs.next()) {
					listaHuellas.add(new HuellasComparar(rs.getInt("id"), rs.getInt("id_usuario"),rs.getBytes("huella"),rs.getInt("tipo_usuario"),rs.getString("nombre_completo")));
					
					System.out.println("Obteniendo huellas..." + i++);
				}

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la consulta de retorno de huellas: " + e.getMessage(),
					"Aviso", JOptionPane.ERROR_MESSAGE);
		} finally {
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
