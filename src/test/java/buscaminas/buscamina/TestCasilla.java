package buscaminas.buscamina;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

import modelo.Casilla;
import modelo.Tablero;

/*
 * Clase test de la clase casella.
 * @author Ridouan, William
 */
class TestCasilla {

	/*
	 * Test del método destapaCasilla.
	 * Comprobar que se destapa una casilla y cambia su estado.
	 */
	@Test
	public void testDestaparCasilla() {
		/*TDD, Particiones equivalentes*/
	
		Tablero tablero = new Tablero();
		tablero.crearTablero(2, 2);
		
		//Destapar casilla 1,1
		tablero.getTablero()[1][1].destaparCasilla();
		assertFalse(tablero.getTablero()[1][1].esTapada());
		
		//Casilla no destapada
		assertTrue(tablero.getTablero()[1][0].esTapada());
	}
	/*
	 * Test del método colocarMina
	 * Comprobar que se coloca la mina en la casilla indicada y cambia su estado.
	 */
	@Test
	public void testColocarMina() {
		/*TDD, particiones equivalentes*/
		
		//Colocar mina
		Casilla casilla1_1 = new Casilla(1,1);
		casilla1_1.colocarMina();
		assertTrue(casilla1_1.esMina());
		
		//casilla sin mina
		Casilla casilla1_2 = new Casilla(1,2);
		assertFalse(casilla1_2.esMina());
	}
	/*
	 * Test del método esTapada
	 * Comprobar que develve el estadocorrecto de la casilla
	 */
	@Test
	public void testEsTapada() {
		/*TDD, particiones equivalentes*/
		Casilla casilla = new Casilla(1, 1);
		assertTrue(casilla.esTapada());
		casilla.setEstadoCasilla(1);
		assertFalse(casilla.esTapada());
		
	}
	/*
	 * Test del método esMina
	 * Comprobar que develve el tipo correcto de la casilla
	 */
	@Test
	public void testEsMina() {
		/*TDD, particiones equivalentes*/
		Casilla casilla = new Casilla(1, 1);
		assertFalse(casilla.esMina());
		casilla.setTipoCasilla(1);
		assertTrue(casilla.esMina());
		
	}
}
