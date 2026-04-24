import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BaseDatos.Conexion;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class licores_registro extends JPanel implements ActionListener{

	
	private void cargarBebidasDesdeBD() {

	    try {
	        Connection con = Conexion.getConexion();
	        Statement stmt = con.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM licores");

	        modelolicor.setRowCount(0); 

	        while (rs.next()) {
	            modelolicor.addRow(new Object[]{
	            
	            			    rs.getInt("id_licor"),
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

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel modelolicor;
	private JLabel lblREGISTRO;
	private String [] datos = new String [4];
	private JButton btnBorrar;
	private JButton btnVolver;

	public licores_registro(DefaultTableModel modelo) {
		setLayout(null);
		
		 this.modelolicor = modelo;
		    
		lblREGISTRO = new JLabel("REGISTRO LICORES");
		lblREGISTRO.setBounds(185, 10, 250, 28);
		lblREGISTRO.setFont(new Font("Rockwell Condensed", Font.BOLD, 19));
		add(lblREGISTRO);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 471, 235);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane); 
		
		table = new JTable();
		table.setModel(modelo); 
		scrollPane.setViewportView(table);
		
		
		scrollPane.setViewportView(table);
		
		btnBorrar = new JButton("");
		btnBorrar.addActionListener(this);
		btnBorrar.setIcon(new ImageIcon(licores_registro.class.getResource("/imagenes/1-trash-cat_icon-icons.com_76677.png")));
		btnBorrar.setBounds(38, 324, 56, 33);
		add(btnBorrar);
		
		btnVolver = new JButton("");
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(licores_registro.class.getResource("/imagenes/19-add-cat_icon-icons.com_76695.png")));
		btnVolver.setBounds(411, 324, 49, 33);
		add(btnVolver);
		
		cargarBebidasDesdeBD();

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob.equals(btnBorrar)) {
			
			int fila = table.getSelectedRow();

		    if(fila == -1){
		        JOptionPane.showMessageDialog(this, "Selecciona una fila");
		        return;
		    }

		    int id = (int) table.getValueAt(fila, 0); 

		    try {
		        Connection con = Conexion.getConexion();

		        PreparedStatement ps = con.prepareStatement(
		            "DELETE FROM licores WHERE id_licor = ?"
		        );

		        ps.setInt(1, id);
		        ps.executeUpdate();

		        modelolicor.removeRow(fila); 

		        con.close();

		        JOptionPane.showMessageDialog(this, "Eliminado correctamente");

		    } catch (Exception ex) {
		        ex.printStackTrace();
		    	}
			}
		if (ob.equals(btnVolver)) {
		    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		    frame.setContentPane(new usuario("cargo"));
		    frame.revalidate();
		    frame.repaint();
					}			
			 }
	}
	
	




