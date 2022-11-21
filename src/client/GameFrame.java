package client;


import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {

    Client client;

    JButton btGiveUp, btQuit, btToggleSound;
    JButton btLogin, btSignUp, btGuest;
    JButton btOption, btChallengeRandom, btChallengeByName, btSearchOpponent, btSearchLeader, btScoreboard;
    JButton btQuestion1, btQuestion2, btQuestion3, btQuestion4;
    JButton btOK;
    JButton btCategory1, btCategory2, btCategory3;
    JButton send;
    JButton btBack;

    JLabel lbUserName, lbPassword, lbLoginMessage, lbLoginTitle;
    //JLabel lbUserImage, lbName;
    JLabel lbOpponentImage, lbOpponentName, lbCurrentScore;
    JLabel lbWaitMessage;
    JLabel lbScore1, lbScore2, lbScore3, lbScore4, lbScore5, lbScore6,
            lbOpponentScore1, lbOpponentScore2, lbOpponentScore3, lbOpponentScore4, lbOpponentScore5, lbOpponentScore6,
            lbRound1, lbRound2, lbRound3, lbRound4, lbRound5, lbRound6,
            lbOpponentRound1, lbOpponentRound2, lbOpponentRound3, lbOpponentRound4, lbOpponentRound5, lbOpponentRound6;
    JLabel lbCategoryMessage;
    JLabel lbLeaderMessage;
    JLabel lbNewUser, lbNewPassword, lbRepeatPassword, lbCreateUserInfo;
    JLabel lbInfoBarName, lbInfoBarOpponentName, lbInfoBarPic, lbInfoBarOpponentPic, lbInfoBarWins, lbInfoBarOpponentWins;

    JTextField tfLogin, tfPassword, tfChat, tfSearchOpponent, tfSearchLeader;
    JTextField tfNewLogin, tfNewPassword, tfRepeatPassword;

    JTextPane tpQuestion;
    JTextPane tpScoreboard;
    JTextPane tpChat;

    JPanel mainScreen;
    JPanel chatScreen;
    JPanel loginScreen;
    JPanel createAccountScreen;
    JPanel homeScreen;
    JPanel playerInfoBar;
    JPanel gameScreen;
    JPanel waitScreen;
    JPanel categoryScreen;
    JPanel scoreScreen;
    JPanel optionScreen;
    JPanel leaderboard;

    private final int square = 96;
    private final int width = square * 6;
    private final int height = square * 8;

    public GameFrame(Client client){
        this.client = client;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Quiz");

        setUpComponents();
        setUpActionListeners();

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setUpComponents() {

        mainScreen = new JPanel();
        mainScreen.setPreferredSize(new Dimension(width, height));
        mainScreen.setFocusable(true);
        mainScreen.setBackground(new Color(0x5B9CFF));
        mainScreen.setLayout(null);
        this.add(mainScreen);

        btQuit = new JButton("Quit");
        btQuit.setBounds(width - 100, height - 50, 75, 25);
        mainScreen.add(btQuit);

        btToggleSound = new JButton("Sound Off");
        btToggleSound.setBounds(width / 2 - 75, height - 50, 150, 25);
        mainScreen.add(btToggleSound);

        btGiveUp = new JButton("Give up");
        btGiveUp.setBounds( 25, height - 50, 75, 25);
        btGiveUp.setVisible(false);
        mainScreen.add(btGiveUp);

        btBack = new JButton("Back");
        btBack.setBounds( 25, height - 50, 75, 25);
        btBack.setVisible(false);
        mainScreen.add(btBack);

        loginScreen = new JPanel();
        loginScreen.setBounds(0, square, width, height / 2);
        loginScreen.setFocusable(true);
        loginScreen.setBackground(new Color(0x5B9CFF));
        loginScreen.setLayout(null);
        mainScreen.add(loginScreen);

        lbLoginTitle = new JLabel("QuizDuellen");
        lbLoginTitle.setFont(new Font("Serif", Font.BOLD, 36));
        lbLoginTitle.setBounds(square * 2, 0, square * 4, 50);
        loginScreen.add(lbLoginTitle);

        lbUserName = new JLabel("User Name");
        lbUserName.setBounds(width / 2 - (square * 3) / 2, square, 75, 25);
        loginScreen.add(lbUserName);

        tfLogin = new JTextField();
        tfLogin.setBounds(width / 2 - (square * 3) / 2, square + 25, square * 3, 25);
        loginScreen.add(tfLogin);

        lbUserName = new JLabel("Password");
        lbUserName.setBounds(width / 2 - (square * 3) / 2, square * 2 - 25, 75, 25);
        loginScreen.add(lbUserName);

        tfPassword = new JTextField();
        tfPassword.setBounds(width / 2 - (square * 3) / 2, square * 2, square * 3, 25);
        loginScreen.add(tfPassword);

        btLogin = new JButton("Login");
        btLogin.setBounds(square + square / 2, square * 3, 75, 25);
        loginScreen.add(btLogin);

        btSignUp = new JButton("Sign Up");
        btSignUp.setBounds(square * 2 + square / 2, square * 3, 100, 25);
        loginScreen.add(btSignUp);

        btGuest = new JButton("Guest");
        btGuest.setBounds(square * 4 - 25, square * 3, 75, 25);
        loginScreen.add(btGuest);

        loginScreen.setVisible(false);

        playerInfoBar = new JPanel();
        playerInfoBar.setBounds(0, 0, width, square * 2 - square / 2);
        playerInfoBar.setFocusable(true);
        playerInfoBar.setBackground(new Color(0x5B9CFF));
        playerInfoBar.setLayout(null);
        mainScreen.add(playerInfoBar);

        Database db = new Database();
        BufferedImage image = db.loadImage("old1");
        Icon icon = new ImageIcon(image);

        lbInfoBarPic = new JLabel(icon);
        lbInfoBarPic.setBounds(20, square / 4, square, square);
        playerInfoBar.add(lbInfoBarPic);

        lbInfoBarName = new JLabel("Player 1");
        lbInfoBarName.setBounds(square / 2, 5, square, 25);
        playerInfoBar.add(lbInfoBarName);

        lbInfoBarWins = new JLabel("Wins: 1");
        lbInfoBarWins.setBounds(square / 2, square - square / 6, square, square);
        playerInfoBar.add(lbInfoBarWins);

        BufferedImage image2 = db.loadImage("old2");
        Icon icon2 = new ImageIcon(image2);

        lbInfoBarOpponentPic = new JLabel(icon2);
        lbInfoBarOpponentPic.setBounds(width - square - 20, square / 4, square, square);
        playerInfoBar.add(lbInfoBarOpponentPic);
        lbInfoBarOpponentPic.setVisible(false);

        lbInfoBarOpponentName = new JLabel("Player 2");
        lbInfoBarOpponentName.setBounds(width - square, 5,  square, 25);
        playerInfoBar.add(lbInfoBarOpponentName);
        lbInfoBarOpponentName.setVisible(false);

        lbInfoBarOpponentWins = new JLabel("Wins: 9000");
        lbInfoBarOpponentWins.setBounds(width - square, square - square / 6, square, square);
        playerInfoBar.add(lbInfoBarOpponentWins);
        lbInfoBarOpponentWins.setVisible(false);

        playerInfoBar.setVisible(false);

        homeScreen = new JPanel();
        homeScreen.setBounds(0, square * 2 - square / 2, width, square * 5);
        homeScreen.setFocusable(true);
        homeScreen.setBackground(Color.darkGray);
        homeScreen.setLayout(null);
        mainScreen.add(homeScreen);




    }

    private void setUpActionListeners() {


    }

    public void GUIState(){

    }
}
