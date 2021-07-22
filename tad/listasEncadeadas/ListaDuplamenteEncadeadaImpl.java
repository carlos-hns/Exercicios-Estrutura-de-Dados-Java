package tad.listasEncadeadas;

import tad.util.Conversor;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {
	
	NodoListaDuplamenteEncadeada<T> cabeca;
	NodoListaDuplamenteEncadeada<T> cauda;
	NodoListaDuplamenteEncadeada<T> sentinela;
	
	public ListaDuplamenteEncadeadaImpl() {
		this.sentinela = new NodoListaDuplamenteEncadeada<T>(null);
		this.sentinela.setAnterior(this.sentinela);
		this.sentinela.setProximo(this.sentinela);
	}
	
	@Override
	public boolean isEmpty() {
		return sentinela.getProximo() == sentinela;
	}

	@Override
	public int size() {
		int contador = 0;
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		while(auxiliar != sentinela) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
			contador++;
		}
		return contador;		
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		}
		if(auxiliar == sentinela) return null;
		return auxiliar;
	}

	@Override
	public void insert(T chave) {
		NodoListaDuplamenteEncadeada<T> lastNode;
		NodoListaDuplamenteEncadeada<T> newNode = new NodoListaDuplamenteEncadeada<T>(chave);
		newNode.setProximo(sentinela);
		newNode.setAnterior(sentinela.getAnterior());
		lastNode = sentinela.getAnterior();
		lastNode.setProximo(newNode);
		sentinela.setAnterior(newNode);
		cabeca = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		cauda = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if(this.size() == 0) throw new ListaVaziaException();
		
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		NodoListaDuplamenteEncadeada<T> anterior;
		NodoListaDuplamenteEncadeada<T> proximo;
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		}
		
		anterior = (NodoListaDuplamenteEncadeada<T>) auxiliar.getAnterior();
		proximo = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		
		anterior.setProximo(proximo);
		proximo.setAnterior(anterior);
		
		return auxiliar;
	}

	@Override
	public String imprimeEmOrdem() {
		if (this.isEmpty()) return "";
		String result = "";
		NodoListaEncadeada<T> auxiliar = sentinela.getProximo();
		while(auxiliar != sentinela) {
			result += auxiliar.getChave();
			if(auxiliar.getProximo() != sentinela) result += ", ";
			auxiliar = auxiliar.getProximo();
		}
		return result;
	}

	@Override
	public String imprimeInverso() {
		if (this.isEmpty()) return "";
		String result = "";
		NodoListaDuplamenteEncadeada<T> auxiliar = sentinela.getAnterior();
		while(auxiliar != sentinela) {
			result += auxiliar.getChave();
			if(auxiliar.getAnterior() != sentinela) result += ", ";
			auxiliar = auxiliar.getAnterior();
		}
		return result;
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		if(this.size()  == 0) return null;
		NodoListaEncadeada<T> auxiliar = sentinela.getProximo();
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = auxiliar.getProximo();
		}
		return auxiliar.getProximo();
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		if(this.size()  == 0) return null;
		NodoListaDuplamenteEncadeada<T> auxiliar = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		while(auxiliar != sentinela && auxiliar.getChave().compareTo(chave) != 0) {
			auxiliar = (NodoListaDuplamenteEncadeada<T>) auxiliar.getProximo();
		}
		if (auxiliar.getAnterior() == sentinela) return null;
		return auxiliar.getAnterior();
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		if (this.size() == 0) return null;
		
		Conversor<T> c = new Conversor<T>();
		T[] meuArray = c.gerarArray(clazz, this.size());
	
		NodoListaEncadeada<T> auxiliar = sentinela.getProximo();
		int i = 0;
		while (auxiliar != sentinela) {
			meuArray[i++] = auxiliar.getChave();
			auxiliar = auxiliar.getProximo();
		}
		return meuArray;
	}

	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> temporaryNode = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		NodoListaDuplamenteEncadeada<T> newNode = new NodoListaDuplamenteEncadeada<T>(elemento);
		newNode.setProximo(sentinela.getProximo());
		temporaryNode.setAnterior(newNode);
		sentinela.setProximo(newNode);
		newNode.setAnterior(sentinela);		
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		if(this.size() == 0) return null;
		
		NodoListaDuplamenteEncadeada<T> lastNode = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
		NodoListaDuplamenteEncadeada<T> previousNode = (NodoListaDuplamenteEncadeada<T>) lastNode.getAnterior();
		
		previousNode.setProximo(sentinela);
		sentinela.setAnterior(previousNode);
		
		cabeca = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		cauda = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
		
		return lastNode;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		if(this.size() == 0) return null;
		
		NodoListaDuplamenteEncadeada<T> nextNode = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		NodoListaDuplamenteEncadeada<T> nextNextNode = (NodoListaDuplamenteEncadeada<T>) nextNode.getProximo();
		
		sentinela.setProximo(nextNextNode);
		nextNextNode.setAnterior(sentinela);
		
		cabeca = (NodoListaDuplamenteEncadeada<T>) sentinela.getProximo();
		cauda = (NodoListaDuplamenteEncadeada<T>) sentinela.getAnterior();
		
		return nextNode;
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Precisa implementar!");
		
	}
	
}
