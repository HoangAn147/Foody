package hcmute.spkt.Foody_18.activity;

import static hcmute.spkt.Foody_18.activity.MapActivity.location;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hcmute.spkt.Foody_18.R;
import hcmute.spkt.Foody_18.controller.FoodRecyclerViewAdapter;
import hcmute.spkt.Foody_18.controller.FragmentBottomNavigation;
import hcmute.spkt.Foody_18.controller.RestaurantRecyclerViewAdapter;
import hcmute.spkt.Foody_18.controller.SQLiteCartHelper;
import hcmute.spkt.Foody_18.controller.SQLiteFoodHelper;
import hcmute.spkt.Foody_18.controller.SQLiteRestaurantHelper;
import hcmute.spkt.Foody_18.controller.SQLiteUser;
import hcmute.spkt.Foody_18.fragment.MyOrderFragment;
import hcmute.spkt.Foody_18.fragment.ProfileFragment;
import hcmute.spkt.Foody_18.model.Cart;
import hcmute.spkt.Foody_18.model.Food;
import hcmute.spkt.Foody_18.model.Restaurant;

public class MainActivity extends AppCompatActivity {

    private FragmentBottomNavigation fragmentBottomNavigation;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    private int currentUserId;
    private TextView choosemap, cartnoti, getprofile, dochien, monchinh, dohop, pizza, trangmieng, fastfood, comsuat, banhmikep, text_foru, notifi_main, bigsale, getmore;
    private LinearLayout maybelove, linear_meal, foru, dealhot, drink;
    private RecyclerView list_food, list_restaurant, list_foryou, list_love;
    private FoodRecyclerViewAdapter adapterfood1, adapterfood2, adapterfood3;
    private RestaurantRecyclerViewAdapter adapter1;
    private SQLiteFoodHelper sqLite;
    private SQLiteCartHelper sqLite2;
    private SQLiteRestaurantHelper sqLite1;
    private SQLiteUser databaseUser;
    private NotificationBadge notificationBadge;
    private int meal = 0;
    private static final List<Integer> list_recommand = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();

        sqLite = new SQLiteFoodHelper(this);
        sqLite.addFood(new Food(0, "Trà sữa trân châu hoàng kim", 25000, "Trà Sữa Koi Thé", 4, 100, 1));
        sqLite.addFood(new Food(0, "Trà sữa sương sáo", 26000, "Trà Sữa Koi Thé", 3, 100,2));
        sqLite.addFood(new Food(0, "Trà sữa Konjac", 25000, "Trà Sữa Koi Thé", 4, 100, 3));
        sqLite.addFood(new Food(0, "Sữa tươi trân châu đường đen", 25000, "Trà Sữa Koi Thé", 4, 100, 4));
        sqLite.addFood(new Food(0, "Trà sữa Oolong", 25000, "Trà Sữa Koi Thé", 4, 100, 5));
        sqLite.addFood(new Food(0, "Trà sữa Oolong Hoàng Kim", 25000, "Trà Sữa Koi Thé", 4, 100, 6));
        sqLite.addFood(new Food(0, "Hồng trà sữa", 25000, "Trà Sữa Koi Thé", 4, 100, 7));
        sqLite.addFood(new Food(0, "Milo Ô", 25000, "Trà Sữa Koi Thé", 4, 100, 8));
        sqLite.addFood(new Food(0, "Green tea macchiato", 25000, "Trà Sữa Koi Thé", 4, 100, 9));
        sqLite.addFood(new Food(0, "Trà sữa đường đen", 25000, "Trà Sữa Koi Thé", 4, 100, 10));

