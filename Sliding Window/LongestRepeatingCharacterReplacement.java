import java.util.*;

class Result {

    public static int getMaximumTeamSize(List<Integer> startTime, List<Integer> endTime) {
        int n = startTime.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = startTime.get(i);
            end[i] = endTime.get(i);
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 0, j = 0;
        int current = 0, maxTeam = 0;

        while (i < n && j < n) {
            if (start[i] <= end[j]) {
                current++;                   // One more employee joins
                maxTeam = Math.max(maxTeam, current);
                i++;
            } else {
                current--;                   // One employee leaves
                j++;
            }
        }

        return maxTeam;
    }
}
