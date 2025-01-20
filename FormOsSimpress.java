// 143,188,143


/*
 *Número Impressora: HCPR-PR-354
Ticket Chamado GLPI: 2025012794
Técnico Responsável: Nathalia Rodrigues Molina Parra
Data: 20/01/2025 Hora: 09:12:54
Local: HC
Anexo: Prédio Central
Setor / Sala:  Recepção Raio X
Andar:  2º
Número de série: BRBSS2L0GL
Descrição da falha: impressora não liga, ocorre error 49.38.03
 */

package main;

//bibliotecas

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class FormOsSimpress extends JPanel implements ActionListener{
	
	// Jlabels - Rótulos
		JLabel title; // titulo
		JLabel lblNumImpressora; // Nome da Impressora HCPR-PR
		JLabel LblNumGlpi; // Ticket Chamado GLPI
		JLabel lblTecnico; // Técnico Responsável
		JLabel lblAnexo; // Anexo
		JLabel lblData; // Data
		JLabel lblHora; // Hora
		JLabel lblAndar; // Andar
		JLabel lblSala; // Sala
		JLabel localLbl; // Local
		JLabel lblAlert; // mensagem de alerta quando o usuário esquece de preencer algum dado
		JLabel lblSucess; // mensagem de quando o forumário é copiado com sucesso
		JLabel lblQualCExt; // Qual? (referente a Casa Externa)
		JLabel lblFalha; // Falha
		
		// JFields - Caixas de Texto
		JTextField txtNumImpressora; //número da impressora
		JTextField txtNumGlpi; // número do chamado no GLPI
		JTextField txtSala; // informar o setor e a sala
		JTextArea txtAFalha; // área de texto para descrição da falha
		
		//JRadioButtons - botões de seleção do tipo rádio
		JRadioButton radioHc, radioCasasExternas; // Local Hc / Casas Externas
		
		// JComboBox - caixa de seleção do tipo drop down
		JComboBox<String> comboBoxAnexos; // seleção de anexos
		JComboBox<String> comboBoxAndarHc; // seleção de andares do Hc
		JComboBox<String> comboBoxAndarCExt; // seleção dos andares das casas externas
		JComboBox<String> comboBoxTecnico; // seleção dos técnicos
		JComboBox<String> comboBoxCasasExternas; // seleção das casas externas
		
		// Cor Personalizada
		Color color2 = new Color(240,230,140); // cor Dark Khaki
		
		// Fontes
		Font fontTitle = new Font("Candara",Font.BOLD,25); // tamanho 25 (titulo)
		Font fontText = new Font("Candara",Font.BOLD,17); // tamanho 17 (texto geral)
		Font fontButton = new Font("Candara",Font.BOLD,15); // tamanho 15 (botões)
		
		// Objeto Date
		Date dataHora = new Date();
		
		// Formatar Data
		SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatadorData.format(dataHora);

		// Formatador da Hora
		SimpleDateFormat formatadorHora;
		String horaFormatada;
		
		// String do texto que será copiado do formulário
		String textoParaCopiar;

		// Grupos de botões de rádio
		ButtonGroup groupConection = new ButtonGroup(); // grupo tipo conexão (Cabo / Wifi)
		ButtonGroup groupLocal = new ButtonGroup(); // grupo local (Hc / Casas Externas)
		
		// Botões
		JButton copiar; // copia o texto do formuário para o Ctrl + C
		JButton limpar; // limpa o forumário e limpa seleção Ctr + C
		
		// Strings
		String tipoConexao; // escreve o tipo de conexão no form (Cabo ou Wifi)
		String localStr=""; // escreve Hc ou Casa Externa no form
		
		// Booleanos
		boolean validationFieldsOk = false; // valida se os campos foram preenchidos corretamente
		boolean isHc = false; // indica se o local selecionado é o Hc ou não
		boolean isCExt = false; // indica se o local selecionado é uma Casa Externa ou não
	
	public FormOsSimpress() {
		
		this.setBackground(color2); // cor personalizada de fundo
		this.setLayout(null); // centraliza tela
		
		lblHora = new JLabel(); // instancia label da Hora
		makeFields(); // desenha componentes no form
		
		
	}
	
	public void makeFields() {
		
		// configurações titulo
		title = new JLabel("Abertura de Chamado para Simpress"); // instancia e coloca texto
		title.setFont(fontTitle); // fonte
		title.setBounds(45,15, 450, 40); // coordenadas e taanho
		title.setForeground(Color.black); // cor da fonte
		this.add(title); // add o componente ao painel
		
		// configurações label impressora
		lblNumImpressora = new JLabel("Número Impressora: HCPR-PR-");
		lblNumImpressora.setFont(fontText);
		lblNumImpressora.setBounds(30,100, 350, 40);
		lblNumImpressora.setForeground(Color.black);
		this.add(lblNumImpressora);
		
		// configurações caixa de texto do número da impressora
		txtNumImpressora = new JTextField();
		txtNumImpressora.setFont(fontText);
		txtNumImpressora.setBounds(260,100, 50, 30);
		this.add(txtNumImpressora);
		
		// configurações label número chamado GLPI
		LblNumGlpi = new JLabel("Ticket Chamado GLPI: ");
		LblNumGlpi.setFont(fontText);
		LblNumGlpi.setBounds(30,140, 300, 35);
		LblNumGlpi.setForeground(Color.black);
		this.add(LblNumGlpi);
				
		// Configurações caixa de texto número chamado GLPI
		txtNumGlpi = new JTextField();
		txtNumGlpi.setFont(fontText);
		txtNumGlpi.setBounds(200,140, 95, 30);
		this.add(txtNumGlpi);
		

		// configurações label técnico
		lblTecnico = new JLabel("Técnico Responsável: ");
		lblTecnico.setFont(fontText);
		lblTecnico.setBounds(30,180, 350, 35);
		lblTecnico.setForeground(Color.black);
		this.add(lblTecnico);
		
		// Configração ComboBox Técnicos
		String[] tec = {"-------", "Erik Hass Dugoinski", "Gilberto Soares Domingues", 
		"Katiely Vitória R. dos Anjos Andrade", "Maycon Nogueira Fernandes",
		"Nathalia Rodrigues Molina Parra", "Philipe Eduardo Pires Santos"}; // rounds options
		comboBoxTecnico = new JComboBox<String>(tec); // creates JComboBox
		comboBoxTecnico.setBounds(190, 180, 270, 30); // sets coordinates and size
		comboBoxTecnico.setFont(fontText); // sets font color
		this.add(comboBoxTecnico); // adds numberOfRounds to this
		
		// atualiza a hora de acordo com o horário do sistema
		getTime();
		
		// configurações label data
		lblData = new JLabel("Data: " + dataFormatada);
		lblData.setFont(fontText);
		lblData.setBounds(30,220, 350, 35);
		lblData.setForeground(Color.black);
		this.add(lblData);
		
		// configurações label hora
		lblHora = new JLabel("Hora: " + horaFormatada);
		lblHora.setFont(fontText);
		lblHora.setBounds(250,220, 350, 35);
		lblHora.setForeground(Color.black);
		this.add(lblHora);
		
		// configurações label Descrição da Falha
		lblFalha = new JLabel("Descrição da falha: ");
		lblFalha.setFont(fontText);
		lblFalha.setBounds(30,260, 250, 30);
		lblFalha.setForeground(Color.black);
		this.add(lblFalha);
		
		// configurações textArea Descrição da Falha
		txtAFalha = new JTextArea();
		txtAFalha.setFont(fontText);
		txtAFalha.setBounds(30,290, 370, 120);
		txtAFalha.setForeground(Color.black);
		txtAFalha.setLineWrap(true);
		this.add(txtAFalha);
		
		// configurações label local
		localLbl = new JLabel("Local: ");
		localLbl.setFont(fontText);
		localLbl.setBounds(30,420, 350, 35);
		localLbl.setForeground(Color.black);
		this.add(localLbl);
		
		
		
		// Configurações radio button Hc
		radioHc = new JRadioButton("HC");
		radioHc.setFont(fontText);
		radioHc.setBounds(80,420, 70, 35);
		radioHc.setBackground(color2);
		radioHc.setForeground(Color.black);
		radioHc.addActionListener(this);
		groupLocal.add(radioHc);
		this.add(radioHc);
		
		// Configurações radio button Casas Externas
		radioCasasExternas = new JRadioButton("Casas Externas");
		radioCasasExternas.setFont(fontText);
		radioCasasExternas.setBounds(150,420, 150, 35);
		radioCasasExternas.setBackground(color2);
		radioCasasExternas.setForeground(Color.black);
		radioCasasExternas.addActionListener(this);
		groupLocal.add(radioCasasExternas);
		this.add(radioCasasExternas);
		
		// configurações label anexo
		lblAnexo = new JLabel("Anexo: ");
		lblAnexo.setFont(fontText);
		lblAnexo.setBounds(30,460, 80, 35);
		lblAnexo.setForeground(Color.black);
		lblAnexo.setVisible(false);
		this.add(lblAnexo);
		
		// configurações combobox anexos
		String[] anex = {"-------", "Prédio Central", "A - Maternindade", "B - Ambulatórios",
		"C - Farmácia", "D - Anatomia Patológica", "E - Salas de Aula",
		"G - Laboratório", "H - UTI"}; // rounds options
		comboBoxAnexos = new JComboBox<String>(anex); // creates JComboBox
		comboBoxAnexos.setBounds(100, 460, 200, 30); // sets coordinates and size
		comboBoxAnexos.setFont(fontText); // sets font color
		comboBoxAnexos.setVisible(false);
		this.add(comboBoxAnexos); // adds numberOfRounds to this
		
		// configurações label andar
		lblAndar = new JLabel("Andar: ");
		lblAndar.setFont(fontText);
		lblAndar.setBounds(310,460, 80, 35);
		lblAndar.setForeground(Color.black);
		lblAndar.setVisible(false);
		this.add(lblAndar);
		
		// configurações combobox andarHc
		String[] andarHc = {"-------", "Térreo","1º", "2º", "3º", "4º", "5º", "6º", "7º", "8º", "9º", "10º", "11º", 
		"12º", "13º", "14º", "15º", "16º", "17º"}; // rounds options
		comboBoxAndarHc = new JComboBox<String>(andarHc); // creates JComboBox
		comboBoxAndarHc.setBounds(360, 460, 90, 30); // sets coordinates and size
		comboBoxAndarHc.setFont(fontText); // sets font color
		comboBoxAndarHc.setVisible(false);
		this.add(comboBoxAndarHc); // adds numberOfRounds to this
		
		// configurações combobox andar Casas Externas
		String[] andarCExt = {"-------", "Térreo","1º", "2º", "3º"}; // rounds options
		comboBoxAndarCExt = new JComboBox<String>(andarCExt); // creates JComboBox
		comboBoxAndarCExt.setBounds(360, 460, 90, 30); // sets coordinates and size
		comboBoxAndarCExt.setFont(fontText); // sets font color
		comboBoxAndarCExt.setVisible(false);
		this.add(comboBoxAndarCExt); // adds numberOfRounds to this
		
		// configurações label Qual referente a Casas Externas
		lblQualCExt = new JLabel("Qual? ");
		lblQualCExt.setFont(fontText);
		lblQualCExt.setBounds(30,460, 300, 35);
		lblQualCExt.setVisible(false);
		lblQualCExt.setForeground(Color.black);
		this.add(lblQualCExt);
		
		// configurações combobox Casas Externas
		String[] casas = {"-------", "Alergologia", "Barracão - Almoxarifado", "Biobanco",
		"CENEP", "Centro da Mama", "Cirurgia Experimental", "CPN - Nefrologia", "FAPE - Farmácia Especial", "HMVFA", 
		"Hotelaria", "Ouvidoria", "Patrimônio", "Pipa Encantada", "Puericultura", "Sam 30 - Pós Covid", 
		"Saúde Mental", "SEMPR", "UEP - Endocrino Pediatria", "USOST", "OUTRA"}; // rounds options
		comboBoxCasasExternas = new JComboBox<String>(casas); // creates JComboBox
		comboBoxCasasExternas.setBounds(90, 460, 210, 30); // sets coordinates and size
		comboBoxCasasExternas.setFont(fontText); // sets font color
		comboBoxCasasExternas.setVisible(false);
		this.add(comboBoxCasasExternas); // adds numberOfRounds to this
		
		// configurações label setor / sala
		lblSala = new JLabel("Setor / Sala: ");
		lblSala.setFont(fontText);
		lblSala.setBounds(30,500, 130, 35);
		lblSala.setForeground(Color.black);
		lblSala.setVisible(false);
		this.add(lblSala);
		
		// configurações caixa de texto setor / sala
		txtSala = new JTextField("");
		txtSala.setFont(fontText);
		txtSala.setBounds(130,500, 250, 30);
		txtSala.setForeground(Color.black);
		txtSala.setVisible(false);
		this.add(txtSala);
		
		// configurações botão copiar
		copiar = new JButton("Copiar");
		copiar.setBounds(70, 560, 100, 35);
		copiar.setFont(fontText);
		copiar.setForeground(Color.black);
		copiar.addActionListener(this);
		this.add(copiar);
		
		// configurações botão limpar
		limpar = new JButton("Limpar");
		limpar.setBounds(330, 560, 100, 35);
		limpar.setFont(fontText);
		limpar.setForeground(Color.black);
		limpar.addActionListener(this);
		this.add(limpar);
		
		// configurações label de alerta
		lblAlert = new JLabel("Preencha todos os campos");
		lblAlert.setFont(fontText);
		lblAlert.setBounds(145,600, 200, 35);
		lblAlert.setForeground(Color.red);
		lblAlert.setVisible(false);
		this.add(lblAlert);
		
		// configurações label de mensagem de sucesso
		lblSucess= new JLabel("Copiado com sucesso!");
		lblSucess.setFont(fontText);
		lblSucess.setBounds(160,600, 200, 35);
		lblSucess.setForeground(new Color (0,128,0));
		lblSucess.setVisible(false);
		this.add(lblSucess);
		
		// torna frame visivel após todos os componentes 
		this.setVisible(true);
	}
	
	
	// Faz a atualização da hora quando o botão limpar é acionado
	public void getTime() {
			// Formata a hora
			Date dataHora = new Date();
			formatadorHora = new SimpleDateFormat("HH:mm:ss");
			horaFormatada = formatadorHora.format(dataHora);
			lblHora.setText("Hora: " + horaFormatada);
		}

	// Habilita campos de localização do HC
	public void setHc() {
		
		// quando isHc é verdadeira, isCEx se torna falsa
		isHc = true;
		isCExt = false;
		
		// Habilita Campos de HC
		lblAnexo.setVisible(true);
		comboBoxAnexos.setVisible(true);
		lblAndar.setVisible(true);
		comboBoxAndarHc.setVisible(true);
		
		// Desabilita Campos de Casas Externas
		comboBoxAndarCExt.setVisible(false);
		comboBoxCasasExternas.setVisible(false);
		lblQualCExt.setVisible(false);
		lblSala.setVisible(true);
		txtSala.setVisible(true);
		localStr = "HC";
	}	

	public void setCasaExterna() {
		
		// quando isCEx é verdadeira, isHc se torna falsa
		isCExt = true;
		isHc = false;
		
		// Desabilita Campos de Hc
		lblAnexo.setVisible(false);
		comboBoxAnexos.setVisible(false);
		comboBoxAndarHc.setVisible(false);
		
		// Habilita Campos de Casas Externas
		lblAndar.setVisible(true);
		comboBoxAndarCExt.setVisible(true);
		comboBoxCasasExternas.setVisible(true);
		lblQualCExt.setVisible(true);
		lblSala.setVisible(true);
		txtSala.setVisible(true);
		localStr="Casa Externa - ";

	}
	
	// Checa se todos os dados do formulário foram preenchidos 
	public void checkFields() {
		
		// se Hc ou Casas Externas (radio) não for selecionada, a validação falha
		if(isHc == false && isCExt == false) {
			lblAlert.setVisible(true);
			lblSucess.setVisible(false);
			validationFieldsOk = false;
		}
		
		// se o radio Hc foi selecionado, checa os campos
		if (isHc && isCExt == false) {
			
			// verifica se existe algum campo em branco ou não selecionado
			if  (
				txtNumImpressora.getText().isEmpty()
				|| (txtNumGlpi.getText().isEmpty())
				|| (comboBoxTecnico.getSelectedItem() == "-------") 
				|| (comboBoxAnexos.getSelectedItem() == "-------")
				|| (comboBoxAndarHc.getSelectedItem() == "-------")
				|| (txtSala.getText().isEmpty())
				|| (txtAFalha.getText().isEmpty())
					)

				// caso algum dado não foi preenchido, 
				//aparece mensagem de alerta, mensagem de sucesso é apagada e a validação é negada
				{
				lblAlert.setVisible(true);
				lblSucess.setVisible(false);
				validationFieldsOk = false;
				}
			// caso todos os dados tenham sido preenchidos, 
			//aparece mensagem de sucesso, a mensagem de erro é apagada e a validação é aceita
			else {
				lblSucess.setVisible(true);
				lblAlert.setVisible(false);
				validationFieldsOk = true;
				}
		} // end if isHc
		
		// se o radio das Casas Externas foi selecionado
		if (isHc ==false && isCExt == true) {
			
			// verifica se existe algum campo em branco ou não selecionado
			if  (
				txtNumImpressora.getText().isEmpty()
				|| (txtNumGlpi.getText().isEmpty())
				|| (comboBoxTecnico.getSelectedItem() == "-------") 
				|| (comboBoxCasasExternas.getSelectedItem() == "-------") 
				|| (comboBoxAndarCExt.getSelectedItem() == "-------")
				|| (txtSala.getText().isEmpty())
				|| (txtAFalha.getText().isEmpty())
				
				)

				// caso algum dado não foi preenchido, aparece mensagem de erro, mensagem de sucesso é apagada
				// e a validação é negada
				{
				lblAlert.setVisible(true);
				lblSucess.setVisible(false);
				validationFieldsOk = false;
				}
			
			// caso todos os dados tenham sido preenchidos, aparece mensagem de sucesso, 
			//a mensagem de erro é apagada e a validação é aceita
			else {
				lblSucess.setVisible(true);
				lblAlert.setVisible(false);
				validationFieldsOk = true;
				}
		} // end if isCExt
		
			
	} // end checkFields()

	// Copia texto do form para a área de transferência (Ctrl + C)
	// Copia texto do form para a área de transferência (Ctrl + C)
	 public void copy() { 
	             
		 // ao copiar a mensagem, significa que todos os dados foram validados
		 // habilita mensagem de sucesso
		 // desabilita mensagem de erro
	 	lblAlert.setVisible(false);
	 	lblSucess.setVisible(true);
	 		
	 	// pega informações dos campos do form para copiar
	    textoParaCopiar = title.getText() + "\n \n" + lblNumImpressora.getText() + txtNumImpressora.getText()
	    + "\n" + LblNumGlpi.getText() + txtNumGlpi.getText()
	    + "\n" + lblTecnico.getText() + comboBoxTecnico.getSelectedItem()
	    + "\n" + lblData.getText()  + " " + lblHora.getText()
	    + "\n" + lblFalha.getText() + " " + txtAFalha.getText()
 	    + "\n" + localLbl.getText()  + localStr;
	             
	    // se Hc é selecionado, pega os dados em questão para copiar
	    if (radioHc.isSelected()) {
	            	 
	    	textoParaCopiar = textoParaCopiar 
	    	+ "\n" + lblAnexo.getText() + comboBoxAnexos.getSelectedItem()
	        + "\n" + lblSala.getText() + " " + txtSala.getText()
	        + "\n" + lblAndar.getText() + " " +comboBoxAndarHc.getSelectedItem();
	       }
	             
	     // se Casas Externas é selecionado, pega os dados em questão para copiar
	     if (radioCasasExternas.isSelected()) {
	    	 
	    	 textoParaCopiar = textoParaCopiar 
	         + comboBoxCasasExternas.getSelectedItem()
	         + "\n" + lblSala.getText() + " " + txtSala.getText()
	         + "\n" + lblAndar.getText() + " " +comboBoxAndarCExt.getSelectedItem();
	         }
	             
	      // copa dados para a área de transferência (Cttrl + C)
	      StringSelection selection = new StringSelection(textoParaCopiar);
	      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	      clipboard.setContents(selection, selection);   
		 }

	 // Limpa todos os campos do painel e limpa a área de transferência (Ctrl + C)
	 // Limpa todos os campos do painel e limpa a área de transferência (Ctrl + C)
	public void reset() {
			
		// Atualiza a hora
		getTime();
			
		// Limpa seleção dos grupos de botões
		groupConection.clearSelection();
		groupLocal.clearSelection();
			
		// Limpa texto dos campos de texto JFields
		txtNumGlpi.setText("");
		txtNumImpressora.setText("");
		txtSala.setText("");
		txtAFalha.setText("");
			
		// Limpa área de transferência (Ctrl + C)
		textoParaCopiar = "";
		StringSelection selection = new StringSelection(textoParaCopiar);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	        
	    // Desabilita todos os campos de Hc e Casas Externas
	    comboBoxAnexos.setVisible(false);
	    comboBoxCasasExternas.setVisible(false);
	    comboBoxAndarHc.setVisible(false);
	    comboBoxAndarCExt.setVisible(false);
	    lblAnexo.setVisible(false);
	    lblAndar.setVisible(false);
	    lblSala.setVisible(false);
	    lblQualCExt.setVisible(false);
	    txtSala.setVisible(false);
	        
	    // Retorna ComboBoxes para seleção padrão
	    comboBoxTecnico.setSelectedItem("-------");
	    comboBoxAndarCExt.setSelectedItem("-------");
	    comboBoxAndarHc.setSelectedItem("-------");
	    comboBoxAnexos.setSelectedItem("-------");
	    comboBoxCasasExternas.setSelectedItem("-------");
	        
	    // Apaga mensagens de erro e de sucesso
	    lblAlert.setVisible(false);
	    lblSucess.setVisible(false);
	        
	    // Seta booleanos para falso
	    isHc = false;
	    isCExt = false;
			
		}
	 
	// Eventos de botão
  	// Eventos de botão
  	
  	@Override
	public void actionPerformed(ActionEvent e) {
  	  	// Eventos botão copiar
  		if(e.getSource() == copiar) {
			
			checkFields(); // valida se todos os campos foram preenchidos corretamente
			if (validationFieldsOk) { // se for validado
				copy(); // copiar texto para a área de seleção
			}
		}

		
		if (radioHc.isSelected()) {
			setHc();

		}
		
		if (radioCasasExternas.isSelected()) {
			setCasaExterna();
		}
		
		
		// botão limpar chama método reset
		if (e.getSource() == limpar) {
			
			reset();
			
		}	
			
		} 
  	
}