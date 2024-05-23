package br.com.fiap.bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GUIJogos extends JPanel{
	
	private JLabel lbImagem, lbResultado, lbComputador, lbPlacar;
	private JTextArea taResultado;
	private JRadioButton rbPedra, rbPapel, rbTesoura;
	private ButtonGroup buttonGroup;
	private JButton btJogar;
	private ImageIcon imagem1, imagem2;
	private int placarJogador = 0;
	private int placarComputador = 0;
	
	public GUIJogos() {
		inicializarComponentes();
		definirEventos();
		esconderImagem();
	}
	
	private void inicializarComponentes() {
		setLayout(null);
		setBackground(Color.white);
		
		
		rbPedra = new JRadioButton("Pedra");
		rbPedra.setFont(new Font("Verdana", Font.BOLD, 12));
		rbPedra.setForeground(Color.black);
		rbPedra.setBackground(getBackground());
		rbPedra.setSelected(true);
		
		rbPapel = new JRadioButton("Papel");
		rbPapel.setFont(new Font("Verdana", Font.BOLD, 12));
		rbPapel.setForeground(Color.black);
		rbPapel.setBackground(getBackground());
		rbPapel.setSelected(true);
		
		rbTesoura = new JRadioButton("Tesoura");
		rbTesoura.setFont(new Font("Verdana", Font.BOLD, 12));
		rbTesoura.setForeground(Color.black);
		rbTesoura.setBackground(getBackground());
		rbTesoura.setSelected(true);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rbPedra);
		buttonGroup.add(rbPapel);
		buttonGroup.add(rbTesoura);
		btJogar = new JButton("Jogar");
		btJogar.setForeground(Color.white);
		btJogar.setBackground(Color.black);
		
		imagem2 = new ImageIcon(getClass().getResource("images/branco.png"));
		lbImagem = new JLabel(imagem2);
		lbComputador = new JLabel(imagem2);
		lbResultado = new JLabel("");
		lbPlacar = new JLabel("0 x 0");
		
		rbPedra.setBounds(20, 280, 100, 25);
		rbPapel.setBounds(20, 300, 100, 25);
		rbTesoura.setBounds(20, 320, 100, 25);
		btJogar.setBounds(220, 340, 140, 30);
		
		lbImagem.setBounds(20, 40, 180, 180);
		lbComputador.setBounds(300, 40, 180, 180);
		lbResultado.setBounds(220, 200, 300, 250);
		lbPlacar.setBounds(20, 20, 180, 180);
		
		add(rbPedra);
		add(rbPapel);
		add(rbTesoura);
		add(btJogar);
		add(lbImagem);
		add(lbComputador);
		add(lbResultado);
		add(lbPlacar);		
		
	}
	
	private void definirEventos() {
		btJogar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (rbPedra.isSelected()) {
						imagem1 = new ImageIcon(getClass().getResource("images/pedra.png"));
						lbImagem.setIcon(imagem1);
					} else if(rbPapel.isSelected()){
						imagem1 = new ImageIcon(getClass().getResource("images/papel.png"));
						lbImagem.setIcon(imagem1);
					} else {
						imagem1 = new ImageIcon(getClass().getResource("images/tesoura.png"));
						lbImagem.setIcon(imagem1);
					}
					
					int randomNum = (int)(Math.random() * 3);
					if(randomNum == 0) {
						imagem2 = new ImageIcon(getClass().getResource("images/pedra.png"));
						lbComputador.setIcon(imagem2);
					} else if(randomNum == 1){
						imagem2 = new ImageIcon(getClass().getResource("images/papel.png"));
						lbComputador.setIcon(imagem2);
					} else {
						imagem2 = new ImageIcon(getClass().getResource("images/tesoura.png"));
						lbComputador.setIcon(imagem2);
					}
					
					String resultado = determinarResultado(rbPedra.isSelected(), rbPapel.isSelected(), rbTesoura.isSelected(), randomNum);
                    lbResultado.setText(resultado);
                    lbResultado.setVisible(true);
                    atualizarPlacar(resultado);
                    
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),
							"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		}
	
	private String determinarResultado(boolean pedra, boolean papel, boolean tesoura, int escolhaComputador) {
        if ((pedra && escolhaComputador == 2) || (papel && escolhaComputador == 0) || (tesoura && escolhaComputador == 1)) {
        	lbResultado.setForeground(Color.green);
            return "Você ganhou!";
        } else if ((pedra && escolhaComputador == 1) || (papel && escolhaComputador == 2) || (tesoura && escolhaComputador == 0)) {
        	lbResultado.setForeground(Color.red);
            return "Você perdeu!";
        } else {
        	lbResultado.setForeground(Color.darkGray);
            return "Empate!";
        }
    }
	
	private void atualizarPlacar(String resultado) {
        if (resultado.equals("Você ganhou!")) {
            placarJogador++;
        } else if (resultado.equals("Você perdeu!")) {
            placarComputador++;
        }
        lbPlacar.setText("Placar - Jogador: " + placarJogador + " x Computador: " + placarComputador);
    }
	
	private void esconderImagem(){
		ActionListener action = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				lbComputador.setIcon(new ImageIcon(getClass().getResource("image/branco.png")));
				btJogar.setVisible(true);
				lbResultado.setVisible(false);
			}
		};
		Timer timer = new Timer(2000, action);
		timer.setRepeats(false);
		timer.start();
	}
	
		
	}
	
	
