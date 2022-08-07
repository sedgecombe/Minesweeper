
public class MinesweeperModel1 implements MinesweeperModel {

    // all the variables
    int height = 16;
    int width = 30;
    int mines = 99;
    int minesLeft = 99;
    int squares[][] = new int[this.height][this.width];
    boolean kaboom = false;
    boolean firstMove = true;

    @Override
    public void setHeight(int height) {
        this.height = height;

    }

    @Override
    public int getHeight() {

        return this.height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;

    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public void setMines(int mines) {
        this.mines = mines;

    }

    @Override
    public int getMines() {

        return this.mines;
    }

    @Override
    public void play(int x, int y) {

        if (this.squares[y][x] < 0) {
            this.kaboom = true;
        } else {
            this.squares[y][x] = 1;
        }

        if (this.firstMove) {
            this.setUpMines();
            this.firstMove = false;
        }
    }

    @Override
    public void reset() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.squares[i][j] = 0;
            }
        }

        this.kaboom = false;
        this.firstMove = true;

    }

    @Override
    public boolean canPlay(int y, int x) {

        return this.squares[y][x] < 1;
    }

    @Override
    public boolean squareSafe(int i, int j) {

        return this.squares[i][j] == 1;
    }

    private void setUpMines() {
        int minesRemaining = this.mines;
        while (minesRemaining > 0) {
            int i = (int) (Math.random() * this.height);
            int j = (int) (Math.random() * this.width);

            if (this.squares[i][j] == 0) {
                this.squares[i][j] = -1;
                minesRemaining--;
            }
        }
    }

    @Override
    public boolean gameLost() {

        return this.kaboom;
    }

    @Override
    public int getNumber(int y, int x) {
        int sum = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // make sure square isn't off the edge
                if (x + i >= 0 && x + i < this.width) {
                    if (y + j >= 0 && y + j < this.height) {
                        // check if square is mine
                        if (this.squares[y + j][x + i] == -1) {
                            sum++;
                        }
                    }
                }
            }
        }
        return sum;
    }

    @Override
    public void plantFlag(int x, int y) {
        if (this.squares[y][x] < 1) {
            if (this.squares[y][x] == -2) {
                this.squares[y][x] = 0;
                this.minesLeft++;
            } else {
                this.squares[y][x] = -2;
                this.minesLeft--;
            }
        }

    }

    @Override
    public int minesLeft() {

        return this.minesLeft;
    }

    @Override
    public boolean isFlagged(int i, int j) {

        return this.squares[i][j] == -2;
    }

}
