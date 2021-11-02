package cl.previred.desafio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.previred.desafio.dto.PeriodoDTO;
import cl.previred.desafio.dto.PeriodoResponseDTO;
import cl.previred.desafio.utils.DateUtils;


/**
 * Clase servicio del ejercicio
 *
 */
@Service
public class DesafioPeriodoService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Obtiene las fechas faltantes  del periodo
	 * @param dto parametros inicio
	 * @return dto de salida con fechas faltantes
	 */
	public PeriodoResponseDTO getPeriodos(PeriodoDTO dto) {
		PeriodoResponseDTO response = null;
		try {
			List<LocalDate> missingDates = new ArrayList<>();
			List<LocalDate> periodDates = DateUtils.getDatesInPeriod(dto.getFechaCreacion(), dto.getFechaFin());
			periodDates.stream().forEach(item -> {
				if(!DateUtils.isDatePresentOnList(item, dto.getFechas())) {
					missingDates.add(item);
				}
			});				
			response = new PeriodoResponseDTO(dto);		
			response.setFechasFaltantes(missingDates);
			
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		return response;		
	}
}
