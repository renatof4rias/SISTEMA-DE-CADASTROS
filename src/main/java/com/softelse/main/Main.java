package com.softelse.main;

import com.softelse.service.PessoaService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PessoaService leitor = new PessoaService();

        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();

        while(!(g==0)) {

            try {
                leitor.cadastrarPessoa();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        leitor.listAllPessoas();
    }
}