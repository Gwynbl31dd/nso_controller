[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.gwynbl31dd/nso_controller.svg)](https://clojars.org/org.clojars.gwynbl31dd/nso_controller)

<a href="https://cxtools.cisco.com/cxestore/#/toolDetail/77588"><img alt="CX eStore Tool ID" src="https://img.shields.io/badge/TOOL%20ID-77588-blue"></a>

# NSO_Controller

nso_controller is a NSO java API.

This library can be used as a java API, Katalon library, or as a Keyword library for Robot framework.

You can find a Robot framework implementation [here](https://github.com/Gwynbl31dd/project_testsuite)

## Documentation

All the documentation can be found in the java doc in the release section

## Java Usage

nso_controller can be used as a Java API to communicate with NSO.

[RELEASE](https://github.com/Gwynbl31dd/nso_controller/releases)

## Installation

###  Leiningen/Boot

```
[org.clojars.gwynbl31dd/nso_controller "4.3.0"]
```

### Clojure CLI/deps.edn

```
org.clojars.gwynbl31dd/nso_controller {:mvn/version "4.3.0"}
```

### Gradle

```
compile 'org.clojars.gwynbl31dd:nso_controller:4.3.0'
```

### Maven

```
<repositories>
  <repository>
    <id>clojars</id>
      <name>Clojars repository</name>
      <url>https://clojars.org/repo</url>
  </repository>
</repositories>
```

```
<dependency>
  <groupId>org.clojars.gwynbl31dd</groupId>
  <artifactId>nso_controller</artifactId>
  <version>LATEST</version>
</dependency>
```

## Reading data

nso_controller provides two main functionalities to read NSO,

you can pass a file (xml or json) or directly read from a leaf.


### Reading data from NSO default method

nso_controller provides an easy way of reading the NSO data in 4 steps.

* Create an instance of the NSOController.
* Start a transaction.
* Read the data.
* Close the connection.

```java
import com.apaulin.nso_controller.NSOController;

public class Main {
	//NSO credentials
	final static String USER = "anthony";
	final static String PASSWORD = "password123";
	//NSO HTTP port
	final static String ADDRESS = "http://127.0.0.1:9701";
	
	public static void main(String[] args) {
		NSOController nso = null;
		try {
			nso = new NSOController(ADDRESS,USER,PASSWORD);
			nso.startTransaction();//This is optional, a transaction is started if you miss it
			//Read
			String aaa = nso.showConfig("/aaa");
			System.out.println(aaa);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();//Make sure you logout
		}
	}
}

```

### Reading data from NSO using REST

```
WARNING : REST is deprecated for NSO 5.X.X, therefore we will also deprecate the REST functionality for the next versions.
```

In some special case, you could be forced to use the REST API (For example, if you test a system using REST)

In that case, you will use 3 steps.

* Create an instance of the NSOController.
* Read the data.
* Close the connection.

In this case, you do not need to start a transaction because REST does not require an open 
session.
But you still need to close the connection, because NSOController uses JRPC for its internal mechanism.

```java
import com.apaulin.nso_controller.NSOController;

public class Main {
	final static String USER = "anthony";
	final static String PASSWORD = "password123";
	final static String ADDRESS = "http://127.0.0.1:9701";
	
	public static void main(String[] args) {
		NSOController nso = null;
		try {
			nso = new NSOController(ADDRESS,USER,PASSWORD);
			String aaa = nso.restGet("/config/aaa");
			System.out.println(aaa);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();
		}
	}
}
```

## Writing data

### Create a leaf

nso.create() does not only create a leaf, but also the data in it
You can also use nso.delete() to remove them.

The transaction will be automatically started on the
running database, with the option read_write. Using startTransaction() is only required if you want to modify the options.

This is due to JRPC, JRPC expects a validation. But NSOController will take care of it if you 
did not.

NOTE : after a successful commit, you need to create a new transaction.

```java
import com.apaulin.nso_controller.NSOController;

public class Main {
	final static String USER = "anthony";
	final static String PASSWORD = "password123";
	final static String ADDRESS = "http://127.0.0.1:9701";
	
	public static void main(String[] args) {
		NSOController nso = null;
		try {
			nso = new NSOController(ADDRESS,USER,PASSWORD);
			nso.startTransaction();//optional, this will be generated in read_right by default
			nso.create("/network/infrastructure/edge/NCPF5Service{Londsdale TID_DNS tier-2}/ltm-pool/pool-list{TID_DNS-28May-pool}/monitors/user-defined-monitor-list{TID_DNS-Name-adv_external-monitor}");
			nso.commit();
			nso.startTransaction();
			nso.delete("/network/infrastructure/edge/NCPF5Service{Londsdale TID_DNS tier-2}/ltm-pool/pool-list{TID_DNS-28May-pool}/monitors/user-defined-monitor-list{TID_DNS-Name-adv_external-monitor}");
			nso.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();
		}
	}
}

```

### Load a file

NSOController can also load a file. you can choose to replace,merge or create. (Equivalent of POST, PATCH and PUT for REST).

```java
import com.apaulin.nso_controller.NSOController;

public class Main {
	final static String USER = "anthony";
	final static String PASSWORD = "password123";
	final static String ADDRESS = "http://127.0.0.1:9701";
	
	public static void main(String[] args) {
		NSOController nso = null;
		try {
			nso = new NSOController(ADDRESS,USER,PASSWORD);
			nso.startTransaction();
			nso.Load("/network/infrastructure/edge", "/var/lol/resources/NCP_F5/TC00.json", "replace", "json"));
			nso.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();
		}
	}
}
```

## Advance usage (Custom RPC call)

In some case, you could need a custom call. For example if the call is not exposed from nso_controller or if your version of NSO does not support what nso_controller sends.

In any case, nso_controller allows you to use its session manager. 
In order to create a custom call, you will need to follow these 4 steps.

* Step 1 : Create a class that extends RPCData
* Step 2 : Build the NSO controller object and start the authentication
* Step 3 : Generate the id and the transaction (th) by calling startTransaction
* Step 4 : Post your custom call to the session manager

```java
import com.apaulin.nso_controller.http.rpc.RCPparameterException;
import com.apaulin.nso_controller.http.rpc.RPCException;
import com.apaulin.nso_controller.http.rpc.RpcData;
import com.apaulin.nso_controller.NSOController;
import com.apaulin.nso_controller.exception.NSOException;

public class CustomCallExample {
	private static String url = "http://127.0.0.1:8080";
	private static String username = "admin";
	private static String password = "cisco123";
	
	public CustomCallExample() {
		NSOController nso = null;
		try {
			
			// Step 2 : Build the NSO controller object and start the authentication
			nso = new NSOController(url,username,password);
			
			// Step 3 : Generate the id and the transaction (th) by calling startTransaction
			//Create a transaction
			int th = nso.startTransaction("running", "read", "private", "test", "reuse");
			// Get the generated ID
			int id = nso.getSessionManager().getCurrentId();
			
			// Step 4 : Post your custom call to the session manager
			// We will create a show config with oper state to true
			MyRPCCustomCall call = new MyRPCCustomCall("show_config");
			// Set the generate ID
			call.setId(id);		
			// Build the request string, do not forget the transaction
			call.setRequest("{\"th\": " + th + ",\"path\":\"/devices\",\"result_as\": \"json\",\"with_oper\": true}");
			// Just push that object to the session manager and send it.
			String result = nso.getSessionManager().getCurrentReq().send(call);
			// And that's it.
			System.out.println(result);
			
		} catch (NSOException | RPCException | RCPparameterException e) {
			e.printStackTrace();
		} 
		finally {
			nso.logout();
		}
	}
	
}

// Step 1 : Create a class that will extend the abstract class RPCData.
class MyRPCCustomCall extends RpcData {
	/* 
	 * Just specify the method. Example "show_config"
	 * This is defined from the web_ui documentation in NSO
	 */
	public MyRPCCustomCall(String method) {
		super(method);
	}
}

```

This is the equivalent of  using nso.showRun("/devices");

## Commit Options usage 

NSO controller can pass any options to your commit.

By default, there are a few methods available like

* commitDryRunCli(...); 
* commitDryRunNative(...);
* ...

If you need specific call, you can use the option (E.g : Force LSA, commit queue, etc)

```java
public class Main {
	
	final static String USER = "anthony";
	final static String PASSWORD = "password123";
	final static String ADDRESS = "https://localhost:443";
	
	public static void main(String[] args) {
		NSOController nso = null;
		try {
			//Create the NSO controller object
			nso = new NSOController(ADDRESS,USER,PASSWORD);
			//Load the payload to NSO
			nso.load("/network/infrastructure/edge", "/tmp/payload.json");
			//Show the output, here we use timeout 0 as nso 4.7 changed it's behavior 
			System.out.println(nso.commitDryRunCli(0));
	
			//Create the commit option object
			CommitOptions options = new CommitOptions();
			//Add the options to the object.
			//Add force LSA
			options.addUseLSA();
			//Add bypass for the commit queue
			options.addCommitQueue("bypass");
			//Don't forget to validate your commit !
			nso.validateCommit();
			//Get the transaction in progress
			int th = nso.getTransactionId();
			//Here we go, you can commit your changes
			String result = nso.getReq().postRequest(new Commit(th,options, 0));
			System.out.println(result);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			nso.logout();
		}
	}
}
```

## Robot example

nso_controller can be used as a powerful keyword library by adding it to Robot framework.

Here is an example :

```robot
*** Setting ***
Documentation     
...    Test of the new NSO library  

Metadata    Version            0.1
Metadata    Script status      Example

Library   com.apaulin.nso_controller.NSOController
Library   OperatingSystem

*** Test Cases ***

Initialise
    Init   http://10.120.18.99:28080  robot  robot
    Start Transaction  running  read_write  private  test  reuse
    Is Existing  /aaa
    ${config}   Show config   /aaa
    Log to Console  ${config}

Rest API Requests
    # Note that the request does not take "/api/", this is already added during the call
    # You just need to add /<database>/<path>
    ${config}  Rest Get    /config/devices/device/s3cw-e-501
    Log to Console  ${config}
    ${interfaces}   OperatingSystem.Get File  resources/l3vpn_config/s3cw-e-501-interface.json
    Rest Post  /config/network/infrastructure   ${interfaces}
    ${config}  Rest Get    /config/network/infrastructure
    Log to Console  ${config}
```

You can find more examples [here](https://github.com/Gwynbl31dd/project_testsuite)

## License

This project is covered under the terms described in [LICENSE](./LICENSE)

## Maintainers

* [Gwynbl31dd](https://github.com/Gwynbl31dd)
* [smansor84](https://github.com/smansor84)
