package com.dudo.dailytest;

import java.util.List;

/**
 * Created by zkai on 2015/1/21.
 */
public class PetOwner {
    private final boolean isSickDog;
    private boolean consideredOwnerSickDog;

    public PetOwner(boolean isSickDog) {
        this.isSickDog = isSickDog;
    }

    /**
     * @param day 第几天
     * @return 看到病狗的数量
     */
    public boolean watchOtherDogAndThink(int day, List<PetOwner> petOwners) {
        int count = 0;
        for (PetOwner petOwner : petOwners) {
            if (petOwner != this && petOwner.dogStatus(this)) {
                count++;
            }
        }
        // 此处是最关键的。day 与 count 的关系。
        consideredOwnerSickDog = day - count == 1; // 看到病狗的数量，和日期的关系。
        return consideredOwnerSickDog;
    }

    /**
     *
     * @return 是否枪杀了自己的狗。true：杀了，false：没杀
     */
    public boolean killMyselfDog() {
        if (consideredOwnerSickDog) {
            System.out.println("▄︻┳═一   枪杀自己的狗~~~ 嘭~~~");
        }
        return consideredOwnerSickDog;
    }

    /**
     * 查看狗的状态，必须是非自己
     * @param watcher
     * @return
     */
    public boolean dogStatus(PetOwner watcher) {
        if (watcher == null || watcher == this) {
            throw new IllegalStateException("自己不能看自己的狗");
        }
        return isSickDog;
    }
}
