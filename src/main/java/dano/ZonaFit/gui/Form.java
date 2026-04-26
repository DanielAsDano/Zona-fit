package dano.ZonaFit.gui;

import dano.ZonaFit.modelo.Cliente;
import dano.ZonaFit.servicio.IClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Slf4j
@Component
public class Form extends JFrame {

    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    IClienteServicio clienteservicio;
    private DefaultTableModel tablaModeloClientes;
    private Integer idCliente;

    @Autowired
    public Form(IClienteServicio clienteservicio){
        this.clienteservicio = clienteservicio;
        inicializar();
        guardarButton.addActionListener(e -> guardarCliente());
        eliminarButton.addActionListener(e -> eliminarCliente());
        limpiarButton.addActionListener(e -> limpiarFormulario());
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });


    }

    private void inicializar(){
        setContentPane(panelPrincipal);
        setSize(720,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloClientes = new DefaultTableModel(0, 4){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        String[] headers = {"ID", "Nombre", "Apellido", "Membresia"};
        tablaModeloClientes.setColumnIdentifiers(headers);
        clientesTabla = new JTable(tablaModeloClientes);
        clientesTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarClientes();
    }

    private void listarClientes(){
        this.tablaModeloClientes.setRowCount(0);
        List<Cliente> clientes = clienteservicio.listarClientes();
        clientes.forEach(cliente -> {
            Object[] renglon = {cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getMembresia()};

            tablaModeloClientes.addRow(renglon);
        });
    }

    private void guardarCliente(){
        if (nombreTexto.getText().equals("")){
            mostrarMensaje("Proporciona un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if (membresiaTexto.getText().equals("")){
            mostrarMensaje("Proporciona una membresia");
            membresiaTexto.requestFocusInWindow();
            return;
        }

        String nombre = nombreTexto.getText();
        String apellido = apellidoTexto.getText();
        Integer membresia = Integer.parseInt(membresiaTexto.getText());

        Cliente cliente = new Cliente(this.idCliente, nombre, apellido, membresia);

        if (this.idCliente == null){
            mostrarMensaje("Cliente Agregado");
        }else {
            mostrarMensaje("Cliente Actualizado");
        }

        clienteservicio.guardarCliente(cliente);
        limpiarFormulario();
        listarClientes();
    }

    private void eliminarCliente(){
        int renglon = clientesTabla.getSelectedRow();

        if (renglon != -1){
            String clienteIdStr = clientesTabla.getModel().getValueAt(renglon, 0).toString();
            idCliente = Integer.parseInt(clienteIdStr);
            Cliente cliente = new Cliente();
            cliente.setId(idCliente);
            clienteservicio.eliminarCliente(cliente);
            mostrarMensaje("Cliente eliminado");
            limpiarFormulario();
            listarClientes();
        }else {
            mostrarMensaje("Selecciona un cliente");
        }
    }

    private void cargarClienteSeleccionado(){
        int renglon = clientesTabla.getSelectedRow();
        if (renglon != -1){
            String id = clientesTabla.getModel().getValueAt(renglon, 0).toString();
            this.idCliente = Integer.parseInt(id);

            String nombre = clientesTabla.getModel().getValueAt(renglon, 1).toString();
            this.nombreTexto.setText(nombre);

            String apellido = clientesTabla.getModel().getValueAt(renglon, 2).toString();
            this.apellidoTexto.setText(apellido);

            String memebresia = clientesTabla.getModel().getValueAt(renglon, 3).toString();
            this.membresiaTexto.setText(memebresia);
        }
    }

    private void limpiarFormulario(){
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaTexto.setText("");

        this.idCliente = null;

        clientesTabla.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
