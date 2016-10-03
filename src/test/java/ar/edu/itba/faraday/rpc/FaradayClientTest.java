package ar.edu.itba.faraday.rpc;

import org.junit.Assert;
import org.junit.Test;

public class FaradayClientTest {

    @Test
    public void logIntoDevelopmentLog() {
        FaradayClient faradayClient = new FaradayClient("http://localhost:9876/");

        faradayClient.devlog("SuperMegaTest");
    }

    @Test
    public void addHostAndReturnId() {
        FaradayClient faradayClient = new FaradayClient("http://localhost:9876/");

        String hostId = faradayClient.createAndAddHost("0.0.0.0", "MacOSX", "", "", "");

        Assert.assertEquals(40, hostId.length());
    }
}
