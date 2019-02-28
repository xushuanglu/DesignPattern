package com.designpattern.adapter.objectaceadapter;

import com.designpattern.adapter.classadapter.Voltage220;
import com.designpattern.adapter.classadapter.Voltage5;

/**
 * 介绍：对象适配器模式：
 * 持有 src类，实现 dst 类接口，完成src->dst的适配。 。以达到解决**兼容性**的问题。
 *     基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承src类，而是持有src类的实例，以解决兼容性的问题。 
        即：持有 src类，实现 dst 类接口，完成src->dst的适配。 
	（根据“合成复用原则”，在系统中尽量使用关联关系来替代继承关系，因此大部分结构型模式都是对象结构型模式。） 
 * @author Administrator
 */
public class VoltageAdapter2 implements Voltage5 {
	
    private Voltage220 mVoltage220;

    public VoltageAdapter2(Voltage220 voltage220) {
        mVoltage220 = voltage220;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != mVoltage220) {
            int src = mVoltage220.output220V();
            System.out.println("对象适配器工作，开始适配电压");
            dst = src / 44;
            System.out.println("适配完成后输出电压：" + dst);
        }
        return dst;
    }
}
