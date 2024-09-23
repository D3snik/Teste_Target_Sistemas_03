package com.ryanlinhares.faturamento.controller;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryanlinhares.faturamento.model.Faturamentos;

@RestController
public class FaturamentoController {

    @GetMapping("/faturamento")
    public String calcularFaturamento() throws JAXBException {
        File file = new File("src/main/resources/faturamento.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Faturamentos.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Faturamentos faturamentos = (Faturamentos) unmarshaller.unmarshal(file);

        List<Faturamentos.Faturamento> listaFaturamento = faturamentos.getFaturamentoList();

        List<Double> valoresValidos = listaFaturamento.stream()
                .map(Faturamentos.Faturamento::getValor)
                .filter(valor -> valor > 0)
                .collect(Collectors.toList());

        double menor = valoresValidos.stream().min(Double::compare).orElse(0.0);
        double maior = valoresValidos.stream().max(Double::compare).orElse(0.0);
        double media = valoresValidos.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        long diasAcimaDaMedia = valoresValidos.stream().filter(valor -> valor > media).count();

        return String.format(
            "Menor Faturamento: R$ %.2f\nMaior Faturamento: R$ %.2f\nDias Acima da Média: %d",
            menor, maior, diasAcimaDaMedia
        );
    }
}
