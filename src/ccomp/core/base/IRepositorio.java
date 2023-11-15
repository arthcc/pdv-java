package ccomp.core.base;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;


public interface IRepositorio<IdT, E> {
	
	E criar( E entidade );
	
	void deletar( E entidade );
	
	Optional<? extends E> encontrarPorId( IdT id );
	
	E editarPorId( IdT id, Consumer<E> consumerEntidade );
	
	boolean existePorId( IdT id );
	
	void deletarPorId( IdT id );

	Collection<E> todos();
	
	IdT criarIdUnico();
	
}
