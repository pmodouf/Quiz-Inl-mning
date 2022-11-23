package client;

import utility.StaticImageHandler;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/*
TODO lägga till några labels för att visa titel på sidorna
TODO Lägga till action listeners
TODO Eventuellt skala av onödig kod men fan palr inte sitta med swing mer lol
TODO Metoder för att fylla vissa funktioner
 */

public class GameFrame extends JFrame {

    Client client;

    JButton btGiveUp, btQuit, btToggleSound;
    JButton btLogin, btSignUp, btGuest;
    JButton btOption, btChallengeRandom, btChallengeByName, btSearchLeader, btScoreboard, btLogout,
    btCreateUser, btChangeAvatar;
    JButton btAnswer1, btAnswer2, btAnswer3, btAnswer4;

    JButton btOK;
    JButton btUpload;
    JButton btCategory1, btCategory2, btCategory3;
    JButton btSend;
    JButton btBack;

    JLabel lbUserName, lbPassword, lbLoginMessage, lbLoginTitle, lbCreateUser;

    JLabel lbWaitMessage;
    JLabel lbScore1, lbScore2, lbScore3, lbScore4, lbScore5, lbScore6,
            lbOpponentScore1, lbOpponentScore2, lbOpponentScore3, lbOpponentScore4, lbOpponentScore5, lbOpponentScore6,
            lbRound1, lbRound2, lbRound3, lbRound4, lbRound5, lbRound6;

    JLabel lbCategoryMessage;

    JLabel lbLeaderMessage;
    JLabel lbNewUser, lbNewPassword, lbRepeatPassword, lbCreateUserInfo;
    JLabel lbInfoBarName, lbInfoBarOpponentName, lbInfoBarPic, lbInfoBarOpponentPic, lbInfoBarWins, lbInfoBarOpponentWins;

    JTextField tfLogin, tfPassword, tfChat, tfSearchLeader;
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
    JPanel changeAvatar;

    int test = 1;

    private final int square = 96;
    private final int width = square * 6;
    private final int height = square * 8;

    Timer timer;

    public static class Timer extends JPanel implements Runnable{
        public Thread thread;
        public boolean go = false;
        int thirty = 0;
        int indicator = 0;
        int pixel = 450;

        public Timer(){
            thread = new Thread(this);
        }
        @Override
        public void run() {
            double updateInterval = 1000000000 / 60.0;
            double delta = 0;
            long lastUpdate = System.nanoTime();
            long currentTime;
            while (thread.isAlive()){
                currentTime = System.nanoTime();
                delta += (currentTime - lastUpdate) / updateInterval;
                lastUpdate = currentTime;
                if (delta >= 1){
                    repaint();
                    delta--;
                }
            }
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if(go){
                indicator++;
                g2.setColor(Color.red);
                if (indicator > 3) {
                    pixel--;
                    indicator = 0;
                }
                thirty++;
                if(thirty > 1800){
                    go = false;
                }
            } else {
                g2.setColor(new Color(0x5B9CFF));
                thirty = 0;
                pixel = 450;
            }
            g2.fillRect(63, 0, pixel, 10);
            g2.dispose();
        }
    }

    public GameFrame(Client client) {
        this.client = client;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Quiz");

        setUpComponents();
        setUpActionListeners();

        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        GUIState(1);

    }

    private void setUpComponents() {

        setUpMainScreen();

        setUpCountBar();

        setUpLoginScreen();

        setUpPlayerInfoBar();

        setUpHomeScreen();

        setUpCreateAccountScreen();

        setUpWaitScreen();

        setUpGameScreen();

        setUpChatScreen();

        setUpCategoryScreen();

        setUpScoreScreen();

        setUpOptionScreen();

        setUpLeaderboard();


    }
    private void setUpActionListeners() {
        ActionListener al = e -> {
            JButton bt = (JButton) e.getSource();
            if(bt.equals(btQuit)){
                test++;
                if(test == 10){
                    test = 0;
                }
                GUIState(test);
            }else{
                if (timer.go){
                    timer.go = false;
                }else{
                    timer.go = true;
                }
            }
        };
        btQuit.addActionListener(al);
        btToggleSound.addActionListener(al);
    }

