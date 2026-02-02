package umu.tds.maven.apps.MavenProyectFromArchetype;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import dominio.*;

public class TestUsuario {

	private Usuario usuario;
    private Playlist playlist;
    private Cancion cancion;

    @Before
    public void setUp() {
        usuario = new Usuario("John Doe", "john@example.com", "password123", "01/01/2000");
        playlist = new Playlist("My Playlist");
        cancion = new Cancion("Song Title", "ruta/ruta");
    }

    @Test
    public void testConstructor() {
        assertEquals("John Doe", usuario.getNombre());
        assertEquals("john@example.com", usuario.getEmail());
        assertEquals("password123", usuario.getPassword());
        assertEquals("01/01/2000", usuario.getFechaNacimiento());
        assertFalse(usuario.isPremium());
        assertNotNull(usuario.getPlaylists());
        assertNotNull(usuario.getRecientes());
        assertNotNull(usuario.getFavoritas());
        assertNotNull(usuario.getDesc());
    }

    @Test
    public void testRealizarPago() {
        usuario.realizarPago();
        assertTrue(usuario.isPremium());
    }

    @Test
    public void testAddPlaylist() {
        usuario.addPlaylist(playlist);
        assertEquals(1, usuario.getPlaylists().size());
        assertEquals(playlist, usuario.getPlaylists().getFirst());
    }

    @Test
    public void testSetAndGetId() {
        usuario.setId(100);
        assertEquals(100, usuario.getId());
    }

    @Test
    public void testSetAndGetPlaylists() {
        LinkedList<Playlist> newPlaylists = new LinkedList<>();
        newPlaylists.add(playlist);
        usuario.setPlaylists(newPlaylists);
        assertEquals(newPlaylists, usuario.getPlaylists());
    }

    @Test
    public void testAñadirRecientes() {
        usuario.añadirRecientes(cancion);
        assertEquals(1, usuario.getRecientes().getCanciones().size());
        assertEquals(cancion, usuario.getRecientes().getCanciones().getFirst());
    }

    @Test
    public void testSetAndGetRecientes() {
        usuario.setRecientes(playlist);
        assertEquals(playlist, usuario.getRecientes());
    }

    @Test
    public void testSetAndGetDesc() {
        Descuento nuevoDescuento = new DescuentoFijo();
        usuario.setDesc(nuevoDescuento);
        assertEquals(nuevoDescuento, usuario.getDesc());
    }

    @Test
    public void testAñadirFavorita() {
        usuario.añadirFavorita(cancion);
        assertTrue(usuario.getFavoritas().getCanciones().contains(cancion));
    }

    @Test
    public void testEliminarFavorita() {
        usuario.añadirFavorita(cancion);
        usuario.eliminarFavorita(cancion);
        assertFalse(usuario.getFavoritas().getCanciones().contains(cancion));
    }

    @Test
    public void testSetAndGetFavoritas() {
        usuario.setFavoritas(playlist);
        assertEquals(playlist, usuario.getFavoritas());
    }

    @Test
    public void testSetPremium2() {
        usuario.setPremium2("true");
        assertTrue(usuario.isPremium());
        usuario.setPremium2("false");
        assertFalse(usuario.isPremium());
    }

    @Test
    public void testGetPremium() {
        usuario.setPremium(true);
        assertEquals("true", usuario.getPremium());
        usuario.setPremium(false);
        assertEquals("false", usuario.getPremium());
    }

    @Test
    public void testIsCancionFavouriteByCancion() {
        usuario.añadirFavorita(cancion);
        assertTrue(usuario.isCancionFavourite(cancion));
    }

}
