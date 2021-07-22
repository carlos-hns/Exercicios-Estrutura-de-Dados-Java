package tad.arvoreBinaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestArvoreBinaria {

	ArvoreBinariaIF<Integer> bst = null;
	Integer[] arrayAux = { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 };
	
	@BeforeEach
	void setup() {
		bst = new ArvoreBinariaImpl<>();
	}

	//M�todo auxilar para os testes
	public void insereElementosNaBST(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			bst.inserir(array[i]);
		}
	}

	@Test //Testa se inser��es est�o sendo feitas na BST
	void testInserir() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		assertEquals(Integer.valueOf(8), bst.buscar(Integer.valueOf(8)).getChave());
		assertEquals(Integer.valueOf(12), bst.buscar(Integer.valueOf(12)).getChave());
		assertEquals(Integer.valueOf(6), bst.buscar(Integer.valueOf(6)).getChave());
		assertEquals(Integer.valueOf(1), bst.buscar(Integer.valueOf(1)).getChave());
		bst.inserir(16);
		bst.inserir(17);
		assertEquals(Integer.valueOf(16), bst.buscar(Integer.valueOf(16)).getChave());
		assertEquals(Integer.valueOf(17), bst.buscar(Integer.valueOf(17)).getChave());
	}
	
	@Test //Testando a estrutura do NodeBST
	void testNodeBST() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		NoArvoreBinaria<Integer> node = bst.buscar(Integer.valueOf(12));
		assertEquals(Integer.valueOf(8), node.getPai().getChave());
		assertEquals(Integer.valueOf(10), node.getFilhoEsquerdo().getChave());
		assertEquals(Integer.valueOf(14), node.getFilhoDireito().getChave());
	}

	@Test //Testa casos de sucessos na busca
	void testBuscar1() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		assertEquals(Integer.valueOf(8), bst.buscar(Integer.valueOf(8)).getChave());
		assertEquals(Integer.valueOf(15), bst.buscar(Integer.valueOf(15)).getChave());
		assertEquals(Integer.valueOf(2), bst.buscar(Integer.valueOf(2)).getChave());
		assertEquals(Integer.valueOf(10), bst.buscar(Integer.valueOf(10)).getChave());
		
		bst.inserir(Integer.valueOf(16));
		assertEquals(Integer.valueOf(16), bst.buscar(Integer.valueOf(16)).getChave());
	}
	
	@Test //Testa casos de falhas na busca
	void testBuscar2() {
		insereElementosNaBST(arrayAux);
		assertThrows(ExceptionBST.class, () -> bst.buscar(16)); //busca elemento n�o contido na bst
		bst = new ArvoreBinariaImpl<>();
		assertThrows(ExceptionBST.class, () -> bst.buscar(8)); //busca elemento com a bst vazia
	}
	
	@Test //Tenta remover um elemento de uma BST vazia
	void testRemoverBSTVazia() {
		bst = new ArvoreBinariaImpl<>();
		assertThrows(ExceptionBST.class, () -> bst.remover(8));
		assertThrows(ExceptionBST.class, () -> bst.remover(9));
	}
	
	@Test //Tenta remover um elemento que n�o existe na BST
	void testRemoverElementoNaoExistente() {
		insereElementosNaBST(arrayAux);
		assertThrows(ExceptionBST.class, () -> bst.remover(16));
		assertThrows(ExceptionBST.class, () -> bst.remover(17));
	}
	
	@Test //Remove n� folha
	void testRemoverFolha() throws ExceptionBST{
		insereElementosNaBST(new Integer[] {15, 23, 6, 7, 4, 5, 71, 50});
		assertEquals(Integer.valueOf(7), bst.remover(7).getChave());
		Integer[] emOrdem = {4, 5, 6, 15, 23, 50, 71};
		assertTrue(Arrays.toString(emOrdem).equals(bst.imprimeEmOrdem(15)));
	}
	
	@Test //Remove n� com filho esquerdo
	void testRemoveNoComFilhoEsquerdo() throws ExceptionBST {
		insereElementosNaBST(new Integer[] {15, 23, 6, 4, 5, 20, 21});
		assertEquals(Integer.valueOf(6), bst.remover(6).getChave());
		Integer[] emOrdem = {4, 5, 15, 20, 21, 23};
		assertTrue(Arrays.toString(emOrdem).equals(bst.imprimeEmOrdem(15)));
	}
	
	@Test //Remove n� com filho direito
	void testRemoveNoComFilhoDireito() throws ExceptionBST {
		insereElementosNaBST(new Integer[] {15, 10, 20, 8, 9, 25, 22, 30});
		assertEquals(Integer.valueOf(20), bst.remover(20).getChave());
		Integer[] emOrdem = {8, 9, 10, 15, 22, 25, 30};
		assertTrue(Arrays.toString(emOrdem).equals(bst.imprimeEmOrdem(15)));
	}
	
	@Test //Remove n� com dois filhos
	void testRemoveNoComDoisFilhos() throws ExceptionBST {
		insereElementosNaBST(new Integer[] {15, 23, 6, 7, 4, 5, 71, 50});
		assertEquals(Integer.valueOf(6), bst.remover(6).getChave());
		Integer[] emOrdem = {4, 5, 7, 15, 23, 50, 71};
		assertTrue(Arrays.toString(emOrdem).equals(bst.imprimeEmOrdem(15)));
	}
	
	@Test //Testa casos de sucesso para busca do sucessor
	void testSucessor1() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		assertEquals(Integer.valueOf(3), bst.sucessor(2).getChave());
		assertEquals(Integer.valueOf(13), bst.sucessor(12).getChave());
		assertEquals(Integer.valueOf(9), bst.sucessor(8).getChave());
	}
	
	@Test //Testa casos de falha para busca do sucessor
	void testSucessor2() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		assertThrows(ExceptionBST.class, () -> bst.sucessor(16)); //busca sucessor de elemento inexistente
		assertNull(bst.sucessor(9)); //n�o tem sucessor
		
		bst = new ArvoreBinariaImpl<>();
		assertThrows(Exception.class, () -> bst.sucessor(8)); //busca sucessor em �rvove vazia
	}
	
	@Test //Testa casos de sucesso para busca do predecessor
	void testPredecessor1() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		assertEquals(Integer.valueOf(1), bst.predecessor(2).getChave());
		assertEquals(Integer.valueOf(11), bst.predecessor(12).getChave());
		assertEquals(Integer.valueOf(7), bst.predecessor(8).getChave());
	}
	
	@Test //Testa casos de falha para busca do predecessor
	void testPredecessor2() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		assertThrows(ExceptionBST.class, () -> bst.predecessor(16)); //busca predecessor de elemento inexistente
		assertNull(bst.predecessor(9)); //n�o tem predecessor
		
		bst = new ArvoreBinariaImpl<>();
		assertThrows(Exception.class, () -> bst.predecessor(8)); //busca predecessor em �rvove vazia
	}

	@Test //testa impress�o em ordem e casos de falhas
	void testImprimeEmOrdem() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		Integer[] emOrdem = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		assertTrue(Arrays.toString(emOrdem).equals(bst.imprimeEmOrdem(8)));
		
		Integer[] emOrdem2 = {9, 10, 11, 12, 13, 14, 15};
		assertTrue(Arrays.toString(emOrdem2).equals(bst.imprimeEmOrdem(12)));
		
		assertTrue("[9]".equals(bst.imprimeEmOrdem(9)));
		
		assertThrows(ExceptionBST.class, () -> bst.imprimeEmOrdem(16)); //elemento inexistente
		
		bst = new ArvoreBinariaImpl<>();
		assertThrows(ExceptionBST.class, () -> bst.imprimeEmOrdem(8)); // BST vazia
	}
	
	@Test //testa impress�o pr� ordem e casos de falhas
	void testImprimePreOrdem() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		Integer[] preOrdem = { 8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15 };
		assertTrue(Arrays.toString(preOrdem).equals(bst.imprimePreOrdem(8)));

		Integer[] preOrdem2 = { 12, 10, 9, 11, 14, 13, 15 };
		assertTrue(Arrays.toString(preOrdem2).equals(bst.imprimePreOrdem(12)));

		assertTrue("[9]".equals(bst.imprimePreOrdem(9)));

		assertThrows(ExceptionBST.class, () -> bst.imprimePreOrdem(16)); // elemento inexistente

		bst = new ArvoreBinariaImpl<>();
		assertThrows(ExceptionBST.class, () -> bst.imprimePreOrdem(8)); // BST vazia
	}
	
	@Test //testa impress�o p�s ordem e casos de falhas
	void testImprimePosOrdem() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		Integer[] posOrdem = { 1, 3, 2, 5, 7, 6, 4, 9, 11, 10, 13, 15, 14, 12, 8 };
		assertTrue(Arrays.toString(posOrdem).equals(bst.imprimePosOrdem(8)));
		
		Integer[] posOrdem2 = { 9, 11, 10, 13, 15, 14, 12 };
		assertTrue(Arrays.toString(posOrdem2).equals(bst.imprimePosOrdem(12)));
		
		assertTrue("[9]".equals(bst.imprimePosOrdem(9)));
		
		assertThrows(ExceptionBST.class, () -> bst.imprimePosOrdem(16)); // elemento inexistente

		bst = new ArvoreBinariaImpl<>();
		assertThrows(ExceptionBST.class, () -> bst.imprimePosOrdem(8)); // BST vazia
	}
	
	@Test //testa impress�o dos valores ordenados e casos de falha
	void testImprimeValoresOrdenados() throws ExceptionBST {
		insereElementosNaBST(arrayAux);
		Integer[] valoresOrdenados = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		assertTrue(Arrays.toString(valoresOrdenados).equals(bst.imprimeValoresOrdenados(8)));
		
		Integer[] valoresOrdenados2 = { 1, 2, 3, 4, 5, 6, 7 };
		assertTrue(Arrays.toString(valoresOrdenados2).equals(bst.imprimeValoresOrdenados(4)));
		
		assertTrue("[9]".equals(bst.imprimeValoresOrdenados(9)));
		
		assertThrows(ExceptionBST.class, () -> bst.imprimeValoresOrdenados(16)); //elemento inexistente
		
		bst = new ArvoreBinariaImpl<>();
		assertThrows(ExceptionBST.class, () -> bst.imprimeValoresOrdenados(8)); // BST vazia
	}
}