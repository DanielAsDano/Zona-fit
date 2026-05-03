package dano.ZonaFit.controlador;

import dano.ZonaFit.modelo.Cliente;
import dano.ZonaFit.servicio.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    IClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.clientes = this.clienteServicio.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente() {
        this.clienteSeleccionado = new Cliente();
    }

    public void guardarCliente() {
        logger.info("cliente a guardar: " + this.clienteSeleccionado);
        if (this.clienteSeleccionado.getId() == null) {
            if (clienteSeleccionado.getId() == null) {
                clienteServicio.guardarCliente(clienteSeleccionado);
                clientes.add(clienteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente agregado"));
            }
        }else {
            clienteServicio.guardarCliente(clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Actualizado"));
        }
        PrimeFaces.current().executeInitScript("PF('ventanaModalCliente').hide()");
        PrimeFaces.current().ajax().update("forma-clientes:mensajes", "forma-clientes:clientes-tabla");
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente(){
        logger.info("Cliente a eliminar: " + clienteSeleccionado);
        this.clienteServicio.eliminarCliente(clienteSeleccionado);
        clientes.remove(clienteSeleccionado);
        clienteSeleccionado=null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente eliminado"));
        PrimeFaces.current().ajax().update("forma-clientes:mensajes", "forma-clientes:clientes-tabla");
    }
}
