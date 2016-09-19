package com.android.graduate.daoway.h_login_and_register;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.graduate.daoway.R;

import com.android.graduate.daoway.z_db.User;
import com.android.graduate.daoway.z_db.UserDao;
import com.android.graduate.daoway.utils.BaseActivity;
import com.android.graduate.daoway.z_db.DBUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;



import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Register extends BaseActivity {

    @BindView(R.id.register_back_iv)
    ImageView backIv;
    @BindView(R.id.register_phone_et)
    EditText phoneEt;
    @BindView(R.id.register_password_et)
    EditText passwordEt;
    @BindView(R.id.register_password_repeat_et)
    EditText passwordReEt;
    @BindView(R.id.register_commit)
    Button commitBtn;
    private String phoneStr;
    private String passwordStr;
    private String passwordReStr;
    private UserDao userDao;
    public Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            finish();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initListener();
    }
    /*
    * 判断用户注册信息填写是否正确
    * */
    private int inputInfo() {
        phoneStr = phoneEt.getText().toString();
        passwordStr = passwordEt.getText().toString();
        passwordReStr = passwordReEt.getText().toString();
        if(!TextUtils.isEmpty(phoneStr)&&!TextUtils.isEmpty(passwordStr)
                &&!TextUtils.isEmpty(passwordReStr)&&passwordStr.equals(passwordReStr)){
            //都不为空，且密码相同
            return 0;
        }else if (TextUtils.isEmpty(phoneStr)||TextUtils.isEmpty(passwordStr)
                ||TextUtils.isEmpty(passwordReStr)){

            return 1;

        }else if(!passwordStr.equals(passwordReStr)){

            return 2;

        }
        return 3;
    }

    private boolean isExist(){
        userDao = DBUtils.getUserDao(Register.this);
        QueryBuilder<User> builder = userDao.queryBuilder();
        builder.where(UserDao.Properties.Phone.eq(phoneStr));
        List<User> list = builder.list();
        if(list.size()!=0){
            //已经存在该手机号
            return true;
        }
        return false;
    }



    private void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int code=inputInfo();
                if(code==0&&!isExist()){
                   User user=new User();
                    user.setPhone(phoneStr);
                    user.setPassword(passwordStr);
                    userDao.insert(user);
                    Toast.makeText(Register.this, "注册成功,3秒后跳转到登陆界面！", Toast.LENGTH_SHORT).show();
                    mHandler.sendEmptyMessageDelayed(1,3000);
                }else if(code==1) {
                    Toast.makeText(Register.this, "信息填写不完整，请填写完整", Toast.LENGTH_SHORT).show();
                }else if(code==2){
                    Toast.makeText(Register.this, "两次输入的密码不一致，请修改", Toast.LENGTH_SHORT).show();
                }else if(code==0&&isExist()){

                    Toast.makeText(Register.this, "注册手机号已经存在，请您更换手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
