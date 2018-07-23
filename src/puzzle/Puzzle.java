package puzzle;

import estado.Estado;

public class Puzzle {

	
	public static void main(String args[]) {
		Estado estado = inicializaTabuleiro (new Estado(3));
		estado.setObj(estado);
		embaralhaTabuleiro(estado,50,"");
		estado.buscaSolucao();
		
	}
	
	public static Estado inicializaTabuleiro(Estado estado) {
		int i = 0,j = 0,  c=1;
		for ( i = 0; i < estado.getTamanho(); i++) {
			for ( j = 0; j < estado.getTamanho(); j++) {						
					estado.posicoes[i][j] = c++;
		
			}
		}
		return estado;
	}
	public static void embaralhaTabuleiro(Estado estado, int qtdIteracoes, String ultimo) {
		boolean moveu;
		int posi =0,posj = 0;
		int movimentoAleatorio;
		int i = 0, j = 0;
		if (qtdIteracoes > 0) {
			for (i = 0; i < 3; i++) {
				for (j = 0; j < 3; j++) {
					if (estado.posicoes[i][j] == 9) {
						posi = i;
						posj = j;
						break;
					}
				}
			}
			
			moveu = false;
			while (!moveu) {
				movimentoAleatorio = (int) Math.floor(Math.random() * 4);
				switch (movimentoAleatorio) {
				case 0:
					if (posi > 0 && ultimo != "b") { // testa se pode mover para cima
						trocaPeca(estado, posi, posj, posi - 1, posi);
						ultimo = "c";
						moveu = true;
					}
					break;
				case 1:
					if (posi < 2 && ultimo != "c") { // testa se pode mover para baixo
						trocaPeca(estado, posi,posj, posi + 1, posj);
						ultimo = "b";
						moveu = true;
					}
					break;
				case 2:
					if (posj > 0 && ultimo != "d") { // testa se pode mover para a esquerda
						trocaPeca(estado, posi, posj, posi, posj - 1);
						ultimo = "e";
						moveu = true;
					}
					break;
				case 3:
					if (posj < 2 && ultimo != "e") { // testa se pode mover para a direita
						trocaPeca(estado, posi, posj, posi, posj + 1);
						ultimo = "d";
						moveu = true;
					}

				}
			}	
			qtdIteracoes--;
			embaralhaTabuleiro(estado, qtdIteracoes, ultimo);
		}else {			
			exibeEstado(estado);
		}
	}
	
	public static void trocaPeca(Estado estado,int origemI,int origemJ,int destinoI,int destinoJ) {
		int t = estado.posicoes[destinoI][destinoJ];
		estado.posicoes[destinoI][destinoJ] = estado.posicoes[origemI][origemJ];
		estado.posicoes[origemI][origemJ] = t;
	}

	public static void exibeEstado(Estado estado) { // exibe estado no console
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(j == 0) {
					System.out.println();
				}
				System.out.print(estado.posicoes[i][j]+ " ");
			}
		}
	}
	
}


