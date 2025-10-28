package hcmute.spkt.Foody_18.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.Foody_18.R;
import hcmute.spkt.Foody_18.activity.OrderActivity;
import hcmute.spkt.Foody_18.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodFullDetailRecyclerViewAdapter extends RecyclerView.Adapter<FoodFullDetailRecyclerViewAdapter.FoodsFullDetailViewHolder> {
    private List<Food> list;
    private final Activity activity;


    public FoodFullDetailRecyclerViewAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
    }

    public void setFoods(List<Food> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FoodFullDetailRecyclerViewAdapter.FoodsFullDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.food_item_fulldetail, parent, false);
        return new FoodFullDetailRecyclerViewAdapter.FoodsFullDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodFullDetailRecyclerViewAdapter.FoodsFullDetailViewHolder holder, int position) {
        Food o = list.get(position);
        holder.txtName.setText(o.getName());
        holder.txtPrice.setText("" + o.getPrice());
        holder.txtRestaurant.setText(o.getRestaurant());
        holder.rating.setRating(o.getRate());
        holder.txtOrder.setText("(" + o.getOrders() + "+)");
        Resources resources = activity.getResources();
        final int resourceId = resources.getIdentifier("p"+String.valueOf(o.getImage()), "drawable",
                activity.getPackageName());
        holder.imgItem.setImageResource(resourceId);

        holder.imgLove.setImageResource(R.drawable.ic_star);

        Activity activity = this.activity;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, OrderActivity.class);
                intent.putExtra("food", o.getId());
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    class FoodsFullDetailViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtName;
        private final TextView txtOrder;
        private final TextView txtPrice;
        private final TextView txtRestaurant;
        private final ImageView imgItem;
        private final ImageView imgLove;
        private final RatingBar rating;

        public FoodsFullDetailViewHolder(@NonNull View v) {
            super(v);
            txtName = v.findViewById(R.id.item1_name);
            txtPrice = v.findViewById(R.id.item1_price);
            txtRestaurant = v.findViewById(R.id.item1_restaurant);
            imgItem = v.findViewById(R.id.item1_img);
            rating = v.findViewById(R.id.item1_rate);
            txtOrder = v.findViewById(R.id.item1_order);
            imgLove = v.findViewById(R.id.item1_love);
        }
    }
}
