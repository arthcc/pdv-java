package ccomp.core.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;

import ccomp.core.base.EntidadeBase;
import ccomp.core.base.IRepositorio;

public class EmMemoriaRepositorio<T extends EntidadeBase> implements IRepositorio<Long, T> {

	private final Map<Long, T> entidades;
	
	public EmMemoriaRepositorio() 
	{
		entidades = new TreeMap<>();
	}
	
	@Override
	public T criar(T entidade) {

		Long novoId = criarIdUnico();
		entidade.setId(novoId);
		entidades.put(novoId, entidade);
		return entidade;
		
	}
	
	@Override
	public Long criarIdUnico() {
		
		if (!entidades.isEmpty()) {
			return ((TreeMap<Long, T>)entidades).lastKey() + 1;
		}
		return 0L;
	}
	
	@Override
	public void deletar(T entidade) {
		
		if (entidade != null)
			entidades.remove(entidade.getId());
		
	}
	
	@Override
	public T editarPorId(Long id, Consumer<T> consumerEntidade) {

		Optional<T> entitdadeOpt = encontrarPorId(id);
		
		if (!entitdadeOpt.isPresent() || consumerEntidade == null)
			return null;
		
		final T entidadeModificavel = entitdadeOpt.get();
		consumerEntidade.accept(entidadeModificavel);
		
		return entidadeModificavel;
		
	}

	@Override
	public Optional<T> encontrarPorId(Long id) {
		
		if (id == null)
			throw new IllegalStateException("Identificador não pode ser nulo");
		
		T entidade = entidades.get(id);
		return Optional.ofNullable(entidade);
		
	}

	@Override
	public boolean existePorId(Long id) {
		return entidades.containsKey(id);
	}
	
	@Override
	public void deletarPorId(Long id) {
		entidades.remove(id);
	}
	
	@Override
	public Collection<T> todos() {
		return entidades.values();
	}

	

}
