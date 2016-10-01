package com.yichao.hackernews;

public final class HackerNewsConstant {

    private HackerNewsConstant() {
    }

    public static final int MAX_STORIES = 50;

    public static final String HACKER_NEWS_BASE_URL = "https://hacker-news.firebaseio.com/v0";
    public static final String HACKER_NEWS_TOP_STORIES = "/topstories.json";
    public static final String HACKER_NEWS_NEW_STORIES = "/newstories.json";
    public static final String HACKER_NEWS_ITEM = "/item/%s.json";
    public static final String HACKER_NEWS_USER = "/user/%s.json";

    public static final String HACKER_NEWS_USER_PAGE = "https://news.ycombinator.com/user?id=";
}
