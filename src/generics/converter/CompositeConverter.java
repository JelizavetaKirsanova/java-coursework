package generics.converter;

public class CompositeConverter <T,U,V> implements Converter <T, V> {

        private final Converter <T, U> first;
        private final Converter <U, V> second;

        public CompositeConverter(Converter<T, U> first, Converter<U, V> second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public V convert(T input) {
            return second.convert(first.convert(input));
        }
    }
