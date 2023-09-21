package br.edu.faj.poo.ministerio.MinisterioService.repositories;

import br.edu.faj.poo.ministerio.MinisterioService.entities.Ministerio;
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
public class MinisterioRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Ministerio create(Ministerio m) throws Exception {
        String sqlInsert = "INSERT INTO MINISTERIO (NOME, NUM_FUNCIONARIOS, VERBA) " + "VALUES (?, ?, ?)";

        try (
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(
                sqlInsert,
                Statement.RETURN_GENERATED_KEYS
            )
        ) {
            ps.setString(1, m.getNome());
            ps.setInt(2, m.getNumFuncionarios());
            ps.setDouble(3, m.getVerba());

            int result = ps.executeUpdate();
            if (result == 1) {
                ResultSet tableKeys = ps.getGeneratedKeys();
                tableKeys.next();

                m.setId(tableKeys.getInt(1));
                System.out.println("Ministerio inserido com sucesso:" + m.getNome());
                return m;
            }
            throw new Exception("Erro ao inserir no banco.");
        }
    }

    public Ministerio getById(int id) {
        String sqlSelect = "SELECT * FROM MINISTERIO WHERE ID = ?";
        Ministerio ministerio = null;

        try (
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sqlSelect)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ministerio = new Ministerio();
                ministerio.setId(rs.getInt("ID"));
                ministerio.setNome(rs.getString("NOME"));
                ministerio.setNumFuncionarios(rs.getInt("NUM_FUNCIONARIOS"));
                ministerio.setVerba(rs.getDouble("VERBA"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ministerio;
    }

    public List<Ministerio> getAll() {
        String sqlSelectAll = "SELECT * FROM MINISTERIO";
        List<Ministerio> ministerios = new ArrayList<>();

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ministerio ministerio = new Ministerio();
                ministerio.setId(rs.getInt("ID"));
                ministerio.setNome(rs.getString("NOME"));
                ministerio.setNumFuncionarios(rs.getInt("NUM_FUNCIONARIOS"));
                ministerio.setVerba(rs.getDouble("VERBA"));
                ministerios.add(ministerio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ministerios;
    }

    public Ministerio update(Ministerio m) throws Exception {
        String sqlUpdate = "UPDATE MINISTERIO SET NOME=?, NUM_FUNCIONARIOS=?, VERBA=? WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlUpdate)
        ) {
            ps.setString(1, m.getNome());
            ps.setInt(2, m.getNumFuncionarios());
            ps.setDouble(3, m.getVerba());
            ps.setInt(4, m.getId());

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Ministerio atualizado com sucesso: " + m.getId());
                return m;
            }
            throw new Exception("Erro ao atualizar no banco.");
        }
    }

    public void delete(int id) throws Exception {
        String sqlDelete = "DELETE FROM MINISTERIO WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlDelete)
        ) {
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new Exception("Erro ao excluir no banco.");
            }
            System.out.println("Ministerio excluído com sucesso: " + id);
        }
    }
}
