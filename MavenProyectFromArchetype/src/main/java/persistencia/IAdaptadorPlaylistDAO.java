package persistencia;

import java.util.List;
import dominio.Playlist;

public interface IAdaptadorPlaylistDAO {

	public void registrarPlaylist(Playlist Playlist);
	public void borrarPlaylist(Playlist Playlist);
	public void modificarPlaylist(Playlist Playlist);
	public Playlist recuperarPlaylist(int id);
	public List<Playlist> recuperarTodosPlaylists();
}
