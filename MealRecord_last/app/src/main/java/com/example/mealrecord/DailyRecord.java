package com.example.mealrecord;

public class DailyRecord {
    String category;
    String foodName;
    // 더 추가해야 할 항목이 생각이 안나서 우선 이렇게만 해둡니다!
    public DailyRecord(String category, String foodName){
        this.category = category;
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

}
