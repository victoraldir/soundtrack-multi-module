package com.devquartzo.crawler.component;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageCrawlController {

    private static final Logger logger = LoggerFactory.getLogger(ImageCrawlController.class);

    public static void main(String[] args) throws Exception {

        String rootFolder = "data/rootFolder";
        int numberOfCrawlers = 7;
        String storageFolder = "storage/folder";
        String[] crawlDomains = {"https://www.whatstube.com.br/categoria/imagens/"};

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(rootFolder);

        /*
         * Since images are binary content, we need to set this parameter to
         * true to make sure they are included in the crawl.
         */
        config.setIncludeBinaryContentInCrawling(true);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        for (String domain : crawlDomains) {
            controller.addSeed(domain);
        }

        ImageCrawler.configure(crawlDomains, storageFolder);

        controller.start(ImageCrawler.class, numberOfCrawlers);
    }

}
