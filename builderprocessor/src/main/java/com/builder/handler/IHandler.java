package com.builder.handler;

import com.builder.BuilderManager;

import javax.annotation.processing.RoundEnvironment;

/**
 * Description: Defines the interface for when the annotation processor runs, the single method is called.
 * This enables unified handling of separate annotations.
 * annotation.
 */
public interface IHandler {

    void handle(RoundEnvironment roundEnvironment, BuilderManager manager);
}
