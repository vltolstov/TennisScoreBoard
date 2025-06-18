package services;

import models.Match;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class OngoingMatchesService {

    private static final Map<Integer, Match> ongoingMatches = Collections.synchronizedMap(new HashMap<>());

    private OngoingMatchesService() {}

    public static void addMatch(Match match) {

    }

    public static void getMatch (String matchUuid) {

    }

    private Integer generateUuid(){
        return 0;
    }

}
