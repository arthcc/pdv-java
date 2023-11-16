package ccomp;

import java.awt.EventQueue;

import javax.management.InstanceAlreadyExistsException;
import javax.swing.UIManager;

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
