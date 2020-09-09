package io.github.heltonricardo.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import io.github.heltonricardo.cm.excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	void minar() {
		minado = true;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		int d = (int) (Math.sqrt(Math.pow(linha - vizinho.linha, 2) + 
				       Math.pow(coluna - vizinho.coluna, 2)));
		if (d == 1) {
			vizinhos.add(vizinho);
			return true;
		}
		return false;
	}
	
	void alternarMarcacao() {
		if (!aberto)
			marcado = !marcado;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = aberto && !minado;
		boolean protegido = minado && marcado;
		
		return desvendado || protegido;
	}
	
	boolean abrir() {
		if (aberto || marcado)
			return false;
		
		aberto = true;
		
		if (minado)
			throw new ExplosaoException();
		
		if (vizinhancaSegura())
			vizinhos.forEach(v -> v.abrir());
		
		return true;
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	@Override
	public String toString() {
		if (marcado)
			return "X";
		else if (aberto && minado)
			return "*";
		else if (aberto && minasNaVizinhanca() > 0)
			return Long.toString(minasNaVizinhanca());
		else if (aberto)
			return " ";
		else
			return "?";
	}
}
