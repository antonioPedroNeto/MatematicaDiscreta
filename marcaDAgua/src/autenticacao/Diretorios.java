package autenticacao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Diretorios {
	
	public static String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	public static String THIS_FOLDER = new File("..").getAbsolutePath();
	public static String BASE_PATH = THIS_FOLDER + FILE_SEPARATOR + "Output" + FILE_SEPARATOR;
	public static final String imagemProcessada = BASE_PATH;

	static {
		Path basePath = Paths.get(BASE_PATH);
		if (Files.notExists(basePath)) {
			File file = new File(basePath.toUri());
			file.mkdir(); // cria diretorio base
		}
		
	}
	
	/**
	 * metodo que busca em todo o diretorio e pega o nome de todos os arquivos
	 * @param directory - caminho para o diretorio
	 * @return retorna uma ArrayList com todos os nomes das fotos
	 * @throws IOException
	 */
	
	public static ArrayList<String> getAllImages(File directory) throws IOException {
		
        ArrayList<String> resultList = new ArrayList<String>();
        
        File[] files  = directory.listFiles();//metodo que pega todos os arquivos de um diretorio
        
        for (File file : files) {//percorre todo o diretorio arquivo por arquivo
   
        	//verifica se o arquivo tem no final de sua string .png ou .jpg
            if (file != null && file.getName().toLowerCase().endsWith(".jpg")||
            		file != null && file.getName().toLowerCase().endsWith(".png")){
            	
            	//adiciona no ArrayList uma string referente ao caminho do arquivo
                resultList.add(file.getCanonicalPath());
            }
        }
        if (resultList.size() > 0){
        	
            return resultList;
            
        }
        else{
        	
            return null;
        }
    }
	
}
