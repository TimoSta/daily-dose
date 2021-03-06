package com.timostaudinger.dailydose.scheduleEmails;

import com.timostaudinger.dailydose.common.model.dao.UserDAO;
import com.timostaudinger.dailydose.common.model.dto.User;
import com.timostaudinger.dailydose.scheduleEmails.mail.Mailer;
import com.timostaudinger.dailydose.common.model.dao.RedditDAO;
import com.timostaudinger.dailydose.common.model.dto.ImageSubmission;
import com.timostaudinger.dailydose.common.model.dto.SelfpostSubmission;
import com.timostaudinger.dailydose.scheduleEmails.render.RedditMailRenderer;
import com.timostaudinger.dailydose.common.model.dao.SubmissionMapper;
import com.timostaudinger.dailydose.common.util.Frequency;

import java.util.List;

public class EmailScheduler {
    private  static final Frequency FREQUENCY = Frequency.DAILY;
    private static final String SUBREDDIT = System.getenv("SUBREDDIT");
    private static final String MAIL_SENDER = System.getenv("MAIL_SENDER");
    private static final String MAIL_SUBJECT = System.getenv("MAIL_SUBJECT");

    private EmailScheduler() {}

    public static long scheduleEmails() {
        List<User> users = new UserDAO().findAllActive();

        ImageSubmission imageSubmission = SubmissionMapper.mapImageSubmission(RedditDAO.getTopImageOf(SUBREDDIT, FREQUENCY));
        SelfpostSubmission selfpostSubmission = SubmissionMapper.mapSelfpostSubmission(RedditDAO.getTopSelfPostOf(SUBREDDIT, FREQUENCY));

        String mailBody = new RedditMailRenderer(selfpostSubmission, imageSubmission).render();

        Mailer mailer = new Mailer(MAIL_SENDER);
        mailer.sendHtmlMail(MAIL_SUBJECT, mailBody, users);

        return users.size();
    }
}
