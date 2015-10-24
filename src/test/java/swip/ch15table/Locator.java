package swip.ch15table;


import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Locator<T1, T2> extends Function<T1, T2> {

    /**
     * locate the result on the search context.
     *
     * @param where the search context
     * @return located result
     */
    T2 locate(T1 where);

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    default <V> Locator<V, T2> compose(Function<? super V, ? extends T1> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param after the function to locate after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(Function)
     */
    default <V> Locator<T1, V> andThen(Locator<? super T2, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T1 t) -> after.locate(locate(t));
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and a locator.  When evaluating the composed
     * predicate, locating the element first and test the element with the Predicate.
     * <p>
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be tested with this after locating
     *              element using this locator
     * @return a predicate that represents the {@code other} predicate for
     * the element located by this
     * @throws NullPointerException if other is null
     */
    default Predicate<T1> and(Predicate<T2> other) {
        Objects.requireNonNull(other);
        return (T1 t) -> other.test(locate(t));
    }

    /**
     * Applies this function to the given argument by calling
     * the locate method of this locator.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default T2 apply(T1 t) {
        return locate(t);
    }
}
