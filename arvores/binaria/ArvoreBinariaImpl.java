package tad.arvoreBinaria;

public class ArvoreBinariaImpl<T extends Comparable<T>> implements ArvoreBinariaIF<T>{

	private NoArvoreBinaria<T> raiz = null;
	private String toPrint;
	
	public ArvoreBinariaImpl() { }
	
	public ArvoreBinariaImpl(T valor) {
		this.raiz = new NoArvoreBinaria<T>(valor);
	}
	
	
	/**
	* Busca um elemento dado uma chave.
	* @throws ExceptionBST lança uma exceção se o elemento da chave não for encontrado.
	* @param chave elemento a ser procurado.
	*/
	
	@Override
	public NoArvoreBinaria<T> buscar(T chave) throws ExceptionBST {
		NoArvoreBinaria<T> result = treeSearch(this.raiz, chave);
		if(result == null) throw new ExceptionBST("Not Found");
		else return result;
	}

	private NoArvoreBinaria<T> treeSearch(NoArvoreBinaria<T> node, T chave) {
		if(node == null || node.getChave().compareTo(chave) == 0) return node;
		if(chave.compareTo(node.getChave()) < 0) return treeSearch(node.getFilhoEsquerdo(), chave);
		else return treeSearch(node.getFilhoDireito(), chave);
	}
	
	/**
	* Insere um elemento dado uma chave.
	* @throws ExceptionBST lança uma exceção se o elemento da chave não for encontrado.
	* @param chave elemento a ser inserido na árvore.
	*/
	
	@Override
	public void inserir(T chave) {
		NoArvoreBinaria<T> newNode = new NoArvoreBinaria<T>(chave);
		NoArvoreBinaria<T> holder = null;
		NoArvoreBinaria<T> assistant = this.raiz;
		
		while(assistant != null) {
			holder = assistant;
			if(newNode.getChave().compareTo(assistant.getChave()) < 0) assistant = assistant.getFilhoEsquerdo();
			else assistant = assistant.getFilhoDireito();
		}
		
		newNode.setPai(holder);
		if (holder == null) this.raiz = newNode;
		else if (newNode.getChave().compareTo(holder.getChave()) < 0) {
			holder.setFilhoEsquerdo(newNode);
		} else {
			holder.setFilhoDireito(newNode);
		}
	}

	
	/**
	* Remove um elemento dado uma chave.
	* Método mais complexo de operar, pois, irá realizar três operações: 
	* - Quando não possui filho esquerdo.
	* - Quando não possui filho direito.
	* - Quando possui ambos os filhos.
	* Implementado de acordo com os pseudo códigos do livro do Cormem.
	* @throws ExceptionBST lança uma exceção se o elemento da chave não for encontrado.
	* @param chave elemento a ser inserido na árvore.
	*/
	
	@Override
	public NoArvoreBinaria<T> remover(T chave) throws ExceptionBST {
		NoArvoreBinaria<T> searchedNode = this.buscar(chave);
		if(searchedNode == null) throw new ExceptionBST("Not Found");
		
		if(searchedNode.getFilhoEsquerdo() == null) transplant(searchedNode, searchedNode.getFilhoDireito());
		else if(searchedNode.getFilhoDireito() == null) transplant(searchedNode, searchedNode.getFilhoEsquerdo());
		else {
			try {
				NoArvoreBinaria<T> sucessor = minimo(searchedNode.getFilhoDireito().getChave());
				if(!sucessor.getPai().equals(searchedNode)) {
					transplant(sucessor, sucessor.getFilhoDireito());
					sucessor.setFilhoDireito(searchedNode.getFilhoDireito());
					sucessor.getFilhoDireito().setPai(sucessor);
				}
				transplant(searchedNode, sucessor);
				sucessor.setFilhoEsquerdo(searchedNode.getFilhoEsquerdo());
				sucessor.getFilhoEsquerdo().setPai(sucessor);
			} catch (ArvoreVaziaException e) {
				e.printStackTrace();
			}
		}
		return searchedNode;
	}
	
	private void transplant(NoArvoreBinaria<T> source, NoArvoreBinaria<T> destination) {
		if(source.getPai() == null) this.raiz = destination;
		else if(source.equals(source.getPai().getFilhoEsquerdo())) {
			source.getPai().setFilhoEsquerdo(destination);
		} else {
			source.getPai().setFilhoDireito(destination);
		}
		
		if(destination != null) destination.setPai(source.getPai());
	}
	
	/**
	* Encontra o sucessor de um determinado nó.
	* Se a árvore não for vazia o sucessor é o nó extremo esquerdo da subárvore direita.
	* Simétrico ao predecessor.
	* @throws ExceptionBST lança uma exceção se o elemento da chave não for encontrado ou a arvore for vazia.
	* @param chave elemento a ser procurado.
	*/
	
	@Override
	public NoArvoreBinaria<T> sucessor(T chave) throws ExceptionBST {
		NoArvoreBinaria<T> assistant = this.buscar(chave);
		if(assistant == null) throw new ExceptionBST("Not Found");
		
		if (assistant.getFilhoDireito() != null) {
			try {
				return this.minimo(assistant.getFilhoDireito().getChave());
			} catch (ArvoreVaziaException e) {
				throw new ExceptionBST("Arvore Vazia.");
			}
		}
		
		NoArvoreBinaria<T> holder = assistant.getPai();
		while(holder != null && assistant.equals(holder.getFilhoDireito())) {
			assistant = holder;
			holder = holder.getPai();
		}
		
		return holder;	
	}
	
	/**
	* Encontra o predecessor de um determinado nó.
	* Simétrico ao sucessor.
	* @throws ExceptionBST lança uma exceção se o elemento da chave não for encontrado ou a arvore for vazia.
	* @param chave elemento a ser procurado.
	*/
	
