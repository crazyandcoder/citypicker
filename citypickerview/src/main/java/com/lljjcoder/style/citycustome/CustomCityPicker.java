package com.lljjcoder.style.citycustome;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCustomCityPickerItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.CustomCityData;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CustomConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.style.citypickerview.widget.CanShow;
import com.lljjcoder.style.citypickerview.widget.wheel.OnWheelChangedListener;
import com.lljjcoder.style.citypickerview.widget.wheel.WheelView;
import com.lljjcoder.style.citypickerview.widget.wheel.adapters.ArrayWheelAdapter;
import com.lljjcoder.utils.utils;

import java.util.List;

public class CustomCityPicker implements CanShow, OnWheelChangedListener {


    private PopupWindow popwindow;

    private View popview;

    private WheelView mViewProvince;

    private WheelView mViewCity;

    private WheelView mViewDistrict;

    private Context mContext;

    private RelativeLayout mRelativeTitleBg;

    private TextView mTvOK;

    private TextView mTvTitle;

    private TextView mTvCancel;

    private CustomConfig config;

    private OnCustomCityPickerItemClickListener listener = null;

    private CustomConfig.WheelType type = CustomConfig.WheelType.PRO_CITY_DIS;

    public void setOnCustomCityPickerItemClickListener(OnCustomCityPickerItemClickListener listener) {
        this.listener = listener;
    }

    public CustomCityPicker(Context context) {
        this.mContext = context;
    }

    public void setCustomConfig(CustomConfig config) {
        this.config = config;
    }

