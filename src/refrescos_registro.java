import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BaseDatos.Conexion;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class refrescos_registro extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel modelorefresco;
	private JLabel lblREGISTRO;
	private JButton btnBorrar;
	private JButton btnVolver;
	private String [] datos = new String [4];

	private void cargarBebidasDesdeBD() {

	 try {
	        Connection con = Conexion.getConexion();
	        Statement stmt = con.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM refrescos");

	        modelorefresco.setRowCount(0); 

	        while (rs.next()) {
	            modelorefresco.addRow(new Object[]{
	            
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
	
	public refrescos_registro(DefaultTableModel modelo) {
		
		this.modelorefresco = modelo;
		
		setLayout(null); 
		
		lblREGISTRO = new JLabel("REGISTRO REFRESCOS");
		lblREGISTRO.setFont(new Font("Rockwell Condensed", Font.BOLD, 19));
		lblREGISTRO.setBounds(185, 10, 250, 28);
		add(lblREGISTRO);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(25, 52, 471, 235);
		add(scrollPane); 
		
		table = new JTable();
		table.setModel(modelo); 
		scrollPane.setViewportView(table);
		
		btnBorrar = new JButton("");
		btnBorrar.addActionListener(this);
		btnBorrar.setIcon(new ImageIcon(refrescos_registro.class.getResource("/imagenes/1-trash-cat_icon-icons.com_76677.png")));
		btnBorrar.setBounds(63, 308, 55, 41);
		add(btnBorrar);
		
		btnVolver = new JButton("");
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(refrescos_registro.class.getResource("/imagenes/19-add-cat_icon-icons.com_76695.png")));
		btnVolver.setBounds(380, 308, 55, 41);
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
		            "DELETE FROM refrescos WHERE id_refrescos = ?"
		        );

		        ps.setInt(1, id);
		        ps.executeUpdate();

		        modelorefresco.removeRow(fila); 

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
		
	
	

