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

import crud.CrudBanimento;
import crud.CrudMarca;
import crud.CrudModalidade;
import crud.CrudPeriferico;
import tela.HomeFuncionario;
import utilitario.BordaEscura;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;

import java.awt.Font;

import localizar.LocalizarJogador;

public class MenuConfiguracoes extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel menuMeio;
	private JButton btMarca;
	private JButton btPeriferico;
	private JButton btModalidade;
	private JButton btBanimento;
	
	public MenuConfiguracoes() {
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
		menuLateralTopo.setBackground(new Color(232, 234, 239));
		menuLateral.add(menuLateralTopo);
		menuLateralTopo.setLayout(null);

		JLabel tituloMenu = new JLabel("Configurações");
		tituloMenu.setForeground(Color.DARK_GRAY);
		tituloMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
		tituloMenu.setHorizontalAlignment(SwingConstants.CENTER);
		tituloMenu.setBounds(0, 0, 240, 25);
		menuLateralTopo.add(tituloMenu);

		menuMeio = new JPanel();
		menuMeio.setSize(UtilitarioTela.getTamanhoMeio());
		menuMeio.setLocation(250, 0);
		menuMeio.setLayout(null);
		menuMeio.setBackground(new Color(46, 49, 56));
		add(menuMeio);

		JPanel menuLateralBaixo = new JPanel();
		menuLateralBaixo.setBounds(0, 30, 240, getHeight() - 30);
		menuLateralBaixo.setBackground(new Color(46, 49, 56));
		menuLateralBaixo.setLayout(null);
		menuLateral.add(menuLateralBaixo);

		JPanel jp1 = new JPanel();
		jp1.setBounds(0, 0, 240, 40);
		jp1.setBackground(null);
		jp1.setLayout(null);
		jp1.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp1);

		btMarca = new JButton("Marca");
		btMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btMarca, true);
				menuMeio.removeAll();
				CrudMarca c = new CrudMarca(MenuConfiguracoes.this);
				menuMeio.add(c);
				menuMeio.revalidate();
				menuMeio.repaint();
			}
		});
		btMarca.setBounds( 5, 5,  230, 30);
		btMarca.setName("marca");
		btMarca.setBorderPainted(false);
		btMarca.setHorizontalAlignment(SwingConstants.LEFT);
		getIcon(btMarca, false);
		jp1.add(btMarca);

		JPanel jp2 = new JPanel();
		jp2.setBounds(0, 40, 240, 40);
		jp2.setBackground(null);
		jp2.setLayout(null);
		jp2.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp2);

		btPeriferico = new JButton("Periférico");

		btPeriferico.setBounds(5, 5, 230, 30);
		btPeriferico.setBorderPainted(false);
		btPeriferico.setBackground(null);
		btPeriferico.setLayout(null);
		btPeriferico.setName("periferico");
		btPeriferico.setHorizontalAlignment(SwingConstants.LEFT);
		btPeriferico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btPeriferico, true);
				menuMeio.removeAll();
				CrudPeriferico c = new CrudPeriferico(MenuConfiguracoes.this);
				menuMeio.add(c);
				menuMeio.revalidate();
				menuMeio.repaint();
			}
		});
		jp2.add(btPeriferico);

		JPanel jp3 = new JPanel();
		jp3.setBounds(0, 80, 240, 40);
		jp3.setBackground(null);
		jp3.setLayout(null);
		jp3.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp3);
		
		btModalidade = new JButton("Modalidade");
		btModalidade.setBounds(5, 5, 230, 30);
		btModalidade.setBorderPainted(false);
		btModalidade.setBackground(null);
		btModalidade.setLayout(null);
		btModalidade.setName("modalidade");
		btModalidade.setHorizontalAlignment(SwingConstants.LEFT);
		btModalidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btModalidade, true);
				menuMeio.removeAll();
				CrudModalidade c = new CrudModalidade(MenuConfiguracoes.this);
				menuMeio.add(c);
				menuMeio.revalidate();
				menuMeio.repaint();
			}
		});
		jp3.add(btModalidade);
		

		JPanel jp4 = new JPanel();
		jp4.setBounds(0, 120, 240, 40);
		jp4.setBackground(null);
		jp4.setLayout(null);
		jp4.setBorder(new BordaEscura());
		menuLateralBaixo.add(jp4);
		
		btBanimento = new JButton("Banimento");
		btBanimento.setBounds(5, 5, 230, 30);
		btBanimento.setBorderPainted(false);
		btBanimento.setBackground(null);
		btBanimento.setLayout(null);
		btBanimento.setName("banimento");
		btBanimento.setHorizontalAlignment(SwingConstants.LEFT);
		btBanimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeraSelecao();
				getIcon(btBanimento, true);
				menuMeio.removeAll();
				CrudBanimento c = new CrudBanimento(MenuConfiguracoes.this);
				menuMeio.add(c);
				menuMeio.revalidate();
				menuMeio.repaint();
			}
		});
		jp4.add(btBanimento);
		

		getIcon(btMarca, false);
		getIcon(btPeriferico, false);
		getIcon(btBanimento, false);
		getIcon(btModalidade, false);
		
		btMarca.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		btPeriferico.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		btBanimento.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		btModalidade.setForeground(UtilitarioTela
				.getFontColorSelecao(false));
		
		btMarca.setFont(UtilitarioTela.getFont(14));
		btPeriferico.setFont(UtilitarioTela.getFont(14));
		btBanimento.setFont(UtilitarioTela.getFont(14));
		btModalidade.setFont(UtilitarioTela.getFont(14));
		
		zeraSelecao();
		getIcon(btMarca, true);
		menuMeio.removeAll();
		CrudMarca c = new CrudMarca(MenuConfiguracoes.this);
		menuMeio.add(c);
		menuMeio.revalidate();
		menuMeio.repaint();
		
	}

	public void home(){
		zeraSelecao();
	}
	
	
	
	public void zeraSelecao() {
		
		btMarca.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/marca.png")));
		btMarca.setBackground(UtilitarioTela.getBtnFundo(false));
		btMarca.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
		btPeriferico.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/crud/peri.png")));
		btPeriferico.setBackground(UtilitarioTela.getBtnFundo(false));
		btPeriferico.setForeground(UtilitarioTela.getFontColorSelecao(false));

		btModalidade.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/modalidade.png")));
		btModalidade.setBackground(UtilitarioTela.getBtnFundo(false));
		btModalidade.setForeground(UtilitarioTela.getFontColorSelecao(false));
		
		btBanimento.setIcon(new ImageIcon(HomeFuncionario.class
				.getResource("/imagem/ban.png")));
		btBanimento.setBackground(UtilitarioTela.getBtnFundo(false));
		btBanimento.setForeground(UtilitarioTela.getFontColorSelecao(false));
	}

	public void getIcon(JButton botao, boolean selecionado) {
		String url = "";
		if (botao.getName() != null) {
			if (botao.getName().equals("marca")) {
				if (selecionado) {
					url = "/imagem/crud/marcaSelect.png";
				} else {
					url = "/imagem/crud/marca.png";
				}
			}
			if (botao.getName().equals("periferico")) {
				if (selecionado) {
					url = "/imagem/crud/periSelect.png";
				} else {
					url = "/imagem/crud/peri.png";
				}
			}
			if (botao.getName().equals("driver")) {
				if (selecionado) {
					url = "/imagem/crud/driverSelect.png";
				} else {
					url = "/imagem/crud/driver.png";
				}
			}
			if (botao.getName().equals("modalidade")) {
				if (selecionado) {
					url = "/imagem/modalidadeSelect.png";
				} else {
					url = "/imagem/modalidade.png";
				}
			}
			if (botao.getName().equals("banimento")) {
				if (selecionado) {
					url = "/imagem/ban_select.png";
				} else {
					url = "/imagem/ban.png";
				}
			}
			botao.setFocusPainted(false);
			botao.setBackground(UtilitarioTela.getBtnFundo(selecionado));
			botao.setIcon(new ImageIcon(HomeFuncionario.class.getResource(url)));
			botao.setForeground(UtilitarioTela.getFontColorSelecao(true));
			repaint();
		}
	}
}
