package brasileiraoapi.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaGoogleDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numProc; 
	private String statusPartida; //ok
	private String tempoPartida; //ok 
	
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

/*
*private String numProc; 
	private String statusPartida; //ok
	private String tempoPartida; //ok 
	
	//INFORMAÇÕES EQUIPE CASA
	private String nomeEquipeCasa; ok
	private String urlLogoEquipeCasa;
	private Integer placarEquipeCasa; //ok
	private String golsEquipeCasa; OK
	private String placarEstendidoEquipeCasa; ok
	//INFORMAÇÕES EQUIPE VISITANTE
		private String nomeEquipeVisitante; ok
		private String urlLogoEquipeVisitante;
		private Integer placarEquipeVisitante; //ok
		private String golsEquipeVisitante; OK
		private String placarEstendidoEquipeVisitante; ok

*/






