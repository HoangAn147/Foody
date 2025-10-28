package hcmute.spkt.Foody_18.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.Foody_18.R;
import hcmute.spkt.Foody_18.activity.OrderActivity;
import hcmute.spkt.Foody_18.model.Cart;
import hcmute.spkt.Foody_18.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodsViewHolder> {

    private List<Food> list;
    private final Activity activity;

    public FoodRecyclerViewAdapter(Activity activity) {
        list = new ArrayList<>();
        this.activity = activity;
    }

    public void setFoods(List<Food> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.food_item, parent, false);
        return new FoodsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
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

    class FoodsViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtName;
        private final TextView txtOrder;
        private final TextView txtPrice;
        private final TextView txtRestaurant;
        private final ImageView imgItem;
        private final RatingBar rating;

        public FoodsViewHolder(@NonNull View v) {
            super(v);
            txtName = v.findViewById(R.id.item_name);
            txtPrice = v.findViewById(R.id.item_price);
            txtRestaurant = v.findViewById(R.id.item_restaurant);
            imgItem = v.findViewById(R.id.item_img);
            rating = v.findViewById(R.id.item_rate);
            txtOrder = v.findViewById(R.id.item_order);

        }
    }
}
