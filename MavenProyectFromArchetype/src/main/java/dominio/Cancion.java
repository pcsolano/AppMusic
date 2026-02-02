package dominio;

public class Cancion {
	private String titulo;
	private String rutaFichero;
	private int numReproducciones;
	private String estilomusical;
	private String interprete;
	private int id;

	public Cancion(String titulo, String rutaFichero) {
		this.titulo = titulo;
		this.rutaFichero = rutaFichero;
		this.numReproducciones = 0;
	}

	public void setEstilomusical(String estilomusical) {
		this.estilomusical = estilomusical;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setnumReproducciones(int parseInt) {
		numReproducciones = parseInt;
	}

	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getrutaFichero() {
		return rutaFichero;
	}

	public Integer getnumReproducciones() {
		return numReproducciones;
	}

	public String getInterprete() {
		return interprete;
	}

	public String getEstilomusical() {
		return estilomusical;
	}

	public boolean addView() {
		try {
			setnumReproducciones(++numReproducciones);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
