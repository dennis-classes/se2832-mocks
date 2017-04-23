import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {
    private Twitter twitter;

    public TwitterClient() {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setDebugEnabled(true)
                .setOAuthConsumerKey("GewJnIkJI46iwny7z1cTbD8i4")
                .setOAuthConsumerSecret("76sEaQVHFiMldcoV8PUnTLFvXBQnptSnLL7Yer4iTktbprpfZF")
                .setOAuthAccessToken("65276150-vFliVJlnLeE1TUPqKiUm0qAlmiK5oY27uqgL65qhs")
                .setOAuthAccessTokenSecret("KuEHELCdC6kwRarsJRzah6oorJLV1zodN8uXkE3j6RU6b");
        twitter = new TwitterFactory(config.build()).getInstance();
    }

    public TwitterClient(Twitter twitter) {
        this.twitter = twitter;
    }

    public String getUser(String username) throws TwitterException {
        String userText = "";
        User user = twitter.showUser(username);

        if (user.getStatus() != null) {
            userText = "@" + user.getScreenName() + " - " + user.getStatus().getText();
        } else {
            userText = "@" + user.getScreenName();
        }

        return userText;
    }

    public String getStatus(String username) throws TwitterException {
        String status = "";
        User user = twitter.showUser(username);

        if (user.getStatus() != null) {
            status = user.getStatus().getText();
        } else {
            status = "User's tweets are protected.";
        }

        return status;
    }

    public long tweet(String message)  throws TwitterException {
        Status status = twitter.updateStatus(message);
        return status.getId();
    }

    public void destroyTweet(long id) throws TwitterException {
        twitter.destroyStatus(id);
    }
}
