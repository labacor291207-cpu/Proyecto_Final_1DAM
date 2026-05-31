import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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

public class solicitud_compra extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JLabel lblSolicitud;
    private JLabel lblFecha;
    private JLabel lblProducto;
    private JLabel lblCantidad;
    private JLabel lblTipo;
    private JLabel lblNombreG;
    private JLabel lblEstado;
    private JComboBox<String> cbTIPO;
    private JComboBox<String> cbESTADO;
    private JButton btnVolver;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnAgregar;
    private JDateChooser dateChooser;
    private JTable table;
    private DefaultTableModel modeloCompra;
    private JScrollPane scrollPane;
    private JButton btnVerTodo;
    private DefaultTableModel modeloSolicitud;

    public solicitud_compra(DefaultTableModel modeloCompra) {
        setOpaque(false);

        this.modeloCompra = new DefaultTableModel(
                new String[]{"Nombre", "Cantidad", "Tipo"}, 0
        );
        
        this.modeloSolicitud = new DefaultTableModel(
                new String[]{"ID Solicitud", "Fecha", "Estado"}, 0
        );

        setLayout(null);
        setSize(560, 720);

        Color translucidoBase  = new Color(255, 255, 255, 35);
        Color translucidoHover = new Color(255, 255, 255, 55);

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

        lblSolicitud = new JLabel("SOLICITUD DE COMPRA");
        lblSolicitud.setForeground(Color.WHITE);
        lblSolicitud.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblSolicitud.setBounds(130, 15, 260, 36);
        panelContenedor.add(lblSolicitud);

        lblFecha = new JLabel("FECHA:");
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblFecha.setBounds(36, 75, 71, 19);
        panelContenedor.add(lblFecha);

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
        pildoraFecha.setBounds(36, 97, 155, 42);
        panelContenedor.add(pildoraFecha);

        // -- IMPLEMENTACIÓN DEL CALENDARIO BLINDADO EN BLANCO --
        com.toedter.calendar.JTextFieldDateEditor txtEditor = new com.toedter.calendar.JTextFieldDateEditor() {
            private static final long serialVersionUID = 1L;
            @Override
            public void setForeground(Color c) {
                super.setForeground(Color.WHITE);
            }
            @Override
            public void setDisabledTextColor(Color c) {
                super.setDisabledTextColor(Color.WHITE);
            }
            @Override
            public java.awt.Color getForeground() {
                return Color.WHITE;
            }
        };

        txtEditor.setOpaque(false);
        txtEditor.setCaretColor(Color.WHITE);
        txtEditor.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        txtEditor.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        dateChooser = new JDateChooser(txtEditor);
        dateChooser.setOpaque(false);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        com.toedter.calendar.JCalendar jcal = dateChooser.getJCalendar();
        jcal.setBackground(new Color(30, 30, 30));
        jcal.getDayChooser().setWeekdayForeground(Color.WHITE);
        jcal.getDayChooser().setSundayForeground(Color.WHITE);
        jcal.getDayChooser().setDecorationBackgroundColor(new Color(45, 45, 45));

        dateChooser.addPropertyChangeListener(evt -> {
            txtEditor.setForeground(Color.WHITE);
            txtEditor.repaint();
            try {
                for (java.awt.Component comp : jcal.getDayChooser().getDayPanel().getComponents()) {
                    comp.setForeground(Color.WHITE);
                }
            } catch (Exception ignored) {}
        });

        dateChooser.setBounds(5, 2, 145, 38);
        pildoraFecha.add(dateChooser);
        // --------------------------------------------------------

        lblNombreG = new JLabel("N° DE SOLICITUD:");
        lblNombreG.setForeground(Color.WHITE);
        lblNombreG.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNombreG.setBounds(210, 75, 120, 19);
        panelContenedor.add(lblNombreG);

        textField_4 = crearTextFieldEstilizado();
        textField_4.setBounds(210, 100, 140, 35);
        textField_4.setEditable(false);
        panelContenedor.add(textField_4);

        btnLimpiar = new JButton("");
        btnLimpiar.setBounds(370, 100, 45, 35);
        try { btnLimpiar.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/Limpiar.png"))); } catch(Exception e){}
        configurarBotonIcono(btnLimpiar, translucidoBase, translucidoHover);
        panelContenedor.add(btnLimpiar);

        lblProducto = new JLabel("PRODUCTO:");
        lblProducto.setForeground(Color.WHITE);
        lblProducto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblProducto.setBounds(36, 150, 100, 18);
        panelContenedor.add(lblProducto);

        textField_2 = crearTextFieldEstilizado();
        textField_2.setBounds(36, 175, 130, 35);
        panelContenedor.add(textField_2);

        lblCantidad = new JLabel("CANTIDAD:");
        lblCantidad.setForeground(Color.WHITE);
        lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCantidad.setBounds(185, 150, 83, 18);
        panelContenedor.add(lblCantidad);

        textField_3 = crearTextFieldEstilizado();
        textField_3.setBounds(185, 175, 90, 35);
        panelContenedor.add(textField_3);

        lblTipo = new JLabel("TIPO:");
        lblTipo.setForeground(Color.WHITE);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTipo.setBounds(290, 150, 71, 19); // Ajuste X para equilibrar ancho
        panelContenedor.add(lblTipo);

        JPanel pildoraTipo = crearContenedorPildoraCombo();
        // AUMENTO DEL ANCHO DE 130 A 150 PARA EVITAR QUE SE CORTE
        pildoraTipo.setBounds(285, 175, 150, 35); 
        panelContenedor.add(pildoraTipo);

        cbTIPO = new JComboBox<>(new String[]{"Selecciona el tipo", "REFRESCO", "LICOR", "OTROS"});
        estilarComboBox(cbTIPO);
        cbTIPO.setBounds(0, 0, 150, 35);
        pildoraTipo.add(cbTIPO);

        btnAgregar = new JButton("");
        btnAgregar.setBounds(445, 175, 45, 35); // Reajuste mínimo del botón agregar
        try { btnAgregar.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/Agregar.png"))); } catch(Exception e){}
        configurarBotonIcono(btnAgregar, translucidoBase, translucidoHover);
        panelContenedor.add(btnAgregar);

        lblEstado = new JLabel("ESTADO:");
        lblEstado.setForeground(Color.WHITE);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEstado.setBounds(36, 225, 100, 19);
        panelContenedor.add(lblEstado);

        JPanel pildoraEstado = crearContenedorPildoraCombo();
        pildoraEstado.setBounds(36, 250, 160, 35);
        panelContenedor.add(pildoraEstado);

        cbESTADO = new JComboBox<>(new String[]{"Selecciona estado", "PENDIENTE", "APROBADO", "RECIBIDO"});
        estilarComboBox(cbESTADO);
        cbESTADO.setBounds(0, 0, 160, 35);
        pildoraEstado.add(cbESTADO);

        table = new JTable(this.modeloCompra);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(36, 305, 430, 200);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelContenedor.add(scrollPane);
        
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBackground(new Color(0, 0, 0, 0));
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 40), 1));

        table.setOpaque(false);
        if (table.getDefaultRenderer(Object.class) instanceof javax.swing.table.DefaultTableCellRenderer) {
            ((javax.swing.table.DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
        }
        table.setBackground(new Color(0, 0, 0, 0)); 
        table.setForeground(Color.WHITE);           
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(25);                     
        table.setGridColor(new Color(255, 255, 255, 30)); 
        table.setSelectionBackground(new Color(255, 255, 255, 45)); 
        table.setSelectionForeground(Color.WHITE);

        table.getTableHeader().setReorderingAllowed(false); 
        table.getTableHeader().setBackground(new Color(255, 255, 255, 35)); 
        table.getTableHeader().setForeground(Color.WHITE); 
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 255, 255, 60)));

        btnVolver = new JButton("") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                g2.setColor(new Color(255, 255, 255, 60));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        try { btnVolver.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/Volver.png"))); } catch(Exception e){}
        // SE REDISTRIBUYEN BOTONES Y AMPLÍAN TAMAÑOS PARA NO CORTAR TEXTOS
        btnVolver.setBounds(30, 540, 95, 40);
        configurarBotonIcono(btnVolver, translucidoBase, translucidoHover);
        panelContenedor.add(btnVolver);

        btnGuardar = new JButton("GUARDAR") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                g2.setColor(new Color(255, 255, 255, 60));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        try { btnGuardar.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/Guardar.png"))); } catch(Exception e){}
        btnGuardar.setBounds(140, 540, 140, 40);
        configurarBotonIcono(btnGuardar, translucidoBase, translucidoHover);
        panelContenedor.add(btnGuardar);

        btnVerTodo = new JButton("VER TODOS") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                g2.setColor(new Color(255, 255, 255, 60));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        try { btnVerTodo.setIcon(new ImageIcon(solicitud_compra.class.getResource("/imagenes/VerTodo.png"))); } catch(Exception e){}
        // SE AMPLÍA DE 120 A 145 EL ANCHO PARA EVITAR "Ver t..."
        btnVerTodo.setBounds(295, 540, 145, 40);
        configurarBotonIcono(btnVerTodo, translucidoBase, translucidoHover);
        panelContenedor.add(btnVerTodo);

        try {
            ImageIcon icono = new ImageIcon(solicitud_compra.class.getResource("/imagenes/fondo9.jpeg"));
            Image imgEscalada = icono.getImage().getScaledInstance(560, 720, Image.SCALE_SMOOTH);
            JLabel lblFondo = new JLabel(new ImageIcon(imgEscalada));
            lblFondo.setBounds(0, 0, 560, 720);
            add(lblFondo);
            setComponentZOrder(lblFondo, getComponentCount() - 1);
        } catch (Exception e) {
            System.out.println("No se encontró la imagen de fondo en solicitud_compra");
        }
    }

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
        field.setDisabledTextColor(Color.WHITE); // FUERZA BLANCO AUNQUE ESTÉ BLOQUEADO
        field.setCaretColor(Color.WHITE);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        return field;
    }

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

    private void estilarComboBox(JComboBox<String> combo) {
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
        combo.setBackground(new Color(0,0,0,0));
        combo.setForeground(Color.WHITE);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 25));
    }

    private void configurarBotonIcono(JButton boton, Color base, Color hover) {
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

    private void validarCampos() throws
            validaciones.FechaVaciaException,
            validaciones.ProductoSoloLetrasException,
            validaciones.ProductoLongitudException,
            validaciones.CantidadSoloNumerosException,
            validaciones.CantidadLongitudException,
            validaciones.TipoObligatorioException,
            validaciones.NumeroSolicitudObligatorioException,
            validaciones.EstadoObligatorioException {

        String producto = textField_2.getText().trim();
        String cantidad = textField_3.getText().trim();
        String tipo     = cbTIPO.getSelectedItem().toString();
        String estado   = cbESTADO.getSelectedItem().toString();

        if (dateChooser.getDate() == null)
            throw new validaciones.FechaVaciaException();

        if (!producto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
            throw new validaciones.ProductoSoloLetrasException();

        if (producto.length() > 15)
            throw new validaciones.ProductoLongitudException();

        if (!cantidad.matches("[0-9]+"))
            throw new validaciones.CantidadSoloNumerosException();

        if (cantidad.length() > 6)
            throw new validaciones.CantidadLongitudException();

        if (tipo.equals("Selecciona el tipo"))
            throw new validaciones.TipoObligatorioException();

        if (estado.equals("Selecciona estado"))
            throw new validaciones.EstadoObligatorioException();
    }
    
    private void validarCamposSinFecha() throws 
    validaciones.ProductoSoloLetrasException,
    validaciones.ProductoLongitudException,
    validaciones.CantidadSoloNumerosException,
    validaciones.CantidadLongitudException,
    validaciones.TipoObligatorioException {

        String producto = textField_2.getText().trim();
        String cantidad = textField_3.getText().trim();
        String tipo     = cbTIPO.getSelectedItem().toString();

        if (!producto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
            throw new validaciones.ProductoSoloLetrasException();

        if (producto.length() > 15)
            throw new validaciones.ProductoLongitudException();

        if (!cantidad.matches("[0-9]+"))
            throw new validaciones.CantidadSoloNumerosException();

        if (cantidad.length() > 6)
            throw new validaciones.CantidadLongitudException();

        if (tipo.equals("Selecciona el tipo"))
            throw new validaciones.TipoObligatorioException();
    }

    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if (ob.equals(btnVolver)) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new gerente("Gerente"));
            frame.revalidate();
            frame.repaint();
        }

        if (ob.equals(btnVerTodo)) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new contiene(modeloSolicitud));
            frame.revalidate();
            frame.repaint();
        }

        if (ob.equals(btnAgregar)) {
            try {
                validarCamposSinFecha();
                modeloCompra = (DefaultTableModel) table.getModel();

                String producto = textField_2.getText();
                String cantidad = textField_3.getText();
                String tipo = cbTIPO.getSelectedItem().toString();

                modeloCompra.addRow(new Object[]{producto, cantidad, tipo});
                JOptionPane.showMessageDialog(null, "Producto agregado");

            } catch (validaciones.ProductoSoloLetrasException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.ProductoLongitudException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.CantidadSoloNumerosException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.CantidadLongitudException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.TipoObligatorioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage());
            }
        }    
        
        if (ob.equals(btnGuardar)) {
            try {
                validarCampos();
                GestionBaseDatos g = new GestionBaseDatos();
                Date fechaDate = dateChooser.getDate();

                if (fechaDate == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione una fecha");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = sdf.format(fechaDate);
                String estado = cbESTADO.getSelectedItem().toString();

                int idGenerado = g.guardarSolicitud(fecha, estado);
                textField_4.setText(String.valueOf(idGenerado));

                for (int i = 0; i < table.getRowCount(); i++) {
                    String producto = table.getValueAt(i, 0).toString();
                    int cantidad = Integer.parseInt(table.getValueAt(i, 1).toString());
                    String tipo = table.getValueAt(i, 2).toString();

                    g.guardarDetalle(idGenerado, producto, cantidad, tipo);
                }

                JOptionPane.showMessageDialog(null, "Solicitud guardada con ID: " + idGenerado);

            } catch (validaciones.FechaVaciaException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.ProductoSoloLetrasException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.ProductoLongitudException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.CantidadSoloNumerosException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.CantidadLongitudException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.TipoObligatorioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.EstadoObligatorioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (validaciones.NumeroSolicitudObligatorioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar: " + ex.getMessage());
            }
        }

        if (ob.equals(btnLimpiar)) {
            int filaSeleccionada = table.getSelectedRow();

            if (filaSeleccionada != -1) {
                DefaultTableModel modelo = (DefaultTableModel) table.getModel();
                modelo.removeRow(filaSeleccionada);
            } else {
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
                cbTIPO.setSelectedIndex(0);
                cbESTADO.setSelectedIndex(0);
                dateChooser.setDate(null);
            }
        }
    }
}