package io.github.heltonricardo.cm.visao;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import io.github.heltonricardo.cm.modelo.Campo;
import io.github.heltonricardo.cm.modelo.CampoEvento;
import io.github.heltonricardo.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador {

	private Campo campo;
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCAR = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(169, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		campo.adicionarObservador(this);
	}

	@Override
	public void ocorreuEvento(Campo campo, CampoEvento evento) {
		switch(evento) {
			case ABRIR:
				aplicarEstiloAbrir();
				break;
			case MARCAR:
				aplicarEstiloMarcar();
				break;
			case EXPLODIR:
				aplicarEstiloExplodir();
				break;
			default:
				aplicarEstiloPadrao();
		}
	}

	private void aplicarEstiloAbrir() {
		// TODO Auto-generated method stub
		
	}
	
	private void aplicarEstiloMarcar() {
		// TODO Auto-generated method stub
		
	}
	
	private void aplicarEstiloExplodir() {
		// TODO Auto-generated method stub
		
	}
	
	private void aplicarEstiloPadrao() {
		// TODO Auto-generated method stub
		
	}

}
