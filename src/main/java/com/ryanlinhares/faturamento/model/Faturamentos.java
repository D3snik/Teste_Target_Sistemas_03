package com.ryanlinhares.faturamento.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "faturamentos")
public class Faturamentos {

    private List<Faturamento> faturamentoList;

    @XmlElement(name = "faturamento")
    public List<Faturamento> getFaturamentoList() {
        return faturamentoList;
    }

    public void setFaturamentoList(List<Faturamento> faturamentoList) {
        this.faturamentoList = faturamentoList;
    }

    public static class Faturamento {
        private String dia;
        private double valor;

        @XmlAttribute(name = "dia")
        public String getDia() {
            return dia;
        }

        public void setDia(String dia) {
            this.dia = dia;
        }

        @XmlElement
        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }
    }
}