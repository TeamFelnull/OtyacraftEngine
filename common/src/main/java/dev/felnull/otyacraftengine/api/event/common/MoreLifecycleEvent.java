package dev.felnull.otyacraftengine.api.event.common;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.events.common.LifecycleEvent;

public interface MoreLifecycleEvent {
    Event<LifecycleEvent.ServerState> SERVER_SAVING = EventFactory.createLoop();
}
