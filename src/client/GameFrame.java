package client;


import database.Database;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {

    Client client;

    JButton btGiveUp, btQuit, btToggleSound;
    JButton btLogin, btSignUp, btGuest;
    JButton btOption, btChallengeRandom, btChallengeByName, btSearchOpponent, btSearchLeader, btScoreboard, btLogout,
    btCreateUser;
    JButton btQuestion1, btQuestion2, btQuestion3, btQuestion4;
    JButton btOK;
    JButton btCategory1, btCategory2, btCategory3;
    JButton btSend;
    JButton btBack;

    JLabel lbUserName, lbPassword, lbLoginMessage, lbLoginTitle, lbCreateUser;
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

        lbPassword = new JLabel("Password");
        lbPassword.setBounds(width / 2 - (square * 3) / 2, square * 2 - 25, 75, 25);
        loginScreen.add(lbPassword);

        tfPassword = new JTextField();
        tfPassword.setBounds(width / 2 - (square * 3) / 2, square * 2, square * 3, 25);
        loginScreen.add(tfPassword);

        lbLoginMessage = new JLabel("Wrong login information");
        lbLoginMessage.setBounds(width / 2 - (square + square / 2) / 2, square * 2 + square / 2, square + square / 2, 25);
        lbLoginMessage.setVisible(false);
        loginScreen.add(lbLoginMessage);

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
        playerInfoBar.setBackground(new Color(0x428CFD));
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

        playerInfoBar.setVisible(true);

        homeScreen = new JPanel();
        homeScreen.setBounds(0, square * 2 - square / 2, width, square * 5);
        homeScreen.setFocusable(false);
        homeScreen.setBackground(new Color(0x5B9CFF));
        homeScreen.setLayout(null);
        mainScreen.add(homeScreen);

        btChallengeRandom = new JButton("Quick Match");
        btChallengeRandom.setBounds(width / 2 - square, square / 2, square * 2, square / 2);
        homeScreen.add(btChallengeRandom);

        btChallengeByName = new JButton("Challenge a Friend");
        btChallengeByName.setBounds(width / 2 - square, square + square / 2 , square * 2, square / 2);
        homeScreen.add(btChallengeByName);

        btOption = new JButton("Configure Profile");
        btOption.setBounds(width / 2 - square, square * 2 + square / 2, square * 2, square / 2);
        homeScreen.add(btOption);

        btScoreboard= new JButton("Leaderboard");
        btScoreboard.setBounds(width / 2 - square, square * 3 + square / 2, square * 2, square / 2);
        homeScreen.add(btScoreboard);

        btLogout = new JButton("Logout");
        btLogout.setBounds( width / 2 - 40, square * 4 + square / 2, 80, 25);
        homeScreen.add(btLogout);

        homeScreen.setVisible(false);

        createAccountScreen = new JPanel();
        createAccountScreen.setBounds(0, square, width, height / 2);
        createAccountScreen.setFocusable(true);
        createAccountScreen.setBackground(new Color(0x5B9CFF));
        createAccountScreen.setLayout(null);
        mainScreen.add(createAccountScreen);

        lbCreateUser = new JLabel("Create User");
        lbCreateUser.setFont(new Font("Serif", Font.BOLD, 36));
        lbCreateUser.setBounds(square * 2, 0, square * 4, 50);
        createAccountScreen.add(lbCreateUser);

        lbNewUser = new JLabel("User Name");
        lbNewUser.setBounds(width / 2 - (square * 3) / 2, square, 75, 25);
        createAccountScreen.add(lbNewUser);

        tfNewLogin = new JTextField();
        tfNewLogin.setBounds(width / 2 - (square * 3) / 2, square + 25, square * 3, 25);
        createAccountScreen.add(tfNewLogin);

        lbNewPassword = new JLabel("Password");
        lbNewPassword.setBounds(width / 2 - (square * 3) / 2, square * 2 - 25, 75, 25);
        createAccountScreen.add(lbNewPassword);

        tfNewPassword = new JTextField();
        tfNewPassword.setBounds(width / 2 - (square * 3) / 2, square * 2, square * 3, 25);
        createAccountScreen.add(tfNewPassword);

        lbRepeatPassword = new JLabel("Confirm Password");
        lbRepeatPassword.setBounds(width / 2 - (square * 3) / 2, square * 2 + 46, square * 3, 25);
        createAccountScreen.add(lbRepeatPassword);

        tfRepeatPassword = new JTextField();
        tfRepeatPassword.setBounds(width / 2 - (square * 3) / 2, square * 2 + 71, square * 3, 25);
        createAccountScreen.add(tfRepeatPassword);

        lbCreateUserInfo = new JLabel("FELMEDELANDE!");
        lbCreateUserInfo.setBounds(width / 2 - (square * 3) / 2, square * 3 + 10, square * 3, 25);
        createAccountScreen.add(lbCreateUserInfo);

        btCreateUser = new JButton("Create User");
        btCreateUser.setBounds(square + square / 2, square * 3 + square / 2, 150, 25);
        createAccountScreen.add(btCreateUser);

        createAccountScreen.setVisible(false);

        waitScreen = new JPanel();
        waitScreen.setBounds(0, square, width, height / 2);
        waitScreen.setFocusable(true);
        waitScreen.setBackground(new Color(0x5B9CFF));
        waitScreen.setLayout(null);
        mainScreen.add(waitScreen);

        lbWaitMessage = new JLabel("Waiting for Category");
        lbWaitMessage.setFont(new Font("Serif", Font.BOLD, 36));
        lbWaitMessage.setBounds(square + square / 3, height / 6, square * 4, 50);
        waitScreen.add(lbWaitMessage);

        waitScreen.setVisible(false);

        gameScreen = new JPanel();
        gameScreen.setBounds(0, square, width, height / 2);
        gameScreen.setFocusable(true);
        gameScreen.setBackground(new Color(0x5B9CFF));
        gameScreen.setLayout(null);
        mainScreen.add(gameScreen);

        tpQuestion = new JTextPane();
        tpQuestion.setBounds(square / 4, square / 2, width - square / 2, height / 5);
        tpQuestion.setEditable(false);
        tpQuestion.setBackground(new Color(0x5B9CFF));
        tpQuestion.setText("HÄR KOMMER DET VARA FRÅGOR BLBA LBA LBAL BLA BLA BLA BLA BLA");
        StyledDocument doc = tpQuestion.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        tpQuestion.setForeground(Color.DARK_GRAY);
        tpQuestion.setFont(new Font("Serif", Font.BOLD, 24));
        gameScreen.add(tpQuestion);

        btQuestion1 = new JButton("FRÅGA 1");
        btQuestion1.setBounds(square / 4, square * 2 + 9 ,(width - square / 2) / 2 ,square - 5);
        gameScreen.add(btQuestion1);

        btQuestion2 = new JButton("FRÅGA 2");
        btQuestion2.setBounds(width / 2, square * 2 + 9 ,(width - square / 2) / 2 ,square- 5);
        gameScreen.add(btQuestion2);

        btQuestion3 = new JButton("FRÅGA 3");
        btQuestion3.setBounds(square / 4, square * 3 + 4,(width - square / 2) / 2 ,square - 5);
        gameScreen.add(btQuestion3);

        btQuestion4 = new JButton("FRÅGA 4");
        btQuestion4.setBounds(width / 2, square * 3 + 4,(width - square / 2) / 2 ,square - 5);
        gameScreen.add(btQuestion4);

        chatScreen = new JPanel();
        chatScreen.setBounds(0, width / 2 + square * 2, width, square * 2 + square / 4);
        chatScreen.setFocusable(true);
        chatScreen.setBackground(new Color(0x5B9CFF));
        chatScreen.setLayout(null);
        mainScreen.add(chatScreen);

        ScrollPane sp = new ScrollPane();
        sp.setBounds(square / 4, 10, width - square / 2, height / 5);
        tpChat = new JTextPane();
        tpChat.setEditable(false);
        sp.add(tpChat);
        chatScreen.add(sp);

        tfChat = new JTextField();
        tfChat.setBounds(square / 4, height / 5 + 20, width - square - square / 3, 25);
        chatScreen.add(tfChat);

        btSend = new JButton("Send");
        btSend.setBounds(width - square - 3 ,height / 5 + 20, 75, 25);
        chatScreen.add(btSend);

        chatScreen.setVisible(false);
        gameScreen.setVisible(false);

    }

    private void setUpActionListeners() {


    }

    public void GUIState(){

    }
}
