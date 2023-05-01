package brasileiraoapi.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PartidaGoogleDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String statusPartida;
	private String temmpoPartida;
	
	//INFORMAÇÕES EQUIPE CASA
	private String nomeEquipeCasa;
	private String urlLogoEquipeCasa;
	private Integer placarEquipeCasa;
	private String golsEquipeCasa;
	private String placarEstendidoEquipeCasa;
	//INFORMAÇÕES EQUIPE VISITANTE
		private String nomeEquipeVisitante;
		private String urlLogoEquipeVisitante;
		private Integer placarEquipeVisitante;
		private String golsEquipeVisitante;
		private String placarEstendidoEquipeVisitante;


}
