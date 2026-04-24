import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Choice;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import BaseDatos.Conexion;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Button;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class registro_inventario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNumero;
	private JLabel lblFecha;
	private JLabel lblProducto;
	private JLabel lblCantidad;
	private JLabel lblTipo;
	private JButton btnEnviar;
	private JButton btnBorrar;
	private JSeparator separator;
	private JComboBox comboBox;
	private JButton btnGuardar;
	private DefaultTableModel modelorefresco;
	private DefaultTableModel modelolicor;
	private JButton btnSalir;
	private JTable table;
	private JTable table_1;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JButton btnVolver;
	private String cargoUsuario;
	
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro_inventario frame = new registro_inventario("gerente");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	
	
		
		// ← variable de clase

	    // ✅ Constructor con cargo
	    public registro_inventario(String cargo) {
	        this.cargoUsuario = cargo;
	    
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblregistro = new JLabel("REGISTRO DE INVENTARIO");
		lblregistro.setBounds(148, 23, 210, 20);
		contentPane.add(lblregistro);
		lblregistro.setFont(new Font("Rockwell Condensed", Font.BOLD, 19));
		
		textField = new JTextField();
		textField.setBounds(114, 64, 154, 28);
		textField.setBackground(new Color(255, 255, 255));
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNumero = new JLabel("N° REGISTRO:");
		lblNumero.setBounds(15, 73, 115, 19);
		lblNumero.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		contentPane.add(lblNumero);
		
		lblFecha = new JLabel("FECHA:");
		lblFecha.setBounds(278, 66, 45, 19);
		lblFecha.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		contentPane.add(lblFecha);
		
		

		dateChooser = new JDateChooser();
		dateChooser.setBounds(333, 64, 160, 28);
		dateChooser.setDateFormatString("yyyy-MM-dd");

		contentPane.add(dateChooser);
		
		lblProducto = new JLabel("PRODUCTO:");
		lblProducto.setBounds(114, 140, 115, 19);
		lblProducto.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		contentPane.add(lblProducto);
		
		textField_2 = new JTextField();
		textField_2.setBounds(262, 142, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setBounds(114, 187, 115, 19);
		lblCantidad.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		contentPane.add(lblCantidad);
		
		textField_3 = new JTextField();
		textField_3.setBounds(262, 189, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblTipo = new JLabel("TIPO:");
		lblTipo.setBounds(114, 227, 79, 20);
		lblTipo.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		contentPane.add(lblTipo);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Refresco", "Licor"}));
		comboBox.setBounds(264, 229, 94, 20);
		contentPane.add(comboBox);
		
		btnEnviar = new JButton("");
		btnEnviar.addActionListener(this);
		btnEnviar.setIcon(new ImageIcon(registro_inventario.class.getResource("/imagenes/32officeicons-15_89722.png")));
		btnEnviar.setBounds(387, 369, 65, 28);
		contentPane.add(btnEnviar);
		
		btnBorrar = new JButton("");
		btnBorrar.addActionListener(this);
		btnBorrar.setIcon(new ImageIcon(registro_inventario.class.getResource("/imagenes/1-trash-cat_icon-icons.com_76677.png")));
		btnBorrar.setBounds(43, 363, 65, 34);
		contentPane.add(btnBorrar);
		
		separator = new JSeparator();
		separator.setBounds(15, 98, 491, 2);
		contentPane.add(separator);
		
		btnGuardar = new JButton("");
		btnGuardar.addActionListener(this);
		btnGuardar.setIcon(new ImageIcon(registro_inventario.class.getResource("/imagenes/1485477072-check_78599.png")));
		btnGuardar.setBounds(214, 369, 54, 28);
		contentPane.add(btnGuardar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(15, 407, 491, 33);
		contentPane.add(separator_1);
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(this);
		btnSalir.setIcon(new ImageIcon(registro_inventario.class.getResource("/imagenes/4115235-exit-logout-sign-out_114030.png")));
		btnSalir.setBounds(422, 420, 54, 30);
		contentPane.add(btnSalir);
		
		JLabel lblFechaC = new JLabel("FECHA CADUCIDAD:");
		lblFechaC.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		lblFechaC.setBounds(114, 269, 133, 20);
		contentPane.add(lblFechaC);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(262, 271, 96, 28);
		contentPane.add(dateChooser_1);
		
		table = new JTable();
		table.setForeground(new Color(128, 255, 255));
		table.setBackground(new Color(143, 239, 239));
		table.setBounds(88, 127, 164, 186);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(201, 158, 224));
		table_1.setBounds(250, 127, 194, 186);
		contentPane.add(table_1);
		
		btnVolver = new JButton("");
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(registro_inventario.class.getResource("/imagenes/19-add-cat_icon-icons.com_76695.png")));
		btnVolver.setBounds(46, 420, 62, 33);
		contentPane.add(btnVolver);
		
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
				   if (ob == btnBorrar) {
					    textField.setText("");
					    textField_2.setText("");
					    textField_3.setText("");
					    comboBox.setSelectedIndex(0);
					    dateChooser.setDate(null);
					    dateChooser_1.setDate(null);
					}		
			if(e.getSource().equals(btnEnviar)) {

			    String opcion = comboBox.getSelectedItem().toString();

			    if(opcion.equals("Refresco")) {
			        this.setContentPane(new refrescos_registro(modelorefresco));
			        this.revalidate();
			    }

			    if(opcion.equals("Licor")) {
			        this.setContentPane(new licores_registro(modelolicor)); 
			        this.revalidate();
			    }
			    
			}
				if (ob.equals(btnGuardar)) {

				    String nombre = textField_2.getText();
				    String stock = textField_3.getText();
				    String tipo = comboBox.getSelectedItem().toString();
				    Date fecha_caducidad = dateChooser_1.getDate();

				    if(nombre.isEmpty() || stock.isEmpty() || tipo.equals("Seleccionar") ||fecha_caducidad == null){
				        JOptionPane.showMessageDialog(null, "Completa todos los campos");
				        return;
				    }

				    try {
				        Connection con = Conexion.getConexion();

				        String sql = "";

				        if(tipo.equals("Refresco")){
				            sql = "INSERT INTO refrescos (nombre, stock, fecha_caducidad) VALUES (?, ?,?)";
				        } else if(tipo.equals("Licor")){
				            sql = "INSERT INTO licores (nombre, stock, fecha_caducidad) VALUES (?, ?, ?)";
				        }

				        PreparedStatement ps = con.prepareStatement(sql);

				        ps.setString(1, nombre);
				        ps.setString(2, stock);
				        ps.setDate(3, new java.sql.Date(fecha_caducidad.getTime()));

				        ps.executeUpdate();

				        JOptionPane.showMessageDialog(null, "Guardado correctamente");

				        
				        if(tipo.equals("Refresco")){
				            this.setContentPane(new refrescos_registro(modelorefresco));
				        } else if(tipo.equals("Licor")){
				            this.setContentPane(new licores_registro(modelolicor));
				        }

				        this.revalidate();

				        con.close();

				    } catch (Exception ex) {
				        ex.printStackTrace();
				    	}
					}
				if(ob.equals(btnSalir)) {
					System.exit(EXIT_ON_CLOSE);
					}
				
				if (ob.equals(btnVolver)) {
				    JFrame framePrincipal = new JFrame();
				    framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    framePrincipal.setBounds(100, 100, 500, 400); // ← tamaño fijo igual al original

				    if (cargoUsuario.equalsIgnoreCase("Gerente")) {
				        framePrincipal.setContentPane(new gerente(cargoUsuario));
				    } else {
				        framePrincipal.setContentPane(new usuario(cargoUsuario));
				    }

				    framePrincipal.setVisible(true);
				    this.dispose();
					}
				}
			}

		
