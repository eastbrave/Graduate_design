package com.android.graduate.daoway.b_category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.graduate.daoway.R;
import com.android.graduate.daoway.start.ClassDetailitemActivity;
import com.android.graduate.daoway.x_http.HttpService;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.CategoryBean;
import com.android.graduate.daoway.y_bean.ClassDetailBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFragment extends Fragment {
    @BindView(R.id.all_list)
    ListView allList;
    List<CategoryBean.DataBean.TagsBean> tags;
    List<ClassDetailBean.DataBean.ItemsBean> items=new ArrayList<>();
    private MyAdapter adapter;
    int res=1;
    int num;
    boolean bottom;
    private HttpService init;

    public static AllFragment newInstance(Bundle args) {
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        ButterKnife.bind(this, view);

        initBean();
        setAdapter();
        initData();
        setListen();
        initListen1();
        View viewById = view.findViewById(R.id.emptyElement);
        allList.setEmptyView(viewById);
        return view;
    }

    private void initListen1() {
        allList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getContext(), ClassDetailitemActivity.class);
                intent.putExtra("serviceId",items.get(i).getServiceId());
                intent.putExtra("id",items.get(i).getId());
                startActivity(intent);
            }
        });

    }

    private void setListen() {
        allList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if(i==0&&bottom&& res!=tags.size()){
                    if(init!=null){
                        return;
                    }
                     init = HttpUtils.init();
                    init
                    .getItems(tags.get(res).getName()).enqueue(new Callback<ClassDetailBean>() {
                        @Override
                        public void onResponse(Call<ClassDetailBean> call, Response<ClassDetailBean> response) {
                            List<ClassDetailBean.DataBean.ItemsBean> items1 = response.body().getData().getItems();
                            items.addAll(items1);
                            adapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onFailure(Call<ClassDetailBean> call, Throwable t) {
                        }
                    });
                    init=null;
                    res++;
                }
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
              if(i+i1==i2){
                  bottom=true;
              }else {
                  bottom=false;
              }
            }
        });
    }

    private void setAdapter() {
      adapter=new MyAdapter();
        allList.setAdapter(adapter);
    }

    private void initData() {
        HttpUtils.init().getItems(tags.get(0).getName()).enqueue(new Callback<ClassDetailBean>() {
            @Override
            public void onResponse(Call<ClassDetailBean> call, Response<ClassDetailBean> response) {
                List<ClassDetailBean.DataBean.ItemsBean>  bean= response.body().getData().getItems();
                items.addAll(bean);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ClassDetailBean> call, Throwable t) {
            }
        });
    }

    private void initBean() {
        Bundle arguments = getArguments();
        CategoryBean.DataBean bean = (CategoryBean.DataBean) arguments.getSerializable("bean");
        tags = bean.getTags();
    }
  class MyAdapter extends BaseAdapter{

      @Override
      public int getCount() {
          return items==null?0:items.size();
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
         if (view==null){
             view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_classdetail_vp_list,null);
             viewHolder = new ViewHolder(view);
         } else {
             viewHolder= (ViewHolder) view.getTag();
         }
          Picasso.with(getContext()).load(items.get(i).getPic_url()).placeholder(R.drawable.img_pic_default).into(viewHolder.vpFragmentListImageShow);
          viewHolder.vpFragmentListNameShow.setText(items.get(i).getName());
          viewHolder.vpFragmentListShangmenShow.setText(items.get(i).getAheadHours()+"小时");
          viewHolder.vpFragmentListContentShow.setText(items.get(i).getDescription());
          viewHolder.vpFragmentListTextShow.setText(""+items.get(i).getPrice());
          viewHolder.vpFragmentListTextShow1.setText(items.get(i).getPrice_unit());
          viewHolder.vpFragmentListZhenShow.setText("￥"+items.get(i).getOriginalPrice());
          viewHolder.vpFragmentListTitleShow.setText(items.get(i).getServiceTitle());
          viewHolder.vpFragmentListNumShow.setText("已售"+items.get(i).getSalesNum());
          viewHolder.vpFragmentListCommentRateShow.setText("好评"+items.get(i).getPositiveCommentRate());



          return view;
      }

      class ViewHolder {
          @BindView(R.id.vpFragment_list_image_show)
          ImageView vpFragmentListImageShow;
          @BindView(R.id.vpFragment_list_name_show)
          TextView vpFragmentListNameShow;
          @BindView(R.id.vpFragment_list_shangmen_show)
          TextView vpFragmentListShangmenShow;
          @BindView(R.id.vpFragment_list_content_show)
          TextView vpFragmentListContentShow;
          @BindView(R.id.vpFragment_list_text_show)
          TextView vpFragmentListTextShow;
          @BindView(R.id.vpFragment_list_text_show1)
          TextView vpFragmentListTextShow1;
          @BindView(R.id.vpFragment_list_zhen_show)
          TextView vpFragmentListZhenShow;
          @BindView(R.id.vpFragment_list_title_show)
          TextView vpFragmentListTitleShow;
          @BindView(R.id.vpFragment_list_num_show)
          TextView vpFragmentListNumShow;
          @BindView(R.id.vpFragment_list_CommentRate_show)
          TextView vpFragmentListCommentRateShow;

          ViewHolder(View view) {
              view.setTag(this);
              ButterKnife.bind(this, view);
          }
      }
  }

}
