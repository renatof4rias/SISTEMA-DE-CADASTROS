package com.softelse.main;

import com.softelse.file.LeitorArquivo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        LeitorArquivo leitor = new LeitorArquivo();
        try {
            leitor.LendoPerguntas();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}