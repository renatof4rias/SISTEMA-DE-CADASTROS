package com.softelse.model;

import java.util.ArrayList;

public class PerguntaExtra {
    private ArrayList<String> perguntaExtras;

    public PerguntaExtra() {
    }

    public PerguntaExtra(ArrayList<String> perguntaExtras) {
        this.perguntaExtras = perguntaExtras;
    }

    public ArrayList<String> getPerguntaExtras() {
        return perguntaExtras;
    }

    public void setPerguntaExtras(String pergunta) {
        perguntaExtras.add(pergunta);
    }

    public void listarPerguntas() {
        for (String pergunta : perguntaExtras) {
            System.out.println(pergunta);
        }
    }
}