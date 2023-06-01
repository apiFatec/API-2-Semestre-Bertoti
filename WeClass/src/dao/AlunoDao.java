/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controllers.FormAlunoController;
import java.sql.Connection;
import factory.ConnectionFactory;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import models.Aluno;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.ResultSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import models.Turma;

/**
 *
 * @author Mateus
 */
public class AlunoDao {
    Connection conn;
    ConnectionFactory conexao;
    
    public AlunoDao(){
        conexao = new ConnectionFactory();
        conn = conexao.getConnection();
    }
 
    public void adicionarAluno(Aluno aluno){
        String sql = "INSERT INTO weclass.aluno(RA, nome, Turma_idTurma) VALUES (?, ?, ?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, aluno.getRa());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getTurma());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar aluno " + e.getMessage());
        }
    }
    
    public void atualizarAluno(Aluno aluno, Turma turma){
        String sql = "UPDATE weclass.aluno SET nome = ? WHERE RA = ? and WHERE Turma_idTurma = ?";
        try{
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setInt(2, aluno.getRa());
           stmt.setInt(3, turma.getIdTurma());
           stmt.setString(1, aluno.getNome());
           stmt.execute();
           stmt.close();
        } catch(SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno " + e.getMessage());
        }
    }
    
    public void deletarAluno(Aluno aluno){
        String sql = "DELETE FROM `weclass`.`aluno` WHERE (`RA` = ? ) and (`Turma_idTurma` = ? );";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, aluno.getRa());
            stmt.setInt(2, aluno.getTurma());
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao deletar aluno" + e.getMessage());
        }
    }
    
    public ArrayList<Aluno> listarAluno(){
        String sql = "SELECT * FROM weclass.aluno";
        ArrayList<Aluno> list = new ArrayList<Aluno>();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            int ra = rs.getInt("RA");
            String nome = rs.getString("nome");
            int turma = rs.getInt("Turma_idTurma");
            
            Aluno aluno = new Aluno(ra, nome, turma);
            list.add(aluno);
            }
            return list;
        } catch(SQLException e){
            return null; 
        }

    }
    
    public ArrayList<Aluno> listSala(int id){
        String sql = "SELECT * FROM weclass.aluno where Turma_idTurma = ?";
        ArrayList<Aluno> list = new ArrayList();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                int ra = rs.getInt("RA");
                int turma = rs.getInt("Turma_idTurma");
                Aluno aluno = new Aluno(ra,nome,turma);
                
                Button btnEdit = new Button();
                Button btnDel = new Button();
                btnEdit.setText("Editar");
                btnEdit.setStyle("-fx-background-color: #1590c4; -fx-text-fill: #fff");
                btnEdit.setCursor(Cursor.HAND);
                
                btnDel.setText("Deletar");
                btnDel.setStyle("-fx-text-fill: #fff; -fx-background-colort: #FF2E24");
                btnDel.setCursor(Cursor.HAND);
                
                aluno.setExcluir(btnDel);
                aluno.setEditar(btnEdit);
                
                list.add(aluno);
            }
            return list;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao retornar lista de alunos da sala "+ e.getMessage());
        }
        return null;
    }
    
    public Aluno retornarAluno(Aluno aluno){
        String sql = "SELECT * FROM weclass.aluno where Turma_idTurma = ? and WHERE RA = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, aluno.getTurma());
            stmt.setInt(2, aluno.getRa());
            ResultSet rs = stmt.executeQuery();
            int ra = rs.getInt("RA");
            int idTurma = rs.getInt("Turma_idTurma");
            String nome = rs.getString("nome");
            Aluno alunoRetorno = new Aluno(ra, nome, idTurma);
            return alunoRetorno;
        } catch (SQLException e) {
        }
        return null;
    }

    public void atualizarAlunoTarefa(Aluno aluno){
        String sql = "UPDATE weclass.alunotarefa SET  NomeAluno = ? WHERE  (Aluno_RA = ?) and (Aluno_Turma_idTurma = ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getRa());
            stmt.setInt(3, aluno.getTurma());

            stmt.execute();
            stmt.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "erro ao atualizar aluno" + e.getMessage());
        }
    }
}
