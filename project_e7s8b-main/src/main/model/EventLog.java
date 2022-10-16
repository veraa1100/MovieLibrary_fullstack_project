package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class EventLog implements Iterable<Event> {


    private static EventLog theLog;
    private final Collection<Event> events;


    private EventLog() {
        events = new ArrayList<Event>();
    }


    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }


    public void logEvent(Event e) {
        events.add(e);
    }


    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
