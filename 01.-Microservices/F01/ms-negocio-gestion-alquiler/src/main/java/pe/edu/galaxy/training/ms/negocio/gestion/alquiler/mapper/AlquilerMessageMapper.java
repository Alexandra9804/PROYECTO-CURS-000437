package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.events.AlquilerEvent;
import pe.edu.galaxy.training.ms.negocio.gestion.alquiler.producer.AlquilerMessage;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlquilerMessageMapper {

    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\")))")
    AlquilerMessage toMessage(AlquilerEvent event);
}
