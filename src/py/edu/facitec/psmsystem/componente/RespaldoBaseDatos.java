package py.edu.facitec.psmsystem.componente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class RespaldoBaseDatos extends JFrame {
	private JFileChooser seleccion = new JFileChooser();
	private JTextField tfRespaldo;
	private JTextField tfRestablecer;
	
	private String host = "localhost";
	private String puerto = "5432";
	private String usuario = "postgress";
	private String password = "12345";
	private String basedatos = "psmsystem_db";
	private String formato = "custom";
	
	Process proceso;
	ProcessBuilder constructorProceso;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RespaldoBaseDatos frame = new RespaldoBaseDatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RespaldoBaseDatos() {
		setTitle("Respaldo/Recupera base de datos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RespaldoBaseDatos.class.getResource("/img/icono.png")));
		setBounds(100, 100, 550, 215);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblRespaldo = new JLabel("Guardar respaldo en:");
		lblRespaldo.setBounds(22, 29, 164, 14);
		getContentPane().add(lblRespaldo);
		
		tfRespaldo = new JTextField();
		tfRespaldo.setBounds(22, 49, 356, 20);
		getContentPane().add(tfRespaldo);
		tfRespaldo.setColumns(10);
		
		JButton btnRutaRespaldo = new JButton(".  .  .");
		btnRutaRespaldo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(seleccion.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					tfRespaldo.setText(seleccion.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnRutaRespaldo.setForeground(Color.BLACK);
		btnRutaRespaldo.setEnabled(true);
		btnRutaRespaldo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRutaRespaldo.setBounds(377, 49, 40, 21);
		getContentPane().add(btnRutaRespaldo);
		
		JButton btnRespaldo = new JButton("Respaldo");
		btnRespaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String direccion = tfRespaldo.getText();
				pgBackUp(host, puerto, usuario, password, basedatos, formato, direccion);
			}
		});
		btnRespaldo.setBounds(427, 49, 89, 23);
		getContentPane().add(btnRespaldo);
		
		JLabel lblRestablecer = new JLabel("Recuperar base de:");
		lblRestablecer.setBounds(22, 95, 164, 14);
		getContentPane().add(lblRestablecer);
		
		tfRestablecer = new JTextField();
		tfRestablecer.setBounds(22, 114, 356, 20);
		getContentPane().add(tfRestablecer);
		tfRestablecer.setColumns(10);
		
		JButton btnRutaRecuperar = new JButton(".  .  .");
		btnRutaRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tfRestablecer.setText(seleccion.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnRutaRecuperar.setBounds(377, 113, 40, 21);
		getContentPane().add(btnRutaRecuperar);
		
		JButton btnRecuperar = new JButton("Recuperar");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String direccion = tfRestablecer.getText();
				pgRestore(host, puerto, usuario, password, basedatos, formato, direccion);
			}
		});
		btnRecuperar.setBounds(427, 113, 89, 23);
		getContentPane().add(btnRecuperar);
	}
		
//-----------------FIN DEL CONSTRUCTOR----------------------------------------------------------

		public void pgBackUp(String host, String puerto, String usuario, String password, String basedatos, String format, String path) {
			try {
				constructorProceso = new ProcessBuilder(
						"D:/Programas/PostgreSQL/9.4/bin\\pg_dump.exe",
						"--verbose", "--format", format,  "-f", path);
				
				constructorProceso.environment().put("PGHOST", host);
				constructorProceso.environment().put("PGPORT", puerto);
				constructorProceso.environment().put("PGUSER", usuario);
				constructorProceso.environment().put("PGPASSWORD", password);
				constructorProceso.environment().put("PGDATABASE", basedatos);
				constructorProceso.redirectErrorStream(true);
				proceso = constructorProceso.start();
				
				System.out.println("Terminado backup " + path + "\n");
				JOptionPane.showMessageDialog(null, "Respaldo de base de datos creada con suceso");
			} catch (Exception e) {
				System.out.println("Backup \n" + e.getMessage() + "\n");
			}
		}
		
		public void pgRestore(String host, String puerto, String usuario, String password, String basedatos, String format, String path) {
			try {
				constructorProceso = new ProcessBuilder(
						"D:/Programas/PostgreSQL/9.4/bin\\pg_restore.exe",
						"--exit-on-error", "--verbose", "-1", path);
				
				constructorProceso.environment().put("PGHOST", host);
				constructorProceso.environment().put("PGPORT", puerto);
				constructorProceso.environment().put("PGUSER", usuario);
				constructorProceso.environment().put("PGPASSWORD", password);
				constructorProceso.environment().put("PGDATABASE", basedatos);
				constructorProceso.redirectErrorStream(true);
				proceso = constructorProceso.start();
				
				System.out.println("Terminado restore \n");
				JOptionPane.showMessageDialog(null, "Base de datos restaurada con suceso");
			} catch (Exception e) {
				System.out.println("Restore \n" + e.getMessage() + "\n");
			}
	}
}
