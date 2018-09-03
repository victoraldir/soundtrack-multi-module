package com.devquartzo.crawler.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MyCrawlerTest {

    @Autowired
    private MyController myController;

    @Test
    public void shouldCrawSomethingStandAlone() throws Exception {

        List<URL> urlList = new ArrayList<>();

        urlList.add(new URL("http://www.portaltransparencia.gov.br/"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myController.execute(urlList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Thread.sleep(15000);

        myController.stopExecution();

        while (!myController.isShuttingDown()){
            Thread.sleep(5000);
            System.out.println("Still waiting");
        }

    }

}