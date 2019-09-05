package com.tent.common.entity;

import com.tent.common.json.JSONObject;

import java.util.Map;

public class Consts {

    public static enum BoolType implements IConst {
        YES(1, "是"),
        NO(0, "否");

        public static BoolType get(int i) {
            switch (i) {
                case 0:
                    return BoolType.NO;
                case 1:
                    return BoolType.YES;
            }
            return null;
        }

        private int nCode;
        private String nLable;

        BoolType(int nCode, String nLable) {
            this.nCode = nCode;
            this.nLable = nLable;
        }

        @Override
        public int val() {
            return this.nCode;
        }

        @Override
        public String label() {
            return this.nLable;
        }

        @Override
        public int getVal() {
            return this.nCode;
        }

        @Override
        public String getLabel() {
            return this.nLable;
        }

        @Override
        public IConst[] vals() {
            return BoolType.values();
        }

        @Override
        public IConst valof(String str) {
            return BoolType.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {
            return i == nCode;
        }

        @Override
        public boolean isNot(int i) {
            return i != nCode;
        }
        public static Map<Integer,String> map(){
            return  IConstUtils.map(BoolType.values());
        }
        public static String json(){
            return JSONObject.wrap(map(),3).toString();
        }
    }

    public static enum Sex implements IConst {
        WOMAN(1, "女"),
        MAN(0, "男");

        public static Sex get(int i) {
            switch (i) {
                case 0:
                    return Sex.MAN;
                case 1:
                    return Sex.WOMAN;
            }
            return null;
        }

        private int nCode;
        private String nLable;

        Sex(int nCode, String nLable) {
            this.nCode = nCode;
            this.nLable = nLable;
        }

        @Override
        public int val() {
            return this.nCode;
        }

        @Override
        public String label() {
            return this.nLable;
        }

        @Override
        public int getVal() {
            return this.nCode;
        }

        @Override
        public String getLabel() {
            return this.nLable;
        }

        @Override
        public IConst[] vals() {
            return BoolType.values();
        }

        @Override
        public IConst valof(String str) {
            return BoolType.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {
            return i == nCode;
        }

        @Override
        public boolean isNot(int i) {
            return i != nCode;
        }
        public static Map<Integer,String> map(){
            return  IConstUtils.map(BoolType.values());
        }
        public static String json(){
            return JSONObject.wrap(map(),3).toString();
        }
    }

    //会员模块(hy)
    public static enum MemberType implements IConst {
        EXCHANGE(0, "交易中心"),
        MEMBER(1, "交易会员"),
        Chain(2, "供应链会员");

        public static MemberType get(int i) {
            switch (i) {
                case 0:
                    return MemberType.EXCHANGE;
                case 1:
                    return MemberType.MEMBER;
                case 2:
                    return MemberType.Chain;
            }
            return null;
        }

        private int nCode;
        private String nLable;

        MemberType(int nCode, String nLable) {
            this.nCode = nCode;
            this.nLable = nLable;
        }

        @Override
        public int val() {
            return nCode;
        }

        @Override
        public String label() {
            return nLable;
        }

        @Override
        public int getVal() {
            return nCode;
        }

        @Override
        public String getLabel() {
            return nLable;
        }

        @Override
        public IConst[] vals() {
            return MemberType.values();
        }

        @Override
        public IConst valof(String str) {
            return MemberType.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {
            return i == nCode;
        }

        @Override
        public boolean isNot(int i) {
            return i != nCode;
        }

    }

    public static enum CompanyType implements IConst {
        PRODUCTIVE(0, "生产型"),
        PROCESSING(1, "加工型"),
        TRADE(2, "贸易型");

        public static CompanyType get(int i) {
            switch (i) {
                case 0:
                    return CompanyType.PRODUCTIVE;
                case 1:
                    return CompanyType.PROCESSING;
                case 2:
                    return CompanyType.TRADE;
            }
            return null;
        }


        private int nCode;
        private String nLabel;

        CompanyType(int _nCode, String _nlabel) {

            this.nCode = _nCode;
            this.nLabel = _nlabel;
        }

        @Override
        public int val() {
            return nCode;
        }

        @Override
        public String label() {
            return nLabel;
        }

        @Override
        public String getLabel() {
            return nLabel;
        }

        @Override
        public int getVal() {
            return nCode;
        }

        @Override
        public IConst[] vals() {
            return CompanyType.values();
        }

        @Override
        public IConst valof(String str) {

            return CompanyType.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {

            return nCode == i;
        }

        @Override
        public boolean isNot(int i) {

            return nCode != i;
        }

    }

