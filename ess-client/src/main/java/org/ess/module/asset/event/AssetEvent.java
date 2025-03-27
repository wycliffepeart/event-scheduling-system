package org.ess.module.asset.event;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class AssetEvent {
    public final static PublishSubject<AssetEvent.Type> subject = PublishSubject.create();

    /**
     * Publishes an event of the specified type.
     *
     * @param eventType the type of event to publish
     */
    public static void subscribe(AssetEvent.Type eventType, Consumer<AssetEvent.Type> consumer) {
        subject.filter(e -> e.equals(eventType)).subscribe(consumer);
    }

    public static enum Type {
        CREATE,
        UPDATE,
        DELETE
    }
}
