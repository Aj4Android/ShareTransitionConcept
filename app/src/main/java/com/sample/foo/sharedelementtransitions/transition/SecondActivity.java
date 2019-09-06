package com.sample.foo.sharedelementtransitions.transition;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeScroll;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sample.foo.sharedelementtransitions.R;

public class SecondActivity extends AppCompatActivity {

    ImageView iv_img;
    TextView txv_name;
    String imageurl,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set an exit transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // inside your activity (if you did not enable transitions in your theme)
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
//            getWindow().setExitTransition(new Explode());
//            getWindow().setExitTransition(new AutoTransition());
//            getWindow().setExitTransition(new ChangeBounds());
//            getWindow().setExitTransition(new ChangeClipBounds());
//            getWindow().setExitTransition(new ChangeImageTransform());
//            getWindow().setExitTransition(new ChangeTransform());
//            getWindow().setExitTransition(new Fade());
//            getWindow().setExitTransition(new TransitionSet());
//            getWindow().setExitTransition(new Slide());
        }
        setContentView(R.layout.activity_second);
        iv_img = findViewById(R.id.iv_img);
        txv_name = findViewById(R.id.txv_name);
        imageurl = getIntent().getExtras().getString("img");
        name = getIntent().getExtras().getString("name");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Below lines are required in both First & Second Activity
            iv_img.setTransitionName("imageTransform");
            txv_name.setTransitionName("textTransform");
            Log.d("ok","");
        }


        Glide.with(SecondActivity.this)
                .applyDefaultRequestOptions(new RequestOptions()
                .placeholder(R.drawable.aa_logo_blue)
                .error(R.drawable.aa_logo_blue))
                .load(imageurl)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(iv_img);
        txv_name.setText(name);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
//            Toast.makeText(this, "transfinish", Toast.LENGTH_SHORT).show();
        }
        else{
//            Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
