package com.ryanlinhares.faturamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryanlinhares.faturamento.services.FaturamentoService;

@RestController
@RequestMapping("/faturamento")
public class FaturamentoController {

    @Autowired
    private FaturamentoService faturamentoService;

    @GetMapping("/processar")
    public ResponseEntity<String> processarFaturamento() {
        System.out.println("Requisição para processar faturamento recebida.");
        faturamentoService.processarFaturamento();
        return ResponseEntity.ok("Faturamento processado com sucesso.");
    }

}