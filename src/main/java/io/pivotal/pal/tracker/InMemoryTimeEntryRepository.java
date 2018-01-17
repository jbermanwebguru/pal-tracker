package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private List<TimeEntry> timeEntries;

    public InMemoryTimeEntryRepository() {
        timeEntries = new ArrayList<>();
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry timeEntry1 = timeEntries.stream().max((x, y) -> Long.compare(x.getId(), y.getId())).orElse(new TimeEntry());

        timeEntry.setId(timeEntry1.getId() + 1);
        timeEntries.add(timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<TimeEntry> list() {
        return timeEntries;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntryToUpdate = find(id);

        if (timeEntryToUpdate != null) {
            timeEntry.setId(id);

            int index = timeEntries.indexOf(timeEntryToUpdate);
            timeEntries.set(index, timeEntry);

            return timeEntry;
        }

        return null;
    }

    @Override
    public void delete(long id) {
        TimeEntry timeEntry = find(id);
        timeEntries.remove(timeEntry);
    }
}
