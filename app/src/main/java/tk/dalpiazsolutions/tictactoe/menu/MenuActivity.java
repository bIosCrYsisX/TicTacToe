package tk.dalpiazsolutions.tictactoe.menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import tk.dalpiazsolutions.tictactoe.MainActivity;
import tk.dalpiazsolutions.tictactoe.R;

public class MenuActivity extends AppCompatActivity implements MenuModelListener {

    Button buttonPVP;
    Button buttonKI;
    Switch switchKIStarts;

    Spinner spinnerBackground;
    Spinner spinnerColorOne;
    Spinner spinnerColorTwo;
    Spinner spinnerDifficulty;

    TextView backgroundColor;
    TextView textColorOne;
    TextView textColorTwo;

    SharedPreferences preferences;

    private MenuModel mMenuModel;
    private ResourceArrayAdapter backgroundArrayAdapter;
    private ResourceArrayAdapter difficultyArrayAdapter;
    private ResourceArrayAdapter colorOneArrayAdapter;
    private ResourceArrayAdapter colorTwoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        spinnerBackground = findViewById(R.id.spinnerBackground);
        spinnerDifficulty = findViewById(R.id.spinnerDifficulty);
        spinnerColorOne = findViewById(R.id.spinnerColorOne);
        spinnerColorTwo = findViewById(R.id.spinnerColorTwo);
        buttonPVP = findViewById(R.id.buttonOneonOne);
        buttonKI = findViewById(R.id.buttonYouvsKI);
        switchKIStarts = findViewById(R.id.switchKistarts);
        backgroundColor = findViewById(R.id.backgroundColor);
        textColorTwo = findViewById(R.id.skin2);
        textColorOne = findViewById(R.id.skin1);

        mMenuModel = new MenuModel();
        mMenuModel.addModelListener(this);

        LoadSharedPreferences();

        backgroundArrayAdapter = new ResourceArrayAdapter(this, R.array.backgrounds_array);
        difficultyArrayAdapter = new ResourceArrayAdapter(this, R.array.difficulty_array);
        colorOneArrayAdapter = new ResourceArrayAdapter(this, R.array.colorOne_array);
        colorTwoArrayAdapter = new ResourceArrayAdapter(this, R.array.colorTwo_array);

        spinnerBackground.setAdapter(backgroundArrayAdapter);
        spinnerDifficulty.setAdapter(difficultyArrayAdapter);
        spinnerColorOne.setAdapter(colorOneArrayAdapter);
        spinnerColorTwo.setAdapter(colorTwoArrayAdapter);

        spinnerBackground.setSelection(
                backgroundArrayAdapter.getPosition(mMenuModel.getBackground())
        );
        spinnerDifficulty.setSelection(
                difficultyArrayAdapter.getPosition(
                    difficultyArrayAdapter.getItem(
                            mMenuModel.getDifficultyMode()))
        );
        spinnerColorOne.setSelection(
                colorOneArrayAdapter.getPosition(mMenuModel.getColorPlayerOne())
        );
        spinnerColorTwo.setSelection(
                colorTwoArrayAdapter.getPosition(mMenuModel.getColorPlayerOne())
        );

        spinnerBackground.setOnItemSelectedListener(new BackgroundSelectListener(mMenuModel));
        spinnerColorOne.setOnItemSelectedListener(new ColorOneSelectListener(mMenuModel));
        spinnerColorTwo.setOnItemSelectedListener(new ColorTwoSelectListener(mMenuModel));
        spinnerDifficulty.setOnItemSelectedListener(new DifficultySelectListener(mMenuModel));

        buttonPVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartGame(GameMode.PlayerVsPlayer);
            }
        });

        buttonKI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (switchKIStarts.isChecked() == true) {
                StartGame(GameMode.KIStarts);
            } else {
                StartGame(GameMode.PlayerStarts);
            }
            }
        });
    }

    private void StartGame(GameMode gameMode){
        if(colorsDiffer() == false)
        {
            Toast.makeText(getApplicationContext(), R.string.differentColors, Toast.LENGTH_SHORT).show();
        }
        else
        {
            SaveSharedPreferences();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("mode", gameMode.ordinal());
            startActivity(intent);
        }
    }

    private void SaveSharedPreferences(){
        preferences = getSharedPreferences("modeFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor
                .putString("background", mMenuModel.getBackground())
                .putString("spinnerColorOne", mMenuModel.getColorPlayerOne())
                .putString("spinnerColorTwo", mMenuModel.getColorPlayerTwo())
                .putInt("difficulty", mMenuModel.getDifficultyMode())
                .apply();
    }

    private boolean colorsDiffer(){
        String colorPlayerOne = mMenuModel.getColorPlayerOne();
        String colorPlayerTwo = mMenuModel.getColorPlayerTwo();

        return colorPlayerOne.equals(colorPlayerTwo) == false;
    }

    private void LoadSharedPreferences() {
        preferences = getSharedPreferences("modeFile", MODE_PRIVATE);
        mMenuModel.setBackground(preferences.getString("background", getString(R.string.orange)));
        mMenuModel.setColorPlayerOne(preferences.getString("spinnerColorOne", getString(R.string.skinRed)));
        mMenuModel.setColorPlayerTwo(preferences.getString("spinnerColorTwo", getString(R.string.skinYellow)));
        mMenuModel.setDifficultyMode(preferences.getInt("difficulty", 0));
    }

    @Override
    public void onPropertyChanged() {
        if(mMenuModel.getBackground() != null){
            if(mMenuModel.getBackground().equals(getString(R.string.orange))){
                backgroundColor.setTextColor(Color.rgb(254, 175, 0));
            }else{
                backgroundColor.setTextColor(Color.parseColor(mMenuModel.getBackground().toLowerCase()));
            }
        }

        if(mMenuModel.getColorPlayerOne() != null)
            textColorOne.setTextColor(Color.parseColor(mMenuModel.getColorPlayerOne().toLowerCase()));
        if(mMenuModel.getColorPlayerTwo() != null)
            textColorTwo.setTextColor(Color.parseColor(mMenuModel.getColorPlayerTwo().toLowerCase()));
    }

}




