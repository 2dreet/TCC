package Telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;





import utilitario.BordaSombreada;
import utilitario.Login;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.RedefinirSenha;
import utilitario.RetornoCliente;
import utilitario.UtilitarioImagem;
import utilitario.UtilitarioTela;
import utilitario.ValidadorCrud;
import utilitario.ValidarLoginJogador;

import java.awt.Component;

import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;

import componente.Menssage;
import dao.FuncionarioDao;
import dao.JogadorDao;
import dao.UsuarioDao;
import entidade.Usuario;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private String senhaOld;
	private String loginOld;

	public TelaLogin() throws Throwable {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);

		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null,
				null, new Color(176, 177, 184), new Color(225, 225, 225)));
		contentPane.setBackground(new Color(70, 74, 84));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		JPanel header = new JPanel();
		header.setSize(296, 30);
		header.setLayout(null);
		header.setLocation(2, 2);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoVisualizar()));
		header.setBorder(null);
		add(header);

		JLabel lbHeader = new JLabel("Login");
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setSize(header.getSize().width - 32, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);

		JButton btFechar = new JButton("");
		btFechar.setIcon(new ImageIcon(TelaLogin.class
				.getResource("/imagem/cancelBlack.png")));
		btFechar.setSize(30, 30);
		btFechar.setLocation(header.getSize().width - 30, 0);
		btFechar.setBackground(UtilitarioTela.getFontColorPadrao());
		btFechar.setFocusPainted(false);
		btFechar.setBorder(new BordaSombreada(false, true, false, false));
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		header.add(btFechar);

		JLabel lbLg = new JLabel("Usuário:");
		lbLg.setBounds(10, 40, 60, 50);
		lbLg.setFont(UtilitarioTela.getFont(14));
		lbLg.setForeground(UtilitarioTela.getFontColorPadrao());
		add(lbLg);

		JTextField login = new JTextField();
		login.setColumns(100);
		login.setBounds(75, 50, 200, 30);
		login.setFont(UtilitarioTela.getFont(14));
		login.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				login.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				login.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		login.setLayout(null);
		login.setBorder(UtilitarioTela.jTextFieldNormal());
		add(login);

		JLabel lbLgS = new JLabel("Senha:");
		lbLgS.setBounds(10, 80, 60, 50);
		lbLgS.setFont(UtilitarioTela.getFont(14));
		lbLgS.setForeground(UtilitarioTela.getFontColorPadrao());
		add(lbLgS);

		JPasswordField senha = new JPasswordField();
		senha.setColumns(100);
		senha.setBounds(75, 90, 200, 30);
		senha.setFont(UtilitarioTela.getFont(14));
		senha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				senha.setBorder(UtilitarioTela.jTextFieldComFocus());
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				senha.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		senha.setLayout(null);
		senha.setBorder(UtilitarioTela.jTextFieldNormal());
		add(senha);

		JButton logar = new JButton("Logar");
		logar.setSize(80, 40);
		logar.setLocation(120, 140);
		logar.setBackground(UtilitarioTela.getFontColorPadrao());
		logar.setFocusPainted(false);
		logar.setBorder(null);
		logar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (login.getText() == null || login.getText().isEmpty()) {
					login.requestFocus();
				} else if (senha.getText() == null || senha.getText().isEmpty()) {
					senha.requestFocus();
				} else if (UsuarioDao.logar(login.getText(), senha.getText(), 1)) {
					try {
						senhaOld = senha.getText();
						loginOld = login.getText();
						logado();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (UsuarioDao.logar(login.getText(), senha.getText(), 1)) {
					try {
						senhaOld = senha.getText();
						loginOld = login.getText();
						logado();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Menssage.setMenssage(
							"Erro",
							"Usuário ou senha Invalido\nOu desativado!\nOu sem Permissão",
							ParametroCrud.getModoCrudDeletar(),
							getContentPane());
				}
			}
		});
		add(logar);

	}

	public void logado() throws Throwable {
		Usuario u = null;
		if (senhaOld.equals("123")) {
			boolean alterou = false;
			RedefinirSenha rs = null;
			if (UsuarioDao.usuarioLogin(loginOld, senhaOld, 1) != null) {
				u = UsuarioDao.usuarioLogin(loginOld, senhaOld, 1);
				rs = new RedefinirSenha(senhaOld, UsuarioDao.usuarioLogin(
						loginOld, senhaOld, 1), this);
			} else {
				Menssage.setMenssage("Erro", "Jogador não cadastrado ou sem permissão!",
						ParametroCrud.getModoCrudDeletar(), getContentPane());
				return;
			}
			rs.setVisible(true);
			alterou = rs.alterada;
			if (rs.alterada) {

			} else {
				Menssage.setMenssage("Erro", "Senha deve ser subistituida",
						ParametroCrud.getModoCrudDeletar(), getContentPane());
				return;

			}
		} else {
			if (UsuarioDao.usuarioLogin(loginOld, senhaOld, 1) != null) {
				u = UsuarioDao.usuarioLogin(loginOld, senhaOld, 1);
			}else{
				Menssage.setMenssage("Erro", "Jogador não cadastrado ou sem permissão!",
						ParametroCrud.getModoCrudDeletar(), getContentPane());
				return;
			} 
		}
		if (u != null) {
			if(ValidarLoginJogador.validarLogin(u)){
				if(!ValidarLoginJogador.jogadorBanido(JogadorDao.getJogadorPorUsuario(u.getCodigoUsuario()).getCodigoJogador())){
					dispose();
					Login.usuario = JogadorDao.getJogador(u
							.getCodigoUsuario()).getUsuario();
					RetornoCliente.logado();
					Home home = new Home();
					home.setVisible(true);
				}else{
					Menssage.setMenssage("Erro", "Jogador está Banido!",
							ParametroCrud.getModoCrudDeletar(), getContentPane());
					return;
				}
			} else{
				Menssage.setMenssage("Erro", "Jogador não faz parte da partida\nOu ainda não foi cadastrado na Partida!",
						ParametroCrud.getModoCrudDeletar(), getContentPane());
				return;
			}
		}
	}

}
