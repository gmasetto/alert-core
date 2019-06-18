package com.involves.selecao.alerta;

public class TipoAlerta {

	private String alerta;
	private boolean compararValor;
	private Evento evento;
	
	public String getAlerta() {
		return alerta;
	}
	
	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public boolean isCompararValor() {
		return compararValor;
	}

	public void setCompararValor(boolean compararValor) {
		this.compararValor = compararValor;
	}
	
}