    public void GUIState(int state){
        final int loginScreenState = 1;
        final int createAccountScreenState = 2;
        final int homeScreenState = 3;
        final int gameScreenState = 4;
        final int waitScreenState = 5;
        final int categoryScreenState = 6;
        final int scoreScreenState = 7;
        final int optionScreenState = 8;
        final int leaderboardState = 9;

        switch (state){
            case loginScreenState -> {
                timer.setVisible(false);
                chatScreen.setVisible(false);
                loginScreen.setVisible(true);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(false);
                    lbInfoBarOpponentPic.setVisible(false);
                    lbInfoBarOpponentName.setVisible(false);
                    lbInfoBarOpponentWins.setVisible(false);
                gameScreen.setVisible(false);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(false);
                leaderboard.setVisible(false);
                btBack.setVisible(false);
                btGiveUp.setVisible(false);
            }case createAccountScreenState -> {
                timer.setVisible(false);
                chatScreen.setVisible(false);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(true);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(false);
                    lbInfoBarOpponentPic.setVisible(false);
                    lbInfoBarOpponentName.setVisible(false);
                    lbInfoBarOpponentWins.setVisible(false);
                gameScreen.setVisible(false);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(false);
                leaderboard.setVisible(false);
                btBack.setVisible(true);
                btGiveUp.setVisible(false);
            }case homeScreenState -> {
                timer.setVisible(false);
                chatScreen.setVisible(false);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(true);
                playerInfoBar.setVisible(true);
                    lbInfoBarOpponentPic.setVisible(false);
                    lbInfoBarOpponentName.setVisible(false);
                    lbInfoBarOpponentWins.setVisible(false);
                gameScreen.setVisible(false);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(false);
                leaderboard.setVisible(false);
                btBack.setVisible(false);
                btGiveUp.setVisible(false);
            }case gameScreenState -> {
                timer.setVisible(true);
                chatScreen.setVisible(true);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(true);
                    lbInfoBarOpponentPic.setVisible(true);
                    lbInfoBarOpponentName.setVisible(true);
                    lbInfoBarOpponentWins.setVisible(true);
                gameScreen.setVisible(true);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(false);
                leaderboard.setVisible(false);
                btBack.setVisible(false);
                btGiveUp.setVisible(true);
            }case waitScreenState -> {
                timer.setVisible(false);
                chatScreen.setVisible(true);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(true);
                    lbInfoBarOpponentPic.setVisible(true);
                    lbInfoBarOpponentName.setVisible(true);
                    lbInfoBarOpponentWins.setVisible(true);
                gameScreen.setVisible(false);
                waitScreen.setVisible(true);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(false);
                leaderboard.setVisible(false);
                btBack.setVisible(false);
                btGiveUp.setVisible(true);
            }case categoryScreenState -> {
                timer.setVisible(true);
                chatScreen.setVisible(true);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(true);
                    lbInfoBarOpponentPic.setVisible(true);
                    lbInfoBarOpponentName.setVisible(true);
                    lbInfoBarOpponentWins.setVisible(true);
                gameScreen.setVisible(false);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(true);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(false);
                leaderboard.setVisible(false);
                btBack.setVisible(false);
                btGiveUp.setVisible(true);
            }case scoreScreenState -> {
                timer.setVisible(false);
                chatScreen.setVisible(false);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(true);
                    lbInfoBarOpponentPic.setVisible(true);
                    lbInfoBarOpponentName.setVisible(true);
                    lbInfoBarOpponentWins.setVisible(true);
                gameScreen.setVisible(false);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(true);
                optionScreen.setVisible(false);
                leaderboard.setVisible(false);
                btBack.setVisible(true);
                btGiveUp.setVisible(false);
            }case optionScreenState -> {
                timer.setVisible(false);
                chatScreen.setVisible(false);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(false);
                    lbInfoBarOpponentPic.setVisible(false);
                    lbInfoBarOpponentName.setVisible(false);
                    lbInfoBarOpponentWins.setVisible(false);
                gameScreen.setVisible(false);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(true);
                leaderboard.setVisible(false);
                btBack.setVisible(true);
                btGiveUp.setVisible(false);
            }case leaderboardState -> {
                timer.setVisible(false);
                chatScreen.setVisible(false);
                loginScreen.setVisible(false);
                createAccountScreen.setVisible(false);
                homeScreen.setVisible(false);
                playerInfoBar.setVisible(false);
                    lbInfoBarOpponentPic.setVisible(false);
                    lbInfoBarOpponentName.setVisible(false);
                    lbInfoBarOpponentWins.setVisible(false);
                gameScreen.setVisible(false);
                waitScreen.setVisible(false);
                categoryScreen.setVisible(false);
                scoreScreen.setVisible(false);
                optionScreen.setVisible(false);
                leaderboard.setVisible(true);
                btBack.setVisible(true);
                btGiveUp.setVisible(false);
            }
        }
    }
    private void setUpMainScreen() {
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
    }
    private void setUpCountBar() {
        timer = new Timer();
        timer.setBounds(0, width / 2 + square * 2 + 10 , width, 10);
        timer.setBackground(new Color(0x5B9CFF));
        timer.setLayout(null);
        mainScreen.add(timer);
        timer.thread.start();
    }

