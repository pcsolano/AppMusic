package persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	public TDSFactoriaDAO () {
	}
	
	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorPlaylistDAO getPlaylistDAO() {
		return AdaptadorPlaylistTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return AdaptadorCancionTDS.getUnicaInstancia();
	}


}
