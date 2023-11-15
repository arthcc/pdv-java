package ccomp.core;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Icones {
	
	private static final Map<String, ImageIcon> iconesCache = new HashMap<>(10);
	
	public static ImageIcon getIcone(String caminho) {
		ImageIcon imgIcone = iconesCache.get(caminho.toLowerCase());
		if (imgIcone != null) {
			return imgIcone;
		}
		imgIcone = new ImageIcon(Utilitarios.recursoInterno(caminho));
		iconesCache.put(caminho.toLowerCase(), imgIcone);
		return imgIcone;
	}


}
