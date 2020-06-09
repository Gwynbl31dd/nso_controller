# NSO_Controller

nso_controller is a NSO java API.

This library can be used as a java API, Katalon library, or as a Keyword library for Robot framework.

## Documentation

All the documentation can be found in the java doc in the release section

## Java Usage

nso_controller can be used as a Java API to communicate with NSO.

[RELEASE](https://github.com/Gwynbl31dd/nso_controller/releases)

## Installation

###  Leiningen/Boot

```
[org.clojars.gwynbl31dd/nso_controller "4.0.1"]
```

### Clojure CLI/deps.edn

```
org.clojars.gwynbl31dd/nso_controller {:mvn/version "4.0.1"}
```

### Gradle

```
compile 'org.clojars.gwynbl31dd:nso_controller:4.0.1'
```

### Maven

```
<dependency>
  <groupId>org.clojars.gwynbl31dd</groupId>
  <artifactId>nso_controller</artifactId>
  <version>4.0.1</version>
</dependency>
```

## Reading data

nso_controller provides two main functionality to write a on NSO,

you can pass a file (xml or json) or directly write in the leaf.


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
You can also you nso.delete() to remove them.

Since the version 1.3.2 you do not need to valide the commit before running it. 
BUT if you create your own commit object, nso.validateCommit() must be called.
Since the version 1.3.3 you do not need to start a transaction. The transaction will be automatically started on the
running database, with the option read_right. Using startTransaction() is only required if you want to modify the options.

This is due to JRPC, JRPC expecte a validation. But NSOController will take care of it if you 
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

In some case, you will need to call the HTTPRequest object provides by NSOController.

This object can take another object as a function to expose the advanced NSO functionality.
This can be useful if you need a specific method not directly exposed.

This object can be called by getReq(),and will return a HTTPRequest.

This HTTPRequest has a method called postRequest(RPCRequest rpcRequest).

Therefore, you can create a object inherited from RPCRequest, and passing it to 
to your "http request" to create your own request.

```
WARNING : postRequest() does not mean it is a request REST POST. it is simply "posting" a RPC request.
```

Xplorer's library already provide a suite of Object usable in com.apaulin.http.rpc

For example, the object ShowConfig. ShowConfig takes two arguments.

The transaction number (th) (Provided by NSOController)  and the xpath to NSO.

```java
import com.apaulin.nso_controller.http.HttpRequest;
import com.apaulin.nso_controller.http.rpc.ShowConfig;
import com.apaulin.nso_controller.NSOController;

public class Main {
	final static String USER = "anthony";
	final static String PASSWORD = "password123";
	final static String ADDRESS = "http://127.0.0.1:9701";
	public static void main(String[] args) {
		NSOController nso = null;
		try {
			//Create a NSOController object
			nso = new NSOController(ADDRESS,USER,PASSWORD);
			//Start the transation (This is done by default if missing)
			//nso.startTransaction();
			
			//You get the transaction id previously created.
			//You could keep them open and using multiple transaction
			int th = nso.getTransactionId();
			
			/* Take the http object from nso controller
			 * This object is used to send the data
			 * Then you create an object extends RPCRequest.
			 * You can find a list of object in the package
			 * com.apaulin.http.rpc
			 */
			HttpRequest req = nso.getReq();
			String myResult = req.postRequest(new ShowConfig(th,"/aaa"));
			System.out.println(myResult);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Logout if nothing else need to be done
			nso.logout();
		}
	}
}
```

This is the equivalent of  using nso.showConfig("/aaa");

## Commit Options usage 

NSO controller can pass any options to your commit.

By default, there are a few method available like
commitDryRunCli(...); 
commitDryRunNative(...);

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

## Extra

nso_controller provides a lot of functions to validate, create, delete, test NSO and the device.

For example, lifeStatus can allow you to directly talk to the device through NSO.

if the class NSOController does not provide a functionality, take a look at 
the advanced function provided in com.apaulin.nso_controller.http.rpc

If you still do not find a function, extends the object in com.apaulin.http.rpc

and if you still need help, contact me : paulin.anthony@gmail.com
