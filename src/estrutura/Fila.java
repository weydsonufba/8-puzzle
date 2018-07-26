package estrutura;

import java.util.ArrayList;
import java.util.List;

import estado.Estado;

public class Fila {

	private List<Estado> estados;
	private int inicio;
	private int fim;
	private int numeroElementos;
	
	public Fila() {
		this.estados = new ArrayList<Estado>();
		this.inicio = 0;
		this.fim = -1;
		this.numeroElementos = 0;
	}
	
	public void enfileirar(Estado estado) {
			++fim;
			if(!estados.isEmpty()) {				
				estado.pai = this.estados.get(this.estados.size()-1);
			}
			this.estados.add(estado);
			this.numeroElementos++;
	}
	
	public Estado desenfileirar() {
		if (!estados.isEmpty()) {
			this.inicio++;
			Estado temp = this.estados.remove(0);
			
			this.numeroElementos--;
			return temp;
		}else {
			System.out.println("A fila já está vazia");
		}
		return null;
	}
	
	public Estado getPrimeiro() {
		return this.estados.get(0);
	}
	
	public boolean filaVazia() {
		return estados.isEmpty();
	}
	
	public int getNumeroElementos() {
		return numeroElementos;
	}

	public void setNumeroElementos(int numeroElementos) {
		this.numeroElementos = numeroElementos;
	}
	public int getSize() {
		return this.estados.size();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public static void main(String args[]) {
		Fila fila = new Fila();
		fila.enfileirar(new Estado(3));
		fila.enfileirar(new Estado(4));
		fila.enfileirar(new Estado(5));
		System.out.println(fila.getPrimeiro().getTamanho());
		fila.desenfileirar();
		fila.desenfileirar();

		System.out.println(fila.getPrimeiro().getTamanho());
	}
}
