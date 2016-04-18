package colocaMarcaDAgua;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import autenticacao.Diretorios;

public class WatermarkStamper {
	
	private final int FONT_SIZE = 30;//tamanho da letra da marca d agua
	private final int FONT_STYLE = Font.BOLD;//constante para a cor da marca d agua
	
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
			
			System.out.println("Arquivo de Imagem nao encontrado");
			return false;
		}

			ImageIcon icon = new ImageIcon(file.getPath()); // cria um objeto de imagem representando a imagem do caminho especificado.
			BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);                       

			Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
			g2d.drawImage(icon.getImage(), 0, 0, null);

			AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);

			g2d.setComposite(alpha);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setFont(new Font(font, FONT_STYLE, FONT_SIZE));

			//Largura da String Marca D'agua
			int stringLen = (int) g2d.getFontMetrics().getStringBounds(marcaText, g2d).getWidth();
			
			//Coordenada centralizada da string na imagem.
			int centeredStringWidth = icon.getIconWidth() / 2 - stringLen / 2;
			int centeredStringHeight = icon.getIconHeight() / 2;
			
			//Desenha Borda para a Marca d'agua
			g2d.setColor(Color.BLACK);
			
			g2d.drawString(marcaText, shiftWest(centeredStringWidth, 1), shiftNorth(centeredStringHeight, 1));
			g2d.drawString(marcaText, shiftWest(centeredStringWidth, 1), shiftSouth(centeredStringHeight, 1));
			g2d.drawString(marcaText, shiftEast(centeredStringWidth, 1), shiftNorth(centeredStringHeight, 1));
			g2d.drawString(marcaText, shiftEast(centeredStringWidth, 1), shiftSouth(centeredStringHeight, 1));
			
			//Desenha a Marca d'agua
			g2d.setColor(fontColor);
			g2d.drawString(marcaText, centeredStringWidth, centeredStringHeight);
			g2d.dispose();                        

			//diretorio onde a marca d agua sera inserida
			File fileout = new File(Diretorios.imagemProcessada+file.getName());
			
			try {
				if(file.getName().toLowerCase().endsWith("png")){
					return ImageIO.write(bufferedImage, "png", fileout);//salva a imagem com a marca d agua

				}else if(file.getName().toLowerCase().endsWith("jpg")){
					return ImageIO.write(bufferedImage, "jpg", fileout);//salva a imagem com a marca d agua

				}else{
					return false;
				}
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return false;
			}

	}
	
	/**
	 * Metodo que desloga para o norte
	 * @param p
	 * @param distance
	 * @return
	 */
	public int shiftNorth(int p, int distance) {
	   return (p - distance);
	}
	
	/**
	 * Metodo que desloca para Sul
	 * @param p
	 * @param distance
	 * @return
	 */
	public int shiftSouth(int p, int distance) {
	   return (p + distance);
	}
	
	/**
	 * Metodo que desloca para o leste
	 * @param p - valor fixo
	 * @param distance
	 * @return
	 */
	public int shiftEast(int p, int distance) {
	   return (p + distance);
	}
	
	/**
	 * Metodo que desloca para o oeste
	 * @param p
	 * @param distance
	 * @return
	 */
	public int shiftWest(int p, int distance) {
	   return (p - distance);
	}
	
}