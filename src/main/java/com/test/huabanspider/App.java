package com.test.huabanspider;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Spider;

import com.test.huabanspider.util.SeleniumDownloader;

public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream("config.properties");
            prop.load(is);
        } catch (Exception e) {
            logger.error("Load config error", e);
            return;
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                logger.error("Close inputStream error", e);
            }
        }

        String userName = prop.getProperty("huaban.userName");
        String downloadPath = prop.getProperty("huaban.downloadPath");
        int threadNum = Integer.valueOf(prop.getProperty("huaban.threadNum"));

        SeleniumDownloader downloader = new SeleniumDownloader("lib/chromedriver.exe");
        downloader.setThread(threadNum);

        Spider.create(new UserPageProcessor(userName)).thread(threadNum).addUrl("http://huaban.com/" + userName + "/")
                .addPipeline(new DownloadPipeline(downloadPath)).setDownloader(downloader).run();
    }
}