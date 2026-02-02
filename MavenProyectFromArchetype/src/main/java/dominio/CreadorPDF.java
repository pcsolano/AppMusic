package dominio;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreadorPDF {
	
	private static CreadorPDF instancia;
	
	public static CreadorPDF getUnicaInstancia() {
		if (instancia == null) {
			instancia = new CreadorPDF();
		}
		return instancia;
	}
	
	public boolean crearPDF(Usuario usuarioActivo) {
		try {
			// Obtener el nombre del usuario activo
			String nombreUsuario = usuarioActivo.getNombre();

			// Crear el documento PDF
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(nombreUsuario + ".pdf"));
			document.open();

			// Agregar el título del documento
			Paragraph titulo = new Paragraph("Playlists de " + nombreUsuario);
			titulo.setAlignment(Element.ALIGN_CENTER);
			titulo.setSpacingAfter(20);
			document.add(titulo);

			// Recorrer las playlists del usuario activo
			for (Playlist playlist : usuarioActivo.getPlaylists()) {
				// Agregar el nombre de la playlist
				Paragraph nombrePlaylist = new Paragraph("Playlist: " + playlist.getNombre());
				nombrePlaylist.setSpacingAfter(10);
				document.add(nombrePlaylist);

				// Crear una tabla para mostrar las canciones de la playlist
				PdfPTable tablaCanciones = new PdfPTable(3);
				tablaCanciones.setWidthPercentage(100);
				tablaCanciones.setSpacingAfter(10);

				// Agregar encabezados de columna
				tablaCanciones.addCell("Título");
				tablaCanciones.addCell("Intérprete");
				tablaCanciones.addCell("Estilo");

				// Recorrer las canciones de la playlist
				for (Cancion cancion : playlist.getCanciones()) {
					tablaCanciones.addCell(cancion.getTitulo());
					tablaCanciones.addCell(cancion.getInterprete());
					tablaCanciones.addCell(cancion.getEstilomusical());
				}

				// Agregar la tabla de canciones al documento
				document.add(tablaCanciones);
			}

			// Cerrar el documento
			document.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
