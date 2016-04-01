package pixel;


	import java.awt.Color;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;

	import javax.imageio.ImageIO;
	import javax.swing.JFileChooser;

	public class ColocaPixel {
		
		public static void main(String[] args) {
			
			File f = new File("C:\\Users\\Pedro\\Documents\\GitHub\\matematicaDiscreta\\marcaDAgua\\src\\autenticacao\\teste.png");//pega o caminho da imagem
			try{
				BufferedImage img = ImageIO.read(f);
				img.setRGB(0, 0, Color.black.getRGB());//troca a cor do pixel (0,0)
				img.setRGB(0, 1, Color.yellow.getRGB());//troca a cor do pixel (0,1)
			
				ImageIO.write(img, "png", new File("output.png"));//gera uma imagem com os pixels na posicao (0,0) e (0,1)
				//obs podemos colocar uma sequencia maior de pixels
			}catch(Exception e){
				e.printStackTrace();
			}
		}//fecha main
}//fecha class
