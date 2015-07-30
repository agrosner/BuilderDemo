package com.builder;

import com.builder.definition.IntentBuilderDefinition;
import com.builder.handler.IHandler;
import com.builder.handler.IntentBuilderHandler;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Description: The class that holds information that we need during processing. Such as the {@link Filer},
 * {@link }
 */
public class BuilderManager {

    private ProcessingEnvironment environment;

    // running array of handlers this manager passes the process call to.
    private final IHandler[] handlers = new IHandler[]{
            new IntentBuilderHandler()
    };

    private final Map<TypeName, IntentBuilderDefinition> intentBuilderDefinitionMap = new HashMap<>();

    public BuilderManager(ProcessingEnvironment environment) {
        this.environment = environment;
    }

    public String packageOf(Element element) {
        return environment.getElementUtils().getPackageOf(element).toString();
    }

    public Filer getFiler() {
        return environment.getFiler();
    }

    public Elements getElementUtils() {
        return environment.getElementUtils();
    }

    public void addIntentBuilderForType(TypeName typeName, IntentBuilderDefinition intentBuilderDefinition) {
        intentBuilderDefinitionMap.put(typeName, intentBuilderDefinition);
    }

    public void logError(String error, Object...args) {
        environment.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(error, args));
    }

    /**
     * Called during the processor {@link BuilderProcessor#process(Set, RoundEnvironment)} method.
     *
     * @param roundEnvironment The round environment currently we're on.
     */
    public void handle(RoundEnvironment roundEnvironment) {
        // let the registered handlers run here.
        for (IHandler handler : handlers) {
            handler.handle(roundEnvironment, this);
        }

        if (roundEnvironment.processingOver()) {

            TypeSpec.Builder holderBuilder = TypeSpec.classBuilder("Builder_Holder_Generated")
                    .superclass(ClassNames.BUILDER_HOLDER)
                    .addModifiers(Modifier.FINAL);

            MethodSpec.Builder constructor = MethodSpec.constructorBuilder();

            final String INTENT_INJECTOR_MAP = "INTENT_INJECTOR_MAP";

            Collection<IntentBuilderDefinition> definitions = intentBuilderDefinitionMap.values();
            for (IntentBuilderDefinition definition : definitions) {
                constructor.addCode(CodeBlock.builder()
                        .addStatement("$L.put($T.class, new $T())", INTENT_INJECTOR_MAP,
                                definition.elementClassName, definition.injectorOutputClassName)
                        .build());
            }

            holderBuilder.addMethod(constructor.build());

            JavaFile javaFile = JavaFile.builder("com.builder", holderBuilder.build()).build();
            try {
                javaFile.writeTo(getFiler());
            } catch (IOException e) {
                // crash if error!
                throw new RuntimeException(e);
            }
        }
    }
}
