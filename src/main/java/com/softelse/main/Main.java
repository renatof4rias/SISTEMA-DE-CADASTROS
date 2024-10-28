package com.softelse.main;

import com.softelse.service.PessoaService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PessoaService leitor = new PessoaService();
        Scanner sc = new Scanner(System.in);

        String opcaoMenu = """
                🚨🚨🚨🚨🚨Escolha uma Opção do Menu🚨🚨🚨🚨🚨
                1 - Cadastrar o usuário ➕
                2 - Listar todos usuários cadastrados 📋
                3 - Cadastrar nova pergunta no formulário ➕
                4 - Deletar pergunta do formulário ➖
                5 - Pesquisar usuário por nome ou idade ou email 🔍
                6 - Sair do Sistema 🚪            
                """;

        System.out.print(opcaoMenu);
        int opcaoEscolhida = sc.nextInt();

        while (opcaoEscolhida != 6) {
            if (opcaoEscolhida == 1) {
                try {
                    System.out.println("*** CADASTRAR USUÁRIO ***");
                    leitor.cadastrarPessoa();
                } catch (IOException e) {
                    e.getMessage();
                }
            } else if (opcaoEscolhida == 2) {
                System.out.println("*** LISTA DE USUÁRIOS CADASTRADOS ***");
                leitor.listAllPessoas();
            } else if (opcaoEscolhida == 3) {
                System.out.println("*** CADASTRAR PERGUNTAS ***");
                leitor.cadastrarPergunta();
            } else if (opcaoEscolhida == 4) {
                System.out.println("*** BUSCAR USUÁRIO ***");
                leitor.buscarPessoaId();
            } else if (opcaoEscolhida == 5) {
                System.out.println("*** SAIR DO SISTEMA ***");
                break;
            } else {
                System.out.println("*** ERROR AO ESCOLHER ***");
            }

            opcaoMenu = """
                    🚨🚨🚨🚨🚨Escolha uma Opção do Menu🚨🚨🚨🚨🚨
                    1 - Cadastrar o usuário ➕
                    2 - Listar todos usuários cadastrados 📋
                    3 - Cadastrar nova pergunta no formulário ➕
                    4 - Deletar pergunta do formulário ➖
                    5 - Pesquisar usuário por nome ou idade ou email 🔍
                    6 - Sair do Sistema 🚪        
                    """;

            System.out.print(opcaoMenu);
            opcaoEscolhida = sc.nextInt();

        }
        sc.close();
    }
}