package dagger.app.com.dagger.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by 611399999 on 06/10/2017.
 */
@Root(name = "channel", strict = false)
public class Channel {
//    <title>Popular – Refind</title>
//        <link>https://refind.com/feed/popular</link>
//    <description>Daily top links.</description>

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
}
