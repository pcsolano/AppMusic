package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;

import dominio.Cancion;

//Usa un pool para evitar problemas doble referencia con Cancions
public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;

	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			unicaInstancia = new AdaptadorCancionTDS();
		return unicaInstancia;
	}

	private AdaptadorCancionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	/* cuando se registra un Cancion se le asigna un identificador �nico */
	public void registrarCancion(Cancion Cancion) {
		Entidad eCancion = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eCancion = servPersistencia.recuperarEntidad(Cancion.getId());
		} catch (NullPointerException e) {}
		if (eCancion != null) return;

		// crear entidad Cancion
		eCancion = new Entidad();
		eCancion.setNombre("Cancion");
		eCancion.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("titulo", Cancion.getTitulo()), new Propiedad("rutaFichero", Cancion.getrutaFichero()), new Propiedad("numReproducciones", String.valueOf(Cancion.getnumReproducciones())), new Propiedad("estilomusical", Cancion.getEstilomusical()), new Propiedad("interprete", Cancion.getInterprete())
						)));

		// registrar entidad Cancion
		eCancion = servPersistencia.registrarEntidad(eCancion);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		Cancion.setId(eCancion.getId());
		
	}

	public void borrarCancion(Cancion Cancion) {
		// No se comprueban restricciones de integridad con Cancion
		Entidad eCancion = servPersistencia.recuperarEntidad(Cancion.getId());

		servPersistencia.borrarEntidad(eCancion);
	}

	public void modificarCancion(Cancion Cancion) {

		Entidad eCancion = servPersistencia.recuperarEntidad(Cancion.getId());

		for (Propiedad prop : eCancion.getPropiedades()) {
			if (prop.getNombre().equals("Id")) {
				prop.setValor(String.valueOf(Cancion.getId()));
			} else if (prop.getNombre().equals("titulo")) {
				prop.setValor(Cancion.getTitulo());
			} else if (prop.getNombre().equals("rutaFichero")) {
				prop.setValor(Cancion.getTitulo());
			} else if (prop.getNombre().equals("numReproducciones")) {
				prop.setValor(String.valueOf(Cancion.getnumReproducciones()));
			}  else if (prop.getNombre().equals("estilomusical")) {
				prop.setValor(String.valueOf(Cancion.getEstilomusical()));
			}  else if (prop.getNombre().equals("interprete")) {
				prop.setValor(String.valueOf(Cancion.getInterprete()));
			} 
			servPersistencia.modificarPropiedad(prop);
		}

	}

	public Cancion recuperarCancion(int Id) {

		//Si la entidad est� en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(Id))
			return (Cancion) PoolDAO.getUnicaInstancia().getObjeto(Id);


		// si no, la recupera de la base de datos
		Entidad eCancion;
		String titulo;
		String rutaFichero;
		String numReproducciones;
		String estilomusical;
		String interprete;

		// recuperar entidad
		eCancion = servPersistencia.recuperarEntidad(Id);

		// recuperar propiedades que no son objetos
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutaFichero");
		numReproducciones = servPersistencia.recuperarPropiedadEntidad(eCancion, "numReproducciones");
		estilomusical = servPersistencia.recuperarPropiedadEntidad(eCancion, "estilomusical");
		interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");

		Cancion Cancion = new Cancion(titulo, rutaFichero);
		Cancion.setId(Id);
		Cancion.setnumReproducciones(Integer.valueOf(numReproducciones));
		Cancion.setEstilomusical(estilomusical);
		Cancion.setInterprete(interprete);

		// IMPORTANTE:a�adir el Cancion al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(Id, Cancion);


		return Cancion;
	}

	public List<Cancion> recuperarTodosCanciones() {

		List<Entidad> eCanciones = servPersistencia.recuperarEntidades("Cancion");
		List<Cancion> Cancion = new LinkedList<Cancion>();

		for (Entidad eCancion : eCanciones) {
			Cancion.add(recuperarCancion(eCancion.getId()));
		}
		return Cancion;
	}


}