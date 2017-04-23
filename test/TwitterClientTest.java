import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import twitter4j.TwitterException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

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

    @Test(expected = TwitterException.class)
    public void destroyTweetShouldThrowExceptionForInvalidId() throws Exception {
        //Arrange

        //Act
        client.destroyTweet(-1);
        //Assert
    }

}