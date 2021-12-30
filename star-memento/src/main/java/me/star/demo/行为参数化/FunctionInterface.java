package me.star.demo.行为参数化;

/**
 * @author sida_zhou
 * @description
 * @date 2021/12/30
 */
@FunctionalInterface
public interface FunctionInterface {

    /**
     * 定义行为函数
     *
     * @param a
     * @param b
     */
    void doSomething(String a, String b);
}
