package modelo;

/*
 * Clase que implementa el objeto casella.
 * @author Ridouan, William
 */
public class Casilla {

	//variables
	private int posX;
	private int posY;
	private int tipoCasella; //0: sin mina, 1:con mina
	private int estadoCasella; //0:tapada, 1:destapada
	
	//Constructor de la clase
	public Casilla(int x, int y) {
		posX = x;
		posY = y;
		tipoCasella = 0;
		estadoCasella = 0;
	}
	
	/*
	 * Método para destapar una casella.
	 * @return: int finalizar
	 */
	public int destaparCasilla() {
		int finalizar = 0;
		
		if(esTapada() && esMina()) {
			finalizar = 1;
			setEstadoCasilla(1);
		}else if(esTapada()) {
			setEstadoCasilla(1);
		}
		return finalizar;
	}
	
	/*
	 * Método para colocar una mina en una casilla.
	 */
	public void colocarMina() {
		setTipoCasilla(1);
	}
	
	/*
	 * Método para comprobar si la casilla contiene mina o no.
	 * @Return boolean 
	 */
	public boolean esMina() {
		boolean result = true;
		if(tipoCasella == 0) {
			result = false;
		}
		return result;
	}
	
	/*
	 * Método para comprobar si la casilla esta tapada o no.
	 * @Return boolean 
	 */
	public boolean esTapada() {
		boolean result = false;
		if(estadoCasella == 0) {
			result = true;
		}
		return result;
	}
	/*
	 * Devolver la posición x, la fila del tablero.
	 * return int posX
	 */
	public int getPosX() {
		return posX;
	}
	
	/*
	 * Devolver la posición y, la columnas del tablero.
	 * return int posY
	 */
	public int getPosY() {
		return posY;
	}
	
	/*
	 * Actualizar el tipo de casilla
	 * Params int tipo
	 */
	public void setTipoCasilla(int tipo) {
		tipoCasella = tipo;
	}
	/*
	 * Actualizar el estado de casilla
	 * Params int estado
	 */
	public void setEstadoCasilla(int estado) {
		estadoCasella = estado;
	}

}