    private void setUpLoginScreen() {
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
    }

    private void setUpPlayerInfoBar() {
        playerInfoBar = new JPanel();
        playerInfoBar.setBounds(0, 0, width, square * 2 - square / 2);
        playerInfoBar.setFocusable(true);
        playerInfoBar.setBackground(new Color(0x428CFD));
        playerInfoBar.setLayout(null);
        mainScreen.add(playerInfoBar);

        BufferedImage image = StaticImageHandler.loadImage("old1");
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

        BufferedImage image2 = StaticImageHandler.loadImage("old2");
        Icon icon2 = new ImageIcon(image2);

        lbInfoBarOpponentPic = new JLabel(icon2);
        lbInfoBarOpponentPic.setBounds(width - square - 20, square / 4, square, square);
        playerInfoBar.add(lbInfoBarOpponentPic);
        lbInfoBarOpponentPic.setVisible(true);

        lbInfoBarOpponentName = new JLabel("Player 2");
        lbInfoBarOpponentName.setBounds(width - square, 5,  square, 25);
        playerInfoBar.add(lbInfoBarOpponentName);
        lbInfoBarOpponentName.setVisible(true);

        lbInfoBarOpponentWins = new JLabel("Wins: 9000");
        lbInfoBarOpponentWins.setBounds(width - square, square - square / 6, square, square);
        playerInfoBar.add(lbInfoBarOpponentWins);
        lbInfoBarOpponentWins.setVisible(true);

        playerInfoBar.setVisible(true);
    }

    private void setUpHomeScreen() {
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
    }

    private void setUpCreateAccountScreen() {
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
    }

    private void setUpWaitScreen() {

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
    }

    private void setUpGameScreen() {
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

        btAnswer1 = new JButton("FRÅGA 1");
        btAnswer1.setBounds(square / 4, square * 2 + 9 ,(width - square / 2) / 2 ,square - 5);
        gameScreen.add(btAnswer1);

        btAnswer2 = new JButton("FRÅGA 2");
        btAnswer2.setBounds(width / 2, square * 2 + 9 ,(width - square / 2) / 2 ,square- 5);
        gameScreen.add(btAnswer2);

        btAnswer3 = new JButton("FRÅGA 3");
        btAnswer3.setBounds(square / 4, square * 3 + 4,(width - square / 2) / 2 ,square - 5);
        gameScreen.add(btAnswer3);

        btAnswer4 = new JButton("FRÅGA 4");
        btAnswer4.setBounds(width / 2, square * 3 + 4,(width - square / 2) / 2 ,square - 5);
        gameScreen.add(btAnswer4);

        gameScreen.setVisible(true);
    }