        sqLite.addFood(new Food(1, "Trà sữa đào", 25500, "Trà Sữa Gongcha", 5, 100,11));
        sqLite.addFood(new Food(1, "Trà xanh đào", 25300, "Trà Sữa Gongcha", 1, 200,12));
        sqLite.addFood(new Food(1, "Trà đen đào", 25500, "Trà Sữa Gongcha", 5, 100,13));
        sqLite.addFood(new Food(1, "Yakult đào đá xay", 25300, "Trà Sữa Gongcha", 1, 200,14));
        sqLite.addFood(new Food(1, "Đào latte", 25500, "Trà Sữa Gongcha", 5, 100,15));
        sqLite.addFood(new Food(1, "Trà sữa Oolong", 25300, "Trà Sữa Gongcha", 1, 200,16));
        sqLite.addFood(new Food(1, "Trà sữa trà xanh", 25500, "Trà Sữa Gongcha", 5, 100,17));
        sqLite.addFood(new Food(1, "Trà sữa Hokkaido", 25300, "Trà Sữa Gongcha", 1, 200,18));
        sqLite.addFood(new Food(1, "Trà sữa trà đen", 25500, "Trà Sữa Gongcha", 5, 100,19));
        sqLite.addFood(new Food(1, "Trà sữa khoai môn", 25300, "Trà Sữa Gongcha", 1, 200,20));

        sqLite.addFood(new Food(2, "Gà tắm nước mắm", 22000, "Gà quán Popeyes", 2, 500,21));
        sqLite.addFood(new Food(2, "Gà giòn cay", 22000, "Gà quán Popeyes", 2, 500,22));
        sqLite.addFood(new Food(2, "Burger tôm", 22000, "Gà quán Popeyes", 2, 500,23));
        sqLite.addFood(new Food(2, "Khoai tây chiên", 22000, "Gà quán Popeyes", 2, 500,24));
        sqLite.addFood(new Food(2, "Snack cá", 22000, "Gà quán Popeyes", 2, 500,25));
        sqLite.addFood(new Food(2, "Bắp cải trộn", 22000, "Gà quán Popeyes", 2, 500,26));
        sqLite.addFood(new Food(2, "Coca Cola", 22000, "Gà quán Popeyes", 2, 500,27));
        sqLite.addFood(new Food(2, "Sprite", 22000, "Gà quán Popeyes", 2, 500,28));
        sqLite.addFood(new Food(2, "Fanta", 22000, "Gà quán Popeyes", 2, 500,29));
        sqLite.addFood(new Food(2, "Nước suối", 22000, "Gà quán Popeyes", 2, 500,30));

        sqLite.addFood(new Food(3, "Bánh mì chấm cà phê sữa", 15000, "Bánh mì PewPew", 4, 1000, 31));
        sqLite.addFood(new Food(3,"Bánh mì bò tiêu đen", 19000, "Bánh mì PewPew", 4, 300, 32));
        sqLite.addFood(new Food(3, "Bánh mì thập cẩm", 15000, "Bánh mì PewPew", 4, 1000, 33));
        sqLite.addFood(new Food(3,"Bánh mì gà nướng mật ong", 19000, "Bánh mì PewPew", 4, 300, 34));
        sqLite.addFood(new Food(3, "Bánh mì truyền thống", 15000, "Bánh mì PewPew", 4, 1000, 35));
        sqLite.addFood(new Food(3,"Bánh mì heo quay", 19000, "Bánh mì PewPew", 4, 300, 36));
        sqLite.addFood(new Food(3, "Bánh mì gà sốt nhật", 15000, "Bánh mì PewPew", 4, 1000, 37));
        sqLite.addFood(new Food(3,"Xôi thập cẩm", 19000, "Bánh mì PewPew", 4, 300, 38));
        sqLite.addFood(new Food(3, "Xôi gà", 15000, "Bánh mì PewPew", 4, 1000, 39));
        sqLite.addFood(new Food(3,"Xôi xá xíu", 19000, "Bánh mì PewPew", 4, 300, 40));

