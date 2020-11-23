import java.awt.*;         //Using layouts
import java.awt.event.*;   //Using AWT event classes and listener inerfaces
import javax.swing.*;      //Using Swing components and containers
import java.util.Scanner;  //Reads user input
import java.util.concurrent.TimeUnit;
import java.util.*;

public class Cardiators//Game Created and Coded by Drew "IceBeam" Kendziora
{
   static JFrame f = new JFrame("Drew Kendziora's Cardiators");
   
   
   public static void main (String[] args) throws InterruptedException 
   {
      int[] redDefault = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,200,200,200,200,200};
      int[] blueDefault = {20,20,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,80,80};
      int[] yellowDefault = {0,0,0,0,0,0,0,0,0,0,60,90,100,100,100,100,100,100,100,150}; //default damage values for each card type
      int[] redCustom = redDefault;
      int[] blueCustom = blueDefault;
      int[] yellowCustom = yellowDefault; //creates customizable damage values for card editor, same as defaults until changed
      // List allRolls = new ArrayList[int]();
      f.setVisible(true);
   f.setSize(1200,800);
   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel placeholder = new JPanel();
      placeholder.setBackground(Color.GRAY);
      placeholder.setLayout(new GridLayout(4, 4, 10, 10));
      
      Font biggerFont = new Font("Serif", Font.BOLD, 24);
      Font prettyFont = new Font("Cambria", Font.BOLD, 18);
      Font labelFont = new Font("Verdana", Font.BOLD, 32);
      
      JTextField display = new JTextField("Hi! I'm the event display!");
      
      JTextArea roll = new JTextArea("Roll");
      JTextArea rollDisplay = new JTextArea("D20");
      JTextArea playerCard = new JTextArea("Health: 200", 1, 1);
      JTextArea playerDamageValues = new JTextArea("Damage Values: \n1 2 3 4 5 \n6 7 8 9 10 \n11 12 13 14 15 \n16 17 18 19 20", 1, 1);
      JTextArea allRolls = new JTextArea("Your damages:");
      
      JTextArea cpuCard = new JTextArea("Health: 200", 1, 1);
      JTextArea cpuDamageValues = new JTextArea("Damage Values: \n1 2 3 4 5 \n6 7 8 9 10 \n11 12 13 14 15 \n16 17 18 19 20", 1, 1);
      
      JLabel playerCardLabel = new JLabel("<HTML><U>You</U></HTML>", 0);
      JLabel cpuCardLabel = new JLabel("<HTML><U>Opponent</HTML>", 0);
      JLabel turn = new JLabel("Turn 0", 0);
      JLabel currentPlayerScore = new JLabel("Your KOs: 0/0", 0);
      JLabel currentCPUScore = new JLabel("Opponent's KOs: 0/0", 0);
      JLabel redLeft = new JLabel("Reds left: 6", 0);
      JLabel blueLeft = new JLabel("Blues left: 6", 0);
      JLabel yellowLeft = new JLabel("Yellows left: 6" , 0);
      
      playerCardLabel.setFont(labelFont);
      cpuCardLabel.setFont(labelFont);
      playerDamageValues.setFont(biggerFont);
      cpuDamageValues.setFont(biggerFont);
      // playerDamageValues.setForeground(Color.WHITE);
//       cpuDamageValues.setForeground(Color.WHITE);
      turn.setFont(biggerFont);
      display.setFont(prettyFont);
      currentPlayerScore.setFont(biggerFont);
      currentCPUScore.setFont(biggerFont);
      allRolls.setFont(biggerFont);
      playerCard.setFont(biggerFont);
      cpuCard.setFont(biggerFont);
      // playerCard.setForeground(Color.WHITE);
//       cpuCard.setForeground(Color.WHITE);
      turn.setFont(biggerFont);
      roll.setFont(biggerFont);
      rollDisplay.setFont(biggerFont);
      redLeft.setFont(biggerFont);
      blueLeft.setFont(biggerFont);
      yellowLeft.setFont(biggerFont);
      redLeft.setForeground(Color.RED);
      blueLeft.setForeground(Color.BLUE);
      yellowLeft.setForeground(Color.YELLOW);
      
