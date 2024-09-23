package com.ryanlinhares.faturamento.services;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.ryanlinhares.faturamento.model.FaturamentoDia;
import com.ryanlinhares.faturamento.model.Faturamentos;

@Service
public class FaturamentoService {

	public void processarFaturamento() {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Faturamentos.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			File xmlFile = new File("src/main/resources/faturamento.xml");
			Faturamentos faturamentos = (Faturamentos) unmarshaller.unmarshal(xmlFile);

			List<FaturamentoDia> listaFaturamentos = faturamentos.getFaturamentos();

			double menorValor = Double.MAX_VALUE;
			double maiorValor = Double.MIN_VALUE;
			double soma = 0;
			int diasComFaturamento = 0;

			for (FaturamentoDia dia : listaFaturamentos) {
				double valor = dia.getValor();
				if (valor > 0) {
					soma += valor;
					diasComFaturamento++;

					if (valor < menorValor) {
						menorValor = valor;
					}

					if (valor > maiorValor) {
						maiorValor = valor;
					}
				}
			}

			double media = soma / diasComFaturamento;
			System.out.println("Menor valor: " + menorValor);
			System.out.println("Maior valor: " + maiorValor);
			System.out.println("Média mensal: " + media);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
