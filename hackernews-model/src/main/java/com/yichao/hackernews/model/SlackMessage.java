package com.yichao.hackernews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SlackMessage {

    private String text;

    private List<SlackMessageAttachment> attachments = new ArrayList<>();

    @JsonProperty("unfurl_links")
    private Boolean unfurlLink = false;

    @JsonProperty("unfurl_media")
    private Boolean unfurMedia = false;

    public SlackMessage() {
    }

    public SlackMessage(String text, SlackMessageAttachment attachment) {
        this.text = text;
        attachments.add(attachment);
    }

    public SlackMessage(String text, SlackMessageAttachment attachment, Boolean unfurlLink, Boolean unfurMedia) {
        this.text = text;
        attachments.add(attachment);
        this.unfurlLink = unfurlLink;
        this.unfurMedia = unfurMedia;
    }

    public String getText() {
        return text;
    }

    public List<SlackMessageAttachment> getAttachments() {
        return attachments;
    }

    public Boolean getUnfurlLink() {
        return unfurlLink;
    }

    public Boolean getUnfurMedia() {
        return unfurMedia;
    }

    public static class SlackMessageAttachment {

        private String fallback;

        private final String color = "warning";

        @JsonProperty("author_name")
        private String authorName;

        @JsonProperty("author_link")
        private String authorLink;

        @JsonProperty("author_icon")
        private String authorIcon = "";

        private String title;

        @JsonProperty("title_link")
        private String titleLink;

        private final String footer = "HackerNews Slack API";

        @JsonProperty("ts")
        private Long timestamp;

        public SlackMessageAttachment() {
        }

        public SlackMessageAttachment(String fallback, String authorName, String authorLink, String authorIcon, String title, String titleLink, Long timestamp) {
            this.fallback = fallback;
            this.authorName = authorName;
            this.authorLink = authorLink;
            this.authorIcon = authorIcon;
            this.title = title;
            this.titleLink = titleLink;
            this.timestamp = timestamp;
        }

        public String getFallback() {
            return fallback;
        }

        public String getColor() {
            return color;
        }

        public String getAuthorName() {
            return authorName;
        }

        public String getAuthorLink() {
            return authorLink;
        }

        public String getAuthorIcon() {
            return authorIcon;
        }

        public String getTitle() {
            return title;
        }

        public String getTitleLink() {
            return titleLink;
        }

        public String getFooter() {
            return footer;
        }

        public Long getTimestamp() {
            return timestamp;
        }
    }
}
