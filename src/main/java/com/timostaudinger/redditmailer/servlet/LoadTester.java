package com.timostaudinger.redditmailer.servlet;

import com.timostaudinger.redditmailer.util.Subreddit;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loadtester", urlPatterns = {"/test/load"})
public class LoadTester extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SubredditPaginator getMotivated = Subreddit.getPaginator("getmotivated", 10, TimePeriod.ALL, Sorting.TOP);

        for (Submission submission : getMotivated.next()) {
            response.getWriter().println(submission.getTitle());
        }

    }
}
