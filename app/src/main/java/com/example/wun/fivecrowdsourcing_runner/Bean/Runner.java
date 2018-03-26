package com.example.wun.fivecrowdsourcing_runner.Bean;

import java.io.Serializable;

/**
 * Created by WUN on 2018/2/18.
 */

public class Runner implements Serializable {
    private Long runnerid;// 跑腿人id
    private String phone;// 电话
    private String name;// 姓名
    private String password;// 密码
    private String idcardnumber;// 身份证号
    private String photoofidcardinhand;// 手持身份证照存储地址
    private String photoofidcardoppo;// 身份证反面照存储地址
    private String photoofidcardposi;// 身份证正面照存储地址
    private String photoofhealcert;// 健康证照片存储地址
    private Double balance;// 余额
    private int integral;// 积分
    private Long margin;// 保证金提交状态：1：提交；2：未提交
    private Double laititude;
    private Double longtitude;
    private String status;// 审核状态:0:新注册 1：待审核 2：审核通过

    public Runner() {
    }

    public Runner(Long runnerid, String phone, String name, String password, String idcardnumber, String photoofidcardinhand, String photoofidcardoppo, String photoofidcardposi, String photoofhealcert, Double balance, int integral, Long margin, Double laititude, Double longtitude, String status) {
        this.runnerid = runnerid;
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.idcardnumber = idcardnumber;
        this.photoofidcardinhand = photoofidcardinhand;
        this.photoofidcardoppo = photoofidcardoppo;
        this.photoofidcardposi = photoofidcardposi;
        this.photoofhealcert = photoofhealcert;
        this.balance = balance;
        this.integral = integral;
        this.margin = margin;
        this.laititude = laititude;
        this.longtitude = longtitude;
        this.status = status;
    }

    public Long getRunnerid() {
        return runnerid;
    }

    public void setRunnerid(Long runnerid) {
        this.runnerid = runnerid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcardnumber() {
        return idcardnumber;
    }

    public void setIdcardnumber(String idcardnumber) {
        this.idcardnumber = idcardnumber;
    }

    public String getPhotoofidcardinhand() {
        return photoofidcardinhand;
    }

    public void setPhotoofidcardinhand(String photoofidcardinhand) {
        this.photoofidcardinhand = photoofidcardinhand;
    }

    public String getPhotoofidcardoppo() {
        return photoofidcardoppo;
    }

    public void setPhotoofidcardoppo(String photoofidcardoppo) {
        this.photoofidcardoppo = photoofidcardoppo;
    }

    public String getPhotoofidcardposi() {
        return photoofidcardposi;
    }

    public void setPhotoofidcardposi(String photoofidcardposi) {
        this.photoofidcardposi = photoofidcardposi;
    }

    public String getPhotoofhealcert() {
        return photoofhealcert;
    }

    public void setPhotoofhealcert(String photoofhealcert) {
        this.photoofhealcert = photoofhealcert;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public Long getMargin() {
        return margin;
    }

    public void setMargin(Long margin) {
        this.margin = margin;
    }

    public Double getLaititude() {
        return laititude;
    }

    public void setLaititude(Double laititude) {
        this.laititude = laititude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}