        sqLite.addFood(new Food(4,"Cà phê sữa", 21000, "Cà phê Ông Bầu", 4, 100, 41));
        sqLite.addFood(new Food(4, "Cà phê đen", 27000, "Cà phê Ông Bầu", 4, 100, 42));
        sqLite.addFood(new Food(4,"Bạc xỉu ông bầu", 21000, "Cà phê Ông Bầu", 4, 100, 43));
        sqLite.addFood(new Food(4, "Cà phê trứng đá", 27000, "Cà phê Ông Bầu", 4, 100, 44));
        sqLite.addFood(new Food(4,"Sữa đá milk foam", 21000, "Cà phê Ông Bầu", 4, 100, 45));
        sqLite.addFood(new Food(4, "Cà phê trứng nóng", 27000, "Cà phê Ông Bầu", 4, 100, 46));
        sqLite.addFood(new Food(4,"Americano", 21000, "Cà phê Ông Bầu", 4, 100, 47));
        sqLite.addFood(new Food(4, "Capuchino", 27000, "Cà phê Ông Bầu", 4, 100, 48));
        sqLite.addFood(new Food(4,"Cà phê dừa nóng", 21000, "Cà phê Ông Bầu", 4, 100, 49));
        sqLite.addFood(new Food(4, "Chocolate mint nóng", 27000, "Cà phê Ông Bầu", 4, 100, 50));

        sqLite.addFood(new Food(5,"Cơm sườn cây", 25000, "Cơm Tấm Sà Bì Chưởng", 5, 100, 51));
        sqLite.addFood(new Food(5, "Cơm sườn, bì, chả", 27000, "Cơm Tấm Sà Bì Chưởng", 4, 100, 52));
        sqLite.addFood(new Food(5,"Cơm sườn cốt lết", 25000, "Cơm Tấm Sà Bì Chưởng", 5, 100, 53));
        sqLite.addFood(new Food(5, "Cơm sườn cốt lết, chả", 27000, "Cơm Tấm Sà Bì Chưởng", 4, 100, 54));
        sqLite.addFood(new Food(5,"Cơm tấm bì, chả", 25000, "Cơm Tấm Sà Bì Chưởng", 5, 100, 55));
        sqLite.addFood(new Food(5, "Cơm tấm trứng ốp la", 27000, "Cơm Tấm Sà Bì Chưởng", 4, 100, 56));
        sqLite.addFood(new Food(5,"Tóp mỡ 'Rộp Rộp' ", 25000, "Cơm Tấm Sà Bì Chưởng", 5, 100, 57));
        sqLite.addFood(new Food(5, "Chả hấp", 27000, "Cơm Tấm Sà Bì Chưởng", 4, 100, 58));
        sqLite.addFood(new Food(5,"Canh chua", 25000, "Cơm Tấm Sà Bì Chưởng", 5, 100, 59));
        sqLite.addFood(new Food(5, "Canh rong biển", 27000, "Cơm Tấm Sà Bì Chưởng", 4, 100, 60));

        sqLite.addFood(new Food(6, "Pizza Gà BBQ Nướng Dứa", 27000, "Pizza Hut", 4, 100, 61));
        sqLite.addFood(new Food(6,"Pizza Chất Gà BBQ Nướng Dứa", 25000, "Pizza Hut", 5, 100, 62));
        sqLite.addFood(new Food(6, "Pizza Hải Sản Pesto Xanh", 27000, "Pizza Hut", 4, 100, 63));
        sqLite.addFood(new Food(6,"Pizza Hải Sản & Đào", 25000, "Pizza Hut", 5, 100, 64));
        sqLite.addFood(new Food(6, "Pizza Tôm Cocktail", 27000, "Pizza Hut", 4, 100, 65));
        sqLite.addFood(new Food(6,"Pizza phủ phô mai Ý", 25000, "Pizza Hut", 5, 100, 66));
        sqLite.addFood(new Food(6, "Pizza rau củ", 27000, "Pizza Hut", 4, 100, 67));
        sqLite.addFood(new Food(6,"Sườn siêu sao", 25000, "Pizza Hut", 5, 100, 68));
        sqLite.addFood(new Food(6, "Đùi gà chiên giòn", 27000, "Pizza Hut", 4, 100, 69));
        sqLite.addFood(new Food(6,"Nui Gà BBQ", 25000, "Pizza Hut", 5, 100, 70));

