package br.edu.faj.poo.ministerio.MinisterioService.repositories;

import br.edu.faj.poo.ministerio.MinisterioService.entities.Presidente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PresidenteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Presidente create(Presidente ministro) throws Exception {
        String sqlInsert = "INSERT INTO PRESIDENTE (NOME, PARTIDO, DATA_ENTRADA, DATA_SAIDA, SALARIO, VERBA) VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(
                        sqlInsert,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            ps.setString(1, ministro.getNome());
            ps.setString(2, ministro.getPartido());
            ps.setDate(3, new java.sql.Date(ministro.getDataEntrada().getTime()));

            if (ministro.getDataSaida() != null) {
                ps.setDate(4, new java.sql.Date(ministro.getDataSaida().getTime()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            ps.setDouble(5, ministro.getSalario());
            ps.setDouble(6, ministro.getVerba());

            int result = ps.executeUpdate();
            if (result == 1) {
                ResultSet tableKeys = ps.getGeneratedKeys();
                tableKeys.next();

                ministro.setId(tableKeys.getInt(1));
                System.out.println("Presidente inserido com sucesso: " + ministro.getNome());
                return ministro;
            }
            throw new Exception("Erro ao inserir no banco.");
        }
    }

    public Presidente getById(int id) {
        String sqlSelect = "SELECT * FROM PRESIDENTE WHERE ID = ?";
        Presidente ministro = null;

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelect)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ministro = new Presidente();
                ministro.setId(rs.getInt("ID"));
                ministro.setNome(rs.getString("NOME"));
                ministro.setPartido(rs.getString("PARTIDO"));
                ministro.setDataEntrada(rs.getDate("DATA_ENTRADA"));
                ministro.setDataSaida(rs.getDate("DATA_SAIDA"));
                ministro.setSalario(rs.getDouble("SALARIO"));
                ministro.setVerba(rs.getDouble("VERBA"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ministro;
    }

    public List<Presidente> getAll() {
        String sqlSelectAll = "SELECT * FROM PRESIDENTE";
        List<Presidente> ministros = new ArrayList<>();

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Presidente ministro = new Presidente();
                ministro.setId(rs.getInt("ID"));
                ministro.setNome(rs.getString("NOME"));
                ministro.setPartido(rs.getString("PARTIDO"));
                ministro.setDataEntrada(rs.getDate("DATA_ENTRADA"));
                ministro.setDataSaida(rs.getDate("DATA_SAIDA"));
                ministro.setSalario(rs.getDouble("SALARIO"));
                ministro.setVerba(rs.getDouble("VERBA"));
                ministros.add(ministro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ministros;
    }

    public Presidente update(Integer id, Presidente ministro) throws Exception {
        String sqlUpdate = "UPDATE PRESIDENTE SET NOME=?, PARTIDO=?, DATA_ENTRADA=?, DATA_SAIDA=?, SALARIO=?, VERBA=? WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlUpdate)
        ) {
            ps.setString(1, ministro.getNome());
            ps.setString(2, ministro.getPartido());
            ps.setDate(3, new java.sql.Date(ministro.getDataEntrada().getTime()));

            if (ministro.getDataSaida() != null) {
                ps.setDate(4, new java.sql.Date(ministro.getDataSaida().getTime()));
            } else {
                ps.setNull(4, Types.DATE);
            }

            ps.setDouble(5, ministro.getSalario());
            ps.setDouble(6, ministro.getVerba());
            ps.setInt(7, id);

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Presidente atualizado com sucesso: " + ministro.getId());
                return ministro;
            }
            throw new Exception("Erro ao atualizar no banco.");
        }
    }

    public void delete(int id) throws Exception {
        String sqlDelete = "DELETE FROM PRESIDENTE' WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlDelete)
        ) {
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new Exception("Erro ao excluir no banco.");
            }
            System.out.println("Presidente excluído com sucesso: " + id);
        }
    }
}
