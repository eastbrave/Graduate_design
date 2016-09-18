package com.android.graduate.daoway.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.main.MainActivity;
import com.android.graduate.daoway.utils.BaseActivity;
import com.mob.tools.utils.SharePrefrenceHelper;

/**
 * Created by Administrator on 2016/9/6.
 */
public class StartActivity extends BaseActivity {

    public Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Intent intent=new Intent(StartActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

            mHandler.sendEmptyMessageDelayed(1,3000);
        }




}
