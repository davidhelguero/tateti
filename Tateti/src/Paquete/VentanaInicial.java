package Paquete;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInicial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_jugador1;
	private JTextField textField_jugador2;
	private VentanaJuego ventanaJuego;
	
	public static void main(String[] args) {
		
		try {
			VentanaInicial dialog = new VentanaInicial();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public VentanaInicial() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		ventanaJuego = new VentanaJuego();
		
		JLabel lbl_jugador1 = new JLabel("Ingrese el nombre del jugador 1:");
		lbl_jugador1.setBounds(42, 62, 185, 14);
		contentPanel.add(lbl_jugador1);
		
		JLabel lbl_jugador2 = new JLabel("Ingrese el nombre del jugador 2:");
		lbl_jugador2.setBounds(42, 109, 185, 14);
		contentPanel.add(lbl_jugador2);
		
		textField_jugador1 = new JTextField();
		textField_jugador1.setBounds(237, 59, 86, 20);
		contentPanel.add(textField_jugador1);
		textField_jugador1.setColumns(10);
		
		textField_jugador2 = new JTextField();
		textField_jugador2.setBounds(237, 106, 86, 20);
		contentPanel.add(textField_jugador2);
		textField_jugador2.setColumns(10);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		JButton btn_jugar = new JButton("Jugar!");
		btn_jugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nombresVacios())
					informarNombresVacios();
				else if(nombresIguales())
					informarNombresIguales();
				else {
					prepararJuego();
					cerrarVentana();
					abrirSiguienteVentana();
				}
			}
					
			private boolean nombresVacios() {
				return textField_jugador1.getText().equals("") || textField_jugador2.getText().equals("");
			}
			private void informarNombresVacios() {
				JOptionPane.showMessageDialog(contentPanel, "Ambos usuarios deben ingresar sus nombres");
			}
			private boolean nombresIguales() {
				return textField_jugador1.getText().equals(textField_jugador2.getText());
			}
			private void informarNombresIguales() {
				JOptionPane.showMessageDialog(contentPanel, "No pueden tener el mismo nombre");
			}
			private void prepararJuego() {
				ventanaJuego.prepararJuego(textField_jugador1.getText(), textField_jugador2.getText());
			}
			private void abrirSiguienteVentana() {
				ventanaJuego.frmTateti.setVisible(true);
			}
			private void cerrarVentana() {
				dispose();
			}
				
		});
		
		//BOTON OK DE LA VENTANA DE MENSAJE
		btn_jugar.setActionCommand("OK");
		buttonPane.add(btn_jugar);
		getRootPane().setDefaultButton(btn_jugar);
			
		
		
	}
}
