package dao;

import java.sql.ResultSet;

public class RelatorioDao {

	public static ResultSet getJogadoresMediaModalidade() {
		String sql = "SELECT m.descricao , "
				+ "IF(sexo = 1, 'Masculino','Feminino') as sexo , count(*)qtdSexo, "
				+ "CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) AS idade "
				+ "FROM jogador_partida jp INNER JOIN jogador j ON jp.codigoJogador = j.codigoJogador "
				+ "INNER JOIN usuario u ON j.codigoUsuario = u.codigoUsuario "
				+ "INNER JOIN partida p ON p.codigoPartida = jp.codigoPartida "
				+ "INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato "
				+ "INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ "GROUP BY sexo,idade,m.codigoModalidade";
		return EntityManagerLocal.getResultSet(sql);
	}

	
	
}
