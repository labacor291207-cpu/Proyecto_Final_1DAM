import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Logeo extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    
    // Componentes de la interfaz gráfica
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JComboBox<String> cbCargo;
    private JButton btnEntrar;
    private JButton btnLimpiar;
    private JButton btnSalir;
    private JPanel panelPrincipal;
    private DefaultTableModel modeloProducto;

    // Conexión con el servidor backend (XAMPP)
    private final String urlServidor = "http://localhost/licoreria/verificar_login.php";

    /**
     * Constructor principal de la ventana de Inicio de Sesión.
     */
    public Logeo() {
        // Configuración básica de la ventana JFrame
        setTitle("Licoreria CSA");
        setSize(560, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel contenedor base
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(20, 20, 20));
        setContentPane(panelPrincipal);

        // PANEL DE LOGIN CENTRAL (Efecto Cristal / Translúcido)
        JPanel panelLogin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Bordes redondeados suaves
                g2.setColor(new Color(255, 255, 255, 38)); // Borde fino blanco semitransparente
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panelLogin.setLayout(null);
        panelLogin.setBackground(new Color(0, 0, 0, 180)); // Oscuro translúcido 
        panelLogin.setOpaque(false);
        panelLogin.setBounds(97, 45, 370, 590);
        panelPrincipal.add(panelLogin);

        // TÍTULO PRINCIPAL
        JLabel lblTitulo = new JLabel("Licoreria CSA");
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setBounds(56, 26, 237, 35);
        panelLogin.add(lblTitulo);

        // CAMPO: NOMBRE DE USUARIO
        JLabel lblNombre = new JLabel("Nombre de usuario");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
        lblNombre.setBounds(40, 100, 294, 20);
        panelLogin.add(lblNombre);

        txtNombre = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 20));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight()); // Forma de píldora
                g2.dispose();
                super.paintComponent(g);
            }
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBorder() instanceof javax.swing.border.LineBorder
                        ? ((javax.swing.border.LineBorder) getBorder()).getLineColor()
                        : new Color(255, 255, 255, 40));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                g2.dispose();
            }
        };
        txtNombre.setOpaque(false);
        txtNombre.setForeground(Color.WHITE);
        txtNombre.setCaretColor(Color.WHITE);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNombre.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        txtNombre.setBounds(40, 125, 294, 42);
        panelLogin.add(txtNombre);

        // CAMPO: CONTRASEÑA
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setForeground(Color.WHITE);
        lblContrasena.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        lblContrasena.setBounds(40, 185, 294, 20);
        panelLogin.add(lblContrasena);

        txtPassword = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 20));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight()); // Forma de píldora
                g2.dispose();
                super.paintComponent(g);
            }
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBorder() instanceof javax.swing.border.LineBorder
                        ? ((javax.swing.border.LineBorder) getBorder()).getLineColor()
                        : new Color(255, 255, 255, 40));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                g2.dispose();
            }
        };
        txtPassword.setOpaque(false);
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        txtPassword.setBounds(40, 210, 294, 42);
        panelLogin.add(txtPassword);

        // SELECCIÓN DE CARGO
        JLabel lblCargo = new JLabel("Seleccionar cargo");
        lblCargo.setForeground(Color.WHITE);
        lblCargo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
        lblCargo.setBounds(40, 270, 294, 20);
        panelLogin.add(lblCargo);

        // CONTENEDOR PÍLDORA DEL COMBOBOX
        JPanel pildoraCargo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 20));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                g2.setColor(new Color(255, 255, 255, 40));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                
                // Pintar flecha del indicador desplegable a blanco
                g2.setColor(new Color(255, 255, 255, 180));
                int cx = getWidth() - 20;
                int cy = getHeight() / 2;
                int[] xp = {cx - 5, cx + 5, cx};
                int[] yp = {cy - 3, cy - 3, cy + 4};
                g2.fillPolygon(xp, yp, 3);
                g2.dispose();
            }
        };
        pildoraCargo.setLayout(null);
        pildoraCargo.setOpaque(false);
        pildoraCargo.setBounds(40, 295, 294, 42);
        panelLogin.add(pildoraCargo);

        String[] opcionesCargo = {"-- Seleccione un cargo --", "Gerente", "Empleado"};
        cbCargo = new JComboBox<>(opcionesCargo);

        // Personalización JComboBox para respetar la transparencia
        cbCargo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
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
                javax.swing.ListCellRenderer renderer = comboBox.getRenderer();
                java.awt.Component c = renderer.getListCellRendererComponent(
                        listBox, comboBox.getSelectedItem(), -1, false, false);
                c.setFont(comboBox.getFont());
                if (c instanceof javax.swing.JComponent) {
                    ((javax.swing.JComponent) c).setOpaque(false);
                }
                currentValuePane.paintComponent(g, c, comboBox,
                        bounds.x, bounds.y, bounds.width, bounds.height, c instanceof JButton);
            }
            @Override
            public void paintCurrentValueBackground(Graphics g, java.awt.Rectangle bounds, boolean hasFocus) {
               
            }
            @Override
            protected javax.swing.plaf.basic.ComboPopup createPopup() {
                javax.swing.plaf.basic.BasicComboPopup popup =
                        (javax.swing.plaf.basic.BasicComboPopup) super.createPopup();
                popup.getList().setBackground(new Color(30, 30, 30));
                popup.getList().setForeground(Color.WHITE);
                popup.getList().setSelectionBackground(new Color(0, 128, 255));
                popup.getList().setFont(new Font("Segoe UI", Font.PLAIN, 14));
                popup.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 38), 1));
                return popup;
            }
        });

        cbCargo.setRenderer(new javax.swing.plaf.basic.BasicComboBoxRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setOpaque(index != -1);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                setForeground(Color.WHITE);
                return this;
            }
        });

        cbCargo.setOpaque(false);
        cbCargo.setBackground(new Color(0, 0, 0, 0));
        cbCargo.setForeground(Color.WHITE);
        cbCargo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbCargo.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 30));
        cbCargo.setBounds(0, 0, 294, 42);
        pildoraCargo.add(cbCargo);

        // CONFIGURACIÓN DE BOTONES INFERIORES TRANSLÚCIDOS
        Color translucidoBase  = new Color(255, 255, 255, 35);
        Color translucidoHover = new Color(255, 255, 255, 55);

        // BOTÓN LIMPIAR
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
        try { btnLimpiar.setIcon(new ImageIcon(Logeo.class.getResource("/imagenes/Clear.png"))); } catch (Exception ignored) {}
        configurarBotonEstilo(btnLimpiar, translucidoBase, translucidoHover, 10);
        panelLogin.add(btnLimpiar);

        // BOTÓN ENTRAR
        btnEntrar = new JButton("") {
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
        try { btnEntrar.setIcon(new ImageIcon(Logeo.class.getResource("/imagenes/Open v2.png"))); } catch (Exception ignored) {}
        configurarBotonEstilo(btnEntrar, translucidoBase, translucidoHover, 130);
        panelLogin.add(btnEntrar);

        // BOTÓN SALIR
        btnSalir = new JButton("") {
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
        try { btnSalir.setIcon(new ImageIcon(Logeo.class.getResource("/imagenes/salida.png"))); } catch (Exception ignored) {}
        configurarBotonEstilo(btnSalir, translucidoBase, translucidoHover, 250);
        panelLogin.add(btnSalir);

        // CAPA DE IMAGEN DE FONDO
        try {
            ImageIcon icono = new ImageIcon(Logeo.class.getResource("/imagenes/fondo6.png"));
            Image imgEscalada = icono.getImage().getScaledInstance(560, 720, Image.SCALE_SMOOTH);
            JLabel lblFondo = new JLabel(new ImageIcon(imgEscalada));
            lblFondo.setBounds(-16, 0, 560, 720);
            panelPrincipal.add(lblFondo);
            panelPrincipal.setComponentZOrder(lblFondo, panelPrincipal.getComponentCount() - 1);
        } catch (Exception e) {
            System.out.println("No se encontró la imagen de fondo en la ruta especificada.");
        }
    }

    /**
     * Helper modular encargado de aplicar los estilos comunes, dimensiones, coordenadas x / y efectos Hover de entrada y salida.
     */
    private void configurarBotonEstilo(JButton boton, Color base, Color hover, int xPos) {
        boton.setBackground(base);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBounds(xPos, 385, 110, 46);
        boton.addActionListener(this);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { boton.setBackground(hover); }
            @Override
            public void mouseExited(MouseEvent e)  { boton.setBackground(base);  }
        });
    }

    // ── LÓGICA DE CONTROLADORES Y ACCIONES ──
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnSalir) {
            System.exit(0);
        }

        if (source == btnLimpiar) {
            txtNombre.setText("");
            txtPassword.setText("");
            cbCargo.setSelectedIndex(0);
            txtNombre.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            txtPassword.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        }

        if (source == btnEntrar) {
            String nombre = txtNombre.getText();
            String pass   = new String(txtPassword.getPassword());
            String cargo  = cbCargo.getSelectedItem().toString();

            //visual de campos antes de evaluar
            txtNombre.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            txtPassword.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

            // Validaciones obligatorias de campos vacíos
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario es obligatorio");
                txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                return;
            }
            if (pass.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La contraseña es obligatoria");
                txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                return;
            }
            if (cargo.equals("-- Seleccione un cargo --")) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un cargo");
                return;
            }

            // Aplicar bordes de validación
            txtNombre.setBorder(BorderFactory.createLineBorder(new Color(60, 190, 110), 2));
            txtPassword.setBorder(BorderFactory.createLineBorder(new Color(60, 190, 110), 2));

            btnEntrar.setEnabled(false);

            final String nombreFinal = nombre.trim();
            final String passFinal   = pass.trim();
            final String cargoFinal  = cargo.trim();

            // Petición al servidor HTTP 
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String respuesta = llamarServidor(nombreFinal, passFinal, cargoFinal);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            procesarRespuesta(respuesta);
                        }
                    });
                }
            }).start();
        }
    }

    /**
     * Realiza el envío de datos cifrados en formato mediante un método POST.
     */
    private String llamarServidor(String nombre, String pass, String cargo) {
        try {
            URL url = new URL(urlServidor);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setDoOutput(true);
            conexion.setConnectTimeout(5000);
            conexion.setReadTimeout(5000);
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String datos = "nombre=" + URLEncoder.encode(nombre, "UTF-8")
                         + "&clave="  + URLEncoder.encode(pass,   "UTF-8")
                         + "&cargo="  + URLEncoder.encode(cargo,  "UTF-8");

            OutputStream salida = conexion.getOutputStream();
            salida.write(datos.getBytes("UTF-8"));
            salida.flush();
            salida.close();

            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
            String respuesta = lector.readLine();
            lector.close();

            System.out.println("Respuesta del servidor: " + respuesta);
            return respuesta != null ? respuesta.trim() : "ERROR_CONEXION";

        } catch (Exception ex) {
            System.out.println("Error al conectar con el backend PHP: " + ex.getMessage());
            return "ERROR_CONEXION";
        }
    }

    
      //respuesta de red del servidor XAMPP.
    private void procesarRespuesta(String respuesta) {
        btnEntrar.setEnabled(true);

        if (respuesta.equals("Gerente") || respuesta.equals("Empleado")) {
            abrirPantallaInicio(respuesta);
        } else if (respuesta.equals("ERROR_CONEXION")) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar al servidor.\nComprueba que XAMPP esté activo.");
        } else {
            txtNombre.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            JOptionPane.showMessageDialog(null, "Usuario, contraseña o cargo incorrectos");
        }
    }

   
     //Instancia los paneles correspondientes según los roles del usuario de la BD.
     
    private void abrirPantallaInicio(String cargo) {
        JFrame ventanaPrincipal = new JFrame();
        ventanaPrincipal.setTitle("Licoreria CSA - Panel Principal");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(560, 720);
        ventanaPrincipal.setResizable(true);
        ventanaPrincipal.setLocationRelativeTo(null);

        try {
            JPanel panel;
            if (cargo.equalsIgnoreCase("Gerente")) {
                panel = new gerente(cargo);
            } else {
                panel = new producto(new DefaultTableModel(), cargo);
            }
            ventanaPrincipal.setContentPane(panel);
            ventanaPrincipal.setVisible(true);
            this.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo abrir la pantalla principal del panel");
        }
    }
}