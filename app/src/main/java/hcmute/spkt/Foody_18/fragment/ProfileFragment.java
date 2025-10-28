package hcmute.spkt.Foody_18.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import hcmute.spkt.Foody_18.R;
import hcmute.spkt.Foody_18.activity.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import hcmute.spkt.Foody_18.activity.SplashActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import hcmute.spkt.Foody_18.controller.SQLiteUser;

public class ProfileFragment extends Fragment {

    private TextView name, turnback, myorder, camera, favorite, history, wallet, settings, about, textverifi, textgetverifi;
    private ImageButton verifi;
    public static CircleImageView avatar;

    Activity activity;
    private int currentUserId;
    private SQLiteUser databaseUser;

    public ProfileFragment(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        init(view);

        this.turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
                getActivity().finish();
            }
        });

        this.camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                getActivity().startActivityForResult(open, 1000);
            }
        });

        this.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new InfoFragment(getActivity());
                getFrament(current);
            }
        });

        verifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new InfoFragment(getActivity());
                getFrament(current);
            }
        });

        this.myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new MyOrderFragment(activity);
                getFrament(current);
            }
        });

        this.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new SettingFragment();
                getFrament(current);
            }
        });

        this.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment current = new AboutFragment();
                getFrament(current);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void init(View view) {
        turnback = view.findViewById(R.id.back_to_main2);
        name = view.findViewById(R.id.name_profile);
        verifi = view.findViewById(R.id.img_verifi);
        myorder = view.findViewById(R.id.my_order);
        favorite = view.findViewById(R.id.favorite);
        history = view.findViewById(R.id.purchase_history);
        wallet = view.findViewById(R.id.wallet_coupon);
        settings = view.findViewById(R.id.address_manager);
        about = view.findViewById(R.id.support);
        camera = view.findViewById(R.id.cammera);
        avatar = view.findViewById(R.id.profile_image);
        textverifi = view.findViewById(R.id.text_get_verifi);
        textgetverifi = view.findViewById(R.id.get_verifi);

        Intent intent = activity.getIntent();
        currentUserId = intent.getExtras().getInt("currentUserId");
        databaseUser = new SQLiteUser(activity);
        name.setText(databaseUser.getUserById(currentUserId).getUsername());

        if (SplashActivity.login_code == 1) {
            Drawable drawable = getResources().getDrawable(R.drawable.facebook2);
            verifi.setBackground(drawable);
            verifi.setEnabled(false);
            textverifi.setText("Cảm ơn bạn đã sử dụng ứng dụng của chúng tôi. Chúc bạn mua hàng vui vẻ.");
            textgetverifi.setText("Bạn đang đăng nhập Facebook!");
        } else {
            if (false) {
                Drawable drawable = getResources().getDrawable(R.drawable.message);
                verifi.setBackground(drawable);
                textgetverifi.setText("Bạn đã xác thực email!");
                textverifi.setText("Bạn sẽ nhận được nhiều thông báo quan trọng và dùng email cho việc đặt lại mật khẩu.");
            } else {
                Drawable drawable = getResources().getDrawable(R.drawable.message);
                verifi.setBackground(drawable);
                textgetverifi.setText("Nhấp vào ảnh để xác thực email!");
                textverifi.setText("Bạn sẽ nhận được nhiều thông báo quan trọng và dùng email cho việc đặt lại mật khẩu.");
            }
        }
    }

    public void getFrament(Fragment current) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.profile_holder, current)
                .addToBackStack(null)
                .commit();
    }
}
