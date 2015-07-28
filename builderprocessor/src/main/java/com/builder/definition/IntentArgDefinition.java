package com.builder.definition;

import com.builder.core.IntentArg;
import com.builder.BuilderManager;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * Description: Simple definition class that represents {@link IntentArg}.
 */
public class IntentArgDefinition {

    private static final String PARAM_METHOD = "param";

    public ClassName containedClassName;

    public String variableName;

    public TypeName elementType;

    public String key;

    public IntentArgDefinition(BuilderManager manager, ClassName containedClassName, Element element) {
        this.containedClassName = containedClassName;
        variableName = element.getSimpleName().toString();
        elementType = TypeName.get(element.asType());

        IntentArg arg = element.getAnnotation(IntentArg.class);
        key = arg.key();
    }

    public MethodSpec getIntentMethod() {

        CodeBlock.Builder intentModifier = CodeBlock.builder()
                .addStatement("getIntent().putExtra($S, $L)", key, PARAM_METHOD)
                .addStatement("return this");

        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(getMethodName())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addParameter(elementType, PARAM_METHOD)
                .returns(containedClassName)
                .addCode(intentModifier.build());

        return methodBuilder.build();
    }

    private String getMethodName() {
        return "set" + (variableName.substring(0, 1).toUpperCase() + variableName.substring(1));
    }
}
