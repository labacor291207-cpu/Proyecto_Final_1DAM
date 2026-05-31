import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import BaseDatos.GestionBaseDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class producto extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel modeloProducto;
	private JLabel lblNombre, lblCategoria, lblFecha, lblStock;
	private JComboBox<categoria_producto> comboBox;
	private JButton btnBuscar, btnLimpiar, btnVolver, btnAgregar, btnEditar, btnSalir, btnEliminar;
	private JScrollPane scrollPane;
	private String cargoUsuario;
	private JDateChooser dateChooser;
	private int idProductoSeleccionado = -1;

	public producto(DefaultTableModel modeloProducto, String cargo) {
		this.cargoUsuario = cargo;

		this.modeloProducto = new DefaultTableModel(
				new String[]{"Nombre", "Stock", "Fecha Caducidad"}, 0
		);

		setLayout(null);
		setSize(560, 720);
		setOpaque(false);

		Color translucidoBase  = new Color(255, 255, 255, 35);
		Color translucidoHover = new Color(255, 255, 255, 55);

		// ── PANEL CONTENEDOR TRANSLÚCIDO CENTRAL ──────────────────────────────
		JPanel panelContenedor = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				g2.setColor(new Color(255, 255, 255, 38));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
				g2.dispose();
				super.paintComponent(g);
			}
		};
		panelContenedor.setLayout(null);
		panelContenedor.setBackground(new Color(0, 0, 0, 180));
		panelContenedor.setOpaque(false);
		panelContenedor.setBounds(30, 30, 500, 620);
		add(panelContenedor);

		// ── TÍTULO ─────────────────────────────────────────────────────────────
		JLabel lblTitulo = new JLabel("GESTIÓN DE PRODUCTOS");
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setBounds(80, 15, 340, 36);
		panelContenedor.add(lblTitulo);

		// ── NOMBRE PRODUCTO ────────────────────────────────────────────────────
		lblNombre = new JLabel("NOMBRE PRODUCTO:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNombre.setBounds(36, 70, 150, 18);
		panelContenedor.add(lblNombre);

		textField = crearTextFieldEstilizado();
		textField.setBounds(36, 93, 175, 35);
		panelContenedor.add(textField);

		// ── CATEGORÍA ──────────────────────────────────────────────────────────
		lblCategoria = new JLabel("CATEGORÍA:");
		lblCategoria.setForeground(Color.WHITE);
		lblCategoria.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCategoria.setBounds(240, 70, 100, 18);
		panelContenedor.add(lblCategoria);

		JPanel pildoraCategoria = crearContenedorPildoraCombo();
		pildoraCategoria.setBounds(235, 93, 145, 35);
		panelContenedor.add(pildoraCategoria);

		comboBox = new JComboBox<>();
		comboBox.removeAllItems();
		comboBox.addItem(new categoria_producto(0, "Seleccionar:"));
		comboBox.addItem(new categoria_producto(1, "Licor"));
		comboBox.addItem(new categoria_producto(2, "Refresco"));
		comboBox.addItem(new categoria_producto(3, "Otros"));
		estilarComboBox(comboBox);
		comboBox.setBounds(0, 0, 145, 35);
		pildoraCategoria.add(comboBox);

		// ── BOTÓN BUSCAR ───────────────────────────────────────────────────────
		btnBuscar = crearBotonPildora();
		try { btnBuscar.setIcon(new ImageIcon(producto.class.getResource("/imagenes/lupa.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnBuscar, translucidoBase, translucidoHover);
		btnBuscar.setBounds(395, 93, 45, 35);
		panelContenedor.add(btnBuscar);

		// ── BOTÓN LIMPIAR ──────────────────────────────────────────────────────
		btnLimpiar = crearBotonPildora();
		try { btnLimpiar.setIcon(new ImageIcon(producto.class.getResource("/imagenes/Limpiar.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnLimpiar, translucidoBase, translucidoHover);
		btnLimpiar.setBounds(450, 93, 45, 35);
		panelContenedor.add(btnLimpiar);

		// ── FECHA CADUCIDAD ────────────────────────────────────────────────────
		lblFecha = new JLabel("FECHA CADUCIDAD:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblFecha.setBounds(36, 148, 140, 18);
		panelContenedor.add(lblFecha);

		// CONTENEDOR PÍLDORA PARA EL JDATECHOOSER
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
		pildoraFecha.setBounds(36, 171, 175, 42);
		panelContenedor.add(pildoraFecha);

		dateChooser = new JDateChooser();
		dateChooser.setOpaque(false);
		dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		dateChooser.getJCalendar().setBackground(new Color(30, 30, 30));
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		if (dateChooser.getDateEditor().getUiComponent() instanceof JTextField) {
			JTextField txtEditor = (JTextField) dateChooser.getDateEditor().getUiComponent();
			txtEditor.setOpaque(false);
			txtEditor.setForeground(Color.RED);
			txtEditor.setCaretColor(Color.RED);
			txtEditor.setDisabledTextColor(Color.RED);
			txtEditor.setBorder(BorderFactory.createEmptyBorder());
			dateChooser.addPropertyChangeListener("date", evt -> {
				txtEditor.setForeground(Color.RED);
				txtEditor.repaint();
			});
		}
		dateChooser.setBounds(5, 2, 165, 38);
		pildoraFecha.add(dateChooser);

		// ── STOCK ──────────────────────────────────────────────────────────────
		lblStock = new JLabel("STOCK:");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblStock.setBounds(240, 148, 80, 18);
		panelContenedor.add(lblStock);

		textField_1 = crearTextFieldEstilizado();
		textField_1.setBounds(235, 171, 120, 35);
		panelContenedor.add(textField_1);

		// ── TABLA TRANSLÚCIDA ──────────────────────────────────────────────────
		table = new JTable(this.modeloProducto);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int fila = table.getSelectedRow();
					if (fila >= 0) {
						idProductoSeleccionado = (int) table.getValueAt(fila, 0);
						textField.setText(table.getValueAt(fila, 1).toString());
						textField_1.setText(table.getValueAt(fila, 2).toString());
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date fecha = sdf.parse(table.getValueAt(fila, 3).toString());
							dateChooser.setDate(fecha);
						} catch (Exception ex) {
							dateChooser.setDate(null);
						}
					}
				}
			}
		});

		table.setOpaque(false);
		table.setBackground(new Color(20, 20, 20, 150));
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.setRowHeight(25);
		table.setGridColor(new Color(255, 255, 255, 30));
		table.setSelectionBackground(new Color(255, 255, 255, 50));
		table.setSelectionForeground(Color.WHITE);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(new Color(40, 40, 40));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 255, 255, 60)));

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(36, 235, 390, 170);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 38), 1));
		panelContenedor.add(scrollPane);

		// ── BOTONES DE ACCIÓN LATERALES ────────────────────────────────────────
		btnAgregar = crearBotonPildora();
		try { btnAgregar.setIcon(new ImageIcon(producto.class.getResource("/imagenes/Agregar.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnAgregar, translucidoBase, translucidoHover);
		btnAgregar.setBounds(438, 235, 55, 46);
		panelContenedor.add(btnAgregar);

		btnEditar = crearBotonPildora();
		try { btnEditar.setIcon(new ImageIcon(producto.class.getResource("/imagenes/editar.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnEditar, translucidoBase, translucidoHover);
		btnEditar.setBounds(438, 295, 55, 46);
		panelContenedor.add(btnEditar);

		btnEliminar = crearBotonPildora();
		try { btnEliminar.setIcon(new ImageIcon(producto.class.getResource("/imagenes/Limpiar.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnEliminar, translucidoBase, translucidoHover);
		btnEliminar.setBounds(438, 355, 55, 46);
		panelContenedor.add(btnEliminar);

		// ── BOTONES INFERIORES ─────────────────────────────────────────────────
		btnVolver = crearBotonPildora();
		try { btnVolver.setIcon(new ImageIcon(producto.class.getResource("/imagenes/Volver.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnVolver, translucidoBase, translucidoHover);
		btnVolver.setBounds(36, 545, 110, 46);
		panelContenedor.add(btnVolver);

		btnSalir = crearBotonPildora();
		try { btnSalir.setIcon(new ImageIcon(producto.class.getResource("/imagenes/salida.png"))); } catch(Exception ignored){}
		configurarBotonEstilo(btnSalir, translucidoBase, translucidoHover);
		btnSalir.setBounds(354, 545, 110, 46);
		panelContenedor.add(btnSalir);

		// ── FONDO fondo6.png ───────────────────────────────────────────────────
		try {
			java.net.URL urlFondo = producto.class.getResource("/imagenes/fondo10.png");
			if (urlFondo != null) {
				ImageIcon icono = new ImageIcon(urlFondo);
				Image imgEscalada = icono.getImage().getScaledInstance(560, 720, Image.SCALE_SMOOTH);
				JLabel lblFondo = new JLabel(new ImageIcon(imgEscalada));
				lblFondo.setBounds(0, 0, 560, 720);
				add(lblFondo);
				setComponentZOrder(lblFondo, getComponentCount() - 1);
			}
		} catch (Exception e) {
			System.out.println("Error al cargar el fondo en producto.");
		}
	}

	// ── MÉTODO: ocultar columna ID ─────────────────────────────────────────────
	private void ocultarColumnaID() {
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		table.getColumnModel().getColumn(0).setResizable(false);
	}

	// ── MÉTODO: validar campos ─────────────────────────────────────────────────
	private void validarCampos() throws
	validaciones.FechaVaciaException,
	validaciones.ProductoSoloLetrasException,
	validaciones.ProductoLongitudException,
	validaciones.CantidadSoloNumerosException2,
	validaciones.CantidadLongitudException2,
	validaciones.ProductoDuplicadoException { // <--- AGREGAR ESTA LÍNEA

String nombre = textField.getText().trim();
String cantidad = textField_1.getText().trim();
Date fecha_caducidad = dateChooser.getDate();

if (fecha_caducidad == null)
	throw new validaciones.FechaVaciaException();

if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
	throw new validaciones.ProductoSoloLetrasException();

if (nombre.length() > 15)
	throw new validaciones.ProductoLongitudException();

if (!cantidad.matches("[0-9]+"))
	throw new validaciones.CantidadSoloNumerosException2();

if (cantidad.length() > 6)
	throw new validaciones.CantidadLongitudException2();

GestionBaseDatos g = new GestionBaseDatos();
if (g.existeProducto(nombre)) {
	throw new validaciones.ProductoDuplicadoException();
}
}

	// ── AUXILIAR: TextField estilizado (píldora translúcida) ──────────────────
	private JTextField crearTextFieldEstilizado() {
		JTextField field = new JTextField() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 255, 255, 20));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
				g2.dispose();
				super.paintComponent(g);
			}
			@Override
			protected void paintBorder(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 255, 255, 40));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
				g2.dispose();
			}
		};
		field.setOpaque(false);
		field.setForeground(Color.WHITE);
		field.setCaretColor(Color.WHITE);
		field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		field.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		return field;
	}

	// ── AUXILIAR: Contenedor píldora para ComboBox ─────────────────────────────
	private JPanel crearContenedorPildoraCombo() {
		JPanel pildora = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(255, 255, 255, 20));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
				g2.setColor(new Color(255, 255, 255, 40));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
				g2.setColor(new Color(255, 255, 255, 180));
				int cx = getWidth() - 18;
				int cy = getHeight() / 2;
				int[] xp = {cx - 5, cx + 5, cx};
				int[] yp = {cy - 3, cy - 3, cy + 4};
				g2.fillPolygon(xp, yp, 3);
				g2.dispose();
			}
		};
		pildora.setLayout(null);
		pildora.setOpaque(false);
		return pildora;
	}

	// ── AUXILIAR: Estilizar ComboBox (sin look&feel nativo) ───────────────────
	private void estilarComboBox(JComboBox<categoria_producto> combo) {
		combo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton invisible = new JButton();
				invisible.setBorder(BorderFactory.createEmptyBorder());
				invisible.setContentAreaFilled(false);
				invisible.setOpaque(false);
				invisible.setPreferredSize(new java.awt.Dimension(0, 0));
				return invisible;
			}
			@Override
			public void paintCurrentValue(Graphics g, java.awt.Rectangle bounds, boolean hasFocus) {
				javax.swing.ListCellRenderer<Object> renderer = comboBox.getRenderer();
				java.awt.Component c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
				c.setFont(comboBox.getFont());
				if (c instanceof javax.swing.JComponent) {
					((javax.swing.JComponent) c).setOpaque(false);
				}
				currentValuePane.paintComponent(g, c, comboBox, bounds.x, bounds.y, bounds.width, bounds.height, c instanceof JButton);
			}
			@Override
			public void paintCurrentValueBackground(Graphics g, java.awt.Rectangle bounds, boolean hasFocus) {}
			@Override
			protected javax.swing.plaf.basic.ComboPopup createPopup() {
				javax.swing.plaf.basic.BasicComboPopup popup = (javax.swing.plaf.basic.BasicComboPopup) super.createPopup();
				popup.getList().setBackground(new Color(30, 30, 30));
				popup.getList().setForeground(Color.WHITE);
				popup.getList().setSelectionBackground(new Color(0, 128, 255));
				popup.getList().setFont(new Font("Segoe UI", Font.PLAIN, 14));
				popup.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 38), 1));
				return popup;
			}
		});

		combo.setRenderer(new javax.swing.plaf.basic.BasicComboBoxRenderer() {
			@Override
			public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				setOpaque(index != -1);
				setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
				setForeground(Color.WHITE);
				return this;
			}
		});

		combo.setOpaque(false);
		combo.setBackground(new Color(0, 0, 0, 0));
		combo.setForeground(Color.WHITE);
		combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		combo.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 25));
	}

	// ── AUXILIAR: Crear botón con forma píldora redondeada ────────────────────
	private JButton crearBotonPildora() {
		return new JButton("") {
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
	}

	// ── AUXILIAR: Configurar estilo e interactividad de botones ──────────────
	private void configurarBotonEstilo(JButton boton, Color base, Color hover) {
		boton.setBackground(base);
		boton.setForeground(Color.WHITE);
		boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		boton.setFocusPainted(false);
		boton.setBorderPainted(false);
		boton.setContentAreaFilled(false);
		boton.setOpaque(false);
		boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boton.addActionListener(this);
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { boton.setBackground(hover); boton.repaint(); }
			@Override
			public void mouseExited(MouseEvent e)  { boton.setBackground(base);  boton.repaint(); }
		});
	}

	// ── LÓGICA DE NEGOCIO ───────────────────────────────────────────
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob.equals(btnBuscar)) {
			try {
				String nombre = textField.getText().trim();
				categoria_producto categoriaSeleccionada = (categoria_producto) comboBox.getSelectedItem();
				String categoria = "";
				if (categoriaSeleccionada != null) {
					categoria = categoriaSeleccionada.toString();
				}

				GestionBaseDatos g = new GestionBaseDatos();
				DefaultTableModel modelo = g.buscarProductos(nombre, categoria);
				table.setModel(modelo);
				ocultarColumnaID();

				if (nombre != null && modelo.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
						"No hay productos registrados con el nombre: " + nombre,
						"Sin resultados",
						JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}

		if (ob.equals(btnAgregar)) {
			try {
				validarCampos();

				String nombre = textField.getText();
				String stock = textField_1.getText().trim();
				Date fecha_caducidad = dateChooser.getDate();

				if (stock.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese el stock");
					return;
				}

				int stockInt;
				try {
					stockInt = Integer.parseInt(stock);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Stock debe ser numérico");
					return;
				}

				if (fecha_caducidad == null) {
					JOptionPane.showMessageDialog(null, "Seleccione una fecha");
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = sdf.format(fecha_caducidad);

				categoria_producto categoriaSeleccionada = (categoria_producto) comboBox.getSelectedItem();

				if (categoriaSeleccionada == null || categoriaSeleccionada.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Seleccione una categoría válida");
					return;
				}

				int idCategoria = categoriaSeleccionada.getId();

				GestionBaseDatos g = new GestionBaseDatos();
				g.guardarProducto(nombre, stockInt, fecha, idCategoria);
				JOptionPane.showMessageDialog(null, "Producto guardado");

			} catch (validaciones.ProductoDuplicadoException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Producto Duplicado", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.FechaVaciaException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.ProductoSoloLetrasException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.ProductoLongitudException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.CantidadSoloNumerosException2 ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.CantidadLongitudException2 ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Error en la conversión de cantidad", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (ob.equals(btnEditar)) {
			if (idProductoSeleccionado == -1) {
				JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla primero");
				return;
			}
			try {
				validarCampos();

				String nuevoNombre = textField.getText().trim();
				int stock = Integer.parseInt(textField_1.getText().trim());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fecha = sdf.format(dateChooser.getDate());

				categoria_producto cat = (categoria_producto) comboBox.getSelectedItem();
				if (cat == null || cat.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Seleccione una categoría válida");
					return;
				}

				GestionBaseDatos g = new GestionBaseDatos();
				g.editarProducto(idProductoSeleccionado, nuevoNombre, stock, fecha, cat.getId());
				JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");

				idProductoSeleccionado = -1;
				DefaultTableModel modelo = g.buscarProductos("", "");
				table.setModel(modelo);
				ocultarColumnaID();

			} catch (validaciones.FechaVaciaException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.ProductoSoloLetrasException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.ProductoLongitudException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.CantidadSoloNumerosException2 ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (validaciones.CantidadLongitudException2 ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al editar: " + ex.getMessage());
			}
		}

		if (ob.equals(btnEliminar)) {
			if (idProductoSeleccionado == -1) {
				JOptionPane.showMessageDialog(null, "Seleccione un producto de la tabla primero");
				return;
			}

			int confirmar = JOptionPane.showConfirmDialog(null,
					"¿Seguro que desea eliminar este producto?",
					"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

			if (confirmar == JOptionPane.YES_OPTION) {
				try {
					GestionBaseDatos g = new GestionBaseDatos();
					g.eliminarProducto(idProductoSeleccionado);
					JOptionPane.showMessageDialog(null, "Producto eliminado");

					idProductoSeleccionado = -1;
					textField.setText("");
					textField_1.setText("");
					dateChooser.setDate(null);
					comboBox.setSelectedIndex(0);

					DefaultTableModel modelo = g.buscarProductos("", "");
					table.setModel(modelo);
					ocultarColumnaID();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
				}
			}
		}

		if (ob.equals(btnLimpiar)) {
			textField.setText("");
			textField_1.setText("");
			comboBox.setSelectedIndex(0);
			dateChooser.setDate(null);
		}

		if (ob.equals(btnVolver)) {
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

			if (cargoUsuario.equalsIgnoreCase("Gerente")) {
				frame.setContentPane(new gerente("Gerente"));
				frame.revalidate();
				frame.repaint();
			} else {
				frame.dispose();
				new Logeo().setVisible(true);
			}
		}

		if (ob.equals(btnSalir)) {
			System.exit(0);
		}
	}
}