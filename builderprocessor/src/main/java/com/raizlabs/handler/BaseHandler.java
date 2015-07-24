package com.raizlabs.handler;

import com.raizlabs.BuilderManager;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Description: The base implementation of the {@link IHandler} class. This class supports single top-level
 * annotations.
 */
public abstract class BaseHandler<AnnotationClass extends Annotation> implements IHandler {

    /**
     * @return The annotation class to use.
     */
    protected abstract Class<AnnotationClass> getAnnotationClass();

    /**
     * Called during processing. This is called when a {@link Set} of {@link Element} are found for the
     * specified {@link AnnotationClass}.
     *
     * @param element  The element that was found. This is almost always a {@link TypeElement}
     * @param roundEnv The environment.
     * @param manager  The manager.
     */
    protected abstract void onProcessElement(TypeElement element, RoundEnvironment roundEnv, BuilderManager manager);

    @Override
    public void handle(RoundEnvironment roundEnvironment, BuilderManager manager) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(getAnnotationClass());
        if (elements != null) {
            for (Element element : elements) {
                // safe to cast here as all our annotations found will be from top-level classes.
                onProcessElement((TypeElement) element, roundEnvironment, manager);
            }
        }
    }
}
