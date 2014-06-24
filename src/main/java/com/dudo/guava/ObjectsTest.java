package com.dudo.guava;

import com.google.common.base.Objects;

/**
 * @author zkai.zhang
 * @date 2014/6/23
 */
public class ObjectsTest {
    public static void main(String[] args) {
        ObjectsTest objectsTest = new ObjectsTest(1,"这是测试");
        System.out.println(objectsTest.toString());
    }

    public ObjectsTest(int a, String b) {
        this.a = a;
        this.b = b;
    }

    private int a;
    private String b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
//        return "ObjectsTest{" +
//                "a=" + a +
//                ", b='" + b + '\'' +
//                '}';

        return Objects.toStringHelper(ObjectsTest.class).add("a",a)
                .add("b",b).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectsTest that = (ObjectsTest) o;

        if (a != that.a) return false;
        if (b != null ? !b.equals(that.b) : that.b != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }
}
