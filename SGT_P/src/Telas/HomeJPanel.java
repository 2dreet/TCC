package Telas;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.JogadorDao;
import entidade.Jogador;
import utilitario.Login;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTela;


public class HomeJPanel extends JPanel{

	private JPanel header;
	private JLabel lblHeader;
	private JPanel meio;

	public HomeJPanel(){
		
		Jogador jogador = JogadorDao.getJogadorPorUsuario(Login.usuario.getCodigoUsuario());
		
		setSize(280, 360);
		setLayout(null);
		setBackground(UtilitarioTela.getFontColorCrud());
		header = new JPanel();
		header.setSize(280, 30);
		header.setLocation(0, 0);
		header.setLayout(null);
		header.setBackground(Color.white);
		header.setBorder(null);
		add(header);

		lblHeader = new JLabel("Início");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(0, 0, header.getWidth(), header.getHeight());
		lblHeader.setFont(UtilitarioTela.getFont(14));
		lblHeader.setForeground(UtilitarioTela.getFontColorCrud());
		header.add(lblHeader);

		meio = new JPanel();
		meio.setSize(280, 330);
		meio.setLocation(0,30);
		meio.setLayout(null);
		meio.setBackground(UtilitarioTela.getFontColorCrud());
		meio.setBorder(null);
		add(meio);
		
		
		JLabel lbLg = new JLabel(
				UtilitarioImagem.converterImage(jogador.getTime().getLogo()));
		lbLg.setBounds(20, 20, 50, 50);
		lbLg.setFont(UtilitarioTela.getFont(14));
		lbLg.setForeground(UtilitarioTela.getFontColorPadrao());
		meio.add(lbLg);

		JLabel lbNome = new JLabel(jogador.getTime()
				.getDescricao());
		lbNome.setBounds(80, 20, 150, 50);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorPadrao());
		meio.add(lbNome);
	}
}
