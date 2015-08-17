package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tela.HomeFuncionario;
import utilitario.BordaEscura;
import utilitario.UtilitarioTela;
import java.awt.Font;

public class MenuJogador extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JButton btLocalizar; 
	private JTextField txLocalizar;
	private JButton btNovoJogador;
	private JButton btAlterarJogador;
	private JButton btDeletarJogador;
	
	public MenuJogador() {
		setSize(UtilitarioTela.getTamanhoMenuBaixo());
		setBackground(null);
		setLayout(null);
		setVisible(true);

		
		JPanel menuLateral = new JPanel();
		menuLateral.setSize(UtilitarioTela.getTamanhoMenuLateral());
		menuLateral.setBorder(new BordaEscura());
		menuLateral.setBackground(new Color(252, 79, 63));
		menuLateral.setLocation(0, 0);
		add(menuLateral);
		menuLateral.setLayout(null);
		
		JPanel menuLateralTopo = new JPanel();
		menuLateralTopo.setBounds(0, 0, 240, 30);
		menuLateralTopo.setBorder(new BordaEscura());
		menuLateralTopo.setBackground(new Color(232,234,239));
		menuLateral.add(menuLateralTopo);
		menuLateralTopo.setLayout(null);

		JLabel tituloMenu = new JLabel("Jogador");
		tituloMenu.setForeground(Color.DARK_GRAY);
		tituloMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
		tituloMenu.setHorizontalAlignment(SwingConstants.CENTER);
		tituloMenu.setBounds(0, 0, 240, 25);
		menuLateralTopo.add(tituloMenu);

		JPanel menuMeio = new JPanel();
		menuMeio.setSize(UtilitarioTela.getTamanhoMeio());
		menuMeio.setLocation(250, 0);
		menuMeio.setBackground(new Color(46, 49, 56));
		add(menuMeio);
		
		JPanel menuLateralBaixo = new JPanel();
		menuLateralBaixo.setBounds(0, 30, 240, getHeight() - 30);
		menuLateralBaixo.setBackground(new Color(46, 49, 56));
		menuLateral.add(menuLateralBaixo);

		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 240, 40);
		jp1.setBackground(null);
		jp1.setLayout(null);
		jp1.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp1);
		
		
		txLocalizar = new JTextField();
		txLocalizar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txLocalizar.setBorder(UtilitarioTela.jTextFieldComFocus());
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				txLocalizar.setBorder(UtilitarioTela.jTextFieldNormal());
			}
		});
		txLocalizar.setBounds(5, 5, 200, 24);
		txLocalizar.setLayout(null);
		txLocalizar.setBorder(UtilitarioTela.jTextFieldNormal());
		
		jp1.add(txLocalizar);
		
		
		btLocalizar = new JButton("");
		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
			}
		});
		btLocalizar.setBounds(jp1.getWidth()-30, 5, 24, 24);
		btLocalizar.setName("localizar");
		btLocalizar.setBorderPainted(false);
		
		getIcon(btLocalizar, false);
		jp1.add(btLocalizar);
		
		
		JPanel jp2 = new JPanel();
		jp2.setBounds(0, 40, 240, 40);
		jp2.setBackground(null);
		jp2.setLayout(null);
		jp2.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp2);
		
		btNovoJogador = new JButton("Cadastrar Jogador");
		
		btNovoJogador.setBounds(5, 5, 230, 30);
		btNovoJogador.setBorderPainted(false);
		btNovoJogador.setBackground(null);
		btNovoJogador.setLayout(null);
		btNovoJogador.setName("cadastrarJogador");
		btNovoJogador.setHorizontalAlignment(SwingConstants.LEFT);
		btNovoJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btNovoJogador, true);
			}
		});
		jp2.add(btNovoJogador);
		
		JPanel jp3 = new JPanel();
		jp3.setBounds(0, 80, 240, 40);
		jp3.setBackground(null);
		jp3.setLayout(null);
		jp3.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp3);

		btAlterarJogador = new JButton("Alterar Jogador");
		btAlterarJogador.setBounds(5, 5, 230, 30);
		btAlterarJogador.setBorderPainted(false);
		btAlterarJogador.setBackground(null);
		btAlterarJogador.setLayout(null);
		btAlterarJogador.setName("alterarJogador");
		btAlterarJogador.setHorizontalAlignment(SwingConstants.LEFT);
		btAlterarJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btAlterarJogador, true);
			}
		});
		
		jp3.add(btAlterarJogador);
		
		
		JPanel jp4 = new JPanel();
		jp4.setBounds(0, 120, 240, 40);
		jp4.setBackground(null);
		jp4.setLayout(null);
		jp4.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp4);
		
		btDeletarJogador = new JButton("Deletar Jogador");
		btDeletarJogador.setBounds(5, 5, 230, 30);
		btDeletarJogador.setBorderPainted(false);
		btDeletarJogador.setBackground(null);
		btDeletarJogador.setLayout(null);
		btDeletarJogador.setName("deletarJogador");
		btDeletarJogador.setHorizontalAlignment(SwingConstants.LEFT);
		btDeletarJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btDeletarJogador, true);
			}
		});
		
		jp4.add(btDeletarJogador);
		
		
		
		JPanel jp5 = new JPanel();
		jp5.setBounds(0, 160, 240, 40);
		jp5.setBackground(null);
		jp5.setLayout(null);
		jp5.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp5);
		
		JPanel jp6 = new JPanel();
		jp6.setBounds(0, 200, 240, 40);
		jp6.setBackground(null);
		jp6.setLayout(null);
		jp6.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp6);
		
		JPanel jp7 = new JPanel();
		jp7.setBounds(0, 240, 240, 40);
		jp7.setBackground(null);
		jp7.setLayout(null);
		jp7.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp7);

		
		getIcon(btNovoJogador, false);
		getIcon(btAlterarJogador, false);
		getIcon(btDeletarJogador, false);
		btDeletarJogador.setForeground(getFontColorSelecao(false));
		btAlterarJogador.setForeground(getFontColorSelecao(false));
		btNovoJogador.setForeground(getFontColorSelecao(false));
		btNovoJogador.setFont(new Font("SansSerif", Font.BOLD, 12));
		btAlterarJogador.setFont(new Font("SansSerif", Font.BOLD, 12));
		btDeletarJogador.setFont(new Font("SansSerif", Font.BOLD, 12));
	
		repaint();
	}

	public void zeraSelecao() {
		btNovoJogador.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/addJog.png")));
		btNovoJogador.setBackground(getBtnFundo(false));
		btNovoJogador.setForeground(getFontColorSelecao(false));
		
		btAlterarJogador.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/altJog.png")));
		btAlterarJogador.setBackground(getBtnFundo(false));
		btAlterarJogador.setForeground(getFontColorSelecao(false));
		
		btDeletarJogador.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/delJog.png")));
		btDeletarJogador.setBackground(getBtnFundo(false));
		btDeletarJogador.setForeground(getFontColorSelecao(false));
		
	}

	public void getIcon(JButton botao, boolean selecionado) {
		String url = "";
		if (botao.getName() != null) {
			if (botao.getName().equals("localizar")) {
				if (selecionado) {
					url = "/imagem/diverssos/localizar.png";
				} else {
					url = "/imagem/diverssos/localizar.png";
				}
			}
			if (botao.getName().equals("cadastrarJogador")) {
				if (selecionado) {
					url = "/imagem/crud/addJogSelect.png";
				} else {
					url = "/imagem/crud/addJog.png";
				}
			}
			if (botao.getName().equals("alterarJogador")) {
				if (selecionado) {
					url = "/imagem/crud/altJogSelect.png";
				} else {
					url = "/imagem/crud/altJog.png";
				}
			}
			if (botao.getName().equals("deletarJogador")) {
				if (selecionado) {
					url = "/imagem/crud/delJogSelect.png";
				} else {
					url = "/imagem/crud/delJog.png";
				}
			}
			botao.setFocusPainted(false);
			botao.setBackground(getBtnFundo(selecionado));
			botao.setIcon(new ImageIcon(HomeFuncionario.class.getResource(url)));
			botao.setForeground(getFontColorSelecao(true));
			repaint();
		}
	}

	
	public Color getBtnFundo(boolean selecionado) {
		int r = 0, g = 0, b = 0;

		if (selecionado) {
			r = 252;
			g = 79;
			b = 63;
		} else {
			r = 46;
			g = 49;
			b = 56;
		}

		return new Color(r, g, b);
	}
	
	public static Color getFontColorSelecao(boolean selecionado){
		int r = 0, g = 0, b = 0;

		if (!selecionado) {
			r = 252;
			g = 79;
			b = 63;
		} else {
			r = 46;
			g = 49;
			b = 56;
		}

		return new Color(r, g, b);
		
	}
	
}
