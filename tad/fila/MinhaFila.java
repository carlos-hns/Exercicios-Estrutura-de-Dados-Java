package tad.fila;

import java.util.Arrays;

/**
 * Fila deve ser implementada com array fixo e estratégia circular
 * de gerenciamento de apontadores de cauda e cabeça.
 * @author fabioleite
 *
 */
public class MinhaFila implements FilaIF<Integer> {
	
	private Integer[] fila;
	private int tamanho = 0;
	private int capacidade = 10;

	public MinhaFila(int tamanhoInicial) {
		this.capacidade = tamanhoInicial;
		this.fila = new Integer[tamanhoInicial];
		this.preencherFilaComNulo();
	}
	
	public MinhaFila() {
		this.fila = new Integer[this.capacidade];
		this.preencherFilaComNulo();
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if (this.tamanho >= this.capacidade) throw new FilaCheiaException();
		this.fila[this.tamanho] = item;
		this.tamanho++;
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (this.tamanho == 0) throw new FilaVaziaException();
		Integer itemRemovido = this.fila[0];		
		ajustarArray(0);
		this.tamanho--;
		return itemRemovido;
	}

	@Override
	public Integer verificarCauda() {
		if(this.tamanho == 0) return null;
		return this.fila[this.tamanho];
	}

	@Override
	public Integer verificarCabeca() {
		if(this.tamanho == 0) return null;
		return this.fila[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tamanho == 0;
	}

	@Override
	public boolean isFull() {
		return this.tamanho == this.capacidade;
	}

    private void preencherFilaComNulo() {
    	for (int i = 0; i < this.capacidade; i++) {
    		this.fila[i] = null;
    	}
    }
    
    private void ajustarArray(int index) {
  	  for (int i = index; i < this.tamanho - 1; i++) {
  		  this.fila[i] = this.fila[i + 1];
  	  }
  	  this.fila[this.tamanho - 1] = null;
    }

    @Override
	public String toString() {
		return Arrays.toString(fila);
	}
    
}
