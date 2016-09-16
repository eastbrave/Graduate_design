package com.android.graduate.daoway.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.utils.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PoPwindowActivity extends BaseActivity {
    String[] splits;
    @BindView(R.id.pop_tv)
    TextView popTv;

    @BindView(R.id.pop_vp)
    ViewPager popVp;
    @BindView(R.id.pop_tv1)
    TextView popTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po_pwindow);
        ButterKnife.bind(this);
        initArray();
        setAdapter();
    }

    private void setAdapter() {
        Myadapter myadapter = new Myadapter();
        popVp.setAdapter(myadapter);
    }

    private void initArray() {
        Intent intent = getIntent();
        splits = intent.getStringArrayExtra("split");

    }

    @OnClick(R.id.pop_tv1)
    public void onClick() {
        finish();
    }

    class Myadapter extends PagerAdapter {


        @Override
        public int getCount() {
            return splits == null ? 0 : splits.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {


            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //创建Item
            ImageView imageView = new ImageView(PoPwindowActivity.this);
            Picasso.with(PoPwindowActivity.this).load(splits[position]).placeholder(R.drawable.img_pic_default).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
         //   popTv.setText(position+"/"+splits.length);
            Log.d("yanyan", "instantiateItem: "+position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
