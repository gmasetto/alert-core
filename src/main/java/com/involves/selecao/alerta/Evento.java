package com.involves.selecao.alerta;

public class Evento {
	
	private Integer tipo;
	private String descricao;
	
	public Evento() {
	}
	
	public Evento(Integer tipo, String descricao) {
		super();
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}
	
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
