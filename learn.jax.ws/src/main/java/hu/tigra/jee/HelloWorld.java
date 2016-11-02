package hu.tigra.jee;

import javax.jws.WebService;

@WebService(targetNamespace = "http://hello.world.ns/")
public interface HelloWorld {
    String sayHi(String text);

    String greetings(Person person);
}

