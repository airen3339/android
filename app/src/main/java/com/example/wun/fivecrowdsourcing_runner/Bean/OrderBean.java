package com.example.wun.fivecrowdsourcing_runner.Bean;

/**
 * Created by WUN on 2018/3/8.
 */

public class OrderBean {

    /**
     * msg : 请求成功
     * status: 0
     * data : [{"createtime":"2017-10-19T20:28:43","orderid":20,"price":100,"status":2,"title":"订单标题测试3","uid":71},{"createtime":"2017-10-19T20:44:40","orderid":31,"price":11800,"status":2,"title":"订单标题测试14","uid":71},{"createtime":"2017-10-19T20:44:51","orderid":32,"price":11800,"status":2,"title":"订单标题测试15","uid":71},{"createtime":"2017-10-20T08:02:07","orderid":43,"price":11800,"status":2,"title":"订单标题测试","uid":71},{"createtime":"2017-10-20T08:02:16","orderid":44,"price":11800,"status":2,"title":"订单标题测试","uid":71},{"createtime":"2017-10-22T15:14:39","orderid":890,"price":11800,"status":2,"title":"","uid":71},{"createtime":"2017-11-09T09:17:20","orderid":1446,"price":99.99,"status":1,"title":"订单标题测试","uid":71},{"createtime":"2017-11-09T09:20:58","orderid":1447,"price":567,"status":2,"title":"订单标题测试","uid":71},{"createtime":"2017-11-09T09:20:58","orderid":1448,"price":256.99,"status":1,"title":"订单标题测试","uid":71},{"createtime":"2017-11-09T09:20:58","orderid":1449,"price":399,"status":2,"title":"订单标题测试","uid":71}]
     * page : 1
     */

    private Long delorderid;//配送单id
    private Long merchantid;//商户id
    private Long delmethodid;//配送方式id
    private Long estimatedtime;//预估时间
    private Double estimatedtotalprice;//预估总价格
    private int distance;//距离
    private String ordertime;//下单时间
    private Long runnerid;//跑腿人id
    private String intime;//接单时间
    private String outtime;//送达时间
    private Double extraprice;//加价费
    private Double trueweight;//真实质量
    private int creditpoints;//信用分
    private int integral;//积分
    private String cusName;//客户名
    private String cusPhone;//客户电话
    private String cusAddress;//客户地址
    private String things;//内容
    private int status;//状态：1：新建；2：待抢单；3：待取单；4：配送中；5：配送完成
    private String storeName;
    private String storeAddress;
    public OrderBean(){}

    public OrderBean(Long delorderid, Long merchantid, Long delmethodid, Long estimatedtime, Double estimatedtotalprice, int distance, String ordertime, Long runnerid, String intime, String outtime, Double extraprice, Double trueweight, int creditpoints, int integral, String cusName, String cusPhone, String cusAddress, String things, int status, String storeName) {
        this.delorderid = delorderid;
        this.merchantid = merchantid;
        this.delmethodid = delmethodid;
        this.estimatedtime = estimatedtime;
        this.estimatedtotalprice = estimatedtotalprice;
        this.distance = distance;
        this.ordertime = ordertime;
        this.runnerid = runnerid;
        this.intime = intime;
        this.outtime = outtime;
        this.extraprice = extraprice;
        this.trueweight = trueweight;
        this.creditpoints = creditpoints;
        this.integral = integral;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusAddress = cusAddress;
        this.things = things;
        this.status = status;
        this.storeName = storeName;
    }

    public Long getDelorderid() {
        return delorderid;
    }

    public void setDelorderid(Long delorderid) {
        this.delorderid = delorderid;
    }

    public Long getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Long merchantid) {
        this.merchantid = merchantid;
    }

    public Long getDelmethodid() {
        return delmethodid;
    }

    public void setDelmethodid(Long delmethodid) {
        this.delmethodid = delmethodid;
    }

    public Long getEstimatedtime() {
        return estimatedtime;
    }

    public void setEstimatedtime(Long estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    public Double getEstimatedtotalprice() {
        return estimatedtotalprice;
    }

    public void setEstimatedtotalprice(Double estimatedtotalprice) {
        this.estimatedtotalprice = estimatedtotalprice;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Long getRunnerid() {
        return runnerid;
    }

    public void setRunnerid(Long runnerid) {
        this.runnerid = runnerid;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public Double getExtraprice() {
        return extraprice;
    }

    public void setExtraprice(Double extraprice) {
        this.extraprice = extraprice;
    }

    public Double getTrueweight() {
        return trueweight;
    }

    public void setTrueweight(Double trueweight) {
        this.trueweight = trueweight;
    }

    public int getCreditpoints() {
        return creditpoints;
    }

    public void setCreditpoints(int creditpoints) {
        this.creditpoints = creditpoints;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public OrderBean(Long delorderid, Long merchantid, Long delmethodid, Long estimatedtime, Double estimatedtotalprice, int distance, String ordertime, Long runnerid, String intime, String outtime, Double extraprice, Double trueweight, int creditpoints, int integral, String cusName, String cusPhone, String cusAddress, String things, int status, String storeName, String storeAddress) {
        this.delorderid = delorderid;
        this.merchantid = merchantid;
        this.delmethodid = delmethodid;
        this.estimatedtime = estimatedtime;
        this.estimatedtotalprice = estimatedtotalprice;
        this.distance = distance;
        this.ordertime = ordertime;
        this.runnerid = runnerid;
        this.intime = intime;
        this.outtime = outtime;
        this.extraprice = extraprice;
        this.trueweight = trueweight;
        this.creditpoints = creditpoints;
        this.integral = integral;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusAddress = cusAddress;
        this.things = things;
        this.status = status;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}