        sqLite.addFood(new Food(7, "Bún bò bò viên", 22000, "Bún Bò Huế 14B", 2, 500,71));
        sqLite.addFood(new Food(7, "Bún riêu cua chả", 22000, "Bún Bò Huế 14B", 2, 500,72));
        sqLite.addFood(new Food(7, "Chả cây", 22000, "Bún Bò Huế 14B", 2, 500,73));
        sqLite.addFood(new Food(7, "Bún riêu cua ốc chả", 22000, "Bún Bò Huế 14B", 2, 500,74));
        sqLite.addFood(new Food(7, "Bún bò Huế đặc biệt", 22000, "Bún Bò Huế 14B", 2, 500,75));
        sqLite.addFood(new Food(7, "Bún bò tái", 22000, "Bún Bò Huế 14B", 2, 500,76));
        sqLite.addFood(new Food(7, "Bún riêu thập cẩm", 22000, "Bún Bò Huế 14B", 2, 500,77));
        sqLite.addFood(new Food(7, "Trà tắc", 22000, "Bún Bò Huế 14B", 2, 500,78));
        sqLite.addFood(new Food(7, "Coca Cola", 22000, "Bún Bò Huế 14B", 2, 500,79));
        sqLite.addFood(new Food(7, "Xá xị", 22000, "Bún Bò Huế 14B", 2, 500,80));

        sqLite1 = new SQLiteRestaurantHelper(this);
        sqLite1.addRestaurant(new Restaurant("Trà sữa Koi Thé", "372 Võ Văn Ngân, P.Bình Thọ, Thủ Đức, HCM.", 0));
        sqLite1.addRestaurant(new Restaurant("Trà sữa GongCha", "101 Dân Chủ, P.Bình Thọ, Thủ Đức, HCM.", 0));
        sqLite1.addRestaurant(new Restaurant("Gà quán Popeyes", "58/13 Nguyễn Bỉnh Khiêm, P.Đa Kao, Quận 1, HCM", 0));
        sqLite1.addRestaurant(new Restaurant("Bánh mì PewPew", "66 Út Tịch, P.4, Tân Bình, TP. HCM", 0));
        sqLite1.addRestaurant(new Restaurant("Cà phê Ông Bầu", "170A Lâm Văn Bền, P.Tân Quy, Quận 7, HCM", 0));
        sqLite1.addRestaurant(new Restaurant("Cơm Tấm Sà Bì Chưởng", "179 Trần Bình Trọng, P.3, Quận 5, HCM", 0));
        sqLite1.addRestaurant(new Restaurant("Pizza Hut", "105-107 Nguyễn Thái Học, P.Cầu Ông Lãnh, Quận 1, HCM", 0));
        sqLite1.addRestaurant(new Restaurant("Bún Bò Huế 14B", "14B Đường Số 46,P.5, Quận 4, HCM", 0));


        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String m = sdf.format(cal.getTime());
//        String m = "23:25:00";
//        String m = "03:00:00";
        if (m.compareTo("8:30:00") <= 0 && m.compareTo("4:30:00") > 0) {
            meal = 1;
        } else if ((m.compareTo("07:30:00") > 0 && m.compareTo("10:30:00") <= 0) ||
                (m.compareTo("13:30:00") > 0 && m.compareTo("17:30:00") <= 0) ||
                (m.compareTo("20:00:00") > 0 && m.compareTo("22:30:00") <= 0)) {
            meal = 5;
        } else if (m.compareTo("10:30:00") > 0 && m.compareTo("13:30:00") <= 0) {
            meal = 2;
        } else if (m.compareTo("17:30:00") > 0 && m.compareTo("20:00:00") <= 0) {
            meal = 3;
        } else if ((m.compareTo("22:30:00") > 0 && m.compareTo("23:59:00") <= 0) || (m.compareTo("00:00:00") >= 0 && m.compareTo("07:30:00") <= 0)) {
            meal = 4;
        } else {
            meal = 0;
        }

        adapterfood1 = new FoodRecyclerViewAdapter(this);
        list_food.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_food.setAdapter(adapterfood1);

