package dagger.app.com.mvp.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 611399999 on 06/10/2017.
 */
@Root(name = "channel", strict = false)
public class Channel implements Serializable{
//    <title>Popular – Refind</title>
//        <link>https://refind.com/feed/popular</link>
//    <description>Daily top links.</description>

    @ElementList(inline = true, name="item")
    private List<FeedItem> FeedItems;

    @Element(name="image")
    private Image image;

    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Channel() {
    }

    public Channel(List<FeedItem> feedItems) {
        FeedItems = feedItems;
    }

    public List<FeedItem> getFeedItems() {
        return FeedItems;
    }

    public Image getImage() {
        return image;
    }
}
