package menu;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

import autenticacao.ValidadorMarcadAgua;
import colocaMarcaDAgua.BlackSymetricImage;
import colocaMarcaDAgua.WatermarkStamper;

/**
 * Menu Iniciar que pega as caracteristicas da imagem
 *
 */
public class Menu {

	private WatermarkStamper stamper = new WatermarkStamper();
	private BlackSymetricImage fundoPreto = new BlackSymetricImage();
	private ValidadorMarcadAgua validador = new ValidadorMarcadAgua();
	
	public static void main(String[] args){
		Menu menu = new Menu();

		Scanner input = new Scanner(System.in);
		System.out.println("Digite a frase para colocar na marca d agua");
		String marcaText = input.nextLine();//texto que será a marca d agua
		
		System.out.println("Digite o caminho da sua imagem");
		String filePath = input.nextLine();//caminho da imagem para colocar a marca d agua
		
		//System.out.println("Digite a cor do texto");
		Color fontColor = Color.BLUE;//por enquanto esta uma cor nao definida pelo usuario
		
		System.out.println("Digite a fonte");
		String font = input.nextLine();//font do texto da marca de agua
		
		try {
			menu.criaMarca(filePath, fontColor, font, marcaText);//chama o metodo criaMarca para criar uma marca d agua na imagem passada no filePath
			
		System.out.println("Deseja verificar se uma imagem possui a marca de agua inserida ?");
		
		String verifica = input.nextLine();//salva a decisao do usuario para verificar ou nao uma imagem
		if(verifica.equals("sim")){
			System.out.println("Digite o caminho da imagem que voce deseja comparar");	
			filePath = input.nextLine(); // pega o caminho da imagem que deseja ser comparada
											//a ideia desse caminho aki abaixo é q seja um caminho padrao do sistema operacional
			menu.autenticacao(filePath, "/home/pedro/workspace/matematicaDiscreta/marcaDAgua/outputPreta.png");//chama o metodo autentica
			}//fecha if						esse caminho acima é o caminho da imagem da marca de agua preta
		} catch (Exception e) {
			
			e.printStackTrace();
		}//fecha try-catch
	
		
		
	}//fecha main
	
	/**
	 * Metodo que delega a criacao da imagem com a marca de agua
	 * @param filePath - caminho do arquivo
	 * @param fontColor - cor do texto(marca de agua)
	 * @param font - tipo do texto(marca de agua)
	 * @param marcaText - texto(marca de agua)
	 * @return retorna true para marca de agua adicionada com sucesso ou false para nao adicionada
	 * @throws IOException 
	 */
	public boolean criaMarca(String filePath, Color fontColor, String font, String marcaText) throws IOException{
		criaImagemFundoPreto(filePath, fontColor, font, marcaText);//cria a imagem preta com a marca de agua
		return stamper.createStampedImage(filePath, fontColor, font, marcaText);//cria a imagem com a marca de agua		
	}//fecha criaMarca
	
	/**
	 * Metodo que delega a criacao da imagem com fundo preto
	 * @param filePath - caminho do arquivo
	 * @param fontColor - cor do texto(marca de agua)
	 * @param font - tipo do texto(marca de agua)
	 * @param marcaText - texto(marca de agua)
	 * @return retorna true para imagem com fundo preto criado com sucesso ou false para nao criado
	 * @throws IOException
	 */
	private boolean criaImagemFundoPreto(String filePath, Color fontColor, String font, String marcaText) throws IOException{
		return fundoPreto.generateSymetricImage(filePath, fontColor, font, marcaText);//cria a imagem preta com a marca de agua
	}//fecha criaImagemFundoPreto
	
	/**
	 * Metodo que delega a tarefa de autenticar uma imagem
	 * @param imagemCandidata - e' a imagem que e' candidata ter a marca de agua
	 * @param imagemDeComparacao - imagem com o fundo preto gerada anteriormente
	 * @return retorna um boolean para true para imagem com marca de agua ou false para imagem sem marca de agua
	 * @throws InterruptedException
	 */
	public boolean autenticacao(String imagemCandidata, String imagemDeComparacao) throws InterruptedException{
		return validador.image(imagemCandidata, imagemDeComparacao);//valida se a marca de agua esta na imagem passada no parametro imagemCandidata
	}//vecha autenticacao
	
}//fecha class
