package com.centrodistribuicao;

public class CentroDistribuicao {
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }
    
    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 1250;
    public static final int MAX_GASOLINA = 10000;
    
    public int tAditivo, tGasolina, tAlcool1, tAlcool2;
    public SITUACAO situacaoAtual;
      
    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        if(tAditivo > 0 || tGasolina > 0 || tAlcool1 > 0 || tAlcool2 > 0){
            this.tAditivo = tAditivo;
            this.tGasolina = tGasolina;
            if(tAlcool1 == tAlcool2){
                this.tAlcool1 = tAlcool1;
                this.tAlcool2 = tAlcool2;
            }
            else{
                int soma = tAlcool1 + tAlcool2;
                this.tAlcool1 = soma/2;
                this.tAlcool2 = soma/2;
            }
            defineSituacao();
        }
        else{
            throw new IllegalArgumentException("A quantidade deve ser maior que zero!");
        }
    }
    
    public void defineSituacao(){ 
        if((getTGasolina() >= (int) (MAX_GASOLINA/2)) && (getTAditivo() >= (int) (MAX_ADITIVO/2)) &&
            (getTAlcool1() >= (int) (MAX_ALCOOL/2)) && (getTAlcool2() >= (int) (MAX_ALCOOL/2))){
            this.situacaoAtual = SITUACAO.NORMAL;
        }
        else if((getTGasolina() < (int) (MAX_GASOLINA/2) && getTGasolina() >= (int) (MAX_GASOLINA/4))
            || (getTAditivo() < (int) (MAX_ADITIVO/2) && getTAditivo() >= (int) (MAX_ADITIVO/4))
            || (getTAlcool1() < (int) (MAX_ALCOOL/2) && getTAlcool1() >= (int) (MAX_ALCOOL/4))
            || (getTAlcool2() < (int) (MAX_ALCOOL/2) && getTAlcool1() >= (int) (MAX_ALCOOL/4))){
            this.situacaoAtual = SITUACAO.SOBRAVISO;
        }
        else if((getTGasolina() < (int) (MAX_GASOLINA/4)) || (getTAditivo() < (int) (MAX_ADITIVO/4)) ||
            (getTAlcool1() < (int) (MAX_ALCOOL/4)) || (getTAlcool2() < (int) (MAX_ALCOOL/4))){
            this.situacaoAtual = SITUACAO.EMERGENCIA;
        }
    }
    
    public SITUACAO getSituacao(){
        return this.situacaoAtual;
    }
    
    public int getTAditivo() {
        return this.tAditivo;
    }
    
    public int getTGasolina() {
        return this.tGasolina;
    }
    
    public int getTAlcool1() {
        return this.tAlcool1;
    }
    
    public int getTAlcool2() {
        return this.tAlcool2;
    }
    
    
    public int recebeAditivo(int qtdade) {
        if(qtdade < 1 || qtdade > (MAX_ADITIVO - this.tAditivo)){
            return -1;
        }
        else{
            this.tAditivo += qtdade;
        }
        defineSituacao();
        return qtdade;
    }
    
    public int recebeGasolina(int qtdade) {
        if(qtdade < 1 || qtdade > (MAX_GASOLINA - this.tGasolina)){
            return -1;
        }
        else{
            this.tGasolina += qtdade;
        }
        defineSituacao();
        return qtdade;
    }
    
    public int recebeAlcool(int qtdade) {
        if(qtdade < 1 || qtdade > (MAX_ALCOOL - (this.tAlcool1))){
            return -1;
        }
        else{
            this.tAlcool1 = (int) (this.tAlcool1 + qtdade/2);
            this.tAlcool2 = (int) (this.tAlcool2 + qtdade/2);
        }
        defineSituacao();
        return qtdade;
    }
    
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        if(qtdade < 1) return new int[] {-7};
        
        int aditivo = (int) (qtdade * 0.05);
        int gasolina = (int) (qtdade * 0.7);
        int alcool = (int) (qtdade * 0.25);

        if(tipoPosto == TIPOPOSTO.COMUM) {
            if(this.situacaoAtual == SITUACAO.SOBRAVISO) {
                aditivo = (int) (aditivo * 0.5);
                gasolina = (int) (gasolina * 0.5);
                alcool = (int) (alcool * 0.5);
            } else if(this.situacaoAtual == SITUACAO.EMERGENCIA) 
                return new int[] {-14};
        } else if(tipoPosto == TIPOPOSTO.ESTRATEGICO) {
            if(this.situacaoAtual == SITUACAO.EMERGENCIA) {
                aditivo = (int) (aditivo * 0.5);
                gasolina = (int) (gasolina * 0.5);
                alcool = (int) (alcool * 0.5);
            }
        }

        if(aditivo > this.getTAditivo() || gasolina > this.getTGasolina() || alcool > this.getTAlcool1() * 2 ) 
            return new int[] {-21};

        this.tAditivo -= aditivo;
        this.tGasolina -= gasolina;
        this.tAlcool1 -= (int) (alcool/2);
        this.tAlcool2 -= (int) (alcool/2);

        int[] a = {this.getTAditivo(), this.getTGasolina(), this.getTAlcool1() , this.getTAlcool2()};
        defineSituacao();
        return a;
    }
}
