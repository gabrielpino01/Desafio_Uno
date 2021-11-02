package cl.previred.desafio.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PeriodoResponseDTO extends PeriodoDTO {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("fechasFaltantes")
	private List<LocalDate> fechasFaltantes;

	public PeriodoResponseDTO() {
		
	}

	public PeriodoResponseDTO(PeriodoDTO dto) {
		super(dto.getId(), dto.getFechaCreacion(), dto.getFechaFin(), dto.getFechas());
		this.fechasFaltantes = new ArrayList<>();
	}

	public List<LocalDate> getFechasFaltantes() {
		return fechasFaltantes;
	}

	public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
		this.fechasFaltantes = fechasFaltantes;
	}
	
}
