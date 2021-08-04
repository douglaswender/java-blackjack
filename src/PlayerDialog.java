import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Players.Player;

public class PlayerDialog extends JDialog implements ActionListener {
    String name;
    int age;
    String gender;

    Player player;

    String[] genders = { "Masculino", "Feminino" };

    JTextField playerName = new JTextField();
    JTextField playerAge = new JTextField();
    JComboBox playerGender = new JComboBox(genders);

    public PlayerDialog(Frame owner, String title, boolean modal, Player player) {
        super(owner, title, modal);
        setSize(300, 200);
        setLocationRelativeTo(null);

        this.player = player;

        JPanel playerDetailsPanel = new JPanel(new SpringLayout());

        JLabel nameLabel = new JLabel("Nome", JLabel.TRAILING);
        playerDetailsPanel.add(nameLabel);
        nameLabel.setLabelFor(playerName);
        playerDetailsPanel.add(playerName);

        JLabel ageLabel = new JLabel("Idade", JLabel.TRAILING);
        playerDetailsPanel.add(ageLabel);
        ageLabel.setLabelFor(playerAge);
        playerDetailsPanel.add(playerAge);

        JLabel genderLabel = new JLabel("Gênero", JLabel.TRAILING);
        playerDetailsPanel.add(genderLabel);
        genderLabel.setLabelFor(playerGender);
        playerDetailsPanel.add(playerGender);

        SpringUtilities.makeCompactGrid(playerDetailsPanel, 3, 2, 5, 5, 5, 5);

        add(playerDetailsPanel, BorderLayout.NORTH);

        JButton updateButton = new JButton("Atualizar detalhes");
        JButton cancelButton = new JButton("Cancelar");

        JPanel playerUpdatePanel = new JPanel();
        playerUpdatePanel.add(updateButton);
        playerUpdatePanel.add(cancelButton);
        add(playerUpdatePanel, BorderLayout.SOUTH);

        updateButton.addActionListener(this);
        cancelButton.addActionListener(this);

        playerName.setText(player.getName());
        playerAge.setText(Integer.toString(player.getAge()));
        playerGender.setSelectedItem(player.getGender());

    }

    public void actionPerformed(ActionEvent evt) {
        String act = evt.getActionCommand();

        if (act.equals("Atualizar detalhes")) {
            updateDetails();
        } else if (act.equals("Cancelar")) {
            setVisible(false);
            dispose();
        }
    }

    private void updateDetails()
    {
        boolean validName = true;
        if (playerName.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Por favor, informe um nome", "Error", JOptionPane.ERROR_MESSAGE);
            validName = false;
        }
        else
        {
            player.setName(playerName.getText());
        }
        
        boolean validAge = true;
        try
        {
            age = Integer.parseInt(playerAge.getText());
            if(age == 0){
                validAge = false;
                JOptionPane.showMessageDialog(null, "Por favor, informe uma idade maior que 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (NumberFormatException e)
	    {
	        JOptionPane.showMessageDialog(null, "Por favor, informe uma idade (apenas números)", "Error", JOptionPane.ERROR_MESSAGE);
	        validAge = false;
	    }
	    
	    if (validAge)
	    {
	        player.setAge(age);
	    }
	    
	    player.setGender((String) playerGender.getSelectedItem());
	    
	    if (validAge && validName)
	    {
	        setVisible(false);
	        dispose();
        }
    }

    public Player getPlayer() {
        return player;
    }
}