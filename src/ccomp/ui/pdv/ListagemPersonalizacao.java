package ccomp.ui.pdv;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListagemPersonalizacao extends JList<Paleta> implements ListCellRenderer<Paleta> {

	private static final long serialVersionUID = -996544306373331509L;

	private DefaultListModel<Paleta> paletaModel = new DefaultListModel<Paleta>();

	
	public static final Paleta SKIN_PADRAO = new Paleta(
			"Eclipse", 
			Color.decode("#3b2e72"),  /* cor topo frame */
			Color.decode("#3b2e72"),  /* cor chao frame */
			new Color(255, 102, 102), /* cor valor desconto */ 
			new Color(102, 204, 0));  /* cor valor acrescimo */
	
	
	public static final Paleta SKIN_FESO = new Paleta(
			"Unifeso", 
			Color.decode("#41978d"),  /* cor topo frame */
			Color.decode("#127e71"),  /* cor chao frame */
			new Color(240, 202, 202), /* cor valor desconto */ 
			new Color(240, 204, 0));  /* cor valor acrescimo */
	
	public static final Paleta SKIN_BRASIL = new Paleta(
			"Brasil", 
			Color.decode("#0e645a"),  /* cor topo frame */
			Color.decode("#eab308"),  /* cor chao frame */
			Color.decode("#eab308"), /* cor valor desconto */ 
			Color.decode("#eab308"));  /* cor valor acrescimo */
	
	public static final Paleta SKIN_CCOMP = new Paleta(
			"CCOMP", 
			Color.decode("#27272a"),  /* cor topo frame */
			Color.decode("#27272a"),  /* cor chao frame */
			Color.decode("#a3e635"), /* cor valor desconto */ 
			Color.decode("#a3e635"));  /* cor valor acrescimo */
	
	public static final Paleta SKIN_SENDA = new Paleta(
			"Sendinha", 
			Color.decode("#b91c1c"),  /* cor topo frame */
			Color.decode("#b91c1c"),  /* cor chao frame */
			Color.decode("#fca5a5"), /* cor valor desconto */ 
			Color.decode("#fca5a5"));  /* cor valor acrescimo */
	
	
	
	public ListagemPersonalizacao(FramePontoDeVendas framePdv) {
		setOpaque(false);
		setCellRenderer(this);
		setModel(paletaModel);
		
		paletaModel.addElement(SKIN_PADRAO);
		paletaModel.addElement(SKIN_FESO);
		paletaModel.addElement(SKIN_BRASIL);
		paletaModel.addElement(SKIN_CCOMP);
		paletaModel.addElement(SKIN_SENDA);
		
		addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				Paleta paleta = getSelectedValue();
				framePdv.atualizarSkinFrame(paleta);
			}
		});

	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Paleta> list, Paleta value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.BLACK);
		label.setFont(Defaults.getFontePadraoDerivada(15));

		label.setText(value.getNome());

		if (isSelected) {
			label.setBackground(value.getBottomCor());
			label.setOpaque(true);
		} else {
			label.setOpaque(false);
		}

		return label;
	}


}
