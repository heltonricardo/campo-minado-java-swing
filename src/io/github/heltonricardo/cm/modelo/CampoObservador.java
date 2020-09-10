package io.github.heltonricardo.cm.modelo;

@FunctionalInterface
public interface CampoObservador {

	public void ocorreuEvento(Campo campo, CampoEvento evento);
}
