package com.centrodistribuicao;

public class App {
  public static final int MAX_ADITIVO = 500;
  public static final int MAX_ALCOOL = 1250;
  public static final int MAX_GASOLINA = 10000;
  public static void main(String[] args) {
    int aditivoInicial = (int) (MAX_ADITIVO/2 - 1);
    int gasolinaInicial = (int) (MAX_GASOLINA/2 - 1);
    int alcoolInicial = (int) (MAX_ALCOOL/2 - 1);

    int qtdAditivo = 10;
    int qtdGasolina = 10;
    int qtdAlcool = 10;

    CentroDistribuicao c = new CentroDistribuicao(aditivoInicial, gasolinaInicial, alcoolInicial, alcoolInicial);
    
    System.out.println(c.getSituacao());
    System.out.println(c.getTGasolina());
    System.out.println(c.getTAditivo());
    System.out.println(c.getTAlcool1());
    System.out.println(c.getTAlcool2());
    c.recebeAditivo(qtdAditivo);
    c.recebeGasolina(qtdGasolina);
    c.recebeAlcool(qtdAlcool);

    System.out.println(c.getSituacao());
    System.out.println(c.getTGasolina());
    System.out.println(c.getTAditivo());
    System.out.println(c.getTAlcool1());
    System.out.println(c.getTAlcool2());
  }
}
