package dominio;

public class DescuentoFijo implements Descuento {
	private double porcentaje = 0.7;
	
	public double getPorcentaje() {
		return porcentaje;
	}

	@Override
	public double calcDescuento(double precio) {
		return precio * porcentaje;
	}

	@Override
	public String getTipoName() {
		return Utilidades.Constantes.DESCUENTOS[1];
	}

}
