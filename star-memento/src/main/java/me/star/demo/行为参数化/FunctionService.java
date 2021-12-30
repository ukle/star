package me.star.demo.行为参数化;

import org.junit.Test;

/**
 * @author sida_zhou
 * @description 参数行为化
 * @date 2021/12/30
 */
public class FunctionService {

    @Test
    public void doAnything() {
        System.out.println("测试行为参数化");
        executeDoSomething(this::doSomething);
    }

    private void executeDoSomething(FunctionInterface functionInterface) {
        System.out.println("方法内业务开始");
        String a = "随便什么参数";
        String b = "随便什么参数";
        functionInterface.doSomething(a, b);
        System.out.println("方法内业务结束");
    }

    public void doSomething(String a, String b) {
        System.out.println("执行了参数行为");
    }
}
