package persistencia;

import java.util.List;
import dominio.Cancion;

public interface IAdaptadorCancionDAO {

	public void registrarCancion(Cancion Cancion);
	public void borrarCancion(Cancion Cancion);
	public void modificarCancion(Cancion Cancion);
	public Cancion recuperarCancion(int id);
	public List<Cancion> recuperarTodosCanciones();
}