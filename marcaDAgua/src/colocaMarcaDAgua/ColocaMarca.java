package colocaMarcaDAgua;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.ImageIcon;
import java.awt.geom.Rectangle2D;
 
public class ColocaMarca {
	
        public static void main(String[] args) {
 
                try {
                        File file = new File("C:/teste/logo.jpg"); // cria um objeto para guardar o caminho da imagem
                        
                        
                        if (!file.exists()) {
                                System.out.println("File not Found");
                        }//fecha if
                        
                        
                        ImageIcon icon = new ImageIcon(file.getPath()); // cria um objeto do tipo ImageIcon com o caminho especificado no objeto file
                        
                        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        
                        bufferedImage.setRGB(0, 0, 0);
                        
                        
                        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
                        g2d.drawImage(icon.getImage(), 0, 0, null);
                        
                        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
                        
                        g2d.setComposite(alpha);
                        g2d.setColor(Color.cyan);
                        
                        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                        
                        g2d.setFont(new Font("Arial", Font.BOLD, 30));
                        
                        String watermark = "Striker";
                        
                        FontMetrics fontMetrics = g2d.getFontMetrics();
                        Rectangle2D rect = fontMetrics.getStringBounds(watermark, g2d);
                        g2d.drawString(watermark, (icon.getIconWidth() - (int) rect.getWidth()) / 2, (icon.getIconHeight() - (int) rect.getHeight()) / 2);
                        g2d.dispose();
                        
                        File fileout = new File("C:\\OCR\\teste.jpg");//diretorio onde a marca d agua sera inserida
                        ImageIO.write(bufferedImage, "jpg", fileout);
                } catch (IOException ioe) {
                	System.out.println(ioe.getMessage());
                }
        }//fecha main
        
}//fecha class