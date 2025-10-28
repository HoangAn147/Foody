package hcmute.spkt.Foody_18.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.spkt.Foody_18.R;
import hcmute.spkt.Foody_18.activity.MainActivity;
import hcmute.spkt.Foody_18.controller.SQLiteUser;
import hcmute.spkt.Foody_18.model.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static hcmute.spkt.Foody_18.activity.SplashActivity.login_code;

public class MailRegisterFragment extends Fragment {

    private EditText regisName, regisMail, regisPass, regisRePass;
    private TextView register;
    private FirebaseAuth firebaseAuth;
    private SQLiteUser databaseUser;

    public MailRegisterFragment() {

    }

    public static MailRegisterFragment newInstance() {
        MailRegisterFragment fragment = new MailRegisterFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.mail_register_fragment, container, false);
        regisName = view.findViewById(R.id.regis_name);
        regisMail = view.findViewById(R.id.regis_mail);
        regisPass = view.findViewById(R.id.regis_password);
        regisRePass = view.findViewById(R.id.regis_repassword);
        register = view.findViewById(R.id.register);
        firebaseAuth = FirebaseAuth.getInstance();

        this.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strml = regisMail.getText().toString().trim();
                String strpw = regisPass.getText().toString().trim();
                String strrp = regisRePass.getText().toString().trim();
                String strnm = regisName.getText().toString().trim();
                databaseUser = new SQLiteUser(inflater.getContext());

                if (TextUtils.isEmpty(strnm)) {
                    regisName.setError("Không được để trống tên người dùng");
                    return;
                }
                else
                if (TextUtils.isEmpty(strml)) {
                    regisMail.setError("Không được để trống email đăng nhập");
                    return;
                }
                else
                if (strpw.length() < 6) {
                    regisPass.setError("Độ dài mật khẩu phải lớn hơn 6 kí tự");
                    return;
                }
                else    
                if (strpw.equals(strrp) != true) {
                    regisRePass.setError("Mật khẩu bạn vừa nhập không trùng khớp");
                    return;
                }
                else
                if (databaseUser.checkEmailExist(strml)){
                    Toast.makeText(inflater.getContext(), "Email da ton tai.", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user = new User(strml, strpw, strnm);

                    if (databaseUser.addUser(user)) {
                        Toast.makeText(inflater.getContext(), "Dang ky tai khoan thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(inflater.getContext(), "Dang ky khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}