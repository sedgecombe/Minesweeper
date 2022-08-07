
public class Minesweeper {
    public static void main(String[] args) {

        MinesweeperModel model = new MinesweeperModel1();
        MinesweeperView view = new MinesweeperView1(model);
        MinesweeperController1 controller = new MinesweeperController1(view,
                model);

        view.setVisible(true);
    }

}
