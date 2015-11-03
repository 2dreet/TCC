package dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.omg.CORBA.TCKind;

import componente.ComboBox;
import componente.Menssage;
import componente.MenssageConfirmacao;
import dao.BanimentoDao;
import dao.EntityManagerLocal;
import dao.MarcaDao;
import dao.ModalidadeDao;
import dao.PerifericoDao;
import utilitario.BordaSombreada;
import utilitario.ParametroCrud;
import utilitario.Parametros;
import utilitario.UtilitarioTela;
import entidade.Banimento;
import entidade.Jogador;
import entidade.JogadorBanimento;
import entidade.JogadorPeriferico;
import entidade.Marca;
import entidade.Modalidade;
import entidade.Periferico;

public class DialogCrudJogadorPeriferico {
	
	private boolean confirmado;
	private JogadorPeriferico jogadorPerifericoSelecionado;
	private JLabel lblMsg;
	private JPanel msg;
	private JPanel meio;
	private Jogador jogadorSelecionado;
	private ComboBox cbMarca;
	private ComboBox cbPeriferico;
	
	public void crudJogadorPeriferico(JogadorPeriferico jogadorPerifericoSelecionado, int modoCrud, JPanel painelPai, Jogador jogadorSelecionado){
		this.jogadorPerifericoSelecionado = jogadorPerifericoSelecionado;
		this.jogadorSelecionado = jogadorSelecionado;
		
		JDialog dialog = new JDialog(Parametros.getPai(), true);
		dialog.setUndecorated(true);
		dialog.setLayout(null);
		dialog.setSize(400, 220);
		dialog.getContentPane().setBackground(new Color(232, 234, 239));
		dialog.setLocationRelativeTo(painelPai);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BordaSombreada(new Color(46, 49, 56), new Color(102, 102, 102)));
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
		header.setBackground(UtilitarioTela.getColorCrud(modoCrud));
		header.setBorder(null);
		panel.add(header);
		
