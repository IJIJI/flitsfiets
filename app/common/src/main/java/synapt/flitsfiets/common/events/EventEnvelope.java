package synapt.flitsfiets.common.events;

import java.time.Instant;
import java.util.UUID;

public record EventEnvelope<T>(
        UUID eventID,
        Instant occurredAt,
        String type,
        T data
) {}
