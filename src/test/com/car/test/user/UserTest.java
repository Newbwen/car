package com.car.test.user;

import org.testng.annotations.Test;

/**
 * Created by YJH on 2016/3/7.
 */
public class UserTest {

    @Test
    public void test(){
        Boolean result = true;
        if(!result){
            System.out.println("是真");
        }else{
            System.out.println("不是真");
        }
    }
}
