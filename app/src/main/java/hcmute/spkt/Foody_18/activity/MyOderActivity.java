package hcmute.spkt.Foody_18.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;

import hcmute.spkt.Foody_18.R;
import hcmute.spkt.Foody_18.fragment.MyOrderFragment;
import hcmute.spkt.Foody_18.fragment.ProfileFragment;

public class MyOderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_oder);
        Fragment current = new MyOrderFragment(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.myOderFragment, current, "TAG")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}