    private void setUpChatScreen() {
        chatScreen = new JPanel();
        chatScreen.setBounds(0, width / 2 + square * 2 + 20, width, square * 2 + square / 4);
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

        chatScreen.setVisible(true);
    }

    private void setUpCategoryScreen() {
        categoryScreen = new JPanel();
        categoryScreen.setBounds(0, square, width, height / 2);
        categoryScreen.setFocusable(true);
        categoryScreen.setBackground(new Color(0x5B9CFF));
        categoryScreen.setLayout(null);
        mainScreen.add(categoryScreen);

        btCategory1 = new JButton("CATEGORY1");
        btCategory1.setBounds(width / 2 - (square / 2) * 3, square / 2 + 12, square * 3, square);
        categoryScreen.add(btCategory1);

        btCategory2 = new JButton("CATEGORY2");
        btCategory2.setBounds(width / 2 - (square / 2) * 3, square * 2 - square / 4  + 6, square * 3, square);
        categoryScreen.add(btCategory2);

        btCategory3 = new JButton("CATEGORY3");
        btCategory3.setBounds(width / 2 - (square / 2) * 3, square * 3, square * 3, square);
        categoryScreen.add(btCategory3);

        categoryScreen.setVisible(false);
    }

    private void setUpScoreScreen() {
        scoreScreen = new JPanel();
        scoreScreen.setBounds(0, square, width, square * 6);
        scoreScreen.setFocusable(true);
        scoreScreen.setBackground(new Color(0x5B9CFF));
        scoreScreen.setLayout(null);
        mainScreen.add(scoreScreen);

        lbScore1 = new JLabel();
        lbScore1.setBounds(square / 2, square, square * 3, 30);
        lbScore1.setIcon(new ImageIcon(StaticImageHandler.scoreImage(6)));
        scoreScreen.add(lbScore1);

        lbScore2 = new JLabel();
        lbScore2.setBounds(square / 2, square + 80, square * 3, 30);
        lbScore2.setIcon(new ImageIcon(StaticImageHandler.scoreImage(7)));
        scoreScreen.add(lbScore2);

        lbScore3 = new JLabel();
        lbScore3.setBounds(square / 2, square + 160, square * 3, 30);
        lbScore3.setIcon(new ImageIcon(StaticImageHandler.scoreImage(7)));
        scoreScreen.add(lbScore3);

        lbScore4 = new JLabel();
        lbScore4.setBounds(square / 2, square + 240, square * 3, 30);
        lbScore4.setIcon(new ImageIcon(StaticImageHandler.scoreImage(7)));
        scoreScreen.add(lbScore4);

        lbScore5 = new JLabel();
        lbScore5.setBounds(square / 2, square + 320, square * 3, 30);
        lbScore5.setIcon(new ImageIcon(StaticImageHandler.scoreImage(7)));
        scoreScreen.add(lbScore5);

        lbScore6 = new JLabel();
        lbScore6.setBounds(square / 2, square + 400, square * 3, 30);
        lbScore6.setIcon(new ImageIcon(StaticImageHandler.scoreImage(7)));
        scoreScreen.add(lbScore6);

        lbOpponentScore1 = new JLabel();
        lbOpponentScore1.setBounds(width - square * 2, square, square * 3, 30);
        lbOpponentScore1.setIcon(new ImageIcon(StaticImageHandler.scoreImage(6)));
        scoreScreen.add(lbOpponentScore1);

        lbOpponentScore2 = new JLabel();
        lbOpponentScore2.setBounds(width - square * 2, square + 80, square * 3, 30);
        lbOpponentScore2.setIcon(new ImageIcon(StaticImageHandler.scoreImage(4)));
        scoreScreen.add(lbOpponentScore2);

        lbOpponentScore3 = new JLabel();
        lbOpponentScore3.setBounds(width - square * 2, square + 160, square * 3, 30);
        lbOpponentScore3.setIcon(new ImageIcon(StaticImageHandler.scoreImage(7)));
        scoreScreen.add(lbOpponentScore3);

        lbOpponentScore4 = new JLabel();
        lbOpponentScore4.setBounds(width - square * 2, square + 240, square * 3, 30);
        lbOpponentScore4.setIcon(new ImageIcon(StaticImageHandler.scoreImage(3)));
        scoreScreen.add(lbOpponentScore4);

        lbOpponentScore5 = new JLabel();
        lbOpponentScore5.setBounds(width - square * 2, square + 320, square * 3, 30);
        lbOpponentScore5.setIcon(new ImageIcon(StaticImageHandler.scoreImage(7)));
        scoreScreen.add(lbOpponentScore5);

        lbOpponentScore6 = new JLabel();
        lbOpponentScore6.setBounds(width - square * 2, square + 400, square * 3, 30);
        lbOpponentScore6.setIcon(new ImageIcon(StaticImageHandler.scoreImage(8)));
        scoreScreen.add(lbOpponentScore6);

        lbRound1 = new JLabel("Round 1");
        lbRound1.setBounds(width / 2 - square / 4, square, square, 30);
        scoreScreen.add(lbRound1);

        lbRound2 = new JLabel("Round 2");
        lbRound2.setBounds(width / 2 - square / 4, square + 80, square, 30);
        scoreScreen.add(lbRound2);

        lbRound3 = new JLabel("Round 3");
        lbRound3.setBounds(width / 2 - square / 4, square + 160, square, 30);
        scoreScreen.add(lbRound3);

        lbRound4 = new JLabel("Round 4");
        lbRound4.setBounds(width / 2 - square / 4, square + 240, square, 30);
        scoreScreen.add(lbRound4);

        lbRound5 = new JLabel("Round 5");
        lbRound5.setBounds(width / 2 - square / 4, square + 320, square, 30);
        scoreScreen.add(lbRound5);

        lbRound6 = new JLabel("Round 6");
        lbRound6.setBounds(width / 2 - square / 4, square + 400, square, 30);
        scoreScreen.add(lbRound6);

//        btOK = new JButton("OK");
//        btOK.setBounds(width / 2 - 50, square + 450, 100, 30);
//        scoreScreen.add(btOK);

        scoreScreen.setVisible(false);
    }

