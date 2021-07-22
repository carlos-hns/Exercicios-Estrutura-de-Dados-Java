package tad.listasEncadeadas;

public class NodoListaDuplamenteEncadeadaSentinela<T extends Comparable<T> extends NodoListaDuplamenteEncadeada {

	@Override
	public NodoListaEncadeada<T> getProximo() {
		if(isSentinel()) return null;
		return super.getProximo();
	}
	
	private boolean isSentinel() {
		return this.getProximo().getChave() == null;
	}
}
