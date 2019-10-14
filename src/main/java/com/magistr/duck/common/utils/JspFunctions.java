package com.magistr.duck.common.utils;

import com.magistr.duck.entity.Term;
import com.magistr.duck.entity.TermGroup;

import java.util.*;


public class JspFunctions {

    public static List<List<String>> termGroupExamples(TermGroup group) {
        Map<String, List<String>> examplesMap = new HashMap<>();
        for (Term term : group.getTerms()) {
            examplesMap.put(term.getLang().name(),
                    term.getExamples() != null ? term.getExamples() : new ArrayList<>(0));
        }
        int maxExamplesSize = examplesMap.values().stream().max(Comparator.comparingInt(List::size))
                .orElseGet(Collections::emptyList).size();

        List<List<String>> examplesMatrix = new ArrayList<>(maxExamplesSize);
        List<String> deExamples = examplesMap.getOrDefault("DE", new ArrayList<>(0));
        List<String> ruExamples = examplesMap.getOrDefault("RU", new ArrayList<>(0));
        addEmptyStrings(deExamples, maxExamplesSize - deExamples.size());
        addEmptyStrings(ruExamples, maxExamplesSize - ruExamples.size());
        for (int i = 0; i < maxExamplesSize; i++) {
            examplesMatrix.add(List.of(deExamples.get(i), ruExamples.get(i)));
        }

        return examplesMatrix;
    }

    private static void addEmptyStrings(List<String> list, int num) {
        for (int i = 0; i < num; i++) {
            list.add("");
        }
    }
}
