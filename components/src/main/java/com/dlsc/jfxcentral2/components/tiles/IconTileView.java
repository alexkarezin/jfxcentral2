package com.dlsc.jfxcentral2.components.tiles;

import com.dlsc.jfxcentral2.components.IconPreviewPane;
import com.dlsc.jfxcentral2.model.IconModel;
import com.dlsc.jfxcentral2.utils.IkonUtil;
import javafx.scene.Node;
import org.kordamp.ikonli.javafx.FontIcon;

public class IconTileView extends TileView<IconModel> {
    public IconTileView(IconModel item) {
        super(item);
        getStyleClass().addAll("icon-tile-view");
        setDescription(item.getDescription());

        setButton1Text("DISCOVER");
        setButton1Graphic(new FontIcon(IkonUtil.link));

        setButton2Text("GitHub");
        setButton2Graphic(new FontIcon(IkonUtil.github));

    }

    @Override
    protected Node createFrontTop() {
        IconPreviewPane previewPane = new IconPreviewPane();
        previewPane.sizeProperty().bind(sizeProperty());
        previewPane.setIconModel(getData());
        return previewPane;
    }

}
