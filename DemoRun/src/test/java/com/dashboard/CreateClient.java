package com.dashboard;

import java.io.IOException;

import org.testng.annotations.Test;

import com.util.Base;

public class CreateClient extends Base {

    public CreateClient() throws IllegalArgumentException, IOException {
        super();
    }

    @Test
    public void newuser()
    {
        System.out.println("new user is created: "+Thread.currentThread().getId());
    }
}
