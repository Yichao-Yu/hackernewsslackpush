# HackerNews Slack Push

This is a service intended for periodically pushing latest stories from HackerNews to a slack channel.

The service is deployed to Heroku platform. An EasyCron job is also scheduled to ping the service in order to keep the Heroku instance live.

Slack Webhook url for Incoming Webhook integration is configured in an 'application.secret.properties' file, which is ignored.

References:  
1. https://www.easycron.com  
2. https://api.slack.com/incoming-webhooks  
3. https://www.heroku.com/pricing  
4. https://hacker-news.firebaseio.com/v0/  

