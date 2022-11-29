package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(lifeCycleConfig.class);
        System.out.println("ac");

        NetworkClient client = ac.getBean(NetworkClient.class);
        System.out.println("ac.getBean");

        ac.close();
        System.out.println("ac.close()");
    }

    @Configuration
    static class lifeCycleConfig {

        @Bean  // (initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            System.out.println("lifeCycleConfig.networkClient");
            NetworkClient networkClient = new NetworkClient();
            System.out.println("new NetworkClient()");
            networkClient.setUrl("http://hello-spring.dev");
            System.out.println("setUrl");
            return networkClient;
        }
    }
}
