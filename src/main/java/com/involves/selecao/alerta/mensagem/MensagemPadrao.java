package com.involves.selecao.alerta.mensagem;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;

public class MensagemPadrao implements Mensagem {
	
	@Override
	public Alerta gerarAlerta(Pesquisa pesquisa, Resposta resposta, TipoAlerta tipoAlerta) {
		Alerta alerta = new Alerta();
	    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
	    alerta.setFlTipo(tipoAlerta.getEvento().getTipo());
	    alerta.setDescricao(tipoAlerta.getEvento().getDescricao());
	    alerta.setProduto(pesquisa.getProduto());
	    
	    return alerta;
	    
	}

}
