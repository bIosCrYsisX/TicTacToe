package tk.dalpiazsolutions.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MenuActivity extends AppCompatActivity {

    Button mbuttonOoO;
    Button mbuttonKIs;
    Switch switchKI;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mbuttonOoO = findViewById(R.id.buttonOneonOne);
        mbuttonKIs = findViewById(R.id.buttonYouvsKI);
        switchKI = findViewById(R.id.switchKistarts);

        preferences = getSharedPreferences("modeFile", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        mbuttonOoO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("mode", 0);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mbuttonKIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchKI.isChecked()==true)
                {
                    editor.putInt("mode", 2);
                }

                else
                {
                    editor.putInt("mode", 1);
                }
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
