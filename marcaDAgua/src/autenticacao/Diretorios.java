package autenticacao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
	
	
	public static ArrayList<String> getAllImages(File directory) throws IOException {
		
        ArrayList<String> resultList = new ArrayList<String>(256);
        File[] files  = directory.listFiles();
        for (File file : files) {
            if (file != null && file.getName().toLowerCase().endsWith(".jpg")||
            		file != null && file.getName().toLowerCase().endsWith(".png")){
                resultList.add(file.getCanonicalPath());
            }
        
        }
        if (resultList.size() > 0)
            return resultList;
        else
            return null;
    }
	
}
