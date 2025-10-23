package pe.edu.galaxy.training.java.ms.negocio.gestion.clientes.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(name = "AUD_REGISTRO_FECHA",nullable = false)
    private LocalDateTime audRegistroFecha;

    @CreatedBy
    @Column(name = "AUD_REGISTRO_ID_USUARIO",nullable = false)
    private Long audRegistroIdUsuario;

    @LastModifiedDate
    @Column(name = "AUD_ACTUALIZACION_FECHA",nullable = true)
    private LocalDateTime audActualizacionFecha;

    @LastModifiedBy
    @Column(name = "AUD_ACTUALIZACION_ID_USUARIO",nullable = true)
    private Long audActualizacionIdUsuario;

    @Column(name = "ESTADO",nullable = false, length = 1)
    private String estado; //Borrado Logico 1: Activo, 0: Inactivo

    @PrePersist
    public void prePersist() {
        this.estado = "1";
    }
}
