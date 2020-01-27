package cl.earthquake.test.Sismo.service.impl;

import cl.earthquake.test.Sismo.service.ConsultaService;
import cl.earthquake.test.Sismo.util.Constantes;

import java.time.LocalDate;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson; 

import org.springframework.web.util.UriComponentsBuilder;

public class ConsultaServiceImpl implements ConsultaService {
	
	 private static final Logger logger = LoggerFactory.getLogger(ConsultaServiceImpl.class);
	 private static final Integer MAXALLOWED = 20000;
	 
	 @Autowired
	 RestTemplate restTemplate;
	 
	 @Value("${earthquake.endpoint.query}")
	 private String urlQuery;
	 
	 @Value("${format.type}")
	 private String formatType;

	 @Override
	    public Object getSismosbyFecha(LocalDate fecIni, LocalDate fecFin) throws Exception {
	        logger.info("getSismosbyFecha "+ fecIni +" "+ fecFin);
	        Gson gson = new Gson();
	        try{
	            ResponseEntity<String> response = restTemplate.exchange(generateBuilder(urlQuery, fecIni, fecFin, null, null).toUriString(),
	                    HttpMethod.GET, generateEntity(), String.class);

	            return gson.fromJson(response.getBody(), Object.class);

	        }
	        catch(Exception e){
	            logger.error("Error al obtener sismos por fecha ,Exception: "+ e.getMessage());
	            throw new Exception(e.getMessage());
	        }

	    }

	  @Override
	    public Object getSismosbyMagnitud(String minMag, String maxMag) throws Exception {
	        logger.info("getSismosbyMagnitud "+ minMag +" "+ maxMag);
	        Gson gson = new Gson();

	        try{
	            ResponseEntity<String> response = restTemplate.exchange(generateBuilder(urlQuery, null, null, minMag, maxMag).toUriString(),
	                    HttpMethod.GET, generateEntity(), String.class);

	            return gson.fromJson(response.getBody(), Object.class);
	        }
	        catch(Exception e){
	            logger.error("Error al obtener sismos por magnitudes ,Exception: "+ e.getMessage());
	            throw new Exception(e.getMessage());
	        }

	    }
	  
	    /**
	     * Metodo que obtiene el componeent builder
	     * @param url url del servicio
	     * @param fechaR1 fecha inicial
	     * @param fechaR2 fecha final
	     * @param minMag magnitud minima
	     * @param maxMag magnitud maxima
	     * @return UriComponentsBuilder
	     */
	    private UriComponentsBuilder generateBuilder(String url, LocalDate fechaR1, LocalDate fechaR2, String minMag, String maxMag){
	        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
	        if(fechaR1 != null && fechaR2 != null){
	            builder.queryParam(Constantes.FORMAT, formatType)
	                    .queryParam(Constantes.START_TIME, fechaR1)
	                    .queryParam(Constantes.END_TIME, fechaR2);
	        }
	        else if(minMag != null & maxMag != null){
	            builder.queryParam(Constantes.FORMAT, formatType)
	                    .queryParam(Constantes.MIN_MAGNITUDE, minMag)
	                    .queryParam(Constantes.MAX_MAGNITUDE, maxMag);
	        }
	        else{
	            builder.queryParam(Constantes.FORMAT, formatType);
	        }

	        return builder;
	    }
	    
	    /**
	     * Metodo que genera cabecera http
	     * @return HttpEntity
	     */
	    private HttpEntity<String> generateEntity(){
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

	        HttpEntity<String> entity = new HttpEntity<>(headers);

	        return entity;
	    }

	    
}


