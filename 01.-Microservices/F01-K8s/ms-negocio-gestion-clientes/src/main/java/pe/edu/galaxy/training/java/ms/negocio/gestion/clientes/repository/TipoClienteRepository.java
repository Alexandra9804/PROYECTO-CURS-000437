package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.TipoClienteEntity;

@Repository
public interface TipoClienteRepository extends JpaRepository<TipoClienteEntity, Long> {
}
