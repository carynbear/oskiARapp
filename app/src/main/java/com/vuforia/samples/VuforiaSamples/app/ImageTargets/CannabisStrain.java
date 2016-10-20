package com.vuforia.samples.VuforiaSamples.app.ImageTargets;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vuforia.Image;
import com.vuforia.samples.VuforiaSamples.R;

/**
 * Created by Caryn on 10/19/2016.
 */

public class CannabisStrain {
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

    private Context context;

    public CannabisStrain(){};

    public CannabisStrain(View v, Context context) {
        this._viewCard = v;
        this.context = context;
        time_tv = (TextView) _viewCard.findViewById(R.id.card_time);
        name_tv = (TextView) _viewCard.findViewById(R.id.card_name);
        logo_iv = (ImageView) _viewCard.findViewById(R.id.card_logo);
        //background_iv = ()
        flavor_tv = (TextView) _viewCard.findViewById(R.id.card_flavor);
        flavor_icon_iv = (ImageView) _viewCard.findViewById(R.id.card_flavor_icon);
        positive_effect_name_tv = (TextView) _viewCard.findViewById(R.id.card_effect);
        positive_effect_icon_iv = (ImageView) _viewCard.findViewById(R.id.card_effect_icon);
        indica_sativa_tv = (TextView) _viewCard.findViewById(R.id.indica_sativa_ratio);
        description_tv = (TextView) _viewCard.findViewById(R.id.card_details);
    }

    void load(){
        time_tv.setText(time);
        name_tv.setText(name);
        Picasso.with(context).load(logo).into(logo_iv);
        flavor_tv.setText(flavor);
        Picasso.with(context).load(flavor_icon).into(flavor_icon_iv);
        positive_effect_name_tv.setText(positive_effect_name);
        Picasso.with(context).load(positive_effect_icon).into(positive_effect_icon_iv);
        indica_sativa_tv.setText(indica_sativa);
        description_tv.setText(description);
    }

    void clear() {
        time_tv.setText("");
        name_tv.setText("");
        Picasso.with(context).load(logo).into(logo_iv);
        flavor_tv.setText("");
        Picasso.with(context).load(flavor_icon).into(flavor_icon_iv);
        positive_effect_name_tv.setText("");
        Picasso.with(context).load(positive_effect_icon).into(positive_effect_icon_iv);
        indica_sativa_tv.setText("");
        description_tv.setText("");
    }

    void reset() {
        time_tv.setText("ANYTIME");
        name_tv.setText("UNKNOWN");
        Picasso.with(context).load(logo).into(logo_iv);
        flavor_tv.setText("FLAVOR");
        Picasso.with(context).load(flavor_icon).into(flavor_icon_iv);
        positive_effect_name_tv.setText("EFFECT");
        Picasso.with(context).load(positive_effect_icon).into(positive_effect_icon_iv);
        indica_sativa_tv.setText("0/0");
        description_tv.setText("THIS IS A FILLER DESCRIPTION");
    }
}
