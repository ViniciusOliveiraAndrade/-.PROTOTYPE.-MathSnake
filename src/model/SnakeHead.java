package model;

import java.awt.Point;

public class SnakeHead {
	
	//private int direction = 0;
	
	private Point cabecaLocal = new Point(0,0);
	
	
	
	public SnakeHead(){}



	public Point getCabecaLocal() {
		return cabecaLocal;
	}



	public void setCabecaLocal(Point cabecaLocal) {
		this.cabecaLocal = cabecaLocal;
	}
	
	
	
	
}
