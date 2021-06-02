package com.koreait.examplerequest;

import java.util.ArrayList;

public class MovieListResult {
    // 프로퍼티이름이 boxOfficeType 이고 값의 타입이 문자열인 JSON 프로퍼티를 꺼내 담는 역활
    String boxOfficeType;
    // 프로퍼티 이름이 showRange 이고 값의 타입이 문자열인 JSON 프로퍼티를 꺼내 담는 역활
    String showRange;

    // 프로퍼티 이름이 dailyBoxOfficeList 이고 값의 타입이 배열인 JSON 프로퍼티를 꺼내 담는 역활
    ArrayList<Movie> dailyBoxOfficeList = new ArrayList<Movie>();
}
