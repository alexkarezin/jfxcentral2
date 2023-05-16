package com.dlsc.jfxcentral2.app.pages.details;

import com.dlsc.jfxcentral.data.model.Blog;
import com.dlsc.jfxcentral2.app.pages.DetailsPageBase;
import com.dlsc.jfxcentral2.model.Size;
import javafx.beans.property.ObjectProperty;

public class BlogDetailsPage extends DetailsPageBase<Blog> {

    public BlogDetailsPage(ObjectProperty<Size> size, String itemId) {
        super(size, Blog.class, itemId);
    }
}