      placeholder.add(turn);
      placeholder.add(playerCardLabel);
      placeholder.add(cpuCardLabel);
      placeholder.add(allRolls);
      placeholder.add(redLeft);
      placeholder.add(playerCard);
      placeholder.add(cpuCard);
      placeholder.add(display);
      placeholder.add(blueLeft);
      placeholder.add(playerDamageValues);
      placeholder.add(cpuDamageValues);
      placeholder.add(rollDisplay);
      placeholder.add(yellowLeft);
      placeholder.add(currentPlayerScore);
      placeholder.add(currentCPUScore);
      placeholder.add(roll);
      
      f.add(placeholder);
      
      JOptionPane.showMessageDialog(null, "Welcome to Cardiators!"); //For purposes of testing the game before GUI is created, prints all display messages whenever I update them
      
      String response = JOptionPane.showInputDialog("Is this your first time playing? Type 'yes' below to hear the rules, or press OK to skip.");
      
      if (response.equals("yes"))
      {
         JOptionPane.showMessageDialog(null, "Alrighty then. Here are the basics of Cardiators.");
         
         JOptionPane.showMessageDialog(null, "In a game of Cardiators, you play against a computer opponent. You each have a card, which can be either red, blue, or yellow. There are 6 of each card color in the deck to start with. \nThe left column of the screen shows how many of each card color are left in the deck. The top left corner shows the current turn. Your card is on the left of the screen, and the computer's card is on the right.\n A list of all the damage the player has done can be found on the top right of the screen. If the text goes out of the box, you can scroll within the text field to read the rest.");

         JOptionPane.showMessageDialog(null, "Cards have both Health and Damage Values, which are shown on the card itself. All cards start at exactly 200 Health, which goes down as they take damage. \nThe Damage Values are the same for each different card color, and represent the possible damage the card can dish out. These values each correspond to a roll of a 20-sided die. \nThis die is on the right side of the screen, controlled by a Roll button.");

         JOptionPane.showMessageDialog(null, "The player is able to modify the Damage Values of one card color to their liking before the game begins, which will apply to all cards of that color. This is done by selecting 20 new Damage Values from a drop-down menu, \none at a time. The player will always start the game with the card they've modified.");
         
         JOptionPane.showMessageDialog(null, "If a card's Health hits zero, it is knocked out, and the opponent earns one KO. These KOs are displayed on the bottom of the screen. The player whose card has been knocked out draws a new one automatically, \nif there are cards left in the deck. The first player to reach a number of KOs that you determine wins the game. If there are no cards left in the deck, the winner is decided by whoever has more KOs at the time.");
               
         JOptionPane.showMessageDialog(null, "On your turn, click 'Roll' on the bottom right of the screen to roll a 20 sided die. The text above it shows the value of your roll. You are able to continuously re-roll  for 4 seconds until you get your desired outcome, \nthough this is solely intended for testing purposes. After 4 seconds, that will be your final roll, and your card will deal the corresponding Damage Value, which will be displayed below your Health.");
      
         JOptionPane.showMessageDialog(null, "And that's all the rules of Cardiators.");
         Thread.sleep(1000);
      }
      response = JOptionPane.showInputDialog("Would you like to edit a card? Type 'red', 'blue', or 'yellow' below to edit that card color, \nor press OK to begin a game using the default damage values for each card.");
      
      String[] damageInts =  {" ", "0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150", "160", "170", "180", "190", "200"};
      JComboBox damageVal = new JComboBox(damageInts);
      
