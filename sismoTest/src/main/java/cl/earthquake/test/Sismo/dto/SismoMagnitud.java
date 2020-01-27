package cl.earthquake.test.Sismo.dto;

public class SismoMagnitud {
	
	private double magnitudInicial;
	private double magnitudFinal;	
	
	public SismoMagnitud() {
	}

	public SismoMagnitud(double magnitudInicial, double magnitudFinal) {
		this.magnitudInicial = magnitudInicial;
		this.magnitudFinal = magnitudFinal;
	}

	public double getMagnitudInicial() {
		return magnitudInicial;
	}

	public void setMagnitudInicial(double magnitudInicial) {
		this.magnitudInicial = magnitudInicial;
	}

	public double getMagnitudFinal() {
		return magnitudFinal;
	}

	public void setMagnitudFinal(double magnitudFinal) {
		this.magnitudFinal = magnitudFinal;
	}

	@Override
	public String toString() {
		return "SismoMagnitud [magnitudInicial=" + magnitudInicial + ", magnitudFinal=" + magnitudFinal + "]";
	}
	
	

}
