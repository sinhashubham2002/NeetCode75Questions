import java.util.*;

class Result {

    public static int getMaximumTeamSize(List<Integer> startTime, List<Integer> endTime) {
        int n = startTime.size();
        List<int[]> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            events.add(new int[]{startTime.get(i), +1});         // start of interval
            events.add(new int[]{endTime.get(i) + 1, -1});       // end (exclusive)
        }
        events.sort((a, b) -> (a[0] == b[0]) ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

        int current = 0, maxTeam = 0;

        // Sweep through all events
        for (int[] e : events) {
            current += e[1];              // +1 or -1
            maxTeam = Math.max(maxTeam, current);
        }

        return maxTeam;
    }
}