		String textoHeader = "";
		if (modoCrud == ParametroCrud.getModoCrudNovo()) {
			textoHeader = "Adicionar Periférico";
		} else if (modoCrud == ParametroCrud.getModoCrudAlterar()) {
			textoHeader = "Alterar Periférico";
		} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			textoHeader = "Deletar Periférico";
		} 
		
		JLabel lbHeader = new JLabel(textoHeader);
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
		
		JLabel lbMarca = new JLabel("Marca:");
		lbMarca.setBounds(5, 5, 80, 30);
		lbMarca.setFont(UtilitarioTela.getFont(14));
		lbMarca.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbMarca);
		
		cbMarca = new ComboBox(new Dimension(250, 30));
		DefaultComboBoxModel comboModel = (DefaultComboBoxModel) cbMarca.getModel();
		comboModel.removeAllElements();
		List<Marca>list = MarcaDao.getListaMarca();
		for (Marca m : list){
			comboModel.addElement(m);
		}
		cbMarca.setLocation(90, 5);
		meio.add(cbMarca);
		
		JLabel lbPeri = new JLabel("Periférico:");
		lbPeri.setBounds(5, 40, 80, 30);
		lbPeri.setFont(UtilitarioTela.getFont(14));
		lbPeri.setForeground(UtilitarioTela.getFontColorCrud());
		meio.add(lbPeri);
		
		cbPeriferico = new ComboBox(new Dimension(250, 30));
		DefaultComboBoxModel comboModelPeri = (DefaultComboBoxModel) cbPeriferico.getModel();
		comboModelPeri.removeAllElements();
		List<Periferico>listP = PerifericoDao.getListaPeriferico();
		for (Periferico m : listP){
			comboModelPeri.addElement(m);
		}
		cbPeriferico.setLocation(90, 40);
		meio.add(cbPeriferico);
		
		msg = new JPanel();
		msg.setSize(396, 35);
		msg.setLocation(0, 80);
		msg.setLayout(null);
		msg.setBackground(null);
		meio.add(msg);
		
		String texto = "";
		 if (modoCrud != ParametroCrud.getModoCrudDeletar()) {
			texto = "Salvar";
		}else{
			texto = "Deletar";
		} 
		
		
		JButton confirmar = new JButton(texto);
		confirmar.setSize(150,30);
		confirmar.setLocation(20, 130);
		confirmar.setBackground(UtilitarioTela.getColorCrud(ParametroCrud.getModoCrudNovo()));
		confirmar.setFocusPainted(false);
		 if (modoCrud != ParametroCrud.getModoCrudDeletar()) {
			 confirmar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/salvar.png")));
		 }else{
			 confirmar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/delete.png")));
		 }
		confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(confirmar(modoCrud)){
					dialog.setVisible(false);
				}
			}
		});
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setSize(150,30);
		cancelar.setLocation(223, 130);
		cancelar.setBackground(UtilitarioTela.getFundoLocalizar());
		cancelar.setFocusPainted(false);
		cancelar.setIcon(new ImageIcon(Menssage.class.getResource("/imagem/cancelBlack.png")));
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelar();
				dialog.setVisible(false);
			}
		});
		
		meio.add(confirmar);
		meio.add(cancelar);
		
		
		if(modoCrud == ParametroCrud.getModoCrudDeletar()){
			cbMarca.setEnabled(false);
			cbPeriferico.setEnabled(false);
		}
		
		if(jogadorPerifericoSelecionado != null){
			cbMarca.setSelectedItem(jogadorPerifericoSelecionado.getMarca());
			cbPeriferico.setSelectedItem(jogadorPerifericoSelecionado.getPeriferico());
		}
		
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
	}
	
	public void cancelar(){
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
	
	public boolean confirmar(int modoCrud){
		boolean confirmado = true;
		if (modoCrud != ParametroCrud.getModoCrudDeletar()) {
			if(cbMarca.getSelectedItem() == null || cbMarca.getSelectedItem().equals("")){
				msgErro("Deve Cadastrar Marcas!");
				return false;
			} 
			if(cbPeriferico.getSelectedItem() == null || cbPeriferico.getSelectedItem().equals("")){
				msgErro("Deve Cadastrar Periféricos!");
				return false;
			} 
		}
		if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
			MenssageConfirmacao.setMenssage("Deletar Periférico", "Confirma a delecao do Periférico do Jogador?",ParametroCrud.getModoCrudDeletar(), meio);
			confirmado = MenssageConfirmacao.isConfirmado();
		}
		
		this.confirmado = confirmado;
		if(confirmado){
			EntityManagerLocal.begin();
			EntityManagerLocal.flush();
			if (modoCrud == ParametroCrud.getModoCrudNovo()) {
				JogadorPeriferico jogadorPeriferico= new JogadorPeriferico();
				jogadorPeriferico.setJogador(jogadorSelecionado);
				jogadorPeriferico.setMarca((Marca) cbMarca.getSelectedItem());
				jogadorPeriferico.setPeriferico((Periferico) cbPeriferico.getSelectedItem());
				EntityManagerLocal.persist(jogadorPeriferico);
			} else if(modoCrud == ParametroCrud.getModoCrudAlterar()){
				jogadorPerifericoSelecionado.setMarca((Marca) cbMarca.getSelectedItem());
				jogadorPerifericoSelecionado.setPeriferico((Periferico) cbPeriferico.getSelectedItem());
				EntityManagerLocal.merge(jogadorPerifericoSelecionado);
			} else if (modoCrud == ParametroCrud.getModoCrudDeletar()) {
				EntityManagerLocal.delete(jogadorPerifericoSelecionado);
			}
			EntityManagerLocal.commit();
		}
		return confirmado;
	}
	
	public boolean getConfirmado(){
		return this.confirmado;
	}
	
}
