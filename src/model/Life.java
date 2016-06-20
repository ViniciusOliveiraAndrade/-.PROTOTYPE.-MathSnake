package model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Life {
	BufferedImage spriteSheet = ImageIO.read(new File("res/life.png"));   

	int largura, tamanho;
	int rows=1, columns=5;
	public int posX;

	public int posY;
	public BufferedImage[] sprites;
	public int aparencia=0;
	
	public Life(int aparencia, int width, int height, int columns, int rows, int posX, int posY) throws IOException {
		this.aparencia=aparencia;
		this.largura = width;
		this.tamanho = height;
		
//		this.width = spriteSheet.getWidth()/columns;
//		this.height = spriteSheet.getHeight()/rows;

		this.rows = columns;
		this.columns = rows;
		this.posX=posX;
		this.posY=posY;

		sprites = new BufferedImage[columns * rows];
		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
			}
		}
		if(aparencia>=5){
			aparencia++;
		}
	}

}
