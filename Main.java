package main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// classe Principal
public class Main {
	
	// constantes tamanho da tela
	final int WIDTH= 500;
	final int HEIGHT = 720;
	
	JFrame frame; // Frame
	JTabbedPane abas = new JTabbedPane(); // Painel com as abas
	
	// construtor
	public Main() {
		
		FormImpressorasN3 form2 = new FormImpressorasN3(); // formulário para abrir chamado para Simpress
		FormOsSimpress form1 = new FormOsSimpress(); // formulário para enviar template de impressora para N3
		frame = new JFrame("Gerenciador de Templates Service Desk"); // instanciar e titulo
		frame.setSize(WIDTH, HEIGHT); // tamanho
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fechar o programa no X
		frame.add(BorderLayout.CENTER, abas); // centralizar Layout
		
		abas.addTab("Abrir OS Simpress", form1); // adicionar form1 nas abas
		
		abas.addTab("Template Impressoras N3", form2); // adicionar form2 nas abas
		
		frame.setVisible(true); // tornando visivel o frame
		

	}

	// método main para rodar o programa
	public static void main(String[] args) {
		Main run = new Main();

	}

}