package org.ess.module.bookings.event;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class BookingEvent {
    public final static PublishSubject<BookingEvent.Type> subject = PublishSubject.create();

    /**
     * Publishes an event of the specified type.
     *
     * @param eventType the type of event to publish
     */
    public static void subscribe(BookingEvent.Type eventType, Consumer<BookingEvent.Type> consumer) {
        subject.filter(e -> e.equals(eventType)).subscribe(consumer);
    }

    public static enum Type {
        CREATE,
        UPDATE,
        DELETE
    }
}
