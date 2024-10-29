package com.softelse.service;

import com.softelse.model.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PessoaService {
    File perguntas = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\src\\main\\java\\com\\softelse\\repository\\perguntas.txt");
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
        for (Pessoa Pessoa : listaPessoas) {
            j++;
            System.out.println(j + " - " + Pessoa.getNome().toUpperCase());
        }
    }

    public void buscarPessoa(){
        j= 0;
        System.out.print("Digite o Nome: ");
        String nomeUsuario = respostaIn.nextLine();

            for (Pessoa usuario : listaPessoas) {
                if (usuario.getNome().toLowerCase().contains(nomeUsuario.toLowerCase())) {
                    System.out.println(j + " - " +usuario.getNome());
                }
            }
        System.out.println("Conseguio Encontrar o Usuário S/N ?");
        String inPerguntas = respostaIn.nextLine();

            while(true){
                if(inPerguntas.equalsIgnoreCase("n")){
                    System.out.print("Digite o Nome: ");
                    nomeUsuario = respostaIn.nextLine();

                    for (Pessoa usuario : listaPessoas) {
                        if (usuario.getNome().toLowerCase().contains(nomeUsuario.toLowerCase())) {
                            System.out.println(usuario.getNome());
                        }
                    }
                } else if (inPerguntas.equalsIgnoreCase("s")) {
                    System.out.println("Qual o Número do Usuário?");
                    int numeroUsuario = respostaIn.nextInt();

                    try {
                        File file = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\DB\\" + String.valueOf((numeroUsuario + 1)) + "-" + pessoa.getNome().toUpperCase() + ".txt");
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        System.out.println("\n");
                        System.out.println("1 - Nome - " +listaPessoas.get(numeroUsuario).getNome());
                        System.out.println("2 - Email - " +listaPessoas.get(numeroUsuario).getEmail());
                        System.out.println("3 - Idade - " +listaPessoas.get(numeroUsuario).getIdade());
                        System.out.println("4 - Altura - " +listaPessoas.get(numeroUsuario).getAltura());

                        int numeroDaLinha = 0;
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            numeroDaLinha++;
                            if (numeroDaLinha > 4) {
                                System.out.println(linha + " ");
                            }
                        }
                        break;
                    }catch (IOException e){
                        e.getMessage();
                    }
                }else {
                    System.out.println("*** ERROR AO ESCOLHER ***");
                    System.out.println("Conseguio Encontrar o Usuário S/N ?");
                    inPerguntas = respostaIn.nextLine();
                }
            }
    }


    ArrayList<String> perguntaExtra;
    public ArrayList<String> cadastrarPergunta() {
        perguntaExtra = new ArrayList<>();
        int k = 4;
        System.out.print("\nDeseja Adicionar Perguntas Extras S/N ? ");
        String inPerguntas = respostaIn.nextLine();
        boolean inPerguntasN = false;
        while (true) {
            if (inPerguntas.equalsIgnoreCase("n")) {
                break;
            } else if (inPerguntas.equalsIgnoreCase("s")) {
                inPerguntasN = true;
                k++;
                System.out.println("Digite sua Pergunta!");
                String perguntaExtraIn = respostaIn.nextLine();
                perguntaExtra.add(k + " - " + perguntaExtraIn);

                System.out.print("Deseja Adicionar Outra ? ");
                inPerguntas = respostaIn.nextLine();
                if (inPerguntas.equalsIgnoreCase("n")) {
                    break;
                }
            } else {
                System.out.println("*** ERROR AO ESCOLHER ***");
                System.out.print("\nDeseja Adicionar Perguntas Extras S/N ? ");
                inPerguntas = respostaIn.nextLine();
            }
        }
        if (inPerguntas.equalsIgnoreCase("s")) {
        System.out.println("\nLista de Perguntas Registradas");
            for (String perguntaExtras : perguntaExtra) {
                System.out.println(perguntaExtras);
            }
        }
        if (inPerguntasN) {
            System.out.print("Deseja Remover Alguma Pergunta S/N ? ");
            inPerguntas = respostaIn.nextLine();
            while (true) {
                if (inPerguntas.equalsIgnoreCase("n")) {
                    break;
                } else if (inPerguntas.equalsIgnoreCase("s")) {
                    System.out.print("Digite o Número da Perguta ?");
                    int perguntaExtraIn = respostaIn.nextInt();
                    respostaIn.nextLine();

                    System.out.println(perguntaExtra.get((perguntaExtraIn - 5)));

                    System.out.print("Deseja Remover Esta Pergunta S/N ?");
                    inPerguntas = respostaIn.nextLine();
                    if (inPerguntas.equalsIgnoreCase("n")) {
                        break;
                    } else if (inPerguntas.equalsIgnoreCase("s")) {
                        perguntaExtra.remove(perguntaExtraIn - 5);
                    } else {
                        System.out.println("*** ERROR AO ESCOLHER ***");
                        System.out.print("Deseja Remover Esta Pergunta S/N ?");
                        inPerguntas = respostaIn.nextLine();
                    }
                    System.out.println("Lista Atualizada de Perguntas Registradas");
                    for (String perguntaExtras : perguntaExtra) {
                        System.out.println(perguntaExtras);
                    }
                    System.out.print("Deseja Remover Outra Pergunta S/N ? ");
                    inPerguntas = respostaIn.nextLine();
                } else {
                    System.out.println("*** ERROR AO ESCOLHER ***");
                    System.out.print("Deseja Remover Alguma Pergunta S/N ? ");
                    inPerguntas = respostaIn.nextLine();
                }
            }
        }
        return perguntaExtra;
    }
}