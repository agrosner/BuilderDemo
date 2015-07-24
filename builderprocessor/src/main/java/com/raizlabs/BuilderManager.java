package com.raizlabs;

import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;

/**
 * Description: The class that holds information that we need during processing. Such as the {@link Filer},
 * {@link }
 */
public class BuilderManager {

    private ProcessingEnvironment environment;

    public BuilderManager(ProcessingEnvironment environment) {
        this.environment = environment;
    }

    public String packageOf(Element element) {
        return environment.getElementUtils().getPackageOf(element).toString();
    }

    public Filer getFiler() {
        return environment.getFiler();
    }

    /**
     * Called during the processor {@link BuilderProcessor#process(Set, RoundEnvironment)} method.
     * @param roundEnvironment The round environment currently we're on.
     */
    public void handle(RoundEnvironment roundEnvironment) {

    }
}
