package com.dlsc.jfxcentral2.app.pages.category;

import com.dlsc.jfxcentral.data.model.Company;
import com.dlsc.jfxcentral.data.model.Person;
import com.dlsc.jfxcentral2.app.pages.CategoryPageBase;
import com.dlsc.jfxcentral2.components.CategoryContentPane;
import com.dlsc.jfxcentral2.components.filters.CompaniesFilterView;
import com.dlsc.jfxcentral2.components.headers.CategoryHeader;
import com.dlsc.jfxcentral2.model.Size;
import com.dlsc.jfxcentral2.utils.IkonUtil;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;

public class CompaniesCategoryPage extends CategoryPageBase<Person> {

    public CompaniesCategoryPage(ObjectProperty<Size> size) {
        super(size);
    }

    @Override
    public String title() {
        return "JFXCentral - Companies";
    }

    @Override
    public String description() {
        return "A curated list of companies connected to JavaFX. They develop libraries, applications, tools.";
    }

    @Override
    public Node content() {
        // header
        CategoryHeader header = createCategoryHeader("Companies", IkonUtil.getModelIkon(Company.class));

        // filter
        CompaniesFilterView filterView = new CompaniesFilterView();
        filterView.sizeProperty().bind(sizeProperty());

        // details
        CategoryContentPane contentPane = createCategoryContentPane();
        contentPane.getNodes().add(filterView);

        return wrapContent(header, contentPane);
    }
}
