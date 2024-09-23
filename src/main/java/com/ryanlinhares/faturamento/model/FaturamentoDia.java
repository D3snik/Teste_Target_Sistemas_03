package com.ryanlinhares.faturamento.model;

public class FaturamentoDia {
    private String dia;
    private double valor;

    public FaturamentoDia() {}

    public FaturamentoDia(String dia, double valor) {
        this.dia = dia;
        this.valor = valor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

