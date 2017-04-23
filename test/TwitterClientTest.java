import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterClientTest {

    TwitterClient client;

    @Before
    public void setUp() throws Exception {
        client = new TwitterClient();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void  getStatusShouldReturnLatestTweet() throws Exception {
        //Arrange
        String tweet = "Test Tweet";
        long id = client.tweet(tweet);

        //Act
        String status = client.getStatus("@bcdennis");

        //Assert
        assertEquals(tweet, status);

    }

    @Test
    public void tweetShouldUpdateTwitterStatus() throws Exception {
        //Arrange
        String tweet = "Status Tweet";

        //Act
        long id = client.tweet(tweet);

        //Assert
        assertEquals(tweet, client.getStatus("@bcdennis"));
    }
}