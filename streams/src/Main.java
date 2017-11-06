
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String filename = "test.txt";
    public static void main(String[] args) {
        List<Employee> employees;
        try {
            File source = new File(filename);
            employees = Files
                    .readAllLines(source.toPath())
                    .stream()
                    .map(Employee::new)
                    .collect(Collectors.toList());

            Collections.sort(employees, Comparator.comparing(Employee::getName));
            for(Employee e : employees) System.out.println(e.getName());

        } catch (IOException e) {
            System.out.println("File not found");
            return;
        }
        System.out.println("Max salary: " + maxSalary(employees));
        System.out.println("Min salary: " + minSalary(employees));
        System.out.println("Average salary: " + averageSalary(employees));
        System.out.println("\nJob count:");
        jobCount(employees).forEach((s, i) -> System.out.println(s + " - " + i));
        System.out.println("\nABC:");
        abc(employees).forEach((c, i) -> System.out.println(c + " - " + i));
    }

    public static int maxSalary(List<Employee> list) {
        return list.parallelStream().max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElse(0);
    }

    public static int minSalary(List<Employee> list) {
        return list.parallelStream().map(Employee::getSalary)
                .min(Integer::compareTo).orElse(0);
    }

    public static double averageSalary(List<Employee> list) {
        return list.parallelStream()
                .mapToInt(Employee::getSalary)
                .average().orElse(0);
    }

    public static Map<String, Integer> jobCount(List<Employee> list) {
        return list.parallelStream()
                .collect(Collectors.groupingBy(Employee::getJob, Collectors.summingInt(e -> 1)));
    }

    public static Map<Character, Integer> abc(List<Employee> list) {
        return list.parallelStream()
                .collect(Collectors.groupingBy(e -> e.getName().charAt(0), Collectors.summingInt(e -> 1)));
    }
}