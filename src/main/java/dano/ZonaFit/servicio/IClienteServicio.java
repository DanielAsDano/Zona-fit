package dano.ZonaFit.servicio;

import dano.ZonaFit.modelo.Cliente;

import java.util.List;

public interface IClienteServicio {
    public List<Cliente> listarClientes();
    public Cliente buscarClientePorId(Integer id);
    public void guardarCliente(Cliente cliente);
    public void eliminarCliente(Cliente cliente);
}
