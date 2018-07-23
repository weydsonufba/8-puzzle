package estado;

import java.util.Date;

public class Estado {

	private int tamanho;
	public int posicoes[][] ; 
	public int profundidade;
	public Estado pai;
	public int valorRf;
	public int valorRg;
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
		Estado estadoCopia = new Estado(this);
		Long tempoInicio = new Date().getTime();
		this.valorRh = calculaHeuristica();
		this.valorRf = this.valorRh;
	}
	
	public int calculaHeuristica() {
		int x,y,n,d,py,px;
		n = 0;
		for (y=0; y<3; y++) {
			for(x=0; x<3; x++) {
				if (this.posicoes[y][x] != this.obj.posicoes[y][x] && this.posicoes[y][x] != 9) { // verifica peças fora do lugar, sem contar o espaço em branco
																								 //	calcula distância total das peças de suas posições corretas
					py = (this.posicoes[y][x]-1)/3;	// calcula linha correta, baseado no valor da peça
					px = this.posicoes[y][x]-py*3-1;			// calcula coluna correta
					d = Math.abs(x-px) + Math.abs(y-py);// Manhattan Distance
					n += d;
				}
			}
		}
		
		return n;	
	}
	
}
