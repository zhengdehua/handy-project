package com.edward.io.jdk.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by edwardcheng on 2017/8/30.
 */
public class StreamExample {

//    private static String getNearestShop(List<Property> properties) {
//        Collections.sort(properties, (x, y) -> {
//            return x.getDistance().compareTo(y.getDistance());
//        });
//        return properties.get(0).getName();
//    }

    private static String getNearestShop(List<Property> properties) {
        return properties.stream()
                .sorted(Comparator.comparingInt(x -> x.getDistance()))
                .findFirst()
                .get().getName();
    }

    private static long countSalesOver1000(List<Property> properties) {
        return properties.stream()
                .filter(p -> p.getSales() > 1000)
                .count();
    }

    private static String readFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path))
                .stream()
                .collect(Collectors.joining("\n"));
    }

    private static List<String> getAllShopNames(List<Property> properties) {
        return properties.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());
    }

    private static long countLengthOver2(List<List<String>> lists) {
        return lists.stream()
                .flatMap(Collection::stream)
                .filter(str -> str.length() > 2)
                .count();
    }

    private static Property getMaxPriceLevelShop(List<Property> properties) {
        return properties.stream()
                .max(Comparator.comparingInt(p -> p.getPriceLevel()))
                .get();
    }

    private static List<Property> getNearest2Shops(List<Property> properties) {
        return properties.stream()
                .sorted(Comparator.comparingInt(p -> p.getDistance()))
                .limit(2)
                .collect(Collectors.toList());
    }

    private static Map<String, Integer> getShopPriceLevelMap(List<Property> properties) {
        return properties.stream()
                .collect(Collectors.toMap(Property::getName, Property::getPriceLevel));
    }

    private static Map<Integer, List<Property>> groupByPriceLevel(List<Property> properties) {
        return properties.stream()
                .collect(Collectors.groupingBy(Property::getPriceLevel));
    }

    private static List<String> get2NearestWithPriceLevelLess4(List<Property> properties) {
        return properties.parallelStream() // parallelStream() ~ stream().parallel()
                .filter(p -> p.getPriceLevel() < 4)
                .sorted(Comparator.comparingInt(Property::getDistance))
                .map(Property::getName)
                .limit(2)
                .collect(Collectors.toList());
    }

    private static void parallelStreamTimeEfficiency() {
        List<Integer> list = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();

        for (int i=0; i<9999999; i++) {
            list.add(i);
        }

        long start = System.currentTimeMillis();
        long end = 0L;

        for (Integer num : list) {
            if (num % 2 == 0) {
                evens.add(num);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("non-parallel calc -> " + (end-start)/1000 + " seconds");
        evens.clear();

        start = System.currentTimeMillis();
        evens = list.stream()
                .filter(num -> num%2 == 0)
                .collect(Collectors.toList());

        end = System.currentTimeMillis();
        System.out.println("parallel calc -> " + (end-start)/1000 + " seconds");

    }

    public static void main(String[] args) throws Exception {

        Property p1 = new Property("doujiang", 1000, 500, 2);
        Property p2 = new Property("jiaozi", 2300, 1500, 3);
        Property p3 = new Property("shaxian", 580, 3000, 1);
        Property p4 = new Property("kfc", 6000, 200, 4);

        List<Property> properties = Arrays.asList(p1, p2, p3, p4);

        System.out.println("nearest shop -> " + getNearestShop(properties));
        System.out.println("number of sales over 1000 -> " + countSalesOver1000(properties));
//        System.out.println("content of test.txt -> " + readFile(System.getProperty("user.dir") + "/core-features/src/main/resources/test.txt"));
        System.out.println("names of all shops -> " + getAllShopNames(properties));

        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("apple", "click"));
        lists.add(Arrays.asList("boss", "dig", "qq", "vivo"));
        lists.add(Arrays.asList("c#", "biezhi"));

        System.out.println("number of length over 2 -> " + countLengthOver2(lists));
        System.out.println("max price level's shop -> " + getMaxPriceLevelShop(properties));
        System.out.println("nearest 2 shops -> " + getNearest2Shops(properties));
        System.out.println("every shop's price level -> " + getShopPriceLevelMap(properties));
        System.out.println("group by price level -> " + groupByPriceLevel(properties));
        System.out.println("two nearest shop and price level less than 4 -> " + get2NearestWithPriceLevelLess4(properties));
        System.out.println("parallel calc efficiency -> ");
        parallelStreamTimeEfficiency();
    }
}
