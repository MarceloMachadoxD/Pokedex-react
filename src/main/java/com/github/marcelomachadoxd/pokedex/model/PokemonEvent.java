package com.github.marcelomachadoxd.pokedex.model;

import java.util.Objects;

public class PokemonEvent {

    private Long EventId;
    private String eventType;

    public PokemonEvent(Long eventId, String eventType) {
        EventId = eventId;
        this.eventType = eventType;
    }

    public Long getEventId() {
        return EventId;
    }

    public void setEventId(Long eventId) {
        EventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonEvent that = (PokemonEvent) o;
        return Objects.equals(EventId, that.EventId) && Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EventId, eventType);
    }


    @Override
    public String toString() {
        return "PokemonEvent{" +
            "EventId=" + EventId +
            ", eventType='" + eventType + '\'' +
            '}';
    }
}
