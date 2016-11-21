package com.yl.model;

/**
 * Created by JerryMouse on 2016/11/19.
 *
 * 高精度IP定位
 */
public class HighIP {

    //纬度坐标
    private String lat;

    //经度坐标
    private String lng;

    //定位结果半径
    private String radius;

    //定位结果可信度
    private String confidence;

    //国家
    private String country;

    //省份
    private String province;

    //城市
    private String city;

    //区县
    private String district;

    //街道
    private String street;

    //门牌号
    private String street_number;

    //行政区划代码（身份证前6位）
    private String admin_area_code;

    //结构化地址信息
    private String formatted_address;

    //商圈信息
    private String business;

    //定位时间
    private String loc_time;

    //定位结果状态码   161：定位成功  167：定位失败
    private String error;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getAdmin_area_code() {
        return admin_area_code;
    }

    public void setAdmin_area_code(String admin_area_code) {
        this.admin_area_code = admin_area_code;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getLoc_time() {
        return loc_time;
    }

    public void setLoc_time(String loc_time) {
        this.loc_time = loc_time;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
