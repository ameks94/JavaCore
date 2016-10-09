package com.labs.task_2_1;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by olenasyrota on 6/28/16.
 */
public class DeveloperService {

    public static List<String> getLanguages(List<Developer> team) {
        return team
                .stream()
                .map( developer -> developer.getLanguages() )
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());
    }


}
