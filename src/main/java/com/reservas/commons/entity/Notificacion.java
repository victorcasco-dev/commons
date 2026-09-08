package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.CanalNotificacion;
import com.reservas.commons.enums.EstadoNotificacion;
import com.reservas.commons.enums.TipoNotificacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "notificacion")
@NoArgsConstructor
public class Notificacion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TipoNotificacion tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private CanalNotificacion canal = CanalNotificacion.EMAIL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private EstadoNotificacion estado = EstadoNotificacion.PENDIENTE;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> payload;

    @Column(name = "enviada_at")
    private Instant enviadaAt;

    @Column(name = "error_mensaje", length = 500)
    private String errorMensaje;
}
