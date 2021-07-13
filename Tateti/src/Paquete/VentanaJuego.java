package Paquete;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Window.Type;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class VentanaJuego {

	public JFrame frmTateti;
	private Juego juego;
	private JLabel lbl_info;
	private JLabel lbl_cantTurnos;
	private String jugador1;
	private String jugador2;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9; 
	JButton btnJugarDeNuevo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego window = new VentanaJuego();
					window.frmTateti.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaJuego() {

//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		initialize();
	}

	private void initialize() {
		frmTateti = new JFrame();
		frmTateti.setTitle("TaTeTi");
		frmTateti.setBounds(100, 100, 450, 300);
		frmTateti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTateti.getContentPane().setLayout(null);
		juego = new Juego();
		
		//DESACTIVAR LA BARRA ESPACIADORA
		InputMap im = (InputMap)UIManager.get("Button.focusInputMap"); 
		im.put(KeyStroke.getKeyStroke("pressed SPACE"), "none"); 
		im.put(KeyStroke.getKeyStroke("released SPACE"), "none");
		
		//LABEL PARA MOSTRAR TURNO Y GANADOR
		lbl_info = new JLabel("");
		lbl_info.setBounds(151, 200, 147, 14);
		frmTateti.getContentPane().add(lbl_info);
		
		//BOTONES
		btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn1,0,0);
			}
		});
		btn1.setBounds(151, 43, 50, 50);
		frmTateti.getContentPane().add(btn1);
		
		btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn2,1,0);
			}
		});
		btn2.setBounds(199, 43, 50, 50);
		frmTateti.getContentPane().add(btn2);
		
		btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn3,2,0);
			}
		});
		btn3.setBounds(248, 43, 50, 50);
		frmTateti.getContentPane().add(btn3);
		
		btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn4,0,1);
			}
		});
		btn4.setBounds(151, 91, 50, 50);
		frmTateti.getContentPane().add(btn4);
		
		btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn5,1,1);
			}
		});
		btn5.setBounds(199, 91, 50, 50);
		frmTateti.getContentPane().add(btn5);
		
		btn6 = new JButton("");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn6,2,1);
			}
		});
		btn6.setBounds(248, 91, 50, 50);
		frmTateti.getContentPane().add(btn6);
		
		btn7 = new JButton("");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn7,0,2);
			}
		});
		btn7.setBounds(151, 139, 50, 50);
		frmTateti.getContentPane().add(btn7);
		
		btn8 = new JButton("");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn8,1,2);
			}
		});
		btn8.setBounds(199, 139, 50, 50);
		frmTateti.getContentPane().add(btn8);
		
		btn9 = new JButton("");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionar(btn9,2,2);
			}
		});
		btn9.setBounds(248, 139, 50, 50);
		frmTateti.getContentPane().add(btn9);
		
		lbl_cantTurnos = new JLabel("");
		lbl_cantTurnos.setBounds(132, 225, 192, 14);
		frmTateti.getContentPane().add(lbl_cantTurnos);
		
		btnJugarDeNuevo = new JButton("Jugar de nuevo");
		btnJugarDeNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarPartida();
			}

			
		});
		btnJugarDeNuevo.setBounds(277, 200, 147, 23);
		frmTateti.getContentPane().add(btnJugarDeNuevo);
		ocultarBotonJugarDeNuevo();
		
	}
	
	void accionar(JButton button, int columna, int fila){
			inhabilitarBoton(button);
			dibujarSimbolo(button);
			enviarDatos(columna, fila);
			if(!hayGanador()) 
				informarSiguienteTurno();
			else {
				inhabilitarTodosLosBotones();
				mostrarGanador();
				mostrarCantidadTurnos();
				btnJugarDeNuevo.setVisible(true);
			}
	}

	private void informarSiguienteTurno() {
		lbl_info.setText("Es el turno de " + turno());
	}

	private boolean hayGanador() {
		return juego.hayGanador();
	}

	private void enviarDatos(int columna, int fila) {
		juego.jugar(columna,fila);
	}

	private void dibujarSimbolo(JButton button) {
		button.setText(String.valueOf(jugadorSimbolo()));
	}

	private void inhabilitarBoton(JButton button) {
		button.setEnabled(false);
	}

	private void mostrarCantidadTurnos() {
		lbl_cantTurnos.setText("Cantidad de turnos jugados: " + cantTurnos());
	}

	private void mostrarGanador() {
		lbl_info.setText("El ganador es " + ganador());
	}
	
	char jugadorSimbolo() {
		return juego.getTurno();
	}
	void prepararJuego(String jug1, String jug2) {
		registrarJugadores(jug1, jug2);
		avisarQuienComienza();
	}
	void registrarJugadores(String jug1, String jug2) {
		jugador1 = jug1;
		jugador2 = jug2;
	}
	void avisarQuienComienza() {
		lbl_info.setText("Comienza " + turno());
	}
	String turno() {
		return jugador(juego.getTurno());
	}
	String ganador() {
		return jugador(juego.getGanador());
	}
	private String jugador(char jugador) {
		return jugador == 'X' ? jugador1 : jugador2;
	}
	
	private int cantTurnos() {
		return juego.getCantTurnos();
	}
	
	private void inhabilitarTodosLosBotones() {
		JButton[] botones = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
		for(JButton boton:botones)
			inhabilitarBoton(boton);
	}
	
	private void reiniciarPartida() {
		juego.reiniciar();
		blanquearBotones();
		habilitarTodosLosBotones();
		ocultarCantTurnos();
		ocultarBotonJugarDeNuevo();
		avisarQuienComienza();
	}

	private void ocultarBotonJugarDeNuevo() {
		btnJugarDeNuevo.setVisible(false);
	}

	private void ocultarCantTurnos() {
		lbl_cantTurnos.setText("");
	}
	
	private void habilitarTodosLosBotones() {
		JButton[] botones = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
		for(JButton boton:botones)
			habilitarBoton(boton);
	}
	
	private void habilitarBoton(JButton button) {
		button.setEnabled(true);
	}
	
	private void blanquearBotones() {
		JButton[] botones = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
		for(JButton boton:botones)
			boton.setText("");
	}
}