      f.remove(placeholder);
      
       
         if (response.equals("red"))
         {
            f.setVisible(false);
            f.setSize(600,400);
            JPanel editor = new JPanel();
            editor.setBackground(Color.RED);
            editor.setLayout(new GridLayout(3, 1, 10, 10));
            f.add(editor);
            f.setVisible(true);
            
            JLabel redEdit = new JLabel("Please select a new Damage Value for red cards from the dropdown menu below.");
            JLabel editsLeft = new JLabel("You are currently editing the Damage Value for rolling a 1.");
            editor.add(redEdit);
            editor.add(editsLeft);
            editor.add(damageVal);
            
            String value = " ";
            for (int i = 0; i < 20; i++)
            {
               editsLeft.setText("You are currently editing the Damage Value for rolling a " + Integer.toString(i+1) + ".");
               damageVal.setSelectedItem(" ");
               value = (String) damageVal.getSelectedItem();
               while (value.equals(" "))
               {
                  value = (String) damageVal.getSelectedItem();
                  Thread.sleep(500);
               }   
               redCustom[i] = Integer.parseInt(value);      
            }
            
            JOptionPane.showMessageDialog(null, "Values have been updated. Now, let's play a game with this new card!");
            f.remove(editor);
            int playerCardsLost = 0;
            int cpuCardsLost = 0;
            int killsToWin = 0;
            int redsLeft = 5;
            int bluesLeft = 6;
            int yellowsLeft = 6;
            int currentTurn = 1;
            
            response = JOptionPane.showInputDialog("How many KOs would you like to play to?  This should be at least 1 but no more than 9, as that would go beyond the number of cards in the deck.\n Do not press OK until you have input a number.");
            
            killsToWin = Integer.parseInt(response);
            
            Card playerFirstCard = new Card("red", 200);
            JOptionPane.showMessageDialog(null, "You start with a " + playerFirstCard.getType() + " card.");
            Card cpuFirstCard = drawCard(redsLeft,bluesLeft,yellowsLeft);
            JOptionPane.showMessageDialog(null, "The computer starts with a " + cpuFirstCard.getType() + " card.");
            
            if(cpuFirstCard.getType() == "red")
               redsLeft--;
            else if (cpuFirstCard.getType() == "blue")
               bluesLeft--;
            else
               yellowsLeft--;
            JOptionPane.showMessageDialog(null, "Let's get started! A window should pop up shortly. Having the window open in full screen is recommended. Closing the window will close the game.");
            
            playerTurn(playerCardsLost, cpuCardsLost, killsToWin, currentTurn, playerFirstCard, cpuFirstCard, redsLeft, bluesLeft, yellowsLeft, redCustom, blueCustom, yellowCustom);
         }
         
         else if (response.equals("blue"))
         {
            f.setVisible(false);
            f.setSize(600,400);
            JPanel editor = new JPanel();
            editor.setBackground(Color.BLUE);
            editor.setLayout(new GridLayout(3, 1, 10, 10));
            f.add(editor);
            f.setVisible(true);
            
            JLabel blueEdit = new JLabel("Please select a new Damage Value for blue cards from the dropdown menu below.");
            JLabel editsLeft = new JLabel("You are currently editing the Damage Value for rolling a 1.");
            editor.add(blueEdit);
            editor.add(editsLeft);
            editor.add(damageVal);
            
            String value = " ";
            for (int i = 0; i < 20; i++)
            {
               editsLeft.setText("You are currently editing the Damage Value for rolling a " + Integer.toString(i+1) + ".");
               damageVal.setSelectedItem(" ");
               value = (String) damageVal.getSelectedItem();
               while (value.equals(" "))
               {
                  value = (String) damageVal.getSelectedItem();
                  Thread.sleep(500);
               }   
               blueCustom[i] = Integer.parseInt(value);   
            }
            
            JOptionPane.showMessageDialog(null, "Values have been updated. Now, let's play a game with this new card!");
            f.remove(editor);
            int playerCardsLost = 0;
            int cpuCardsLost = 0;
            int killsToWin = 0;
            int redsLeft = 6;
            int bluesLeft = 5;
            int yellowsLeft = 6;
            int currentTurn = 1;
            
            response = JOptionPane.showInputDialog(null, "How many KOs would you like to play to?  This should be at least 1 but no more than 9, as that would go beyond the number of cards in the deck.\n Do not press OK until you have input a number.");
            
            killsToWin = Integer.parseInt(response);
            
            Card playerFirstCard = new Card("blue", 200);
            JOptionPane.showMessageDialog(null, "You start with a " + playerFirstCard.getType() + " card.");
            
            Card cpuFirstCard = drawCard(redsLeft,bluesLeft,yellowsLeft);
            JOptionPane.showMessageDialog(null, "The computer starts with a " + cpuFirstCard.getType() + " card.");
            
            if(cpuFirstCard.getType() == "red")
               redsLeft--;
            else if (cpuFirstCard.getType() == "blue")
               bluesLeft--;
            else
               yellowsLeft--;
            JOptionPane.showMessageDialog(null, "Let's get started! A window should pop up shortly. Having the window open in full screen is recommended. Closing the window will close the game.");
            
            playerTurn(playerCardsLost, cpuCardsLost, killsToWin, currentTurn, playerFirstCard, cpuFirstCard, redsLeft, bluesLeft, yellowsLeft, redCustom, blueCustom, yellowCustom);
         }
         
