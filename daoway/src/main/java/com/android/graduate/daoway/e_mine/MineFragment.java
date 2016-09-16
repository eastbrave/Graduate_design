package com.android.graduate.daoway.e_mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.graduate.daoway.R;

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
        initListener();
        return view;
    }

    private void initListener() {
        clickToUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
