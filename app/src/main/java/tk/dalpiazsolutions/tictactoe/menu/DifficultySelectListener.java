package tk.dalpiazsolutions.tictactoe.menu;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by michael on 25.03.18.
 */

public class DifficultySelectListener extends MenuSelectListener {
    public DifficultySelectListener(MenuModel menuModel) {
        super(menuModel);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        menuModel.setDifficultyMode(
                parent.getSelectedItemPosition()
        );
    }
}
