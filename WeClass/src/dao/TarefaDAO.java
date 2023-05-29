/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import models.Aluno;
import models.Tarefa;
import models.Turma;

public class TarefaDAO {

    private ConnectionFactory factory;
    private Connection conn;

    
    
    public TarefaDAO() {
        factory = new ConnectionFactory();
        conn = factory.getConnection();
    }

    public void inserirTarefa(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO `weclass`.`tarefa` (`nomeTarefa`, `desc`, `nota`, `data_inicio`, `data_fim`, `Turma_idTurma`) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tarefa.getNomeTarefa());
            statement.setString(2, tarefa.getDescricao());
            statement.setFloat(3, tarefa.getNota());
            statement.setDate(4, (Date) tarefa.getDataInicio());
            statement.setDate(5, (Date) tarefa.getDataFim());
            statement.setInt(6, tarefa.getIdTurma());
            statement.execute();
            statement.close();           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao adicionar tarefa "+ e.getMessage());
        }
        

    }

   public void atualizarTarefa(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE `weclass`.`tarefa` SET `nomeTarefa` = ?, `desc` = ?, `nota` = ?, `data_fim` = ? WHERE (`idTarefa` = ?) and (`Turma_idTurma` = ?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tarefa.getNomeTarefa());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getNota());
            stmt.setDate(4, (Date) tarefa.getDataFim());
            stmt.setInt(5, tarefa.getId());
            stmt.setInt(6, tarefa.getIdTurma());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao Atualizar Tarefa "+ e.getMessage());
        }
        
    }

    public void excluirTarefa(Tarefa tarefa) throws SQLException {
        String sql = "DELETE FROM `weclass`.`alunotarefa` WHERE `Tarefa_idTarefa` = ?;";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tarefa.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao deletar tarefa "+ e.getMessage());
        }
        
        sql = "DELETE FROM tarefa WHERE idTarefa = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, tarefa.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao deletar tarefa"+ e);
        }
        
    }

    public Tarefa buscarTarefa(int id) throws SQLException {
        Tarefa tarefa = null;
        String sql = "SELECT * FROM tarefa WHERE idTarefa = ?";


        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nomeTarefa = resultSet.getString("nomeTarefa");
            String descricao = resultSet.getString("desc");
            int nota = resultSet.getInt("nota");
            Date dataInicio = resultSet.getDate("data_inicio");
            Date dataFim = resultSet.getDate("data_fim");
            int idTurma = resultSet.getInt  ("Turma_idTurma");

        tarefa = new Tarefa(id, nomeTarefa, descricao, nota, dataInicio, dataFim, idTurma);
    }

    resultSet.close();
    statement.close();

    return tarefa;
}

public int ultimaTarefa(){
    String sql = "SELECT idTarefa FROM weclass.tarefa order by idTarefa desc limit 1;";
    try {
        int id = 0;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
        id = rs.getInt("idTarefa");
        }
        return id;
    } catch (SQLException e) {
    }
        return 0;
}     
    
public ArrayList<Tarefa> listarTarefas() throws SQLException {
    ArrayList<Tarefa> listaTarefas = new ArrayList<Tarefa>();
    String sql = "SELECT * FROM tarefa";
    
    try {
            Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {
        int id = resultSet.getInt("idTarefa");
        String nomeTarefa = resultSet.getString("nomeTarefa");
        String descricao = resultSet.getString("desc");
        int nota = resultSet.getInt("nota");
        Date dataInicio = resultSet.getDate("data_inicio");
        Date dataFim = resultSet.getDate("data_fim");
        int idTurma = resultSet.getInt("Turma_idTurma");
        
        //Creating a button and set task name and style 
        Button btn = new Button();
        btn.setText(nomeTarefa);
        btn.setStyle("-fx-background-color:#1590c4; -fx-text-fill: #fff; ");
        btn.setMinWidth(190);
        btn.setCursor(Cursor.HAND);
        
        Tarefa tarefa = new Tarefa(id, nomeTarefa, descricao, nota, dataInicio, dataFim, idTurma);
        tarefa.setBtn(btn);
        listaTarefas.add(tarefa);}
        resultSet.close();
        statement.close();
        return listaTarefas;
    } catch (SQLException e) {
    

    }
        return null;
}

public void tarefaAluno(Aluno aluno, int id, Tarefa tarefa){
    String sql = "INSERT INTO `weclass`.`alunotarefa` (`status`, `nota`, `Aluno_RA`, `Aluno_Turma_idTurma`, `Tarefa_idTarefa`, `DataEntrega`, `NomeAluno`) VALUES ('Não Entregue', ?, ?, ?, ?, ?, ?);";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, 0);
        stmt.setInt(2, aluno.getRa());
        stmt.setInt(3, tarefa.getIdTurma());
        stmt.setInt(4, id);
        stmt.setDate(5, null);
        stmt.setString(6, aluno.getNome());
        stmt.execute();
        stmt.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Erro ao atribuir tarefa aos alunos "+ e.getMessage());
    }
        
}

public ArrayList<Tarefa> tarefaPorTurma(int id){
    String sql = "SELECT * FROM weclass.tarefa where Turma_idTurma = ?";
    ArrayList<Tarefa> list = new ArrayList<>();
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            int idTarefa = rs.getInt("idTarefa");
            String nomeTarefa = rs.getString("nomeTarefa");
            String descricao = rs.getString("desc");
            int nota = rs.getInt("nota");
            Date dataInicio = rs.getDate("data_inicio");
            Date dataFim = rs.getDate("data_fim");
            int idTurma = rs.getInt("Turma_idTurma");
            Tarefa tarefa = new Tarefa(idTarefa, nomeTarefa, descricao, nota, dataInicio, dataFim, idTurma);
            list.add(tarefa);
        }
        return list;
    } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao listar tarefas pela turma "+ e.getMessage());
    }
        return null;
}

public void deletarTarefasDaTurma(Turma turma){
    String sql = "DELETE FROM weclass.tarefa where (Turma_idTurma = ?)";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, turma.getIdTurma());
        stmt.execute();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Erro ao deletar todas as tarefas da turma "+ e.getMessage());
    }
}
}