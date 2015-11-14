package utilitario;

import java.util.Date;

import dao.EntityManagerLocal;
import dao.PermissaoDao;
import entidade.Jogador;
import entidade.Pc;
import entidade.Time;
import entidade.Usuario;

public class DadosGenericos {

	public static void gerarPc(){
		for(int i = 0; i < 13; i++){
			Pc pc = new Pc();
			pc.setAtivo(true);
			pc.setDescricao("PC "+i);
			pc.setIp("127.0.0.1");
			EntityManagerLocal.begin();
			EntityManagerLocal.persist(pc);
			EntityManagerLocal.commit();
			EntityManagerLocal.clear();
		}
	}
	
	public static void gerarTimes(){
		for(int i = 0; i < 30; i++){
			Time time = new Time();
			time.setAtivo(true);
			time.setDataCadastro(new Date());
			time.setDescricao("Time "+i);
			EntityManagerLocal.begin();
			EntityManagerLocal.persist(time);
			gerarJogadores(time);
			EntityManagerLocal.commit();
			EntityManagerLocal.clear();
		}
	}
	
	public static void gerarJogadores(Time time){
		for(int i = 0; i < 8; i++){
			Usuario usuario = new Usuario();
			usuario.setAtivo(true);
			usuario.setCpf("00000000000");
			usuario.setDataNascimento(new Date());
			usuario.setEmail("");
			usuario.setNome("Jogador "+time.getDescricao()+" "+i);
			usuario.setPermissao(PermissaoDao.getPermissao(1));
			usuario.setSenha("123");
			usuario.setSexo(1);
			usuario.setSobreNome("");
			usuario.setTelefone("0000000000");
			usuario.setUsuario("jogador"+time.getDescricao()+i);
			Jogador jogador = new Jogador();
			jogador.setDataCadastro(new Date());
			jogador.setTime(time);
			jogador.setUsuario(usuario);
			if(i < 5){
				jogador.setTitular(true);
			}else{
				jogador.setTitular(false);
			}
			EntityManagerLocal.persist(usuario);
			EntityManagerLocal.persist(jogador);
		}
	}
	
	public static void main(String [] args){
		Conectar.conectar();
		gerarTimes();
		gerarPc();
	}
}
