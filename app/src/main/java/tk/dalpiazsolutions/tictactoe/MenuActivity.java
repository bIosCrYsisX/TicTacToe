package tk.dalpiazsolutions.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button mbuttonOoO;
    Button mbuttonKIs;
    Switch switchKI;
    SharedPreferences preferences;
    Spinner spinnerBackground;
    Spinner colorOne;
    Spinner colorTwo;
    Spinner difficultySpinner;
    String background;
    String colorPlayerOne;
    String colorPlayerTwo;
    TextView backgroundColor;
    TextView textColorOne;
    TextView textColorTwo;
    int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mbuttonOoO = findViewById(R.id.buttonOneonOne);
        mbuttonKIs = findViewById(R.id.buttonYouvsKI);
        switchKI = findViewById(R.id.switchKistarts);
        backgroundColor = findViewById(R.id.backgroundColor);
        textColorTwo = findViewById(R.id.skin2);
        textColorOne = findViewById(R.id.skin1);

        preferences = getSharedPreferences("modeFile", MODE_PRIVATE);
        background = preferences.getString("background", getString(R.string.orange));
        colorPlayerOne = preferences.getString("colorOne", getString(R.string.skinRed));
        colorPlayerTwo = preferences.getString("colorTwo", getString(R.string.skinYellow));
        final SharedPreferences.Editor editor = preferences.edit();

        difficulty = preferences.getInt("difficulty", 0);

        spinnerBackground = findViewById(R.id.spinnerBackground);
        spinnerBackground.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> arrayAdapterBackground = ArrayAdapter.createFromResource(getApplicationContext(), R.array.backgrounds_array, android.R.layout.simple_spinner_item);
        arrayAdapterBackground.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBackground.setAdapter(arrayAdapterBackground);

        for(int i = 0; i < spinnerBackground.getCount(); i++)
        {
            if(spinnerBackground.getItemAtPosition(i).toString().equals(background))
            {
                spinnerBackground.setSelection(arrayAdapterBackground.getPosition(background));
            }
        }

        difficultySpinner = findViewById(R.id.spinnerDifficulty);


        difficultySpinner.setSelection(difficulty);

        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals(getString(R.string.simple)))
                {
                    editor.putInt("difficulty", 0);
                    editor.apply();
                }

                else if(adapterView.getItemAtPosition(i).toString().equals(getString(R.string.medium)))
                {
                    editor.putInt("difficulty", 1);
                }

                else if(adapterView.getItemAtPosition(i).toString().equals(getString(R.string.difficult)))
                {
                    editor.putInt("difficulty", 2);
                    editor.apply();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> arrayAdapterDifficulty = ArrayAdapter.createFromResource(getApplicationContext(), R.array.difficulty_array, android.R.layout.simple_spinner_item);
        arrayAdapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(arrayAdapterDifficulty);

        difficultySpinner.setSelection(difficulty);

        colorOne = findViewById(R.id.spinnerColorOne);
        colorOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                colorPlayerOne = adapterView.getItemAtPosition(i).toString();
                checkColorAndSetText(textColorOne, colorPlayerOne);
                editor.putString("colorOne", colorPlayerOne);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
             //   colorPlayerOne = preferences.getString("colorOne", getString(R.string.skinRed));
              //  checkColorAndSetText(textColorOne, colorPlayerOne);
            }
        });
        ArrayAdapter<CharSequence> arrayAdapterColorOne = ArrayAdapter.createFromResource(getApplicationContext(), R.array.colorOne_array, android.R.layout.simple_spinner_item);
        arrayAdapterColorOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorOne.setAdapter(arrayAdapterColorOne);

        for(int i = 0; i < colorOne.getCount(); i++)
        {
            if(colorOne.getItemAtPosition(i).toString().equals(colorPlayerOne))
            {
                colorOne.setSelection(arrayAdapterColorOne.getPosition(colorPlayerOne));
            }
        }


        colorTwo = findViewById(R.id.spinnerColorTwo);
        colorTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                colorPlayerTwo = adapterView.getItemAtPosition(i).toString();
                checkColorAndSetText(textColorTwo, colorPlayerTwo);
                editor.putString("colorTwo", colorPlayerTwo);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //colorPlayerTwo = preferences.getString("colorTwo", getString(R.string.skinYellow));
                //checkColorAndSetText(textColorTwo, colorPlayerTwo);
            }
        });
        ArrayAdapter<CharSequence> arrayAdapterColorTwo = ArrayAdapter.createFromResource(getApplicationContext(), R.array.colorTwo_array, android.R.layout.simple_spinner_item);
        arrayAdapterColorTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorTwo.setAdapter(arrayAdapterColorTwo);

        for(int i = 0; i < colorTwo.getCount(); i++)
        {
            if(colorTwo.getItemAtPosition(i).toString().equals(colorPlayerTwo))
            {
                colorTwo.setSelection(arrayAdapterColorTwo.getPosition(colorPlayerTwo));
            }
        }


        mbuttonOoO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPlayerOne = preferences.getString("colorOne", getString(R.string.skinRed));
                colorPlayerTwo = preferences.getString("colorTwo", getString(R.string.skinYellow));
                if(colorPlayerTwo==colorPlayerOne)
                {
                    Toast.makeText(getApplicationContext(), R.string.differentColors, Toast.LENGTH_SHORT).show();
                }

                else
                {
                    editor.putInt("mode", 0);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        mbuttonKIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorPlayerOne = preferences.getString("colorOne", getString(R.string.skinRed));
                colorPlayerTwo = preferences.getString("colorTwo", getString(R.string.skinYellow));
                if(colorPlayerTwo==colorPlayerOne)
                {
                    Toast.makeText(getApplicationContext(), R.string.differentColors, Toast.LENGTH_SHORT).show();
                }

                else
                {
                    if (switchKI.isChecked() == true) {
                        editor.putInt("mode", 2);
                    } else {
                        editor.putInt("mode", 1);
                    }
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        background = adapterView.getItemAtPosition(i).toString();
        if(background.equals(getString(R.string.orange)))
        {
            backgroundColor.setTextColor(Color.rgb(254, 175, 0));
        }

        else if(background.equals(getString(R.string.green)))
        {
            backgroundColor.setTextColor(Color.GREEN);
        }

        else if(background.equals(getString(R.string.blue)))
        {
            backgroundColor.setTextColor(Color.BLUE);
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("background", background);
        editor.apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
       // background=preferences.getString("background", getString(R.string.orange));
    }

    public void checkColorAndSetText(TextView color, String colorText)
    {
        if(colorText == getString(R.string.skinRed))
        {
            color.setTextColor(Color.RED);
        }

        else if(colorText == getString(R.string.skinYellow))
        {
            color.setTextColor(Color.YELLOW);
        }

        else if(colorText == getString(R.string.skinPurple))
        {
            color.setTextColor(Color.rgb(255, 19, 247));
        }

        else if(colorText == getString(R.string.skinBlack))
        {
            color.setTextColor(Color.BLACK);
        }
    }
}




