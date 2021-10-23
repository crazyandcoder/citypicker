package com.lljjcoder.style.citypickerview;

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
import android.widget.Toast;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityParseHelper;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.widget.CanShow;
import com.lljjcoder.style.citypickerview.widget.wheel.OnWheelChangedListener;
import com.lljjcoder.style.citypickerview.widget.wheel.WheelView;
import com.lljjcoder.style.citypickerview.widget.wheel.adapters.ArrayWheelAdapter;
import com.lljjcoder.utils.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 省市区三级选择
 * 作者：liji on 2015/12/17 10:40
 * 邮箱：lijiwork@sina.com
 */
public class CityPickerView implements CanShow, OnWheelChangedListener {

    private String TAG = "citypicker_log";

    private PopupWindow popwindow;

    private View popview;

    private WheelView mViewProvince;

    private WheelView mViewCity;

    private WheelView mViewDistrict;

    private RelativeLayout mRelativeTitleBg;

    private TextView mTvOK;

    private TextView mTvTitle;

    private TextView mTvCancel;

    private OnCityItemClickListener mBaseListener;

    private CityParseHelper parseHelper;

    private CityConfig config;

    private Context context;

    private List<ProvinceBean> proArra;

    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        mBaseListener = listener;
    }

    public CityPickerView() {

    }

    /**
     * 设置属性
     *
     * @param config
     */
    public void setConfig(final CityConfig config) {
        this.config = config;
    }

    /**
     * 初始化，默认解析城市数据，提交加载速度
     */
    public void init(Context context) {
        this.context = context;
        parseHelper = new CityParseHelper();

        //解析初始数据
        if (parseHelper.getProvinceBeanArrayList().isEmpty()) {
            parseHelper.initData(context);
        }

    }

    /**
     * 初始化popWindow
     */
    private void initCityPickerPopwindow() {

        if (config == null) {
            throw new IllegalArgumentException("please set config first...");
        }

        //解析初始数据
        if (parseHelper == null) {
            parseHelper = new CityParseHelper();
        }

        if (parseHelper.getProvinceBeanArrayList().isEmpty()) {
            ToastUtils.showLongToast(context, "请在Activity中增加init操作");
            return;
        }

        LayoutInflater layoutInflater = LayoutInflater.from(context);
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
                    utils.setBackgroundAlpha(context, 1.0f);
                }
            }
        });
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

        //只显示省市两级联动
        if (config.getWheelType() == CityConfig.WheelType.PRO) {
            mViewCity.setVisibility(View.GONE);
            mViewDistrict.setVisibility(View.GONE);
        } else if (config.getWheelType() == CityConfig.WheelType.PRO_CITY) {
            mViewDistrict.setVisibility(View.GONE);
        } else {
            mViewProvince.setVisibility(View.VISIBLE);
            mViewCity.setVisibility(View.VISIBLE);
            mViewDistrict.setVisibility(View.VISIBLE);
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
                mBaseListener.onCancel();
                hide();
            }
        });

        //确认选择
        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parseHelper != null) {
                    if (config.getWheelType() == CityConfig.WheelType.PRO) {
                        mBaseListener.onSelected(parseHelper.getProvinceBean(), new CityBean(), new DistrictBean());
                    } else if (config.getWheelType() == CityConfig.WheelType.PRO_CITY) {
                        mBaseListener.onSelected(parseHelper.getProvinceBean(),
                                parseHelper.getCityBean(),
                                new DistrictBean());
                    } else {
                        mBaseListener.onSelected(parseHelper.getProvinceBean(),
                                parseHelper.getCityBean(),
                                parseHelper.getDistrictBean());
                    }

                } else {
                    mBaseListener.onSelected(new ProvinceBean(), new CityBean(), new DistrictBean());
                }
                hide();
            }
        });

        //显示省市区数据
        setUpData();

        //背景半透明
        if (config != null && config.isShowBackground()) {
            utils.setBackgroundAlpha(context, 0.5f);
        }

    }

    /**
     * 根据是否显示港澳台数据来初始化最新的数据
     *
     * @param array
     * @return
     */
    private List<ProvinceBean> getProArrData(List<ProvinceBean> array) {

        List<ProvinceBean> provinceBeanList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            provinceBeanList.add(array.get(i));
        }

        //不需要港澳台数据
        if (!config.isShowGAT()) {
            provinceBeanList.remove(provinceBeanList.size() - 1);
            provinceBeanList.remove(provinceBeanList.size() - 1);
            provinceBeanList.remove(provinceBeanList.size() - 1);
        }

        proArra = new ArrayList<ProvinceBean>();
        for (int i = 0; i < provinceBeanList.size(); i++) {
            proArra.add(provinceBeanList.get(i));
        }

        return proArra;
    }

    /**
     * 加载数据
     */
    private void setUpData() {

        if (parseHelper == null || config == null) {
            return;
        }

        //根据是否显示港澳台数据来初始化最新的数据
        getProArrData(parseHelper.getProvinceBeenArray());

        int provinceDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultProvinceName()) && proArra.size() > 0) {
            for (int i = 0; i < proArra.size(); i++) {
                if (proArra.get(i).getName().equals(config.getDefaultProvinceName())) {
                    provinceDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<ProvinceBean>(context, proArra);
        mViewProvince.setViewAdapter(arrayWheelAdapter);

        //自定义item
        if (config.getCustomItemLayout() != CityConfig.NONE && config.getCustomItemTextViewId() != CityConfig.NONE) {
            arrayWheelAdapter.setItemResource(config.getCustomItemLayout());
            arrayWheelAdapter.setItemTextResource(config.getCustomItemTextViewId());
        } else {
            arrayWheelAdapter.setItemResource(R.layout.default_item_city);
            arrayWheelAdapter.setItemTextResource(R.id.default_item_city_name_tv);
        }

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

        updateCities();

        updateAreas();
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {

        if (parseHelper == null || config == null) {
            return;
        }

        //省份滚轮滑动的当前位置
        int pCurrent = mViewProvince.getCurrentItem();

        //省份选中的名称
        ProvinceBean mProvinceBean = proArra.get(pCurrent);
        parseHelper.setProvinceBean(mProvinceBean);

        if (parseHelper.getPro_CityMap() == null) {
            return;
        }

        List<CityBean> cities = parseHelper.getPro_CityMap().get(mProvinceBean.getName());
        if (cities == null) {
            return;
        }

        //设置最初的默认城市
        int cityDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultCityName()) && cities.size() > 0) {
            for (int i = 0; i < cities.size(); i++) {
                if (config.getDefaultCityName().equals(cities.get(i).getName())) {
                    cityDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter cityWheel = new ArrayWheelAdapter<CityBean>(context, cities);

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
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {

        int pCurrent = mViewCity.getCurrentItem();
        if (parseHelper.getPro_CityMap() == null || parseHelper.getCity_DisMap() == null) {
            return;
        }

        if (config.getWheelType() == CityConfig.WheelType.PRO_CITY
                || config.getWheelType() == CityConfig.WheelType.PRO_CITY_DIS) {

            CityBean mCityBean = parseHelper.getPro_CityMap().get(parseHelper.getProvinceBean().getName()).get(pCurrent);
            parseHelper.setCityBean(mCityBean);

            if (config.getWheelType() == CityConfig.WheelType.PRO_CITY_DIS) {

                List<DistrictBean> areas = parseHelper.getCity_DisMap()
                        .get(parseHelper.getProvinceBean().getName() + mCityBean.getName());

                if (areas == null) {
                    return;
                }

                int districtDefault = -1;
                if (!TextUtils.isEmpty(config.getDefaultDistrict()) && areas.size() > 0) {
                    for (int i = 0; i < areas.size(); i++) {
                        if (config.getDefaultDistrict().equals(areas.get(i).getName())) {
                            districtDefault = i;
                            break;
                        }
                    }
                }

                ArrayWheelAdapter districtWheel = new ArrayWheelAdapter<DistrictBean>(context, areas);

                //自定义item
                if (config.getCustomItemLayout() != CityConfig.NONE
                        && config.getCustomItemTextViewId() != CityConfig.NONE) {
                    districtWheel.setItemResource(config.getCustomItemLayout());
                    districtWheel.setItemTextResource(config.getCustomItemTextViewId());
                } else {
                    districtWheel.setItemResource(R.layout.default_item_city);
                    districtWheel.setItemTextResource(R.id.default_item_city_name_tv);
                }

                mViewDistrict.setViewAdapter(districtWheel);

                DistrictBean mDistrictBean = null;
                if (parseHelper.getDisMap() == null) {
                    return;
                }

                if (-1 != districtDefault) {
                    mViewDistrict.setCurrentItem(districtDefault);
                    //获取第一个区名称
                    mDistrictBean = parseHelper.getDisMap().get(parseHelper.getProvinceBean().getName()
                            + mCityBean.getName() + config.getDefaultDistrict());
                } else {
                    mViewDistrict.setCurrentItem(0);
                    if (areas.size() > 0) {
                        mDistrictBean = areas.get(0);
                    }
                }

                parseHelper.setDistrictBean(mDistrictBean);

            }
        }
    }

    public void showCityPicker() {
        initCityPickerPopwindow();
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
            if (parseHelper != null && parseHelper.getCity_DisMap() != null) {

                DistrictBean mDistrictBean = parseHelper.getCity_DisMap()
                        .get(parseHelper.getProvinceBean().getName() + parseHelper.getCityBean().getName()).get(newValue);

                parseHelper.setDistrictBean(mDistrictBean);
            }
        }
    }

}
