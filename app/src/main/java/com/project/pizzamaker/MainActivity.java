package com.project.pizzamaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int MEDIUM_SIZE = 60;
    private final int REGULAR_SIZE = 40;
    private final int LARGE_SIZE = 70;

    private final int THIN_CRUST = 50;
    private final int PAN = 40;
    private final int CHICAGO = 60;

    private final int WHITE_CHEESE = 10;
    private final int BROWN_CHEESE = 20;


    private final int TOMATO_SAUCE = 20;
    private final int BBQ_SAUCE = 15;
    private final int MARINARA_SAUCE = 30;


    private final int ONION = 5;
    private final int PREPPERONI = 30;
    private final int GREEN_PAPPER = 20;
    private final int ANCHOVIES = 20;


    Button submitButton;
    double total = 0;

    CheckBox cbOnion, cbPepperoni, cbGreenPaper, cbAnchovies;
    TextView totalCost;

    NiceSpinner pizzaSize, pizzaType, cheeseType, sauceType;

    boolean isAllFieldAreSelected;

    int pizzaSizeCost = 0;
    int pizzaTypeCost = 0;
    int cheeseTypeCost = 0;
    int sauceTypeCost = 0;
    int toppingCost = 0;


    private final String REGULAR = "Regular";
    private final String MEDIUM = "Medium";
    private final String LARGE = "Large";

    private final String THIN_CRUST_TYPE = "Thin Crust";
    private final String PAN_TYPE = "Pan";
    private final String CHICAGO_TYPE = "Chicago";

    private final String WHITE = "White";
    private final String BROWNE = "Browne";
    private final String SELECT = "Select";

    private final String TOMATO = "Tomato";
    private final String BBQ = "BBQ";
    private final String MARINARA = "Marinara";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submit_button);
        onSubmitButtonClicked();
        totalCost = findViewById(R.id.total_cost);

        cbOnion = findViewById(R.id.onion);
        cbPepperoni = findViewById(R.id.pepperoni);
        cbGreenPaper = findViewById(R.id.green_pepper);
        cbAnchovies = findViewById(R.id.anchovies);

        pizzaSize = findViewById(R.id.pizza_size);
        pizzaType = findViewById(R.id.pizza_type);
        cheeseType = findViewById(R.id.pizza_cheese_type);
        sauceType = findViewById(R.id.sauce_type);

        List<String> sizes = new LinkedList<>(Arrays.asList(REGULAR, MEDIUM, LARGE));
        pizzaSize.attachDataSource(sizes);

        List<String> pizzaTypes = new LinkedList<>(Arrays.asList(THIN_CRUST_TYPE, PAN_TYPE, CHICAGO_TYPE));
        pizzaType.attachDataSource(pizzaTypes);

        List<String> cheeseTypes = new LinkedList<>(Arrays.asList(WHITE, BROWNE));
        cheeseType.attachDataSource(cheeseTypes);

        List<String> sauceTypes = new LinkedList<>(Arrays.asList(TOMATO, BBQ, MARINARA));
        sauceType.attachDataSource(sauceTypes);
        onSpinnerItemClickListener();
        onCheckBoxClickListener();
        showDefaultPrice();

    }

    private void showDefaultPrice() {
        pizzaSizeCost = REGULAR_SIZE;
        pizzaTypeCost = THIN_CRUST;
        cheeseTypeCost = WHITE_CHEESE;
        sauceTypeCost = TOMATO_SAUCE;
        updateUi();
    }

    private void onSpinnerItemClickListener() {
        pizzaSize.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                    switch (item) {
                        case REGULAR:
                            pizzaSizeCost = REGULAR_SIZE;
                            break;
                        case MEDIUM:
                            pizzaSizeCost = MEDIUM_SIZE;
                            break;
                        case LARGE:
                            pizzaSizeCost = LARGE_SIZE;
                            break;
                        default:
                            pizzaSizeCost = 0;

                    }
                    updateUi();


            }
        });

        pizzaType.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case THIN_CRUST_TYPE:
                        pizzaTypeCost = THIN_CRUST;
                        break;
                    case PAN_TYPE:
                        pizzaTypeCost = PAN;
                        break;
                    case CHICAGO_TYPE:
                        pizzaTypeCost = CHICAGO;
                }
                updateUi();


            }
        });
        cheeseType.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case WHITE:
                        cheeseTypeCost = WHITE_CHEESE;
                        break;
                    case BROWNE:
                        cheeseTypeCost = BROWN_CHEESE;
                        break;

                }
                updateUi();


            }
        });
        sauceType.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case TOMATO:
                        sauceTypeCost = TOMATO_SAUCE;
                        break;
                    case BBQ:
                        sauceTypeCost = BBQ_SAUCE;
                        break;
                    case MARINARA:
                        sauceTypeCost = MARINARA_SAUCE;
                        break;
                }
                updateUi();
            }
        });
    }

    private void updateUi() {
        total = pizzaSizeCost + pizzaTypeCost + cheeseTypeCost + sauceTypeCost + toppingCost;
        totalCost.setText("TOTAL: ₱ " + total);
    }
    private void onCheckBoxClickListener() {
        cbOnion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setCost(ONION, true);
                } else {
                    setCost(ONION, false);
                }
            }
        });
        cbPepperoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setCost(PREPPERONI, true);
                } else {
                    setCost(PREPPERONI, false);
                }

            }
        });
        cbGreenPaper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setCost(GREEN_PAPPER, true);
                } else {
                    setCost(GREEN_PAPPER, false);
                }
            }
        });
        cbAnchovies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setCost(ANCHOVIES, true);
                } else {
                    setCost(ANCHOVIES, false);
                }
            }
        });
    }

    public void onSubmitButtonClicked() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //will show an error message
                Toast.makeText(MainActivity.this, "Please select all the required fields.", Toast.LENGTH_SHORT).show();
                submitButton.setText("Done");
            }
        });
    }
    public void setCost(int amount, boolean isAdded) {
        if (isAdded) {
            toppingCost = toppingCost + amount;
        } else {
            toppingCost = toppingCost - amount;
        }
        total = total + toppingCost;
        updateUi();
    }
}
