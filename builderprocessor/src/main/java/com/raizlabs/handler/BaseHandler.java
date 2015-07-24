package com.raizlabs.handler;

import com.raizlabs.BuilderManager;
import com.raizlabs.definition.BaseDefinition;
import com.raizlabs.validator.Validator;
import com.squareup.javapoet.JavaFile;

import java.io.IOException;
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

    protected boolean shouldWriteFile() {
        return true;
    }

    protected abstract Validator getValidator();

    protected abstract BaseDefinition createDefinition(TypeElement element, BuilderManager manager);

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

    /**
     * Called during processing. This is called when a {@link Set} of {@link Element} are found for the
     * specified {@link AnnotationClass}.
     *
     * @param element  The element that was found. This is almost always a {@link TypeElement}
     * @param roundEnv The environment.
     * @param manager  The manager.
     */
    protected void onProcessElement(TypeElement element, RoundEnvironment roundEnv, BuilderManager manager) {
        BaseDefinition baseDefinition = createDefinition(element, manager);
        Validator validator = getValidator();
        if (validator.isValid(manager, baseDefinition)) {
            if (shouldWriteFile()) {
                JavaFile javaFile = JavaFile.builder(manager.packageOf(element), baseDefinition.getTypeSpec()).build();
                try {
                    javaFile.writeTo(manager.getFiler());
                } catch (IOException e) {
                    // crash if error!
                    throw new RuntimeException(e);
                }
            }
            onValidated(manager, baseDefinition);
        }
    }

    protected void onValidated(BuilderManager manager, BaseDefinition baseDefinition) {

    }
}
