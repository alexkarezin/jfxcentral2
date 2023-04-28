package com.dlsc.jfxcentral2.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Objects;

public class CategoryHeader extends PaneBase {

    public CategoryHeader() {
        getStyleClass().add("category-header");

        Label label = new Label();
        label.textProperty().bind(titleProperty());
        FontIcon fontIcon = new FontIcon();
        fontIcon.iconCodeProperty().bind(ikonProperty());
        label.setGraphic(fontIcon);
        label.getStyleClass().add("header-title");
        label.managedProperty().bind(label.visibleProperty());
        label.visibleProperty().bind(titleProperty().isNotEmpty().or(ikonProperty().isNotNull()));

        getChildren().setAll(label);
        //Only one label and content can be displayed;
        //if the content is not empty, the content will be displayed, otherwise the label will be displayed
        contentProperty().addListener((ob, ov, nv) -> {
            getChildren().setAll(Objects.requireNonNullElse(getContent(), label));
        });
    }

    private final StringProperty title = new SimpleStringProperty(this, "title");

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    private final ObjectProperty<Ikon> ikon = new SimpleObjectProperty<>(this, "ikon");

    public Ikon getIkon() {
        return ikon.get();
    }

    public ObjectProperty<Ikon> ikonProperty() {
        return ikon;
    }

    public void setIkon(Ikon ikon) {
        this.ikon.set(ikon);
    }

    private final ObjectProperty<Node> content = new SimpleObjectProperty<>(this, "content");

    public Node getContent() {
        return content.get();
    }

    public ObjectProperty<Node> contentProperty() {
        return content;
    }

    public void setContent(Node content) {
        this.content.set(content);
    }
}