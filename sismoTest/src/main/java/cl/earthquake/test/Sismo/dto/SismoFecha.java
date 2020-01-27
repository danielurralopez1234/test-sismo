package cl.earthquake.test.Sismo.dto;

public class SismoFecha {
	private String fechaInicial;
	private String fechaFinal;
	
	
	public SismoFecha() {
	}
	
	public SismoFecha(String fechaInicial, String fechaFinal) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	@Override
	public String toString() {
		return "SismoFecha [fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + "]";
	}
	
	
}
