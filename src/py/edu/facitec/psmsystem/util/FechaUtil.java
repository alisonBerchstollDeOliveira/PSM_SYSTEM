package py.edu.facitec.psmsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class FechaUtil {
	final private static SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyy");
	private static MaskFormatter mascara;
	
	public static MaskFormatter getMascara() {
		if (mascara == null) {
			try {
				mascara = new MaskFormatter("##/##/####");
				mascara.setPlaceholderCharacter('_');
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return mascara;
	}
	
	public static Date convertirStringADateUtil(String s) {
		FORMATO_FECHA.setLenient(false);
		try {
			return FORMATO_FECHA.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String convertirDateUtilAString(Date d) {
		return FORMATO_FECHA.format(d);
	}
}