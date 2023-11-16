package ccomp.ui.pdv;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

public final class Defaults {

	public static final Font FONTE_PADRAO = new Font("Courier New", Font.BOLD, 5);
	
	private static final Map<Integer, Font> fonteCache = new HashMap<Integer, Font>();
	
	public static final Font getFontePadraoDerivada(Integer tamanho) {
		Font fonte = fonteCache.get(tamanho); 
		if (fonte == null) {
			fonte = FONTE_PADRAO.deriveFont(tamanho.floatValue());
			fonteCache.put(tamanho, fonte);
		}
		return fonte;
	}
	
}