	@Override
	public NoArvoreBinaria<T> predecessor(T chave) throws ExceptionBST {
		NoArvoreBinaria<T> assistant = this.buscar(chave);
		if(assistant == null) throw new ExceptionBST("Not Found");
		
		if (assistant.getFilhoEsquerdo() != null) {
			try {
				return this.maximo(assistant.getFilhoEsquerdo().getChave());
			} catch (ArvoreVaziaException e) {
				throw new ExceptionBST("Arvore Vazia.");
			}
		}
		
		NoArvoreBinaria<T> holder = assistant.getPai();
		while(holder != null && assistant.equals(holder.getFilhoEsquerdo())) {
			assistant = holder;
			holder = holder.getPai();
		}
		
		return holder;	
	}

	@Override
	public String imprimeEmOrdem(T chave) throws ExceptionBST {
		NoArvoreBinaria<T> assistant = this.buscar(chave);
		this.toPrint = "";
		inOrderTreeWalk(assistant);
		String result = this.toPrint.substring(0, this.toPrint.length() - 2);
		return "[" + result + "]";
	}
	
	private void inOrderTreeWalk(NoArvoreBinaria<T> node) {
		if(node != null) {
			inOrderTreeWalk(node.getFilhoEsquerdo());
			this.toPrint += node.getChave() + ", ";
			inOrderTreeWalk(node.getFilhoDireito());
		}
	}

	@Override
	public String imprimePreOrdem(T chave) throws ExceptionBST {
		NoArvoreBinaria<T> assistant = this.buscar(chave);
		this.toPrint = "";
		inPreOrderTreeWalk(assistant);
		String result = this.toPrint.substring(0, this.toPrint.length() - 2);
		return "[" + result + "]";
	}
	
	private void inPreOrderTreeWalk(NoArvoreBinaria<T> node) {
		if(node != null) {
			this.toPrint += node.getChave() + ", ";
			inPreOrderTreeWalk(node.getFilhoEsquerdo());
			inPreOrderTreeWalk(node.getFilhoDireito());
		}
	}
	
	@Override
	public String imprimePosOrdem(T chave) throws ExceptionBST {
		NoArvoreBinaria<T> assistant = this.buscar(chave);
		this.toPrint = "";
		inPosOrderTreeWalk(assistant);
		String result = this.toPrint.substring(0, this.toPrint.length() - 2);
		return "[" + result + "]";
	}
	
	private void inPosOrderTreeWalk(NoArvoreBinaria<T> node) {
		if(node != null) {
			inPosOrderTreeWalk(node.getFilhoEsquerdo());
			inPosOrderTreeWalk(node.getFilhoDireito());
			this.toPrint += node.getChave() + ", ";
		}
	}

	@Override
	public String imprimeValoresOrdenados(T chave) throws ExceptionBST {
		return this.imprimeEmOrdem(chave);
	}
	
	/**
	* Encontra o valor minimo de uma sub árvore.
	* Usado nos métodos de remoção e sucessão.
	* @throws ExceptionBST lança uma exceção se a árvore estiver vazia.
	* @param chave elemento a ser procurado.
	*/

	@Override
	public NoArvoreBinaria<T> minimo(T chave) throws ArvoreVaziaException {
		if (vazio()) throw new ArvoreVaziaException("Arvore vázia");
		NoArvoreBinaria<T> assistant;
		try {
			assistant = this.buscar(chave);
		} catch (ExceptionBST e) {
			return null;
		}
		
		while (assistant.getFilhoEsquerdo() != null) {
			assistant = assistant.getFilhoEsquerdo();
		}
		return assistant;
	}

	/**
	* Encontra o valor maximo de uma sub árvore.
	* * Usado no método de predecessor.
	* @throws ExceptionBST lança uma exceção se a árvore estiver vazia.
	* @param chave elemento a ser procurado.
	*/
	
	@Override
	public NoArvoreBinaria<T> maximo(T chave) throws ArvoreVaziaException {
		if (vazio()) throw new ArvoreVaziaException("Arvore vázia");
		NoArvoreBinaria<T> assistant;
		try {
			assistant = this.buscar(chave);
		} catch (ExceptionBST e) {
			return null;
		}
		
		while (assistant.getFilhoDireito() != null) {
			assistant = assistant.getFilhoDireito();
		}
		return assistant;
	}

	public NoArvoreBinaria<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
	}

	/**
	* Simétrico a altura.
	* Retorna 0 se possuir apenas um elemento na árvore, pois conta as arestas.
	* Caso contrário irá retornar a quantidade de arestas da árvore.
	*/
	
	@Override
	public int tamanho() {
		return calcularTamanhoArvore(this.raiz);
	}
	
	public int calcularTamanhoArvore(NoArvoreBinaria<T> no) {
		if (no == null || (no.getFilhoEsquerdo() == null && no.getFilhoDireito() == null)) return 0;
		else {
			
			if(calcularTamanhoArvore(no.getFilhoEsquerdo()) >
				calcularTamanhoArvore(no.getFilhoDireito())) {
				return (1 + calcularTamanhoArvore(no.getFilhoEsquerdo()));
			} else {
				return (1 + calcularTamanhoArvore(no.getFilhoDireito()));
			}
		}
	}

	@Override
	public boolean vazio() {
		return this.raiz == null;
	}
	
	@Override
	public int altura() {
		// Altura e tamanho são sinonimos.
		// A principio desconsiderar os métodos!
		
		
		// Tamanho da árvore == Distância da raiz para o nó
		// mais profundo da árvore.
		// Geralmente é contabilizado a quantidade de arestas.
		
		return calcularTamanhoArvore(this.raiz);	
	}
}
