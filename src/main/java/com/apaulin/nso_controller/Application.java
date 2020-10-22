package com.apaulin.nso_controller;

import com.apaulin.nso_controller.examples.ActionExample;
import com.apaulin.nso_controller.examples.ActionWithFormatExample;
import com.apaulin.nso_controller.examples.ActionWithParamsExample;
import com.apaulin.nso_controller.examples.CreateExample;
import com.apaulin.nso_controller.examples.CustomCallExample;
import com.apaulin.nso_controller.examples.DeleteExample;
import com.apaulin.nso_controller.examples.GetSchemaExample;
import com.apaulin.nso_controller.examples.GetValueExample;
import com.apaulin.nso_controller.examples.GettingListKeys;
import com.apaulin.nso_controller.examples.InitExample;
import com.apaulin.nso_controller.examples.PingExample;
import com.apaulin.nso_controller.examples.ShowRunExample;
import com.apaulin.nso_controller.examples.ShowRunMultiSessionExample;
import com.apaulin.nso_controller.examples.ShowRunWithOperExample;

@SuppressWarnings("unused")
public class Application {

    public static void main(String[] args) {
    	//new ShowRunExample();
    	//new ShowRunMultiSessionExample();
    	//new InitExample();
    	//new ShowRunExample();
    	//new ShowRunWithOperExample();
    	//new GetValueExample();
    	//new GettingListKeys();
    	//new DeleteExample();
    	//new ActionExample();
    	//new ActionWithFormatExample();
    	//new ActionWithParamsExample();
    	//new CustomCallExample();
    	//new GetSchemaExample();
    	new PingExample();
    	System.exit(0);
    }
}