package dominio;

public interface Descuento {
	
	public double getPorcentaje();

	double calcDescuento(double precio);

	String getTipoName();

}
