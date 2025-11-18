package view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Banco;
import model.Cliente;

public class GUIBanco extends JFrame {

    private Banco banco;
    private Cliente clienteActual;

    private JTextField txtId, txtNombre, txtCorreo;
    private JTextArea areaSalida;

    public GUIBanco() {
        String nombre = JOptionPane.showInputDialog(
            null,
            "Ingrese el nombre del banco:",
            "Nombre del Banco",
            JOptionPane.QUESTION_MESSAGE
        );

        if (nombre == null || nombre.trim().equals("")) {
            nombre = "Banco Sin Nombre";
        }

        banco = new Banco(nombre);

        setTitle("Sistema Bancario - " + nombre);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setBackground(new Color(0, 102, 204));
        panelHeader.setPreferredSize(new Dimension(600, 60));

        JLabel lblHeader = new JLabel("Bienvenido a " + nombre, SwingConstants.CENTER);
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 22));
        panelHeader.add(lblHeader, BorderLayout.CENTER);

        JPanel panelForm = new JPanel(new GridLayout(3, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtCorreo = new JTextField();

        panelForm.add(new JLabel("ID (9 dígitos):"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nombre del cliente:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Correo electrónico:"));
        panelForm.add(txtCorreo);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelHeader, BorderLayout.NORTH);
        panelSuperior.add(panelForm, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultados"));
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 10, 10));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnListar = new JButton("Listar Clientes");
        JButton btnRegistrarProducto = new JButton("Registrar Producto");
        JButton btnVerProductosCliente = new JButton("Productos del Cliente");
        JButton btnVerTodosProductos = new JButton("Todos los Productos");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnListar);
        panelBotones.add(btnRegistrarProducto);
        panelBotones.add(btnVerProductosCliente);
        panelBotones.add(btnVerTodosProductos);

        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nombreC = txtNombre.getText();
                String correo = txtCorreo.getText();
                banco.agregarCliente(id, nombreC, correo);
                areaSalida.append("Cliente agregado.\n");
            } catch (Exception ex) {
                areaSalida.append("Error: datos inválidos.\n");
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                int confirm = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    banco.eliminarCliente(id);
                    areaSalida.append("Cliente eliminado.\n");
                }
            } catch (Exception ex) {
                areaSalida.append("Error eliminando cliente.\n");
            }
        });

        btnConsultar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                clienteActual = banco.consultarClienteId(id);
                if (clienteActual == null) {
                    areaSalida.append("Cliente no encontrado.\n");
                } else {
                    areaSalida.append("Cliente consultado:\n" + clienteActual + "\n");
                }
            } catch (Exception ex) {
                areaSalida.append("Error consultando cliente.\n");
            }
        });

        btnListar.addActionListener(e -> {
            areaSalida.append("\n--- Lista de clientes ---\n");
            banco.consultarClientes();
        });

        btnRegistrarProducto.addActionListener(e -> {
            if (clienteActual == null) {
                areaSalida.append("Debe consultar un cliente antes de registrar un producto.\n");
                return;
            }

            String tipo = JOptionPane.showInputDialog(this, "Tipo de producto (corriente, pactada, certificado):");
            String montoStr = JOptionPane.showInputDialog(this, "Monto:");
            String periodoStr = JOptionPane.showInputDialog(this, "Periodo (días):");
            String moneda = JOptionPane.showInputDialog(this, "Moneda (colones / dolares):");

            try {
                double monto = Double.parseDouble(montoStr);
                int periodo = Integer.parseInt(periodoStr);
                banco.registrarProductoBancario(tipo, monto, periodo, moneda);
                areaSalida.append("Producto registrado para cliente " + clienteActual.getNombre() + ".\n");
            } catch (Exception ex) {
                areaSalida.append("Error registrando producto.\n");
            }
        });

        btnVerProductosCliente.addActionListener(e -> {
            if (clienteActual == null) {
                areaSalida.append("Debe consultar un cliente primero.\n");
                return;
            }
            areaSalida.append("\n--- Productos del cliente " + clienteActual.getNombre() + " ---\n");
            banco.consultarProductosBancarios();
        });

        btnVerTodosProductos.addActionListener(e -> {
            areaSalida.append("\n--- Todos los productos registrados ---\n");
            banco.consultarProductosBancarios();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GUIBanco();
    }
}