    private void initView() {

        if (config == null) {
            ToastUtils.showLongToast(mContext, "请设置相关的config");
            return;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        popview = layoutInflater.inflate(R.layout.pop_citypicker, null);

        mViewProvince = (WheelView) popview.findViewById(R.id.id_province);
        mViewCity = (WheelView) popview.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) popview.findViewById(R.id.id_district);

        mRelativeTitleBg = (RelativeLayout) popview.findViewById(R.id.rl_title);
        mTvOK = (TextView) popview.findViewById(R.id.tv_confirm);
        mTvTitle = (TextView) popview.findViewById(R.id.tv_title);
        mTvCancel = (TextView) popview.findViewById(R.id.tv_cancel);

        popwindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popwindow.setAnimationStyle(R.style.AnimBottom);
        popwindow.setBackgroundDrawable(new ColorDrawable());
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(false);
        popwindow.setFocusable(true);

        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (config.isShowBackground()) {
                    utils.setBackgroundAlpha(mContext, 1.0f);
                }
            }
        });

        //设置显示层级
        type = config.getWheelType();
        setWheelShowLevel(type);


        /**
         * 设置标题背景颜色
         */
        if (!TextUtils.isEmpty(config.getTitleBackgroundColorStr())) {
            if (config.getTitleBackgroundColorStr().startsWith("#")) {
                mRelativeTitleBg.setBackgroundColor(Color.parseColor(config.getTitleBackgroundColorStr()));
            } else {
                mRelativeTitleBg.setBackgroundColor(Color.parseColor("#" + config.getTitleBackgroundColorStr()));

            }
        }

        //标题
        if (!TextUtils.isEmpty(config.getTitle())) {
            mTvTitle.setText(config.getTitle());
        }

        //标题文字大小
        if (config.getTitleTextSize() > 0) {
            mTvTitle.setTextSize(config.getTitleTextSize());
        }

        //标题文字颜色
        if (!TextUtils.isEmpty(config.getTitleTextColorStr())) {
            if (config.getTitleTextColorStr().startsWith("#")) {
                mTvTitle.setTextColor(Color.parseColor(config.getTitleTextColorStr()));
            } else {
                mTvTitle.setTextColor(Color.parseColor("#" + config.getTitleTextColorStr()));
            }
        }

        //设置确认按钮文字大小颜色
        if (!TextUtils.isEmpty(config.getConfirmTextColorStr())) {
            if (config.getConfirmTextColorStr().startsWith("#")) {
                mTvOK.setTextColor(Color.parseColor(config.getConfirmTextColorStr()));
            } else {
                mTvOK.setTextColor(Color.parseColor("#" + config.getConfirmTextColorStr()));
            }
        }

        if (!TextUtils.isEmpty(config.getConfirmText())) {
            mTvOK.setText(config.getConfirmText());
        }

        if ((config.getConfirmTextSize() > 0)) {
            mTvOK.setTextSize(config.getConfirmTextSize());
        }

        //设置取消按钮文字颜色
        if (!TextUtils.isEmpty(config.getCancelTextColorStr())) {
            if (config.getCancelTextColorStr().startsWith("#")) {
                mTvCancel.setTextColor(Color.parseColor(config.getCancelTextColorStr()));
            } else {
                mTvCancel.setTextColor(Color.parseColor("#" + config.getCancelTextColorStr()));
            }
        }

        if (!TextUtils.isEmpty(config.getCancelText())) {
            mTvCancel.setText(config.getCancelText());
        }

        if ((config.getCancelTextSize() > 0)) {
            mTvCancel.setTextSize(config.getCancelTextSize());
        }


        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel();
                hide();
            }
        });


        //确认选择
        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type == CustomConfig.WheelType.PRO) {
                    int pCurrent = mViewProvince.getCurrentItem();
                    List<CustomCityData> provinceList = config.getCityDataList();
                    CustomCityData province = provinceList.get(pCurrent);
                    listener.onSelected(province, new CustomCityData(), new CustomCityData());
                } else if (type == CustomConfig.WheelType.PRO_CITY) {
                    int pCurrent = mViewProvince.getCurrentItem();
                    List<CustomCityData> provinceList = config.getCityDataList();
                    CustomCityData province = provinceList.get(pCurrent);
                    int cCurrent = mViewCity.getCurrentItem();
                    List<CustomCityData> cityDataList = province.getList();
                    if (cityDataList == null) return;
                    CustomCityData city = cityDataList.get(cCurrent);
                    listener.onSelected(province, city, new CustomCityData());
                } else if (type == CustomConfig.WheelType.PRO_CITY_DIS) {
                    int pCurrent = mViewProvince.getCurrentItem();
                    List<CustomCityData> provinceList = config.getCityDataList();
                    CustomCityData province = provinceList.get(pCurrent);
                    int cCurrent = mViewCity.getCurrentItem();
                    List<CustomCityData> cityDataList = province.getList();
                    if (cityDataList == null) return;
                    CustomCityData city = cityDataList.get(cCurrent);
                    int dCurrent = mViewDistrict.getCurrentItem();
                    List<CustomCityData> areaList = city.getList();
                    if (areaList == null) return;
                    CustomCityData area = areaList.get(dCurrent);
                    listener.onSelected(province, city, area);
                }

                hide();
            }
        });


        //背景半透明
        if (config != null && config.isShowBackground()) {
            utils.setBackgroundAlpha(mContext, 0.5f);
        }


        //显示省市区数据
        setUpData();


    }

    /**
     * 显示省市区等级
     *
     * @param type
     */
    private void setWheelShowLevel(CustomConfig.WheelType type) {
        if (type == CustomConfig.WheelType.PRO) {
            mViewProvince.setVisibility(View.VISIBLE);
            mViewCity.setVisibility(View.GONE);
            mViewDistrict.setVisibility(View.GONE);
        } else if (type == CustomConfig.WheelType.PRO_CITY) {
            mViewProvince.setVisibility(View.VISIBLE);
            mViewCity.setVisibility(View.VISIBLE);
            mViewDistrict.setVisibility(View.GONE);
        } else {
            mViewProvince.setVisibility(View.VISIBLE);
            mViewCity.setVisibility(View.VISIBLE);
            mViewDistrict.setVisibility(View.VISIBLE);
        }

    }

    private void setUpData() {

        List<CustomCityData> proArra = config.getCityDataList();
        if (proArra == null) return;

        int provinceDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultProvinceName()) && proArra.size() > 0) {
            for (int i = 0; i < proArra.size(); i++) {
                if (proArra.get(i).getName().startsWith(config.getDefaultProvinceName())) {
                    provinceDefault = i;
                    break;
                }
            }
        }


        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<CustomCityData>(mContext, proArra);
        //自定义item
        if (config.getCustomItemLayout() != CityConfig.NONE && config.getCustomItemTextViewId() != CityConfig.NONE) {
            arrayWheelAdapter.setItemResource(config.getCustomItemLayout());
            arrayWheelAdapter.setItemTextResource(config.getCustomItemTextViewId());
        } else {
            arrayWheelAdapter.setItemResource(R.layout.default_item_city);
            arrayWheelAdapter.setItemTextResource(R.id.default_item_city_name_tv);
        }
        mViewProvince.setViewAdapter(arrayWheelAdapter);

        //获取所设置的省的位置，直接定位到该位置
        if (-1 != provinceDefault) {
            mViewProvince.setCurrentItem(provinceDefault);
        }


        // 设置可见条目数量
        mViewProvince.setVisibleItems(config.getVisibleItems());
        mViewCity.setVisibleItems(config.getVisibleItems());
        mViewDistrict.setVisibleItems(config.getVisibleItems());
        mViewProvince.setCyclic(config.isProvinceCyclic());
        mViewCity.setCyclic(config.isCityCyclic());
        mViewDistrict.setCyclic(config.isDistrictCyclic());

        //显示滚轮模糊效果
        mViewProvince.setDrawShadows(config.isDrawShadows());
        mViewCity.setDrawShadows(config.isDrawShadows());
        mViewDistrict.setDrawShadows(config.isDrawShadows());

        //中间线的颜色及高度
        mViewProvince.setLineColorStr(config.getLineColor());
        mViewProvince.setLineWidth(config.getLineHeigh());
        mViewCity.setLineColorStr(config.getLineColor());
        mViewCity.setLineWidth(config.getLineHeigh());
        mViewDistrict.setLineColorStr(config.getLineColor());
        mViewDistrict.setLineWidth(config.getLineHeigh());


        if (type == CustomConfig.WheelType.PRO_CITY || type == CustomConfig.WheelType.PRO_CITY_DIS) {
            updateCities();
        }

    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        //省份滚轮滑动的当前位置
        int pCurrent = mViewProvince.getCurrentItem();

        //省份选中的名称
        List<CustomCityData> proArra = config.getCityDataList();
        CustomCityData mProvinceBean = proArra.get(pCurrent);

        List<CustomCityData> pCityList = mProvinceBean.getList();
        if (pCityList == null) return;


        //设置最初的默认城市
        int cityDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultCityName()) && pCityList.size() > 0) {
            for (int i = 0; i < pCityList.size(); i++) {
                if (pCityList.get(i).getName().startsWith(config.getDefaultCityName())) {
                    cityDefault = i;
                    break;
                }
            }
        }


        ArrayWheelAdapter cityWheel = new ArrayWheelAdapter<CustomCityData>(mContext, pCityList);
        //自定义item
        if (config.getCustomItemLayout() != CityConfig.NONE && config.getCustomItemTextViewId() != CityConfig.NONE) {
            cityWheel.setItemResource(config.getCustomItemLayout());
            cityWheel.setItemTextResource(config.getCustomItemTextViewId());
        } else {
            cityWheel.setItemResource(R.layout.default_item_city);
            cityWheel.setItemTextResource(R.id.default_item_city_name_tv);
        }

        mViewCity.setViewAdapter(cityWheel);
        if (-1 != cityDefault) {
            mViewCity.setCurrentItem(cityDefault);
        } else {
            mViewCity.setCurrentItem(0);
        }


        mViewCity.setViewAdapter(cityWheel);

        if (type == CustomConfig.WheelType.PRO_CITY_DIS) {
            updateAreas();
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {

        int pCurrent = mViewProvince.getCurrentItem();
        int cCurrent = mViewCity.getCurrentItem();

        List<CustomCityData> provinceList = config.getCityDataList();
        CustomCityData province = provinceList.get(pCurrent);
        List<CustomCityData> cityDataList = province.getList();
        if (cityDataList == null || cityDataList.size() <= cCurrent) return;
        CustomCityData city = cityDataList.get(cCurrent);
        List<CustomCityData> areaList = city.getList();
        if (areaList == null) return;


        int districtDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultDistrict()) && areaList.size() > 0) {
            for (int i = 0; i < areaList.size(); i++) {
                if (areaList.get(i).getName().startsWith(config.getDefaultDistrict())) {
                    districtDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter districtWheel = new ArrayWheelAdapter<CustomCityData>(mContext, areaList);
        //自定义item
        if (config.getCustomItemLayout() != CityConfig.NONE
                && config.getCustomItemTextViewId() != CityConfig.NONE) {
            districtWheel.setItemResource(config.getCustomItemLayout());
            districtWheel.setItemTextResource(config.getCustomItemTextViewId());
        } else {
            districtWheel.setItemResource(R.layout.default_item_city);
            districtWheel.setItemTextResource(R.id.default_item_city_name_tv);
        }

        //设置默认城市
        if (-1 != districtDefault) {
            mViewDistrict.setCurrentItem(districtDefault);
        } else {
            mViewDistrict.setCurrentItem(0);
        }


        mViewDistrict.setViewAdapter(districtWheel);


    }


    public void showCityPicker() {
        initView();
        if (!isShow()) {
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void hide() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }

    @Override
    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {


        }
    }
}
