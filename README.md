# Sistema de Alertas

Um software simples que através do processamento de dados coletados em pontos de venda gera informações estratégicas para empresas de trade-marketing.

## Como funciona

Esse projeto busca dados dos pontos de venda através do endereço `http://selecao-involves.agilepromoter.com/pesquisas` e transforma os mesmos em informações estratégicas. Essas informações são salvas em um banco de dados para posterior análise.

**Atenção: Você precisa do Mongo DB instalado e rodando para prosseguir**

1. Rode o sistema
    ``` 
    gradlew bootRun ou gradle bootRun
    ```
2. Abra o navegador em `http://localhost:8080/alertas/processar`
    ```
    Processamentos dos alertas, você também pode fazer o mesmo procedimento 
    swagger-ui do item 3
    ```
    
3. APIs
    ```
    http://localhost:8080/swagger-ui.html
    ```
