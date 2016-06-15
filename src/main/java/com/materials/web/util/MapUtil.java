package com.materials.web.util;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapUtil
{
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Entry<K, V>> st = map.entrySet().stream();

        st.sorted( Entry.comparingByValue() )
                .forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

        return result;
    }

    public static <K,V extends Comparable<? super V>>
    List<K> entriesSortedByValues(Map<K,V> map) {

        List<Entry<K,V>> sortedEntries = new ArrayList<>(map.entrySet());

        Collections.sort(sortedEntries,
                (e1, e2) -> e2.getValue().compareTo(e1.getValue())
        );

        return sortedEntries.stream().map(Entry::getKey).collect(Collectors.toList());
    }


}