    public static enum MemberStatus implements IConst {
        WAIT(0, "待激活"),
        CHECK(1, "待审核"),
        REJECT(2, "被拒绝"),
        NORMAL(3, "正常"),
        FLEEZE(4, "冻结"),
        EDIT(5, "待完善资料");

        public static MemberStatus get(int i) {
            switch (i) {
                case 0:
                    return MemberStatus.WAIT;
                case 1:
                    return MemberStatus.CHECK;
                case 2:
                    return MemberStatus.REJECT;
                case 3:
                    return MemberStatus.NORMAL;
                case 4:
                    return MemberStatus.FLEEZE;
                case 5:
                    return MemberStatus.EDIT;
            }
            return null;
        }


        private int nCode;
        private String nLabel;

        MemberStatus(int _nCode, String _nlabel) {

            this.nCode = _nCode;
            this.nLabel = _nlabel;
        }

        @Override
        public int val() {
            return nCode;
        }

        @Override
        public String label() {
            return nLabel;
        }

        @Override
        public String getLabel() {
            return nLabel;
        }

        @Override
        public int getVal() {
            return nCode;
        }

        @Override
        public IConst[] vals() {
            return MemberStatus.values();
        }

        @Override
        public IConst valof(String str) {

            return MemberStatus.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {

            return nCode == i;
        }

        @Override
        public boolean isNot(int i) {

            return nCode != i;
        }

    }

    public static enum AuthenticateType implements IConst {
        No(0, "注册会员"),
        PERSONAL(10, "个人认证"),
        Company(20, "企业认证");

        public static AuthenticateType get(int i) {
            switch (i) {
                case 0:
                    return AuthenticateType.No;
                case 10:
                    return AuthenticateType.PERSONAL;
                case 20:
                    return AuthenticateType.Company;
            }
            return null;
        }

        private int nCode;
        private String nLabel;

        AuthenticateType(int _nCode, String _nlabel) {
            this.nCode = _nCode;
            this.nLabel = _nlabel;
        }

        @Override
        public int val() {
            return nCode;
        }

        @Override
        public String label() {
            return nLabel;
        }

        @Override
        public String getLabel() {
            return nLabel;
        }

        @Override
        public int getVal() {
            return nCode;
        }

        @Override
        public IConst[] vals() {
            return AuthenticateType.values();
        }

        @Override
        public IConst valof(String str) {
            return AuthenticateType.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {
            return nCode == i;
        }

        @Override
        public boolean isNot(int i) {
            return nCode != i;
        }

    }

    public static enum Cusource implements IConst{
        PC(0,"PC"),
        MOBILE(1,"手机"),
        OTHER(10,"其它");

        public static Cusource get(int i){
            switch (i) {
                case 0:
                    return Cusource.PC;
                case 1:
                    return Cusource.MOBILE;
                case 10:
                    return Cusource.OTHER;
            }
            return null;
        }

        private int nCode;
        private String nLabel;

        Cusource(int _nCode, String _nlabel) {

            this.nCode = _nCode;
            this.nLabel = _nlabel;
        }

        @Override
        public int val() {
            return nCode;
        }

        @Override
        public String label() {
            return nLabel;
        }

        @Override
        public String getLabel() {
            return nLabel;
        }

        @Override
        public int getVal() {
            return nCode;
        }

        @Override
        public IConst[] vals() {
            return ImageType.values();
        }

        @Override
        public IConst valof(String str) {
            return ImageType.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {
            return nCode == i;
        }

        @Override
        public boolean isNot(int i) {
            return nCode != i;
        }
    }


    //商品模块(sp)
    public static enum ImageType implements IConst {
        ImgPc(0, "pc端"),
        ImgApp(1, "app端"),
        ImgAppIco(2, "app端图标");

        public static ImageType get(int i) {
            switch (i) {
                case 0:
                    return ImageType.ImgPc;
                case 1:
                    return ImageType.ImgApp;
                case 2:
                    return ImageType.ImgAppIco;
            }
            return null;
        }


        private int nCode;
        private String nLabel;

        ImageType(int _nCode, String _nlabel) {

            this.nCode = _nCode;
            this.nLabel = _nlabel;
        }

        @Override
        public int val() {
            return nCode;
        }

        @Override
        public String label() {
            return nLabel;
        }

        @Override
        public String getLabel() {
            return nLabel;
        }

        @Override
        public int getVal() {
            return nCode;
        }

        @Override
        public IConst[] vals() {
            return ImageType.values();
        }

        @Override
        public IConst valof(String str) {
            return ImageType.valueOf(str);
        }

        @Override
        public boolean isEq(int i) {
            return nCode == i;
        }

        @Override
        public boolean isNot(int i) {
            return nCode != i;
        }

    }
}
