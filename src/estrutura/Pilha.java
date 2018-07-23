package estrutura;

import estado.Estado;
import puzzle.Puzzle;

public class Pilha {

	private int tamanho;
	private Estado[] estados;
	private int topo;
	
	public Pilha(int tamanho) {
		this.tamanho = tamanho;
		this.estados = new Estado[this.tamanho];
		this.topo = -1;
	}
	
	public void empilhar(Estado estado) {
		if (!pilhaCheia()) {
			this.estados[++topo] = estado;			
		} else {
			System.out.println("A pilha já está cheia");
		}
	}
	
	public Estado desempilhar() {
		if (!pilhaVazia()) {			
			return this.estados[topo--];
		}else {
			System.out.println("A pilha já está vazia");
		}
		return null;
	}
	
	public Estado getTopo() {
		return this.estados[topo];
	}
	
	private boolean pilhaVazia() {
		return (topo == -1);
	}
	
	private boolean pilhaCheia() {
		return (topo == tamanho -1);
	}
	
	public static void main(String args[]) {
		Pilha pilha = new Pilha(5);
		pilha.empilhar(Puzzle.inicializaTabuleiro(new Estado(3)));
		pilha.empilhar(Puzzle.inicializaTabuleiro(new Estado(4)));
		pilha.empilhar(Puzzle.inicializaTabuleiro(new Estado(5)));
		
		System.out.println(pilha.getTopo().getTamanho());
		
		pilha.desempilhar();
		
		System.out.println(pilha.getTopo().getTamanho());
		
	}
}
