package com.lljjcoder.style.cityjd;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityParseHelper;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.R;
import com.lljjcoder.style.citypickerview.widget.CanShow;
import com.lljjcoder.utils.utils;

import java.util.List;

import static com.lljjcoder.style.cityjd.JDConst.INDEX_INVALID;
import static com.lljjcoder.style.cityjd.JDConst.INDEX_TAB_AREA;
import static com.lljjcoder.style.cityjd.JDConst.INDEX_TAB_CITY;
import static com.lljjcoder.style.cityjd.JDConst.INDEX_TAB_PROVINCE;

/**
 * 仿京东城市选择器
 * 作者：liji on 2018/1/26 16:08
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class JDCityPicker {

    private ListView mCityListView;

    private TextView mProTv;

    private TextView mCityTv;

    private TextView mAreaTv;
    private ImageView mCloseImg;

    private PopupWindow popwindow;
    private View mSelectedLine;
    private View popview;

    private CityParseHelper parseHelper;
    private ProvinceAdapter mProvinceAdapter;
    private CityAdapter mCityAdapter;
    private AreaAdapter mAreaAdapter;

    private List<ProvinceBean> provinceList = null;
    private List<CityBean> cityList = null;
    private List<DistrictBean> areaList = null;

    private int tabIndex = INDEX_TAB_PROVINCE;
    private Context context;
    private String colorSelected = "#ff181c20";
    private String colorAlert = "#ffff4444";

    private OnCityItemClickListener mBaseListener;

    private JDCityConfig cityConfig = null;

    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        mBaseListener = listener;
    }

    public void setConfig(JDCityConfig cityConfig) {
        this.cityConfig = cityConfig;
    }


    private void initJDCityPickerPop() {

        if (this.cityConfig == null) {
            this.cityConfig = new JDCityConfig.Builder().setJDCityShowType(JDCityConfig.ShowType.PRO_CITY_DIS).build();
        }

        tabIndex = INDEX_TAB_PROVINCE;
        //解析初始数据
        if (parseHelper == null) {
            parseHelper = new CityParseHelper();
        }

        if (parseHelper.getProvinceBeanArrayList().isEmpty()) {
            ToastUtils.showLongToast(context, "请调用init方法进行初始化相关操作");
            return;
        }


        LayoutInflater layoutInflater = LayoutInflater.from(context);
        popview = layoutInflater.inflate(R.layout.pop_jdcitypicker, null);

        mCityListView = (ListView) popview.findViewById(R.id.city_listview);
        mProTv = (TextView) popview.findViewById(R.id.province_tv);
        mCityTv = (TextView) popview.findViewById(R.id.city_tv);
        mAreaTv = (TextView) popview.findViewById(R.id.area_tv);
        mCloseImg = (ImageView) popview.findViewById(R.id.close_img);
        mSelectedLine = (View) popview.findViewById(R.id.selected_line);

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
                utils.setBackgroundAlpha(context, 1.0f);
            }
        });


        mCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePop();
                utils.setBackgroundAlpha(context, 1.0f);
                if (mBaseListener != null) {
                    mBaseListener.onCancel();
                }
            }
        });

        mProTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabIndex = INDEX_TAB_PROVINCE;
                if (mProvinceAdapter != null) {
                    mCityListView.setAdapter(mProvinceAdapter);
                    if (mProvinceAdapter.getSelectedPosition() != INDEX_INVALID) {
                        mCityListView.setSelection(mProvinceAdapter.getSelectedPosition());
                    }
                }
                updateTabVisible();
                updateIndicator();
            }
        });
        mCityTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabIndex = INDEX_TAB_CITY;
                if (mCityAdapter != null) {
                    mCityListView.setAdapter(mCityAdapter);
                    if (mCityAdapter.getSelectedPosition() != INDEX_INVALID) {
                        mCityListView.setSelection(mCityAdapter.getSelectedPosition());
                    }
                }
                updateTabVisible();
                updateIndicator();
            }
        });
        mAreaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabIndex = INDEX_TAB_AREA;
                if (mAreaAdapter != null) {
                    mCityListView.setAdapter(mAreaAdapter);
                    if (mAreaAdapter.getSelectedPosition() != INDEX_INVALID) {
                        mCityListView.setSelection(mAreaAdapter.getSelectedPosition());
                    }
                }
                updateTabVisible();
                updateIndicator();
            }
        });
        mCityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedList(position);
            }
        });

        utils.setBackgroundAlpha(context, 0.5f);
        updateIndicator();
        updateTabsStyle(INDEX_INVALID);
        setProvinceListData();

    }

    private void selectedList(int position) {
        switch (tabIndex) {
            case INDEX_TAB_PROVINCE:
                ProvinceBean provinceBean = mProvinceAdapter.getItem(position);
                if (provinceBean != null) {
                    mProTv.setText("" + provinceBean.getName());
                    mCityTv.setText("请选择");
                    mProvinceAdapter.updateSelectedPosition(position);
                    mProvinceAdapter.notifyDataSetChanged();
                    mCityAdapter = new CityAdapter(context, provinceBean.getCityList());
                    //选中省份数据后更新市数据
                    mHandler.sendMessage(Message.obtain(mHandler, INDEX_TAB_CITY, provinceBean.getCityList()));

                }

                break;


            case INDEX_TAB_CITY:
                CityBean cityBean = mCityAdapter.getItem(position);
                if (cityBean != null) {
                    mCityTv.setText("" + cityBean.getName());
                    mAreaTv.setText("请选择");
                    mCityAdapter.updateSelectedPosition(position);
                    mCityAdapter.notifyDataSetChanged();
                    if (this.cityConfig != null && this.cityConfig.getShowType() == JDCityConfig.ShowType.PRO_CITY) {
                        callback(new DistrictBean());
                    } else {
                        mAreaAdapter = new AreaAdapter(context, cityBean.getCityList());
                        //选中省份数据后更新市数据
                        mHandler.sendMessage(Message.obtain(mHandler, INDEX_TAB_AREA, cityBean.getCityList()));
                    }
                }
                break;

            case INDEX_TAB_AREA:
                //返回选中的省市区数据
                DistrictBean districtBean = mAreaAdapter.getItem(position);
                if (districtBean != null) {
                    callback(districtBean);
                }
                break;
        }
    }

    /**
     * 设置默认的省份数据
     */
    private void setProvinceListData() {
        provinceList = parseHelper.getProvinceBeanArrayList();
        if (provinceList != null && !provinceList.isEmpty()) {
            mProvinceAdapter = new ProvinceAdapter(context, provinceList);
            mCityListView.setAdapter(mProvinceAdapter);
        } else {
            ToastUtils.showLongToast(context, "解析本地城市数据失败！");
            return;
        }

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
     * 更新选中城市下面的红色横线指示器
     */
    private void updateIndicator() {
        popview.post(new Runnable() {
            @Override
            public void run() {
                switch (tabIndex) {
                    case INDEX_TAB_PROVINCE:
                        tabSelectedIndicatorAnimation(mProTv).start();
                        break;
                    case INDEX_TAB_CITY:
                        tabSelectedIndicatorAnimation(mCityTv).start();
                        break;
                    case INDEX_TAB_AREA:
                        tabSelectedIndicatorAnimation(mAreaTv).start();
                        break;
                }
            }
        });

    }

    /**
     * tab 选中的红色下划线动画
     *
     * @param tab
     * @return
     */
    private AnimatorSet tabSelectedIndicatorAnimation(TextView tab) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mSelectedLine, "X", mSelectedLine.getX(), tab.getX());

        final ViewGroup.LayoutParams params = mSelectedLine.getLayoutParams();
        ValueAnimator widthAnimator = ValueAnimator.ofInt(params.width, tab.getMeasuredWidth());
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.width = (int) animation.getAnimatedValue();
                mSelectedLine.setLayoutParams(params);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.playTogether(xAnimator, widthAnimator);

        return set;
    }

    public void showCityPicker() {
        initJDCityPickerPop();
        if (!isShow()) {
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }


    private void hidePop() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }

    private boolean isShow() {
        return popwindow.isShowing();
    }


    private void updateTabVisible() {
        mProTv.setVisibility(provinceList == null || provinceList.isEmpty() ? View.GONE : View.VISIBLE);
        mCityTv.setVisibility(cityList == null || cityList.isEmpty() ? View.GONE : View.VISIBLE);
        mAreaTv.setVisibility(areaList == null || areaList.isEmpty() ? View.GONE : View.VISIBLE);
    }


    /**
     * 选择回调
     *
     * @param districtBean
     */
    private void callback(DistrictBean districtBean) {

        ProvinceBean provinceBean = provinceList != null &&
                !provinceList.isEmpty() &&
                mProvinceAdapter != null &&
                mProvinceAdapter.getSelectedPosition() != INDEX_INVALID ?
                provinceList.get(mProvinceAdapter.getSelectedPosition()) : null;

        CityBean cityBean = cityList != null &&
                !cityList.isEmpty() &&
                mCityAdapter != null &&
                mCityAdapter.getSelectedPosition() != INDEX_INVALID ?
                cityList.get(mCityAdapter.getSelectedPosition()) : null;

        mBaseListener.onSelected(provinceBean, cityBean, districtBean);
        hidePop();

    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case INDEX_INVALID:
                    provinceList = (List<ProvinceBean>) msg.obj;
                    mProvinceAdapter.notifyDataSetChanged();
                    mCityListView.setAdapter(mProvinceAdapter);

                    break;

                case INDEX_TAB_PROVINCE:
                    provinceList = (List<ProvinceBean>) msg.obj;
                    mProvinceAdapter.notifyDataSetChanged();
                    mCityListView.setAdapter(mProvinceAdapter);
                    break;


                case INDEX_TAB_CITY:
                    cityList = (List<CityBean>) msg.obj;
                    mCityAdapter.notifyDataSetChanged();
                    if (cityList != null && !cityList.isEmpty()) {
                        mCityListView.setAdapter(mCityAdapter);
                        tabIndex = INDEX_TAB_CITY;
                    }
                    break;

                case INDEX_TAB_AREA:
                    areaList = (List<DistrictBean>) msg.obj;
                    mAreaAdapter.notifyDataSetChanged();
                    if (areaList != null && !areaList.isEmpty()) {
                        mCityListView.setAdapter(mAreaAdapter);
                        tabIndex = INDEX_TAB_AREA;
                    }
                    break;

            }

            updateTabsStyle(tabIndex);
            updateIndicator();
            return true;
        }
    });

    /**
     * 设置选中的城市tab是否可见
     */
    private void updateTabsStyle(int tabIndex) {
        switch (tabIndex) {
            case INDEX_INVALID:
                mProTv.setTextColor(Color.parseColor(colorAlert));
                mProTv.setVisibility(View.VISIBLE);
                mCityTv.setVisibility(View.GONE);
                mAreaTv.setVisibility(View.GONE);

                break;

            case INDEX_TAB_PROVINCE:
                mProTv.setTextColor(Color.parseColor(colorAlert));
                mProTv.setVisibility(View.VISIBLE);
                mCityTv.setVisibility(View.GONE);
                mAreaTv.setVisibility(View.GONE);
                break;


            case INDEX_TAB_CITY:
                mProTv.setTextColor(Color.parseColor(colorSelected));
                mCityTv.setTextColor(Color.parseColor(colorAlert));
                mProTv.setVisibility(View.VISIBLE);
                mCityTv.setVisibility(View.VISIBLE);
                mAreaTv.setVisibility(View.GONE);
                break;

            case INDEX_TAB_AREA:
                mProTv.setTextColor(Color.parseColor(colorSelected));
                mCityTv.setTextColor(Color.parseColor(colorSelected));
                mAreaTv.setTextColor(Color.parseColor(colorAlert));
                mProTv.setVisibility(View.VISIBLE);
                mCityTv.setVisibility(View.VISIBLE);
                mAreaTv.setVisibility(View.VISIBLE);
                break;
        }

    }


}
