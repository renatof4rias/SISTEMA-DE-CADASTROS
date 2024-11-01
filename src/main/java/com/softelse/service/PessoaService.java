package com.softelse.service;

import com.softelse.exeption.EmailException;
import com.softelse.exeption.IdadeException;
import com.softelse.exeption.NomeException;
import com.softelse.model.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class PessoaService {
    File perguntas = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\src\\main\\java\\com\\softelse\\repository\\perguntas.txt");

    Scanner respostaIn = new Scanner(System.in).useLocale(Locale.of("pt", "BR"));

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
                String nome = respostaIn.nextLine();
                while (true) {
                    try {
                        validarNome(nome);
                        pessoa.setNome(nome);
                        break;
                    } catch (NomeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.print(linha + " ");
                    nome = respostaIn.nextLine();
                }
            } else if (numeroDaLinha == 2) {
                String email = respostaIn.nextLine();
                while (true) {
                    try {
                        validarEmail(email);
                        pessoa.setEmail(email);
                        break;
                    } catch (EmailException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.print(linha + " ");
                    email = respostaIn.nextLine();
                }
            } else if (numeroDaLinha == 3) {
                int idade = respostaIn.nextInt();
                while (true) {
                    try {
                        validarIdade(idade);
                        pessoa.setIdade(idade);
                        break;
                    } catch (IdadeException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.print(linha + " ");
                    idade = respostaIn.nextInt();
                }
            } else if (numeroDaLinha == 4) {
                pessoa.setAltura(respostaIn.nextDouble());
                respostaIn.nextLine();
            }
        }
        listaPessoas.add(pessoa);
        gerandoArquivo(pessoa);
    }

    private void validarNome(String nome) {
        if (nome.length() < 10) {
            throw new NomeException("O nome deve ter no mínimo 10 caracteres.");
        }
    }

    private void validarEmail(String email) {
        if (!email.contains("@")) {
            throw new EmailException("O Email Invalido! Espera-se @. ");
        }

        for (Pessoa pessoa : listaPessoas) {
            if (email.equalsIgnoreCase(pessoa.getEmail())) {
                throw new EmailException("O Email Já foi Registrado, Cadastre-se Utilizando um Novo. ");
            }
        }
    }

    private void validarIdade(int idade) {
        if (idade < 18) {
            throw new IdadeException("O Usuário deve ser Maior de 18 anos!");
        }
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

    public void listAllPessoas() {
        for (Pessoa Pessoa : listaPessoas) {
            System.out.println(listaPessoas.indexOf(Pessoa) + " - " + Pessoa.getNome().toUpperCase());
        }
    }

    public void buscarPessoa() {
        System.out.print("Digite o Nome: ");
        String nomeUsuario = respostaIn.nextLine();

        for (Pessoa usuario : listaPessoas) {
            if (usuario.getNome().toLowerCase().contains(nomeUsuario.toLowerCase())) {
                System.out.println(listaPessoas.indexOf(usuario) + " - " + usuario.getNome());
            }
        }
        System.out.print("Conseguio Encontrar o Usuário S/N ?");
        String inPerguntas = respostaIn.nextLine();

        while (true) {
            if (inPerguntas.equalsIgnoreCase("n")) {

                System.out.print("Digite o Nome: ");
                nomeUsuario = respostaIn.nextLine();

                for (Pessoa usuario : listaPessoas) {
                    if (usuario.getNome().toLowerCase().contains(nomeUsuario.toLowerCase())) {
                        System.out.println(listaPessoas.indexOf(usuario) + " - " + usuario.getNome());
                    }
                }

                System.out.println("Conseguio Encontrar o Usuário S/N ?");
                inPerguntas = respostaIn.nextLine();

            } else if (inPerguntas.equalsIgnoreCase("s")) {
                System.out.println("Qual o Número do Usuário?");
                int numeroUsuario = respostaIn.nextInt();

                var usuarioEscolhido = listaPessoas.get(numeroUsuario);
                try {
                    File file = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\DB\\"
                            + (numeroUsuario + 1) + "-"
                            + usuarioEscolhido.getNome().toUpperCase()
                            + ".txt");

                    if (file.exists()) {
                        try (FileReader fr = new FileReader(file);
                             BufferedReader br = new BufferedReader(fr)) {

                            System.out.println("1 - Nome - " + usuarioEscolhido.getNome());
                            System.out.println("2 - Email - " + usuarioEscolhido.getEmail());
                            System.out.println("3 - Idade - " + usuarioEscolhido.getIdade());
                            System.out.println("4 - Altura - " + usuarioEscolhido.getAltura());

                            int numeroDaLinha = 0;
                            String linha;
                            while ((linha = br.readLine()) != null) {
                                numeroDaLinha++;
                                if (numeroDaLinha > 4) {
                                    System.out.println(linha + " ");
                                }
                            }
                        }
                    } else {
                        System.out.println("Arquivo do usuário não encontrado.");
                    }
                    break;
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                }
            } else {
                System.out.println("Número de usuário inválido. Tente novamente.");
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