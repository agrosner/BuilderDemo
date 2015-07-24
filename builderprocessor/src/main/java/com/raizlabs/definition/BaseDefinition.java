package com.raizlabs.definition;

import com.raizlabs.BuilderManager;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.Arrays;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * Description: Holds onto a common-set of fields and provides a common-set of methods to output class files.
 */
public abstract class BaseDefinition implements TypeDefinition {

    protected BuilderManager manager;

    protected TypeElement typeElement;

    public final ClassName elementClassName;

    public ClassName outputClassName;

    public BaseDefinition(BuilderManager manager, TypeElement typeElement) {
        this.manager = manager;
        this.typeElement = typeElement;
        this.elementClassName = ClassName.get(typeElement);
    }

    /**
     * Sets what the name of the class we're going to write.
     *
     * @param postfix The identifier of the generated code class so it doesn't class with the existing class file.
     */
    protected void setOutputClassNamePostfix(String postfix) {
        outputClassName = ClassName.get(elementClassName.packageName(), elementClassName.simpleName() + postfix);
    }

    @Override
    public TypeSpec getTypeSpec() {
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(outputClassName.simpleName())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterfaces(Arrays.asList(getImplementClasses()))
                .superclass(getExtendsClass())
                .addJavadoc("This is generated code. Please do not modify");
        onWriteDefinition(typeBuilder);
        return typeBuilder.build();
    }

    /**
     * @return the superclass of this generated class file.
     */
    protected TypeName getExtendsClass() {
        return null;
    }

    /**
     * @return The classes that the generated class may implement.
     */
    protected TypeName[] getImplementClasses() {
        return null;
    }

    /**
     * Called with the already constructed class object.
     *
     * @param typeBuilder The class object where we add methods, fields, and more.
     */
    protected abstract void onWriteDefinition(TypeSpec.Builder typeBuilder);

}
