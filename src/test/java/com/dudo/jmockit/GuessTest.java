package com.dudo.jmockit;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhangkai9
 * @date 2015/5/29
 */
public class GuessTest {
    @Tested        // 表明被修饰实例是将会被自动构建和注入的实例
            Guess guess = new Guess(3);
    @Injectable    // 表明被修饰实例将会自动注入到@Tested修饰的实例中，并且会自动mock掉，除非在测试前被赋值
            GuessDAO guessDAO;

    /**
     * 连续3次失败
     */
    @Test
    public void behaviorTest_fail3time() {

        new Expectations() {        // Expectations中包含的内部类区块中，体现的是一个录制被测类的逻辑。
            @Mocked(methods = "tryIt")  // 表明被修饰的类对tryIt()方法进行mock。
                    Guess g;

            {
                g.tryIt();             // 期待调用Guess.tryIt()方法
                result = false;        // mock掉返回值为false（表明猜不中）
                times = 3;             // 期待以上过程重复3次
                guessDAO.saveResult(false, anyInt); // 期待调用guessDAO把猜失败的结果保存
            }
        };
        guess.doit();               // 录制完成后，进行实际的代码调用，也称回放(replay)
    }
}