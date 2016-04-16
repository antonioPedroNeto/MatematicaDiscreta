package autenticacao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * 
 * A classe ainda nao esta autenticando apenas esta com um exemplo de como percorrer uma imagem
 *
 */
public class ValidadorMarcadAgua {
	
	private final Color COR_DE_FUNDO = Color.BLACK;
	
	/**
	 * Metodo que percorre uma imagem como um vetor e mostra o rgb de cada pixel
	 * @throws InterruptedException
	 */
	public boolean validar(String filePath,BufferedImage imagemPretaBuff,Color COR_FONTE, String FONTE) throws Exception {

		double pixeisTotal = 0;
		double pixeisDiff = 0;	
		
		try {
			File imagemMarcada = new File(filePath); // Caminho da imagem original
			
			if (!imagemMarcada.exists()) {
				System.out.println("Arquivo de Imagem nao encontrado.");//Imagem nao existe
				return false;
				// ou Throw Exception <-
	        }
			
			BufferedImage imagemMarcadaBuff = ImageIO.read(imagemMarcada);

			//Percorre a imagem
			for(int i = 0; i < imagemPretaBuff.getWidth(); i++){
				for(int j = 0; j < imagemPretaBuff.getHeight(); j++){
					Color imagemPretaColor = new Color(imagemPretaBuff.getRGB(i, j));//aponta a referencia para a instancia(objeto) color
					
					if (!(imagemPretaColor.equals(COR_DE_FUNDO))) {
						pixeisTotal = pixeisTotal + 1;
						
						Color imagemMarcadaColor = new Color(imagemMarcadaBuff.getRGB(i,j));
						
						if (!(imagemMarcadaColor.equals(imagemPretaColor))) {
							pixeisDiff = pixeisDiff + 1;
							
						}
						
					}
					
				}
				
			}
			
			double media = (pixeisDiff / pixeisTotal) * 100; //calcula a media de erros ao redor da Marca d'agua
			if(media >= 35){
				
				String resultado;
				
				resultado = "A imagem "+imagemMarcada.getName()+" nao contem a marca!";
				resultado = resultado + String.format(" (Margem de erro: %.2f %%)", media);
				
				System.out.println(resultado);
				
			}else{
				
				String resultado;
				
				resultado = "A imagem "+imagemMarcada.getName()+" tem a marca dagua!!";
				resultado = resultado + String.format(" (Margem de erro: %.2f %%)", media);
				
				System.out.println(resultado);
			}
		
			
		} catch (Exception e) {
			System.out.println("erro: "+ e.getMessage());//mostra a mensagem caso tenha erro
			return false;
		}
		return true;		
	}

}
