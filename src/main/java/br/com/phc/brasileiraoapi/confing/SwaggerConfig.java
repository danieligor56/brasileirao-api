package br.com.phc.brasileiraoapi.confing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.phc.brasileiraoapi.entity.Partida;
import controller.PartidaController;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
//@OpenAPIDefinition
@Configuration

public class SwaggerConfig {
	
	private static final String BASE_PACKAGE = "br.com.phc.brasileiraoapi.controller";
	private static final String API_TITULO = "Brasileirão API";
	private static final String API_DESCRICAO ="API REST que obtem dados das partidas do brasileirão em tempo real";
	private static final String API_VERSAO = "1.0";
	private static final String CONTATO_NOME = "Daniel Igor";
	private static final String CONTATO_GITHUB ="https://github.com/danieligor56";
	private static final String CONTATO_EMAIL="danieligor.vale@hotmail.com";	
	

@Bean
		public OpenAPI buildApiInfo() {
			return new OpenAPI()
					.info(new Info().title("Brasileirão API")					
					.description(API_DESCRICAO)
					.version(API_VERSAO));


		}


}
