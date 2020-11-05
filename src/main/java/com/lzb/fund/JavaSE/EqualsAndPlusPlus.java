package com.lzb.fund.JavaSE;

import java.util.Objects;

public class EqualsAndPlusPlus {
    private String key1;
    private String key2;

    public EqualsAndPlusPlus(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    // pay attention to the difference between overriding Object.equals
    public static void main(String[] args) {
        EqualsAndPlusPlus epp1 = new EqualsAndPlusPlus("take1", "take2");
        EqualsAndPlusPlus epp2 = new EqualsAndPlusPlus("take1", "take2");
        System.out.println(epp1.equals(epp2));
        System.out.println(epp1 == epp2);
    }

//    @Override
//    public int hashCode() {
//        int result = key1 != null ? key1.hashCode() : 0;
//        result = 31 * result + (key2 != null ? key2.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        EqualsAndPlusPlus that = (EqualsAndPlusPlus) o;
//
//        if (key1 != that.key1) return false;
//
//        return Objects.equals(key2, that.key2);
//    }
}
