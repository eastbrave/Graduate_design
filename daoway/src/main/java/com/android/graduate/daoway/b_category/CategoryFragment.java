package com.android.graduate.daoway.b_category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.android.graduate.daoway.widget.MyGridView;
import com.android.graduate.daoway.x_http.HttpUtils;
import com.android.graduate.daoway.y_bean.CategoryBean;
import com.android.graduate.daoway.z_constant.GvAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/5.
 */
public class CategoryFragment extends Fragment {
 private Context mContext;
  private   List<CategoryBean.DataBean> data;
    @BindView(R.id.category_list1)
    ListView categoryList1;
    @BindView(R.id.category_list2)
    ListView categoryList2;
    private list1Adapter adaptet1;
    String city="武汉";
    private list2Adapter adaptet2;
    private boolean isStop=true;
    private boolean num=true;
    public static CategoryFragment newInstance(Bundle bundle) {
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO: 2016/9/5
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        setAdapter();
        initData();
        initListen();
        return view;
    }

    private void initListen() {
      categoryList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              if(position<=5){
                  categoryList2.setSelection(position);
              }
              categoryList1.setSelection(position);
              adaptet1.changeSelected(position);

          }
      });
       categoryList2.setOnScrollListener(new AbsListView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(AbsListView absListView, int scrollState) {
               //停止滚动时候判断
               if(scrollState==SCROLL_STATE_IDLE) {
                   isStop=true;
               }else {
                   isStop=false;
               }
           }

           @Override
           public void onScroll(AbsListView absListView, int firstVisibleItem, int i1, int i2) {
              if(firstVisibleItem+i1==i2){
                  num=false;
              } else {
                  num=true;
              }
               if(!isStop){//静止状态下不定住

                   categoryList1.setSelection(firstVisibleItem);
               }
               adaptet1.changeSelected(firstVisibleItem);
           }
       });
    }

    private void setAdapter() {
      adaptet1=new list1Adapter();
        categoryList1.setAdapter(adaptet1);
        adaptet2=new list2Adapter();
        categoryList2.setAdapter(adaptet2);
    }

    private void initData() {
        HttpUtils.init().getData(city).enqueue(new Callback<CategoryBean>() {
            @Override
            public void onResponse(Call<CategoryBean> call, Response<CategoryBean> response) {
                data = response.body().getData();
                adaptet1.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CategoryBean> call, Throwable t) {

            }
        });
    }
  class list1Adapter extends BaseAdapter{
      private int mSelect=0;
      public void changeSelected(int position) {
          if (position != mSelect) {
              mSelect = position;
              notifyDataSetChanged();
          }
      }
      @Override
      public int getCount() {
          return data==null?0:data.size();
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
         if(view==null){
             view=LayoutInflater.from(mContext).inflate(R.layout.fragment_category_list1,null);
             viewHolder=new ViewHolder(view);
         }else {
             viewHolder= (ViewHolder) view.getTag();
         }
          Picasso.with(mContext).load(data.get(i).getIconUrl()).into(viewHolder.image);
          viewHolder.text.setText(data.get(i).getName());
          // 如果明目显示的第一条数据和当前显示的数据的索引值一直，就高亮显示
          if (mSelect == i) {
              viewHolder.view.setVisibility(View.VISIBLE);

          } else {
              viewHolder.view.setVisibility(View.GONE);

          }


          switch (i){
              case 0:
               viewHolder.view.setBackgroundResource(R.color.pidan_cheng);
              break;
              case 1:
                  viewHolder.view.setBackgroundResource(R.color.pidan_cheng);
                  break;
              case 2:
                  viewHolder.view.setBackgroundResource(R.color.pidan_huangcheng);
                  break;
              case 3:
                  viewHolder.view.setBackgroundResource(R.color.pidan_lan);
                  break;
              case 4:
                  viewHolder.view.setBackgroundResource(R.color.pidan_lan);
                  break;
              case 5:
                  viewHolder.view.setBackgroundResource(R.color.pidan_lan);
                  break;
              case 6:
                  viewHolder.view.setBackgroundResource(R.color.pidan_lan);
                  break;
              case 7:
                  viewHolder.view.setBackgroundResource(R.color.pidan_fen);
                  break;
              case 8:
                  viewHolder.view.setBackgroundResource(R.color.pidan_lv);
                  break;
              case 9:
                  viewHolder.view.setBackgroundResource(R.color.pidan_lv);
                  break;
              case 10:
                  viewHolder.view.setBackgroundResource(R.color.pidan_lan);
                  break;

          }
          return view;
      }
  }
   class ViewHolder{
       @BindView(R.id.category_list1_image)
      ImageView image;
       @BindView(R.id.category_list1_text)
       TextView text;
       @BindView(R.id.category_list1_view)
       View view;
       public ViewHolder(View view) {
        view.setTag(this);
        ButterKnife.bind(this,view);
       }
   }
    class list2Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data==null?0:data.size();
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
           if(view==null){
               view=LayoutInflater.from(mContext).inflate(R.layout.fragment_category_list2,null);
               viewHolder2=new ViewHolder2(view);
           }else {
               viewHolder2= (ViewHolder2) view.getTag();
           }
           viewHolder2.text1.setText(data.get(i).getName());
            String name="热门";
            if(data.get(i).getName().equals(name)){
                viewHolder2.text2.setVisibility(View.GONE);
            }
            GvAdapter  gvAdapter=new GvAdapter(mContext,data.get(i).getTags());
             viewHolder2.gv.setAdapter(gvAdapter);
            gvAdapter.notifyDataSetChanged();
            return view;
        }
    }
    class ViewHolder2{
        @BindView(R.id.category_list2_text)
       TextView text1;
        @BindView(R.id.category_list2_text1)
        TextView text2;
        @BindView(R.id.category_list2_myGridView)
       MyGridView gv;
        public ViewHolder2(View view) {
           view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
