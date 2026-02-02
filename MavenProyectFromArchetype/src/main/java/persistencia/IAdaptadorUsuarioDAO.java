package persistencia;

import java.util.List;
import dominio.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario Usuario);
	public void borrarUsuario(Usuario Usuario);
	public void modificarUsuario(Usuario Usuario);
	public Usuario recuperarUsuario(int id);
	public List<Usuario> recuperarTodosUsuarios();
}
