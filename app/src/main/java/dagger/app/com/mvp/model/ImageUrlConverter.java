package dagger.app.com.mvp.model;

import android.util.Log;

import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * Created by 611399999 on 12/10/2017.
 */

public class ImageUrlConverter implements org.simpleframework.xml.convert.Converter {

    private static final String TAG = ImageUrlConverter.class.getSimpleName();

    @Override
    public Object read(InputNode node) throws Exception {
        if(node == null){
            return null;
        }
        String url = node.getAttributes().get("url").getValue();
        return url;
    }

    @Override
    public void write(OutputNode node, Object value) throws Exception {
        Log.i(TAG, "ImageUrlConverter write: "+node);

    }

}
