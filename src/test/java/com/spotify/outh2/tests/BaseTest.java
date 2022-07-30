package com.spotify.outh2.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforMethod(Method m)
    {
        System.out.println("Staring Test : "+m.getName());
        System.out.println("Thread ID : "+Thread.currentThread().getId());

    }


}
