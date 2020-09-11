package io.github.heltonricardo.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Tabuleiro implements CampoObservador {

	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<Campo>();
	private final List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
	
	public void adicionarObservador(Consumer<ResultadoEvento> obs) {
		observadores.add(obs);
	}
	
	private void notificarObservadores(boolean resultado) {
		observadores.stream().forEach(o -> o.accept(new ResultadoEvento(resultado)));
	}

	private void gerarCampos() {
		for (int i = 0; i < linhas; ++i)
			for (int j = 0; j < colunas; ++j) {
				Campo campo = new Campo(i, j);
				campo.adicionarObservador(this);
				campos.add(campo);
			}
	}

	private void associarVizinhos() {
		for (Campo c1: campos)
			for (Campo c2: campos)
				c1.adicionarVizinho(c2);
	}

	private void sortearMinas() {
		Random rnd = new Random();
		int minadas = 0;
		int max = linhas * colunas;
		int num;
		
		while (minadas < minas) {
			num = rnd.nextInt(max);
			if (!campos.get(num).isMinado()) {
				campos.get(num).minar();
				++minadas;
			}
		}
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		if (0 <= linha && linha < linhas && 0 <= coluna && coluna < colunas)
			campos.get(linha * colunas + coluna).abrir();
	}
	
	public void mostrarMinas() {
		campos.stream()
		.filter(c -> c.isMinado())
		.forEach(c -> c.setAberto(true));
	}
	
	public void AlterarMarcacao(int linha, int coluna) {
		if (0 <= linha && linha < linhas && 0 <= coluna && coluna < colunas)
			campos.get(linha * colunas + coluna).alternarMarcacao();
	}

	@Override
	public void ocorreuEvento(Campo campo, CampoEvento evento) {
		if (evento == CampoEvento.EXPLODIR) {
			mostrarMinas();
			notificarObservadores(false);
		} else if (objetivoAlcancado()) {
			notificarObservadores(true);
		}
	}
}