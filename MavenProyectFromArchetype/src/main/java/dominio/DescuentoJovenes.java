package dominio;

public class DescuentoJovenes implements Descuento {
	private double porcentaje = 0.5;

	public double getPorcentaje() {
		return porcentaje;
	}

	@Override
	public double calcDescuento(double precio) {
		return precio * porcentaje;
	}

	@Override
	public String getTipoName() {
		return Utilidades.Constantes.DESCUENTOS[2];
	}

}
