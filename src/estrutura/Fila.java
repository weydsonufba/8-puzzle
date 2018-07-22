package estrutura;

public class Fila {

	private int tamanho;
//	private Cidade[] cidades;
//	private int inicio;
//	private int fim;
//	private int numeroElementos;
//	
//	public Fila(int tamanho) {
//		this.tamanho = tamanho;
//		this.cidades = new Cidade[this.tamanho];
//		this.inicio = 0;
//		this.fim = -1;
//		this.numeroElementos = 0;
//	}
//	
//	public void enfileirar(Cidade cidade) {
//		if (!filacheia()) {
//			if (this.fim == this.tamanho - 1) {
//				this.fim = -1;
//			}
//
//			this.cidades[++fim] = cidade;
//			this.numeroElementos++;
//		} else {
//			System.out.println("A fila já está cheia");
//		}
//	}
//	
//	public Cidade desenfileirar() {
//		if (!filaVazia()) {
//			Cidade temp = this.cidades[this.inicio++];
//			if (this.inicio == this.tamanho) {
//				this.inicio = 0;
//			}
//			this.numeroElementos--;
//			return temp;
//		}else {
//			System.out.println("A fila já está vazia");
//		}
//		return null;
//	}
//	
//	public Cidade getPrimeiro() {
//		return this.cidades[this.inicio];
//	}
//	
//	public boolean filaVazia() {
//		return (numeroElementos == 0);
//	}
//	
//	public boolean filacheia() {
//		return (numeroElementos == this.tamanho);
//	}
//	
//	public int getNumeroElementos() {
//		return numeroElementos;
//	}
//
//	public void setNumeroElementos(int numeroElementos) {
//		this.numeroElementos = numeroElementos;
//	}
//
//	public static void main(String args[]) {
//		Fila fila = new Fila(5);
//		Mapa mapa = new Mapa();
//		fila.enfileirar(mapa.getAraucaria());
//		fila.enfileirar(mapa.getBalsaNova());
//		fila.enfileirar(mapa.getContenda());
//		System.out.println(fila.getPrimeiro().getNome());
//		fila.desenfileirar();
//		fila.desenfileirar();
//		fila.enfileirar(mapa.getCanoinhas());
//		fila.enfileirar(mapa.getIrati());
//		fila.enfileirar(mapa.getPalmeira());
//		fila.enfileirar(mapa.getPortoUniao());
//		
//		System.out.println(fila.getPrimeiro().getNome());
//	}
}
