import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
public class Logeo extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JPasswordField pfpass;
	private JComboBox cbCargo;
	private JButton btnLimpiar;
	private JButton btnEntrar;
	private JButton btnSalir;
	private String Usuario = "Celeste";
	private String cargoUsuario;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logeo frame = new Logeo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. 
	 * (Constructor de la Ventana)
	 */
	public Logeo() {
		
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Logeo.class.getResource("/Imagenes/Copa.png")));
		setTitle("Ventana de Logeo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Usuario");
		lblNombre.setForeground(new Color(128, 0, 128));
		lblNombre.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(162, 186, 60, 20);
		contentPane.add(lblNombre);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setForeground(new Color(128, 0, 128));
		lblContraseña.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		lblContraseña.setBounds(162, 217, 101, 29);
		contentPane.add(lblContraseña);
		
		tfNombre = new JTextField();
		tfNombre.setForeground(new Color(128, 255, 0));
		tfNombre.setBounds(273, 185, 96, 20);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		pfpass = new JPasswordField();
		pfpass.setBounds(273, 219, 96, 20);
		contentPane.add(pfpass);
		
		cbCargo = new JComboBox();
		cbCargo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbCargo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una de las opciones", "Gerente", "Empleado"}));
		cbCargo.setEditable(true);
		cbCargo.setBounds(253, 252, 177, 22);
		contentPane.add(cbCargo);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setForeground(new Color(128, 0, 128));
		lblCargo.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		lblCargo.setBounds(162, 256, 48, 18);
		contentPane.add(lblCargo);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 102, 0));
		separator.setBounds(10, 309, 634, 8);
		contentPane.add(separator);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(363, 328, 88, 22);
		contentPane.add(btnSalir);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEntrar.addActionListener(this);
		btnEntrar.setBounds(265, 328, 88, 22);
		contentPane.add(btnEntrar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		
		btnLimpiar.setBounds(163, 328, 88, 22);
		contentPane.add(btnLimpiar);
		
		JLabel lblimagen = new JLabel("");
		lblimagen.setIcon(new ImageIcon(Logeo.class.getResource("/imagenes/acceso.png")));
		lblimagen.setBounds(263, 29, 135, 143);
		contentPane.add(lblimagen);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob.equals(btnSalir)) {
			System.exit(0);
		}
		
		if(ob.equals(btnLimpiar)) {
			tfNombre.setText(null);
			pfpass.setText(null);
			cbCargo.setSelectedIndex(0);
		}
		
		if (e.getSource().equals(btnEntrar)) {

		    String opcion = cbCargo.getSelectedItem().toString();

		    
		    if (opcion.equals("Seleccione una de las opciones")) {
		        JOptionPane.showMessageDialog(null, "Selecciona un cargo");
		        return;
		    }

		    JFrame framePrincipal = new JFrame();
		    framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    framePrincipal.setBounds(100, 100, 500, 400);

		    if (opcion.equals("Gerente")) {
		        framePrincipal.setContentPane(new gerente(opcion));
		    } else {
		        framePrincipal.setContentPane(new usuario(opcion));
		    }

		    framePrincipal.setVisible(true);
		    this.dispose();
		}
		}
	}

		

