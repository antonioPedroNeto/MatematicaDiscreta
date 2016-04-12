package menu;

import java.awt.Color;
import java.util.Scanner;

import colocaMarcaDAgua.WatermarkStamper;
/**
 * Menu Iniciar que pega as caracteristicas da imagem
 * @author antoniopdan
 *
 */
public class Menu {

	
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Digite a frase para colocar na marca d agua");
		String marcaText = input.nextLine();
		
		System.out.println("Digite o caminho da sua imagem");
		String filePath = input.nextLine();
		
		//System.out.println("Digite a cor do texto");
		Color fontColor = Color.BLUE;
		
		System.out.println("Digite a fonte");
		String font = input.nextLine();
		
		WatermarkStamper stamper = new WatermarkStamper();
		
		stamper.createStampedImage(filePath, fontColor, font, marcaText);
		
	}//fecha main
	
	
	
}//fecha class
