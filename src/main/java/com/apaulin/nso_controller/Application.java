package com.apaulin.nso_controller;

import com.apaulin.nso_controller.examples.ActionExample;
import com.apaulin.nso_controller.examples.CreateExample;
import com.apaulin.nso_controller.examples.DeleteExample;
import com.apaulin.nso_controller.examples.GetSchemaExample;
import com.apaulin.nso_controller.examples.GettingListKeys;
import com.apaulin.nso_controller.examples.ShowRunExample;
import com.apaulin.nso_controller.examples.ShowRunMultiSessionExample;

public class Application {

    public static void main(String[] args) {
    	System.out.println("Hello nso_controller");
    	new ShowRunExample();
    	new ShowRunMultiSessionExample();
    	new CreateExample();
    	new GettingListKeys();
    	new DeleteExample();
    	new ActionExample();
    	new GetSchemaExample();
    }
}