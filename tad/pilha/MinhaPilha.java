package tad.pilha;

import java.util.Arrays;

public class MinhaPilha implements PilhaIF<Integer> {
	
	Integer[] pilha;
	
	private int tamanho = -1;
	private int capacidade = 10;

	public MinhaPilha(int tamanho) {
		this.pilha = new Integer[capacidade];
		this.capacidade = tamanho;
		this.preencherPilhaComNulo();
	}
	
	public MinhaPilha() {
		this.pilha = new Integer[this.capacidade];
		this.preencherPilhaComNulo();
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (tamanho == capacidade - 1) throw new PilhaCheiaException();
		this.tamanho++;
		this.pilha[tamanho] = item;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (tamanho < 0) throw new PilhaVaziaException();
		this.tamanho--;
		return this.pilha[tamanho + 1];
	}

	@Override
	public Integer topo() {
		if (tamanho < 0) return null;
		return this.pilha[tamanho];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		for (int i = 1; i < k; i++) {
			this.tamanho--;
    	}
		return this;
	}

	@Override
	public boolean isEmpty() {
		return this.tamanho < 0;
	}
    
    private void preencherPilhaComNulo() {
    	for (int i = 0; i < this.capacidade; i++) {
    		this.pilha[i] = null;
    	}
    }
    
    @Override
    public String toString() {
    	return Arrays.toString(pilha);
    }
 
	
}
