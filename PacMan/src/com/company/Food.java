package com.company;

public class Food {
    private FoodTypes foodType;

    public Food(char foodChar) {
        switch (foodChar) {
            case '.':
                this.foodType = FoodTypes.SmallFood;
                break;
            case '*':
                this.foodType = FoodTypes.BigFood;
        }
    }

    public int getPoints() {
        int points = 0;
            switch (this.foodType) {
                case SmallFood -> points = 1;
                case BigFood -> points = 50;
                case null -> points = 0;
        }
        return points;
    }

    public static boolean isTypeOfFoodChar(char ch) {
        return ch == '.' || ch == '*';
    }
}
