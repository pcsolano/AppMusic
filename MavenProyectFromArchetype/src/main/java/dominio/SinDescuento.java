package dominio;

public class SinDescuento implements Descuento {
	private double porcentaje = 1.0;
	
	public double getPorcentaje() {
		return porcentaje;
	}

	@Override
	public double calcDescuento(double precio) {
		return precio * porcentaje;
	}

	@Override
	public String getTipoName() {
		return Utilidades.Constantes.DESCUENTOS[0];
	}

}
