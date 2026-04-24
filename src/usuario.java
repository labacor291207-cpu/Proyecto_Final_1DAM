import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class usuario extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JLabel lblBienvenido;
	private JButton btnRCaducadas;
	private JButton btnLCaducadas;
	private JButton btnInventario;
	private JButton btnTodosB;
	private JButton btnTodosL; 
	private JButton btnSalir;
	private JButton btnVolver;
	private DefaultTableModel modelorefresco;
	private DefaultTableModel modelolicor;
	private String cargoUsuario;
	

	/**
	 * Create the panel.
	 */
	 public usuario (String cargo) { 
	        this.cargoUsuario = cargo;
	        setLayout(null);
		
		lblBienvenido = new JLabel("BIENVENIDO USUARIO");
		lblBienvenido.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 28));
		lblBienvenido.setBounds(133, 22, 272, 34);
		add(lblBienvenido);
		
		btnRCaducadas = new JButton("REFRESCOS CADUCADOS");
		btnRCaducadas.addActionListener(this);
		btnRCaducadas.setFont(new Font("Rockwell Nova Cond", Font.PLAIN, 14));
		btnRCaducadas.setBounds(190, 66, 148, 29);
		add(btnRCaducadas);
		
		btnLCaducadas = new JButton("LICORES CADUCADOS");
		btnLCaducadas.addActionListener(this);
		btnLCaducadas.setFont(new Font("Rockwell Nova Cond", Font.PLAIN, 14));
		btnLCaducadas.setBounds(190, 105, 150, 28);
		add(btnLCaducadas);
		
		btnInventario = new JButton("INGRESAR INVENTARIO");
		btnInventario.addActionListener(this);
		btnInventario.setFont(new Font("Rockwell Nova Cond", Font.PLAIN, 14));
		btnInventario.setBounds(190, 150, 151, 29);
		add(btnInventario);
		
		btnTodosB = new JButton("VER REFRESCOS");
		btnTodosB.addActionListener(this);
		btnTodosB.setFont(new Font("Rockwell Nova Cond", Font.PLAIN, 14));
		btnTodosB.setBounds(190, 192, 150, 28);
		add(btnTodosB);
		
		btnTodosL = new JButton("VER LICORES");
		btnTodosL.addActionListener(this);
		btnTodosL.setFont(new Font("Rockwell Nova Cond", Font.PLAIN, 14));
		btnTodosL.setBounds(190, 231, 149, 28);
		add(btnTodosL);
		
		table = new JTable();
		table.setBackground(new Color(255, 129, 91));
		table.setForeground(new Color(255, 129, 91));
		table.setBounds(148, 61, 230, 81);
		add(table);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(213, 100, 225));
		table_1.setBounds(148, 184, 230, 81);
		add(table_1);
		
		table_2 = new JTable();
		table_2.setBackground(new Color(107, 233, 101));
		table_2.setBounds(148, 136, 230, 50);
		add(table_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 277, 487, 19);
		add(separator);
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(this);
		btnSalir.setIcon(new ImageIcon(usuario.class.getResource("/imagenes/4115235-exit-logout-sign-out_114030.png")));
		btnSalir.setBounds(414, 297, 65, 34);
		add(btnSalir);
		
		btnVolver = new JButton("");
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(usuario.class.getResource("/imagenes/19-add-cat_icon-icons.com_76695.png")));
		btnVolver.setBounds(32, 297, 65, 34);
		add(btnVolver);
		
		modelorefresco = new DefaultTableModel();
		modelorefresco.addColumn("ID");
		modelorefresco.addColumn("NOMBRE");
		modelorefresco.addColumn("STOCK");
		modelorefresco.addColumn("F.CADUCIDAD");
		
		modelolicor = new DefaultTableModel();
		modelolicor.addColumn("ID");
		modelolicor.addColumn("NOMBRE");
		modelolicor.addColumn("STOCK");
		modelolicor.addColumn("F.CADUCIDAD");


	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		Object ob = e.getSource();
		 if (ob.equals(btnSalir)) {
		        System.exit(0);
		    }
		 if(ob.equals(btnVolver)) {
			    Logeo ventana = new Logeo();
			    ventana.setVisible(true);

			    
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.dispose();
					}
		 if (ob.equals(btnTodosL)) {
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.setContentPane(new licores_registro(modelolicor));
			    frame.revalidate();
			    frame.repaint();
			}
		 if (ob.equals(btnTodosB)) {
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.setContentPane(new refrescos_registro(modelorefresco));
			    frame.revalidate();
			    frame.repaint();
			}
		 if(ob.equals(btnInventario)) {
			    registro_inventario ventana = new registro_inventario("usuario");
			    ventana.setVisible(true);

			    
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.dispose();
					}

		 
		 
		 
	}
}
