package tad.fila;

import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;
import tad.listasEncadeadas.NodoListaEncadeada;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	ListaEncadeadaImpl<Integer> fila = new ListaEncadeadaImpl<>();
	
	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		fila.insert(item);
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if(fila.isEmpty()) throw new FilaVaziaException();	
		String[] elements = fila.imprimeEmOrdem().split(", ");
		NodoListaEncadeada<Integer> element = null;
		try {
			element = fila.remove(Integer.parseInt(elements[0]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ListaVaziaException e) {
			e.printStackTrace();
		}
		return element.getChave();
	}

	@Override
	public Integer verificarCauda() {
		if(fila.isEmpty()) return null;	
		String[] elements = fila.imprimeEmOrdem().split(", ");
		NodoListaEncadeada<Integer> element = null;
		try {
			element = fila.remove(Integer.parseInt(elements[elements.length]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ListaVaziaException e) {
			e.printStackTrace();
		}
		return element.getChave();
	}

	@Override
	public Integer verificarCabeca() {
		if(fila.isEmpty()) return null;	
		String[] elements = fila.imprimeEmOrdem().split(", ");
		NodoListaEncadeada<Integer> element = null;
		try {
			element = fila.remove(Integer.parseInt(elements[0]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ListaVaziaException e) {
			e.printStackTrace();
		}
		return element.getChave();
	}

	@Override
	public boolean isEmpty() {
		return fila.isEmpty();
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

}
