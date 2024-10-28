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

    public void cadastrarPessoa() throws IOException {
        pessoa = new Pessoa();
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

    int i = 0;
    ArrayList<String> perguntasExtras;

    private void gerandoArquivo(Pessoa pessoa) {
        i++;
        perguntasExtras = cadastrarPergunta();
        try {
            File resposta = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\DB\\" + String.valueOf(i) + "-" + pessoa.getNome().toUpperCase() + ".txt");
            FileWriter fw = new FileWriter(resposta, true);
            BufferedWriter br = new BufferedWriter(fw);
            br.write("1 - Nome: " + pessoa.getNome() + " \n");
            br.write("2 - E-mail: " + pessoa.getEmail() + " \n");
            br.write("3 - Idade: " + String.valueOf(pessoa.getIdade()) + " \n");
            br.write("4 - Altura: " + String.valueOf(pessoa.getAltura()) + " \n");
            br.write("Perguntas Extras \n");

            for (String perguntasExtra : perguntasExtras) {
                br.write(perguntasExtra + "\n");
            }

            br.flush();
            br.close();
            fw.close();
            resposta.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    int j = 0;
    public void listAllPessoas() {
        j++;
        for (Pessoa Pessoa : listaPessoas) {
            System.out.println(j + " - " + Pessoa.getNome().toUpperCase());
        }
    }

    public void buscarPessoaId() {
        System.out.println("buscarPessoaId sem Implementação");
        //trocar nome do metodo
    }

    public void removerPergunta() {
        System.out.println("removerPergunta sem Implementação");
    }

    ArrayList<String> perguntaExtra;
    int k = 4;
    public ArrayList<String> cadastrarPergunta() {
        perguntaExtra = new ArrayList<>();
        System.out.print("Deseja Adicionar Perguntas Extras S/N ? ");
        String inPerguntas = respostaIn.nextLine();
        while (true) {
            if (inPerguntas.equalsIgnoreCase("n")) {
                break;
            } else if (inPerguntas.equalsIgnoreCase("s")) {
                k++;
                System.out.println("Digite sua Pergunta!");
                String perguntaExtraIn = respostaIn.nextLine();
                perguntaExtra.add(k + " - " + perguntaExtraIn);

                System.out.print("Deseja Adicionar Outra ? ");
                inPerguntas = respostaIn.nextLine();
                    if (inPerguntas.equalsIgnoreCase("n")) {
                        break;
                    }
            }
        }
        return perguntaExtra;
    }
}