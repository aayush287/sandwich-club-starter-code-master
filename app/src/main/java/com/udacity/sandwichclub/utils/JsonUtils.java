package com.udacity.sandwichclub.utils;



import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        /* Creating a new Instance of class Sandwich */
        Sandwich sandwich = new Sandwich();

        /* Origin of Sandwich */
        final String JSON_ORIGIN = "placeOfOrigin";
        /* Name object */
        final String JSON_NAME = "name";
        /* Main name string */
        final String JSON_MAIN_NAME = "mainName";
        final String JSON_INGREDIENTS = "ingredients";
        final String JSON_ALSO_KNOWN_AS = "alsoKnownAs";
        final String JSON_IMAGE = "image";
        final String JSON_DESCRIPTION = "description";


        JSONObject jsonObject = new JSONObject(json);

        JSONObject nameObject = jsonObject.getJSONObject(JSON_NAME);

        /* getting main name from the name object and set sit to sandwich object*/
        String mainName = nameObject.getString(JSON_MAIN_NAME);
        sandwich.setMainName(mainName);

        /*getting also known As from nameObject*/
        JSONArray alsoKnownJsonArray = nameObject.getJSONArray(JSON_ALSO_KNOWN_AS);
        List<String> alsoKnownAs = new ArrayList<>();
        for (int i = 0; i<alsoKnownJsonArray.length();i++){
            String name = alsoKnownJsonArray.getString(i);
            alsoKnownAs.add(name);
        }
        sandwich.setAlsoKnownAs(alsoKnownAs);

        /*getting place of origin from jsonObject and setting it to sandwich object*/
        String placeOfOrigin = jsonObject.getString(JSON_ORIGIN);
        sandwich.setPlaceOfOrigin(placeOfOrigin);

        /*getting ingredients from jsonObject*/
        JSONArray jsonArray = jsonObject.getJSONArray(JSON_INGREDIENTS);
        List<String> ingredients = new ArrayList<>();
        for (int i = 0; i<jsonArray.length();i++){
            String ingredient = jsonArray.getString(i);
            ingredients.add(ingredient);
        }
        sandwich.setIngredients(ingredients);

        /* Getting and setting the description */
        String description = jsonObject.getString(JSON_DESCRIPTION);
        sandwich.setDescription(description);

        /*  Getting image string from jsonObject and setting it to sandwich object*/
        String image = jsonObject.getString(JSON_IMAGE);
        sandwich.setImage(image);


        return sandwich;
    }
}
