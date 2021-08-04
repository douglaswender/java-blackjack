import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;

import Players.*;
import Cards.*;

public class GamePanel extends JPanel implements ActionListener
{
    private Dealer dealer;
    private Player player;
    
    private GameTable table;
    
    private JButton newGameButton = new JButton("Dar as cartas");
    private JButton hitButton = new JButton("Comprar uma carta");
    private JButton doubleButton = new JButton("Dobrar aposta");
	private JButton standButton = new JButton("Mostrar as cartas");
    private JButton add1Chip = new JButton("R$ 1");
    private JButton add5Chip = new JButton("R$ 5");
    private JButton add10Chip = new JButton("R$ 10");
    private JButton add25Chip = new JButton("R$ 25");
    private JButton add100Chip = new JButton("R$ 100");
    private JButton clearBet =  new JButton("Limpar");
    
    private JLabel currentBet = new JLabel("Por favor, aposte...");
    private JLabel playerWallet = new JLabel("$999.99");
    private JLabel cardsLeft = new JLabel("Cartas restantes...");
    private JLabel dealerSays = new JLabel("Dealer diz...");
    
    public GamePanel()
    {
        this.setLayout(new BorderLayout());
        
        table = new GameTable();
        add(table, BorderLayout.CENTER);
        
        JPanel betPanel = new JPanel();
        betPanel.add(currentBet);
        betPanel.add(clearBet);
        betPanel.add(add1Chip);
        betPanel.add(add5Chip);
        betPanel.add(add10Chip);
        betPanel.add(add25Chip);
        betPanel.add(add100Chip);
        betPanel.add(playerWallet);
        
        JPanel dealerPanel = new JPanel();
        dealerPanel.add(dealerSays);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(newGameButton);
        optionsPanel.add(hitButton);
        optionsPanel.add(doubleButton);
        optionsPanel.add(standButton);
        optionsPanel.add(cardsLeft);
        
        JPanel bottomItems = new JPanel();
        bottomItems.setLayout(new GridLayout(0,1));
        bottomItems.add(dealerPanel);
        bottomItems.add(betPanel);
        bottomItems.add(optionsPanel);
        add(bottomItems, BorderLayout.SOUTH);
        
        // opaque stuff
        //this.setBackground(new Color(6, 120, 0)); // now done in AppWindow.java
        betPanel.setOpaque(false);
        dealerPanel.setOpaque(false);
        optionsPanel.setOpaque(false);
        bottomItems.setOpaque(false);
        
        // add listeners to buttons
        newGameButton.addActionListener(this);
        hitButton.addActionListener(this);
        doubleButton.addActionListener(this);
		standButton.addActionListener(this);
		clearBet.addActionListener(this);
		add1Chip.addActionListener(this);
		add5Chip.addActionListener(this);
		add10Chip.addActionListener(this);
		add25Chip.addActionListener(this);
		add100Chip.addActionListener(this);

		// tool tips
		newGameButton.setToolTipText("Da início ao jogo");
		hitButton.setToolTipText("Solicita outra carta.");
		doubleButton.setToolTipText("Dobra sua aposta e recebe outra carta.");
		standButton.setToolTipText("Mostra as cartas em sua mão.");
        clearBet.setToolTipText("Limpa sua aposta atual.");
        add1Chip.setToolTipText("Adiciona R$ 1 a sua aposta atual.");
        add5Chip.setToolTipText("Adiciona R$ 5 a sua aposta atual.");
        add10Chip.setToolTipText("Adiciona R$ 10 a sua aposta atual.");
        add25Chip.setToolTipText("Adiciona R$ 25 a sua aposta atual.");
        add100Chip.setToolTipText("Adiciona R$ 100 a sua aposta atual.");
		
		dealer = new Dealer();
        player = new Player("James Bond", 32, "Male");
        player.setWallet(100.00);
		
        updateValues();
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        String act = evt.getActionCommand();
        
        if (act.equals("Dar as cartas"))
        {
            newGame();
        }
        else if (act.equals("Comprar uma carta"))
        {
            hit();
        }
        else if (act.equals("Dobrar aposta"))
        {
            playDouble();
        }
        else if (act.equals("Mostrar as cartas"))
        {
            stand();
        }
        else if (act.equals("R$ 1") || act.equals("R$ 5") || act.equals("R$ 10") || act.equals("R$ 25") || act.equals("R$ 100"))
        {
            System.out.println(act.split(" ")[1]);
            increaseBet(Integer.parseInt(act.split(" ")[1]));
        }
        else if (act.equals("Limpar"))
        {
            System.out.println("clear bet");
            clearBet();
        }
        
        updateValues();
    }
    
    public void newGame()
    {
        dealer.deal(player);
    }
    
    public void hit()
    {
        dealer.hit(player);
    }
    
    public void playDouble()
    {
        dealer.playDouble(player);
    }
    
    public void stand()
    {
        dealer.stand(player);
    }
    
    public void increaseBet(int amount)
    {
        System.out.println(amount);
        dealer.acceptBetFrom(player, amount + player.getBet());
    }
    
    public void clearBet()
    {
        player.clearBet();
    }
    
