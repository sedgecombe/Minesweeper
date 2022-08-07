
public interface MinesweeperModel {

    // sets height of game board
    public void setHeight(int height);

    // returns height of game board
    public int getHeight();

    // sets width of game board
    public void setWidth(int width);

    // returns width of game board
    public int getWidth();

    // sets number of mines in game
    public void setMines(int mines);

    // returns number of mines in games
    public int getMines();

    // respond to a click on square (x, y)
    public void play(int buttonX, int buttonY);

    // give it the reboot
    public void reset();

    // returns whether (x, y) button can be played
    public boolean canPlay(int y, int x);

    // finds if square has been played and does not contain a mine
    public boolean squareSafe(int i, int j);

    // returns if game has been lost
    public boolean gameLost();

    // returns number to be placed on played square;
    public int getNumber(int i, int j);

    // toggles flag on unclicked mines
    public void plantFlag(int x, int y);

    // returns number of unflagged mines
    public int minesLeft();

    // returns whether square is flagged
    public boolean isFlagged(int i, int j);
}
