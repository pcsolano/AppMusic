package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;

/* El catálogo mantiene los objetos en memoria, en una tabla hash
 * para mejorar el rendimiento. Esto no se podría hacer en una base de
 * datos con un número grande de objetos. En ese caso se consultaria
 * directamente la base de datos
 */
public class CatalogoUsuarios {
	private Map<String, Usuario> Usuarios;
	private static CatalogoUsuarios unicaInstancia;

	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;

	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuario = dao.getUsuarioDAO();
			Usuarios = new HashMap<String, Usuario>();
			this.cargarCatalogo();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new CatalogoUsuarios();
		}
		return unicaInstancia;
	}

	/* devuelve todos los Usuarios */
	public List<Usuario> getUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario c : Usuarios.values())
			lista.add(c);
		return lista;
	}

	public Usuario getUsuario(int codigo) {
		for (Usuario c : Usuarios.values()) {
			if (c.getId() == codigo)
				return c;
		}
		return null;
	}

	public Usuario getUsuario(String nombre) {
		return Usuarios.get(nombre);
	}

	public void addUsuario(Usuario cli) {
		Usuarios.put(cli.getNombre(), cli);
	}

	public void removeUsuario(Usuario cli) {
		Usuarios.remove(cli.getNombre());
	}

	/* Recupera todos los Usuarios para trabajar con ellos en memoria */
	private void cargarCatalogo() throws DAOException {
		List<Usuario> UsuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		for (Usuario cli : UsuariosBD)
			Usuarios.put(cli.getNombre(), cli);
	}

	public Usuario exists(String usuario, String contraseña) {
		for (String s : Usuarios.keySet()) {
			if (Usuarios.get(s).getPassword().equals(contraseña) && Usuarios.get(s).getNombre().equals(usuario)) {
				return Usuarios.get(contraseña);
			}
		}
		return null;
	}

	public Usuario addUsuario(String usuario, String email, String contraseña, String fecha) {
		Usuario cli = new Usuario(usuario, email, contraseña, fecha);
		adaptadorUsuario.registrarUsuario(cli);
		Usuarios.put(cli.getNombre(), cli);
		return cli;
	}

	public boolean emailEnUso(String email) {
		for (Usuario c : Usuarios.values()) {
			if (c.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

}
