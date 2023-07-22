package controller;

import java.lang.annotation.Repeatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import br.com.phc.brasileiraoapi.entity.Equipe;
import exception.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import service.EquipeService;

@Controller
@Tag (name = "API de Equipes")
@RestController
@RequestMapping("/api/v1/equipes")
public class EquipeController {

	@Autowired
	private EquipeService equipeService;

	@Bean
	@Operation (summary = "Get Equipe by ID ", description  =  "Buscar equipe por ID")
	
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Equipe encontrada", content = @Content(schema = @Schema(implementation = Equipe.class))),
			  @ApiResponse(responseCode = "404", description = "Não axada", content = @Content(schema = @Schema(implementation = StandardError.class)))})			  

			
			
	
	@GetMapping("/{id}")
	public ResponseEntity<Equipe> buscarEquipeID(@PathVariable("id")Long id){
		return ResponseEntity.ok().body(equipeService.buscarEquipeID(id));
		
	}

}
