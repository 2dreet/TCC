package dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class ParametroDao {

	public static void atualizaBanco() {
		while(true) {
			Integer versaoBanco = getVersaoBanco();
			switch (versaoBanco) {
				case 0: {
					atualizarBanco("INSERT INTO permissao(descricao, ativo) values('Jogador',true)", 1);
				}
				case 1: {
					atualizarBanco("INSERT INTO permissao(descricao, ativo) values('Funcionario',true)", 2);
				}
				case 2: {
					atualizarBanco("INSERT INTO permissao(descricao, ativo) values('Administrador',true)", 3);
				}
				default: {
					return;
				}
			}
		}

	}

	public static void atualizarBanco(String sql, int versaoBanco) {
		try {
			System.out.println("Atualizando Versao: " + versaoBanco);
			System.out.println("Atualização : " + sql);
			EntityManagerLocal.begin();
			Query query = EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql);
			query.executeUpdate();
			EntityManagerLocal.commit();
			setVersaoBanco(versaoBanco + 1);
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
	}

	public static Integer getVersaoBanco() {
		try {
			String sql = "SELECT versao FROM versao_banco";
			Query query = EntityManagerLocal.getEntityManager()
					.createNativeQuery(sql);
			return (Integer) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public static void setVersaoBanco(int versao) {
		try {
			String sql = "UPDATE versao_banco SET versao = '" + versao + "'";
			
			EntityManagerLocal.begin();
			EntityManagerLocal.getEntityManager().createNativeQuery(sql).executeUpdate();
			EntityManagerLocal.commit();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
	}

}
