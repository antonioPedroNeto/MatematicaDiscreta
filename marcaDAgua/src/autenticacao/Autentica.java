package autenticacao;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * A classe ainda nao esta autenticando apenas esta com um exemplo de como percorrer uma imagem
 *
 */
public class Autentica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Autentica m = new Autentica();//criar um objeto do tipo autentica
		try {
			m.image();//chama o metodo que vai percorrer a imagem
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//fecha main
	
	/**
	 * Metodo que percorre uma imagem como um vetor e mostra o rgb de cada pixel
	 * @throws InterruptedException
	 */
	public void image() throws InterruptedException{
		
		File f = new File("C:\\OCR\\teste.png");//cria um objeto do tipo file para guardar o caminho da imagem
		Color color; //cria uma referencia do tipo Color
		
		try {
			BufferedImage buff = ImageIO.read(f);
			
			//percorre a imagem
			for(int i = 0; i < buff.getWidth(); i++){
				for(int j = 0; j < buff.getHeight(); j++){
					
					color = new Color(buff.getRGB(i, j));//aponta a referencia para a instancia(objeto) color
					System.out.println("posicao: ("+i+","+j+")"+color);
					
				}//fecha for j
				
				Thread.sleep(10000);
				
			}//fecha for i
			
			
		} catch (IOException e) {
			System.out.println("erro: "+ e.getMessage());//mostra a mensagem caso tenha erro
		}//fecha try catch
		
		
		
	}//fecha image

}//fecha class
