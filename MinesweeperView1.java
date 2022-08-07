import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MinesweeperView1 extends JFrame implements MinesweeperView {

    private int width;
    private int height;
    private int mines;
    private final int boxSize = 20;

    // Variables needed
    MinesweeperModel model;

    private JPanel gameScreen = new JPanel();
    private JPanel topScreen = new JPanel();
    private BorderLayout topLayout = new BorderLayout();

    private JButton[][] gameBoard;
    private JButton resetButton = new JButton("(:");
    private JLabel mineCounter;
    private JLabel timer;

    MinesweeperView1(MinesweeperModel model) {
        this.model = model;

        // get height, width and mine infos from model
        this.height = model.getHeight();
        this.width = model.getWidth();
        this.mines = model.getMines();

        // set length of array
        this.gameBoard = new JButton[this.height][this.width];

        // initialize buttons

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.gameBoard[i][j] = new JButton();
                this.gameBoard[i][j].setOpaque(true);
                this.gameBoard[i][j].setFocusable(false);
            }
        }

        // initialize counter and timer
        this.mineCounter = new JLabel(String.valueOf(this.mines));
        this.timer = new JLabel("0");

        // set up panels
        this.gameScreen.setLayout(new GridLayout(this.height, this.width));
        this.gameScreen.setPreferredSize(new Dimension(
                this.width * this.boxSize, this.height * this.boxSize));

        this.topLayout.setHgap(this.boxSize * this.width / 2);
        this.topScreen.setLayout(this.topLayout);

        // add buttons to gameScreen
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.gameScreen.add(this.gameBoard[i][j]);
            }
        }

        // add topScreen buttons and Labels
        this.topScreen.add(this.resetButton, BorderLayout.CENTER);
        this.topScreen.add(this.mineCounter, BorderLayout.LINE_START);
        this.topScreen.add(this.timer, BorderLayout.LINE_END);

        // set up this and add panels
        this.setLayout(new BorderLayout());
        this.add(this.gameScreen, BorderLayout.CENTER);
        this.add(this.topScreen, BorderLayout.PAGE_START);

        this.resetButton.setFocusable(false);

        this.pack();

    }

    @Override
    public void addGameListener(ActionListener[][] listenerArray) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.gameBoard[i][j].addActionListener(listenerArray[i][j]);
            }
        }

    }

    @Override
    public void addResetListener(ActionListener listener) {
        this.resetButton.addActionListener(listener);

    }

    @Override
    public void updateView(MinesweeperModel model) {
        this.model = model;
        this.mineCounter.setText(String.valueOf(model.minesLeft()));

        if (model.gameLost()) {
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    this.gameBoard[i][j].setEnabled(false);
                    this.resetButton.setText("):");
                }
            }
        } else {

            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    this.gameBoard[i][j].setEnabled(model.canPlay(i, j));
                    if (model.squareSafe(i, j)) {
                        this.gameBoard[i][j].setBackground(Color.GREEN);
                        this.gameBoard[i][j].setText(
                                String.valueOf(this.model.getNumber(i, j)));
                    } else if (model.isFlagged(i, j)) {
                        this.gameBoard[i][j].setBackground(Color.RED);
                    } else {

                        this.gameBoard[i][j].setBackground(Color.GRAY);
                    }
                }
            }
        }

    }

    @Override
    public void resetView(MinesweeperModel model) {
        this.model = model;

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.gameBoard[i][j].setEnabled(true);
                this.gameBoard[i][j].setBackground(Color.GRAY);
                this.gameBoard[i][j].setText("");
            }
        }

        this.resetButton.setText("(:");

    }
}