         else if (response.equals("yellow"))
         {
            f.setVisible(false);
            f.setSize(600,400);
            JPanel editor = new JPanel();
            editor.setBackground(Color.YELLOW);
            editor.setLayout(new GridLayout(3, 1, 10, 10));
            f.add(editor);
            f.setVisible(true);
            
            JLabel yellowEdit = new JLabel("Please select a new Damage Value for yellow cards from the dropdown menu below.");
            JLabel editsLeft = new JLabel("You are currently editing the Damage Value for rolling a 1.");
            editor.add(yellowEdit);
            editor.add(editsLeft);
            editor.add(damageVal);
            
            String value = " ";
            for (int i = 0; i < 20; i++)
            {
               editsLeft.setText("You are currently editing the Damage Value for rolling a " + Integer.toString(i+1) + ".");
               damageVal.setSelectedItem(" ");
               value = (String) damageVal.getSelectedItem();
               while (value.equals(" "))
               {
                  value = (String) damageVal.getSelectedItem();
                  Thread.sleep(500);
               }   
               yellowCustom[i] = Integer.parseInt(value);   
            }
            
            JOptionPane.showMessageDialog(null, "Values have been updated. Now, let's play a game with this new card!");
            f.remove(editor);
            int playerCardsLost = 0;
            int cpuCardsLost = 0;
            int killsToWin = 0;
            int redsLeft = 6;
            int bluesLeft = 6;
            int yellowsLeft = 5;
            int currentTurn = 1;
            
            response = JOptionPane.showInputDialog("How many KOs would you like to play to?  This should be at least 1 but no more than 9, as that would go beyond the number of cards in the deck.\n Do not press OK until you have input a number.");
            
            killsToWin = Integer.parseInt(response);
           
            Card playerFirstCard = new Card("yellow", 200);
            JOptionPane.showMessageDialog(null, "You start with a " + playerFirstCard.getType() + " card.");
            
            if(playerFirstCard.getType() == "red")
               redsLeft--;
            else if (playerFirstCard.getType() == "blue")
               bluesLeft--;
            else
               yellowsLeft--;
            Card cpuFirstCard = drawCard(redsLeft,bluesLeft,yellowsLeft);
            JOptionPane.showMessageDialog(null, "The computer starts with a " + cpuFirstCard.getType() + " card.");
            
            if(cpuFirstCard.getType() == "red")
               redsLeft--;
            else if (cpuFirstCard.getType() == "blue")
               bluesLeft--;
            else
               yellowsLeft--;
            JOptionPane.showMessageDialog(null, "Let's get started! A window should pop up shortly. Having the window open in full screen is recommended. Closing the window will close the game.");
            
            playerTurn(playerCardsLost, cpuCardsLost, killsToWin, currentTurn, playerFirstCard, cpuFirstCard, redsLeft, bluesLeft, yellowsLeft, redCustom, blueCustom, yellowCustom);
         } 
      else
      {
         int playerCardsLost = 0;
         int cpuCardsLost = 0;
         int killsToWin = 0;
         int redsLeft = 6;
         int bluesLeft = 6;
         int yellowsLeft = 6;
         int currentTurn = 1;
         JOptionPane.showMessageDialog(null, "Very well then. Let's play some Cardiators against a computer opponent!");
         
         response = JOptionPane.showInputDialog("How many KOs would you like to play to?  This should be at least 1 but no more than 9, as that would go beyond the number of cards in the deck.\n Do not press OK until you have input a number.");
       
            killsToWin = Integer.parseInt(response);
         Card playerFirstCard = drawCard(redsLeft,bluesLeft,yellowsLeft);
            JOptionPane.showMessageDialog(null, "You start with a " + playerFirstCard.getType() + " card.");
            
            if(playerFirstCard.getType() == "red")
               redsLeft--;
            else if (playerFirstCard.getType() == "blue")
               bluesLeft--;
            else
               yellowsLeft--;
            Card cpuFirstCard = drawCard(redsLeft,bluesLeft,yellowsLeft);
            JOptionPane.showMessageDialog(null, "The computer starts with a " + cpuFirstCard.getType() + " card.");
            
            if(cpuFirstCard.getType() == "red")
               redsLeft--;
            else if (cpuFirstCard.getType() == "blue")
               bluesLeft--;
            else
               yellowsLeft--;
            JOptionPane.showMessageDialog(null, "Let's get started! A window should pop up shortly. Having the window open in full screen is recommended. Closing the window will close the game.");
            
            
         playerTurn(playerCardsLost, cpuCardsLost, killsToWin, currentTurn, playerFirstCard, cpuFirstCard, redsLeft, bluesLeft, yellowsLeft, redCustom, blueCustom, yellowCustom);
      }     
   }
     
    private static void playerTurn(int playerLosses, int cpuLosses, int deathsNeeded, int currentTurn, Card playerCurrentCard, Card cpuCurrentCard, int reds, int blues, int yellows, int[] rCustom, int[] bCustom, int[] yCustom)  throws InterruptedException
    {
      f.setVisible(true);
      
      JPanel p = new JPanel();
      p.setBackground(Color.GRAY);
      p.setLayout(new GridLayout(4, 4, 10, 10));
      
      Font biggerFont = new Font("Serif", Font.BOLD, 24);
      Font buttonFont = new Font("Serif", Font.BOLD, 48);
      Font prettyFont = new Font("Cambria", Font.BOLD, 18);
      Font labelFont = new Font("Verdana", Font.BOLD, 32);
      
      JTextField display = new JTextField("Let the game begin!");
      
      JButton roll = new JButton("Roll");
      JLabel rollDisplay = new JLabel("");
      roll.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent e)
         {
               for(int i = 0; i < 100; i++)
               {
                  rollDisplay.setText(Integer.toString((int)(Math.random() * 20 + 1)));
               }     
         }
      });
      JTextArea playerCard = new JTextArea("Health: 200", 1, 1);
      JTextArea playerDamageValues = new JTextArea("Damage Values: \n");
      JTextArea allRolls = new JTextArea("Your damages:");
      if(playerCurrentCard.getType().equals("red"))
      {
         playerCard.setBackground(Color.RED);
         playerDamageValues.setText("Damage Values: \n" + damageValues(rCustom));
         playerDamageValues.setBackground(Color.RED);
      }
      else if(playerCurrentCard.getType().equals("blue"))
      {
         playerCard.setBackground(Color.BLUE);
         playerDamageValues.setText("Damage Values: \n" + damageValues(bCustom));
         playerDamageValues.setBackground(Color.BLUE);
      }
      else  
      {
         playerCard.setBackground(Color.YELLOW);
         playerDamageValues.setText("Damage Values: \n" + damageValues(yCustom));
         playerDamageValues.setBackground(Color.YELLOW);
      }    
      JTextArea cpuCard = new JTextArea("Health: 200", 1, 1);
      JTextArea cpuDamageValues = new JTextArea("Damage Values: \n");
      if(cpuCurrentCard.getType().equals("red"))
      {
         cpuCard.setBackground(Color.RED);
         cpuDamageValues.setText("Damage Values: \n" + damageValues(rCustom));
         cpuDamageValues.setBackground(Color.RED);
      }
      else if(cpuCurrentCard.getType().equals("blue"))
      {
         cpuCard.setBackground(Color.BLUE);
         cpuDamageValues.setText("Damage Values: \n" + damageValues(bCustom));
         cpuDamageValues.setBackground(Color.BLUE);
      }
      else  
      {
         cpuCard.setBackground(Color.YELLOW);
         cpuDamageValues.setText("Damage Values: \n" + damageValues(yCustom));
         cpuDamageValues.setBackground(Color.YELLOW);
      }
      JLabel playerCardLabel = new JLabel("<HTML><U>You</U></HTML>", 0);
      JLabel cpuCardLabel = new JLabel("<HTML><U>Computer</U></HTML>", 0);
      JLabel turn = new JLabel("Turn " + Integer.toString(currentTurn), 0);
      JLabel currentPlayerScore = new JLabel("Your KOs: " + Integer.toString(cpuLosses) + "/" + Integer.toString(deathsNeeded), 0);
      JLabel currentCPUScore = new JLabel("Computer's KOs: " + Integer.toString(playerLosses) + "/" + Integer.toString(deathsNeeded), 0);
      JLabel redLeft = new JLabel("Reds left: " + Integer.toString(reds), 0);
      JLabel blueLeft = new JLabel("Blues left: " + Integer.toString(blues), 0);
      JLabel yellowLeft = new JLabel("Yellows left: " + Integer.toString(yellows), 0);
      
      playerCardLabel.setFont(labelFont);
      cpuCardLabel.setFont(labelFont);
      playerDamageValues.setFont(biggerFont);
      cpuDamageValues.setFont(biggerFont);
      // playerDamageValues.setForeground(Color.WHITE);
