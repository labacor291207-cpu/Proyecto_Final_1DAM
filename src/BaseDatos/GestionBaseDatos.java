package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;


import BaseDatos.Conexion;

	
	public class GestionBaseDatos {
	public DefaultTableModel modeloProducto;
	private DefaultTableModel modeloSolicitud;

	
		
	public DefaultTableModel buscarProductos(String nombre, String categoria) {

	    DefaultTableModel modeloProducto = new DefaultTableModel();
	   
	    modeloProducto.addColumn("ID"); 
	    modeloProducto.addColumn("Nombre");
	    modeloProducto.addColumn("Stock");
	    modeloProducto.addColumn("Caducidad");

	    try {
	        Connection con = Conexion.getConexion();

	        String sql;

	        if (categoria == null || categoria.equals("Seleccionar:") || categoria.isEmpty()) {

	            sql = "SELECT p.id_producto, p.nombre, p.stock, p.fecha_caducidad " +
	                  "FROM producto p " +
	                  "WHERE p.nombre LIKE ?";

	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, "%" + nombre + "%");

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Object[] fila = {
	                	rs.getInt("id_producto"),
	                    rs.getString("nombre"),
	                    rs.getInt("stock"),
	                    rs.getDate("fecha_caducidad")
	                };
	                modeloProducto.addRow(fila);
	            }

	        } else {

	            sql = "SELECT p.id_producto, p.nombre, p.stock, p.fecha_caducidad " +
	                  "FROM producto p " +
	                  "INNER JOIN categoria c " +
	                  "ON p.id_categoria_aux = c.id_categoria " +
	                  "WHERE p.nombre LIKE ? " +
	                  "AND c.nombre_categoria = ?";

	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, "%" + nombre + "%");
	            ps.setString(2, categoria);

	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Object[] fila = {
	                    rs.getInt("id_producto"),        
	                    rs.getString("nombre"),         
	                    rs.getInt("stock"),             
	                    rs.getDate("fecha_caducidad")   
	                };
	                modeloProducto.addRow(fila);
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error: " + e);
	    }
	    

	    return modeloProducto;
	}
	
	public void guardarProducto(String nombre,int stock,String fecha, int categoria) {

		try {

			Connection con = Conexion.getConexion();	

			String sql = "INSERT INTO producto(nombre, stock, fecha_caducidad, id_categoria_aux) VALUES(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, nombre);
			ps.setInt(2, stock);
			ps.setString(3, fecha);
			ps.setInt(4, categoria);
			ps.executeUpdate();

			} catch(Exception e) {
				System.out.println(e);
			}
	}
	
	
	public DefaultTableModel buscarSolicitud(String fecha) {

	    DefaultTableModel modeloSolicitud = new DefaultTableModel();

	    modeloSolicitud.addColumn("N°Solicitud");
	    modeloSolicitud.addColumn("Fecha");
	    modeloSolicitud.addColumn("Estado");

	    try {

	        Connection con = Conexion.getConexion();

	        String sql;

	        if (fecha == null || fecha.equals("Seleccionar:") || fecha.isEmpty()) {

	          
	            sql = "SELECT id_solicitud_compra, fecha, estado " +
	                  "FROM solicitud_compra";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Object[] fila = {
	                    rs.getInt("id_solicitud_compra"),
	                    rs.getDate("fecha"),
	                    rs.getString("estado")
	                };
	                modeloSolicitud.addRow(fila);
	            }

	        } else {

	           
	            sql = "SELECT id_solicitud_compra, fecha, estado " +
	                  "FROM solicitud_compra " +
	                  "WHERE fecha = ?";

	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setDate(1, java.sql.Date.valueOf(fecha)); 

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Object[] fila = {
	                    rs.getInt("id_solicitud_compra"),
	                    rs.getDate("fecha"),
	                    rs.getString("estado")
	                };
	                modeloSolicitud.addRow(fila);
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error: " + e);
	    }

	    return modeloSolicitud;
	}
	
	
		public int guardarSolicitud( String fecha,String estado) {

			int idGenerado = 0;

			try {

				 Connection con = Conexion.getConexion();

					String sql = "INSERT INTO solicitud_compra( fecha, estado) VALUES(?,?)";

						PreparedStatement ps = con.prepareStatement(
								sql,
						Statement.RETURN_GENERATED_KEYS
								);
							
							ps.setString(1, fecha);
							ps.setString(2, estado);
							ps.executeUpdate();

							ResultSet rs = ps.getGeneratedKeys();

							if(rs.next()) {
							idGenerado = rs.getInt(1);
							
							}

							} catch(Exception e) {
								System.out.println(e);
							}

				return idGenerado;
			}
		
		public void guardarDetalle(int idSolicitud, String producto, int cantidad, String tipo) {
		    try {
		        Connection con = Conexion.getConexion();
		        String sql = "INSERT INTO contiene(id_solicitud_compra_aux, nombre_producto, cantidad, tipo) " +
		                     "VALUES(?, ?, ?, ?)";
		        
		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setInt(1, idSolicitud);
		        ps.setString(2, producto);
		        ps.setInt(3, cantidad);
		        ps.setString(4, tipo);
		        ps.executeUpdate();
		    } catch (Exception e) {
		        System.out.println("Error: " + e);
		    }
		}
			
			public void editarProducto(int id, String nuevoNombre, int stock, String fecha, int idCategoria) throws Exception {
					
				Connection con = Conexion.getConexion();
				
				String sql = "UPDATE producto SET nombre=?, stock=?, fecha_caducidad=?, " +
						"id_categoria_aux=? WHERE id_producto=?";
				
				PreparedStatement ps = con.prepareStatement(sql);
					
					ps.setString(1, nuevoNombre);
					ps.setInt(2, stock);
					ps.setString(3, fecha);
					ps.setInt(4, idCategoria);
					ps.setInt(5, id);
					ps.executeUpdate();
					ps.close();
					con.close();
			}

			public DefaultTableModel buscarDetalleSolicitud(int idSolicitud) {
			    DefaultTableModel modelo = new DefaultTableModel();
			    modelo.addColumn("Nombre Producto");
			    modelo.addColumn("Cantidad");
			    modelo.addColumn("Tipo");

			    try {
			        Connection con = Conexion.getConexion();
			        String sql = "SELECT nombre_producto, cantidad, tipo " +
			                     "FROM contiene " +
			                     "WHERE id_solicitud_compra_aux = ?";
			        PreparedStatement ps = con.prepareStatement(sql);
			        ps.setInt(1, idSolicitud);
			        ResultSet rs = ps.executeQuery();

			        while (rs.next()) {
			            Object[] fila = {
			                rs.getString("nombre_producto"),
			                rs.getInt("cantidad"),
			                rs.getString("tipo")
			            };
			            modelo.addRow(fila);
			        }
			    } catch (Exception e) {
			        System.out.println("Error: " + e);
			    }
			    return modelo;
			}
			
			
			public void eliminarProducto(int id) throws Exception {
				
				Connection con = Conexion.getConexion();
				
				String sql = "DELETE FROM producto WHERE id_producto=?";
				
				PreparedStatement ps = con.prepareStatement(sql);

				ps.setInt(1, id);
				ps.executeUpdate();
				ps.close();
				con.close();
			}
			
			public void eliminarSolicitud(int id) {

			    try {

			        Connection con = Conexion.getConexion();

			        String sql = "DELETE FROM solicitud_compra WHERE id_solicitud_compra = ?";

			        PreparedStatement ps = con.prepareStatement(sql);

			        ps.setInt(1, id);

			        ps.executeUpdate();

			    } catch (Exception e) {

			        System.out.println("Error: " + e);
			    	}
				}
			public boolean existeProducto(String nombre) {
		        boolean existe = false;
		        try {
		            Connection con = Conexion.getConexion();
		            
		            String sql = "SELECT nombre FROM producto WHERE nombre = ?";
		            
		            PreparedStatement ps = con.prepareStatement(sql);
		            
		            ps.setString(1, nombre.trim());
		            
		            ResultSet rs = ps.executeQuery();
		            
		            if (rs.next()) {
		                existe = true;
		            }
		            
		            rs.close();
		            ps.close();
		        } catch (Exception e) {
		            System.out.println("Error al verificar duplicado: ");
		            e.printStackTrace();
		        }
		        return existe;
		    }
			}
	
	
	
	
	
	
	
	
	
