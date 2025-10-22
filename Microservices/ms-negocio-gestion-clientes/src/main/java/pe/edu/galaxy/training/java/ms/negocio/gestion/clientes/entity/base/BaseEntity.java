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
    @Column(name = "aud_registro_fecha",nullable = false)
    private LocalDateTime audRegistroFecha;

    @CreatedBy
    @Column(name = "aud_registro_id_usuario",nullable = false)
    private Long audRegistroIdUsuario;

    @LastModifiedDate
    @Column(name = "aud_actualizacion_fecha",nullable = true)
    private LocalDateTime audActualizacionFecha;

    @LastModifiedBy
    @Column(name = "aud_actualizacion_id_usuario",nullable = true)
    private Long audActualizacionIdUsuario;

    @Column(name = "estado",nullable = false, length = 1)
    private String estado; //Borrado Logico 1: Activo, 0: Inactivo

    @PrePersist
    public void prePersist() {
        this.estado = "1";
    }
}
