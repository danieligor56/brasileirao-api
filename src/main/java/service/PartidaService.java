package service;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.entity.Partida;
import exception.NotFound;
import repository.PartidaRepository;

@Service
public class PartidaService {
@Autowired
private PartidaRepository partidaRepository;

public Partida buscaPartidaPorID(Long id) {
return partidaRepository.findById(id)
		.orElseThrow(()-> new NotFound("Nenhuma partida encontrada"+id));
		
	}
}
