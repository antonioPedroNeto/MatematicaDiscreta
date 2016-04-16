package menu;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import autenticacao.Diretorios;
import autenticacao.ValidadorMarcadAgua;
import colocaMarcaDAgua.BlackSymetricImage;
import colocaMarcaDAgua.WatermarkStamper;

/**
 * Menu Iniciar que pega as caracteristicas da imagem
 *
 */

public class Menu {

	static WatermarkStamper stamper = new WatermarkStamper();
	static BlackSymetricImage fundoPreto = new BlackSymetricImage();
	static ValidadorMarcadAgua validador = new ValidadorMarcadAgua();
	static Scanner input = new Scanner(System.in);
	static final String BREAK_LINE = System.getProperty("line.separator");
	
	static Color COR_FONTE = Color.PINK;
	static final String FONTE = "Comic Sans";
	
	static final int INSERIR_MARCA = 1;
	static final int VERIFICAR_IMAGENS = 2;
	static final int SAIR = 3;	

	
	public static void main(String[] args) throws IOException{
		int opcao;
		
		String tela = "[Menu]"+BREAK_LINE+
				"Opções :"+BREAK_LINE+
				"[1] Inserir Marca Numa Imagem."+BREAK_LINE+
				"[2] Verificar Marca em várias Imagens."+BREAK_LINE+
				"[3] Sair."+BREAK_LINE;
		
		System.out.println(tela);
		
		opcao = input.nextInt();
		input.nextLine();
		
		while(opcao != SAIR){
			
			switch (opcao) {
			
				case INSERIR_MARCA:
				
					System.out.println("Digite o caminho da sua imagem");
					String filePath = input.nextLine();//caminho da imagem para colocar a marca d agua
				
					System.out.println("Digite a frase para colocar na marca d agua");
					String marcaText = input.nextLine();//texto que será a marca d agua
				
					stamper.createStampedImage(filePath, COR_FONTE, FONTE, marcaText);
					//chama o metodo criaMarca para criar uma marca d agua na imagem passada no filePath
					break;
				
				case VERIFICAR_IMAGENS:
				
					System.out.println("Digite o caminho das imagens que voce deseja comparar: ");
					String targetDir = input.nextLine(); // pega o caminho das imagens a serem comparadas
					
					File diretorio = new File(targetDir);
					
					System.out.println("Digite o texto que representa a marca d'agua: ");
					String targetText = input.nextLine();
					
					ArrayList<String> imagens = Diretorios.getAllImages(diretorio);
										
					for (String caminhoImagem : imagens) {
						
						BufferedImage imagemPreta = fundoPreto.generateSymetricImage(caminhoImagem, COR_FONTE, FONTE, targetText);
						try {
							validador.validar(caminhoImagem,imagemPreta, COR_FONTE, FONTE);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					
					break;
					
				case SAIR:
					break;
					
				default:
					System.out.println("Opcao invalida, tente novamente.");
					break;
			}
			System.out.println(tela);
			opcao = input.nextInt();
			
		}
		


		
		
	}
	
		
		

	
}
