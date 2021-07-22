package tad.conjuntoDinamico;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer>{

	int tamanho;
	int capacidade;
	Integer[] conjunto;
	
	public MeuConjuntoDinamico() {
		this.tamanho = 0;
		this.capacidade = 10;
		this.conjunto = new Integer[10];
	}
	
	@Override
	public void inserir(Integer item) {
		this.incrementarTamanho();
		this.conjunto[this.tamanho] = item;
		this.tamanho++;
	}

	@Override
	public Integer remover(Integer item) throws Exception {
		boolean foiAchado = false;
		int index = 0;
		Integer itemAchado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto[i].equals(item)) {
				foiAchado = true;
				index = i;
				itemAchado = this.conjunto[i];
				break;
			}
			i++;
		}
		
		if(foiAchado) {
			this.ajustarArray(index);
			this.tamanho--;
			return itemAchado;
		} else {
			throw new Exception("Item não encontrado.");
		}
	}

	@Override
	public Integer predecessor(Integer item) throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vázio");
	
		boolean foiAchado = false;
		int index = 0;
		Integer itemAchado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto[i].equals(item)) {
				foiAchado = true;
				index = i;
				itemAchado = this.conjunto[i];
				break;
			}
			i++;
		}
		
		if(foiAchado) {
			if (index - 1 < 0) return null;
			else return this.conjunto[index - 1];
		} else {
			throw new Exception("Item não encontrado.");
		}
		
	}

	@Override
	public Integer sucessor(Integer item) throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vázio");
		
		boolean foiAchado = false;
		int index = 0;
		Integer itemAchado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto[i].equals(item)) {
				foiAchado = true;
				index = i;
				itemAchado = this.conjunto[i];
				break;
			}
			i++;
		}
		
		if(foiAchado) {
			if (index == this.tamanho) return null;
			else return this.conjunto[index + 1];
		} else {
			throw new Exception("Item não encontrado.");
		}
	}

	@Override
	public int tamanho() {
		return this.tamanho;
	}

	@Override
	public Integer buscar(Integer item) throws Exception {
		boolean foiAchado = false;
		Integer itemAchado = 0;
		
		int i = 0;
		while(i < this.tamanho) {
			if(this.conjunto[i].equals(item)) {
				foiAchado = true;
				itemAchado = this.conjunto[i];
				break;
			}
			i++;
		}
		
		if(foiAchado) return itemAchado;
		else throw new Exception("Item não encontrado.");
	}

	@Override
	public Integer minimum() throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vázio");
		int minimum = this.conjunto[0];
		for (int i = 0; i < this.tamanho; i++) {
			if(this.conjunto[i] < minimum) minimum = this.conjunto[i];
		}
		return minimum;
	}

	@Override
	public Integer maximum() throws Exception {
		if (this.tamanho == 0) throw new Exception("Conjunto vázio");
		int maximum = this.conjunto[0];
		for (int i = 0; i < this.tamanho; i++) {
			if(this.conjunto[i] > maximum) maximum = this.conjunto[i];
		}
		return maximum;
	}
	
  private void incrementarTamanho() {
      if (this.tamanho == this.capacidade) {
      	Integer[] novoConjunto = new Integer[capacidade];
      	for (int i =0; i < this.tamanho; i++) {
      		novoConjunto[i] = this.conjunto[i];
      	}
      }
      atualizarCapacidade();
  }
  
  private void atualizarCapacidade() {
      this.capacidade = this.conjunto.length;
  }
  
  private void ajustarArray(int index) {
	  for (int i = index; i < this.tamanho; i++) {
		  this.conjunto[i] = this.conjunto[i + 1];
	  }
  }
  
}
