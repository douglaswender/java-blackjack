import javax.swing.*;
import java.awt.*;

import java.awt.event.*;


/**
 * Application window.
 * Holds the menu-bar etc.
 *
 * @author David Winter
 */
public class AppWindow extends JFrame 
    implements ActionListener, ComponentListener
{
    private GamePanel gamePanel;
    private Color defaultTableColour = new Color(6, 120, 0);
    
    private JMenuItem savePlayer = new JMenuItem("Salvar perfil atual");
    private JMenuItem openPlayer = new JMenuItem("Abrir perfil existente");
    
    final int WIDTH = 600;
    final int HEIGHT = 500;

	public AppWindow()
    {
        super("Blackjack");
        
        addComponentListener(this);
        
        setVisible(true);
        setResizable(true);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        
        this.setBackground(defaultTableColour);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // menu bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu playerMenu = new JMenu("Jogador");
        JMenuItem updatePlayerDetails = new JMenuItem("Atualizar detalhes do perfil");
        playerMenu.add(updatePlayerDetails);
        playerMenu.addSeparator();
        playerMenu.add(savePlayer);
        playerMenu.add(openPlayer);
        menuBar.add(playerMenu);
        
        JMenu actionMenu = new JMenu("Ações");
        JMenuItem dealAction = new JMenuItem("Dar as cartas");
        JMenuItem hitAction = new JMenuItem("Comprar uma carta");
        JMenuItem doubleAction = new JMenuItem("Dobrar aposta");
        JMenuItem standAction = new JMenuItem("Mostrar as cartas");
        actionMenu.add(dealAction);
        actionMenu.add(hitAction);
        actionMenu.add(doubleAction);
        actionMenu.add(standAction);
        menuBar.add(actionMenu);
        
        JMenu betMenu = new JMenu("Apostar");
        JMenuItem oneChip = new JMenuItem("R$ 1");
        JMenuItem fiveChip = new JMenuItem("R$ 5");
        JMenuItem tenChip = new JMenuItem("R$ 10");
        JMenuItem twentyFiveChip = new JMenuItem("R$ 25");
        JMenuItem hundredChip = new JMenuItem("R$ 100");
        betMenu.add(oneChip);
        betMenu.add(fiveChip);
        betMenu.add(tenChip);
        betMenu.add(twentyFiveChip);
        betMenu.add(hundredChip);
        menuBar.add(betMenu);
        
        JMenu windowMenu = new JMenu("Preferências");
        JMenuItem windowTableColourMenu = new JMenuItem("Mudar a cor da mesa");
        windowMenu.add(windowTableColourMenu);
        menuBar.add(windowMenu);
        
        JMenu helpMenu = new JMenu("Ajuda");
        JMenuItem helpBlackjackRulesMenu = new JMenuItem("Regras do Blackjack");
        JMenuItem helpAboutMenu = new JMenuItem("Sobre Blackjack Java");
        helpMenu.add(helpBlackjackRulesMenu);
        helpMenu.addSeparator();
        helpMenu.add(helpAboutMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
        // keyboard shortcuts
        
        updatePlayerDetails.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_U,                                            
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        savePlayer.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        openPlayer.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_O,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));   
        dealAction.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        hitAction.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_C,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        doubleAction.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_D,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        standAction.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_F,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        oneChip.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_1,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fiveChip.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_2,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        tenChip.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_3,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        twentyFiveChip.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_4,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        hundredChip.setAccelerator(
            KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_5,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        
		// action listeners
		dealAction.addActionListener(this);
        hitAction.addActionListener(this);
        doubleAction.addActionListener(this);
        standAction.addActionListener(this);
		updatePlayerDetails.addActionListener(this);
		savePlayer.addActionListener(this);
		openPlayer.addActionListener(this);
		windowTableColourMenu.addActionListener(this);
		helpAboutMenu.addActionListener(this);
		oneChip.addActionListener(this);
        fiveChip.addActionListener(this);
        tenChip.addActionListener(this);
        twentyFiveChip.addActionListener(this);
        hundredChip.addActionListener(this);
        		
        gamePanel = new GamePanel();
        gamePanel.setBackground(defaultTableColour);
		add(gamePanel);
        
        setVisible(true);
    }

	public void actionPerformed(ActionEvent evt)
    {
        String act = evt.getActionCommand();
        
        if (act.equals("R$ 1"))
        {
            gamePanel.increaseBet(1);
        }
        else if (act.equals("R$ 5"))
        {
            gamePanel.increaseBet(5);
        }
        else if (act.equals("R$ 10"))
        {
            gamePanel.increaseBet(10);
        }
        else if (act.equals("R$ 25"))
        {
            gamePanel.increaseBet(25);
        }
        else if (act.equals("R$ 100"))
        {
            gamePanel.increaseBet(100);
        }
        else if (act.equals("Dar as cartas"))
        {
            gamePanel.newGame();
        }
        else if (act.equals("Comprar uma carta"))
        {
            gamePanel.hit();
        }
        else if (act.equals("Dobrar aposta"))
        {
            gamePanel.playDouble();
        }
        else if (act.equals("Mostrar as cartas"))
        {
            gamePanel.stand();
        }
        else if (act.equals("Atualizar detalhes do perfil"))
        {
            gamePanel.updatePlayer();
        }
        else if (act.equals("Salvar perfil atual"))
        {
            gamePanel.savePlayer();
        }
        else if (act.equals("Abrir perfil existente"))
        {
            gamePanel.openPlayer();
        }
		else if (act.equals("Mudar a cor da mesa"))
		{
		    Color tableColour = JColorChooser.showDialog(this, "Selecione a cor da mesa", defaultTableColour);
		    this.setBackground(tableColour);
		    gamePanel.setBackground(tableColour);
		    gamePanel.repaint();
		    this.repaint();
		}
		else if (act.equals("Sobre Blackjack Java"))
		{
		    String aboutText = "<html><p align=\"center\" style=\"padding-bottom: 10px;\">Written by David Winter &copy; 2006<br>Version 1.1</p><p align=\"center\" style=\"padding-bottom: 10px;\"><small>Torne-se um especialista enquanto desenvolve isso, ganhe 1000 dólares online em um jogo de Blackjack!</small></p><p align=\"center\">email: djw@davidwinter.me.uk<br>web: davidwinter.me.uk</p></html>";
		    JOptionPane.showMessageDialog(this, aboutText, "About Blackjack", JOptionPane.PLAIN_MESSAGE);
		}
		
		gamePanel.updateValues();
	}
	
	public void componentResized(ComponentEvent e)
	{
	    int currentWidth = getWidth();
	    int currentHeight = getHeight();
	    
	    boolean resize = false;
	    
	    if (currentWidth < WIDTH)
	    {
	        resize = true;
	        currentWidth = WIDTH;
	    }
	    
	    if (currentHeight < HEIGHT)
	    {
	        resize = true;
	        currentHeight = HEIGHT;
	    }
	    
	    if (resize)
	    {
	        setSize(currentWidth, currentHeight);
	    }
	}
	
	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) { }
}