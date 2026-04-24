package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import BaseDatos.Conexion;

	public class GestionBaseDatos {

	 
	 

		public void cargarBebidasDesdeBD(DefaultTableModel modelo) {

		    try {
		        Connection con = Conexion.getConexion();
		        Statement stmt = con.createStatement();

		        ResultSet rs = stmt.executeQuery("SELECT * FROM refrescos");

		        modelo.setRowCount(0); // limpiar tabla

		        while (rs.next()) {
		            modelo.addRow(new Object[]{
		                rs.getInt("id_refrescos"),
		                rs.getString("nombre"),
		                rs.getString("stock"),
		                rs.getString("fecha_caducidad")
		            });
		        }

		        rs.close();
		        stmt.close();
		        con.close();

		    } catch (Exception ex) {
		        ex.printStackTrace();
		    		
		    	}
			}
	 }


