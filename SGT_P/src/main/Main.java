package main;
import dao.ParametroDao;



public class Main {

	public static void main(String[] args) {
		try {
			ParametroDao.atualizaBanco();
			TelaLogin tl = new TelaLogin();
			tl.setVisible(true);
			RetornoCliente.aberto();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
