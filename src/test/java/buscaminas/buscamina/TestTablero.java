package buscaminas.buscamina;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controlador.Juego;
import modelo.Casilla;
import modelo.Tablero;

/*
 * Test de la clase tablero.
 * @author Ridouan, William
 
 */
class TestTablero {

	Tablero tablero;
	
	@BeforeEach
	public void setUp() {
		tablero = new Tablero();
	}
	/*
	 * Test del m�todo creatTablero. 
	 * Coomproba la incializaci�n y tama�o del tablero.
	 */
	@Test
	public void testCrearTablero() {
		/*Loop Test, valores limites frontera, mockObject*/
		tablero.setNMinas(2);
		tablero.crearTablero(2, 2);
		
		//Comprobar que contiene casillas
		assertNotNull(tablero.getTablero());
				
		//Limite inferior para crear tablero
		assertTrue(tablero.getNFilas()>=2 || tablero.getNColumnas()>=2);
		assertTrue(tablero.getNMinas() >=2);
		
		//Comprobar que se ha creado correctamente el tablero.
		Casilla tableroTest [][] = new Casilla[2][2];
		for(int i = 0; i< 2; i++) {
			for(int j = 0; j< 2; j++) {
				Casilla casilla = mock(Casilla.class);
				when(casilla.esTapada()).thenReturn(true);
				when(casilla.getPosX()).thenReturn(i);
				when(casilla.getPosY()).thenReturn(j);
				tableroTest [i][j] = casilla;
			}
		}
		
		int minas = 0;
		for(int i = 0; i< 2; i++) {
			for(int j = 0; j< 2; j++) {
				assertEquals(tablero.getTablero()[i][j].esTapada(), tableroTest[i][j].esTapada());
				assertEquals(tablero.getTablero()[i][j].getPosX(), tableroTest[i][j].getPosX());
				assertEquals(tablero.getTablero()[i][j].getPosY(), tableroTest[i][j].getPosY());
				if(tablero.getTablero()[i][j].esMina()) {
					minas = minas+1;
				}
			}
		}
		//Comprobar Que haya la cantidad de minas que escoge le usuario y en posiciones diferentes.
		assertEquals(minas,tablero.getNMinas());
	}
}
