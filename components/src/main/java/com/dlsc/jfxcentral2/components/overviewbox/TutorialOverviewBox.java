package com.dlsc.jfxcentral2.components.overviewbox;

import com.dlsc.jfxcentral.data.DataRepository;
import com.dlsc.jfxcentral.data.model.Tutorial;

public class TutorialOverviewBox extends SimpleOverviewBox<Tutorial> {

    public TutorialOverviewBox(Tutorial data) {
        super(data);
        getStyleClass().add("tutorial-overview-box");
        setBaseURL(DataRepository.getInstance().getRepositoryDirectoryURL() + "tutorials/" + getModel().getId());
        markdownProperty().bind(DataRepository.getInstance().tutorialTextProperty(data));
    }
}