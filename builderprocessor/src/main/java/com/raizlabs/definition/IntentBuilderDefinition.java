package com.raizlabs.definition;

import com.builder.core.IntentBuilder;
import com.google.common.collect.Lists;
import com.raizlabs.BuilderManager;
import com.raizlabs.TypeNames;
import com.raizlabs.validator.IntentArgValidator;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Description: Holds information related to the {@link IntentBuilder} annotation.
 */
public class IntentBuilderDefinition extends BaseDefinition {

    private List<IntentArgDefinition> intentArgsList;

    public IntentBuilderDefinition(BuilderManager manager, TypeElement typeElement) {
        super(manager, typeElement);
        setOutputClassNamePostfix("_IntentBuilder");

        intentArgsList = Lists.newArrayList();

        // use this method vs. typeElement.getEnclosedElements()
        // latter is only non-inherited members
        List<? extends Element> elements = manager.getElementUtils().getAllMembers(typeElement);
        IntentArgValidator intentArgValidator = new IntentArgValidator();
        for (Element element : elements) {
            IntentArgDefinition definition = new IntentArgDefinition(manager, element);
            if (intentArgValidator.isValid(manager, definition)) {
                intentArgsList.add(definition);
            }
        }
    }

    @Override
    protected void onWriteDefinition(TypeSpec.Builder typeBuilder) {
        // here we add to the class.

        
    }

    @Override
    protected TypeName getExtendsClass() {
        return TypeNames.BASE_INTENT_BUILDER;
    }
}
