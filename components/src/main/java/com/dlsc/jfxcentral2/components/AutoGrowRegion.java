package com.dlsc.jfxcentral2.components;

import javafx.beans.property.ObjectProperty;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.css.converter.EnumConverter;
import javafx.geometry.Orientation;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoGrowRegion extends Region {

    private static final PseudoClass VERTICAL_PSEUDOCLASS_STATE = PseudoClass.getPseudoClass("vertical");
    private static final PseudoClass HORIZONTAL_PSEUDOCLASS_STATE = PseudoClass.getPseudoClass("horizontal");

    public AutoGrowRegion() {
        this(Orientation.HORIZONTAL);
    }

    public AutoGrowRegion(Orientation orientation) {
        getStyleClass().add("auto-grow-region");
        setOrientation(orientation);
        pseudoClassStateChanged(HORIZONTAL_PSEUDOCLASS_STATE, orientation == Orientation.HORIZONTAL);
        pseudoClassStateChanged(VERTICAL_PSEUDOCLASS_STATE, orientation == Orientation.VERTICAL);
        setOrientationGrow();
    }

    private void setOrientationGrow() {
        Orientation orientation = getOrientation();
        if (orientation == Orientation.HORIZONTAL) {
            VBox.setVgrow(this, Priority.NEVER);
            HBox.setHgrow(this, Priority.ALWAYS);
        } else {
            VBox.setVgrow(this, Priority.ALWAYS);
            HBox.setHgrow(this, Priority.NEVER);
        }
    }

    private final ObjectProperty<Orientation> orientation =
            new StyleableObjectProperty<>(Orientation.HORIZONTAL) {

                @Override
                public Object getBean() {
                    return AutoGrowRegion.this;
                }

                @Override
                public String getName() {
                    return "orientation";
                }

                @Override
                protected void invalidated() {
                    final boolean isVertical = (get() == Orientation.VERTICAL);
                    pseudoClassStateChanged(VERTICAL_PSEUDOCLASS_STATE, isVertical);
                    pseudoClassStateChanged(HORIZONTAL_PSEUDOCLASS_STATE, !isVertical);
                    setOrientationGrow();
                }

                @Override
                public CssMetaData<AutoGrowRegion, Orientation> getCssMetaData() {
                    return StyleableProperties.ORIENTATION;
                }

            };

    public final void setOrientation(Orientation value) {
        orientation.set(value);
    }

    public final Orientation getOrientation() {
        return orientation.get();
    }

    public final ObjectProperty<Orientation> orientationProperty() {
        return orientation;
    }

    private static class StyleableProperties {
        private static final CssMetaData<AutoGrowRegion, Orientation> ORIENTATION =
                new CssMetaData<>("-fx-orientation",
                        new EnumConverter<>(Orientation.class),
                        Orientation.HORIZONTAL) {

                    @Override
                    public Orientation getInitialValue(AutoGrowRegion node) {
                        return node.getOrientation();
                    }

                    @Override
                    public boolean isSettable(AutoGrowRegion n) {
                        return !n.orientation.isBound();
                    }

                    @Override
                    public StyleableProperty<Orientation> getStyleableProperty(AutoGrowRegion n) {
                        return (StyleableProperty<Orientation>) n.orientationProperty();
                    }
                };
        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                    new ArrayList<>(Region.getClassCssMetaData());
            styleables.add(ORIENTATION);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

}
