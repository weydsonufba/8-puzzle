package estrutura;

import java.util.ArrayList;
import java.util.List;

import estado.Estado;
import puzzle.Puzzle;

public class Pilha {

	private List<Estado> estados;
	private int topo;
	
	public Pilha() {
		this.estados = new ArrayList<Estado>();
		this.topo = -1;
	}
	
	public void empilhar(Estado estado) {
			++topo;
			this.estados.add(estado);			
		
	}
	
	public Estado desempilhar() {
		if (!pilhaVazia()) {
			topo--;
			return this.estados.remove(this.estados.size()-1);
		}else {
			System.out.println("A pilha já está vazia");
		}
		return null;
	}
	
	public Estado getTopo() {		
		if(!pilhaVazia()){			
			return this.estados.get(this.estados.size()-1);
		}else{
			System.out.println("A pilha já está vazia");
			return null;
		}
	}
	
	private boolean pilhaVazia() {
		return (topo == -1);
	}
	
	public int  getTamanho() {
		return topo;
	}
	public static void main(String args[]) {
		Pilha pilha = new Pilha();
		pilha.empilhar(Puzzle.inicializaTabuleiro(new Estado(3)));
		pilha.empilhar(Puzzle.inicializaTabuleiro(new Estado(4)));
		pilha.empilhar(Puzzle.inicializaTabuleiro(new Estado(5)));
		
		System.out.println(pilha.getTopo().getTamanho());
		
		pilha.desempilhar();
		
		System.out.println(pilha.getTopo().getTamanho());
		
	}
}
