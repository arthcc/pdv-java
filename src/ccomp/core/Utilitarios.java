package ccomp.core;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import ccomp.core.exception.ValidadorException;

public class Utilitarios {
	
	private static final Random RANDOM = new Random();
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	public static String dateToString(LocalDateTime dateTime) {
		return dateTime.format(DATE_FORMATTER);
	}
	
	public static int randomInt(int min, int max) {
		return RANDOM.nextInt(max - min) + min;
	}
	
	public static void validar(boolean condicao, String mensagemDeErro, String esperado) {
		if (condicao) 
			throw new ValidadorException(
				String.format("Erro: %s [Esperado: %s]", mensagemDeErro, esperado));
		
	}
	
	public static void validarNaoNulo(Object objeto, String nome) {
		if (objeto == null) 
			throw new ValidadorException(
				String.format("\"%s\" não pode ser nulo.", nome));
		
	}
	
	public static void validarNaoVazio(boolean condicao, String mensagemDeErro) {
		if (condicao) 
			throw new ValidadorException(
				String.format("Erro: %s [Valor não deve ser vazio]", mensagemDeErro));
		
	}
	
	public static URL recursoInterno(String caminho) {
		return Utilitarios.class.getResource(caminho);
	}
	
	public static boolean isNullOrEmpty(String string) {
		return (string == null || string.isEmpty());
	}
	
}
