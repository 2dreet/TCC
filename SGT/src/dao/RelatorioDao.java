package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import utilitario.UtilitarioCrud;
import entidade.Campeonato;
import entidade.Marca;
import entidade.Modalidade;
import entidade.Periferico;

public class RelatorioDao {

	private static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

	public static ResultSet getJogadoresQuantidadeCampeonato(
			Campeonato campeonato,  String dataInicial,
			String dataFinal, Modalidade modalidade) {
		String sql = "SELECT c.descricao as campeonato, m.descricao as modalidade, IF(sexo = 1, 'Masculino','Feminino') as sexo ,";
		
			sql += " CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) AS idade,";
		sql += " count(*)qtdSexo"
				+ " FROM jogador_partida jp INNER JOIN jogador j ON jp.codigoJogador = j.codigoJogador "
				+ " INNER JOIN usuario u ON j.codigoUsuario = u.codigoUsuario "
				+ " INNER JOIN partida p ON p.codigoPartida = jp.codigoPartida "
				+ " INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato "
				+ " INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " WHERE c.codigoCampeonato > 0 ";

		if (campeonato != null) {
			sql += " AND c.codigoCampeonato = "
					+ campeonato.getCodigoCampeonato() + " ";
		}

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}

		if (modalidade != null) {
			sql += " AND m.codigoModalidade = "
					+ modalidade.getCodigoModalidade() + " ";
		}

		sql += " GROUP BY sexo,m.codigoModalidade,idade";
		
