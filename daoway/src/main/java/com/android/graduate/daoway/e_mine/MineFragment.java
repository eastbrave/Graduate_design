package com.android.graduate.daoway.e_mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.h_login_and_register.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/9/5.
 */
public class MineFragment extends Fragment {

    @BindView(R.id.mine_circle_image)
    CircleImageView circleImage;
    @BindView(R.id.mine_user_name)
    TextView userName;
    @BindView(R.id.mine_click_to_user_info)
    RelativeLayout clickToUserInfo;
    private Context mContext;
    private SharedPreferences sp;
    private boolean login_key;

    public static MineFragment newInstance(Bundle bundle) {
        MineFragment fragment = new MineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: 2016/9/5
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
         sp=mContext.getSharedPreferences("isLogin",Context.MODE_PRIVATE);
        login_key = sp.getBoolean("login_key", false);
        if(!login_key){
            userName.setText("未登录");
        }else {
            userName.setText("皮蛋君");
        }
        initListener();
        return view;
    }

    private void initListener() {
        clickToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!login_key){
                    Intent intent=new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    return;
                }

                Intent intent=new Intent(mContext,UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
