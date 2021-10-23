package com.lljjcoder.style.citythreelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.widget.RecycleViewDividerForList;

import java.util.List;

import static com.lljjcoder.style.citylist.utils.CityListLoader.BUNDATA;
import static com.lljjcoder.style.citythreelist.ProvinceActivity.RESULT_DATA;

public class AreaActivity extends Activity {
    
    private TextView mCityNameTv;
    
    private ImageView mImgBack;
    
    private RecyclerView mCityRecyclerView;
    
    private CityInfoBean mProCityInfo = null;
    
    private CityBean areaBean = new CityBean();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        mProCityInfo = this.getIntent().getParcelableExtra(BUNDATA);
        initView();
        
        setData();
        
    }
    
    private void setData() {
        
        if (mProCityInfo != null && mProCityInfo.getCityList().size() > 0) {
            mCityNameTv.setText("" + mProCityInfo.getName());
            
            final List<CityInfoBean> cityList = mProCityInfo.getCityList();
            if (cityList == null) {
                return;
            }
            
            CityAdapter cityAdapter = new CityAdapter(AreaActivity.this, cityList);
            mCityRecyclerView.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
                @Override
                public void onItemSelected(View view, int position) {
                    
                    areaBean.setName(cityList.get(position).getName());
                    areaBean.setId(cityList.get(position).getId());
                    
                    //将计算的结果回传给第一个Activity
                    Intent reReturnIntent = new Intent();
                    reReturnIntent.putExtra("area", areaBean);
                    setResult(RESULT_DATA, reReturnIntent);
                    //退出第二个Activity
                    AreaActivity.this.finish();
                    
                }
            });
            
        }
    }
    
    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_left);
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mImgBack.setVisibility(View.VISIBLE);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));
        
    }
    
}
