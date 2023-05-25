/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.ProgressBar;
import javax.swing.JOptionPane;
import models.Aluno;
import models.GraficoTarefaEntregue;
import models.TarefaAluno;

/**
 *
 * @author Mateus
 */
public class TarefaAlunoDao {
    ConnectionFactory factory;
    Connection conn;
    
    public TarefaAlunoDao(){
    factory = new ConnectionFactory();
    conn = factory.getConnection();
}
    

    public ArrayList<models.TarefaAluno> listarPorTarefa(int id){
        String sql = "SELECT * FROM weclass.alunotarefa where Tarefa_idTarefa = ?";
        ArrayList<models.TarefaAluno> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int serial = rs.getInt("serial");
                String nome = rs.getString("NomeAluno");
                String status = rs.getString("status");
                Date data = rs.getDate("DataEntrega");
                int nota = rs.getInt("nota");
                TarefaAluno tarefaAluno = new TarefaAluno(serial, nome, status, (java.sql.Date) data);
                tarefaAluno.setNota(nota);
                list.add(tarefaAluno);
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public void reverterTarefa(TarefaAluno tarefaAluno){
        String sql = "UPDATE `weclass`.`alunotarefa` SET `status` = 'Não entregue', `DataEntrega` = NULL WHERE (`serial` = ?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tarefaAluno.getSerial());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
        }
    }
    
    public void entregarTarefa(TarefaAluno tarefa){
        String sql = "UPDATE `weclass`.`alunotarefa` SET `status` = 'Entregue', `DataEntrega` = ?, `nota` = ? WHERE (`serial` = ? );";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            LocalDate data= LocalDate.now();
            stmt.setDate(1, tarefa.getEntrega());
            stmt.setInt(2, tarefa.getNota());
            stmt.setInt(3, tarefa.getSerial());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
        }
    }
    
    public TarefaAluno ProgressoAluno(Aluno aluno){
        String nome = aluno.getNome();
        String status = "Entregue";
        Double progresso = 0.0;
        Double entregas = 0.0;
        Double total = 0.0;
        String sql = "SELECT * FROM weclass.alunotarefa WHERE Aluno_RA = ?;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, aluno.getRa());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String comp = rs.getString("status");
                if(comp.equals("Não entregue") || comp.equals("Não Entregue")){
                    status = "Pendente";
                } else {
                    entregas = entregas + 1;
                }
                total = total + 1;
            }
            if(total == 0){
                status = "Não possui tarefas para entregar";
                TarefaAluno tarefa = new TarefaAluno(nome, status, null);
                return tarefa;
            }
            progresso = (entregas / total);
            ProgressBar barraDeProgresso = new ProgressBar();
            barraDeProgresso.setProgress(progresso);
            TarefaAluno tarefa = new TarefaAluno(nome, status, barraDeProgresso);
            return tarefa;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        return null;
    } 
    
    public GraficoTarefaEntregue graficoMain(){
        String sql = "SELECT * FROM weclass.alunotarefa;";
        int entregue = 0;
        int naoEntregue = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String status = rs.getString("status");
                if(status.equals("Entregue") || status.equals("entregue")){
                    entregue = entregue +1;
                }else{naoEntregue = naoEntregue+1;}
            }
            GraficoTarefaEntregue grafico = new GraficoTarefaEntregue(entregue, naoEntregue);
            return grafico;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar gráfico "+e.getMessage());
        }
        return null;
    }
}
