/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Mateus
 */
public class Aluno {
    private int RA;
    private String nome;
    private int turma;

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public int getTurma() {
        return turma;
    }

    public void setRA(int RA) {
        this.RA = RA;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRA() {
        return RA;
    }

    public String getNome() {
        return nome;
    }
}
