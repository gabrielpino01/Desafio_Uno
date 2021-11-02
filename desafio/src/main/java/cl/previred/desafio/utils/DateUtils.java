package cl.previred.desafio.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de ayuda en la gestion de fechas
 *
 */
public class DateUtils {
	
	/**
	 * Obtiene las fechas correspondientes al inicio de cada mes dentro de un periodo de tiempo
	 * @param start fecha inicio
	 * @param end fecha fin
	 * @return lista de fechas
	 */
	public static List<LocalDate> getDatesInPeriod(LocalDate start, LocalDate end){
		if(start == null || end == null || end.isBefore(start) || start.isEqual(end)) {
			return null;
		}	
			
		List<LocalDate> response =	start.datesUntil(end, Period.ofMonths(1)).collect(Collectors.toList());
		if(!isDatePresentOnList(end,response)) {
			response.add(end);
		}
		return response;
	}
	
	/**
	 * determina si una fecha se encuentra presente dentro de una lista de fechas
	 * @param date fecha a buscar
	 * @param list lista de fechas
	 * @return boolean
	 */
	public static Boolean isDatePresentOnList(LocalDate date, List<LocalDate> list) {
		if(date == null || list == null || list.isEmpty()) {
			return Boolean.FALSE;
		}
		
		long counter = list.stream().filter(item -> item.isEqual(date)).count();
				
		if(counter > 0) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
}
