package colocaMarcaDAgua;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import autenticacao.Diretorios;

public class BlackSymetricImage {
	private final int FONT_SIZE = 30;
	private final int FONT_STYLE = Font.BOLD;
	
	public boolean generateSymetricImage(String filePath,Color fontColor, String font, String marcaText) throws IOException {
		File file = new File(filePath); // Caminho da imagem original
		if (!file.exists()) {
			System.out.println("Arquivo de Imagem nao encontrado.");//Imagem nao existe
			return false;
        }
        
        /**
         * Criamos uma imagem original de referencia(objeto apenas)
         */
			
        ImageIcon imagemOriginal = new ImageIcon(file.getPath()); 
        
        /**
         * Criamos uma objeto imagem que servirah para comparar a marca dagua, tem o mesmo tamanho
         * da imagem original, e serah toda preta (000).
         */
        ImageIcon symetricImage = new ImageIcon(); //Cria uma nova imagem (objeto).
        BufferedImage bufferedSymetricImage = new BufferedImage(imagemOriginal.getIconWidth(), 
        		imagemOriginal.getIconHeight(), BufferedImage.TYPE_INT_RGB);// Constroi uma imagem do tamanho da imagem original, com pixeis pretos(000).                       
        
        
        Graphics2D g2d = (Graphics2D) bufferedSymetricImage.getGraphics();
        g2d.drawImage(symetricImage.getImage(), 0, 0, null);
        
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        
        g2d.setComposite(alpha);
        g2d.setColor(fontColor);
        
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.setFont(new Font(font, FONT_STYLE, FONT_SIZE));
        
        int stringLen = (int) g2d.getFontMetrics().getStringBounds(marcaText, g2d).getWidth();//Largura da String Marca D'agua
        int centeredStringWidth = imagemOriginal.getIconWidth()/2 - stringLen/2;//Coordenada centralizada da string na imagem.
                
        g2d.drawString(marcaText, centeredStringWidth, imagemOriginal.getIconHeight()/2);
        g2d.dispose();
        
        File fileout = new File(Diretorios.imagemDeComparacao); //diretorio onde a marca d agua sera inserida
        ImageIO.write(bufferedSymetricImage, "png", fileout);
        
        return true;
        
	}
	
}
