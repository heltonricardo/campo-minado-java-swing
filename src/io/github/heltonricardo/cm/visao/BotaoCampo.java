package io.github.heltonricardo.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import io.github.heltonricardo.cm.modelo.Campo;
import io.github.heltonricardo.cm.modelo.CampoEvento;
import io.github.heltonricardo.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

	private Campo campo;
	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCAR = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(169, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);
	
	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
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
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		switch (campo.minasNaVizinhanca()) {
			case 1:
				setForeground(TEXTO_VERDE); break;
			case 2:
				setForeground(Color.BLUE); break;
			case 3:
				setForeground(Color.YELLOW); break;
			case 4: case 5: case 6:
				setForeground(Color.RED); break;
			default:
				setForeground(Color.PINK);
		}
		
		String valor = !campo.vizinhancaSegura() ?
				Integer.toString(campo.minasNaVizinhanca()) : null;
					
		setText(valor);
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
	
	// Interface dos eventos do mouse:
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1)
			campo.abrir();
		else
			campo.alternarMarcacao();
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
