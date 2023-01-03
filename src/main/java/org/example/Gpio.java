package org.example;

import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.platform.Platforms;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Gpio {

    public static long distance, start, end;

    public void gpio() throws InterruptedException {

        var pi4j = Pi4J.newAutoContext();

        var output = pi4j.dout().create(1);
        output.config().setShutdownState(DigitalState.HIGH);

        output.state(DigitalState.LOW);
        output.state(DigitalState.HIGH);
        output.state(DigitalState.LOW);

        Properties properties = new Properties();
        properties.put("id", "input");
        properties.put("address", 29);
        properties.put("pull", "UP");
        properties.put("name", "SONIC-INPUT");

        var config = DigitalInput.newConfigBuilder(pi4j)
                .load(properties)
                .build();

        var input = pi4j.din().create(config);

        input.addListener(e -> {
            if (e.state() == DigitalState.HIGH) {
                end = System.currentTimeMillis();
                distance = (end - start) / 58;
                System.out.println("Distance " + distance + " cm");
            }
            if (e.state() == DigitalState.LOW) {
                start = System.currentTimeMillis();
            }
        });

        for(;;) {
          Thread.sleep(1000);
        }

        //pi4j.shutdown();
    }

    public void gpioButton() throws InterruptedException {

        var pi4j = Pi4J.newAutoContext();

        long DEBOUNC = 10_000;

        DigitalInputConfig input = DigitalInput.newConfigBuilder(pi4j).id("BCM26")
                .name("Button")
                .address(26)
                .debounce(DEBOUNC).pull(PullResistance.PULL_DOWN)
                .build();

        final var button = pi4j.create(input);

        button.addListener(digitalStateChangeEvent -> {
            DigitalState state = button.state();

            System.out.println("Button is " + state + ".");

        });

    }

}
