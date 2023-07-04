package br.com.phc.brasileiraoapi.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Partida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "partida_id")
	private long id;
	
	@Transient
	private String statusPartida;
	
    @ManyToOne
	@JoinColumn(name="equipe_casa_id")
	private Equipe equipeCasa;
	
	@ManyToOne
	@JoinColumn(name="equipe_visitante_id")
	private Equipe equipeVisitante;
	
	@Column(name = "placar_equipe_casa")
	private Integer plcarEqpCasa;
	@Column(name = "placar_equipe_visitante")
	private Integer plcarEqpVisitante;
	@Column(name = "gols_equipe_casa")
	private String golsEqpCasa;
	@Column(name = "gols_equipe_visitante")
	private String golsEqpVisitante;
	@Column(name = "placar_estendido_eqp_casa")
	private Integer placarEstendidoEqpCasa;
	@Column(name = "placar_estendido_eqp_visitante")
	private Integer placarEstendidoEqpVisitante;
	
	@Schema(example = "dd/MM/yyyy HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm",timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_hora_partida")
	private Date dataHoraPartiDate;
	
	@Column(name = "local_partida")
	private String localPartida;

}
