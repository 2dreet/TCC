package dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class ParametroDao {

	public static void atualizaBanco() {
		while (true) {
			Integer versaoBanco = getVersaoBanco();
			switch (versaoBanco) {
			case 0: {
				atualizarBanco(
						"INSERT INTO permissao(descricao, ativo) values('Jogador',true)",
						1);
			}
			case 1: {
				atualizarBanco(
						"INSERT INTO permissao(descricao, ativo) values('Funcionario',true)",
						2);
			}
			case 2: {
				atualizarBanco(
						"INSERT INTO permissao(descricao, ativo) values('Administrador',true)",
						3);
			}
			case 3: {
				atualizarBanco(
						"INSERT INTO chave(descricao, ativo) values('Mata-Mata',true)",
						4);
			}
			case 4: {
				atualizarBanco(
						"INSERT INTO chave(descricao, ativo) values('Winner e Lower',true)",
						5);
			}
			case 5: {
				atualizarBanco(
						"INSERT INTO chave(descricao, ativo) values('Grupo',true)",
						6);
			}
			case 6: {
				atualizarBanco("INSERT INTO `usuario`" + "(`codigoUsuario`,"
						+ "`codigoPermissao`," + "`nome`," + "`sobreNome`,"
						+ "`cpf`," + "`dataNascimento`," + "`telefone`,"
						+ "`email`," + "`usuario`," + "`senha`," + "`ativo`,"
						+ "`sexo`)" + "VALUES" + "(1," + "3," + "'Admin',"
						+ "'Admin'," + "'00000000000'," + "now(),"
						+ "'(00)0000-0000'," + "'admin@admin'," + "'admin',"
						+ "'123'," + "true," + "0);", 7);
			}
			case 7: {
				atualizarBanco("INSERT INTO `funcionario`"
						+ "(`codigoFuncionario`," + "`codigoUsuario`,"
						+ "`dataCadastro`)" + "VALUES" + "(1," + "1,"
						+ "now());", 8);
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
			setVersaoBanco(versaoBanco);
		} catch (Exception ex) {
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
			EntityManagerLocal.getEntityManager().createNativeQuery(sql)
					.executeUpdate();
			EntityManagerLocal.commit();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
	}

}
