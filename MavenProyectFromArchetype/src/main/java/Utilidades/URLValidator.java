package Utilidades;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class URLValidator {

    // Expresión regular para validar el formato de una URL
    private static final String URL_REGEX =
            "^((https?|ftp|file)://)?(([\\w-]+\\.)+[\\w-]+|localhost)(:[0-9]{1,5})?(/[^\\s]*)?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean isValidURL(String urlString) {
        if (urlString == null || urlString.isEmpty()) {
            return false;
        }
        
        // Primero validamos el formato con la expresión regular
        if (!URL_PATTERN.matcher(urlString).matches()) {
            return false;
        }
        
        try {
            // Intentamos crear un objeto URL a partir del string
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            // Si se lanza una excepción, no es una URL válida
            return false;
        }
    }
    
}