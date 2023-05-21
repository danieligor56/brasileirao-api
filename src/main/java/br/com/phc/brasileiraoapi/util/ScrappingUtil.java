package br.com.phc.brasileiraoapi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.hibernate.engine.query.spi.ReturnMetadata;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import brasileiraoapi.dto.PartidaGoogleDto;

public class ScrappingUtil {

	public static final Logger LOGGER = LoggerFactory.getLogger(ScrappingUtil.class);
	private static final String BASE_URL_GOOGLE = "https://www.google.com/search?q=";
	private static final String COMPLEMENTO_URL_GOOGLE = "&hl=pt-BR";
	private static final String CASA = "casa";
	private static final String VISITANTE = "visitante";

	public static void main(String[] args) {

		String url = BASE_URL_GOOGLE + "brasil+vs+croácia+2022" + COMPLEMENTO_URL_GOOGLE;

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
			Status statusPartida = getStatusPt(document);
			if (statusPartida != Status.PARTIDA_NAO_INICIADA) {
				// Recupera tempo da partida,
				String tempoPartida = getTempPt(document);
				// LOGGER.info("Tempo da partida: {}", tempoPartida);

				// Recupera placar equipe de casa.
				Integer recPlacEquipCasa = getPlacEquipCasa(document);
				LOGGER.info("Placar equipe casa: {}", recPlacEquipCasa);
				// Recupera placar equipe visitante
				Integer recPlacEquipVisit = getPlacEquipVisit(document);
				LOGGER.info("Placar equipe Visitante: {}", recPlacEquipVisit);
				// Recupera marcadores casa
				String recMarcCasa = getMarcCasa(document);
				LOGGER.info("Marcantes casa: {}", recMarcCasa);
				// Recupera marcadores visitantes
				String recMarcVisit = getMarcVisit(document);
				LOGGER.info("Marcantes visitantes: {}", recMarcVisit);
				
				Integer placPenEqpCas = getPlacEstEqpCas(document, CASA);
				LOGGER.info("Gols estendido equipe casa {}",placPenEqpCas);
				
				Integer placPenEqpVisit = getPlacEstEqpCas(document, VISITANTE);
				LOGGER.info("Gols estendido equipe visitante {}", placPenEqpVisit);	
			}

			String getNomEquip = getNomEquipCasa(document);
			LOGGER.info("Nome da equipe casa: {}", getNomEquip);

			String getNomEquipVisit = getNomEquipvisit(document);
			LOGGER.info("Nome da equipe visitante: {}", getNomEquipVisit);

			/*
			 * //RECUPERA URL LOGO EQUIP CASA. String getUrlLogEquipCasa =
			 * recLogEqpCasa(document);
			 * LOGGER.info("Logo equipe casa: {}",getUrlLogEquipCasa);
			 */

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
			LOGGER.info("Status da partida: {}", stsPt);
		}

