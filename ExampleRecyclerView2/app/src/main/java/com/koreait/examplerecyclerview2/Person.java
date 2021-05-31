package com.koreait.examplerecyclerview2;

// 원본 데이터를 담고 있을 객체
// 이 객채가 어댑터가 됨
public class Person {
    String name;
    String mobile;

    public Person(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
