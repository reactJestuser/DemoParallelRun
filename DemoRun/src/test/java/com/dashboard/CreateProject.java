package com.dashboard;

import java.io.IOException;

import org.testng.annotations.Test;

import com.util.Base;

public class CreateProject extends Base {

    public CreateProject() throws IllegalArgumentException, IOException {
        super();
        // TODO Auto-generated constructor stub
    }
    @Test
    public void newproduct()
    {
        System.out.println("new product is created "+Thread.currentThread().getId());
    }
}
