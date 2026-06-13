package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.document.AlquilerValidacionMessageDocument;

public interface AlquilerValidacionMessageRepository
        extends MongoRepository<AlquilerValidacionMessageDocument, String> {
}