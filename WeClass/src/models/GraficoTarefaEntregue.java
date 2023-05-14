/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Mateus
 */
public class GraficoTarefaEntregue {
    private int NumEntregas;
    private int NumNaoEntregue;

    public GraficoTarefaEntregue(int NumEntregas, int NumNaoEntregue) {
        this.NumEntregas = NumEntregas;
        this.NumNaoEntregue = NumNaoEntregue;
    }

    public GraficoTarefaEntregue() {
    }

    public int getNumEntregas() {
        return NumEntregas;
    }

    public void setNumEntregas(int NumEntregas) {
        this.NumEntregas = NumEntregas;
    }

    public int getNumNaoEntregue() {
        return NumNaoEntregue;
    }

    public void setNumNaoEntregue(int NumNaoEntregue) {
        this.NumNaoEntregue = NumNaoEntregue;
    }

}