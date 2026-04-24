import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BaseDatos.Conexion;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
import java.awt.event.ActionEvent;

public class solicitud_compra extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JTable table_1;
	private JLabel lblSolicitud;
	private JLabel lblNumero;
	private JLabel lblFecha;
	private JLabel lblProducto;
	private JLabel lblCantidad;
	private JLabel lblTipo;
	private JLabel lblProveedor;
	private JLabel lblEstado;
	private JLabel lblMonto;
	private JComboBox cbTIPO;
	private JComboBox cbESTADO;
	private JButton btnVolver;
	private JButton btnGuardar;
	private JButton btnLimpiar;
	private JDateChooser dateChooser;
	private DefaultTableModel modelo;
	

	/**
	 * Create the panel.
	 */
	public solicitud_compra() {
		setLayout(null);
		
		lblSolicitud = new JLabel("SOLICITUD DE COMPRA");
		lblSolicitud.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 23));
		lblSolicitud.setBounds(140, 35, 237, 36);
		add(lblSolicitud);
		
		lblNumero = new JLabel("N°SOLICITUD:");
		lblNumero.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblNumero.setBounds(26, 86, 92, 27);
		add(lblNumero);
		
		textField = new JTextField();
		textField.setBounds(121, 81, 101, 23);
		add(textField);
		textField.setColumns(10);
		
		lblFecha = new JLabel("FECHA:");
		lblFecha.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblFecha.setBounds(269, 90, 71, 19);
		add(lblFecha);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(332, 82, 160, 28);
		dateChooser.setDateFormatString("yyyy-MM-dd");

		add(dateChooser);
		
		lblProducto = new JLabel("PRODUCTO:");
		lblProducto.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblProducto.setBounds(64, 144, 70, 18);
		add(lblProducto);
		
		lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblCantidad.setBounds(64, 177, 83, 18);
		add(lblCantidad);
		
		lblTipo = new JLabel("TIPO:");
		lblTipo.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblTipo.setBounds(64, 211, 71, 19);
		add(lblTipo);
		
		lblProveedor = new JLabel("PROVEEDOR:");
		lblProveedor.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblProveedor.setBounds(64, 240, 84, 19);
		add(lblProveedor);
		
		lblEstado = new JLabel("ESTADO:");
		lblEstado.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblEstado.setBounds(64, 269, 71, 19);
		add(lblEstado);
		
		lblMonto = new JLabel("MONTO TOTAL:");
		lblMonto.setFont(new Font("Rockwell Nova Cond", Font.BOLD, 14));
		lblMonto.setBounds(64, 298, 101, 19);
		add(lblMonto);
		
		textField_2 = new JTextField();
		textField_2.setBounds(221, 141, 119, 18);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(221, 169, 119, 18);
		add(textField_3);
		
		cbTIPO = new JComboBox();
		cbTIPO.setModel(new DefaultComboBoxModel(new String[] {"Selecciona el tipo", "REFRESCO", "LICOR"}));
		cbTIPO.setBounds(221, 197, 119, 27);
		add(cbTIPO);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(221, 240, 119, 18);
		add(textField_4);
		
		cbESTADO = new JComboBox();
		cbESTADO.setModel(new DefaultComboBoxModel(new String[] {"Selecciona estado", "PENDIENTE", "APROBADO", "RECIBIDO"}));
		cbESTADO.setBounds(221, 269, 119, 20);
		add(cbESTADO);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(221, 299, 119, 18);
		add(textField_5);
		
		table = new JTable();
		table.setBackground(new Color(245, 214, 154));
		table.setBounds(36, 123, 151, 212);
		add(table);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(163, 189, 237));
		table_1.setBounds(184, 123, 203, 212);
		add(table_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 357, 482, 19);
		add(separator);
		
		btnVolver = new JButton("");
		btnVolver.addActionListener(this);
		btnVolver.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/19-add-cat_icon-icons.com_76695.png")));
		btnVolver.setBounds(36, 386, 71, 36);
		add(btnVolver);
		
		btnGuardar = new JButton("");
		btnGuardar.addActionListener(this);
		btnGuardar.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/1485477072-check_78599.png")));
		btnGuardar.setBounds(214, 386, 71, 36);
		add(btnGuardar);
		
		btnLimpiar = new JButton("");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/1-trash-cat_icon-icons.com_76677.png")));
		btnLimpiar.setBounds(366, 386, 71, 36);
		add(btnLimpiar);
		
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

		if (ob.equals(btnVolver)) {
		    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		    frame.setContentPane(new gerente("cargo"));
		    frame.revalidate();
		    frame.repaint();
					}
		if (ob.equals(btnGuardar)) {

		    String producto = textField_2.getText();
		    String cantidad = textField_3.getText();
		    String tipo = cbTIPO.getSelectedItem().toString();
		    Date fecha = dateChooser.getDate();
		    String proveedor = textField_4.getText();
		    String estado = cbESTADO.getSelectedItem().toString();
		    String monto = textField_5.getText();

		    
		    if (producto.isEmpty() || cantidad.isEmpty()
		            || tipo.equals("Selecciona el tipo")
		            || fecha == null
		            || proveedor.isEmpty()
		            || estado.equals("Selecciona estado")
		            || monto.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Completa todos los campos");
		        return;
		    }

		    try {
		        Connection con = Conexion.getConexion();

		        
		        String sql = "INSERT INTO solicitudes_compra "
		                   + "(producto, cantidad, tipo, fecha_solicitud, proveedor, estado, monto_total) "
		                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setString(1, producto);
		        ps.setString(2, cantidad);
		        ps.setString(3, tipo);
		        ps.setDate(4, new java.sql.Date(fecha.getTime()));
		        ps.setString(5, proveedor);
		        ps.setString(6, estado);   // ✅ antes ponías monto aquí por error
		        ps.setString(7, monto);

		        ps.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Guardado correctamente");

		        
		        textField_2.setText("");
		        textField_3.setText("");
		        textField_4.setText("");
		        textField_5.setText("");
		        cbTIPO.setSelectedIndex(0);
		        cbESTADO.setSelectedIndex(0);
		        dateChooser.setDate(null);

		        con.close();

		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage());
		    }
		}
		
		
		
		
		}
	}

