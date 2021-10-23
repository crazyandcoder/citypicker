package com.ihidea.as.citypicker.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.lljjcoder.Interface.OnCustomCityPickerItemClickListener;
import com.lljjcoder.bean.CustomCityData;
import com.lljjcoder.citywheel.CustomConfig;
import com.lljjcoder.style.citycustome.CustomCityPicker;

import java.util.ArrayList;
import java.util.List;

public class CityPickerCustomeDataActivity extends Activity {

    private TextView customeBtn;
    private TextView resultTv;


    EditText mProVisibleCountEt;

    CheckBox mProCyclicCk;

    CheckBox mCityCyclicCk;

    CheckBox mAreaCyclicCk;

    CheckBox mHalfBgCk;
    TextView mResetSettingTv;

    TextView mOneTv;

    TextView mTwoTv;

    TextView mThreeTv;

    LinearLayout pro_cyclic_ll;
    LinearLayout city_cyclic_ll;
    LinearLayout area_cyclic_ll;

    private int visibleItems = 5;

    private boolean isProvinceCyclic = true;

    private boolean isCityCyclic = true;

    private boolean isDistrictCyclic = true;

    private boolean isShowBg = true;
    private CustomCityPicker customCityPicker = null;
    /**
     * 自定义数据源-省份数据
     */
    private List<CustomCityData> mProvinceListData = new ArrayList<>();
    public CustomConfig.WheelType mWheelType = CustomConfig.WheelType.PRO_CITY_DIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker_custome_data);
        initCustomeCityData();

        customCityPicker = new CustomCityPicker(this);
        pro_cyclic_ll = (LinearLayout) findViewById(R.id.pro_cyclic_ll);
        city_cyclic_ll = (LinearLayout) findViewById(R.id.city_cyclic_ll);
        area_cyclic_ll = (LinearLayout) findViewById(R.id.area_cyclic_ll);
        customeBtn = (TextView) findViewById(R.id.customBtn);
        resultTv = (TextView) findViewById(R.id.resultTv);

        mProVisibleCountEt = (EditText) findViewById(R.id.pro_visible_count_et);
        mProCyclicCk = (CheckBox) findViewById(R.id.pro_cyclic_ck);
        mCityCyclicCk = (CheckBox) findViewById(R.id.city_cyclic_ck);
        mAreaCyclicCk = (CheckBox) findViewById(R.id.area_cyclic_ck);
        mHalfBgCk = (CheckBox) findViewById(R.id.half_bg_ck);
        mResetSettingTv = (TextView) findViewById(R.id.reset_setting_tv);
        mOneTv = (TextView) findViewById(R.id.one_tv);
        mTwoTv = (TextView) findViewById(R.id.two_tv);
        mThreeTv = (TextView) findViewById(R.id.three_tv);


        setWheelType(mWheelType);
        //提交
        customeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView();
            }

        });

        //重置属性
        mResetSettingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });


        //一级联动，只显示省份，不显示市和区
        mOneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CustomConfig.WheelType.PRO;
                setWheelType(mWheelType);
            }
        });

        //二级联动，只显示省份， 市，不显示区
        mTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CustomConfig.WheelType.PRO_CITY;
                setWheelType(mWheelType);
            }
        });

        //三级联动，显示省份， 市和区
        mThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelType = CustomConfig.WheelType.PRO_CITY_DIS;
                setWheelType(mWheelType);
            }
        });

        //省份是否循环显示
        mProCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isProvinceCyclic = isChecked;
            }
        });

        //市是否循环显示
        mCityCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCityCyclic = isChecked;
            }
        });

        //区是否循环显示
        mAreaCyclicCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDistrictCyclic = isChecked;
            }
        });

        //半透明背景显示
        mHalfBgCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isShowBg = isChecked;
            }
        });



    }

    /**
     * 重置属性
     */
    private void reset() {
        visibleItems = 5;
        isProvinceCyclic = true;
        isCityCyclic = true;
        isDistrictCyclic = true;
        isShowBg = true;
        mWheelType = CustomConfig.WheelType.PRO_CITY_DIS;

        setWheelType(mWheelType);

        mProCyclicCk.setChecked(true);
        mCityCyclicCk.setChecked(true);
        mAreaCyclicCk.setChecked(true);
        mProVisibleCountEt.setText("" + visibleItems);

        mHalfBgCk.setChecked(isShowBg);
        mProCyclicCk.setChecked(isProvinceCyclic);
        mAreaCyclicCk.setChecked(isDistrictCyclic);
        mCityCyclicCk.setChecked(isCityCyclic);

        setWheelType(mWheelType);

    }



    /**
     * @param wheelType
     */
    private void setWheelType(CustomConfig.WheelType wheelType) {
        if (wheelType == CustomConfig.WheelType.PRO) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#ffffff"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));


            pro_cyclic_ll.setVisibility(View.VISIBLE);
            city_cyclic_ll.setVisibility(View.GONE);
            area_cyclic_ll.setVisibility(View.GONE);

        } else if (wheelType == CustomConfig.WheelType.PRO_CITY) {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#ffffff"));
            mThreeTv.setTextColor(Color.parseColor("#333333"));

            pro_cyclic_ll.setVisibility(View.VISIBLE);
            city_cyclic_ll.setVisibility(View.VISIBLE);
            area_cyclic_ll.setVisibility(View.GONE);

        } else {
            mOneTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mTwoTv.setBackgroundResource(R.drawable.city_wheeltype_normal);
            mThreeTv.setBackgroundResource(R.drawable.city_wheeltype_selected);
            mOneTv.setTextColor(Color.parseColor("#333333"));
            mTwoTv.setTextColor(Color.parseColor("#333333"));
            mThreeTv.setTextColor(Color.parseColor("#ffffff"));

            pro_cyclic_ll.setVisibility(View.VISIBLE);
            city_cyclic_ll.setVisibility(View.VISIBLE);
            area_cyclic_ll.setVisibility(View.VISIBLE);
        }
    }


    private void showView() {

        CustomConfig cityConfig = new CustomConfig.Builder()
                .title("选择城市")
                .visibleItemsCount(visibleItems)
                .setCityData(mProvinceListData)
                .provinceCyclic(isProvinceCyclic)
                .province("浙江省")
                .city("宁波市")
                .district("鄞州区")
                .cityCyclic(isCityCyclic)
                .setCustomItemLayout(R.layout.item_custome_city)//自定义item的布局
                .setCustomItemTextViewId(R.id.item_custome_city_name_tv)
                .districtCyclic(isDistrictCyclic)
                .drawShadows(isShowBg)
                .setCityWheelType(mWheelType)
                .build();

        customCityPicker.setCustomConfig(cityConfig);
        customCityPicker.setOnCustomCityPickerItemClickListener(new OnCustomCityPickerItemClickListener() {
            @Override
            public void onSelected(CustomCityData province, CustomCityData city, CustomCityData district) {
                if (province != null && city != null && district != null) {
                    resultTv.setText("province：" + province.getName() + "    " + province.getId() + "\n" +
                            "city：" + city.getName() + "    " + city.getId() + "\n" +
                            "area：" + district.getName() + "    " + district.getId() + "\n");
                }else{
                    resultTv.setText("结果出错！");
                }
            }
        });
        customCityPicker.showCityPicker();

    }

    private void initCustomeCityData() {

        CustomCityData jsPro = new CustomCityData("10000", "江苏省");

        CustomCityData ycCity = new CustomCityData("11000", "盐城市");
        List<CustomCityData> ycDistList = new ArrayList<>();
        ycDistList.add(new CustomCityData("11100", "滨海县"));
        ycDistList.add(new CustomCityData("11200", "阜宁县"));
        ycDistList.add(new CustomCityData("11300", "大丰市"));
        ycDistList.add(new CustomCityData("11400", "盐都区"));
        ycCity.setList(ycDistList);

        CustomCityData czCity = new CustomCityData("12000", "常州市");
        List<CustomCityData> czDistList = new ArrayList<>();
        czDistList.add(new CustomCityData("12100", "新北区"));
        czDistList.add(new CustomCityData("12200", "天宁区"));
        czDistList.add(new CustomCityData("12300", "钟楼区"));
        czDistList.add(new CustomCityData("12400", "武进区"));
        czCity.setList(czDistList);

        List<CustomCityData> jsCityList = new ArrayList<>();
        jsCityList.add(ycCity);
        jsCityList.add(czCity);

        jsPro.setList(jsCityList);


        CustomCityData zjPro = new CustomCityData("20000", "浙江省");

        CustomCityData nbCity = new CustomCityData("21000", "宁波市");
        List<CustomCityData> nbDistList = new ArrayList<>();
        nbDistList.add(new CustomCityData("21100", "海曙区"));
        nbDistList.add(new CustomCityData("21200", "鄞州区"));
        nbCity.setList(nbDistList);

        CustomCityData hzCity = new CustomCityData("22000", "杭州市");
        List<CustomCityData> hzDistList = new ArrayList<>();
        hzDistList.add(new CustomCityData("22100", "上城区"));
        hzDistList.add(new CustomCityData("22200", "西湖区"));
        hzDistList.add(new CustomCityData("22300", "下沙区"));
        hzCity.setList(hzDistList);

        List<CustomCityData> zjCityList = new ArrayList<>();
        zjCityList.add(hzCity);
        zjCityList.add(nbCity);

        zjPro.setList(zjCityList);


        CustomCityData gdPro = new CustomCityData("30000", "广东省");

        CustomCityData fjCity = new CustomCityData("21000", "潮州市");
        List<CustomCityData> fjDistList = new ArrayList<>();
        fjDistList.add(new CustomCityData("21100", "湘桥区"));
        fjDistList.add(new CustomCityData("21200", "潮安区"));
        fjCity.setList(fjDistList);



        CustomCityData gzCity = new CustomCityData("22000", "广州市");
        List<CustomCityData> szDistList = new ArrayList<>();
        szDistList.add(new CustomCityData("22100", "荔湾区"));
        szDistList.add(new CustomCityData("22200", "增城区"));
        szDistList.add(new CustomCityData("22300", "从化区"));
        szDistList.add(new CustomCityData("22400", "南沙区"));
        szDistList.add(new CustomCityData("22500", "花都区"));
        szDistList.add(new CustomCityData("22600", "番禺区"));
        szDistList.add(new CustomCityData("22700", "黄埔区"));
        szDistList.add(new CustomCityData("22800", "白云区"));
        szDistList.add(new CustomCityData("22900", "天河区"));
        szDistList.add(new CustomCityData("22110", "海珠区"));
        szDistList.add(new CustomCityData("22120", "越秀区"));
        gzCity.setList(szDistList);

        List<CustomCityData> gdCityList = new ArrayList<>();
        gdCityList.add(gzCity);
        gdCityList.add(fjCity);

        gdPro.setList(gdCityList);


        mProvinceListData.add(jsPro);
        mProvinceListData.add(zjPro);
        mProvinceListData.add(gdPro);

    }
}
