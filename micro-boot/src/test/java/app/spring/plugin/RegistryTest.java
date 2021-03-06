package app.spring.plugin;

import com.oath.micro.server.MicroserverApp;
import com.oath.micro.server.boot.MicroBoot;
import com.oath.micro.server.config.Microserver;
import com.oath.micro.server.rest.client.nio.AsyncRestClient;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.ExecutionException;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@Microserver
@MicroBoot
public class RegistryTest {


    AsyncRestClient rest = new AsyncRestClient(1000,1000).withAccept("text/plain");

    @Test
    public void runAppAndBasicTest() throws InterruptedException, ExecutionException {

        MicroserverApp app = new MicroserverApp( ()-> "spring-mvc");
        Thread.sleep(3000);

        assertThat(rest.get("http://localhost:8080/spring-mvc/scheduled").get(),is(not("0")));

        ((ConfigurableApplicationContext)app.getSpringContext()).close();

    }


}
