package py.edu.facitec.psmsystem.app;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import py.edu.facitec.psmsystem.componente.LoadingPanel;
import py.edu.facitec.psmsystem.util.Factory;

public class Loading extends JFrame {

	private LoadingPanel contentPane;
	private JLabel lblCargando;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(GraphiteLookAndFeel.class.getName());
					Loading frame = new Loading();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Loading() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 339);
		contentPane = new LoadingPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);		
		contentPane.setLayout(null);

		lblCargando = new JLabel("Cargando...");
		lblCargando.setBounds(10, 251, 248, 49);
		lblCargando.setFont(new Font("Serif", Font.BOLD, 38));
		lblCargando.setForeground(Color.BLACK);
		contentPane.add(lblCargando);


		lblVersion = new JLabel("v1.5");
		lblVersion.setBounds(345, 280, 95, 20);
		lblVersion.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblVersion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblVersion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVersion.setForeground(Color.BLACK);
		contentPane.add(lblVersion);
		abrir.start();
	}

	Timer abrir = new Timer(500, new ActionListener() {	
		public void actionPerformed(ActionEvent e) {
			abrirMenu();
			abrir.stop();
		}
	});
	private JLabel lblVersion;

	public void abrirMenu(){
		Factory.setUp();
		//conectarReporte();
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.setVisible(true);
		dispose();
	}


	//	private void conectarReporte(){
	//		ConexionReportes<VentanaPrincipal> conexionReportes = new ConexionReportes<VentanaPrincipal>();
	//		try {
	//			conexionReportes.primeraConexion();
	//		} catch (JRException e) {
	//			e.printStackTrace();
	//		}
	//	}
}
