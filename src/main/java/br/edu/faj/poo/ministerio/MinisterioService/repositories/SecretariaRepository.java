package br.edu.faj.poo.ministerio.MinisterioService.repositories;

import br.edu.faj.poo.ministerio.MinisterioService.entities.Secretaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SecretariaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Secretaria create(Secretaria secretaria) throws Exception {
        String sqlInsert = "INSERT INTO Secretaria (NOME, NUM_FUNCIONARIOS, VERBA, MINISTERIO_ID) VALUES (?, ?, ?, ?)";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(
                        sqlInsert,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            ps.setString(1, secretaria.getNome());
            ps.setInt(2, secretaria.getNumFuncionarios());
            ps.setDouble(3, secretaria.getVerba());
            ps.setInt(4, secretaria.getMinisterioId());

            int result = ps.executeUpdate();
            if (result == 1) {
                ResultSet tableKeys = ps.getGeneratedKeys();
                tableKeys.next();

                secretaria.setId(tableKeys.getInt(1));
                System.out.println("Secretaria inserida com sucesso: " + secretaria.getNome());
                return secretaria;
            }
            throw new Exception("Erro ao inserir no banco.");
        }
    }

    public Secretaria getById(int id) {
        String sqlSelect = "SELECT * FROM Secretaria WHERE ID = ?";
        Secretaria secretaria = null;

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelect)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                secretaria = new Secretaria();
                secretaria.setId(rs.getInt("ID"));
                secretaria.setNome(rs.getString("NOME"));
                secretaria.setNumFuncionarios(rs.getInt("NUM_FUNCIONARIOS"));
                secretaria.setVerba(rs.getDouble("VERBA"));
                secretaria.setMinisterioId(rs.getInt("MINISTERIO_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretaria;
    }

    public List<Secretaria> getAll() {
        String sqlSelectAll = "SELECT * FROM Secretaria";
        List<Secretaria> secretarias = new ArrayList<>();

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Secretaria secretaria = new Secretaria();
                secretaria.setId(rs.getInt("ID"));
                secretaria.setNome(rs.getString("NOME"));
                secretaria.setNumFuncionarios(rs.getInt("NUM_FUNCIONARIOS"));
                secretaria.setVerba(rs.getDouble("VERBA"));
                secretaria.setMinisterioId(rs.getInt("MINISTERIO_ID"));
                secretarias.add(secretaria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return secretarias;
    }

    public Secretaria update(Secretaria secretaria) throws Exception {
        String sqlUpdate = "UPDATE Secretaria SET NOME=?, NUM_FUNCIONARIOS=?, VERBA=?, MINISTERIO_ID=? WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlUpdate)
        ) {
            ps.setString(1, secretaria.getNome());
            ps.setInt(2, secretaria.getNumFuncionarios());
            ps.setDouble(3, secretaria.getVerba());
            ps.setInt(4, secretaria.getMinisterioId());
            ps.setInt(5, secretaria.getId());

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Secretaria atualizada com sucesso: " + secretaria.getId());
                return secretaria;
            }
            throw new Exception("Erro ao atualizar no banco.");
        }
    }

    public void delete(int id) throws Exception {
        String sqlDelete = "DELETE FROM Secretaria WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlDelete)
        ) {
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new Exception("Erro ao excluir no banco.");
            }
            System.out.println("Secretaria excluída com sucesso: " + id);
        }
    }
}
