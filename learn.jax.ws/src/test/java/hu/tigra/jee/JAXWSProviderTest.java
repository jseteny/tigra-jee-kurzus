package hu.tigra.jee;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.ws.spi.Provider;

public class JAXWSProviderTest {

    @Test
    public void test() {
        Provider p = Provider.provider();
        Logger.getLogger(this.getClass()).warn("Current JAXWS Provider: " + p);
        Assert.assertTrue(p.getClass().getName().startsWith("org.jboss."));
    }
}