//       cpuDamageValues.setForeground(Color.WHITE);
      turn.setFont(biggerFont);
      display.setFont(prettyFont);
      currentPlayerScore.setFont(biggerFont);
      currentCPUScore.setFont(biggerFont);
      allRolls.setFont(biggerFont);
      playerCard.setFont(biggerFont);
      cpuCard.setFont(biggerFont);
      // playerCard.setForeground(Color.WHITE);
//       cpuCard.setForeground(Color.WHITE);
      turn.setFont(biggerFont);
      roll.setFont(buttonFont);
      rollDisplay.setFont(labelFont);
      redLeft.setFont(biggerFont);
      blueLeft.setFont(biggerFont);
      yellowLeft.setFont(biggerFont);
      redLeft.setForeground(Color.RED);
      blueLeft.setForeground(Color.BLUE);
      yellowLeft.setForeground(Color.YELLOW);
      
      p.add(turn);
      p.add(playerCardLabel);
      p.add(cpuCardLabel);
      p.add(allRolls);
      p.add(redLeft);
      p.add(playerCard);
      p.add(cpuCard);
      p.add(display);
      p.add(blueLeft);
      p.add(playerDamageValues);
      p.add(cpuDamageValues);
      p.add(rollDisplay);
      p.add(yellowLeft);
      p.add(currentPlayerScore);
      p.add(currentCPUScore);
      p.add(roll);
      
      f.add(p);
      
      int damageGiven = 0;
      int diceRoll = 0;
      ArrayList<String> rollList = new ArrayList<String>();
      Thread.sleep(1000);
      
            
      while(playerLosses < deathsNeeded && cpuLosses < deathsNeeded) 
      {
         Thread.sleep(4000);
         rollDisplay.setText("");
         if (cpuCurrentCard.getType() == "red")       
            cpuDamageValues.setText("Damage Values: \n" + damageValues(rCustom));  
         else if (cpuCurrentCard.getType() == "blue")
            cpuDamageValues.setText("Damage Values: \n" + damageValues(bCustom));
         else
            cpuDamageValues.setText("Damage Values: \n" + damageValues(yCustom));
         display.setText("Your turn! Click Roll to roll the die!");
         display.setBackground(Color.CYAN);
         display.setForeground(Color.RED);
         turn.setText("Turn " + Integer.toString(currentTurn));
         while(rollDisplay.getText().equals(""))
         {
            roll.setForeground(Color.WHITE);
            Thread.sleep(500);
            roll.setForeground(Color.BLACK);
            Thread.sleep(500);
         }
         Thread.sleep(4000);
         display.setBackground(Color.WHITE);
         display.setForeground(Color.BLACK);
         diceRoll = Integer.valueOf(rollDisplay.getText());
         if(diceRoll == 8 || diceRoll == 11 || diceRoll == 18)
            display.setText("You roll an " + Integer.toString(diceRoll) + "!");
         else
            display.setText("You roll a " + Integer.toString(diceRoll) + "!");
         Thread.sleep(1000);
         damageGiven = 0;
         if (playerCurrentCard.getType() == "red")
         {
            damageGiven = damageCalc(rCustom, diceRoll);
            playerDamageValues.setText(highlightDamageValues(rCustom, diceRoll));
         }   
         else if (playerCurrentCard.getType() == "blue")
         {
            damageGiven = damageCalc(bCustom, diceRoll);
            playerDamageValues.setText(highlightDamageValues(bCustom, diceRoll));
         }
         else
         {
            damageGiven = damageCalc(yCustom, diceRoll);
            playerDamageValues.setText(highlightDamageValues(yCustom, diceRoll));      
         }
         display.setText("Your card deals " + Integer.toString(damageGiven) + " damage!");
         Thread.sleep(1000);
         if (damageGiven == 0)
            display.setText("Aw, that's too bad...");
         cpuCurrentCard.setCurrentHP(damageGiven);
         cpuCard.setText("Health: " + Integer.toString(cpuCurrentCard.getCurrentHP()));
         if (damageGiven != 0)
         {
            cpuCard.setForeground(Color.WHITE);
            Thread.sleep(250);
            cpuCard.setForeground(Color.BLACK);
         }   
         rollList.add(Integer.toString(damageGiven));
         allRolls.setText("Your damages: " + rollList.toString());
         if(cpuCurrentCard.getCurrentHP() <= 0)
         {
            display.setText("The computer's card is defeated!");
            cpuCard.setText("");
            cpuCard.setBackground(Color.GRAY);
            cpuDamageValues.setText("");
            cpuDamageValues.setBackground(Color.GRAY);
            Thread.sleep(2000);
            cpuLosses++;
            currentPlayerScore.setText("Your KOs: " + Integer.toString(cpuLosses) + "/" + Integer.toString(deathsNeeded));
            if(cpuLosses == deathsNeeded)
            {
               display.setText("And that's the game! You win!");
               Thread.sleep(7240);
               display.setText("Thanks for playing my game!  -Drew");
               return;
            }   
            else
            {
               display.setText("The computer now must draw a new card.");
               Thread.sleep(2500);
               cpuCurrentCard = drawCard(reds,blues,yellows);
               if(cpuCurrentCard.getType() == "null")
               {
                  display.setText("But there are no cards left to draw!");
                  Thread.sleep(2000);
                  if (cpuLosses > playerLosses)
                     display.setText("And that's the game! You win!");
                  else if (cpuLosses > playerLosses)
                     display.setText("And that's the game! You lose...");
                  else
                     display.setText("And that's the game! It's a tie.");      
                  Thread.sleep(7240);
                  display.setText("Thanks for playing my game!  -Drew");
                  return;
               }
               display.setText("The computer draws a new " + cpuCurrentCard.getType() + " card.");
               cpuCard.setText("Health: " + Integer.toString(cpuCurrentCard.getCurrentHP()));
               if(cpuCurrentCard.getType() == "red")
               {
                  cpuCard.setBackground(Color.RED);
                  cpuDamageValues.setText(damageValues(rCustom));
                  cpuDamageValues.setBackground(Color.RED);
                  reds--;
                  redLeft.setText("Reds left: " + Integer.toString(reds));
               }
               else if (cpuCurrentCard.getType() == "blue")
               {
                  cpuCard.setBackground(Color.BLUE);
                  cpuDamageValues.setText(damageValues(bCustom));
                  cpuDamageValues.setBackground(Color.BLUE);
                  blues--;
                  blueLeft.setText("Blues left: " + Integer.toString(blues));
               }
               else
               {
                  cpuCard.setBackground(Color.YELLOW);
                  cpuDamageValues.setText(damageValues(yCustom));
                  cpuDamageValues.setBackground(Color.YELLOW);
                  yellows--;
                  yellowLeft.setText("Yellows left: " + Integer.toString(yellows));
               }
               } //end player death script
            } //end CPU draw script
               Thread.sleep(4000);
               display.setText("Now it's the computer's turn!");
               rollDisplay.setText("");
               if (playerCurrentCard.getType() == "red")       
                  playerDamageValues.setText("Damage Values: \n" + damageValues(rCustom));  
               else if (playerCurrentCard.getType() == "blue")
                  playerDamageValues.setText("Damage Values: \n" + damageValues(bCustom));
               else
                  playerDamageValues.setText("Damage Values: \n" + damageValues(yCustom));
               display.setBackground(Color.CYAN);
               display.setForeground(Color.RED);
               Thread.sleep(3000);
               display.setBackground(Color.WHITE);
               display.setForeground(Color.BLACK);
               diceRoll = (int)(Math.random() * 20 + 1);
               if(diceRoll == 8 || diceRoll == 11 || diceRoll == 18)
                  display.setText("The computer rolls an " + Integer.toString(diceRoll) + ".");
               else
                  display.setText("The computer rolls a " + Integer.toString(diceRoll) + ".");
               Thread.sleep(2000);
               damageGiven = 0;
               if (cpuCurrentCard.getType() == "red")
               {
                  damageGiven = damageCalc(rCustom, diceRoll);
                  cpuDamageValues.setText(highlightDamageValues(rCustom, diceRoll));
               }   
               else if (cpuCurrentCard.getType() == "blue")
               {
                  damageGiven = damageCalc(bCustom, diceRoll);
                  cpuDamageValues.setText(highlightDamageValues(bCustom, diceRoll));
               }
               else
               {
                  damageGiven = damageCalc(yCustom, diceRoll);
                  cpuDamageValues.setText(highlightDamageValues(yCustom, diceRoll));      
               }     
               display.setText("Your card takes " + Integer.toString(damageGiven) + " damage!");
               Thread.sleep(1000);
               if (damageGiven == 0)
                  display.setText("Lucky you!");
               playerCurrentCard.setCurrentHP(damageGiven);
               playerCard.setText("Health: " + Integer.toString(playerCurrentCard.getCurrentHP()));
               if (damageGiven != 0)
               {
                  playerCard.setForeground(Color.WHITE);
                  Thread.sleep(250);
                  playerCard.setForeground(Color.BLACK);
               }   
               if(playerCurrentCard.getCurrentHP() <= 0)
               {
                  display.setText("Oh no! Your card is defeated!");
                  playerCard.setText("");
                  playerCard.setBackground(Color.GRAY);
                  playerDamageValues.setText("");
                  playerDamageValues.setBackground(Color.GRAY);
                  Thread.sleep(2000);
                  playerLosses++;
                  currentCPUScore.setText("Opponent's KOs: " + Integer.toString(playerLosses) + "/" + Integer.toString(deathsNeeded));
                  if(playerLosses == deathsNeeded)
                  {
                     display.setText("And that's the game! You lose...");
                     Thread.sleep(10000);
                     display.setText("Thank you for playing!  -Drew");
                     return;
                  }   
                  else
                  {
                     display.setText("Now you'll draw a new card.");
                     Thread.sleep(2000);
                     playerCurrentCard = drawCard(reds,blues,yellows);
                     if(cpuCurrentCard.getType() == "null")
                     {
                         display.setText("But there are no cards left to draw!");
                         Thread.sleep(2000);
                         if (cpuLosses > playerLosses)
                            display.setText("And that's the game! You win!");
                         else if (cpuLosses > playerLosses)
                            display.setText("And that's the game! You lose...");
                         else
                            display.setText("And that's the game! It's a tie.");      
                         Thread.sleep(7240);
                         display.setText("Thanks for playing my game!  -Drew");
                         return;
                     }
                     display.setText("You draw a new " + playerCurrentCard.getType() + " card.");
                     playerCard.setText("Health: " + Integer.toString(playerCurrentCard.getCurrentHP()));
                     if(playerCurrentCard.getType() == "red")
                     {
                        playerCard.setBackground(Color.RED);
                        playerDamageValues.setText(damageValues(rCustom));
                        playerDamageValues.setBackground(Color.RED);
                        reds--;
                        redLeft.setText("Reds left: " + Integer.toString(reds));
                     }
                     else if (playerCurrentCard.getType() == "blue")
                     {
                        playerCard.setBackground(Color.BLUE);
                        playerDamageValues.setText(damageValues(bCustom));
                        playerDamageValues.setBackground(Color.BLUE);
                        blues--;
                        blueLeft.setText("Blues left: " + Integer.toString(blues));
                     }
                     else
                     {
                        playerCard.setBackground(Color.YELLOW);
                        playerDamageValues.setText(damageValues(yCustom));
                        playerDamageValues.setBackground(Color.YELLOW);
                        yellows--;
                        yellowLeft.setText("Yellows left: " + Integer.toString(yellows));
                     }
                  } //end player draw script              
         } //end CPU death script            
      currentTurn++;
      } //end while loop
    }
    
    public static String damageValues(int[] values)
    {
      String vals = "";
      for(int i = 0; i < 20; i++)
      {
         int val = values[i];
         vals += Integer.toString(val)+ " ";
         if (i % 5 == 4 & i != 19)
            vals += "\n";
      }
      return vals;
    }
    
    public static String highlightDamageValues(int[] values, int roll)
    {
         String highlighted = "Damage Values: \n";
         for(int i = 0; i < 20; i++)
         {
            if(i+1 == roll)
               highlighted += ">" + Integer.toString(values[i]) + " ";
            else
               highlighted += Integer.toString(values[i]) + " ";
            if (i % 5 == 4 & i != 19)
               highlighted += "\n";       
         }
         return highlighted;
    }
    
    public static Card drawCard(int reds, int blues, int yellows)
    {
      Card newCard;
      int totalCardsLeft = reds + blues + yellows;
      if (totalCardsLeft == 0)
         return new Card("null", 0);
      int blueCutoff = reds + blues;
      int draw = (int)(Math.random() * totalCardsLeft);
      if (draw < reds)
         newCard = new Card("red", 200);
      else if (draw < blueCutoff)
         newCard = new Card("blue", 200);
      else
         newCard = new Card("yellow", 200);
      return newCard;         
    }
    
    public static int damageCalc(int[] values, int roll)
    {
      return values[roll-1];
    }  
}