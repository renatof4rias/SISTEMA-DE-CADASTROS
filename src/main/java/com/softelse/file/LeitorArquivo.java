package com.softelse.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LeitorArquivo {
    File perguntas = new File("C:\\Users\\Renato\\Desktop\\SISTEMA-DE-CADASTROS\\src\\main\\java\\com\\softelse\\file\\perguntas.txt");
    Scanner resposta = new Scanner(System.in);

    public void LendoPerguntas() throws IOException {
        FileReader fileReader = new FileReader(perguntas);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            System.out.print(linha + " ");
            resposta.nextLine();
        }
    }
}