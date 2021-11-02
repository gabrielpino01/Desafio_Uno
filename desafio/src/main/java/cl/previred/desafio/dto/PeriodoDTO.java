package cl.previred.desafio.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
public class PeriodoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long id = null;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("fechaCreacion")
	private LocalDate fechaCreacion;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("fechaFin")
	private LocalDate fechaFin;

	@NotNull
	@NotEmpty
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("fechas")
	private List<LocalDate> fechas;


	public PeriodoDTO() {
		
	}	
	

	public PeriodoDTO(Long id, @NotNull LocalDate fechaCreacion, @NotNull LocalDate fechaFin,
			@NotNull @NotEmpty List<LocalDate> fechas) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaFin = fechaFin;
		this.fechas = fechas;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<LocalDate> getFechas() {
		return fechas;
	}

	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}


}
