package tk.dalpiazsolutions.tictactoe.menu;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by michael on 25.03.18.
 */

public class ColorOneSelectListener extends MenuSelectListener {
    public ColorOneSelectListener(MenuModel menuModel) {
        super(menuModel);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        menuModel.setColorPlayerOne(parent.getSelectedItem().toString());
    }
}
