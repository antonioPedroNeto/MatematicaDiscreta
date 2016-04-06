package colocaMarcaDAgua;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.swing.ImageIcon;
 
public class inserindoMarcaDAgua {
	
        public static void main(String[] args) {
 
                try {
                        File file = new File("//home/aramissa/Desktop/meh.png"); // cria um objeto para guardar o caminho da imagem
                        if (!file.exists()) {
                                System.out.println("File not Found");
                        }
                        
                        
                        ImageIcon icon = new ImageIcon(file.getPath()); // cria um objeto do tipo ImageIcon com o caminho especificado no objeto file
                        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);                       
                        
                        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
                        g2d.drawImage(icon.getImage(), 0, 0, null);
                        
                        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
                        
                        g2d.setComposite(alpha);
                        g2d.setColor(Color.cyan);
                        
                        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                        
                        g2d.setFont(new Font("Comic Sans", Font.BOLD, 30));
                        
                        String watermark = "era uma vez uma moca chamada maria, maria gostava de goiabada. Maria morreu.";
                        
                        g2d.drawString(watermark, icon.getIconWidth()/2, icon.getIconHeight()/ 2);
                        g2d.dispose();
                        
                        System.out.println();
                        
                        
                        
                        File fileout = new File("//home/aramissa/Desktop/mehAgua.png");//diretorio onde a marca d agua sera inserida
                        ImageIO.write(bufferedImage, "png", fileout);
                        
                } catch (IOException ioe) {
                	System.out.println(ioe.getMessage());
                }
        }
        
}