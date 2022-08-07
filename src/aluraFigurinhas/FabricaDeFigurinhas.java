package aluraFigurinhas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class FabricaDeFigurinhas {
	
	public void cria(InputStream intputStream, String nomeArquivo, String nota) throws IOException {
		
	BufferedImage imagemOriginal =  ImageIO.read(intputStream);
	
	int altura = imagemOriginal.getHeight();
	int largura = imagemOriginal.getWidth();
	int novaAltura = altura + 400;
	BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
	
	Graphics2D graficos = (Graphics2D) novaImagem.getGraphics();
	graficos.drawImage(imagemOriginal,0,0,null);
	
	Font fonte =  new Font(Font.SANS_SERIF,Font.BOLD,60);
	graficos.setColor(Color.yellow);
	graficos.setFont(fonte);
	
	graficos.drawString("Nota: "+ nota, largura/2 , novaAltura - 100);
	
	File diretorio = new File("saida");
	if (!diretorio.exists()) diretorio.mkdirs();
	
	ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));

		
	}
	

}
