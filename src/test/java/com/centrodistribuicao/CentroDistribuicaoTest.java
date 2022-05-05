package com.centrodistribuicao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.centrodistribuicao.CentroDistribuicao.SITUACAO;
import com.centrodistribuicao.CentroDistribuicao.TIPOPOSTO;

public class CentroDistribuicaoTest 
{
    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 1250;
    public static final int MAX_GASOLINA = 10000;

    private CentroDistribuicao centroDistribuicao = null;
    @BeforeEach void setUp() {
        // centroDistribuicao = new CentroDistribuicao();
    }

    @Test
    public void testeSituacaoNormal()
    {
        centroDistribuicao = new CentroDistribuicao(MAX_ADITIVO/2, MAX_GASOLINA/2, MAX_ALCOOL/2, MAX_ALCOOL/2);
        SITUACAO situacaoEsperada = SITUACAO.NORMAL;
        SITUACAO situacaoRecebida = centroDistribuicao.getSituacao();
        Assertions.assertEquals(situacaoEsperada, situacaoRecebida);
    }

    @Test
    public void testeSituacaoSobraviso()
    {
        centroDistribuicao = new CentroDistribuicao(MAX_ADITIVO/4, MAX_GASOLINA/4, MAX_ALCOOL/4, MAX_ALCOOL/4);
        SITUACAO situacaoEsperada = SITUACAO.SOBRAVISO;
        SITUACAO situacaoRecebida = centroDistribuicao.getSituacao();
        Assertions.assertEquals(situacaoEsperada, situacaoRecebida);
    }

    @Test
    public void testeSituacaoEmergencia()
    {
        centroDistribuicao = new CentroDistribuicao(MAX_ADITIVO/4 - 1, MAX_GASOLINA/4 - 1, MAX_ALCOOL/4 - 1, MAX_ALCOOL/4 - 1);
        SITUACAO situacaoEsperada = SITUACAO.EMERGENCIA;
        SITUACAO situacaoRecebida = centroDistribuicao.getSituacao();
        Assertions.assertEquals(situacaoEsperada, situacaoRecebida);
    }

