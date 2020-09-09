package io.github.heltonricardo.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import io.github.heltonricardo.cm.excecao.ExplosaoException;
import io.github.heltonricardo.cm.excecao.SairException;
import io.github.heltonricardo.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while (continuar) {
				
				cicloDoJogo();
				System.out.print("Outra partida? [S/n]: ");
				String resposta = entrada.nextLine();
				
				if (resposta.equalsIgnoreCase("n"))
					continuar = false;
				else
					tabuleiro.reiniciar();
			}
		} catch(SairException e) {
			System.out.println("Tchau!");
		} finally {
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				String digitado = capturarValorDigitado("Entre o valor para (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
					.map(m -> Integer.parseInt(m.trim())).iterator();
				
				digitado = capturarValorDigitado("[1] - Abrir ou [2] - Des(Marcar): ");
				
				if (digitado.equals("1"))
					tabuleiro.abrir(xy.next(), xy.next());
				else if (digitado.equals("2"))
					tabuleiro.AlterarMarcacao(xy.next(), xy.next());
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!");
			
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if (digitado.equalsIgnoreCase("sair"))
			throw new SairException();
		
		return digitado;
	}
}
