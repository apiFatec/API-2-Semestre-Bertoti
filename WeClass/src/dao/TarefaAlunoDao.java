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
import javax.swing.JOptionPane;
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
    
    public TarefaAluno ProgressoAluno(int ra){
        String nome = "";
        String status = "Entregue";
        Double progresso = 0.0;
        Double entregas = 0.0;
        Double total = 0.0;
        String sql = "SELECT * FROM weclass.alunotarefa WHERE Aluno_RA = ?;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ra);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                nome = rs.getString("NomeAluno");
                String comp = rs.getString("status");
                if(comp.equals("Não entregue")){
                    status = "Pendente";
                } else {
                    entregas = entregas + 1;
                }
                total = total + 1;
            }
            progresso = (entregas / total) * 100;
            TarefaAluno tarefa = new TarefaAluno(nome, status, progresso);
            return tarefa;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        }
        return null;
    } 
}
