package com.builder.definition;

import com.builder.BuilderManager;
import com.builder.ClassNames;
import com.builder.core.IntentArg;
import com.builder.core.IntentBuilder;
import com.builder.validator.IntentArgValidator;
import com.google.common.collect.Lists;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Description: Holds information related to the {@link IntentBuilder} annotation.
 */
public class IntentBuilderDefinition extends BaseDefinition {

    public static final String PARAM_OBJECT = "object";

    public static final String PARAM_INTENT = "intent";

    private List<IntentArgDefinition> intentArgsList;

    public ClassName injectorOutputClassName;

    public IntentBuilderDefinition(BuilderManager manager, TypeElement typeElement) {
        super(manager, typeElement);
        setOutputClassNamePostfix("_IntentBuilder");

        injectorOutputClassName = ClassName.get(elementClassName.packageName(),
                elementClassName.simpleName() + "_Injector");

        intentArgsList = Lists.newArrayList();

        // use this method vs. typeElement.getEnclosedElements()
        // latter is only non-inherited members
        List<? extends Element> elements = manager.getElementUtils().getAllMembers(typeElement);
        IntentArgValidator intentArgValidator = new IntentArgValidator();
        for (Element element : elements) {
            IntentArg intentArg = element.getAnnotation(IntentArg.class);

            // use annotation vs. if its a VariableElement.
            if (intentArg != null) {
                IntentArgDefinition definition = new IntentArgDefinition(manager, outputClassName, element);
                if (intentArgValidator.isValid(manager, definition)) {
                    intentArgsList.add(definition);
                }
            }
        }
    }

    public TypeSpec getInjectorTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(injectorOutputClassName.simpleName())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(ParameterizedTypeName.get(ClassNames.I_INTENT_INJECTOR, elementClassName));

        MethodSpec.Builder inject = MethodSpec.methodBuilder("inject")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addParameter(elementClassName, PARAM_OBJECT)
                .addParameter(ClassNames.INTENT, PARAM_INTENT);

        for (IntentArgDefinition intentArgDefinition : intentArgsList) {
            inject.addCode(intentArgDefinition.getInjectionCodeBlock());
        }
        typeBuilder.addMethod(inject.build());

        return typeBuilder.build();
    }

    @Override
    protected void onWriteDefinition(TypeSpec.Builder typeBuilder) {
        // here we add to the class.

        MethodSpec.Builder typeClassbuilder = MethodSpec.methodBuilder("getTypeClass")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PROTECTED, Modifier.FINAL)
                .returns(ParameterizedTypeName.get(ClassName.get(Class.class), elementClassName))
                .addCode("return $L.class;", elementClassName);
        typeBuilder.addMethod(typeClassbuilder.build());

        for (IntentArgDefinition intentArgDefinition : intentArgsList) {
            typeBuilder.addMethod(intentArgDefinition.getIntentMethod());
        }
    }

    @Override
    protected TypeName getExtendsClass() {
        return ClassNames.BASE_INTENT_BUILDER;
    }
}
