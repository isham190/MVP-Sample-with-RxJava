package dagger.app.com.mvp.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by 611399999 on 12/10/2017.
 */
@Root(name = "image", strict = false)
public class Image implements Serializable {
    @Element(name = "url")
    private String url;

    public Image() {
    }

    public String getUrl() {
        return url;
    }
}
