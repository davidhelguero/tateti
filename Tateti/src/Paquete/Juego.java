package Paquete;

import java.util.Random;
//ESTE TATETI SIEMPRE TIENE UN GANADOR
public class Juego {
	private char[][] tablero;
	private char turno;
	private boolean hayGanador;
	private char ganador;
	private int cantTurnos;
	
	Juego(){
		tablero = new char[3][3];
		darTurno();
		hayGanador = false;
		cantTurnos = 0;
	}
	
	private void darTurno() {
		Random random = new Random();
		if(random.nextInt(2)==0)
			turno = 'O';
		else
			turno = 'X';
	}
	
	void cambiarTurno() {
		if(turno == 'O')
			turno = 'X';
		else
			turno = 'O';
	}
	
	private void contarTurno() {
		cantTurnos++;
	}
	
	public char getTurno() {
		return turno;
	}
	
	public int getCantTurnos() {
		return cantTurnos;
	}

	void jugar(int columna, int fila) {
		if(posicionVacia(columna, fila)) {
			
			tablero[columna][fila] = turno;
			contarTurno();
			if(gano(turno)) {
				hayGanador = true;
				ganador = turno;
			}
			else
				cambiarTurno();
		}
	}

	private boolean posicionVacia(int columna, int fila) {
		return tablero[columna][fila]!='X' && tablero[columna][fila]!='O';
	}
	
	public boolean hayGanador() {
		return hayGanador;
	}
	
	public char getGanador() {
		return ganador;
	}

	boolean gano(char jug) {
		return ganoPorFila(jug) || ganoPorColumna(jug) || ganoPorDiagonal(jug);
	}
	//00 | 10 | 20
	//01 | 11 | 21
	//02 | 12 | 22
	private boolean ganoPorFila(char jug) {
		return tablero[0][0]==jug && tablero[1][0]==jug && tablero[2][0]==jug ||
		       tablero[0][1]==jug && tablero[1][1]==jug && tablero[2][1]==jug ||
		       tablero[0][2]==jug && tablero[1][2]==jug && tablero[2][2]==jug;
	}
	private boolean ganoPorColumna(char jug) {
		return tablero[0][0]==jug && tablero[0][1]==jug && tablero[0][2]==jug ||
		       tablero[1][0]==jug && tablero[1][1]==jug && tablero[1][2]==jug ||
		       tablero[2][0]==jug && tablero[2][1]==jug && tablero[2][2]==jug;
	}
	private boolean ganoPorDiagonal(char jug) {
			   //DIAGONALES EN BAJADA
		return tablero[0][1]==jug && tablero[1][0]==jug && tablero[2][2]==jug ||
		       tablero[0][2]==jug && tablero[1][1]==jug && tablero[2][0]==jug ||
		       tablero[1][2]==jug && tablero[2][1]==jug && tablero[0][0]==jug ||
		       //DIAGONALES EN SUBIDA
		       tablero[0][1]==jug && tablero[1][2]==jug && tablero[2][0]==jug ||
		       tablero[0][0]==jug && tablero[1][1]==jug && tablero[2][2]==jug ||
		       tablero[1][0]==jug && tablero[2][1]==jug && tablero[0][2]==jug;
	}
	
	public void reiniciar() {
		for(int i=0;i<tablero.length;i++) {
			for(int j=0;j<tablero[0].length;j++) {
				 tablero[i][j]=' ';
			}
		}
		darTurno();
		hayGanador = false;
		ganador = ' ';
		cantTurnos = 0;
	}	
}