    @Test
    public void testeSituacaoNormalPedidoCombustivelComum()
    {
        int qtdDeseja = 20;
        int qntdAditivoEsperado = (int) (qtdDeseja * 0.05);
        int qntdGasolinaEsperado = (int) (qtdDeseja * 0.7);
        int qntdAlcoolEsperadoPorTanque = (int) ((qtdDeseja * 0.25) / 2);

        int aditivoInicial = (int) (MAX_ADITIVO/2);
        int gasolinaInicial = (int) (MAX_GASOLINA/2);
        int alcoolInicial = (int) (MAX_ALCOOL/2);

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        int respostaEsperada[] = {aditivoInicial - qntdAditivoEsperado, gasolinaInicial - qntdGasolinaEsperado, alcoolInicial - qntdAlcoolEsperadoPorTanque, alcoolInicial - qntdAlcoolEsperadoPorTanque}; 
        int respostaRecebida[] = centroDistribuicao.encomendaCombustivel(qtdDeseja, TIPOPOSTO.COMUM);
        Assertions.assertArrayEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSituacaoSobravisoPedidoCombustivelComum()
    {
        int qtdDeseja = 50;
        int qntdAditivoEsperado = (int) (qtdDeseja * 0.05 * 0.5);
        int qntdGasolinaEsperado = (int) (qtdDeseja * 0.7 * 0.5);
        int qntdAlcoolEsperadoPorTanque = (int) ((qtdDeseja * 0.25 * 0.5) / 2);

        int aditivoInicial = (int) (MAX_ADITIVO/4);
        int gasolinaInicial = (int) (MAX_GASOLINA/4);
        int alcoolInicial = (int) (MAX_ALCOOL/4);

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        int respostaEsperada[] = {aditivoInicial - qntdAditivoEsperado, gasolinaInicial - qntdGasolinaEsperado, alcoolInicial - qntdAlcoolEsperadoPorTanque, alcoolInicial - qntdAlcoolEsperadoPorTanque}; 
        int respostaRecebida[] = centroDistribuicao.encomendaCombustivel(qtdDeseja, TIPOPOSTO.COMUM);
        Assertions.assertArrayEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSituacaoEmergenciaPedidoCombustivelComum()
    {
        int qtdDeseja = 50;

        int aditivoInicial = (int) (MAX_ADITIVO/4 - 1);
        int gasolinaInicial = (int) (MAX_GASOLINA/4 - 1);
        int alcoolInicial = (int) (MAX_ALCOOL/4 - 1);

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        int respostaEsperada[] = {-14}; 
        int respostaRecebida[] = centroDistribuicao.encomendaCombustivel(qtdDeseja, TIPOPOSTO.COMUM);
        Assertions.assertArrayEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSituacaoNormalPedidoCombustivelEstrategico()
    {
        int qtdDeseja = 50;
        int qntdAditivoEsperado = (int) (qtdDeseja * 0.05);
        int qntdGasolinaEsperado = (int) (qtdDeseja * 0.7);
        int qntdAlcoolEsperadoPorTanque = (int) ((qtdDeseja * 0.25) / 2);

        int aditivoInicial = (int) (MAX_ADITIVO/2);
        int gasolinaInicial = (int) (MAX_GASOLINA/2);
        int alcoolInicial = (int) (MAX_ALCOOL/2);

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        int respostaEsperada[] = {aditivoInicial - qntdAditivoEsperado, gasolinaInicial - qntdGasolinaEsperado, alcoolInicial - qntdAlcoolEsperadoPorTanque, alcoolInicial - qntdAlcoolEsperadoPorTanque}; 
        int respostaRecebida[] = centroDistribuicao.encomendaCombustivel(qtdDeseja, TIPOPOSTO.ESTRATEGICO);
        Assertions.assertArrayEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSituacaoSobravisoPedidoCombustivelEstrategico()
    {
        int qtdDeseja = 50;
        int qntdAditivoEsperado = (int) (qtdDeseja * 0.05);
        int qntdGasolinaEsperado = (int) (qtdDeseja * 0.7);
        int qntdAlcoolEsperadoPorTanque = (int) ((qtdDeseja * 0.25) / 2);

        int aditivoInicial = (int) (MAX_ADITIVO/2);
        int gasolinaInicial = (int) (MAX_GASOLINA/2);
        int alcoolInicial = (int) (MAX_ALCOOL/2);

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        int respostaEsperada[] = {aditivoInicial - qntdAditivoEsperado, gasolinaInicial - qntdGasolinaEsperado, alcoolInicial - qntdAlcoolEsperadoPorTanque, alcoolInicial - qntdAlcoolEsperadoPorTanque}; 
        int respostaRecebida[] = centroDistribuicao.encomendaCombustivel(qtdDeseja, TIPOPOSTO.ESTRATEGICO);
        Assertions.assertArrayEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSituacaoEmergenciaPedidoCombustivelEstrategico()
    {
        int qtdDeseja = 4;
        int qntdAditivoEsperado = (int) (qtdDeseja * 0.05 * 0.5);
        int qntdGasolinaEsperado = (int) (qtdDeseja * 0.7 * 0.5);
        int qntdAlcoolEsperadoPorTanque = (int) ((qtdDeseja * 0.25 * 0.5) / 2);

        int aditivoInicial = (int) (MAX_ADITIVO/4 - 1);
        int gasolinaInicial = (int) (MAX_GASOLINA/4 - 1);
        int alcoolInicial = (int) (MAX_ALCOOL/4 - 1);

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        int respostaEsperada[] = {aditivoInicial - qntdAditivoEsperado, gasolinaInicial - qntdGasolinaEsperado, alcoolInicial - qntdAlcoolEsperadoPorTanque, alcoolInicial - qntdAlcoolEsperadoPorTanque}; 
        int respostaRecebida[] = centroDistribuicao.encomendaCombustivel(qtdDeseja, TIPOPOSTO.ESTRATEGICO);
        Assertions.assertArrayEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSobravisoParaNormal()
    {
        int aditivoInicial = (int) (MAX_ADITIVO/2 - 1);
        int gasolinaInicial = (int) (MAX_GASOLINA/2 - 1);
        int alcoolInicial = (int) (MAX_ALCOOL/2 - 1);

        int qtdAditivo = 10;
        int qtdGasolina = 10;
        int qtdAlcool = 10;

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        
        centroDistribuicao.recebeAditivo(qtdAditivo);
        centroDistribuicao.recebeGasolina(qtdGasolina);
        centroDistribuicao.recebeAlcool(qtdAlcool);

        SITUACAO respostaEsperada = SITUACAO.NORMAL;
        SITUACAO respostaRecebida = centroDistribuicao.getSituacao();

        Assertions.assertEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeEmergenciaParaSobraviso()
    {
        int aditivoInicial = (int) (MAX_ADITIVO/4 - 1);
        int gasolinaInicial = (int) (MAX_GASOLINA/4 - 1);
        int alcoolInicial = (int) (MAX_ALCOOL/4 - 1);

        int qtdAditivo = 10;
        int qtdGasolina = 10;
        int qtdAlcool = 10;

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        
        centroDistribuicao.recebeAditivo(qtdAditivo);
        centroDistribuicao.recebeGasolina(qtdGasolina);
        centroDistribuicao.recebeAlcool(qtdAlcool);

        SITUACAO respostaEsperada = SITUACAO.SOBRAVISO;
        SITUACAO respostaRecebida = centroDistribuicao.getSituacao();

        Assertions.assertEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSituacaoNormalParaSobravisoPedidoCombustivelComum()
    {
        int aditivoInicial = (int) (MAX_ADITIVO/2);
        int gasolinaInicial = (int) (MAX_GASOLINA/2);
        int alcoolInicial = (int) (MAX_ALCOOL/2);

        int qtdade = 20;

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        
        centroDistribuicao.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);

        SITUACAO respostaEsperada = SITUACAO.SOBRAVISO;
        SITUACAO respostaRecebida = centroDistribuicao.getSituacao();

        Assertions.assertEquals(respostaEsperada, respostaRecebida);
    }

    @Test
    public void testeSituacaoSobravisoParaEmergenciaPedidoCombustivelComum()
    {
        int aditivoInicial = (int) (MAX_ADITIVO/4);
        int gasolinaInicial = (int) (MAX_GASOLINA/4);
        int alcoolInicial = (int) (MAX_ALCOOL/4);

        int qtdade = 40;

        centroDistribuicao = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
        
        centroDistribuicao.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);

        SITUACAO respostaEsperada = SITUACAO.EMERGENCIA;
        SITUACAO respostaRecebida = centroDistribuicao.getSituacao();

        Assertions.assertEquals(respostaEsperada, respostaRecebida);
    }

    // @Test()
    // public void testeArumentoInvalidoConstrutor()
    // {
    //     centroDistribuicao = new CentroDistribuicao(0, 0, 0, 0);

    //     SITUACAO respostaEsperada = SITUACAO.EMERGENCIA;
    //     SITUACAO respostaRecebida = centroDistribuicao.getSituacao();

    //     Assertions.assertEquals(respostaEsperada, respostaRecebida);
    // }
}


// 10
// 0,5 aditivo 
// 7 gasolina
// 2,5 alcool -> 1,25 pra cada tanque

