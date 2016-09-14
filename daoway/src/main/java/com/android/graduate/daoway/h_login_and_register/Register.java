package com.android.graduate.daoway.h_login_and_register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.utils.BaseActivity;

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
        String phoneStr = phoneEt.getText().toString();
        String passwordStr = passwordEt.getText().toString();
        String passwordRe = passwordReEt.getText().toString();
        if(!TextUtils.isEmpty(phoneStr)&&!TextUtils.isEmpty(passwordStr)
                &&!TextUtils.isEmpty(passwordRe)&&passwordEt.equals(passwordReEt)){
            return 0;
        }else if (TextUtils.isEmpty(phoneStr)||TextUtils.isEmpty(passwordStr)
                ||TextUtils.isEmpty(passwordRe)){

            return 1;

        }else if(!passwordEt.equals(passwordReEt)){

            return 2;

        }
        return 1;
    }

    private boolean isExist(){

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
                if(code==0&&isExist()){

                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else if(code==1) {
                    Toast.makeText(Register.this, "信息填写不完整，请填写完整", Toast.LENGTH_SHORT).show();
                }else if(code==1){
                    Toast.makeText(Register.this, "两次输入的密码不一致，请修改", Toast.LENGTH_SHORT).show();
                }else if(code==0&&!isExist()){

                    Toast.makeText(Register.this, "注册手机号已经存在，请您更换手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
