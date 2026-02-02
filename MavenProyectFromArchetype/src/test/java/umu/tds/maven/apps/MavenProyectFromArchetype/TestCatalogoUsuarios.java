package umu.tds.maven.apps.MavenProyectFromArchetype;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;



import dominio.*;

public class TestCatalogoUsuarios {

	 private CatalogoUsuarios catalogo;
	    private Usuario usuario1;
	    private Usuario usuario2;

	    @Before
	    public void setUp() {
	        catalogo = CatalogoUsuarios.getUnicaInstancia();
	        
	        limpiarCatalogo();

	        usuario1 = new Usuario("User1", "user1@example.com", "password1", "2000-01-01");
	        usuario1.setId(1);
	        usuario2 = new Usuario("User2", "user2@example.com", "password2", "2000-01-02");
	        usuario2.setId(2);

	        catalogo.addUsuario(usuario1);
	        catalogo.addUsuario(usuario2);
	    }

	    private void limpiarCatalogo() {
	        List<Usuario> usuarios = catalogo.getUsuarios();
	        for (Usuario usuario : usuarios) {
	            catalogo.removeUsuario(usuario);
	        }
	    }

	    @Test
	    public void testGetUnicaInstancia() {
	        CatalogoUsuarios catalogo2 = CatalogoUsuarios.getUnicaInstancia();
	        assertSame(catalogo, catalogo2);
	    }

	    @Test
	    public void testGetUsuarios() {
	        List<Usuario> usuarios = catalogo.getUsuarios();
	        assertTrue(usuarios.contains(usuario1));
	        assertTrue(usuarios.contains(usuario2));
	    }

	    @Test
	    public void testGetUsuarioById() {
	        assertEquals(usuario1, catalogo.getUsuario(1));
	        assertEquals(usuario2, catalogo.getUsuario(2));
	    }

	    @Test
	    public void testGetUsuarioByNombre() {
	        assertEquals(usuario1, catalogo.getUsuario("User1"));
	        assertEquals(usuario2, catalogo.getUsuario("User2"));
	    }

	    @Test
	    public void testAddUsuario() {
	        Usuario usuario3 = new Usuario("User3", "user3@example.com", "password3", "2000-01-03");
	        usuario3.setId(3);
	        catalogo.addUsuario(usuario3);
	        assertEquals(usuario3, catalogo.getUsuario("User3"));
	    }

	    @Test
	    public void testRemoveUsuario() {
	        catalogo.removeUsuario(usuario1);
	        assertNull(catalogo.getUsuario("User1"));
	    }

	    @Test
	    public void testAddUsuarioFull() {
	        Usuario usuario3 = catalogo.addUsuario("User3", "user3@example.com", "password3", "2000-01-03");
	        assertEquals(usuario3, catalogo.getUsuario("User3"));
	    }

	    @Test
	    public void testEmailEnUso() {
	        assertTrue(catalogo.emailEnUso("user1@example.com"));
	        assertFalse(catalogo.emailEnUso("nonexistent@example.com"));
	    }

}
