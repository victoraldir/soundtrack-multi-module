package com.devquartzo.crawler.component;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class MyController {

    private CrawlController controller;

    protected CrawlConfig createCrawlConfig(){
        String crawlStorageFolder = "data/crawl/root";

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        return  config;
    }

    protected void createCrawlController() throws Exception {

        int crawlDepth = 2;

        /*
         * Instantiate the controller for this crawl.
         */
        CrawlConfig config = createCrawlConfig();

        config.setMaxDepthOfCrawling(crawlDepth);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        controller = new CrawlController(config, pageFetcher, robotstxtServer);

    }

    public void execute(List<URL> urlList) throws Exception {

        int numberOfCrawlers = 7;
        createCrawlController();


        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */

        urlList.forEach(r -> controller.addSeed(r.toString()));

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }

    public void stopExecution(){
        controller.shutdown();
    }

    public boolean isShuttingDown(){
        return controller.isShuttingDown();
    }
}