        adapterfood2 = new FoodRecyclerViewAdapter(this);
        list_foryou.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_foryou.setAdapter(adapterfood2);

        adapterfood3 = new FoodRecyclerViewAdapter(this);
        list_love.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_love.setAdapter(adapterfood3);

        sqLite = new SQLiteFoodHelper(this);

        List<Food> list_drink = sqLite.getFoodsByCategory(0);
        adapterfood1.setFoods(list_drink);
        list_food.setAdapter(adapterfood1);

        List<Food> list_foru = new ArrayList<>();
        switch (meal) {
            case 0: {
                foru.setVisibility(View.GONE);
                drink.setVisibility(View.GONE);
                dealhot.setVisibility(View.GONE);
                linear_meal.setVisibility(View.GONE);
                maybelove.setVisibility(View.GONE);
                notifi_main.setVisibility(View.VISIBLE);
                cartnoti.setEnabled(false);
                choosemap.setEnabled(false);
                break;
            }
            case 1: {
                text_foru.setText("Ăn sáng liền tay, đón ngay ngày mới!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(3);
                List<Food> list2 = sqLite.getFoodsByCategory(7);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                break;
            }
            case 2: {
                text_foru.setText("Buổi trưa vui vẻ cùng Foody!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(2);
                List<Food> list2 = sqLite.getFoodsByCategory(4);
                List<Food> list3 = sqLite.getFoodsByCategory(5);
                List<Food> list4 = sqLite.getFoodsByCategory(6);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                list_foru.addAll(list3);
                list_foru.addAll(list4);
                break;
            }
            case 3: {
                text_foru.setText("Muốn ăn ngon nhưng lại lười? Đặt bữa tối ngay!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(2);
                List<Food> list2 = sqLite.getFoodsByCategory(5);
                List<Food> list3 = sqLite.getFoodsByCategory(7);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                list_foru.addAll(list3);
                break;
            }
            case 4: {
                text_foru.setText("Đặt món khuya, cùng nhau hóa siêu lợn!!!");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(1);
                List<Food> list2 = sqLite.getFoodsByCategory(0);
                List<Food> list3 = sqLite.getFoodsByCategory(6);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                list_foru.addAll(list3);
                break;
            }
            case 5: {
                text_foru.setText("Giải lao nhẹ nhàng với đồ uống và ăn vặt nha");
                list_foru.clear();
                List<Food> list1 = sqLite.getFoodsByCategory(0);
                List<Food> list2 = sqLite.getFoodsByCategory(1);
                list_foru.addAll(list1);
                list_foru.addAll(list2);
                break;
            }
        }

        while (list_foru.size() > 8) {
            list_foru.remove(0);
        }

        adapterfood2.setFoods(list_foru);
        list_foryou.setAdapter(adapterfood2);

        Intent intent = getIntent();
        for (int i = 0; i < list_recommand.size(); i++) {
            if (intent.getIntExtra("recommand", -1) == list_recommand.get(i)) {
                list_recommand.remove(i);
            }
        }
        list_recommand.add(intent.getIntExtra("recommand", -1));

        if (list_recommand.size() > 8) {
            list_recommand.remove(0);
        }

        List<Food> list_love_foru = new ArrayList<>();
        for (int i = list_recommand.size() - 1; i >= 0; i--) {
            if (list_recommand.get(i) != -1) {
                list_love_foru.add(sqLite.getFoodById(list_recommand.get(i)));
            }
        }

        if (list_love_foru != null && list_love_foru.size() != 0) {
            maybelove.setVisibility(View.VISIBLE);
            adapterfood3.setFoods(list_love_foru);
            list_love.setAdapter(adapterfood3);
        } else {
            maybelove.setVisibility(View.GONE);
        }

        adapter1 = new RestaurantRecyclerViewAdapter(this);
        list_restaurant.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_restaurant.setAdapter(adapter1);
        sqLite1 = new SQLiteRestaurantHelper(this);
        List<Restaurant> restaurants = sqLite1.getAll();
        adapter1.setRestaurant(restaurants);
        list_restaurant.setAdapter(adapter1);

        sqLite2 = new SQLiteCartHelper(this);
        List<Cart> carts = sqLite2.getCartUserPay(String.valueOf(currentUserId), 0);

        sqLite.close();
        sqLite1.close();
        sqLite2.close();

        if (location != null && !location.equals("")) {
            String[] str = location.split(",");
            choosemap.setText(str[0]);
        } else {
            choosemap.setText("Get location now!");
        }



        this.choosemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
//              MainActivity.this.finish();
            }
        });

