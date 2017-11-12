import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaPrincipal {

	// La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;
	JLabel puntos;
	int mina;
	int contadorPuntos = 0;
	VentanaPrincipal ventanaInstancia = this; //Instancia necesaria para pasarla a los listener
	ImageIcon icono;
	JLabel imagenMina;

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar después los componentes por otros
	JPanel[][] panelesJuego;
	JButton[][] botonesJuego;

	// Correspondencia de colores para las minas:
	static Color correspondenciaColores[] = { Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	JButton botonEmpezar;
	JTextField pantallaPuntuacion;

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;

	// Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
	}

	// Inicializa todos los componentes del frame
	public void inicializarComponentes() {

		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelImagen = new JPanel();
		panelImagen.setLayout(new GridLayout(1,1));
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10, 10));

		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}

		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		// BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);

		// Panel imagen
		BufferedImage imagen = null;
		imagenMina = new JLabel();
		
		try {
			imagen = ImageIO.read(new File("D:\\Escritorio\\2� DAM\\Desarrollo de interfaces\\TEMA 2\\imagenBusca.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		icono=new ImageIcon(imagen.getScaledInstance(50,50, Image.SCALE_SMOOTH));
		imagenMina.setIcon(icono);
		imagenMina.setHorizontalAlignment(SwingConstants.CENTER);
		
		settings = new GridBagConstraints();
		settings.gridx=0;
		settings.gridy=0;
		panelImagen.add(imagenMina,settings);
	}

	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el
	 * programa
	 */
	public void inicializarListeners() {

		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j].addActionListener(new ActionBoton(ventanaInstancia, i, j));
			}
		}

		botonEmpezar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
				ventana = new JFrame();
				ventana.setBounds(100, 100, 700, 500);
				ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				juego = new ControlJuego();
				contadorPuntos=0;
				inicializar();

			}
		});
	}

	/**
	 * Método que pinta en la pantalla el número de minas que hay alrededor de la
	 * celda Saca el botón que haya en la celda determinada y añade un JLabel
	 * centrado y no editable con el número de minas alrededor. Se pinta el color
	 * del texto según la siguiente correspondecia (consultar la variable
	 * correspondeciaColor): - 0 : negro - 1 : cyan - 2 : verde - 3 : naranja - 4 ó
	 * más : rojo
	 * 
	 * @param i:
	 *            posición vertical de la celda.
	 * @param j:
	 *            posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {

		mina = juego.getMinasAlrededor(i, j);// esto devuelve un numero que son las minas que hay alrededor de esa
		JLabel sustituto = new JLabel();
		sustituto.setForeground(Color.WHITE);
		sustituto.setText(Integer.toString(mina));
		sustituto.setHorizontalAlignment(SwingConstants.CENTER);
		panelesJuego[i][j].removeAll();
		refrescarPantalla();
		panelesJuego[i][j].add(sustituto);
		panelesJuego[i][j].setBackground(aplicarColores(mina));
		refrescarPantalla();

	}

	/*
	 * metodo que devuelve el color asignado a un label seg�n sea mina o no
	 */
	public static Color aplicarColores(int mina) {

		if (mina == -1) {
			return Color.WHITE;

		} else {
			return correspondenciaColores[mina];
		}

	}

	/**
	 * Método que muestra una ventana que muestra el fin del juego
	 * 
	 * @param porExplosion
	 *            : Un booleano que indica si es final del juego porque ha explotado
	 *            una mina (true) o bien porque hemos desactivado todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(boolean formaPerder) {
		if (formaPerder) {
			JOptionPane.showMessageDialog(ventana, "Boom!! Has perdido");
			
			// for que recorre los botones del tablero y los desactiva
			for (int i = 0; i < botonesJuego.length; i++) {
				for (int j = 0; j < botonesJuego.length; j++) {
					botonesJuego[i][j].setEnabled(false);
				}
			}
			JOptionPane.showMessageDialog(ventana, "Puntuaci�n "+(getJuego().getPuntuacion()-1)+"\nPara iniciar otra partida pulse GO");
			
			
		} else {
			JOptionPane.showMessageDialog(ventana, "Enhorabuena!! Has desactivado todas las bombas");
		}

	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		puntos = new JLabel();
		panelPuntuacion.setLayout(new GridBagLayout());
		if (mina != -1) {
			contadorPuntos++;
			panelPuntuacion.removeAll();
			refrescarPantalla();
			puntos.setText(Integer.toString(contadorPuntos));
			panelPuntuacion.add(puntos);

		}
	}

	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS
		// COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}

}
