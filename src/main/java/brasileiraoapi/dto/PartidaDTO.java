package brasileiraoapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDTO implements Serializable{
	
	private static final long serialVersionUID = 5538645734956860665L;
	
	@NotBlank
	private String nomeEquipeCasa;
	@NotBlank
	private String nomEquipeVizitante;
	@NotBlank
	private String localPartidaString;
	@NotNull
	@Schema(example = "dd/MM/yyyy HH:mm")
	@JsonFormat(shape = Shape.STRING,pattern = "dd/MM/yyyy HH:mm" , timezone = "America/Sao_Paulo")
	private Date dataHoraPartiDate;
	

}
