package dano.ZonaFit.repositorio;

import dano.ZonaFit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
