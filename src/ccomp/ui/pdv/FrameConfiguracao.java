package ccomp.ui.pdv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FrameConfiguracao extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	
	private FrameConfiguracao(final FramePontoDeVendas donoPontoDeVendas) {
		
		setBounds(100, 100, 264, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JPanel pnTema = new JPanel();
			pnTema.setBounds(10, 95, 232, 122);
			pnTema.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), 
					new Color(160, 160, 160)), "Tema:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(pnTema);
			{
				ListagemPersonalizacao listagemPersonalizacao = new ListagemPersonalizacao(donoPontoDeVendas);
				pnTema.add(listagemPersonalizacao);
			}
		}
		
		JEditorPane dtrpnAlgumasConfiguraesDo = new JEditorPane();
		dtrpnAlgumasConfiguraesDo.setFont(new Font("Verdana", Font.PLAIN, 11));
		dtrpnAlgumasConfiguraesDo.setEnabled(false);
		dtrpnAlgumasConfiguraesDo.setText("Algumas configurações do PDV\r\n- Tema\r\n- Só isso mesmo kk");
		dtrpnAlgumasConfiguraesDo.setBounds(10, 11, 232, 73);
		contentPanel.add(dtrpnAlgumasConfiguraesDo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public static FrameConfiguracao iniciarConfiguradorPDV(FramePontoDeVendas hospedeiro) {
		FrameConfiguracao configuracao = new FrameConfiguracao(hospedeiro);
		configuracao.setVisible(true);
		return configuracao;
	}
}
