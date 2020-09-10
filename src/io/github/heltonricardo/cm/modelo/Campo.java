package io.github.heltonricardo.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import com.sun.jdi.AbsentInformationException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<Campo>();
	private List<CampoObservador> observadores = new ArrayList<>();
	
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
		
		if (aberto)
			notificarObservadores(CampoEvento.ABRIR);
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	void minar() {
		minado = true;
	}
	
	public void adicionarObservador(CampoObservador obs) {
		observadores.add(obs);
	}
	
	private void notificarObservadores(CampoEvento evento) {
		observadores.stream().forEach(o -> o.ocorreuEvento(this, evento));
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
		if (!aberto) {
			marcado = !marcado;
			
			if (marcado)
				notificarObservadores(CampoEvento.MARCAR);
			else
				notificarObservadores(CampoEvento.DESMARCAR);
		}
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = aberto && !minado;
		boolean protegido = minado && marcado;
		
		return desvendado || protegido;
	}
	
	boolean abrir() {
		if (aberto || marcado)
			return false;
		
		if (minado) {
			notificarObservadores(CampoEvento.EXPLODIR);
			return true;
		}
		
		setAberto(true);
		
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
}
