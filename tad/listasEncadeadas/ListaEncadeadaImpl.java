package tad.listasEncadeadas;

import tad.util.Conversor;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T>{
	
	NodoListaEncadeada<T> cabeca = null;

	@Override
	public boolean isEmpty() {
		if (cabeca == null) return true;
		else return false;
	}

	@Override
	public int size() {
		int contador = 0;
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null) {
			auxiliar = auxiliar.getProximo();
			contador++;
		}
		return contador;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = auxiliar.getProximo();
		}
		if(auxiliar == null) return null;
		return auxiliar;
	}

	@Override
	public void insert(T chave) {
		
		NodoListaEncadeada<T> newNode = new NodoListaEncadeada<>(chave);
		
		if(cabeca == null) {
			cabeca = newNode;
		} else {
			NodoListaEncadeada<T> anterior = null;
			NodoListaEncadeada<T> auxiliar = cabeca;
			while(auxiliar != null) {
				anterior = auxiliar;
				auxiliar = auxiliar.getProximo();
			}
			anterior.setProximo(newNode);
			newNode.setProximo(auxiliar);
		}
		
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if(cabeca == null) throw new ListaVaziaException();
		NodoListaEncadeada<T> auxiliar = cabeca;
		if(cabeca.getChave().compareTo(chave) == 0) {
			this.cabeca = this.cabeca.getProximo();
		} else {
			NodoListaEncadeada<T> anterior = null;
			while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
				anterior = auxiliar;
				auxiliar = auxiliar.getProximo();
			}
			if (auxiliar == null) return null;
			anterior.setProximo(auxiliar.getProximo());
		}
		return auxiliar;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		if (this.size() == 0) return null;
		
		Conversor<T> c = new Conversor<T>();
		T[] meuArray = c.gerarArray(clazz, this.size());
		
		NodoListaEncadeada<T> auxiliar = cabeca;
		int i = 0;
		while (auxiliar != null) {
			meuArray[i++] = auxiliar.getChave();
			auxiliar = auxiliar.getProximo();
		}
		return meuArray;
	}

	@Override
	public String imprimeEmOrdem() {
		if (this.isEmpty()) return "";
		String result = "";
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null) {
			result += auxiliar.getChave();
			if(auxiliar.getProximo() != null) result += ", ";
			auxiliar = auxiliar.getProximo();
		}
		return result;
	}

	@Override
	public String imprimeInverso() {
		if (this.isEmpty()) return "";
		String current = "";
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null) {
			String toConcat = ""; 
			if(auxiliar.getProximo() != null) toConcat += ", ";
			toConcat += auxiliar.getChave();
			toConcat += current;
			current = toConcat;
			auxiliar = auxiliar.getProximo();
		}
		return current;
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaEncadeada<T> auxiliar = cabeca;
		while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = auxiliar.getProximo();
		}
		return auxiliar.getProximo();
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaEncadeada<T> auxiliar = cabeca;
		NodoListaEncadeada<T> anterior = null;
		while(auxiliar != null && auxiliar.getChave().compareTo(chave) != 0) {
			anterior = auxiliar;
			auxiliar = auxiliar.getProximo();
		}
		return anterior;
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Precisa implementar!");
	}

}
