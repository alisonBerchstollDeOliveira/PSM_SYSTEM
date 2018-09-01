package py.edu.facitec.psmsystem.componente;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.net.URL;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class BotonIconoPrincipal extends JButton{
	
	private static final long serialVersionUID = -5135218231501823013L;

	public BotonIconoPrincipal(){
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setPreferredSize(new Dimension(100, 100));
		setHorizontalTextPosition(SwingConstants.CENTER);
		setMaximumSize(new Dimension(100, 100));
		setFont(new Font("Tahoma", Font.BOLD, 16));
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
	}
	public void setText(String text) {
		setIcono(text);
		super.setText(text);
	}
	
	public void setIcono(String nombreIcono){
		try {
			URL url = BotonIconoPrincipal.class.getResource("/img/"+nombreIcono.toLowerCase()+".png");
			setIcon(new ImageIcon(url));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
