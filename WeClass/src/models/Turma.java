/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Mateus
 */
public class Turma {
    private int id_turma;
    private String nome_turma;
    private String escola;

    public int getId_turma() {
        return id_turma;
    }

    public String getNome_turma() {
        return nome_turma;
    }

    public String getEscola() {
        return escola;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public void setNome_turma(String nome_turma) {
        this.nome_turma = nome_turma;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }
    
}
