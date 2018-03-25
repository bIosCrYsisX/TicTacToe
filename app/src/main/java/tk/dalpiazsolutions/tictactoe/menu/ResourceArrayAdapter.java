package tk.dalpiazsolutions.tictactoe.menu;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by michael on 25.03.18.
 */

public class ResourceArrayAdapter extends ArrayAdapter<String> {
    public ResourceArrayAdapter(@NonNull Context context, @ArrayRes int arrayResource) {
        super(context, android.R.layout.simple_list_item_1);

        this.addAll(context
                        .getResources()
                        .getStringArray(arrayResource));
    }
}
