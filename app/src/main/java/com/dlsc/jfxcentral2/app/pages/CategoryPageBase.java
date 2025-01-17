package com.dlsc.jfxcentral2.app.pages;

import com.dlsc.jfxcentral.data.model.ModelObject;
import com.dlsc.jfxcentral2.components.CategoryContentPane;
import com.dlsc.jfxcentral2.components.Mode;
import com.dlsc.jfxcentral2.components.StripView;
import com.dlsc.jfxcentral2.components.filters.SearchFilterView;
import com.dlsc.jfxcentral2.components.gridview.ModelGridView;
import com.dlsc.jfxcentral2.components.headers.CategoryHeader;
import com.dlsc.jfxcentral2.components.tiles.TileViewBase;
import com.dlsc.jfxcentral2.model.Size;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.util.Callback;
import org.kordamp.ikonli.Ikon;

public abstract class CategoryPageBase<T extends ModelObject> extends PageBase {

    public CategoryPageBase(ObjectProperty<Size> size) {
        super(size, Mode.DARK);
    }

    @Override
    public Node content() {
        // header
        CategoryHeader header = createCategoryHeader();
        header.sizeProperty().bind(sizeProperty());
        header.setTitle(getCategoryTitle());
        header.setIkon(getCategoryIkon());

        // filter
        SearchFilterView<T> filterView = createSearchFilterView();
        filterView.sizeProperty().bind(sizeProperty());
        blockingProperty().bind(filterView.blockingProperty());

        // data
        ObservableList<T> itemsList = getCategoryItems();

        FilteredList<T> filteredList = new FilteredList<>(itemsList);
        filteredList.predicateProperty().bind(filterView.predicateProperty());

        SortedList<T> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(filterView.comparatorProperty());

        // grid view
        ModelGridView<T> gridView = createGridView();
        gridView.sizeProperty().bind(sizeProperty());
        gridView.setTileViewProvider(getTileViewProvider());
        gridView.setDetailNodeProvider(getDetailNodeProvider());
        gridView.setColumns(getNumberOfGridViewColumns());
        gridView.setRows(getNumberOfGridViewRows());
        gridView.setItems(sortedList);

        // wrap grid view in a strip view
        StripView stripView = new StripView(gridView);
        stripView.sizeProperty().bind(sizeProperty());

        // details
        CategoryContentPane contentPane = createCategoryContentPane();
        contentPane.sizeProperty().bind(sizeProperty());
        contentPane.getNodes().addAll(filterView, stripView);

        return wrapContent(header, contentPane);
    }

    protected int getNumberOfGridViewColumns() {
        return 3;
    }

    protected int getNumberOfGridViewRows() {
        return 3;
    }

    protected abstract String getCategoryTitle();

    protected abstract Ikon getCategoryIkon();

    protected abstract ObservableList<T> getCategoryItems();

    protected abstract Callback<T, TileViewBase<T>> getTileViewProvider();

    protected Callback<T, Node> getDetailNodeProvider() {
        return null;
    }

    protected CategoryHeader createCategoryHeader() {
        return new CategoryHeader();
    }

    protected CategoryContentPane createCategoryContentPane(Node... nodes) {
        return new CategoryContentPane(nodes);
    }

    protected abstract SearchFilterView<T> createSearchFilterView();

    protected ModelGridView<T> createGridView() {
        return new ModelGridView<>();
    }
}
