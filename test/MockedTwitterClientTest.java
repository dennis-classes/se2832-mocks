import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.verification.VerificationMode;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MockedTwitterClientTest {

    private TwitterClient client;
    private  Twitter mockTwitter;

    @Before
    public void setUp() throws Exception {
        mockTwitter = mock(Twitter.class);
        client = new TwitterClient(mockTwitter);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void  getStatusShouldReturnLatestTweet() throws Exception {
        //Arrange - Tell the mock how to behave when called
        String tweet = "This is a test";
        //Twitter4J showUser returns a User type, we need to mock this first
        User mockUser = mock(User.class);
        Status mockStatus = mock(Status.class);

        when(mockTwitter.showUser("@bcdennis")).thenReturn(mockUser);
        when(mockUser.getStatus()).thenReturn(mockStatus);
        when(mockStatus.getText()).thenReturn(new String(tweet));

        //Act
        String status = client.getStatus("@bcdennis");

        //Assert
        assertEquals(tweet, status);
    }

    @Test
    public void tweetShouldUpdateTwitterStatus() throws Exception {
        //Arrange
        String tweet = "Status Tweet";

        Status mockStatus = mock(Status.class);
        when(mockTwitter.updateStatus(tweet)).thenReturn(mockStatus);

        //Act
        client.tweet(tweet);

        //Assert - this is done with the verify.
        verify(mockTwitter, times(1)).updateStatus(tweet);
    }

    @Test(expected = TwitterException.class)
    public void destroyTweetShouldThrowExceptionForInvalidId() throws Exception {
        //Arrange
        long id = -1;
        doThrow(new TwitterException("TwitterException")).when(mockTwitter).destroyStatus(id);

        //Act
        client.destroyTweet(id);

        //Assert
        //nothing here
    }

}