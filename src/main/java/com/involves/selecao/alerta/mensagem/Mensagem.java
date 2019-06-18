package com.involves.selecao.alerta.mensagem;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;

public interface Mensagem {
	
	public Alerta gerarAlerta(Pesquisa pesquisa, Resposta resposta, TipoAlerta tipoAlerta);
	
}
