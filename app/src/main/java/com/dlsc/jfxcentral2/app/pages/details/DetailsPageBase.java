package com.dlsc.jfxcentral2.app.pages.details;

import com.dlsc.jfxcentral.data.DataRepository;
import com.dlsc.jfxcentral.data.model.Blog;
import com.dlsc.jfxcentral.data.model.Book;
import com.dlsc.jfxcentral.data.model.Company;
import com.dlsc.jfxcentral.data.model.Download;
import com.dlsc.jfxcentral.data.model.Library;
import com.dlsc.jfxcentral.data.model.ModelObject;
import com.dlsc.jfxcentral.data.model.Person;
import com.dlsc.jfxcentral.data.model.RealWorldApp;
import com.dlsc.jfxcentral.data.model.Tip;
import com.dlsc.jfxcentral.data.model.Tool;
import com.dlsc.jfxcentral.data.model.Tutorial;
import com.dlsc.jfxcentral.data.model.Video;
import com.dlsc.jfxcentral2.app.pages.PageBase;
import com.dlsc.jfxcentral2.components.MenuView;
import com.dlsc.jfxcentral2.components.TopMenuBar;
import com.dlsc.jfxcentral2.components.detailsbox.AppsDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.BlogsDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.BooksDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.CompaniesDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.DetailsBoxBase;
import com.dlsc.jfxcentral2.components.detailsbox.DownloadsDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.LibrariesDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.PersonsDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.TipsAndTricksDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.ToolsDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.TutorialsDetailsBox;
import com.dlsc.jfxcentral2.components.detailsbox.VideosDetailsBox;
import com.dlsc.jfxcentral2.model.Feature;
import com.dlsc.jfxcentral2.model.Size;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class DetailsPageBase<T extends ModelObject> extends PageBase {

    private T item;

    public DetailsPageBase(ObjectProperty<Size> size, Class<? extends T> clazz, String itemId) {
        super(size, TopMenuBar.Mode.DARK);
        setItem((T) DataRepository.getInstance().getByID(clazz, itemId));
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String title() {
        return "JFXCentral - " + getItem().getName();
    }

    @Override
    public String description() {
        String text = getItem().getDescription();

        // TODO: replace with StringUtils
        if (text != null && !text.trim().equals("")) {
            return text;
        }

        return "";
    }

    @Override
    public Node content() {
        return wrapContent(new Label(getItem().getName()));
    }

    protected List<DetailsBoxBase<?>> createDetailBoxes() {
        ModelObject modelObject = getItem();
        List<DetailsBoxBase<?>> boxes = new ArrayList<>();

        maybeAddBox(modelObject, Video.class, VideosDetailsBox::new, boxes);
        maybeAddBox(modelObject, Tool.class, ToolsDetailsBox::new, boxes);
        maybeAddBox(modelObject, Library.class, LibrariesDetailsBox::new, boxes);
        maybeAddBox(modelObject, Book.class, BooksDetailsBox::new, boxes);
        maybeAddBox(modelObject, Blog.class, BlogsDetailsBox::new, boxes);
        maybeAddBox(modelObject, Tutorial.class, TutorialsDetailsBox::new, boxes);
        maybeAddBox(modelObject, Download.class, DownloadsDetailsBox::new, boxes);
        maybeAddBox(modelObject, Tip.class, TipsAndTricksDetailsBox::new, boxes);
        maybeAddBox(modelObject, RealWorldApp.class, AppsDetailsBox::new, boxes);
        maybeAddBox(modelObject, Person.class, PersonsDetailsBox::new, boxes);
        maybeAddBox(modelObject, Company.class, CompaniesDetailsBox::new, boxes);

        return boxes;
    }

    private void maybeAddBox(ModelObject modelObject, Class<? extends ModelObject> clazz, Supplier<DetailsBoxBase> boxSupplier, List<DetailsBoxBase<?>> boxList) {
        ListProperty<? extends ModelObject> linkedObjects = DataRepository.getInstance().getLinkedObjects(modelObject, clazz);
        if (!linkedObjects.isEmpty()) {
            DetailsBoxBase box = boxSupplier.get();
            box.sizeProperty().bind(sizeProperty());
            boxList.add(box);
        }
    }

    protected List<MenuView.Item> createMenuItems() {
        return FXCollections.observableArrayList(
                new MenuView.Item("TOOLS", null, "Tools url"),
                new MenuView.Item("BLOGS", null, "Blogs url"),
                new MenuView.Item("DOWNLOADS", null, "Downloads url"),
                new MenuView.Item("LIBRARIES", null, "Libraries url"),
                new MenuView.Item("VIDEOS", null, "Videos url"),
                new MenuView.Item("APP", null, "app url"),
                new MenuView.Item("BOOK", null, "book url"),
                new MenuView.Item("TIPS & TRICKS", null, "Tips & Tricks url")
        );
    }

    protected List<Feature> createFeatures() {
        return List.of(
                new Feature("Video", "[1] Having Fun with Java and JavaFX on the Raspberry PI lorem ipsum whatever long text", "Featured", "5 min video", MaterialDesign.MDI_TIMER, Feature.Type.VIDEO, new Image(PageBase.class.getResource("feature-img.png").toExternalForm()), "url ..."),
                new Feature("Video", "[2] Having Fun with Java and JavaFX on the Raspberry PI lorem ipsum whatever long text", "Featured", "5 min video", MaterialDesign.MDI_TIMER, Feature.Type.VIDEO, new Image(PageBase.class.getResource("feature-img.png").toExternalForm()), "url ..."),
                new Feature("Video", "[3] Having Fun with Java and JavaFX on the Raspberry PI lorem ipsum whatever long text", "Featured", "5 min video", MaterialDesign.MDI_TIMER, Feature.Type.VIDEO, new Image(PageBase.class.getResource("feature-img.png").toExternalForm()), "url ..."));
    }
}
