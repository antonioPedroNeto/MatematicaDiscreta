package autenticacao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Diretorios {

	public static String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	public static String USER_HOME = System.getProperties().getProperty("user.home");
	public static String BASE_PATH = USER_HOME + FILE_SEPARATOR + "marcaDAgua" + FILE_SEPARATOR;
	
	public static final String imagemDeComparacao = BASE_PATH + "outputPreta.png";
	public static final String imagemProcessada = BASE_PATH + "output.png";

	static {
		Path basePath = Paths.get(BASE_PATH);
		if (Files.notExists(basePath)) {
			File file = new File(basePath.toUri());
			file.mkdir(); // cria diretório base
		}
	}
	
}
