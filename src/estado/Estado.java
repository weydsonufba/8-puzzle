package estado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import estrutura.Fila;
import puzzle.Puzzle;

public class Estado  implements Cloneable{

	private int tamanho;
	private int posicoes[][] ; 
	public int profundidade;
	public Estado pai;
	public int valorRf;
	public int valorG;
	public int valorRh;
	public Estado obj;
	
	
	
	public Object clone() {
		try {
		 Estado clone =	(Estado) super.clone();
		 clone.setPosicoes(clonePosicoes(this));
		 //System.arraycopy(this.getPosicoes(), 0, clone.getPosicoes(), 0, this.tamanho);
			return clone;
		} catch (CloneNotSupportedException e) {
			System.out.println("Cloning not allowed.");
            return this;
		}
	}
	
	public int[][] clonePosicoes(Estado estado) {
		int[][] clPosicoes = new int[estado.tamanho][estado.tamanho];
		for(int i=0; i < estado.tamanho; i++) 
			for (int j=0; j < estado.tamanho; j++ )
				clPosicoes[i][j] = estado.posicoes[i][j];
		return clPosicoes;
	}
	
	public int[][] getPosicoes() {
		return posicoes;
	}
	public void setPosicoes(int[][] posicoes) {
		this.posicoes = posicoes;
	}
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
		int profund = 0;
		int nodos = 0;
		int profundMax = 30;
		Fila fila  = new Fila(); 
		List<Estado> avaliados = new ArrayList<Estado>();
		List<Estado> solucao = new ArrayList<Estado>();
		Estado estadoCopia = ObjectUtils.clone(this);
		Estado estado; 
		
		Estado nodo;
		Long tempoInicio = new Date().getTime();
		Long tempoFim;
		this.valorRh = calculaHeuristica(this);
		this.valorRf = this.valorRh;
		fila.enfileirar(this);
		int i = 0;
		while (!fila.filaVazia()) {
			nodo = fila.desenfileirar();
			 nodos++;
			avaliados.add(nodo); // coloca nodo na lista de nodos já avaliados
			estado = nodo;
			profund = nodo.profundidade;
			if (comparaEstados(estado, this.obj)) {
				calculaTempo(tempoInicio);
				solucao.add(nodo);
				while (nodo.pai != null) {
					nodo = nodo.pai;
					solucao.add(nodo);
				}
				estado = solucao.remove(solucao.size() - 1);
				System.out.println("profundidade: "+profund);
				System.out.println("N�s testados: "+nodos);
				Puzzle.exibeEstado(estado);
				Puzzle.exibeSolucao(solucao);
				return;
			} else {
				if (profund < profundMax) {
					geraFilhos(nodo,profund, avaliados, fila);
					
//					System.out.println(i++);
				}
			}
		}
}
	
	private void geraFilhos(Estado nodo,int profund, List avaliados, Fila fila ) {
		profund++;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (nodo.posicoes[i][j] == 0) { // localiza o espa�o em branco
					// gera os filhos poss�veis e coloca na pilha
					if (i > 0)
						empilhaFilho(nodo,profund, i, j, i - 1, j, fila); 
					if (i < 2)
						empilhaFilho(nodo,profund, i, j, i + 1, j, fila); 
					if (j > 0)
						empilhaFilho(nodo,profund, i, j, i, j - 1, fila); 
					if (j < 2)
						empilhaFilho(nodo,profund, i, j, i, j + 1, fila);
					return; 
				} // end
			}
		}
	}
	
	
	private void empilhaFilho(Estado pai,int profund, int origemI, int origemJ, int destinoI, int destinoJ, Fila fila){
		int  valorg, valorf, valorh;
		Integer i;
		Estado filho = new Estado(pai.tamanho);
		Estado estado = ObjectUtils.clone(pai);
		estado.setObj(pai.getObj());
		Puzzle.trocaPeca(estado, origemI, origemJ, destinoI, destinoJ);
		if (procuraLista(fila, estado) != null) {
			return;
		}
		valorg = pai.valorG + 1;
		valorh = calculaHeuristica(estado);
		valorf = valorg + valorh;
		
		filho.posicoes = clonePosicoes(estado);
	    filho.profundidade = profund;
		filho.pai = pai;
		filho.valorRf  = valorf;
		filho.valorG = valorg;
		filho.valorRh = valorh;
		filho.setObj(estado.getObj());
		i = procuraLista(fila, estado);
		if (i != null) {
			if (fila.getEstados().get(i).valorG <= valorg) {
				return;
			}else {
				if(i > 0) {					
					fila.getEstados().remove(i -1); // ver 
				}else {
					fila.getEstados().remove(i);
				}
			}
		}
		insereFilaPrioridade(filho,fila);
	}
	
	private void insereFilaPrioridade(Estado filho, Fila fila) {
		int i = 0;

		while (i < fila.getSize() && filho.valorRf > fila.getEstados().get(i).valorRf) {			
			i++;
		}
		if (i >= fila.getSize())
			fila.enfileirar(filho);		// insere no fim
		else
			fila.getEstados().add(i, filho);	// insere na posição i 
	}
	
	private Integer procuraLista(Fila fila,Estado estado) {
		
		for (int i=0; i < fila.getSize(); i++) {
			if (comparaEstados(estado, fila.getEstados().get(i)))
				return i;
		}
		return null;
			
	}
	
	private void calculaTempo(Long tempoInicio) {
		Long tempo = new Date().getTime() - tempoInicio;	// calcula tempo transcorrido
		int tms =  Integer.parseInt(StringUtils.leftPad(String.valueOf(tempo%1000), 3,"0"));
		tempo = (long) Math.floor(tempo/1000);
		
		int tseg = Integer.parseInt(StringUtils.leftPad(String.valueOf(tempo%60), 2,"0"));
		tempo = (long) Math.floor(tempo/60);
		int tmin = Integer.parseInt(StringUtils.leftPad(String.valueOf(tempo%60), 2,"0"));
		tempo = (long) Math.floor(tempo/60);
		System.out.print("Tempo percorrido: ");
		System.out.println(tempo + ":"+tmin +":"+tseg + "."+tms);
	}
	
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
				if (estado.posicoes[y][x] != estado.obj.posicoes[y][x] && estado.posicoes[y][x] != 0) { // verifica peças fora do lugar, sem contar o espaço em branco
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
