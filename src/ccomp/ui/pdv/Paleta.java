package ccomp.ui.pdv;

import java.awt.Color;

public class Paleta {

	private Color topCor;
	private Color bottomCor;
	private Color descontoCor;
	private Color acrescimoCor;
	private String nome;
	
	public Paleta(String nome, Color topCor, Color bottomCor, 
				  Color descontoCor, Color acrescimoCor) 
	{
		this.nome = nome;
		this.topCor = topCor;
		this.bottomCor = bottomCor;
		this.descontoCor = descontoCor;
		this.acrescimoCor = acrescimoCor;
	}

	public String getNome() {
		return nome;
	}
	
	public Color getTopCor() {
		return topCor;
	}
	
	public Color getBottomCor() {
		return bottomCor;
	}
	
	public Color getDescontoCor() {
		return descontoCor;
	}
	
	public Color getAcrescimoCor() {
		return acrescimoCor;
	}
	
	
	
}
