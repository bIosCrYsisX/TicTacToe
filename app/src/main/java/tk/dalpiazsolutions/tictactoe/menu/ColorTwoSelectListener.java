package tk.dalpiazsolutions.tictactoe.menu;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by michael on 25.03.18.
 */

public class ColorTwoSelectListener extends MenuSelectListener {
    public ColorTwoSelectListener(MenuModel menuModel) {
        super(menuModel);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        menuModel.setColorPlayerTwo(parent.getSelectedItem().toString());
    }
}
