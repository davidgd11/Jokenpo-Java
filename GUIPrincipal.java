package br.com.fiap.main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.fiap.bean.GUIJogos;

@SuppressWarnings("serial")
public class GUIPrincipal extends JFrame{
	
	private Container contentPane;
	private JMenuBar mnBarra;
	private JMenu mnArquivo;
	private JMenuItem miSair, miJogos;
	
	public GUIPrincipal() {
		inicializarComponentes();
		definirEventos();
	}
	
	public void inicializarComponentes() {
		setTitle("Janela Principal");
		setBounds(0,0,600,400);
		contentPane = getContentPane();
		
		mnBarra = new JMenuBar();
		mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		miJogos = new JMenuItem("Jogos");
		miSair = new JMenuItem("Sair");
		
		setJMenuBar(mnBarra);
		mnBarra.add(mnArquivo);
		mnArquivo.add(miJogos);
		mnArquivo.add(miSair);
	}
	
	public void definirEventos() {
		
		miSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		miJogos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIJogos jogos = new GUIJogos();
				contentPane.removeAll();
				contentPane.add(jogos);
				contentPane.validate();
			}
		});
	}
	
	public static void main(String[] args) {
		GUIPrincipal frame = new GUIPrincipal();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(tela.width - frame.getWidth()/2, (tela.height - frame.getHeight()/2));
		frame.setVisible(true);
	}

}
