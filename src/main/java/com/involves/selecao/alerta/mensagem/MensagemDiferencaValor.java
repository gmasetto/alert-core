package com.involves.selecao.alerta.mensagem;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;

public class MensagemDiferencaValor implements Mensagem {
	
	@SuppressWarnings("unused")
	@Override
	public Alerta gerarAlerta(Pesquisa pesquisa, Resposta resposta, TipoAlerta tipoAlerta) {
		Alerta alerta = new Alerta();
		Integer margem = 0;
			
		Integer valorColetado = Integer.parseInt(resposta.getResposta());

		if (pesquisa.getPreco_estipulado() != null) {
			Integer precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
			
			margem = getMargem(precoEstipulado, valorColetado);
			
			alerta.setDescricao(getDescricao(precoEstipulado, valorColetado, tipoAlerta.getEvento().getDescricao()));
			alerta.setMargem(margem);
		} else if (pesquisa.getParticipacao_estipulada() != null) {
			Integer participacaoEstipulado = Integer.parseInt(pesquisa.getParticipacao_estipulada());
			
			margem = getMargem(participacaoEstipulado, valorColetado);
			
			alerta.setDescricao(getDescricao(participacaoEstipulado, valorColetado, tipoAlerta.getEvento().getDescricao()));
			alerta.setMargem(margem);			
		}
		
		alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
		alerta.setFlTipo(tipoAlerta.getEvento().getTipo());
		alerta.setProduto(pesquisa.getProduto());

		return alerta;
	}
	
	public String getDescricao(Integer valorEncontrado, Integer valorColetado, String descricao) {
		if (valorColetado > valorEncontrado) {
			return descricao + " - Acima";
		} else if (valorColetado < valorEncontrado) {
			return descricao +  " - Abaixo";
		}
		
		return "";
	}
	
	public Integer getMargem(Integer preco, Integer valorColetado) {
		return preco - valorColetado;
	}
	
}
