package com.test.huabanspider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class UserPageProcessor implements PageProcessor {

    private String userName;

    private Site site;

    public UserPageProcessor(String userName) {
        this(userName, 3, 100);
    }

    public UserPageProcessor(String userName, int retryTimes, int sleepTime) {
        this.userName = userName;
        this.site = Site.me().setRetryTimes(retryTimes).setSleepTime(sleepTime);
    }

    @Override
    public void process(Page page) {
        if (page.getUrl().toString().contains(userName)) {
            page.addTargetRequests(page.getHtml().css("div.Board>a.link", "href").all());
        } else if (page.getUrl().toString().contains("boards")) {
            page.addTargetRequests(page.getHtml().css("div.pin>a.img", "href").all());
        }

        if (page.getUrl().toString().contains("pins")) {
            page.putField("img", page.getHtml().css("div.image-holder img", "src"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}