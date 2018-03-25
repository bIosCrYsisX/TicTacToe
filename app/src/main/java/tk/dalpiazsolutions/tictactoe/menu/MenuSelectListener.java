package tk.dalpiazsolutions.tictactoe.menu;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by michael on 25.03.18.
 */

public abstract class MenuSelectListener implements AdapterView.OnItemSelectedListener {
    protected final MenuModel menuModel;

    public MenuSelectListener(MenuModel menuModel){
        this.menuModel = menuModel;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
