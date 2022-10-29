import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 *
 * @author JABER-UL HUDA 101137524
 * @version March 28, 2022
 */
public class TicTacToeGUI implements ActionListener
{
    JFrame frame; 
    JPanel titlePanel;
    JPanel buttonPanel;
    JLabel textField;
    JButton button[] = new JButton[9];
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenuItem resetItem;
    JMenuItem quitItem;
    boolean playerXTurn;
    
    
    /** 
    * Constructs a new Tic-Tac-Toe board and sets up the basic GUI.
    *  
    */
    public TicTacToeGUI()
    {
        frame = new JFrame("tic tac toe"); 
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        textField = new JLabel();
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        resetItem = new JMenuItem("Reset");
        quitItem = new JMenuItem("Quit");
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        
        frame.setJMenuBar(menuBar);
        menuBar.add(gameMenu);
        resetItem.addActionListener(this);
        quitItem.addActionListener(this);
        resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        gameMenu.add(resetItem);
        gameMenu.add(quitItem);
        
        frame.setVisible(true);
        
        textField.setBackground(new Color(192,192,192));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free",Font.BOLD,45));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("tic tac toe");
        
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,700,800,100);
        
        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(new Color(25,25,25));
        
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
            buttonPanel.add(button[i]);
            button[i].setFont(new Font("Ink Free",Font.BOLD,75));
            button[i].setFocusable(false);
            button[i].addActionListener(this);
        }
        
        titlePanel.add(textField);
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel);
        
        firstTurn();
    }
    
    /** 
    * This method reacts when a button is pressed 
    * 
    * @param ActionEvent ev takes in an action 
    */
    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == quitItem) {
            System.exit(0);
        }
        if (ev.getSource() == resetItem) {
            
            for(int i = 0; i < 9; i++) {
                button[i].setText("");
                this.allNotGreen();
                button[i].setEnabled(true);
                textField.setText("Game is in progress: X player's Turn");
            }
            
        }
        
        for(int i = 0; i < 9; i++) {
            if(ev.getSource() == button[i]) {
                if(playerXTurn) {
                    if(button[i].getText() == "") {
                        button[i].setForeground(new Color(255,0,0));
                        button[i].setText("X");
                        playerXTurn = false;
                        textField.setText("Game is in progress: O player's Turn");
                        checkGameOver();
                        checkGameOver();
                        check();
                    }
                }else{
                    if(button[i].getText() == "") {
                        button[i].setForeground(new Color(0,0,255));
                        button[i].setText("O");
                        playerXTurn = true;
                        textField.setText("Game is in progress: X player's Turn");
                        checkGameOver();
                        check();
                    }    
                }
            }
        
        }
    }
    
    private void firstTurn() {
        playerXTurn = true;
        textField.setText("Game is in progress: X player's Turn");
    }
    
    private void checkGameOver() {
        int count = 0;
        for(int i = 0; i < 9; i++) {
            if (button[i].getText() == "X" || button[i].getText() == "O") {
                count++;
            }
        }
        if (count == 9) {
            this.buttonAllDisable();
            this.allGreen();
            textField.setText("The Game is a Tie");
        }
    }
    
    private void buttonAllDisable() {
        for(int i = 0; i < 9; i++) {
            button[i].setEnabled(false);
        }
    }
    
    private void allGreen() {
        for(int i = 0; i < 9; i++) {
            button[i].setBackground(Color.GREEN);
        }
    }
    
    private void allNotGreen() {
        for(int i = 0; i < 9; i++) {
            button[i].setBackground(new Color(25,25,25));
        }
    }
    
    private void check() {
        // cheaks rows
        if(button[0].getText() == "X" && button[1].getText() == "X" && button[2].getText() == "X") {
            xWins(0,1,2);
        }
        if(button[0].getText() == "O" && button[1].getText() == "O" && button[2].getText() == "O") {
            oWins(0,1,2);
        }
        
        if(button[3].getText() == "X" && button[4].getText() == "X" && button[5].getText() == "X") {
            xWins(3,4,5);
        }
        if(button[3].getText() == "O" && button[4].getText() == "O" && button[5].getText() == "O") {
            oWins(3,4,5);
        }
        
        if(button[6].getText() == "X" && button[7].getText() == "X" && button[8].getText() == "X") {
            xWins(6,7,8);
        }
        if(button[6].getText() == "O" && button[7].getText() == "O" && button[8].getText() == "O") {
            oWins(6,7,8);
        }
        
        // cheaks colums
        if(button[0].getText() == "X" && button[3].getText() == "X" && button[6].getText() == "X") {
            xWins(0,3,6);
        }
        if(button[0].getText() == "O" && button[3].getText() == "O" && button[6].getText() == "O") {
            oWins(0,3,6);
        }
        
        if(button[1].getText() == "X" && button[4].getText() == "X" && button[7].getText() == "X") {
            xWins(1,4,7);
        }
        if(button[1].getText() == "O" && button[4].getText() == "O" && button[7].getText() == "O") {
            oWins(1,4,7);
        }
        
        if(button[2].getText() == "X" && button[5].getText() == "X" && button[8].getText() == "X") {
            xWins(2,5,8);
        }
        if(button[2].getText() == "O" && button[5].getText() == "O" && button[8].getText() == "O") {
            oWins(2,5,8);
        }
        
        // cheaks diagonal
        if(button[0].getText() == "X" && button[4].getText() == "X" && button[8].getText() == "X") {
            xWins(0,4,8);
        }
        if(button[0].getText() == "O" && button[4].getText() == "O" && button[8].getText() == "O") {
            oWins(0,4,8);
        }
        
        if(button[2].getText() == "X" && button[4].getText() == "X" && button[6].getText() == "X") {
            xWins(2,4,6);
        }
        if(button[2].getText() == "O" && button[4].getText() == "O" && button[6].getText() == "O") {
            oWins(2,4,6);
        }
    }
    
    private void xWins(int x,int y,int z) {
        button[x].setBackground(Color.GREEN);
        button[y].setBackground(Color.GREEN);
        button[z].setBackground(Color.GREEN);
        
        this.buttonAllDisable();
        textField.setText("X player is the Winner");
    }
    
    private void oWins(int x,int y,int z) {
        button[x].setBackground(Color.GREEN);
        button[y].setBackground(Color.GREEN);
        button[z].setBackground(Color.GREEN);
        
        this.buttonAllDisable();
        textField.setText("O player is the Winner");
    }
}
