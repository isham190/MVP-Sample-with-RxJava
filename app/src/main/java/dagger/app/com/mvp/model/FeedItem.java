package dagger.app.com.mvp.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.io.Serializable;

/**
 * Created by 611399999 on 12/10/2017.
 */

@Root(name = "item", strict = false)
public class FeedItem implements Serializable {

    @Element(name = "pubDate")
    private String pubDate;
    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;
    @Element(name = "description")
    private String description;
    @Element(name = "enclosure", required = false)
    @Convert(ImageUrlConverter.class)
    String imageUrl;//

    public FeedItem() {
    }

    public FeedItem(String pubDate, String title, String link, String description) {
        this.pubDate = pubDate;
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
