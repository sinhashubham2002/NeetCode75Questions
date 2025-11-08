class Solution {
    public String minWindow(String s, String t) {
        if(t.isEmpty()){
            return "";
        }
        //countT stores the char count int string T
        //window stores the char count in current window
        Map<Character, Integer> countT = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for(char c : t.toCharArray()){
            countT.put(c, countT.getOrDefault(c,0) + 1);
        }

        int left = 0;
        //have stores the count where we have sufficient values in window
        int have = 0;
        //need stores the total count required for substring to contain all chars of T
        int need = countT.size();
        //minWindowLength stores the minimum length
        int minWindowLength = Integer.MAX_VALUE;
        //result stores the current window left and right pointer values
        int[] result = {-1, -1};

        for(int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c,0)+1);
            //keep expanding the window to right and check of right most character
            //If it is in t string, and count also matches, have++
            if(countT.containsKey(c) && window.get(c).equals(countT.get(c))){
                have++;
            }
            //This loop will only get triggered when we get a substring containing
            //all the chars of t in the window. Here we are reducing the window size
            //by shrinking from left and checking if removing left is causing impact on have
            while(have == need){
                int currLength = right - left + 1;
                if(currLength < minWindowLength){
                    result[0] = left;
                    result[1] = right;
                    minWindowLength = currLength;
                }
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                left++;
                if(countT.containsKey(leftChar) && countT.get(leftChar) > window.get(leftChar)){
                    have--;
                }
            }
        }
        return minWindowLength == Integer.MAX_VALUE ? "" : s.substring(result[0], result[1] + 1);
    }
}
