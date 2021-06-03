package com.example.mealrecord;

public class Vitamin {
    // 영양제 이름
    String name;
    // 섭취 여부
    Boolean supplement;

    public Vitamin(String name, Boolean supplement) {
        this.name = name;
        this.supplement = supplement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSupplement() {
        return supplement;
    }

    public void setSupplement(Boolean supplement) {
        this.supplement = supplement;
    }
}