		return stsPt;

	}

	// FIM DO BLOCO DE STATUS //

	/*
	 * OBTER TEMPO DE PARTIDA:
	 * 
	 * SITUAÇÕES: INTERVALO - MINUTOS - ENCERRADO
	 */

	public String getTempPt(Document document) {
		String tmpPtString = null;

		boolean getStsTempPt = document.select("div[class=\"imso_mh__pst-m-stts-l\"]").isEmpty();

		if (!getStsTempPt) {
			String showTmpPtString = document.select("div[class=\"imso_mh__pst-m-stts-l\"]").first().text();
			LOGGER.info(showTmpPtString);
		}

		if (getStsTempPt) {
			String showTmpPtString = document.select("div[class=\"imso_mh__lv-m-stts-cont\"]").first().text();
			LOGGER.info(fixTime(showTmpPtString));
		}

		return tmpPtString;
	}

	// metodo para formatar ret
	public String fixTime(String tempo) {
		String tmpPtString = null;
		if (tempo.contains("'"))
			tmpPtString = tempo.replace("'", " MIN");
		else if (tempo.contains("+")) {
			tmpPtString = tempo.replace(" ", "").concat(" MIN");
		} else {
			return tempo;
		}

		return tmpPtString;
	}

	// RECUPERA NOME DA EQUIPE DE CASA.
	public String getNomEquipCasa(Document document) {
		org.jsoup.nodes.Element element = document
				.selectFirst("div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]");
		String nomEqpCasa = element.select("span").text();

		return nomEqpCasa;
	}

	// RECUPERA NOME DA EQUIPE VISITANTE.
	public String getNomEquipvisit(Document document) {
		org.jsoup.nodes.Element element = document
				.selectFirst("div[class=\"imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol\"]");
		String nomEqpVisit = element.select("span").text();

		return nomEqpVisit;
	}

	/*
	 * 
	 * GOOGLE BLOQUEOU A CAPTURA DE LOGOS.
	 * 
	 * public String recLogEqpCasa(Document document) {
	 * 
	 * org.jsoup.nodes.Element elemento =
	 * document.selectFirst("div[class=\"imso_mh__t-l-cont kno-fb-ctx\"]"); String
	 * urlLogo = "https:" +
	 * elemento.select("img[class=imso_btl__mh-logo]").attr("src"); String
	 * encodedData = urlLogo.split(",")[1]; byte[] decodedData =
	 * Base64.getDecoder().decode(encodedData); String decString = new
	 * String(decodedData);
	 * 
	 * // try (FileOutputStream file = new FileOutputStream("imagem.gif")) {
	 * //file.write(decodedData);
	 * 
	 * return decString;}
	 */

	public Integer getPlacEquipCasa(Document document) {
		String getPlacCasInteger = document.select("div[class=\"imso_mh__l-tm-sc imso_mh__scr-it imso-light-font\"]")
				.first().text();

		return Integer.valueOf(getPlacCasInteger);
	}

	public Integer getPlacEquipVisit(Document document) {
		String getPlacVisit = document.select("div[class=\"imso_mh__r-tm-sc imso_mh__scr-it imso-light-font\"]").first()
				.text();

		return Integer.valueOf(getPlacVisit);
	}

	public String getMarcCasa(Document documento) {

		List<String> golsMarcCasa = new ArrayList<>();
		Elements golsMarcsCasa = documento.select("div[class=\"imso_gs__tgs imso_gs__left-team\"]")
				.select("div[class=\"imso_gs__gs-r\"]");

		for (Element golsCas : golsMarcsCasa) {
			String getGolsCasa = golsCas.select("div[class=\"imso_gs__gs-r\"]").text();
			golsMarcCasa.add(getGolsCasa);
		}

		return String.join(", ", golsMarcCasa);
	}

	public String getMarcVisit(Document document) {

		List<String> golsMarcVisit = new ArrayList<>();

		Elements golsMarcsVisit = document.select("div[class=\"imso_gs__tgs imso_gs__right-team\"]")
				.select("div[class=\"imso_gs__gs-r\"]");

		for (Element golsVisit : golsMarcsVisit) {
			String getGolsVisit = golsVisit.select("div[class=\"imso_gs__gs-r\"]").text();
			golsMarcVisit.add(getGolsVisit);
		}

		return String.join(", ", golsMarcVisit);
	}

	public Integer getPlacEstEqpCas(Document document, String tipEqp) {
		boolean isPen = document.select("div[class=\"imso_mh_s__psn-sc\"]").isEmpty();
		if (!isPen) {
			String getPenal = document.select("div[class=\"imso_mh_s__psn-sc\"]").text();
			String getPenalComp = getPenal.substring(0, 5).replace(" ", "");
			String[] divPenal = getPenalComp.split("-");

			return tipEqp.equals(CASA) ? formatPlacaStringInt(divPenal[0]) : formatPlacaStringInt(divPenal[1]);

		}
		return null;
	}

	public Integer formatPlacaStringInt(String placar) {
		Integer valor;
		try {
			valor = Integer.parseInt(placar);
		} catch (Exception e) {
			valor = 0;
		}
		return valor;
	}
}
