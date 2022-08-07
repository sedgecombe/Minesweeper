import java.awt.event.ActionListener;

public interface MinesweeperView {

    void setVisible(boolean b);

    // adds listeners for all game squares
    public void addGameListener(ActionListener[][] listenerArray);

    // adds listener for reset button
    public void addResetListener(ActionListener listener);

    void updateView(MinesweeperModel model);

    void resetView(MinesweeperModel model);

}
