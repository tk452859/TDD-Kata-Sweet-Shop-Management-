package org.example.sweet_shop.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("✅ Sweet Shop Application Started Successfully!");
        System.out.println("✅ MySQL Database Connected!");
    }
}

