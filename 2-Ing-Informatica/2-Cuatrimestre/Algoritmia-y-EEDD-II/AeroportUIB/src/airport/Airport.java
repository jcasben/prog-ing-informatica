package airport;

import mappings.UnsortedArrayMapping;
import mappings.UnsortedLinkedListMapping;
import sets.UnsortedLinkedListSet;

import java.util.Iterator;

public class Airport {
    private final UnsortedArrayMapping<String, UnsortedLinkedListSet<Person>> counters;
    private final UnsortedLinkedListMapping<String, UnsortedLinkedListSet<Person>> companies;

    public Airport() {
        this.counters = new UnsortedArrayMapping<>(10);
        this.companies = new UnsortedLinkedListMapping<>();
    }

    public void createCompanies(String [] compNames) {
        for (String name : compNames) {
            companies.put(name, new UnsortedLinkedListSet<>());
        }
    }

    public void createCounters() {
        for (int i = 0; i < 10; i++) {
            counters.put("M" + i, new UnsortedLinkedListSet<>());
        }
    }

    public void printCompanies() {
        Iterator iterator = companies.iterator();
        while (iterator.hasNext()) {
            UnsortedLinkedListMapping.Pair pair = (UnsortedLinkedListMapping.Pair) iterator.next();
            System.out.println(pair.getKey());
        }
    }

    public void printCounters() {
        Iterator iterator = counters.iterator();
        while (iterator.hasNext()) {
            UnsortedArrayMapping.Pair<String, UnsortedLinkedListSet<Person>> pair = (UnsortedArrayMapping.Pair) iterator.next();
            System.out.println(pair.getKey());
        }
    }

    public void entryPassenger(
            String name,
            String DNI,
            String phoneNumber,
            String c,
            String m
    ) {
        Person person = new Person(name, DNI, phoneNumber);
        companies.get(c).add(person);
        counters.get(m).add(person);
    }

    public void printPassengerCompany(String c) {
        Iterator iterator = companies.get(c).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void printPassengerCounter(String m) {
        Iterator iterator = counters.get(m).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