        notificationBadge.setNumber(carts.size());
        this.cartnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        this.getprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });

        fragmentBottomNavigation = new FragmentBottomNavigation(getSupportFragmentManager(), FragmentBottomNavigation.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(fragmentBottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ads1: {
                        viewPager.setCurrentItem(0);
                        break;
                    }

                    case R.id.ads2: {
                        viewPager.setCurrentItem(1);
                        break;
                    }

                    case R.id.ads3: {
                        viewPager.setCurrentItem(2);
                        break;
                    }
                }
                return false;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.footer);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()){
                        case R.id.home:
                            return true;

                        case R.id.cart:
                            intent = new Intent(getApplicationContext(), CartActivity.class);
                            intent.putExtra("currentUserId", currentUserId);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.orders:
                            intent = new Intent(getApplicationContext(), MyOderActivity.class);
                            intent.putExtra("currentUserId", currentUserId);
                            startActivity(intent);
                            overridePendingTransition(0,0);
                            return true;

                        case R.id.profile:
                            intent = new Intent(getApplicationContext(), ProfileActivity.class);
                            intent.putExtra("currentUserId", currentUserId);
                            startActivity(intent);                             overridePendingTransition(0,0);
                            return true;

                    }
                    return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.trangmieng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(0);
                putValue(1);
            }
        });

        this.fastfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(3);
            }
        });

        this.monchinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(5);
                putValue(7);
            }
        });

        this.pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(6);
            }
        });

        this.dochien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(2);
            }
        });

        this.banhmikep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(3);
            }
        });

        this.dohop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(7);
            }
        });

        this.comsuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(5);
            }
        });

        this.getmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putValue(0);
            }
        });
    }

    private void init() {
        choosemap = findViewById(R.id.choose_map);
        cartnoti = findViewById(R.id.cart_noti);
        getprofile = findViewById(R.id.get_profile);
        monchinh = findViewById(R.id.monchinh);
        dochien = findViewById(R.id.dochien);
        dohop = findViewById(R.id.dohop);
        banhmikep = findViewById(R.id.banhmikep);
        pizza = findViewById(R.id.pizza);
        comsuat = findViewById(R.id.comsuat);
        fastfood = findViewById(R.id.fastfood);
        trangmieng = findViewById(R.id.trangmieng);
        list_food = findViewById(R.id.list_food);
        list_restaurant = findViewById(R.id.list_restaurant);
        list_foryou = findViewById(R.id.list_foru);
        list_love = findViewById(R.id.list_love);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        notificationBadge = findViewById(R.id.badge);
        maybelove = findViewById(R.id.maybelove);
        text_foru = findViewById(R.id.text_foru);
        notifi_main = findViewById(R.id.notifi_main);
        linear_meal = findViewById(R.id.linear_meal);
        foru = findViewById(R.id.foru);
        dealhot = findViewById(R.id.dealhot);
        drink = findViewById(R.id.drink);
        getmore = findViewById(R.id.getmore_dirnk);

        notifi_main.setVisibility(View.GONE);

        databaseUser = new SQLiteUser(this);
        Intent intent = getIntent();
        currentUserId = intent.getExtras().getInt("currentUserId");
        cartnoti.setText(databaseUser.getUserById(currentUserId).getUsername());

    }

    private void putValue(int category) {
        Intent intent = new Intent(MainActivity.this, GetMoreActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
        MainActivity.this.finish();
    }


}