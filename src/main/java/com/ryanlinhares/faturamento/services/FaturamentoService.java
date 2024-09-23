package com.ryanlinhares.faturamento.services;

import java.io.File;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.ryanlinhares.faturamento.model.Faturamentos.Faturamento;

@Service
public class FaturamentoService {

    public Faturamento carregarFaturamento(String caminhoArquivo) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Faturamento.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Faturamento) unmarshaller.unmarshal(new File(caminhoArquivo));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void calcularFaturamento(Faturamento faturamento) {
        double somaFaturamento = 0;
        int diasComFaturamento = 0;
        int menorFaturamento = Integer.MAX_VALUE;
        int maiorFaturamento = Integer.MIN_VALUE;

        for (FaturamentoDia dia : faturamento.getFaturamentos()) {
            int valor = dia.getValor();
            if (valor > 0) {
                somaFaturamento += valor;
                diasComFaturamento++;

                if (valor < menorFaturamento) {
                    menorFaturamento = valor;
                }
                if (valor > maiorFaturamento) {
                    maiorFaturamento = valor;
                }
            }
        }

        double mediaMensal = (diasComFaturamento > 0) ? (somaFaturamento / diasComFaturamento) : 0;
        int diasAcimaMedia = (int) Arrays.stream(faturamento.getFaturamentos())
                .filter(dia -> dia.getValor() > mediaMensal).count();

        System.out.println("Menor valor de faturamento: " + (menorFaturamento == Integer.MAX_VALUE ? "Nenhum" : menorFaturamento));
        System.out.println("Maior valor de faturamento: " + (maiorFaturamento == Integer.MIN_VALUE ? "Nenhum" : maiorFaturamento));
        System.out.println("Número de dias com faturamento acima da média: " + diasAcimaMedia);
    }
}
