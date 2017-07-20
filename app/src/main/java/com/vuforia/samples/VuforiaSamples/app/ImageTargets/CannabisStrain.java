package com.vuforia.samples.VuforiaSamples.app.ImageTargets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.vuforia.Image;
import com.vuforia.samples.VuforiaSamples.R;

/**
 * Created by Caryn on 10/19/2016.
 */

public class CannabisStrain {
    private String id;

    String time = null;
    String name = null;
    String logo = null;
    String background = null;
    String flavor = null;
    String flavor_icon = null;
//    String ancestor_icon_1 = null;
//    String ancestor_icon_2 = null;
//    String ancestor_icon_3 = null;
    String positive_effect_name = null;
    String positive_effect_icon = null;
    String indica_sativa = null;
    String description = null;

    private View _viewCard = null;
    private TextView time_tv;
    private TextView name_tv;
    private ImageView logo_iv;
    //ImageView background_iv;
    private TextView flavor_tv;
    private ImageView flavor_icon_iv;
    private TextView positive_effect_name_tv;
    private ImageView positive_effect_icon_iv;
    private TextView indica_sativa_tv;
    private TextView description_tv;
    private ImageView background_iv;

    private Context context;

    public CannabisStrain(){};

    public CannabisStrain(View v, Context context, String id) {
        this._viewCard = v;
        this.context = context;
        time_tv = (TextView) _viewCard.findViewById(R.id.card_time);
        name_tv = (TextView) _viewCard.findViewById(R.id.card_name);
        logo_iv = (ImageView) _viewCard.findViewById(R.id.card_logo);
        background_iv = (ImageView) _viewCard.findViewById(R.id.card_background);
        flavor_tv = (TextView) _viewCard.findViewById(R.id.card_flavor);
        flavor_icon_iv = (ImageView) _viewCard.findViewById(R.id.card_flavor_icon);
        positive_effect_name_tv = (TextView) _viewCard.findViewById(R.id.card_effect);
        positive_effect_icon_iv = (ImageView) _viewCard.findViewById(R.id.card_effect_icon);
        indica_sativa_tv = (TextView) _viewCard.findViewById(R.id.indica_sativa_ratio);
        description_tv = (TextView) _viewCard.findViewById(R.id.card_description);
        reset();
    }

    void load(){

        if (!logo.equals("")) {
            Log.d("LOGO", "load: " + logo);
            Picasso.with(context).load(logo).into(logo_iv, new Callback(){
                @Override
                public void onSuccess() {
                    Palette.generateAsync(((BitmapDrawable) logo_iv.getDrawable()).getBitmap(),new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette p) {
                            // Use generated instance
                            Palette.Swatch vibrant = p.getVibrantSwatch();
                            if (vibrant != null) {
                                // Set the background color of a layout based on the vibrant color
                                time_tv.setBackgroundColor(vibrant.getRgb());
                            } else {
                                time_tv.setBackgroundColor(context.getResources().getColor(R.color.transparent));
                            }
                        }
                    });

                }

                @Override
                public void onError() {
                    logo_iv.setVisibility(View.INVISIBLE);
                    time_tv.setBackgroundColor(context.getResources().getColor(R.color.transparent));
                }
            });
            //Picasso.with(context).load("https://s3.amazonaws.com/mystrain-production/products/logos/000/000/042/card/Blue-Dream.png?1459532914").into(logo_iv);
        }

        if (positive_effect_icon != null && !positive_effect_icon.equals("")) {
            Picasso.with(context).load(positive_effect_icon).into(positive_effect_icon_iv);
        }

        if (background != null && !background.equals("")) {
            Picasso.with(context).load(background).into(background_iv);
            background_iv.setVisibility(View.VISIBLE);
        }else{
            background_iv.setVisibility(View.INVISIBLE);
        }
        if (!flavor_icon.equals("")) {
            Picasso.with(context).load(flavor_icon).into(flavor_icon_iv);
        }
        if (!time.equalsIgnoreCase("null")){
            time_tv.setText(time);
        }
        name_tv.setText(name);
        if (!flavor.equalsIgnoreCase("null")){
            flavor_tv.setText(flavor);
        }
        positive_effect_name_tv.setText(positive_effect_name);
        indica_sativa_tv.setText(indica_sativa);
        description_tv.setText(Html.fromHtml(description).toString());
        //ToDo: put into callback for Picasso
        logo_iv.setVisibility(View.VISIBLE);
        positive_effect_icon_iv.setVisibility(View.VISIBLE);
        flavor_icon_iv.setVisibility(View.VISIBLE);
    }

    void clear() {
        time_tv.setText("");
        name_tv.setText("");
        logo_iv.setVisibility(View.INVISIBLE);
        flavor_tv.setText("");
        flavor_icon_iv.setVisibility(View.INVISIBLE);
        positive_effect_name_tv.setText("");
        positive_effect_icon_iv.setVisibility(View.INVISIBLE);
        indica_sativa_tv.setText("");
        description_tv.setText("");
    }

    void reset() {
        time_tv.setText("ANYTIME");
        name_tv.setText("UNKNOWN");
        logo_iv.setVisibility(View.INVISIBLE);
        flavor_tv.setText("FLAVOR");
        flavor_icon_iv.setVisibility(View.INVISIBLE);
        positive_effect_name_tv.setText("EFFECT");
        positive_effect_icon_iv.setVisibility(View.INVISIBLE);
        indica_sativa_tv.setText("0/0");
        description_tv.setText("THIS IS A FILLER DESCRIPTION");
    }

    int getId(){
        return Integer.getInteger(id);
    }

//    static int getId(String name){
//        switch(name) {
//            case "blue_dream":
//                return 14084;
//            case "green_crack":
//                return 14109;
//            case "girl_scout_cookies":
//                return 14013;
//            case "gdp":
//                return 14007;
//            case "sour_diesel":
//                return 14005;
//            case "HighTide":
//                return 1936;
//            case "Harborside":
//                return 1938;
//        }
//        return 0;
//    }
    static int getId(String input){
        return Integer.valueOf(input);
    }
}
