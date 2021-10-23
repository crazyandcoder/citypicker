package com.lljjcoder.style.citythreelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citypickerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：liji on 2017/12/16 15:13
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {
    List<CityInfoBean> cityList = new ArrayList<>();
    
    Context context;
    
    private OnItemSelectedListener mOnItemClickListener;
    
    public void setOnItemClickListener(OnItemSelectedListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    
    public interface OnItemSelectedListener {
        /**
         * item点击事件
         *
         * @param view
         * @param position
         */
        void onItemSelected(View view, int position);
    }
    
    public CityAdapter(Context context, List<CityInfoBean> cityList) {
        this.cityList = cityList;
        this.context = context;
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_citylist, parent, false));
        return holder;
    }
    
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(cityList.get(position).getName());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null && position < cityList.size()) {
                    mOnItemClickListener.onItemSelected(v, position);
                }
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return cityList.size();
    }
    
    class MyViewHolder extends RecyclerView.ViewHolder {
        
        TextView tv;
        
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.default_item_city_name_tv);
        }
    }
}