    public void updateValues()
    {
        dealerSays.setText("<html><p align=\"center\"><font face=\"Serif\" color=\"white\" style=\"font-size: 24px\">" + dealer.says() + "</font></p></html>");
        
        if (dealer.isGameOver())
        {
            if (player.betPlaced() && !player.isBankrupt())
            {
                newGameButton.setEnabled(true);
            }
            else
            {
                newGameButton.setEnabled(false);
            }
            hitButton.setEnabled(false);
            doubleButton.setEnabled(false);
            standButton.setEnabled(false);
            
            if (player.betPlaced())
            {
                clearBet.setEnabled(true);
            }
            else
            {
                clearBet.setEnabled(false);
            }
            
            if (player.getWallet() >= 1.0)
            {
                add1Chip.setEnabled(true);
            }
            else
            {
                add1Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 5)
            {
                add5Chip.setEnabled(true);
            }
            else
            {
                add5Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 10)
            {
                add10Chip.setEnabled(true);
            }
            else
            {
                add10Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 25)
            {
                add25Chip.setEnabled(true);
            }
            else
            {
                add25Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 100)
            {
                add100Chip.setEnabled(true);
            }
            else
            {
                add100Chip.setEnabled(false);
            }
        }
        else
        {
            newGameButton.setEnabled(false);
            hitButton.setEnabled(true);
            if (dealer.canPlayerDouble(player))
            {
                doubleButton.setEnabled(true);
            }
            else
            {
                doubleButton.setEnabled(false);
            }
            
            standButton.setEnabled(true);
            
            clearBet.setEnabled(false);
            add1Chip.setEnabled(false);
            add5Chip.setEnabled(false);
            add10Chip.setEnabled(false);
            add25Chip.setEnabled(false);
            add100Chip.setEnabled(false);
        }
        
        // redraw cards and totals
        table.update(dealer.getHand(), player.getHand(), (dealer.areCardsFaceUp()) ? true : false);
		table.setNames(dealer.getName(), player.getName());
        table.repaint();
        
        cardsLeft.setText("Baralho: " + dealer.cardsLeftInPack() + "/" + (dealer.CARD_PACKS * Cards.CardPack.CARDS_IN_PACK));
        
        if (player.isBankrupt())
        {
            moreFunds();
        }
        
        // redraw bet
        currentBet.setText("Valor apostado: R$ "+Double.toString(player.getBet()));
        playerWallet.setText("Sua carteira: R$ "+Double.toString(player.getWallet()));
    }
    
    private void moreFunds()
    {
        int response = JOptionPane.showConfirmDialog(null, "Marshall Aid. Mil dólares. Com os cumprimentos dos EUA.", "Acabou o dinheiro", JOptionPane.YES_NO_OPTION);
        
        if (response == JOptionPane.YES_OPTION)
        {
            player.setWallet(1000.00);
            updateValues();
        }
    }
    
    public void savePlayer()
	{
	    if (dealer.isGameOver())
	    {
	        JFileChooser playerSaveDialog = new JFileChooser("~");
	        SimpleFileFilter fileFilter = new SimpleFileFilter(".ser", "(.ser) Serialised Files");
	        playerSaveDialog.addChoosableFileFilter(fileFilter);
            int playerSaveResponse = playerSaveDialog.showSaveDialog(this);
        
            if (playerSaveResponse == playerSaveDialog.APPROVE_OPTION)
            {
                String filePath = playerSaveDialog.getSelectedFile().getAbsolutePath();
            
                try
                {
                    ObjectOutputStream playerOut = new ObjectOutputStream(new FileOutputStream(filePath));
                    playerOut.writeObject(player);
                    playerOut.close();
                }
                catch (IOException e)
                {
                    System.out.println(e);
                }
            }
	    }
	    else
	    {
	        JOptionPane.showMessageDialog(this, "Não é possível salvar o perfil enquauto o jogo está acontecendo!", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    
	}
	
	public void openPlayer()
	{
	    if (dealer.isGameOver())
	    {
	        JFileChooser playerOpenDialog = new JFileChooser("~");
	        SimpleFileFilter fileFilter = new SimpleFileFilter(".ser", "(.ser) Serialised Files");
	        playerOpenDialog.addChoosableFileFilter(fileFilter);
            int playerOpenResponse = playerOpenDialog.showOpenDialog(this);
        
            if (playerOpenResponse == playerOpenDialog.APPROVE_OPTION)
            {
                String filePath = playerOpenDialog.getSelectedFile().getAbsolutePath();
            
                try
                {
                    ObjectInputStream playerIn = new ObjectInputStream(new FileInputStream(filePath));
                    Player openedPlayer = (Player) playerIn.readObject();
                    openedPlayer.hand = new PlayerCardHand();
                    player = openedPlayer;
                    playerIn.close();
                    System.out.println(openedPlayer.getName());
                }
                catch (ClassNotFoundException e)
                {
                    System.err.println(e);
                }
                catch (IOException e)
                {
                    System.err.println(e);
                }
            }
	    }
	    else
	    {
	        JOptionPane.showMessageDialog(this, "Não é possível abrir outro perfil enquanto o jogo está em progresso.", "Erro!", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	public void updatePlayer()
	{
	    PlayerDialog playerDetails = new PlayerDialog(null, "Detalhes do jogador", true, player);
        playerDetails.setVisible(true);
        
        player = playerDetails.getPlayer();
	}
}