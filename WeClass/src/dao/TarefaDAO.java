/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import factory.ConecctionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TarefaDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public TarefaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean inserirTarefa(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO tarefa (nomeTarefa, desc, nota, data_inicio, data_fim, Turma_idTurma) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, tarefa.getNomeTarefa());
        statement.setString(2, tarefa.getDescricao());
        statement.setFloat(3, tarefa.getNota());
        statement.setDate(4, tarefa.getDataInicio());
        statement.setDate(5, tarefa.getDataFim());
        statement.setInt(6, tarefa.getIdTurma());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean atualizarTarefa(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefa SET nomeTarefa = ?, desc = ?, nota = ?, "
                     + "data_inicio = ?, data_fim = ?, Turma_idTurma = ? WHERE idTarefa = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, tarefa.getNomeTarefa());
        statement.setString(2, tarefa.getDescricao());
        statement.setFloat(3, tarefa.getNota());
        statement.setDate(4, tarefa.getDataInicio());
        statement.setDate(5, tarefa.getDataFim());
        statement.setInt(6, tarefa.getIdTurma());
        statement.setInt(7, tarefa.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public boolean excluirTarefa(Tarefa tarefa) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE idTarefa = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, tarefa.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Tarefa buscarTarefa(int id) throws SQLException {
        Tarefa tarefa = null;
        String sql = "SELECT * FROM tarefa WHERE idTarefa = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nomeTarefa = resultSet.getString("nomeTarefa");
            String descricao = resultSet.getString("desc");
            float nota = resultSet.getFloat("nota");
            Date dataInicio = resultSet.getDate("data_inicio");
            Date dataFim = resultSet.getDate("data_fim");
            int idTurma = resultSet.getInt  ("Turma_idTurma");

        tarefa = new Tarefa(id, nomeTarefa, descricao, nota, dataInicio, dataFim, idTurma);
    }

    resultSet.close();
    statement.close();

    return tarefa;
}

public List<Tarefa> listarTarefas() throws SQLException {
    List<Tarefa> listaTarefas = new ArrayList<>();

    String sql = "SELECT * FROM tarefa";

    connect();

    Statement statement = jdbcConnection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);

    while (resultSet.next()) {
        int id = resultSet.getInt("idTarefa");
        String nomeTarefa = resultSet.getString("nomeTarefa");
        String descricao = resultSet.getString("desc");
        float nota = resultSet.getFloat("nota");
        Date dataInicio = resultSet.getDate("data_inicio");
        Date dataFim = resultSet.getDate("data_fim");
        int idTurma = resultSet.getInt("Turma_idTurma");

        Tarefa tarefa = new Tarefa(id, nomeTarefa, descricao, nota, dataInicio, dataFim, idTurma);
        listaTarefas.add(tarefa);
    }

    resultSet.close();
    statement.close();

    disconnect();

    return listaTarefas;
}
