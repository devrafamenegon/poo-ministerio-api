package br.edu.faj.poo.ministerio.MinisterioService.repositories;

import br.edu.faj.poo.ministerio.MinisterioService.entities.Ministro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MinistroRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Ministro create(Ministro ministro) throws Exception {
        String sqlInsert = "INSERT INTO MINISTRO (NOME, PARTIDO, DATA_ENTRADA, DATA_SAIDA, SALARIO, MINISTERIO_ID) VALUES (?, ?, ?, ?, ?, ?)";

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
            ps.setInt(6, ministro.getMinisterioId());

            int result = ps.executeUpdate();
            if (result == 1) {
                ResultSet tableKeys = ps.getGeneratedKeys();
                tableKeys.next();

                ministro.setId(tableKeys.getInt(1));
                System.out.println("Ministro inserido com sucesso: " + ministro.getNome());
                return ministro;
            }
            throw new Exception("Erro ao inserir no banco.");
        }
    }

    public Ministro getById(int id) {
        String sqlSelect = "SELECT * FROM MINISTRO WHERE ID = ?";
        Ministro ministro = null;

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelect)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ministro = new Ministro();
                ministro.setId(rs.getInt("ID"));
                ministro.setNome(rs.getString("NOME"));
                ministro.setPartido(rs.getString("PARTIDO"));
                ministro.setDataEntrada(rs.getDate("DATA_ENTRADA"));
                ministro.setDataSaida(rs.getDate("DATA_SAIDA"));
                ministro.setSalario(rs.getDouble("SALARIO"));
                ministro.setMinisterioId(rs.getInt("MINISTERIO_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ministro;
    }

    public List<Ministro> getAll() {
        String sqlSelectAll = "SELECT * FROM MINISTRO";
        List<Ministro> ministros = new ArrayList<>();

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelectAll)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ministro ministro = new Ministro();
                ministro.setId(rs.getInt("ID"));
                ministro.setNome(rs.getString("NOME"));
                ministro.setPartido(rs.getString("PARTIDO"));
                ministro.setDataEntrada(rs.getDate("DATA_ENTRADA"));
                ministro.setDataSaida(rs.getDate("DATA_SAIDA"));
                ministro.setSalario(rs.getDouble("SALARIO"));
                ministro.setMinisterioId(rs.getInt("MINISTERIO_ID"));
                ministros.add(ministro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ministros;
    }

    public Ministro update(Integer id, Ministro ministro) throws Exception {
        String sqlUpdate = "UPDATE MINISTRO SET NOME=?, PARTIDO=?, DATA_ENTRADA=?, DATA_SAIDA=?, SALARIO=?, MINISTERIO_ID=? WHERE ID=?";

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
            ps.setInt(6, ministro.getMinisterioId());
            ps.setInt(7, id);

            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Ministro atualizado com sucesso: " + ministro.getId());
                return ministro;
            }
            throw new Exception("Erro ao atualizar no banco.");
        }
    }

    public void delete(int id) throws Exception {
        String sqlDelete = "DELETE FROM MINISTRO WHERE ID=?";

        try (
                Connection con = jdbcTemplate.getDataSource().getConnection();
                PreparedStatement ps = con.prepareStatement(sqlDelete)
        ) {
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            if (result != 1) {
                throw new Exception("Erro ao excluir no banco.");
            }
            System.out.println("Ministro excluído com sucesso: " + id);
        }
    }
}
