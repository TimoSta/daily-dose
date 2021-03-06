package com.timostaudinger.dailydose.scheduleEmails.render;

import com.timostaudinger.dailydose.common.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.common.model.dto.SelfpostSubmission;

import java.util.HashMap;
import java.util.Map;

public class RedditMailRenderer {
    private static final String IMAGE_TITLE = "imageTitle";
    private static final String IMAGE_URL = "imageUrl";
    private static final String IMAGE_IMAGE_URL = "imageImageUrl";
    private static final String QUOTE_TITLE = "quoteTitle";
    private static final String QUOTE_URL = "quoteUrl";
    private static final String QUOTE_CONTENT = "quoteContent";

    private static final String TEMPLATE_NAME = "dailydose.vm";

    private MailRenderer mailRenderer;

    private ImageSubmission imageSubmission;
    private SelfpostSubmission selfpostSubmission;

    public RedditMailRenderer(SelfpostSubmission selfpostSubmission, ImageSubmission imageSubmission) {
        this.selfpostSubmission = selfpostSubmission;
        this.imageSubmission = imageSubmission;
    }

    public String render() {
        return getMailRenderer().renderMail(TEMPLATE_NAME);
    }

    private MailRenderer getMailRenderer() {
        if (mailRenderer == null) {
            mailRenderer = new MailRenderer(mapFields());
        }

        return mailRenderer;
    }

    private Map<String, String> mapFields() {
        Map<String, String> content = new HashMap<>();

        content.put(IMAGE_TITLE, imageSubmission.getTitle());
        content.put(IMAGE_URL, imageSubmission.getUrl());
        content.put(IMAGE_IMAGE_URL, imageSubmission.getImageUrl());
        content.put(QUOTE_TITLE, selfpostSubmission.getTitle());
        content.put(QUOTE_URL, selfpostSubmission.getUrl());
        content.put(QUOTE_CONTENT, selfpostSubmission.getContent());

        return content;
    }
}

