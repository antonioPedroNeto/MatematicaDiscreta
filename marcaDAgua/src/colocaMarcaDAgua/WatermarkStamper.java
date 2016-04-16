package colocaMarcaDAgua;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.swing.ImageIcon;

import autenticacao.Diretorios;

public class WatermarkStamper {

	private final int FONT_SIZE = 30;
	private final int FONT_STYLE = Font.BOLD;
	
	/**
	 * Metodo que cria uma imagem com a marca d agua em uma imagem
	 * @param filePath - caminho da imagem 
	 * @param fontColor - cor da letra da marca d agua
	 * @param font - font da imagem
	 * @param marcaText - texto para ser colocado na imagem
	 * @return - retorna true para imagem marcada com sucesso ou false para nao marcada
	 */
	public boolean createStampedImage(String filePath,Color fontColor, String font, String marcaText) {

		File file = new File(filePath); // cria um objeto para guardar o caminho da imagem
		if (!file.exists()) {
			System.out.println("Arquivo de Imagem nao Encontrado");
		}


			ImageIcon icon = new ImageIcon(file.getPath()); // cria um objeto de imagem representando a imagem do caminho especificado.
			BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);                       

			Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
			g2d.drawImage(icon.getImage(), 0, 0, null);

			AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);

			g2d.setComposite(alpha);
			
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			g2d.setFont(new Font(font, FONT_STYLE, FONT_SIZE));

			int stringLen = (int) g2d.getFontMetrics().getStringBounds(marcaText, g2d).getWidth();//Largura da String Marca D'agua
			int centeredStringWidth = icon.getIconWidth() / 2 - stringLen / 2;//Coordenada centralizada da string na imagem.
			int centeredStringHeight = icon.getIconHeight() / 2;
			
			//Desenha Borda para a Marca d'agua
			g2d.setColor(Color.BLACK);
			
			g2d.drawString(marcaText, ShiftWest(centeredStringWidth, 1), ShiftNorth(centeredStringHeight, 1));
			g2d.drawString(marcaText, ShiftWest(centeredStringWidth, 1), ShiftSouth(centeredStringHeight, 1));
			g2d.drawString(marcaText, ShiftEast(centeredStringWidth, 1), ShiftNorth(centeredStringHeight, 1));
			g2d.drawString(marcaText, ShiftEast(centeredStringWidth, 1), ShiftSouth(centeredStringHeight, 1));
			
			//Desenha a Marca d'agua
			g2d.setColor(fontColor);
			g2d.drawString(marcaText, centeredStringWidth, centeredStringHeight);
			g2d.dispose();                        

			File fileout = new File(Diretorios.imagemProcessada);//diretorio onde a marca d agua sera inserida
			
			try {
				return ImageIO.write(bufferedImage, "png", fileout);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

	}
	
	int ShiftNorth(int p, int distance) {
		   return (p - distance);
		   }
	int ShiftSouth(int p, int distance) {
		   return (p + distance);
		   }
	int ShiftEast(int p, int distance) {
		   return (p + distance);
		   }
	int ShiftWest(int p, int distance) {
		   return (p - distance);
		   }
}