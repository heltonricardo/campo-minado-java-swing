package io.github.heltonricardo.cm.visao;

import io.github.heltonricardo.cm.modelo.Tabuleiro;

public class Temp {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(3, 3, 9);
		
		tabuleiro.adicionarObservador(e -> {
			if (e.isGanhou())
				System.out.println("Ganhou");
			else
				System.out.println("Perdeu");
		});
		
		tabuleiro.AlterarMarcacao(0, 0);
		tabuleiro.AlterarMarcacao(0, 1);
		tabuleiro.AlterarMarcacao(0, 2);
		tabuleiro.AlterarMarcacao(1, 0);
		tabuleiro.AlterarMarcacao(1, 1);
		tabuleiro.AlterarMarcacao(1, 2);
		tabuleiro.AlterarMarcacao(2, 0);
		tabuleiro.AlterarMarcacao(2, 1);
		tabuleiro.AlterarMarcacao(2, 2);
	}
}
