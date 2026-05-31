import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
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

public class gerente extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton btniNVENTARIO;
    private JButton btnSOLICITUD;
    private JButton btnVolver;
    private JButton btnSalir;
    private String cargoUsuario;
    private DefaultTableModel modeloProducto;
    private DefaultTableModel modeloCompra;

    /**
     * Create the panel.
     */
    public gerente(String cargo) {
        this.cargoUsuario = cargo;
        
        // Configuración del panel base para soportar la imagen de fondo de la Licorería
        setLayout(null);
        setSize(560, 720);
        setOpaque(false); // Permite ver el fondo general de la aplicación

        // PANEL CENTRAL TRANSLÚCIDO (Efecto Cristal / Píldora Contenedora)
        JPanel panelMenu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Esquinas redondeadas suaves
                g2.setColor(new Color(255, 255, 255, 38)); // Borde sutil blanco translúcido
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panelMenu.setLayout(null);
        panelMenu.setBackground(new Color(0, 0, 0, 180)); // Fondo oscuro semitransparente 
        panelMenu.setOpaque(false);
        panelMenu.setBounds(97, 45, 370, 590); // Centrado en las dimensiones 560x720
        add(panelMenu);

        // TÍTULO DEL PANEL
        JLabel lblTitulo = new JLabel("Licoreria CSA");
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setBounds(56, 26, 237, 35);
        panelMenu.add(lblTitulo);
        
        JLabel lblSubtitulo = new JLabel("Panel de Control - " + cargoUsuario);
        lblSubtitulo.setHorizontalAlignment(JLabel.CENTER);
        lblSubtitulo.setForeground(new Color(255, 255, 255, 180));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setBounds(30, 65, 290, 20);
        panelMenu.add(lblSubtitulo);

        // SEPARADOR ESTILIZADO
        JSeparator separator = new JSeparator();
        separator.setBounds(40, 95, 270, 2);
        separator.setForeground(new Color(255, 255, 255, 40));
        panelMenu.add(separator);

        // Colores y transparencias para el diseño de píldora
        Color translucidoBase  = new Color(255, 255, 255, 25);
        Color translucidoHover = new Color(255, 255, 255, 50);

        // BOTÓN: REGISTRAR INVENTARIO
        btniNVENTARIO = new JButton("REGISTRAR INVENTARIO") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight()); // Píldora diseño
                g2.setColor(new Color(255, 255, 255, 50));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        configurarBotonEstilo(btniNVENTARIO, translucidoBase, translucidoHover);
        btniNVENTARIO.setBounds(40, 150, 270, 45);
        panelMenu.add(btniNVENTARIO);

        // BOTÓN: SOLICITUD DE COMPRA
        btnSOLICITUD = new JButton("SOLICITUD DE COMPRA") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight()); // Píldora diseño
                g2.setColor(new Color(255, 255, 255, 50));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        configurarBotonEstilo(btnSOLICITUD, translucidoBase, translucidoHover);
        btnSOLICITUD.setBounds(40, 220, 270, 45);
        panelMenu.add(btnSOLICITUD);

        // BOTONES INFERIORES DE ACCIÓN (Estilo cuadriculado con esquinas suavizadas)
        Color btranslucidoBase  = new Color(255, 255, 255, 35);
        Color btranslucidoHover = new Color(255, 255, 255, 55);

        // BOTÓN VOLVER
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
        btnVolver.setIcon(new ImageIcon(gerente.class.getResource("/imagenes/Volver.png")));
        btnVolver.setBounds(40, 480, 110, 46);
        configurarBotonIcono(btnVolver, btranslucidoBase, btranslucidoHover, "/imagenes/Volver.png");
        panelMenu.add(btnVolver);

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
        btnSalir.setIcon(new ImageIcon(gerente.class.getResource("/imagenes/salida.png")));
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnSalir.setBounds(200, 480, 110, 46);
        configurarBotonIcono(btnSalir, btranslucidoBase, btranslucidoHover, "/imagenes/salida.png");
        panelMenu.add(btnSalir);

        // CAPA DE FONDO
        try {
            java.net.URL urlFondo = gerente.class.getResource("/imagenes/fondo6.png");
            if (urlFondo != null) {
                ImageIcon icono = new ImageIcon(urlFondo);
                Image imgEscalada = icono.getImage().getScaledInstance(560, 720, Image.SCALE_SMOOTH);
                JLabel lblFondo = new JLabel(new ImageIcon(imgEscalada));
                lblFondo.setBounds(0, 0, 560, 720);
                add(lblFondo);
                setComponentZOrder(lblFondo, getComponentCount() - 1);
            }
        } catch (Exception e) {
            System.out.println("No se encontró la imagen de fondo en el panel de gerente");
        }
    }

    /**
     * Helper para configurar el estilo de los botones de menú estilo píldora 
     */
    private void configurarBotonEstilo(JButton boton, Color base, Color hover) {
        boton.setBackground(base);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(this);
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { boton.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { boton.setBackground(base);  }
        });
    }

    /**
     * Helper para configurar botones que utilizan exclusivamente imágenes/iconos
     */
    private void configurarBotonIcono(JButton boton, Color base, Color hover, String rutaIcono) {
        boton.setBackground(base);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(this);
        
        try {
            java.net.URL urlIcono = gerente.class.getResource(rutaIcono);
            if (urlIcono != null) {
                boton.setIcon(new ImageIcon(urlIcono));
            } else {
                System.out.println("No se pudo cargar la imagen: " + rutaIcono);
            }
        } catch (Exception e) {
            System.out.println("Error en la ruta del icono: " + e.getMessage());
        }

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { boton.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { boton.setBackground(base);  }
        });
    }

    // ── GESTIÓN DE EVENTOS Y CONEXIONES
    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if (ob.equals(btnVolver)) {
            Logeo ventana = new Logeo();
            ventana.setVisible(true);

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.dispose();
            }
        }
        
        if (ob.equals(btnSalir)) {
            System.exit(0);
        }

        if (ob.equals(btniNVENTARIO)) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.setResizable(true);
                frame.setSize(560, 720);
                frame.setLocationRelativeTo(null);
                frame.setContentPane(new producto(modeloProducto, "Gerente"));
                frame.revalidate();
                frame.repaint();
            }
        }

        if (ob.equals(btnSOLICITUD)) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.setResizable(true);
                frame.setSize(560, 720);
                frame.setLocationRelativeTo(null);
                frame.setContentPane(new solicitud_compra(modeloCompra));
                frame.revalidate();
                frame.repaint();
            }
        }
    }
}