package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.phc.brasileiraoapi.entity.Partida;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import service.PartidaService;

@Tag(name ="API de Partidas")
@RestController
@RequestMapping("/api/v1/Partidas")
public class PartidaController {

@Autowired
private PartidaService partidaService;

@Operation(summary = "Buscar equipe por ID", description = "Recuperar equipe utilizando ID", responses = {
		@ApiResponse(responseCode = "200", description = "Equipe encontrada")})

@GetMapping("/{id}")				
public ResponseEntity<Partida>buscarPartidaPorID(@PathVariable("id") Long id){
	return ResponseEntity.ok().body(partidaService.buscaPartidaPorID(id));
}

           

}
