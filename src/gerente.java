import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class gerente extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel lblBienvenido;
	private JButton btniNVENTARIO;
	private JButton btnSOLICITUD;
	private JButton btnVolver;
	private JButton btnSalir;
	private JButton btnVerS;
	private DefaultTableModel modelo;
	private String cargoUsuario;
	
	

	/**
	 * Create the panel.
	 */
	 public gerente(String cargo) { 
	        this.cargoUsuario = cargo;
		setLayout(null);
		
		lblBienvenido = new JLabel("BIENVENIDO GERENTE");
		lblBienvenido.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 23));
		lblBienvenido.setBounds(155, 31, 218, 31);
		add(lblBienvenido);
		
		btniNVENTARIO = new JButton("REGISTRAR INVENTARIO");
		btniNVENTARIO.addActionListener(this);
		btniNVENTARIO.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 15));
		btniNVENTARIO.setBounds(155, 91, 210, 29);
		add(btniNVENTARIO);
		
		btnSOLICITUD = new JButton("SOLICITUD DE COMPRA");
		btnSOLICITUD.addActionListener(this);
		btnSOLICITUD.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 15));
		btnSOLICITUD.setBounds(155, 155, 210, 29);
		add(btnSOLICITUD);
		
		btnVolver = new JButton("");
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(gerente.class.getResource("/imagenes/19-add-cat_icon-icons.com_76695.png")));
		btnVolver.setBounds(30, 326, 65, 41);
		add(btnVolver);
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(this);
		btnSalir.setIcon(new ImageIcon(gerente.class.getResource("/imagenes/4115235-exit-logout-sign-out_114030.png")));
		btnSalir.setBounds(406, 326, 65, 41);
		add(btnSalir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(408, 60, 0, 2);
		add(separator);
		
		btnVerS = new JButton("TODAS LAS SOLICITUDES");
		btnVerS.addActionListener(this);
		btnVerS.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 15));
		btnVerS.setBounds(155, 218, 208, 29);
		add(btnVerS);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 301, 475, 2);
		add(separator_1);
		
		modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("PRODUCTO");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("TIPO");
		modelo.addColumn("FECHA");
		modelo.addColumn("PROVEEDOR");
		modelo.addColumn("ESTADO");
		modelo.addColumn("MONTO T.");

	}
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		 if(ob.equals(btnVolver)) {
			    Logeo ventana = new Logeo();
			    ventana.setVisible(true);

			    
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.dispose();
					}
		 if (ob.equals(btnSalir)) {
		        System.exit(0);
		    }
		 if (ob.equals(btnSOLICITUD)) {
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.setContentPane(new solicitud_compra());
			    frame.revalidate();
			    frame.repaint();
						}
		 if(ob.equals(btniNVENTARIO)) {
			    registro_inventario ventana = new registro_inventario("gerente");
			    ventana.setVisible(true);

			    
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.dispose();
					}
		 if (ob.equals(btnVerS)) {
			    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			    frame.setContentPane(new solicitudes_compra(modelo));
			    frame.revalidate();
			    frame.repaint();
					}
			}
}
