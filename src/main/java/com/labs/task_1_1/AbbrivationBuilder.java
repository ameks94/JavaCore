package com.labs.task_1_1;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AbbrivationBuilder {


    public static String build(List<String> list) {
        // implement function body in order all tests to complete successfully
        return list
                .stream()
                .filter( item -> item != null && !item.isEmpty() )
                .map( el -> el.substring(0, 1) )
                .collect( Collectors.joining() );
    }

}
