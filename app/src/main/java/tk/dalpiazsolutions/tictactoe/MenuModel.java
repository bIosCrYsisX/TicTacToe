package tk.dalpiazsolutions.tictactoe;

/**
 * Created by michael on 25.03.18.
 */

public class MenuModel {
    private String background;
    private String colorPlayerOne;
    private String colorPlayerTwo;
    private int difficultyMode;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getColorPlayerOne() {
        return colorPlayerOne;
    }

    public void setColorPlayerOne(String colorPlayerOne) {
        this.colorPlayerOne = colorPlayerOne;
    }

    public String getColorPlayerTwo() {
        return colorPlayerTwo;
    }

    public void setColorPlayerTwo(String colorPlayerTwo) {
        this.colorPlayerTwo = colorPlayerTwo;
    }

    public int getDifficultyMode() {
        return difficultyMode;
    }

    public void setDifficultyMode(int difficultyMode) {
        this.difficultyMode = difficultyMode;
    }
}
