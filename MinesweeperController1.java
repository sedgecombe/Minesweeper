import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MinesweeperController1 implements MinesweeperController {

    private MinesweeperView view;
    private MinesweeperModel model;

    private int height;
    private int width;

    public MinesweeperController1(MinesweeperView view,
            MinesweeperModel model) {
        this.view = view;
        this.model = model;

        this.height = model.getHeight();
        this.width = model.getWidth();

        GameListener[][] gameListener = new GameListener[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                gameListener[i][j] = new GameListener(i, j);
            }
        }

        view.addGameListener(gameListener);
        view.addResetListener(new ResetListener());
    }

    // Listener for game buttons
    class GameListener implements ActionListener {

        // ids to keep track of game button
        private int buttonX;
        private int buttonY;

        public GameListener(int y, int x) {
            this.buttonX = x;
            this.buttonY = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MinesweeperController1.this.model.play(this.buttonX, this.buttonY);
            MinesweeperController1.this.view
                    .updateView(MinesweeperController1.this.model);

        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 32) {
                MinesweeperController1.this.model.plantFlag(this.buttonX,
                        this.buttonY);
            }
        }

    }

    // Listener for reset button
    class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MinesweeperController1.this.model.reset();
            MinesweeperController1.this.view
                    .resetView(MinesweeperController1.this.model);

        }

    }

}
