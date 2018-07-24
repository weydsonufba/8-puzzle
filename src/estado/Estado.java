package estado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import estrutura.Fila;
import puzzle.Puzzle;

public class Estado {

	private int tamanho;
	public int posicoes[][] ; 
	public int profundidade;
	public Estado pai;
	public int valorRf;
	public int valorG;
	public int valorRh;
	public Estado obj;
	
	
	
	
	
	public Estado(int tamanho) {
		this.tamanho = tamanho;
		this.posicoes = new int[this.tamanho][this.tamanho];
	}
	public Estado(Estado estado) {
		this.posicoes = estado.posicoes;
		this.tamanho = estado.tamanho;
	}


	public int getTamanho() {
		return tamanho;
	}


	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}	
	
	
	public Estado getObj() {
		return obj;
	}
	public void setObj(Estado obj) {
		this.obj = obj;
	}
	public void buscaSolucao(){
		int profundMax = 30;
		Fila fila  = new Fila(); 
		List<Estado> avaliados = new ArrayList<Estado>();
		List<Estado> solucao = new ArrayList<Estado>();
		Estado estadoCopia = new Estado(this);
		Estado estado; 
		
		Estado nodo;
		Long tempoInicio = new Date().getTime();
		this.valorRh = calculaHeuristica(this);
		this.valorRf = this.valorRh;
		fila.enfileirar(this);
		
		while (!fila.filaVazia()) {
			nodo = fila.desenfileirar();
			// nodos++
			avaliados.add(nodo); // coloca nodo na lista de nodos já avaliados
			estado = nodo;
			if (comparaEstados(estado, this.obj)) {
				solucao.add(nodo);
				while (nodo.pai != null) {
					nodo = nodo.pai;
					solucao.add(nodo);
				}
				estado = solucao.remove(solucao.size() - 1);
				Puzzle.exibeEstado(estado);
				return;
			} else {
				if (profundidade < profundMax) {
					geraFilhos(nodo, avaliados);
				}
			}
		}
}
	
	private void geraFilhos(Estado nodo, List avaliados ) {
		this.profundidade++;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (nodo.posicoes[i][j] == 9) { // localiza o espa�o em branco
					// gera os filhos poss�veis e coloca na pilha
					if (i > 0)
						empilhaFilho(nodo, i, j, i - 1, j, avaliados); 
					if (i < 2)
						empilhaFilho(nodo, i, j, i + 1, j, avaliados); 
					if (j > 0)
						empilhaFilho(nodo, i, j, i, j - 1, avaliados); 
					if (j < 2)
						empilhaFilho(nodo, i, j, i, j + 1, avaliados);
					return; 
				} // end
			}
		}
	}
	
	
	private void empilhaFilho(Estado pai, int origemI, int origemJ, int destinoI, int destinoJ, List<Estado> avaliados){
		int  valorg, valorf, valorh;
		Integer i;
		Estado filho = new Estado(pai.tamanho);
		Estado estado = new Estado(pai);
		Puzzle.trocaPeca(estado, origemI, origemJ, destinoI, destinoJ);
		if (procuraLista(avaliados, estado) != null) {
			return;
		}
		valorg = pai.valorG + 1;
		valorh = calculaHeuristica(estado);
		valorf = valorg + valorh;
		
		filho.posicoes = estado.posicoes;
		filho.profundidade = estado.profundidade;
		filho.pai = pai;
		filho.valorRf  = valorf;
		filho.valorG = valorg;
		filho.valorRh = valorh;
		i = procuraLista(avaliados, estado);
		if (i != null) {
			if (avaliados.get(i).valorG <= valorg) {
				return;
			}else {
				if(i > 0) {					
					avaliados.remove(i -1); // ver
				}else {
					avaliados.remove(i);
				}
			}
		}
	}
	
	
	private Integer procuraLista(List<Estado> lista,Estado estado) {
		
		for (int i=0; i < lista.size(); i++) {
			if (comparaEstados(estado, lista.get(i)))
				return i;
		}
		return null;
			
	}
	
//	private void calculaTempo(Long tempoInicio) {
//		Long tempo = new Date().getTime() - tempoInicio;	// calcula tempo transcorrido
//		int tms =  (tempo%1000,3);
//		tempo = Math.floor(tempo/1000);
//		int tseg = pad(tempo%60,2);
//		tempo = Math.floor(tempo/60);
//		int tmin = pad(tempo%60,2);
//		tempo = Math.floor(tempo/60);
//		System.out.println(tempo + ":"+tmin +":"+tseg + "."+tms);
//	}
	
	public boolean comparaEstados(Estado estado, Estado obj){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if (estado.posicoes[i][j] != obj.posicoes[i][j]) {
					return false;
				}	
			}	
		}			
		return true;
	}
	
	public int calculaHeuristica(Estado estado) {
		int x,y,n,d,py,px;
		n = 0;
		for (y=0; y<3; y++) {
			for(x=0; x<3; x++) {
				if (estado.posicoes[y][x] != estado.obj.posicoes[y][x] && estado.posicoes[y][x] != 9) { // verifica peças fora do lugar, sem contar o espaço em branco
																								 //	calcula distância total das peças de suas posições corretas
					py = (estado.posicoes[y][x]-1)/3;	// calcula linha correta, baseado no valor da peça
					px = estado.posicoes[y][x]-py*3-1;			// calcula coluna correta
					d = Math.abs(x-px) + Math.abs(y-py);// Manhattan Distance
					n += d;
				}
			}
		}
		
		return n;	
	}
	
}