    private void setUpOptionScreen() {
        optionScreen = new JPanel();
        optionScreen.setBounds(0, square, width, square * 6);
        optionScreen.setFocusable(true);
        optionScreen.setBackground(new Color(0x5B9CFF));
        optionScreen.setLayout(null);
        mainScreen.add(optionScreen);

        btUpload = new JButton("Upload Profile Image");
        btUpload.setBounds(width / 2 - (square / 2) * 2, square, square * 2, square / 2);
        optionScreen.add(btUpload);

        btChangeAvatar = new JButton("Change Avatar");
        btChangeAvatar.setBounds(width / 2 - (square / 2) * 2, square * 3, square * 2, square / 2);
        optionScreen.add(btChangeAvatar);

        optionScreen.setVisible(false);
    }

    private void setUpLeaderboard() {
        leaderboard = new JPanel();
        leaderboard.setBounds(0, square, width, square * 6);
        leaderboard.setFocusable(true);
        leaderboard.setBackground(new Color(0x5B9CFF));
        leaderboard.setLayout(null);
        mainScreen.add(leaderboard);

        tfSearchLeader = new JTextField();
        tfSearchLeader.setBounds(square / 4, square / 6, width - square - square / 3, 25);
        leaderboard.add(tfSearchLeader);

        btSearchLeader = new JButton("Search");
        btSearchLeader.setBounds(width - square - 3 ,square / 6, 75, 25);
        leaderboard.add(btSearchLeader);

        ScrollPane sp2 = new ScrollPane();
        sp2.setBounds(square / 4, square / 2, width - square / 2, height / 2 + square);
        tpScoreboard = new JTextPane();
        tpScoreboard.setBackground(new Color(0x7EA9F8));
        tpScoreboard.setEditable(false);
        sp2.add(tpScoreboard);
        leaderboard.add(sp2);

        leaderboard.setVisible(false);
    }
}