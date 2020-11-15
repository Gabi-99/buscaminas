package buscaminas.buscamina;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controlador.Juego;
import modelo.Casilla;
import modelo.Tablero;

/*
 * Clase test de la clase Juego.
 * @author Ridouan, William
 */
class TestJuego {

	Juego juego;
	Tablero tablero;
	
	@BeforeEach
	public void setUp() {
		juego = new Juego();
	}
	
	/*
	 * Test del método de comprobar el estado de juego y resultado.
	 */
	@Test
	public void testComprobarJuego() {
		
		/*Particiones equivalentes, TDD*/
	
		juego.iniciarJuego("2 2 2");
		
		//CASO COMPROBAR UNA CASILLA SIN MINA
		juego.getTablero().getTablero()[1][1].setEstadoCasilla(0);
		juego.getTablero().getTablero()[1][1].setTipoCasilla(0);
		juego.comprobarJuego("1 1");
		assertTrue(juego.getEstadoJuego());
		
		//CASO DE PERDER
		juego.getTablero().getTablero()[1][1].setEstadoCasilla(0);
		juego.getTablero().getTablero()[1][1].setTipoCasilla(1);
		
		juego.comprobarJuego("1 1");
		assertFalse(juego.getEstadoJuego());
		assertFalse(juego.juegoGanado());
		
		//CASO DE GANAR
		juego.getTablero().getTablero()[1][1].setEstadoCasilla(0);
		juego.getTablero().getTablero()[1][1].setTipoCasilla(0);
		
		juego.setCasillasDestapadas(1);
		juego.comprobarJuego("1 1");
		assertFalse(juego.getEstadoJuego());
		assertTrue(juego.juegoGanado());
		assertEquals(juego.getCasillasDestapadas(),2);
		
	}
	
	/*
	 * Test del método de inciar el juego.
	 */
	@Test
	public void testIniciarJuego() {
		
		/*particiones equivalentes, TDD*/
		
		juego.iniciarJuego("2 2 2");
		
		//datos correctos
		assertTrue(juego.datosCorrectos());
		
		//Estado del juego
		assertTrue(juego.getEstadoJuego());
	
	}
	
	/*
	 * Test del m�todo de finalizar el juego.
	 */
	@Test
	public void testFinalizarJuego() {
		
		/*particiones equivalentes, TDD*/
		juego.iniciarJuego("2 2 2");
		assertTrue(juego.getEstadoJuego());
		juego.finalizarJuego();
		assertFalse(juego.getEstadoJuego());
		
	}
	
	/*
	 * Test del método mostrarTablero, que muestra la matriz de casillas que componen el tablero.
	 */
	@Test
	public void testMostrarTablero(){
		/*mockObject*/
		juego.iniciarJuego("2 2 2");
		juego.mostrarTablero();
		
		juego.iniciarJuego("4 4 4");
		Casilla [][] tab = new Casilla[4][4];
		for(int i = 0; i< 4; i++) {
			for(int j = 0; j< 4; j++) {
				Casilla casilla = mock(Casilla.class);
				when(casilla.getPosX()).thenReturn(i);
				when(casilla.getPosY()).thenReturn(j);
				if(i == 2) {
					when(casilla.esTapada()).thenReturn(false);
				}else if(i == 1 && j == 3) {
					when(casilla.esTapada()).thenReturn(false);
					when(casilla.esMina()).thenReturn(true);
				}else {
					when(casilla.esTapada()).thenReturn(true);
				}
				
				tab[i][j] = casilla;
			}
		}
		juego.getTablero().setTablero(tab);
		juego.mostrarTablero();
	}
	
	/*
	 * Test del método de leerdatosInicia, que lee por teclado las filas, columnas y minas para incializar el juego.
	 */
	@Test
	public void testLeerDatosInicio(){
		/*TDD, Valores limites, particiones equivalentes*/
		
		//CASO ENTRADA CORRECTA
		juego.leerDatosInicio("4 4 2");
		assertTrue(juego.datosCorrectos());
		
		//CASO ENTRADA INCORRECTA
		juego.leerDatosInicio("4 4");
		assertFalse(juego.datosCorrectos());
		juego.leerDatosInicio("4 a a");
		assertFalse(juego.datosCorrectos());
		juego.leerDatosInicio(" ");
		assertFalse(juego.datosCorrectos());
		
		//CASO FILAS
		//Debajo del limite inferior
		juego.leerDatosInicio("1 2 2");
		assertFalse(juego.datosCorrectos());
		
		//CASO COLUMNAS
		//Debajo del limite inferior
		juego.leerDatosInicio("2 1 2");
		assertFalse(juego.datosCorrectos());
				
		//CASO MINAS
		//Debajo del limite inferior.
		juego.leerDatosInicio("4 4 0");
		assertFalse(juego.datosCorrectos());
		
		//Encima del limite superior.
		juego.leerDatosInicio("4 4 18");
		assertFalse(juego.datosCorrectos());
		
	}
	/*
	 * Test del método de seleccionarCasilla, que lee por teclado la fila y columna para destapar la casilla.
	 */
	@Test
	public void testSeleccionarCasilla(){
		/*TDD, Valores limites frontera, particiones equivalentes*/
		
		juego.iniciarJuego("2 2 2");
		
		//CASO ENTRADA CORRECTA
	    Casilla casilla =juego.selecionarCasilla("0 0");
	    assertTrue(juego.datosCorrectos());
	    assertEquals(casilla.getPosX(),0);
	    assertEquals(casilla.getPosY(),0);
	    
	    //CASO ENTRADA INCORRECTA
	    juego.selecionarCasilla("0");
	    assertFalse(juego.datosCorrectos());
	    juego.selecionarCasilla("a a");
	    assertFalse(juego.datosCorrectos());
	    juego.selecionarCasilla(" ");
	    assertFalse(juego.datosCorrectos());
	    
	    //CASO FILA (POS X)
	    //Debajo del limite inferior
	    juego.selecionarCasilla("-1 1");
	    assertFalse(juego.datosCorrectos());
		
	    //Encima del limite inferior
	    juego.selecionarCasilla("3 1");
	    assertFalse(juego.datosCorrectos());
	    
	    //FCASO COLUMNA (POS Y)
	    //Debajo del limite inferior
	    juego.selecionarCasilla("1 -1");
	    assertFalse(juego.datosCorrectos());
		
	    //Encima del limite inferior
	    juego.selecionarCasilla("1 3");
	    assertFalse(juego.datosCorrectos());
		
	}
	/*
	 * Test método menuOpcionesFinalizar
	 * Comprueba los valores de entrada al finalizar el juego.
	 */
	@Test
	public void testMenuOpcionesFinalizar() {
		
		//CASO OPCIÓN CORRECTA
		int valor3 = juego.menuOpcionesFinalizar("0");
		assertEquals(valor3,0);
		int valor4 = juego.menuOpcionesFinalizar("1");
		assertEquals(valor4,1);
		
		//CASO OPCIÓN INCORRECTA
		int valor = juego.menuOpcionesFinalizar(" ");
		assertEquals(valor,-1);
		int valor5 = juego.menuOpcionesFinalizar("a");
		assertEquals(valor5,-1);
		//Limite inferior
		int valor1 = juego.menuOpcionesFinalizar("-2");
		assertEquals(valor1,-1);
		//Limite Superior
		int valor2 = juego.menuOpcionesFinalizar("2");
		assertEquals(valor2,-1);
	}
}
