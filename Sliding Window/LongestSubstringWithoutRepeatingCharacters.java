import java.io.*;
import java.util.*;

class Result {
    public static int getMaximumTeamSize(List<Integer> startTime, List<Integer> endTime) {

        int n = startTime.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = startTime.get(i);
            ends[i] = endTime.get(i);
        }
        int[] startsSorted = starts.clone();
        int[] endsSorted = ends.clone();
        Arrays.sort(startsSorted); // ascending start times
        Arrays.sort(endsSorted);   // ascending end times

        int ans = 1;

        for (int i = 0; i < n; i++) {
            int L = starts[i]; // this employee starts
            int R = ends[i];   // this employee ends
            int cntStart = upperBound(startsSorted, R);
            int cntFinishedBefore = lowerBound(endsSorted, L);
            int teamSize = cntStart - cntFinishedBefore;
            if (teamSize > ans) {
                ans = teamSize;
            }
        }

        return ans;
    }
    private static int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private static int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        // This main matches the "custom testing" format shown in the screenshots:
        // n
        // n lines of startTime[i]
        // n
        // n lines of endTime[i]

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        List<Integer> startTime = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            startTime.add(Integer.parseInt(br.readLine().trim()));
        }

        int m = Integer.parseInt(br.readLine().trim()); // should be same as n
        List<Integer> endTime = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            endTime.add(Integer.parseInt(br.readLine().trim()));
        }

        int ans = Result.getMaximumTeamSize(startTime, endTime);
        System.out.println(ans);
    }
}
