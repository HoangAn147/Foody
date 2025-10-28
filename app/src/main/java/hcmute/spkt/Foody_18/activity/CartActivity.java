package hcmute.spkt.Foody_18.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.Foody_18.R;
import hcmute.spkt.Foody_18.controller.CartRecyclerViewAdapter;
import hcmute.spkt.Foody_18.controller.SQLiteCartHelper;
import hcmute.spkt.Foody_18.model.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private TextView back, pay, delete;
    private RecyclerView recyclerView;
    private CartRecyclerViewAdapter adapter;
    private SQLiteCartHelper sqLite;
    private int currentUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.cart_activity);
        init();


        back = findViewById(R.id.back_to_main3);
        pay = findViewById(R.id.pay);
        recyclerView = findViewById(R.id.list_cart);
        delete = findViewById(R.id.delete_order);

        adapter = new CartRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sqLite = new SQLiteCartHelper(this);
        List<Cart> carts = sqLite.getCartUserPay(String.valueOf(currentUserId), 0);
        adapter.setCarts(carts);
        recyclerView.setAdapter(adapter);

        this.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLite.close();
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
                CartActivity.this.finish();
            }
        });

        this.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; i++) {
                    if (CartRecyclerViewAdapter.chooseid[i] == 1) {
                        Cart c = sqLite.getCartUserById(i);
                        c.setPayment(1);
                        sqLite.update(c);
                        sqLite.close();
                        Intent intent = new Intent(CartActivity.this, MainActivity.class);
                        intent.putExtra("currentUserId", currentUserId);
                        startActivity(intent);
                        CartActivity.this.finish();
                        Toast.makeText(CartActivity.this, "Đã thanh toán", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        this.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 100; i++) {
                    if (CartRecyclerViewAdapter.chooseid[i] == 1) {
                        sqLite.delete(i);
                        CartRecyclerViewAdapter.chooseid[i] = 0;
                        sqLite.close();
                        Intent intent = new Intent(CartActivity.this, MainActivity.class);
                        intent.putExtra("currentUserId", currentUserId);
                        startActivity(intent);
                        Toast.makeText(CartActivity.this, "Đã xóa món ăn khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                        CartActivity.this.finish();

                    }
                }
            }
        });
    }
    public void init() {
        currentUserId = getIntent().getExtras().getInt("currentUserId");
    }
}