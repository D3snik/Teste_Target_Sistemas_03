package com.ryanlinhares.faturamento.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "faturamentos")
public class Faturamentos {
    private List<FaturamentoDia> faturamentos;

    @XmlElement(name = "faturamento")
    public List<FaturamentoDia> getFaturamentos() {
        return faturamentos;
    }

    public void setFaturamentos(List<FaturamentoDia> faturamentos) {
        this.faturamentos = faturamentos;
    }
}
