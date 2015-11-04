package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.omg.CORBA.TCKind;

import componente.Menssage;
import componente.MenssageConfirmacao;
import dao.EntityManagerLocal;
import dao.MarcaDao;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;
import entidade.Marca;
import entidade.Usuario;

public class DialogRedefinirSenha {

	public JTextField txNome;
	private boolean confirmado;
	private Usuario usuario;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel meio;

	public void redefinir(Usuario usuario, JPanel painelPai) {
		this.usuario = usuario;

		JDialog dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(400, 220);
		dialog.getContentPane().setBackground(new Color(232, 234, 239));
		dialog.setLocationRelativeTo(painelPai);

		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(
				102, 102, 102)));
		panel.setLayout(null);
		panel.setSize(dialog.getSize());
		panel.setBackground(null);
		panel.setLocation(0, 0);
		panel.setFocusable(true);
		panel.requestFocusInWindow();

		JPanel header = new JPanel();
		header.setSize(396, 30);
		header.setLayout(null);
		header.setLocation(2, 2);
		header.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudAlterar()));
		header.setBorder(null);
		panel.add(header);

		JLabel lbHeader = new JLabel("Redefinir Senha");
		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lbHeader.setBounds(0, 0, 396, 30);
		lbHeader.setFont(UtilitarioTela.getFont(14));
		lbHeader.setForeground(UtilitarioTela.getFontColorCrud());
		lbHeader.setBorder(new BordaSombreada(false, true, false, false));
		header.add(lbHeader);

		meio = new JPanel();
		meio.setSize(396, 188);
		meio.setLayout(null);
		meio.setLocation(2, 30);
		meio.setBackground(new Color(232, 234, 239));
		panel.add(meio);

		JLabel lbNome = new JLabel("Nome : " + usuario.getNome());
		lbNome.setBounds(20, 30, 300, 20);
		lbNome.setFont(UtilitarioTela.getFont(14));
		lbNome.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbNome);
		msg = new JPanel();
		msg.setSize(396, 35);
		msg.setLocation(0, 70);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);

		JButton confirmar = new JButton("Redefinir");
		confirmar.setSize(150, 30);
		confirmar.setLocation(20, 130);
		confirmar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud
				.getModoCrudNovo()));
		confirmar.setFocusPainted(false);
		confirmar.setIcon(new ImageIcon(Menssage.class
				.getResource("/imagem/salvar.png")));
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (confirmar()) {
					Menssage.setMenssage(
							"Senha Redefinida",
							"Senha Redefinida!\nNo proximo Login irá pedir a nova senha!"
									,
							ParametroCrud.getModoCrudDeletar(), meio);
					dialog.setVisible(false);
				}
			}
		});

		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150, 30);
		cancelar.setLocation(223, 130);
		cancelar.setBackground(UtilitarioTela.getFundoLocalizar());
		cancelar.setFocusPainted(false);
		cancelar.setIcon(new ImageIcon(Menssage.class
				.getResource("/imagem/cancelBlack.png")));
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
				dialog.setVisible(false);
			}
		});

		meio.add(confirmar);
		meio.add(cancelar);

		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}

	public void cancelar() {
		this.confirmado = false;
	}

	public void msgErro(String erro) {
		msg.removeAll();
		lblMsg = new JLabel(erro);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(0, 0, 396, 35);
		lblMsg.setFont(UtilitarioTela.getFont(14));
		lblMsg.setForeground(UtilitarioTela.getFontColorCrud());
		msg.add(lblMsg);
		msg.setBackground(UtilitarioTela.getColorErro());
		msg.repaint();
	}

	public boolean confirmar() {
		boolean confirmado = true;
		MenssageConfirmacao.setMenssage("Redefinir Senha",
				"Confirma A redefinição da senha?",
				ParametroCrud.getModoCrudAlterar(), meio);
		confirmado = MenssageConfirmacao.isConfirmado();
		this.confirmado = confirmado;
		if (confirmado) {
			EntityManagerLocal.begin();
			usuario.setSenha("123");
			EntityManagerLocal.merge(usuario);
			EntityManagerLocal.commit();
		}
		return confirmado;
	}

	public boolean getConfirmado() {
		return this.confirmado;
	}

	public JTextField getTxNome() {
		return txNome;
	}

}
