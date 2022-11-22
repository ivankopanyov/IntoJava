package infrastructure.application.linq;

public interface ObjectLinq<TStruct, TItem> {
    TStruct get();

    ObjectLinq<TStruct, TItem> orderBy(Expression<TItem, Integer> predicate);

    TItem first(Expression<TItem, Boolean> predicate);

    ObjectLinq<TStruct, TItem> where(Expression<TItem, Boolean> predicate);

    boolean contains(TItem value);

    int count();
}
