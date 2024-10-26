package com.softelse.service;

import com.softelse.model.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PessoaService {
    File perguntas = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\src\\main\\java\\com\\softelse\\service\\perguntas.txt");
    Scanner respostaIn = new Scanner(System.in);
    Pessoa pessoa;
    ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    int i = 0;

    public void cadastrarPessoa() throws IOException {
        Pessoa pessoa = new Pessoa();
        FileReader fileReader = new FileReader(perguntas);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numeroDaLinha = 0;
        String linha;

        while ((linha = bufferedReader.readLine()) != null) {
            numeroDaLinha++;
            System.out.print(linha + " ");
            if (numeroDaLinha == 1) {
                pessoa.setNome(respostaIn.nextLine());
            } else if (numeroDaLinha == 2) {
                pessoa.setEmail(respostaIn.nextLine());
            } else if (numeroDaLinha == 3) {
                pessoa.setIdade(respostaIn.nextInt());
            } else if (numeroDaLinha == 4) {
                pessoa.setAltura(respostaIn.nextDouble());
                respostaIn.nextLine();
            }
        }
        listaPessoas.add(pessoa);
        gerandoArquivo(pessoa);
    }

    private void gerandoArquivo(Pessoa pessoa) {
        i++;
        try {
            File resposta = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\DB\\" + String.valueOf(i) + "-" + pessoa.getNome().toUpperCase() + ".txt");
            FileWriter fw = new FileWriter(resposta, true);
            BufferedWriter br = new BufferedWriter(fw);
            br.write("Nome: " + pessoa.getNome() + " \n");
            br.write("E-mail: " + pessoa.getEmail() + " \n");
            br.write("Idade: " + String.valueOf(pessoa.getIdade()) + " \n");
            br.write("Altura: " + String.valueOf(pessoa.getAltura()) + " \n");
            br.flush();
            br.close();
            fw.close();
            resposta.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void listAllPessoas() {
        for (Pessoa Pessoa : listaPessoas) {
            System.out.println(Pessoa.getNome());
            System.out.println(Pessoa.getEmail());
            System.out.println(Pessoa.getIdade());
            System.out.println(Pessoa.getAltura());
        }
    }

    public void buscarPessoaId(){
        System.out.println("buscarPessoaId sem Implementação");
        //trocar nome do metodo
    }

    public void removerPergunta(){
        System.out.println("removerPergunta sem Implementação");
    }
    public void cadastrarPergunta(){
        System.out.println("cadastrarPergunta sem Implementação");
    }

}