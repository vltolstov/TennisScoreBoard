package services;

import models.Match;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class OngoingMatchesService {

    private static final Map<UUID, Match> ongoingMatches = Collections.synchronizedMap(new HashMap<>());

    private OngoingMatchesService() {}

    public static void addMatch(UUID uuid, Match match) {
        ongoingMatches.put(uuid, match);
    }

    public static Match getMatch (UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public static void removeMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
}
