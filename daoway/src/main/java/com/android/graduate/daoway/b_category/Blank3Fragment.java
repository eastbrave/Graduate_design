package com.android.graduate.daoway.b_category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.start.PoPwindowActivity;
import com.android.graduate.daoway.widget.MyGridView;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.PingjiaBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Blank3Fragment extends Fragment {
    RadioButton[] rbTabs;
    @BindView(R.id.rg_pingjia)
    RadioGroup rgPingjia;
    @BindView(R.id.list_pingjia)
    ListView listPingjia;
    @BindView(R.id.rbt_show1)
    RadioButton rbtShow1;
    @BindView(R.id.rbt_show2)
    RadioButton rbtShow2;
    @BindView(R.id.rbt_show3)
    RadioButton rbtShow3;
    @BindView(R.id.rbt_show4)
    RadioButton rbtShow4;
    @BindView(R.id.rbt_show5)
    RadioButton rbtShow5;
    private String serviceId;
    List<PingjiaBean.DataBean.CommentsBean> mlist = new ArrayList<>();
    List<PingjiaBean.DataBean.CommentsBean> mlist0 = new ArrayList<>();
    private MyAdapter adapter;

    List<PingjiaBean.DataBean.CommentsBean> mlist1=new ArrayList<>();



    List<PingjiaBean.DataBean.CommentsBean> mlist2=new ArrayList<>();

    List<PingjiaBean.DataBean.CommentsBean> mlist3=new ArrayList<>();

    List<PingjiaBean.DataBean.CommentsBean> mlist4=new ArrayList<>();


    public static Blank3Fragment newInstance(Bundle args) {
        Blank3Fragment fragment = new Blank3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank3, container, false);
        ButterKnife.bind(this, view);
        initID();
        initRTB();

        setAdapter();
        initData();
        return view;
    }

    private void initID() {
        Bundle arguments = getArguments();
        serviceId = (String) arguments.get("serviceId");
    }

    private void setAdapter() {
        adapter = new MyAdapter();
        listPingjia.setAdapter(adapter);
    }

    private void initData() {
        HttpUtils.init().getComments(serviceId).enqueue(new Callback<PingjiaBean>() {
            @Override
            public void onResponse(Call<PingjiaBean> call, Response<PingjiaBean> response) {
                PingjiaBean.DataBean data = response.body().getData();
                rbtShow1.setText(data.getTotalCount() + "\n" + "全部");
                rbtShow2.setText(data.getGoodCount() + "\n" + "好评");
                rbtShow3.setText(data.getAverageCount() + "\n" + "中评");
                rbtShow4.setText(data.getBadCount() + "\n" + "差评");
                rbtShow5.setText(data.getHasImgCount() + "\n" + "有图");
                List<PingjiaBean.DataBean.CommentsBean> comments = response.body().getData().getComments();
                mlist0.addAll(comments);
                mlist.addAll(mlist0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PingjiaBean> call, Throwable t) {

            }
        });
        //好评
        HttpUtils.init().getComments1(serviceId).enqueue(new Callback<PingjiaBean>() {
            @Override
            public void onResponse(Call<PingjiaBean> call, Response<PingjiaBean> response) {
                List<PingjiaBean.DataBean.CommentsBean> comments1 = response.body().getData().getComments();
                mlist1.addAll(comments1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PingjiaBean> call, Throwable t) {

            }
        });
        //中评
        HttpUtils.init().getComments2(serviceId).enqueue(new Callback<PingjiaBean>() {
            @Override
            public void onResponse(Call<PingjiaBean> call, Response<PingjiaBean> response) {
                List<PingjiaBean.DataBean.CommentsBean> comments2 = response.body().getData().getComments();
                mlist2.addAll(comments2);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PingjiaBean> call, Throwable t) {

            }
        });
        //差评
        HttpUtils.init().getComments3(serviceId).enqueue(new Callback<PingjiaBean>() {
            @Override
            public void onResponse(Call<PingjiaBean> call, Response<PingjiaBean> response) {
                List<PingjiaBean.DataBean.CommentsBean> comments3 = response.body().getData().getComments();
                mlist3.addAll(comments3);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PingjiaBean> call, Throwable t) {

            }
        });
        //有图
        HttpUtils.init().getComments4(serviceId).enqueue(new Callback<PingjiaBean>() {
            @Override
            public void onResponse(Call<PingjiaBean> call, Response<PingjiaBean> response) {
                List<PingjiaBean.DataBean.CommentsBean> comments4 = response.body().getData().getComments();
               mlist4.addAll(comments4);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PingjiaBean> call, Throwable t) {

            }
        });
        initRG();
    }

    private void initRTB() {
        int childCount = rgPingjia.getChildCount();
        rbTabs = new RadioButton[childCount];

    }

    private void initRG() {
        rgPingjia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbt_show1:
                        mlist.clear();
                        mlist.addAll(mlist0);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rbt_show2:
                        mlist.clear();
                        mlist.addAll(mlist1);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rbt_show3:
                        mlist.clear();
                        mlist.addAll(mlist2);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rbt_show4:
                        mlist.clear();
                        mlist.addAll(mlist3);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.rbt_show5:
                        mlist.clear();
                        mlist.addAll(mlist4);
                        adapter.notifyDataSetChanged();
                        break;
                }


            }
        });
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mlist == null ? 0 : mlist.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_blank3_pingjia_list, null);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Picasso.with(getContext()).load(mlist.get(i).getIconUrl()).placeholder(R.drawable.icon_head_comment).into(viewHolder.pingjiaListImage);
            viewHolder.pingjiaListText.setText(mlist.get(i).getNick());
            viewHolder.pingjiaListRatingBar.setRating(mlist.get(i).getStar());
            long createtime = mlist.get(i).getCreatetime();
            String date = returnDate(createtime);
            viewHolder.pingjiaListDate.setText(date);
            viewHolder.pingjiaListContent.setText(mlist.get(i).getComment());

            Object imgPath = mlist.get(i).getImgPath();
            String[] split=null;
            if(imgPath!=null){
                viewHolder.MyGridViewPingjia.setVisibility(View.VISIBLE);
              split = imgPath.toString().split(",");

            }else {
               viewHolder.MyGridViewPingjia.setVisibility(View.GONE);
            }
            MyGridAdapter   myAdapter=new MyGridAdapter(split);
            viewHolder.MyGridViewPingjia.setAdapter(myAdapter);

            return view;
        }

        class ViewHolder {
            @BindView(R.id.pingjia_list_image)
            ImageView pingjiaListImage;
            @BindView(R.id.pingjia_list_text)
            TextView pingjiaListText;
            @BindView(R.id.pingjia_list_ratingBar)
            RatingBar pingjiaListRatingBar;
            @BindView(R.id.pingjia_list_date)
            TextView pingjiaListDate;
            @BindView(R.id.pingjia_list_content)
            TextView pingjiaListContent;
            @BindView(R.id.MyGridView_pingjia)
            MyGridView MyGridViewPingjia;

            ViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this, view);
            }
        }

        class MyGridAdapter extends BaseAdapter {
            String[] split;

            public MyGridAdapter(String[] split) {
                this.split = split;
            }

            @Override
            public int getCount() {
                return split == null ? 0 : split.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ViewHolder2 viewHolder2;
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pingjia_mygrid, null);
                viewHolder2=new ViewHolder2(view);
                }else {
                    viewHolder2= (ViewHolder2) view.getTag();
                }
                  Picasso.with(getContext()).load(split[i]).placeholder(R.drawable.img_pic_default).into(viewHolder2.pingjiaMygridImage);
             //设置监听
               view.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Intent intent=new Intent(getContext(), PoPwindowActivity.class);
                       intent.putExtra("split",split) ;
                       startActivity(intent);
                   }
               });
                return view;
            }

             class ViewHolder2 {
                @BindView(R.id.pingjia_mygrid_image)
                ImageView pingjiaMygridImage;

                ViewHolder2(View view) {
                    view.setTag(this);
                    ButterKnife.bind(this, view);
                }
            }
        }


    }

    public String returnDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date(time));
        return now;
    }
}
