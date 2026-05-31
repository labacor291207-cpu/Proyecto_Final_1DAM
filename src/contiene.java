import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import BaseDatos.Conexion;
import BaseDatos.GestionBaseDatos;

public class contiene extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textNumero;
	private JTable table;
	private JLabel lblFecha;
	private JButton btnBuscar, btnLimpiar, btnEliminar, btnVolver;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloSolicitud;
	private JDateChooser dateChooser;
	private String[] datos = new String[3]; 
	private DefaultTableModel modeloCompra;
	private boolean modoEliminar = false;

	/**
	 * Create the panel.
	 */
	public contiene(DefaultTableModel modeloSolicitud) {
		this.modeloSolicitud = modeloSolicitud;
		
		// Ajustes del panel base para soportar la transparencia y fondo
		setLayout(null);
		setSize(560, 720);
		setOpaque(false);

		// PANEL CENTRAL TRANSLÚCIDO (Caja de cristal)
		JPanel panelContenedor = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.setColor(new Color(255, 255, 255, 38)); // Borde sutil blanco
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
				g2.dispose();
				super.paintComponent(g);
			}
		};
		panelContenedor.setLayout(null);
		panelContenedor.setBackground(new Color(0, 0, 0, 180)); // tono translúcido
		panelContenedor.setOpaque(false);
		panelContenedor.setBounds(45, 35, 470, 615); // Dimensiones para la visualización de la tabla
		add(panelContenedor);

		// TÍTULO DE LA APLICACIÓN
		JLabel lblTitulo = new JLabel("CONSULTA SOLICITUDES");
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setBounds(40, 20, 390, 30);
		panelContenedor.add(lblTitulo);

		// ETIQUETA: BUSCAR POR FECHA
		lblFecha = new JLabel("Buscar por fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		lblFecha.setBounds(40, 75, 150, 20);
		panelContenedor.add(lblFecha);

		// CONTENEDOR PÍLDORA PARA EL SELECTOR JDATECHOOSER
		JPanel pildoraFecha = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 255, 255, 20));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
				g2.setColor(new Color(255, 255, 255, 40));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
				g2.dispose();
			}
		};
		pildoraFecha.setLayout(null);
		pildoraFecha.setOpaque(false);
		pildoraFecha.setBounds(40, 100, 250, 42);
		panelContenedor.add(pildoraFecha);

		dateChooser = new JDateChooser();
		dateChooser.setOpaque(false);
		dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dateChooser.getJCalendar().setBackground(new Color(30, 30, 30));
		dateChooser.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		if (dateChooser.getDateEditor().getUiComponent() instanceof JTextField) {
		    JTextField txtEditor = (JTextField) dateChooser.getDateEditor().getUiComponent();
		    txtEditor.setOpaque(false);
		    // CAMBIO: Color del texto a Blanco para su correcta legibilidad
		    txtEditor.setForeground(Color.WHITE);
		    txtEditor.setCaretColor(Color.WHITE);
		    txtEditor.setBorder(BorderFactory.createEmptyBorder());
		    txtEditor.setDisabledTextColor(Color.WHITE); 

		    // Reaplica el color blanco cada vez que se selecciona una fecha
		    dateChooser.addPropertyChangeListener("date", evt -> {
		        txtEditor.setForeground(Color.WHITE);
		        txtEditor.repaint();
		    });
		}

		dateChooser.setBounds(5, 2, 240, 38);
		pildoraFecha.add(dateChooser);

		// BOTÓN BUSCAR (Estilo Píldora redondeada)
		btnBuscar = new JButton("") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
				g2.setColor(new Color(255, 255, 255, 50));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
				g2.dispose();
				super.paintComponent(g);
			}
		};
		try { btnBuscar.setIcon(new ImageIcon(contiene.class.getResource("/imagenes/Lupa.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnBuscar, new Color(255, 255, 255, 35), new Color(255, 255, 255, 55));
		btnBuscar.setBounds(305, 100, 125, 42);
		panelContenedor.add(btnBuscar);

		// JSCROLLPANE Y JTABLE (Inserción translúcida)
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 165, 390, 300);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 38), 1));
		panelContenedor.add(scrollPane);

		table = new JTable();
		table.setOpaque(false);
		table.setBackground(new Color(20, 20, 20, 150)); // Fondo de celdas oscuro semitransparente
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setGridColor(new Color(255, 255, 255, 30));
		table.setRowHeight(25);
		table.setSelectionBackground(new Color(255, 255, 255, 50));
		table.setSelectionForeground(Color.WHITE);
		
		// CAMBIO: Asegura que el auto-redimensionado de columnas evite textos cortados
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(new Color(40, 40, 40));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Segoe UI", Font.BOLD, 13));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] { "N\u00B0Solicitud", "Fecha", "Estado" }
		));
		scrollPane.setViewportView(table);

		// BOTONES INFERIORES DE ACCIÓN 
		Color translucidoBase = new Color(255, 255, 255, 35);
		Color translucidoHover = new Color(255, 255, 255, 55);

		// BOTÓN VOLVER (Ancho reajustado a 125 para evitar textos/iconos cortados)
		btnVolver = new JButton("") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(new Color(255, 255, 255, 60));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
				g2.dispose();
				super.paintComponent(g);
			}
		};
		try { btnVolver.setIcon(new ImageIcon(contiene.class.getResource("/imagenes/Volver.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnVolver, translucidoBase, translucidoHover);
		btnVolver.setBounds(25, 520, 125, 46);
		panelContenedor.add(btnVolver);

		// BOTÓN LIMPIAR (Ancho reajustado a 125 para evitar textos/iconos cortados)
		btnLimpiar = new JButton("") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(new Color(255, 255, 255, 60));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
				g2.dispose();
				super.paintComponent(g);
			}
		};
		try { btnLimpiar.setIcon(new ImageIcon(contiene.class.getResource("/imagenes/Clear.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnLimpiar, translucidoBase, translucidoHover);
		btnLimpiar.setBounds(172, 520, 125, 46);
		panelContenedor.add(btnLimpiar);

		// BOTÓN ELIMINAR (Ancho reajustado a 125 para evitar textos/iconos cortados)
		btnEliminar = new JButton("") {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
				g2.setColor(new Color(255, 255, 255, 60));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
				g2.dispose();
				super.paintComponent(g);
			}
		};
		try { btnEliminar.setIcon(new ImageIcon(contiene.class.getResource("/imagenes/Limpiar.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnEliminar, translucidoBase, translucidoHover);
		btnEliminar.setBounds(320, 520, 125, 46);
		panelContenedor.add(btnEliminar);

		// CAPA DE IMAGEN DE FONDO (fondo.png)
		try {
			java.net.URL urlFondo = contiene.class.getResource("/imagenes/fondo5.png");
			if (urlFondo != null) {
				ImageIcon icono = new ImageIcon(urlFondo);
				Image imgEscalada = icono.getImage().getScaledInstance(560, 720, Image.SCALE_SMOOTH);
				JLabel lblFondo = new JLabel(new ImageIcon(imgEscalada));
				lblFondo.setBounds(0, 0, 560, 720);
				add(lblFondo);
				setComponentZOrder(lblFondo, getComponentCount() - 1);
			}
		} catch (Exception e) {
			System.out.println("Error al renderizar el fondo de pantalla corporativo.");
		}
	}

	/**
	 * Método auxiliar para homogeneizar los efectos y estados interactivos de los botones.
	 */
	private void configurarBotonEstilo(JButton boton, Color base, Color hover) {
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setContentAreaFilled(false);
		boton.setOpaque(false);
		boton.setBackground(base);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.addActionListener(this);
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { boton.setBackground(hover); }
			@Override
			public void mouseExited(MouseEvent e)  { boton.setBackground(base);  }
		});
	}

	// ── MANTENIMIENTO INTEGRO DE TU LÓGICA DE NEGOCIO ORIGINAL ──
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob.equals(btnBuscar)) {
			try {
				Date fechaDate = dateChooser.getDate();
				String fecha = null;

				if (fechaDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					fecha = sdf.format(fechaDate);
				}

				GestionBaseDatos g = new GestionBaseDatos();
				DefaultTableModel modelo = g.buscarSolicitud(fecha);
				table.setModel(modelo);

				if (fecha != null && modelo.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
						"No hay solicitudes registradas para la fecha: " + fecha,
						"Sin resultados",
						JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage());
			}
		}

		if (ob.equals(btnLimpiar)) {
			dateChooser.setDate(null);  
			table.setModel(new DefaultTableModel(
				new String[]{"N°Solicitud", "Fecha", "Estado"}, 0
			));
		}

		if (ob.equals(btnVolver)) {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			if (frame != null) {
				frame.setContentPane(new solicitud_compra(modeloCompra));
				frame.revalidate();
				frame.repaint();
			}
		}

		if (ob.equals(btnEliminar)) {
			modoEliminar = !modoEliminar;  

			if (modoEliminar) {
				JOptionPane.showMessageDialog(null, "Modo eliminar activado, pulse la fila que desea eliminar");

				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (modoEliminar) {
							int fila = table.getSelectedRow();
							if (fila != -1) {
								String numSolicitud = table.getValueAt(fila, 0).toString();
								int confirmar = JOptionPane.showConfirmDialog(null, 
									"¿Está seguro de que desea eliminar la solicitud N° " + numSolicitud + "?", 
									"Confirmar baja", 
									JOptionPane.YES_NO_OPTION);

								if (confirmar == JOptionPane.YES_OPTION) {
									try {
										GestionBaseDatos g = new GestionBaseDatos();
										g.buscarSolicitud(numSolicitud);
										((DefaultTableModel)table.getModel()).removeRow(fila);
										JOptionPane.showMessageDialog(null, "Solicitud eliminada correctamente.");
									} catch (Exception ex) {
										JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
									}
								}
							}
						}
					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "Modo eliminar desactivado");
				for (java.awt.event.MouseListener ml : table.getMouseListeners()) {
					table.removeMouseListener(ml);
				}
			}
		}
	}
}