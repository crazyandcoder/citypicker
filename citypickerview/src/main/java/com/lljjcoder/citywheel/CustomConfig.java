package com.lljjcoder.citywheel;

import android.content.Context;

import com.lljjcoder.bean.CustomCityData;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市选择器样式配置
 * 作者：liji on 2017/11/4 10:31
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class CustomConfig {

    public static final Integer NONE = -1111;

    /**
     * 滚轮显示的item个数
     */
    private int visibleItems = 5;

    /**
     * 省滚轮是否循环滚动
     */
    private boolean isProvinceCyclic = true;

    /**
     * 市滚轮是否循环滚动
     */
    private boolean isCityCyclic = true;

    /**
     * 区滚轮是否循环滚动
     */
    private boolean isDistrictCyclic = true;

    /**
     * Color.BLACK
     */
    private String cancelTextColorStr = "#000000";

    /**
     * Color.BLACK
     */
    private String cancelText = "取消";

    private int cancelTextSize = 16;

    /**
     * Color.BLUE
     */
    private String confirmTextColorStr = "#0000FF";

    /**
     * Color.BLUE
     */
    private String confirmText = "确定";

    /**
     * Color.BLUE
     */
    private int confirmTextSize = 16;

    /**
     * 标题
     */
    private String mTitle = "选择地区";

    /**
     * 标题背景颜色
     */
    private String titleBackgroundColorStr = "#E9E9E9";

    /**
     * 标题颜色
     */
    private String titleTextColorStr = "#585858";

    /**
     * 标题字体大小
     */
    private int titleTextSize = 18;


    /**
     * 自定义的item布局
     */
    private Integer customItemLayout;

    /**
     * 自定义的item txt id
     */
    private Integer customItemTextViewId;

    /**
     * 是否显示滚轮上面的模糊阴影效果
     */
    private boolean drawShadows = true;


    /**
     * 第一次默认的显示省份，一般配合定位，使用
     */
    private String defaultProvinceName = "";

    /**
     * 第一次默认得显示城市，一般配合定位，使用
     */
    private String defaultCityName = "";

    /**
     * 第一次默认得显示，一般配合定位，使用
     */
    private String defaultDistrict = "";


    /**
     * 中间线的颜色
     */
    private String lineColor = "#C7C7C7";

    /**
     * 中间线的宽度
     */
    private int lineHeigh = 3;

    /**
     * 默认显示的城市数据，只包含省市区名称
     */
    /**
     * 定义显示省市区三种滚轮的显示状态
     * PRO:只显示省份的一级选择器
     * PRO_CITY:显示省份和城市二级联动的选择器
     * PRO_CITY_DIS:显示省份和城市和县区三级联动的选择器
     */
    public enum WheelType {
        PRO, PRO_CITY, PRO_CITY_DIS
    }

    /**
     * 定义默认显示省市区三级联动的滚轮选择器
     */
    public CustomConfig.WheelType mWheelType = CustomConfig.WheelType.PRO_CITY_DIS;

    public CustomConfig.WheelType getWheelType() {
        return mWheelType;
    }


    /**
     * 是否显示半透明的背景
     */
    private boolean isShowBackground = true;

    private List<CustomCityData> cityDataList = new ArrayList<>();

    public boolean isShowBackground() {
        return isShowBackground;
    }

    public String getLineColor() {
        return lineColor == null ? "" : lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineHeigh() {
        return lineHeigh;
    }

    public void setLineHeigh(int lineHeigh) {
        this.lineHeigh = lineHeigh;
    }

    public boolean isDrawShadows() {
        return drawShadows;
    }

    public void setDrawShadows(boolean drawShadows) {
        this.drawShadows = drawShadows;
    }

    public int getVisibleItems() {
        return visibleItems;
    }

    public void setVisibleItems(int visibleItems) {
        this.visibleItems = visibleItems;
    }

    public boolean isProvinceCyclic() {
        return isProvinceCyclic;
    }

    public void setProvinceCyclic(boolean provinceCyclic) {
        isProvinceCyclic = provinceCyclic;
    }

    public boolean isCityCyclic() {
        return isCityCyclic;
    }

    public void setCityCyclic(boolean cityCyclic) {
        isCityCyclic = cityCyclic;
    }

    public boolean isDistrictCyclic() {
        return isDistrictCyclic;
    }

    public void setDistrictCyclic(boolean districtCyclic) {
        isDistrictCyclic = districtCyclic;
    }

    public String getDefaultProvinceName() {
        return defaultProvinceName == null ? "" : defaultProvinceName;
    }

    public void setDefaultProvinceName(String defaultProvinceName) {
        this.defaultProvinceName = defaultProvinceName;
    }

    public String getDefaultCityName() {
        return defaultCityName == null ? "" : defaultCityName;
    }

    public void setDefaultCityName(String defaultCityName) {
        this.defaultCityName = defaultCityName;
    }

    public String getDefaultDistrict() {
        return defaultDistrict == null ? "" : defaultDistrict;
    }

    public void setDefaultDistrict(String defaultDistrict) {
        this.defaultDistrict = defaultDistrict;
    }
    public String getCancelTextColorStr() {
        return cancelTextColorStr == null ? "" : cancelTextColorStr;
    }

    public void setCancelTextColorStr(String cancelTextColorStr) {
        this.cancelTextColorStr = cancelTextColorStr;
    }

    public String getCancelText() {
        return cancelText == null ? "" : cancelText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public int getCancelTextSize() {
        return cancelTextSize;
    }

    public void setCancelTextSize(int cancelTextSize) {
        this.cancelTextSize = cancelTextSize;
    }

    public String getConfirmTextColorStr() {
        return confirmTextColorStr == null ? "" : confirmTextColorStr;
    }

    public void setConfirmTextColorStr(String confirmTextColorStr) {
        this.confirmTextColorStr = confirmTextColorStr;
    }

    public String getConfirmText() {
        return confirmText == null ? "" : confirmText;
    }

    public void setConfirmText(String confirmText) {
        this.confirmText = confirmText;
    }

    public int getConfirmTextSize() {
        return confirmTextSize;
    }

    public void setConfirmTextSize(int confirmTextSize) {
        this.confirmTextSize = confirmTextSize;
    }

    public String getTitle() {
        return mTitle == null ? "" : mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitleBackgroundColorStr() {
        return titleBackgroundColorStr == null ? "" : titleBackgroundColorStr;
    }

    public void setTitleBackgroundColorStr(String titleBackgroundColorStr) {
        this.titleBackgroundColorStr = titleBackgroundColorStr;
    }

    public String getTitleTextColorStr() {
        return titleTextColorStr == null ? "" : titleTextColorStr;
    }

    public void setTitleTextColorStr(String titleTextColorStr) {
        this.titleTextColorStr = titleTextColorStr;
    }

    public int getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(int titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    //
    public Integer getCustomItemLayout() {
        return customItemLayout == null ? NONE : customItemLayout;
    }

    public void setCustomItemLayout(int customItemLayout) {
        this.customItemLayout = customItemLayout;
    }

    public Integer getCustomItemTextViewId() {
        return customItemTextViewId == null ? NONE : customItemTextViewId;
    }

    public void setCustomItemTextViewId(Integer customItemTextViewId) {
        this.customItemTextViewId = customItemTextViewId;
    }

    public List<CustomCityData> getCityDataList() {
        return cityDataList;
    }

    public void setCityDataList(List<CustomCityData> cityDataList) {
        this.cityDataList = cityDataList;
    }

    public void setShowBackground(boolean showBackground) {
        isShowBackground = showBackground;
    }

    public CustomConfig(Builder builder) {


        /**
         * 标题栏相关的属性：
         * 0、标题栏背景颜色
         * 1、标题文字、大小、颜色
         *
         * 2、取消字体的颜色、大小、内容
         * 3、确认字体的颜色、大小、内容
         */
        this.titleBackgroundColorStr = builder.titleBackgroundColorStr;

        this.mTitle = builder.mTitle;
        this.titleTextColorStr = builder.titleTextColorStr;
        this.titleTextSize = builder.titleTextSize;

        this.cancelTextColorStr = builder.cancelTextColorStr;
        this.cancelText = builder.cancelText;
        this.cancelTextSize = builder.cancelTextSize;

        this.confirmTextColorStr = builder.confirmTextColorStr;
        this.confirmText = builder.confirmText;
        this.confirmTextSize = builder.confirmTextSize;

        /**
         * 滚轮相关的属性：
         * 1、item显示的个数
         * 2、省份是否可以循环
         * 3、城市是否可以循环
         * 4、地区是否可以循环
         */
        this.visibleItems = builder.visibleItems;
        this.isProvinceCyclic = builder.isProvinceCyclic;
        this.isDistrictCyclic = builder.isDistrictCyclic;
        this.isCityCyclic = builder.isCityCyclic;
        /**
         * 默认的省市区地址
         */
        this.defaultDistrict = builder.defaultDistrict;
        this.defaultCityName = builder.defaultCityName;
        this.defaultProvinceName = builder.defaultProvinceName;


        /**
         * 是否显示城市和地区
         */
        this.mWheelType = builder.mWheelType;


        /**
         * 是否显示半透明
         */
        this.isShowBackground = builder.isShowBackground;
//
        /**
         * 自定义item的布局，必须制定Layout和id
         */
        this.customItemLayout = builder.customItemLayout;
        this.customItemTextViewId = builder.customItemTextViewId;

        /**
         * 是否显示滚轮上面的模糊阴影效果
         */
        this.drawShadows = builder.drawShadows;
        this.lineColor = builder.lineColor;
        this.lineHeigh = builder.lineHeigh;
        this.cityDataList = builder.cityDataList;

    }

    public static class Builder {

        /**
         * 滚轮显示的item个数
         */
        private int visibleItems = 5;

        /**
         * 省滚轮是否循环滚动
         */
        private boolean isProvinceCyclic = true;

        /**
         * 市滚轮是否循环滚动
         */
        private boolean isCityCyclic = true;

        /**
         * 区滚轮是否循环滚动
         */
        private boolean isDistrictCyclic = true;

        /**
         * Color.BLACK
         */
        private String cancelTextColorStr = "#000000";

        /**
         * Color.BLACK
         */
        private String cancelText = "取消";

        private int cancelTextSize = 16;

        /**
         * Color.BLUE
         */
        private String confirmTextColorStr = "#0000FF";

        /**
         * Color.BLUE
         */
        private String confirmText = "确定";

        /**
         * Color.BLUE
         */
        private int confirmTextSize = 16;

        /**
         * 标题
         */
        private String mTitle = "选择地区";

        /**
         * 标题背景颜色
         */
        private String titleBackgroundColorStr = "#E9E9E9";

        /**
         * 标题颜色
         */
        private String titleTextColorStr = "#585858";

        /**
         * 标题字体大小
         */
        private int titleTextSize = 18;


        /**
         * 是否显示半透明的背景
         */
        private boolean isShowBackground = true;

        /**
         * 自定义的item布局
         */
        private Integer customItemLayout;

        /**
         * 自定义的item txt id
         */
        private Integer customItemTextViewId;

        /**
         * 是否显示滚轮上面的模糊阴影效果
         */
        private boolean drawShadows = true;

        /**
         * 中间线的颜色
         */
        private String lineColor = "#C7C7C7";
        /**
         * 定义默认显示省市区三级联动的滚轮选择器
         */
        private CustomConfig.WheelType mWheelType = CustomConfig.WheelType.PRO_CITY_DIS;
        /**
         * 中间线的宽度
         */
        private int lineHeigh = 3;

        private List<CustomCityData> cityDataList = new ArrayList<>();

        /**
         * 第一次默认的显示省份，一般配合定位，使用
         */
        private String defaultProvinceName = "";

        /**
         * 第一次默认得显示城市，一般配合定位，使用
         */
        private String defaultCityName = "";

        /**
         * 第一次默认得显示，一般配合定位，使用
         */
        private String defaultDistrict = "";

        public Builder() {
        }


        /**
         * 显示省市区三级联动的显示状态
         * PRO:只显示省份的一级选择器
         * PRO_CITY:显示省份和城市二级联动的选择器
         * PRO_CITY_DIS:显示省份和城市和县区三级联动的选择器
         *
         * @param wheelType
         * @return
         */
        public CustomConfig.Builder setCityWheelType(CustomConfig.WheelType wheelType) {
            this.mWheelType = wheelType;
            return this;
        }


        public Builder setCityData(List<CustomCityData> data) {
            this.cityDataList = data;
            return this;
        }

        /**
         * 第一次默认的显示省份，一般配合定位，使用
         *
         * @param defaultProvinceName
         * @return
         */
        public Builder province(String defaultProvinceName) {
            this.defaultProvinceName = defaultProvinceName;
            return this;
        }

        /**
         * 第一次默认得显示城市，一般配合定位，使用
         *
         * @param defaultCityName
         * @return
         */
        public Builder city(String defaultCityName) {
            this.defaultCityName = defaultCityName;
            return this;
        }

        /**
         * 第一次默认地区显示，一般配合定位，使用
         *
         * @param defaultDistrict
         * @return
         */
        public Builder district(String defaultDistrict) {
            this.defaultDistrict = defaultDistrict;
            return this;
        }

        /**
         * 中间线的宽度
         *
         * @param lineHeigh
         * @return
         */
        public Builder setLineHeigh(int lineHeigh) {
            this.lineHeigh = lineHeigh;
            return this;
        }

        /**
         * 中间线的颜色
         *
         * @param lineColor
         * @return
         */
        public Builder setLineColor(String lineColor) {
            this.lineColor = lineColor;
            return this;
        }

        /**
         * 是否显示滚轮上面的模糊阴影效果
         *
         * @param drawShadows
         * @return
         */
        public Builder drawShadows(boolean drawShadows) {
            this.drawShadows = drawShadows;
            return this;
        }

        /**
         * 设置标题背景颜色
         *
         * @param colorBg
         * @return
         */
        public Builder titleBackgroundColor(String colorBg) {
            this.titleBackgroundColorStr = colorBg;
            return this;
        }

        /**
         * 设置标题字体颜色
         *
         * @param titleTextColorStr
         * @return
         */
        public Builder titleTextColor(String titleTextColorStr) {
            this.titleTextColorStr = titleTextColorStr;
            return this;
        }

        /**
         * 设置标题字体大小
         *
         * @param titleTextSize
         * @return
         */
        public Builder titleTextSize(int titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        /**
         * 设置标题
         *
         * @param mtitle
         * @return
         */
        public Builder title(String mtitle) {
            this.mTitle = mtitle;
            return this;
        }

        /**
         * 确认按钮文字
         *
         * @param confirmTextSize
         * @return
         */
        public Builder confirmTextSize(int confirmTextSize) {
            this.confirmTextSize = confirmTextSize;
            return this;
        }

        /**
         * 确认按钮文字
         *
         * @param confirmText
         * @return
         */
        public Builder confirmText(String confirmText) {
            this.confirmText = confirmText;
            return this;
        }

        /**
         * 确认按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder confirTextColor(String color) {
            this.confirmTextColorStr = color;
            return this;
        }

        /**
         * 取消按钮文字颜色
         *
         * @param color
         * @return
         */
        public Builder cancelTextColor(String color) {
            this.cancelTextColorStr = color;
            return this;
        }

        /**
         * 取消按钮文字大小
         *
         * @param cancelTextSize
         * @return
         */
        public Builder cancelTextSize(int cancelTextSize) {
            this.cancelTextSize = cancelTextSize;
            return this;
        }

        /**
         * 取消按钮文字
         *
         * @param cancelText
         * @return
         */
        public Builder cancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        /**
         * 滚轮显示的item个数
         *
         * @param visibleItems
         * @return
         */
        public Builder visibleItemsCount(int visibleItems) {
            this.visibleItems = visibleItems;
            return this;
        }

        /**
         * 省滚轮是否循环滚动
         *
         * @param isProvinceCyclic
         * @return
         */
        public Builder provinceCyclic(boolean isProvinceCyclic) {
            this.isProvinceCyclic = isProvinceCyclic;
            return this;
        }

        /**
         * 市滚轮是否循环滚动
         *
         * @param isCityCyclic
         * @return
         */
        public Builder cityCyclic(boolean isCityCyclic) {
            this.isCityCyclic = isCityCyclic;
            return this;
        }

        /**
         * 区滚轮是否循环滚动
         *
         * @param isDistrictCyclic
         * @return
         */
        public Builder districtCyclic(boolean isDistrictCyclic) {
            this.isDistrictCyclic = isDistrictCyclic;
            return this;
        }

        /**
         * 是否显示半透明的背景
         *
         * @param isShowBackground
         * @return
         */
        public Builder showBackground(boolean isShowBackground) {
            this.isShowBackground = isShowBackground;
            return this;
        }
//

        /**
         * 自定义item布局
         *
         * @param itemLayout
         * @return
         */
        public Builder setCustomItemLayout(Integer itemLayout) {
            this.customItemLayout = itemLayout;
            return this;
        }

        /**
         * 自定义item布局中的id
         *
         * @param setCustomItemTextViewId
         * @return
         */
        public Builder setCustomItemTextViewId(Integer setCustomItemTextViewId) {
            this.customItemTextViewId = setCustomItemTextViewId;
            return this;
        }

        public CustomConfig build() {
            CustomConfig config = new CustomConfig(this);
            return config;
        }
    }
}
