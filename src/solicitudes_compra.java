import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
import java.awt.event.ActionEvent;

public class solicitudes_compra extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel lblSolicitudes;
	private JScrollPane scrollPane;
	private String [] datos = new String [8];
	private DefaultTableModel modelo;
	private JButton btnSalir;
	private JButton btnVolver;
	

	/**
	 * Create the panel.
	 */
	private void solicitudesBD() {

	    try {
	        Connection con = Conexion.getConexion();
	        Statement stmt = con.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT * FROM solicitudes_compra");

	        modelo.setRowCount(0); 

	        while (rs.next()) {
	            modelo.addRow(new Object[]{
	            
	            			    rs.getInt("id_solicitud"),
	            			    rs.getString("producto"),
	            			    rs.getString("cantidad"),
	            			    rs.getString("tipo"),
	            			    rs.getString("fecha_solicitud"),
	            			    rs.getString("proveedor"),
	            			    rs.getString("estado"),
	            			    rs.getString("monto_total")

	            			});
	        	}

	        rs.close();
	        stmt.close();
	        con.close();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	public solicitudes_compra(DefaultTableModel modelo) {
		setLayout(null);
		
		this.modelo = modelo;
		
		lblSolicitudes = new JLabel("SOLICITUDES DE COMPRA");
		lblSolicitudes.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 24));
		lblSolicitudes.setBounds(170, 41, 268, 26);
		add(lblSolicitudes);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 78, 617, 406);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane); 
		
		table = new JTable();
		table.setModel(modelo); 
		scrollPane.setViewportView(table);
		
		
		scrollPane.setViewportView(table);
		
		btnVolver = new JButton("");
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(solicitudes_compra.class.getResource("/imagenes/19-add-cat_icon-icons.com_76695.png")));
		btnVolver.setBounds(50, 519, 65, 41);
		add(btnVolver);
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(this);
		btnSalir.setIcon(new ImageIcon(solicitudes_compra.class.getResource("/imagenes/4115235-exit-logout-sign-out_114030.png")));
		btnSalir.setBounds(574, 519, 58, 41);
		add(btnSalir);
		
		solicitudesBD();
	}
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		 if (ob.equals(btnSalir)) {
		        System.exit(0);
		    }
		 if (ob.equals(btnVolver)) {
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.setContentPane(new gerente("cargo"));
			    frame.revalidate();
			    frame.repaint();
						}
		 
		 
	}
	
	
	
	
	
}
