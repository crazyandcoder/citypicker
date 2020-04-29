/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ihidea.as.citypicker.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.ihidea.as.citypicker.model.CityPickerStyleBean;

import java.util.List;

/**
 * 说明:
 * 工单管理list adapter
 */

public class CityPickerAdapter extends RecyclerView.Adapter<CityPickerAdapter.MyViewHolder> {
    
    private Context mContext;
    
    private List<CityPickerStyleBean> datas;
    
    public CityPickerAdapter(Context context, List<CityPickerStyleBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }
    
    public List<CityPickerStyleBean> getData() {
        return this.datas;
    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.item_citypicker, parent, false));
        return holder;
    }
    
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CityPickerStyleBean item = datas.get(position);
        holder.mNameTv.setText("" + item.getTitle());
        holder.mIconImg.setBackgroundResource(item.getResourId());
        if (null != mOnItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }
    }
    
    @Override
    public int getItemCount() {
        return datas.size();
    }
    
    class MyViewHolder extends RecyclerView.ViewHolder {
        
        ImageView mIconImg;
        
        TextView mNameTv;
        
        public MyViewHolder(View view) {
            super(view);
            mNameTv = (TextView) view.findViewById(R.id.name_tv);
            mIconImg = (ImageView) view.findViewById(R.id.icon_img);
        }
    }
    
    private OnItemClickListener mOnItemClickListener;
    
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    
    public interface OnItemClickListener {
        /**
         * item点击事件
         *
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);
    }
    
}
