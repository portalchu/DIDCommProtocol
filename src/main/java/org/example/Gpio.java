package org.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Gpio {

    public static int distance = 0, start = 0, end = 0;
    public static boolean startCheck = false, endCheck = false;
    public static boolean touched = false;

    public DigitalOutput sonicOutput;
    public DigitalInput sonicInput;
    public Context sonicPi4j;

    public static Instant timeSC = Instant.now().truncatedTo(ChronoUnit.MICROS);

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

        /*
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

        /*
        for(;;) {
          Thread.sleep(1000);
        }

         */

        //pi4j.shutdown();
    }

    public static void gpioButton() throws InterruptedException {

        var pi4j = Pi4J.newAutoContext();

        Console console = new Console();
        Platforms platforms = pi4j.platforms();
        console.box("Pi4J PLATFORMS");
        console.println();
        platforms.describe().print(System.out);
        console.println();

        long DEBOUNC = 3000L;

        DigitalInputConfig input = DigitalInput.newConfigBuilder(pi4j).id("BCM26")
                .name("Button")
                .address(24)
                .debounce(DEBOUNC)
                .pull(PullResistance.PULL_DOWN)
                .provider("pigpio-digital-input")
                .build();

        final var button = pi4j.create(input);

        button.addListener(e -> {
            if (e.state() == DigitalState.HIGH) {
                console.println("Button Down!!");
                touched = true;
            }
            if (e.state() == DigitalState.LOW)
            {
                console.println("Button No Down!!");
                touched = false;
            }
        });

        /*
        while (true)
        {
            Thread.sleep(500);
        }

         */
    }

    public void gpioSonicSetup() throws InterruptedException {

        sonicPi4j = Pi4J.newAutoContext();

        Console console = new Console();
        Platforms platforms = sonicPi4j.platforms();
        console.box("Pi4J PLATFORMS");
        console.println();
        platforms.describe().print(System.out);
        console.println();

        long DEBOUNC = 3000L;

        DigitalOutputConfig output = DigitalOutput.newConfigBuilder(sonicPi4j).id("SonicO")
                .name("SonicOut")
                .address(23)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output")
                .build();

        sonicOutput = sonicPi4j.create(output);

        DigitalInputConfig input = DigitalInput.newConfigBuilder(sonicPi4j).id("SonicI")
                .name("Button")
                .address(24)
                .debounce(DEBOUNC)
                .pull(PullResistance.PULL_DOWN)
                .provider("pigpio-digital-input")
                .build();

        sonicInput= sonicPi4j.create(input);

        sonicInput.addListener(e -> {
                    if (e.state() == DigitalState.HIGH) {
                        timeSC = Instant.now().truncatedTo(ChronoUnit.MICROS);
                        start = timeSC.getNano() / 1000;
                        console.println("time Check1 : start is " + start);
                    }
                    if (e.state() == DigitalState.LOW) {
                        timeSC = Instant.now().truncatedTo(ChronoUnit.MICROS);
                        end = timeSC.getNano() / 1000;
                        console.println("time Check2 : end is " + end);
                        distance = (end - start) / 58;
                        System.out.println("Distance " + distance + " cm");
                        startCheck = false;
                    }
                });

        /*
        while (true)
        {
            if (startCheck == false && endCheck == false)
            {
                System.out.println("Sonic Start");
                sonicOutput.low();
                Thread.sleep(2);
                sonicOutput.high();
                Thread.sleep(10);
                sonicOutput.low();
                startCheck = true;
            }
            Thread.sleep(1000);
        }

         */

    }

    public void gpioSonicCheck() throws InterruptedException {
        System.out.println("Sonic Start");
        sonicOutput.low();
        Thread.sleep(2);
        sonicOutput.high();
        Thread.sleep(10);
        sonicOutput.low();
        startCheck = true;
    }
        /*
        while (true)
        {
            Thread.sleep(500);
        }

         */
}

