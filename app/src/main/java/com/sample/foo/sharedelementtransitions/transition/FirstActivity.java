package com.sample.foo.sharedelementtransitions.transition;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sample.foo.sharedelementtransitions.R;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    RecyclerView rv_list;
    LinearLayoutManager content_manager;
    ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // inside your activity (if you did not enable transitions in your theme)
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Toast.makeText(this, "FirstActivity opening", Toast.LENGTH_SHORT).show();
//            getWindow().setEnterTransition(new ChangeBounds());
//            getWindow().setEnterTransition(new ChangeClipBounds());
//            getWindow().setEnterTransition(new ChangeImageTransform());
//            getWindow().setEnterTransition(new ChangeTransform());
//            getWindow().setEnterTransition(new Fade());
//            getWindow().setEnterTransition(new TransitionSet());
//            getWindow().setEnterTransition(new Slide());
            // Apply activity transition
        } else {
            // Swap without transition
            Toast.makeText(this, "No Transition", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_first);

        rv_list = findViewById(R.id.rv_list);
        content_manager = new LinearLayoutManager(FirstActivity.this);
        content_manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(content_manager);
        itemList = new ArrayList<>();
        itemList = getItems();
        rv_list.setAdapter(new ItemAdapter(itemList));


    }

    private ArrayList<Item> getItems(){
        itemList.add(new Item("1","India","https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages-na.ssl-images-amazon.com%2Fimages%2FI%2F41JINRlOkpL._SX425_.jpg&imgrefurl=https%3A%2F%2Fwww.amazon.in%2FIndigo-Creatives-Indian-Flag-Offices%2Fdp%2FB00TPJ788Y&docid=0RckqMWyZ8JWzM&tbnid=d3tDh_cfJ0TlpM%3A&vet=10ahUKEwj2jNSB6PfiAhULT30KHWx0DUMQMwh6KAMwAw..i&w=425&h=303&bih=615&biw=1366&q=indian%20flag&ved=0ahUKEwj2jNSB6PfiAhULT30KHWx0DUMQMwh6KAMwAw&iact=mrc&uact=8"));
        itemList.add(new Item("2","Australia","http://www.senojflags.com/images/country-flag-icons/Australia-Flag.png"));
        itemList.add(new Item("3","New Zealand","http://www.senojflags.com/images/country-flag-icons/New-Zealand-Flag.png"));
        itemList.add(new Item("4","South Africa","http://www.senojflags.com/images/country-flag-icons/South-Africa-Flag.png"));
        itemList.add(new Item("5","West Indies","http://www.senojflags.com/images/country-flag-icons/Zambia-Flag.png"));
        itemList.add(new Item("6","England","http://www.senojflags.com/images/country-flag-icons/England-Flag.png"));
        itemList.add(new Item("7","Pakistan","http://www.senojflags.com/images/country-flag-icons/Pakistan-Flag.png"));
        itemList.add(new Item("8","Bangladesh","http://www.senojflags.com/images/country-flag-icons/Bangladesh-Flag.png"));
        return itemList;
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>{

        ArrayList<Item> itemLists;

        public ItemAdapter(ArrayList<Item> itemList) {
            itemLists = itemList;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row
                    , parent, false);
            return new ItemAdapter.ItemHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            Item item = itemLists.get(position);
            Glide.with(holder.itemView.getContext()).applyDefaultRequestOptions(new RequestOptions()
                    .placeholder(R.drawable.aa_logo_blue)
                    .error(R.drawable.aa_logo_blue))
                    .load(item.getImage())
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(holder.iv_image);
            holder.txv_id.setText(item.getId());
            holder.txv_name.setText(item.getName());
        }

        @Override
        public int getItemCount() {
            return itemLists.size();
        }

        public class ItemHolder extends RecyclerView.ViewHolder {
            public ImageView iv_image;
            public TextView txv_name;
            public TextView txv_id;
            public ItemHolder(View view) {
                super(view);
                iv_image = view.findViewById(R.id.iv_image);
                txv_id = view.findViewById(R.id.txv_id);
                txv_name = view.findViewById(R.id.txv_name);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(),SecondActivity.class);
                        intent.putExtra("name",itemLists.get(getAdapterPosition()).getName());
                        intent.putExtra("img",itemLists.get(getAdapterPosition()).getImage());
                        // Check if we're running on Android 5.0 or higher
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            //Below lines are required in both First & Second Activity...(here optional)
                            iv_image.setTransitionName("imageTransform");
                            txv_name.setTransitionName("textTransform");
                            //Long approach
//                            ActivityOptions options = ActivityOptions
//                                    .makeSceneTransitionAnimation(FirstActivity.this, iv_image, "transform");


//                            Pair<View, String> pairThumb = Pair.create((View) iv_image, "newsimage");
                            ActivityOptions options = ActivityOptions
                                    .makeSceneTransitionAnimation(FirstActivity.this,Pair.create((View)iv_image, "imageTransform"),
                                            Pair.create((View)txv_name, "textTransform"));
                            // start the new activity
                            view.getContext().startActivity(intent, options.toBundle());

                            //shortcut approach
//                            view.getContext().startActivity(intent,
//                                    ActivityOptions.makeSceneTransitionAnimation(FirstActivity.this).toBundle());
//                            view.getContext().startActivity(intent);
                        }
                        else {
                            view.getContext().startActivity(intent);
                        }
                    }
                });
            }
        }
    }
}
