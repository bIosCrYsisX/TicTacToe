package tk.dalpiazsolutions.tictactoe.menu;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 25.03.18.
 */

public class MenuModel {
    private String background;
    private String colorPlayerOne;
    private String colorPlayerTwo;
    private int difficultyMode;

    private List<MenuModelListener> menuModelListeners;

    public MenuModel() {
        menuModelListeners = new LinkedList<>();
    }

    public void addModelListener(MenuModelListener listener){
        menuModelListeners.add(listener);
    }

    public void removeModelListener(MenuModelListener listener){
        menuModelListeners.remove(listener);
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
        firePropertyChanged();
    }

    public String getColorPlayerOne() {
        return colorPlayerOne;
    }

    public void setColorPlayerOne(String colorPlayerOne) {
        this.colorPlayerOne = colorPlayerOne;
        firePropertyChanged();
    }

    public String getColorPlayerTwo() {
        return colorPlayerTwo;
    }

    public void setColorPlayerTwo(String colorPlayerTwo) {
        this.colorPlayerTwo = colorPlayerTwo;
        firePropertyChanged();
    }

    public int getDifficultyMode() {
        return difficultyMode;
    }

    public void setDifficultyMode(int difficultyMode) {
        this.difficultyMode = difficultyMode;
        firePropertyChanged();
    }

    private void firePropertyChanged(){
        for (MenuModelListener listener :
                menuModelListeners) {
            listener.onPropertyChanged();
        }
    }
}
