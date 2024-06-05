package com.plm.studentdb.database;

import java.util.concurrent.ThreadLocalRandom;

public class DBRandom {
    public static void generateAndAddRandomStudent() {
        int studentId = generateRandomStudentId();
        String name = generateRandomName();
        String program = generateRandomProgram();
        int year = ThreadLocalRandom.current().nextInt(1, 5);
        int block = ThreadLocalRandom.current().nextInt(1, 10);
        String email = generateRandomEmail();

        DBAdd.addStudentRecord(studentId, name, program, year, block, email);
    }

    private static int generateRandomStudentId() {
        return ThreadLocalRandom.current().nextInt(100000, 999999);
    }

    private static String generateRandomName() {
        String[] firstNames = {"John", "Alice", "Bob", "Emma", "Michael", "Sophia", "William", "Olivia"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};

        String firstName = firstNames[ThreadLocalRandom.current().nextInt(firstNames.length)];
        String lastName = lastNames[ThreadLocalRandom.current().nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }

    private static String generateRandomProgram() {
        String[] programs = {"Computer Science", "Engineering", "Mathematics", "Biology", "Psychology"};
        return programs[ThreadLocalRandom.current().nextInt(programs.length)];
    }

    private static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com"};
        String[] prefixes = {"john", "alice", "bob", "emma", "michael", "sophia", "william", "olivia"};

        String prefix = prefixes[ThreadLocalRandom.current().nextInt(prefixes.length)];
        String domain = domains[ThreadLocalRandom.current().nextInt(domains.length)];

        return prefix + "@" + domain;
    }
}
