package com.getui.push.v2.sdk.common.type;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * Implementing class for ParameterizedType interface.
 * 具体请看 {@link sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl}
 */
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Class<?> rawType;
    private final Type ownerType;

    private ParameterizedTypeImpl(Class<?> rawType,
                                  Type[] actualTypeArguments,
                                  Type ownerType) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType = rawType;
        this.ownerType = (ownerType != null) ? ownerType : rawType.getDeclaringClass();
        validateConstructorArguments();
    }

    private void validateConstructorArguments() {
        TypeVariable<?>[] formals = rawType.getTypeParameters();
        // check correct arity of actual type args
        if (formals.length != actualTypeArguments.length) {
            throw new MalformedParameterizedTypeException();
        }
        for (int i = 0; i < actualTypeArguments.length; i++) {
            // check actuals against formals' bounds
        }
    }

    /**
     * Static factory. Given a (generic) class, actual type arguments
     * and an owner type, creates a parameterized type.
     * This class can be instantiated with a raw type that does not
     * represent a generic type, provided the list of actual type
     * arguments is empty.
     * If the ownerType argument is null, the declaring class of the
     * raw type is used as the owner type.
     * <p> This method throws a MalformedParameterizedTypeException
     * under the following circumstances:
     * If the number of actual type arguments (i.e., the size of the
     * array {@code typeArgs}) does not correspond to the number of
     * formal type arguments.
     * If any of the actual type arguments is not an instance of the
     * bounds on the corresponding formal.
     *
     * @param rawType             the Class representing the generic type declaration being
     *                            instantiated
     * @param actualTypeArguments a (possibly empty) array of types
     *                            representing the actual type arguments to the parameterized type
     * @param ownerType           the enclosing type, if known.
     * @return An instance of {@code ParameterizedType}
     * @throws MalformedParameterizedTypeException if the instantiation
     *                                             is invalid
     */
    public static ParameterizedTypeImpl make(Class<?> rawType,
                                             Type[] actualTypeArguments,
                                             Type ownerType) {
        return new ParameterizedTypeImpl(rawType, actualTypeArguments,
                ownerType);
    }


    /**
     * Returns an array of {@code Type} objects representing the actual type
     * arguments to this type.
     *
     * <p>Note that in some cases, the returned array be empty. This can occur
     * if this type represents a non-parameterized type nested within
     * a parameterized type.
     *
     * @return an array of {@code Type} objects representing the actual type
     * arguments to this type
     * @throws TypeNotPresentException             if any of the
     *                                             actual type arguments refers to a non-existent type declaration
     * @throws MalformedParameterizedTypeException if any of the
     *                                             actual type parameters refer to a parameterized type that cannot
     *                                             be instantiated for any reason
     * @since 1.5
     */
    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments.clone();
    }

    /**
     * Returns the {@code Type} object representing the class or interface
     * that declared this type.
     *
     * @return the {@code Type} object representing the class or interface
     * that declared this type
     */
    @Override
    public Class<?> getRawType() {
        return rawType;
    }


    /**
     * Returns a {@code Type} object representing the type that this type
     * is a member of.  For example, if this type is {@code O<T>.I<S>},
     * return a representation of {@code O<T>}.
     *
     * <p>If this type is a top-level type, {@code null} is returned.
     *
     * @return a {@code Type} object representing the type that
     * this type is a member of. If this type is a top-level type,
     * {@code null} is returned
     * @throws TypeNotPresentException             if the owner type
     *                                             refers to a non-existent type declaration
     * @throws MalformedParameterizedTypeException if the owner type
     *                                             refers to a parameterized type that cannot be instantiated
     *                                             for any reason
     */
    @Override
    public Type getOwnerType() {
        return ownerType;
    }

    /**
     * From the JavaDoc for java.lang.reflect.ParameterizedType
     * "Instances of classes that implement this interface must
     * implement an equals() method that equates any two instances
     * that share the same generic type declaration and have equal
     * type parameters."
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ParameterizedType) {
            // Check that information is equivalent
            ParameterizedType that = (ParameterizedType) o;

            if (this == that) {
                return true;
            }

            Type thatOwner = that.getOwnerType();
            Type thatRawType = that.getRawType();

            return
                    equals(ownerType, thatOwner) &&
                            equals(rawType, thatRawType) &&
                            Arrays.equals(actualTypeArguments,
                                    that.getActualTypeArguments());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return
                Arrays.hashCode(actualTypeArguments) ^
                        hashCode(ownerType) ^
                        hashCode(rawType);
    }

    boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    int hashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }
}
