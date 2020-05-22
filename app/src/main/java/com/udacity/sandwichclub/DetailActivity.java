package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mainNameTextView;
    private TextView ingredientsTextView;
    private TextView placeOfOriginTextView;
    private TextView descriptionTextView;
    private TextView alsoKnownAsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        mainNameTextView = findViewById(R.id.name_tv);
        descriptionTextView = findViewById(R.id.description_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);
        placeOfOriginTextView = findViewById(R.id.origin_tv);
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        assert intent != null;
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        mainNameTextView.setText(sandwich.getMainName());
        placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());

        List<String> ingredients = sandwich.getIngredients();

        StringBuilder ingredientsString = new StringBuilder();

        for (int i = 0;i<ingredients.size();i++){
            ingredientsString.append(ingredients.get(i));
            if (i != ingredients.size()-1){
                ingredientsString.append(", ");
            }
        }

        ingredientsTextView.setText(ingredientsString.toString());

        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();

        StringBuilder alsoKnownString = new StringBuilder();

        for (int i = 0;i<alsoKnownAs.size();i++){
            alsoKnownString.append(alsoKnownAs.get(i));
            if (i != alsoKnownAs.size()-1){
                alsoKnownString.append(", ");
            }
        }

        alsoKnownAsTextView.setText(alsoKnownString.toString());


    }
}
