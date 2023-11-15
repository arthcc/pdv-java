package ccomp;

import java.awt.EventQueue;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.UIManager;

import ccomp.core.impl.EmMemoriaRepositorio;
import ccomp.dominios.produto.Produto;
import ccomp.facade.GerenciadorSistemaFacade;
import ccomp.ui.FramePrincipal;

public class Main {

	public static void main(String[] args) throws InstanceAlreadyExistsException 
	{
		GerenciadorSistemaFacade.criarGerenciadorSistema();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
}
