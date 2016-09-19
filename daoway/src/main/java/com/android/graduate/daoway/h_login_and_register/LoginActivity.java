package com.android.graduate.daoway.h_login_and_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.User;
import com.android.graduate.daoway.UserDao;
import com.android.graduate.daoway.main.MainActivity;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_back_iv)
    ImageView backIv;
    @BindView(R.id.login_register_tv)
    TextView registerTv;
    @BindView(R.id.login_username_et)
    EditText usernameEt;
    @BindView(R.id.login_username_clear_iv)
    ImageView usernameClearIv;
    @BindView(R.id.login_password_et)
    EditText passwordEt;
    @BindView(R.id.login_password_clear_iv)
    ImageView passwordClearIv;
    @BindView(R.id.login_click_btn)
    Button loginClickBtn;
    @BindView(R.id.login_forget_password)
    TextView loginForgetPassword;//忘记密码
    private String nameStr;
    private String passwordStr;
    public UserDao userDao;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initListener();
    }

    private void inputInfo() {
        //第一行获取光标
        usernameEt.requestFocus();


    }

    @Override
    protected void onStart() {
        super.onStart();
        inputInfo();
    }

    private void initListener() {
        //返回键结束当前页面
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        //点击跳转到注册页面
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });

        //点击登陆按钮
        loginClickBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //获取信息
                nameStr = usernameEt.getText().toString();
                passwordStr = passwordEt.getText().toString();
                userDao = DBUtils.getUserDao(LoginActivity.this);
                QueryBuilder<User> builder = userDao.queryBuilder();
                builder.where(UserDao.Properties.Phone.eq(nameStr) ,UserDao.Properties.Password.eq(passwordStr));

                List<User> list = builder.list();
                if(list.size()!=0){
                    //存在该手机号和密码
                    //登陆成功,将账号存入本地；
                    User user = list.get(0);
                    Long id = user.getId();
                    sp=getSharedPreferences("isLogin",MODE_PRIVATE);
                    edit = sp.edit();
                    edit.putBoolean("login_key",true);//登陆状态改为true
                    edit.putString("userName",nameStr);
                    edit.putLong("userID",id);

                    edit.commit();
                    LoginActivity.this.finish();
                }else {
                    Toast.makeText(LoginActivity.this, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //清空信息操作
        usernameClearIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEt.setText(null);

            }
        });
        passwordClearIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordEt.setText(null);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
