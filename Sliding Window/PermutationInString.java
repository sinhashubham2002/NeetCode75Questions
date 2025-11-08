class Solution {
    public boolean checkInclusion(String s1, String s2) {
        /*
        We will have two arrays s1Count and s2Count to store the frequencies of
        each of the 26 alphabets present in s1 and current window of s2.
        We will also track how many of these 26 chars count matches from s1 to
        current window in s2. If matches == 26, means all chars count matches.
        */
        if(s1.length() > s2.length()){
            return false;
        }
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for(int i=0; i<s1.length();i++){
            s1Count[s1.charAt(i)-'a']++;
            s2Count[s2.charAt(i)-'a']++;
        }

        int matches = 0;
        for(int i=0; i<26; i++){
            if(s1Count[i] == s2Count[i]){
                matches++;
            }
        }

        int left = 0;
        for(int right = s1.length(); right < s2.length(); right++){
            if(matches == 26){
                return true;
            }
            //Take the new element in the current window and increment s2Count
            int index = s2.charAt(right) - 'a';
            s2Count[index]++;
            if(s1Count[index] == s2Count[index]){
                matches++;
            } else if(s1Count[index] + 1 == s2Count[index]){
                matches--;
            }
            //Rempve the left most element from the current window
            index = s2.charAt(left) - 'a';
            s2Count[index]--;
            if(s1Count[index] == s2Count[index]){
                matches++;
            } else if(s1Count[index] == s2Count[index] + 1){
                matches--;
            }
            left++;
        }

        return matches == 26;
    }
}
