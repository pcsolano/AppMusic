package dominio;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorCancionDAO;


/* El catálogo mantiene los objetos en memoria, en una tabla hash
 * para mejorar el rendimiento. Esto no se podría hacer en una base de
 * datos con un número grande de objetos. En ese caso se consultaría
 * directamente la base de datos
 */
public class CatalogoCanciones {
	private Map<String,Cancion> Canciones; 
	private static CatalogoCanciones unicaInstancia;
	
	private FactoriaDAO dao;
	private IAdaptadorCancionDAO adaptadorCancion;
	
	private CatalogoCanciones() {
		try {
  			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorCancion = dao.getCancionDAO();
  			Canciones = new HashMap<String,Cancion>();
  			this.cargarCatalogo();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	
	public static CatalogoCanciones getUnicaInstancia() {
        if (unicaInstancia == null) {
            unicaInstancia = new CatalogoCanciones();
        }
        return unicaInstancia;
    }
	
	/*devuelve todos los Canciones*/
	public List<Cancion> getCanciones(){
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		for (Cancion c:Canciones.values()) 
			lista.add(c);
		return lista;
	}
	
	public Cancion getCancion(int codigo) {
		for (Cancion p : Canciones.values()) {
			if (p.getId()==codigo) return p;
		}
		return null;
	}
	public Cancion getCancion(String nombre) {
		return Canciones.get(nombre); 
	}
	
	public void addCancion(Cancion pro) {
		Canciones.put(pro.getTitulo(),pro);
		adaptadorCancion.registrarCancion(pro);
	}
	public void removeCancion(Cancion pro) {
		Canciones.remove(pro.getTitulo());
		adaptadorCancion.borrarCancion(pro);
	}
	
	/*Recupera todos los Canciones para trabajar con ellos en memoria*/
	private void cargarCatalogo() throws DAOException {
		 List<Cancion> CancionesBD = adaptadorCancion.recuperarTodosCanciones();
		 for (Cancion pro: CancionesBD) 
			     Canciones.put(pro.getTitulo(),pro);
	}

	public List<Cancion> cancionesOrdenadas(){
		return this.getCanciones().stream()
		.sorted(Comparator.comparingInt(c -> c.getnumReproducciones()))
		.collect(Collectors.toList());
	
	}
	
	
}
