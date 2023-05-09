package br.com.phc.brasileiraoapi.util;

import java.io.IOException;
import java.security.PublicKey;

import javax.lang.model.element.Element;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Evaluator.IsEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brasileiraoapi.dto.PartidaGoogleDto;

public class ScrappingUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(ScrappingUtil.class);
	private static final String BASE_URL_GOOGLE = "https://www.google.com/search?q=";
	private static final String COMPLEMENTO_URL_GOOGLE = "&hl=pt-BR";

	public static void main(String[] args) {

		String url = BASE_URL_GOOGLE + "ulsan+hyundai+x+gangwon" + COMPLEMENTO_URL_GOOGLE;

		ScrappingUtil scrappin = new ScrappingUtil();

		// METODO PARA FAZER A CONSULTA.
		scrappin.getInfoPt(url);

	}

	public PartidaGoogleDto getInfoPt(String url) {

		PartidaGoogleDto partida = new PartidaGoogleDto();
		Document document = null;

		try {

			document = Jsoup.connect(url).get();

			// Titulo da pagina.
			String getTitJog = document.selectXpath("//*[@id=\"sports-app\"]/div/div[1]/div/div/div").first().text();
			LOGGER.info("Partida: {}", getTitJog);

			// Recuperar Status da partida.
			getStatusPt(document);

		} catch (IOException e) {

			LOGGER.error("Erro ao conectar com o google JSOUP -> {}", e.getMessage());

		}

		return partida;
	}

	// BLOCO STATUS DA PARTIDA//

	public Status getStatusPt(Document document) {

		// * Situações da partida:
		// 1 - Partida não iniciada. - 
		// 2 - Partida em andamento. - 	
		// 3 - Partida encerrada. - ok
		// 4 - Penalidades.

		// VARIAVEL QUE CONATROLA O STATUS // SITUAÇÃO INICIAL DA VARIAVEL. 
		
		Status stsPt = Status.PARTIDA_NAO_INICIADA;


		// VARIAVEL QUE VERIFICA O STATUS.
		boolean stsTmpPt = document.select("div[class=\"imso_mh__lv-m-stts-cont\"]").isEmpty();
	
		// VERIFICADOR, PARTIDA EM ANDAMENTO + PENALTIS :

		if (!stsTmpPt) {

			String tempPt = document.select("div[class=\"imso_mh__lv-m-stts-cont\"]").first().text();

			stsPt = Status.PARTIDA_EM_ANDAMENTO;
			LOGGER.info("Satus da partida: {}", stsPt);

			// PENALTIS:

			if (tempPt.contains("pênaltis")) {
				stsPt = Status.PARTIDA_PENALTIS;
				LOGGER.info("Satus da partida verifica: {}", stsPt);
			}
			
			
		} 

		// PARTIDA ENCERRADA:
		boolean ifPtEnc = document.select("span[class=\"imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc\"]")
				.isEmpty();
		if (!ifPtEnc) {
			String getIfPtString = document
					.select("span[class=\"imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc\"]").first().text();
			if (getIfPtString.contains("Encerrado")) {
				stsPt = Status.PARTIDA_ENCERRADA;
				LOGGER.info(stsPt.toString());
			}
			
					

		}
		
		if (ifPtEnc && stsTmpPt) {
			LOGGER.info("Status da partida: {}",stsPt);
		}
		
		return stsPt;
		
		
	}
}