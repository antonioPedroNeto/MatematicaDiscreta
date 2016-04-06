package autenticacao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * A classe ainda nao esta autenticando apenas esta com um exemplo de como percorrer uma imagem
 *
 */
public class ValidadorMarcadAgua {
	
	private final Color COR_DE_FUNDO = Color.BLACK;
	
	public static void main(String[] args) {

		ValidadorMarcadAgua m = new ValidadorMarcadAgua();//criar um objeto do tipo autenticador
		try {
			m.image();//chama o metodo que vai percorrer a imagem
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo que percorre uma imagem como um vetor e mostra o rgb de cada pixel
	 * @throws InterruptedException
	 */
	public boolean image() throws InterruptedException{
		
		File imagemPreta = new File("//home/aramissa/Desktop/mehAguaBranca.png");//cria um objeto do tipo file para guardar o caminho da imagem
		File imagemMarcada = new File("//home/aramissa/Desktop/mehAgua.png");//cria um objeto do tipo file para guardar o caminho da imagem

		Color imagemPretaColor; //cria uma referencia do tipo Color
		double pixeisTotal = 0;
		double pixeisDiff = 0;
		
		try {
			BufferedImage imagemPretaBuff = ImageIO.read(imagemPreta);
			BufferedImage imagemMarcadaBuff = ImageIO.read(imagemMarcada);
			
			//percorre a imagem
			for(int i = 0; i < imagemPretaBuff.getWidth(); i++){
				for(int j = 0; j < imagemPretaBuff.getHeight(); j++){
					imagemPretaColor = new Color(imagemPretaBuff.getRGB(i, j));//aponta a referencia para a instancia(objeto) color

					//System.out.println(imagemPretaColor.getRGB());
					if (!(imagemPretaColor.equals(COR_DE_FUNDO))) {
						
						pixeisTotal = pixeisTotal + 1;
						
						Color imagemMarcadaColor = new Color(imagemMarcadaBuff.getRGB(i,j));
						
						if (!(imagemMarcadaColor.equals(imagemPretaColor))) {
							//System.out.println(i + "  "+ j + "  rgb:"+imagemPretaBuff.getRGB(i, j));
							pixeisDiff = pixeisDiff + 1;
							//System.out.println(pixeisDiff);
							//throw new Exception("Eita! Tem nao!");
							
						}
					}
				}
			}
				System.out.println(pixeisDiff+"   "+pixeisTotal);
				double media = (pixeisDiff / pixeisTotal) * 100;
				if(media >= 30){
					System.out.println("Imagem nao contem a marca!");
					System.out.println(String.format("Margem de erro: %.2f %%", media));
				}
				else{System.out.println("Yay, tem marca dagua!!");
				System.out.println(String.format("Margem de erro: %.2f %%", media));
				}
					//System.out.println("posicao: ("+i+","+j+")"+imagemPretaColor);
					//return true;			
			
		} catch (Exception e) {
			System.out.println("erro: "+ e.getMessage());//mostra a mensagem caso tenha erro
			return false;
		}
		return true;
		
		
		
	}

}
