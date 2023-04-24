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
                TarefaAluno tarefaAluno = new TarefaAluno(serial,nome, status, (java.sql.Date) data);
                list.add(tarefaAluno);
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public void entregarTarefa(int id){
        String sql = "UPDATE `weclass`.`alunotarefa` SET `status` = 'Entregue', `DataEntrega` = ? WHERE (`serial` = ? );";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            LocalDate data= LocalDate.now();
            stmt.setDate(1, java.sql.Date.valueOf(data));
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
        }
    }
}
