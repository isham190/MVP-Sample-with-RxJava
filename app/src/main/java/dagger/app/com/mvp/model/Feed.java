package dagger.app.com.mvp.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by 611399999 on 12/10/2017.
 */

@Root(name = "rss", strict = false)
public class Feed implements Serializable {

    @Element(name = "channel")
    private Channel channel;

    public Feed() {
    }

    public Feed(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}
