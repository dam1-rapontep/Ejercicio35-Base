import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendrá que poder acceder a la ventana principal. Se puede lograr
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
		this.ventanaPrincipal=ventanaPrincipal;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ventanaPrincipal.mostrarNumMinasAlrededor(i, j);
		ventanaPrincipal.refrescarPantalla();
	}

}
