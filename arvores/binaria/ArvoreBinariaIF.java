package tad.arvoreBinaria;

public interface ArvoreBinariaIF<T extends Comparable<T>> {
	
	public void inserir(T chave);
	public NoArvoreBinaria<T> buscar(T chave) throws ExceptionBST;
	public NoArvoreBinaria<T> sucessor(T chave) throws ExceptionBST;
	public NoArvoreBinaria<T> remover(T chave) throws ExceptionBST;
	public NoArvoreBinaria<T> predecessor(T chave) throws ExceptionBST;
	public String imprimeEmOrdem(T chave) throws ExceptionBST;
	public String imprimePreOrdem(T chave) throws ExceptionBST;
	public String imprimePosOrdem(T chave) throws ExceptionBST;
	public String imprimeValoresOrdenados(T chave) throws ExceptionBST;
	public NoArvoreBinaria<T> minimo(T chave) throws ArvoreVaziaException;
	public NoArvoreBinaria<T> maximo(T chave) throws ArvoreVaziaException;
	public int tamanho();
	public boolean vazio();
	public NoArvoreBinaria<T> getRaiz();
	public int altura();
}