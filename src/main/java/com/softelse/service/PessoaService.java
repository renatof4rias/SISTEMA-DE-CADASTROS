package com.softelse.service;

import com.softelse.model.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PessoaService {
    File perguntas = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\src\\main\\java\\com\\softelse\\service\\perguntas.txt");
    File resposta = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\src\\main\\java\\com\\softelse\\service\\respostas.txt");
    Scanner respostaIn = new Scanner(System.in);
    ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    public void cadastrarPessoa() throws IOException {
        FileReader fileReader = new FileReader(perguntas);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Pessoa pessoa = new Pessoa();
        FileWriter fw = new FileWriter(resposta, true);
        BufferedWriter br = new BufferedWriter(fw);


        int numeroDaLinha = 0;
        String linha;

        while ((linha = bufferedReader.readLine()) != null) {
            numeroDaLinha++;
            System.out.print(linha + " ");
            if (numeroDaLinha == 1) {
                pessoa.setNome(respostaIn.nextLine());
                br.write("\nNome: " + pessoa.getNome() + " \n");
                br.flush();
            } else if (numeroDaLinha == 2) {
                pessoa.setEmail(respostaIn.nextLine());
                br.write("E-mail: " + pessoa.getEmail() + " \n");
                br.flush();
            } else if (numeroDaLinha == 3) {
                pessoa.setIdade(respostaIn.nextInt());
                br.write("Idade: " + String.valueOf(pessoa.getIdade()) + " \n");
                br.flush();
            } else if (numeroDaLinha == 4) {
                pessoa.setAltura(respostaIn.nextDouble());
                respostaIn.nextLine();
                br.write("Altura: " + String.valueOf(pessoa.getAltura()) + " \n");
                br.flush();
            }

        }
        listaPessoas.add(pessoa);
        br.write("-------------------------------------------------------------------------------------------");
        br.flush();
    }

    public void listAllPessoas() {
        for (Pessoa Pessoa : listaPessoas) {
            System.out.println(Pessoa.getNome());
            System.out.println(Pessoa.getEmail());
            System.out.println(Pessoa.getIdade());
            System.out.println(Pessoa.getAltura());
        }
    }


}