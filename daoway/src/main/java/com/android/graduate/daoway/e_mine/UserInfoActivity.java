package com.android.graduate.daoway.e_mine;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/14.
 */
public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.user_info_back_iv)
    ImageView backIv;
    @BindView(R.id.user_info_exit_tv)
    TextView exitTv;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        exitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // TODO: 2016/9/14 弹出对话框，确认是否退出
                AlertDialog.Builder builder=new AlertDialog.Builder(UserInfoActivity.this);
                builder.setTitle("确定要退出吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sp=getSharedPreferences("isLogin",MODE_PRIVATE);
                        edit = sp.edit();
                        edit.putBoolean("login_key",false);//登陆状态改为false
                        //并清空本地保存的用户名和id；也可以不清空
                         /*edit.putString("userName",null);
                         edit.putLong("userID",0);*/
                        edit.commit();
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

            }
        });
    }
}
