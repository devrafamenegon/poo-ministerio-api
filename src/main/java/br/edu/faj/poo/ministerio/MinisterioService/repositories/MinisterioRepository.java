package br.edu.faj.poo.ministerio.MinisterioService.repositories;

import br.edu.faj.poo.ministerio.MinisterioService.entities.Ministerio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class MinisterioRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Ministerio create(Ministerio m) throws Exception {
        String sqlInsert = "INSERT INTO Pessoa (NOME, NUM_FUNCIONARIOS, VERBA) " + "VALUES (?, ?, ?, ?)";

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
}
