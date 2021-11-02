package cl.previred.desafio.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cl.previred.desafio.dto.PeriodoDTO;
import cl.previred.desafio.dto.PeriodoResponseDTO;
import cl.previred.desafio.service.DesafioPeriodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(value = "/api")
public class DesafioPeriodoController {
	
	@Autowired
	private DesafioPeriodoService service;
	
	@Value("${desafio.uno.gdd.uri}")
	private String GDD_URI;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
 
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
    /**
     * Api que retorna las fechas faltantes
     * @return Periodo Response
     */
    @Operation(
    		  summary = "Retorna un objeto con las fechas, fechas faltantes, fecha inicio, fecha fin y id del periodo.",
    		  tags = {"Operaciones con fechas"},
    		  responses = @ApiResponse(responseCode = "200", description = "Respuesta satisfactoria",
    		    content = @Content(schema = @Schema(implementation = PeriodoResponseDTO.class))
    		  ))
	@GetMapping(value = "/get-missing-dates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PeriodoResponseDTO> getPeriodos() {
    	PeriodoResponseDTO periodo = null;
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");
			

			HttpEntity entity = new HttpEntity(headers);
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<PeriodoDTO> GDDResponse = restTemplate.exchange(
					GDD_URI, HttpMethod.GET, entity, PeriodoDTO.class, 1);
			
			if(GDDResponse.getStatusCode() == HttpStatus.OK) {
				periodo = service.getPeriodos(GDDResponse.getBody());
			}
			
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(periodo);
	}
}
