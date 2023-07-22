package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.entity.Equipe;
import exception.NotFound;
import repository.EquipeRepository;

@Service
public class EquipeService {


	@Autowired
	private EquipeRepository equipeRepository;
	
	public Equipe buscarEquipeID(Long id) {
		return equipeRepository.findById(id)
				.orElseThrow(() -> new NotFound ("Nenhuma equipe encontrada com o ID informado: "+id));
	}

}
