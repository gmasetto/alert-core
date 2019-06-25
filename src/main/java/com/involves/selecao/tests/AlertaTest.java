package com.involves.selecao.tests;

import com.involves.selecao.AlertaController;
import com.involves.selecao.alerta.Alerta;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AlertaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertaController alertaController;

    @Test
    public void testAlertasTotalizadores() throws Exception {
        given(this.alertaController.alertasToalizador()).willReturn(10L);

        this.mockMvc.perform(get("/alertas/totalizador")).andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void testAlertas() throws Exception {
        Alerta alerta = new Alerta();
        alerta.setDescricao("Preço abaixo do estipulado!");
//        alerta.setFlTipo(3);
//        alerta.setMargem(5);
//        alerta.setPontoDeVenda("Padaria do seu João");
//        alerta.setProduto("Ovo de Pascoa Kinder 48");
//        alerta.setCategoria("null");

        ArrayList<Alerta> alertas = new ArrayList<>();
        alertas.add(alerta);

        given(this.alertaController.alertas(1,10, "Ovo de Pascoa Kinder 48",
                "Padaria do seu João")).willReturn(alertas);

        this.mockMvc.perform(get("/alertas")
                .param("page", "1").param("size", "10")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("[{'descricao': 'Preço abaixo do estipulado!'}]"));

    }

}

