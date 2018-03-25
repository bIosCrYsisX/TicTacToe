package tk.dalpiazsolutions.tictactoe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by michael on 25.03.18.
 */

public class StringArrayAdapter extends ArrayAdapter<String> {
    public StringArrayAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }
}