		return EntityManagerLocal.getResultSet(sql);
	}

	public static ResultSet getJogadoresCampeonato(Campeonato campeonato,
			String faixaEtaria, String dataInicial, String dataFinal,
			Modalidade modalidade) {
		String sql = "SELECT c.descricao as campeonato, m.descricao as modalidade, IF(sexo = 1, 'Masculino','Feminino') as sexo , u.nome";
		if (faixaEtaria != null) {
			sql += " CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) AS idade,";
		}
		sql += " count(*)qtdSexo"
				+ " FROM jogador_partida jp INNER JOIN jogador j ON jp.codigoJogador = j.codigoJogador "
				+ " INNER JOIN usuario u ON j.codigoUsuario = u.codigoUsuario "
				+ " INNER JOIN partida p ON p.codigoPartida = jp.codigoPartida "
				+ " INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato "
				+ " INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " WHERE c.codigoCampeonato > 0 ";

		if (campeonato != null) {
			sql += " AND c.codigoCampeonato = "
					+ campeonato.getCodigoCampeonato() + " ";
		}

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}

		if (modalidade != null) {
			sql += " AND m.codigoModalidade = "
					+ modalidade.getCodigoModalidade() + " ";
		}

		if (faixaEtaria != null) {
			sql += " AND CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) "
					+ faixaEtaria;
		}

		sql += " GROUP BY sexo,m.codigoModalidade";

		return EntityManagerLocal.getResultSet(sql);
	}

	public static ResultSet getPcCampeonato(Campeonato campeonato,
			String dataInicial, String dataFinal, Modalidade modalidade) {
		String sql = "SELECT Pc.descricao as descricao, pc.ip as ip, c.descricao as campeonato, m.descricao as modalidade "
				+ "FROM pc_partida pp "
				+ "INNER JOIN partida p ON p.codigoPartida = pp.codigoPartida "
				+ "INNER JOIN pc pc ON pc.codigoPc = pp.codigoPc "
				+ "INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato "
				+ "INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " WHERE c.codigoCampeonato > 0 ";
		if (campeonato != null) {
			sql += " AND c.codigoCampeonato = "
					+ campeonato.getCodigoCampeonato() + " ";
		}

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}

		if (modalidade != null) {
			sql += " AND m.codigoModalidade = "
					+ modalidade.getCodigoModalidade() + " ";
		}

		return EntityManagerLocal.getResultSet(sql);
	}

	public static ResultSet getModalidadesMaisJogadas(Campeonato campeonato,
			String faixaEtaria, String dataInicial, String dataFinal,
			Modalidade modalidade) {
		String sql = "SELECT c.descricao as campeonato, m.descricao as modalidade, IF(sexo = 1, 'Masculino','Feminino') as sexo ,"
				+ " CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) AS idade,"
				+ " count(*)qtdSexo"
				+ " FROM jogador_partida jp INNER JOIN jogador j ON jp.codigoJogador = j.codigoJogador "
				+ " INNER JOIN usuario u ON j.codigoUsuario = u.codigoUsuario "
				+ " INNER JOIN partida p ON p.codigoPartida = jp.codigoPartida "
				+ " INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato "
				+ " INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " WHERE c.codigoCampeonato > 0 ";

		if (campeonato != null) {
			sql += " AND c.codigoCampeonato = "
					+ campeonato.getCodigoCampeonato() + " ";
		}

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}

		if (modalidade != null) {
			sql += " AND m.codigoModalidade = "
					+ modalidade.getCodigoModalidade() + " ";
		}

		if (faixaEtaria != null) {
			sql += " AND CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) "
					+ faixaEtaria;
		}

		sql += " GROUP BY m.codigoModalidade, sexo, idade";
		return EntityManagerLocal.getResultSet(sql);
	}

	public static ResultSet getTempoMedio(Campeonato campeonato,
			String dataInicial, String dataFinal, Modalidade modalidade) {
		String sql = "SELECT m.descricao as modalidade, ch.descricao as chave,  TIME_FORMAT((TIMEDIFF (dataInicio, dataFim) / (COUNT(*))), '%H:%i:%s') as tempoMedio "
				+ "FROM campeonato c "
				+ " INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " INNER JOIN chave ch ON ch.codigoChave = c.codigoChave  "
				+ " WHERE c.codigoCampeonato > 0 ";
		if (campeonato != null) {
			sql += " AND c.codigoCampeonato = "
					+ campeonato.getCodigoCampeonato() + " ";
		}

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}

		if (modalidade != null) {
			sql += " AND m.codigoModalidade = "
					+ modalidade.getCodigoModalidade() + " ";
		}

		sql += " GROUP BY m.descricao, ch.descricao";
		return EntityManagerLocal.getResultSet(sql);
	}

	public static ResultSet getPerifericosUtilizadosModalidade(
			String faixaEtaria, String dataInicial, String dataFinal,
			Modalidade modalidade, Periferico periferico, Marca marca) {
		String sql = " SELECT CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) AS idade,"
				+ " IF(sexo = 1, 'Masculino','Feminino') as sexo , pe.descricao as periferico,"
				+ " ma.descricao as marca, m.descricao as modalidade, count(j.codigoJogador) as totalJogadores"
				+ " FROM jogador_partida jp  "
				+ " INNER JOIN jogador j ON jp.codigoJogador = j.codigoJogador "
				+ " INNER JOIN jogador_periferico jpe ON jpe.codigoJogador = j.codigoJogador "
				+ " INNER JOIN usuario u ON j.codigoUsuario = u.codigoUsuario"
				+ " INNER JOIN partida p ON p.codigoPartida = jp.codigoPartida"
				+ " INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato"
				+ " INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " INNER JOIN marca ma ON jpe.codigoMarca = ma.codigoMarca "
				+ " INNER JOIN periferico pe ON jpe.codigoPeriferico = pe.codigoPeriferico"
				+ " WHERE c.codigoCampeonato > 0  ";

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}

		if (modalidade != null) {
			sql += " AND m.codigoModalidade = "
					+ modalidade.getCodigoModalidade() + " ";
		}
		
		if (periferico != null) {
			sql += " AND jpe.codigoPeriferico = "
					+ periferico.getCodigoPeriferico() + " ";
		}
		
		if (marca != null) {
			sql += " AND jpe.codigoMarca = "
					+ marca.getCodigoMarca() + " ";
		}

		if (faixaEtaria != null) {
			sql += " AND CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) "
					+ faixaEtaria;
		}

		sql += " GROUP BY sexo,m.codigoModalidade,idade";
		return EntityManagerLocal.getResultSet(sql);
	}
	
	public static ResultSet getPerifericosUtilizadosIdade(
			String faixaEtaria, String dataInicial, String dataFinal) {
		String sql = " SELECT CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) AS idade,"
				+ " IF(sexo = 1, 'Masculino','Feminino') as sexo , pe.descricao as periferico,"
				+ " ma.descricao as marca, m.descricao as modalidade, count(j.codigoJogador) as totalJogadores"
				+ " FROM jogador_partida jp  "
				+ " INNER JOIN jogador j ON jp.codigoJogador = j.codigoJogador "
				+ " INNER JOIN jogador_periferico jpe ON jpe.codigoJogador = j.codigoJogador "
				+ " INNER JOIN usuario u ON j.codigoUsuario = u.codigoUsuario"
				+ " INNER JOIN partida p ON p.codigoPartida = jp.codigoPartida"
				+ " INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato"
				+ " INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " INNER JOIN marca ma ON jpe.codigoMarca = ma.codigoMarca "
				+ " INNER JOIN periferico pe ON jpe.codigoPeriferico = pe.codigoPeriferico"
				+ " WHERE c.codigoCampeonato > 0  ";

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}
		
		if (faixaEtaria != null) {
			sql += " AND CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) "
					+ faixaEtaria;
		}

		sql += " GROUP BY sexo,m.codigoModalidade,idade";
		return EntityManagerLocal.getResultSet(sql);
	}
	
	public static ResultSet getPerifericosUtilizadosJogadores(
			String faixaEtaria, String dataInicial, String dataFinal,
			Modalidade modalidade, Periferico periferico, Marca marca) {
		String sql = " SELECT CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) AS idade,"
				+ " IF(sexo = 1, 'Masculino','Feminino') as sexo , pe.descricao as periferico,"
				+ " ma.descricao as marca, m.descricao as modalidade, u.nome"
				+ " FROM jogador_partida jp  "
				+ " INNER JOIN jogador j ON jp.codigoJogador = j.codigoJogador "
				+ " INNER JOIN jogador_periferico jpe ON jpe.codigoJogador = j.codigoJogador "
				+ " INNER JOIN usuario u ON j.codigoUsuario = u.codigoUsuario"
				+ " INNER JOIN partida p ON p.codigoPartida = jp.codigoPartida"
				+ " INNER JOIN campeonato c ON c.codigoCampeonato = p.codigoCampeonato"
				+ " INNER JOIN modalidade m ON m.codigoModalidade = c.codigoModalidade "
				+ " INNER JOIN marca ma ON jpe.codigoMarca = ma.codigoMarca "
				+ " INNER JOIN periferico pe ON jpe.codigoPeriferico = pe.codigoPeriferico"
				+ " WHERE c.codigoCampeonato > 0  ";

		if (dataInicial != null && !dataInicial.trim().isEmpty()) {
			sql += " AND c.dataInicio = '"
					+ sd.format(UtilitarioCrud.getData(dataInicial)) + "' ";
		}

		if (dataFinal != null && !dataFinal.trim().isEmpty()) {
			sql += " AND c.dataFim = '"
					+ sd.format(UtilitarioCrud.getData(dataFinal)) + "' ";
		}

		if (modalidade != null) {
			sql += " AND m.codigoModalidade = "
					+ modalidade.getCodigoModalidade() + " ";
		}
		
		if (periferico != null) {
			sql += " AND jpe.codigoPeriferico = "
					+ periferico.getCodigoPeriferico() + " ";
		}
		
		if (marca != null) {
			sql += " AND jpe.codigoMarca = "
					+ marca.getCodigoMarca() + " ";
		}

		if (faixaEtaria != null) {
			sql += " AND CAST((TO_DAYS(NOW())- TO_DAYS(dataNascimento)) / 365.25 as SIGNED) "
					+ faixaEtaria;
		}
		return EntityManagerLocal.getResultSet(sql);
	}

}
