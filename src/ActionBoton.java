import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendr√° que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener {
	int i;
	int j;
	VentanaPrincipal ventanaPrincipal;

	public ActionBoton(VentanaPrincipal ventanaPrincipal, int i, int j) {
		this.i = i;
		this.j = j;
		this.ventanaPrincipal = ventanaPrincipal;
	}

	/**
	 * Acci√≥n que ocurrir√° cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (ventanaPrincipal.getJuego().abrirCasilla(i, j)) {
			ventanaPrincipal.mostrarNumMinasAlrededor(i, j);// accedemos al metodo con los parametros de i, j
			ventanaPrincipal.actualizarPuntuacion();
			ventanaPrincipal.refrescarPantalla();

			if (ventanaPrincipal.getJuego().esFinJuego()) {// comprueba si la puntuaciÛn es la m·xima
				ventanaPrincipal.mostrarFinJuego(false);// es la m·xima, entonces has desactivado todas las bombas
			}
			if (ventanaPrincipal.getJuego().getMinasAlrededor(i, j) == -1) {
				ventanaPrincipal.mostrarFinJuego(true);
			}
		}

	}

}
