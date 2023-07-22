package brasileiraoapi.dto;

import java.io.Serializable;

import br.com.phc.brasileiraoapi.entity.Equipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List <Equipe> equipes;
	
}
