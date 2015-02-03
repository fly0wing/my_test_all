package com.dudo.dailytest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 村子中有50个人，每人有一条狗。在这50条狗中有病狗（这种病不会传染）。于是人们就要找出病狗。

 每个人可以观察其他的49条狗，以判断它们是否生病（如果有病一定能看出来），只是自己的狗不能看。
 观察后得到的结果不得交流，也不能通知病狗的主人。主人一旦推算出自己家的是病狗就要枪毙自己的狗（发现后必须在一天内枪毙），
 而且每个人只有权利枪毙自己的狗，没有权利打死其他人的狗。

 第一天大家全看完了，但枪没有响，第二天仍没有枪响。到了第三天传来一阵枪声，
 问村里共有几条病狗，如何推算得出？
 *
 * 1. 思考： 第一时间还没有想到直接求解的办法。先采用假设法，简化处理。呵呵。。
 * 假设有1只狗，第几天会有枪声。
 * 假设有2只狗，第几天会有枪声。
 * 。。。
 *
 * 2. 解法： 按照这个思路。将信息抽象为两个类。村庄和拥有狗的村民。
 *  村民
 *      拥有一支狗，且狗的状态不可变。
 *      有查看别人家狗的动作。
 *      有kill自己狗的动作。
 *      另外，还需要提供一个，供别人查看自己的狗状态的动作。
 *
 *  村庄（简化为村庄的村长来组织/发起动作。。村庄与村长奇妙的合体了。。。。哈哈、、）
 *      村庄有村民入住动作。
 *      村长组织村民 看狗。。。
 *      村长给村民一段时间，用来杀自己家狗。
 *
 * 3. 总结：
 *  如果按照这个解法来看，能够用到的技术主要是并发计算了。
 *  1）. 在每个人和每个人的动作互相独立，没有关联，可以完全解耦。可以并行。
 *  2）. 每个人在观察完所有狗（除了自己）之后就有了判断，可以决定杀还是不杀了。
 *  3）. 每天的事件也可以做到并行处理。但是会出现过度计算的问题。需要增加中断和撤销机制。
 *
 *  第一时间没有想到更好的解决方案。他日再优化吧。。。。
 *
 *
 * Created by zkai on 2015/1/21.
 */
public class Village {
    private final List<PetOwner> petOwners;

    /**
     *
     * @param petOwnerCount 村民数量
     * @param sickDogCount 病狗的数量
     */
    public Village(int petOwnerCount,int sickDogCount) {
        petOwners = Collections.unmodifiableList(init(petOwnerCount, sickDogCount));
    }

    /**
     * 村民入住
     * @param petOwnerCount 村民数量
     * @param sickDogCount 病狗的数量
     */
    private List<PetOwner> init(int petOwnerCount, int sickDogCount) {
        List<PetOwner> list = new ArrayList<>();
        for (int i = 0; i < petOwnerCount; i++) {
            PetOwner petOwner;
            if (i < sickDogCount) {
                // 拥有病狗的主人
                petOwner = new PetOwner(true);
            } else {
                // 拥有健康狗的主人
                petOwner = new PetOwner(false);
            }
            list.add(petOwner);
        }
        return list;
    }

    /**
     * 观察狗，并思考自己拥有的是否是病狗
     * @param day
     */
    public void watchDogAndThinkTime(int day) {
        for (PetOwner petOwner : petOwners) {
            petOwner.watchOtherDogAndThink(day, petOwners);
        }
    }

    /**
     * 杀狗时间。。。
     *
     * @return 是否有杀狗动作 true:有村民杀了狗。
     */
    public int killDogTime() {
        int killDogEventCount = 0;
        for (PetOwner petOwner : petOwners) {
            if (petOwner.killMyselfDog())
                killDogEventCount++;
        }
        return killDogEventCount;
    }

    public static void main(String[] args) {
        Village village = new Village(50, 10);// 假设一个村庄有50个村民，有3只病狗。

        for (int day = 1; day < 10000; day++) {// 防御式编程，day设置最大，防止编程错误导致无限循环。
            village.watchDogAndThinkTime(day);
            int killDogEventCount = village.killDogTime();
            if (killDogEventCount > 0) {
                System.out.format("今天是第%d天,发生了%s次枪击事件！！！", day, killDogEventCount);
                break;
            }
        }
    }
}
