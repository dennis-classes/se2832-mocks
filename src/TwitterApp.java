public class TwitterApp {

    public static void main(String[] args) throws Exception {
        TwitterClient client = new TwitterClient();
        System.out.println(client.getUser("@bcdennis"));

        client.tweet("This is a test4");

        System.out.println(client.getUser("@bcdennis"));
    }
}
