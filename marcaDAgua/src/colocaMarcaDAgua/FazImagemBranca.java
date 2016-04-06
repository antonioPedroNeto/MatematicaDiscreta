package colocaMarcaDAgua;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.swing.ImageIcon;

public class FazImagemBranca {
	
	public static void main(String[] args) {
		try {
			File file = new File("//home/aramissa/Desktop/meh.png"); // Caminho da imagem original
			if (!file.exists()) {
                System.out.println("File not Found");//Imagem nao existe
        }
        
        /**
         * Criamos uma imagem original de referencia(objeto apenas)
         */
        ImageIcon imagemOriginal = new ImageIcon(file.getPath()); 
        BufferedImage bufferedImage = new BufferedImage(imagemOriginal.getIconWidth(),
        		imagemOriginal.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        
        /**
         * Criamos uma objeto imagem que servirah para comparar a marca dagua, tem o mesmo tamanho
         * da imagem original, e serah toda preta (000).
         */
        ImageIcon imagemPreta = new ImageIcon(); //Cria uma nova imagem no caminho especificado
        BufferedImage bufferedImageBranco = new BufferedImage(imagemOriginal.getIconWidth(), 
        		imagemOriginal.getIconHeight(), BufferedImage.TYPE_INT_RGB);// Constroi uma imagem do tamanho especificado, com pixeis pretos(000).                       
        
        
        Graphics2D g2d = (Graphics2D) bufferedImageBranco.getGraphics();
        g2d.drawImage(imagemPreta.getImage(), 0, 0, null);
        
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        
        g2d.setComposite(alpha);
        g2d.setColor(Color.cyan);
        
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.setFont(new Font("Comic Sans", Font.BOLD, 30));
        
        String watermark = "era uma vez uma moca chamada maria, maria gostava de goiabada. Maria morreu.";
        
        g2d.drawString(watermark, imagemOriginal.getIconWidth()/2, imagemOriginal.getIconHeight()/ 2);
        g2d.dispose();
        
        System.out.println();
        
        
        
        File fileout = new File("//home/aramissa/Desktop/mehAguaBranca.png");//diretorio onde a marca d agua sera inserida
        ImageIO.write(bufferedImageBranco, "png", fileout);
        
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
}
}


	
}
