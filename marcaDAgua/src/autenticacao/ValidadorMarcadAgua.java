package autenticacao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class ValidadorMarcadAgua {
	
	private final Color COR_DE_FUNDO = Color.BLACK;
	
	/**
	 * Metodo que percorre uma imagem como um vetor e mostra o rgb de cada pixel
	 */
	public boolean validar(String filePath,BufferedImage imagemPretaBuff,Color COR_FONTE, String FONTE){

		double pixeisTotal = 0;
		double pixeisDiff = 0;	
		
		try {
			File imagemMarcada = new File(filePath); // Caminho da imagem original
			
			if (!imagemMarcada.exists()) {
				System.out.println("Arquivo de Imagem nao encontrado.");//O arquivo nao existe
				return false;
	        }
			
			BufferedImage imagemMarcadaBuff = ImageIO.read(imagemMarcada);

			//Percorre a imagem com fundo preto procurando uma cor que nao seja preta
			for(int i = 0; i < imagemPretaBuff.getWidth(); i++){
				for(int j = 0; j < imagemPretaBuff.getHeight(); j++){
					Color imagemPretaColor = new Color(imagemPretaBuff.getRGB(i, j));//aponta a referencia para a instancia(objeto) color
					
					if (!(imagemPretaColor.equals(COR_DE_FUNDO))) {
						pixeisTotal = pixeisTotal + 1;
						
						Color imagemMarcadaColor = new Color(imagemMarcadaBuff.getRGB(i,j));//pega a cor do pixel na imagem normal na coordenada (i,j)
						
						if (!(imagemMarcadaColor.equals(imagemPretaColor))) {//compara a cor do pixel nas duas imagens
							pixeisDiff = pixeisDiff + 1;
							
						}						
					}					
				}				
			}	
			
			double media = (pixeisDiff / pixeisTotal) * 100; //calcula a media de erros ao redor da Marca d'agua
			if(media >= 2){
				
				String resultado;
				
				resultado = "A imagem "+imagemMarcada.getName()+" nao contem a marca d'agua!";
				resultado = resultado + String.format(" (Margem de erro: %.2f %%)", media);
				
				System.out.println(resultado);
				
			}else{
				
				String resultado;
				
				resultado = "A imagem "+imagemMarcada.getName()+" contem a marca d'agua!";
				//resultado = resultado + String.format(" (Margem de erro: %.2f %%)", media);
				
				System.out.println(resultado);
			}
		
			
		} catch (Exception e) {
			System.out.println("Erro: "+ e.getMessage());
			return false;
		}
		return true;		
	}

}
