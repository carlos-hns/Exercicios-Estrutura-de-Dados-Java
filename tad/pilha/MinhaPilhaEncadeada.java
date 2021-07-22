package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;
import tad.listasEncadeadas.NodoListaEncadeada;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

	ListaEncadeadaImpl<Integer> pilha = new ListaEncadeadaImpl<>();
	
	public MinhaPilhaEncadeada(Integer item) {
		pilha.insert(item);
	}
	
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		pilha.insert(item);
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (pilha.isEmpty()) throw new PilhaVaziaException();
		String[] elements = pilha.imprimeEmOrdem().split(", ");
		NodoListaEncadeada<Integer> element = null;
		try {
			element = pilha.remove(Integer.parseInt(elements[elements.length]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ListaVaziaException e) {
			e.printStackTrace();
		}
		return element.getChave();
	}

	@Override
	public Integer topo() {
		String[] elements = pilha.imprimeEmOrdem().split(", ");
		NodoListaEncadeada<Integer> element = null;
		try {
			element = pilha.search(Integer.parseInt(elements[elements.length]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return element.getChave();
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return pilha.isEmpty();
	}

}
