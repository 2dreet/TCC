package utilitario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

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
import dao.EntityManagerLocal;
import dao.UsuarioDao;
import entidade.Usuario;

public class RedefinirSenha extends JDialog {

	private JPanel contentPane;
	private String senhaOld;
	public boolean alterada;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public RedefinirSenha(String senhaOld, Usuario usuario, JFrame pai) throws Throwable{
		alterada = false;
		this.senhaOld = senhaOld;
		setSize(300, 200);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, new Color(176, 177, 184), new Color(225, 225, 225)));
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

		JLabel lbHeader = new JLabel("Nova Senha");
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setSize(header.getSize().width - 32, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);

		JButton btFechar = new JButton("");
		btFechar.setIcon(new ImageIcon(RedefinirSenha.class
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
		
		JLabel lbLg = new JLabel("Nova Senha:");
		lbLg.setBounds(10, 40, 100, 50);
		lbLg.setFont(UtilitarioTela.getFont(14));
		lbLg.setForeground(UtilitarioTela.getFontColorPadrao());
		add(lbLg);
		
		JPasswordField login = new JPasswordField();
		login.setColumns(100);
		login.setBounds(125, 50, 150, 30);
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
		
		JLabel lbLgS = new JLabel("Repita a Senha:");
		lbLgS.setBounds(10, 80, 120, 50);
		lbLgS.setFont(UtilitarioTela.getFont(14));
		lbLgS.setForeground(UtilitarioTela.getFontColorPadrao());
		add(lbLgS);
		
		JPasswordField senha = new JPasswordField();
		senha.setColumns(100);
		senha.setBounds(125, 90, 150, 30);
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
		
		JButton logar = new JButton("Alterar");
		logar.setSize(80, 40);
		logar.setLocation(120, 140);
		logar.setBackground(UtilitarioTela.getFontColorPadrao());
		logar.setFocusPainted(false);
		logar.setBorder(null);
		logar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(login.getText() == null || login.getText().isEmpty()){
					login.requestFocus();
				} else if(senha.getText() == null || senha.getText().isEmpty()){
					senha.requestFocus();
				}else if(senha.getText().equals(login.getText())){
					if(!senha.getText().equals(senhaOld)){
						EntityManagerLocal.begin();
						usuario.setSenha(login.getText());
						EntityManagerLocal.merge(usuario);
						EntityManagerLocal.commit();
						EntityManagerLocal.clear();
						alterada = true;
						Menssage.setMenssage("Alterado",
								"A senha Alterada com sucesso!",
								ParametroCrud.getModoCrudNovo(), getContentPane());
						dispose();
					}else{
						Menssage.setMenssage("Erro",
								"A senha deve ser diferente da anterior",
								ParametroCrud.getModoCrudDeletar(), getContentPane());
					}
				}else{
					Menssage.setMenssage("Erro",
							"As senhas não são iguais",
							ParametroCrud.getModoCrudDeletar(), getContentPane());
				}
			}
		});
		add(logar);
		
	}
}
