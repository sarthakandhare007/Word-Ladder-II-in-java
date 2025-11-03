# Word-Ladder-II-in-java
🧩 Problem Statement:

You are given:

A beginWord, endWord, and a wordList.

You must find all shortest transformation sequences such that:

Only one letter can change at a time.

Each transformed word must exist in the word list.

Each sequence should start from beginWord and end with endWord.

🔹 Example:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]

💡 Approach (BFS + Backtracking):

We combine Breadth-First Search (BFS) and Backtracking (DFS).

BFS:

Find the shortest path distance from beginWord to every other word.

Build a graph mapping each word to its valid next transformations.

DFS Backtracking:

Starting from beginWord, traverse all possible sequences using the graph built by BFS.

Stop when endWord is reached, record the path.

This ensures we only explore shortest transformation paths.

✅ Java Solution:
import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> results = new ArrayList<>();

    

✅ Output:
[hit, hot, dot, dog, cog]
[hit, hot, lot, log, cog]

⚙️ Complexity:
Type	Complexity
Time	O(N × L²) — where N = number of words, L = word length
Space	O(N × L) — graph + queue + recursion stack
🧠 Key Insights:

✅ BFS ensures we find shortest paths only.
✅ DFS reconstructs all valid transformations.
✅ Using distance map avoids cycles and long paths.
✅ Graph stores “neighbor words” differing by one letter.

💬 Step-by-Step Example:

Start: "hit"
Level 1 → "hot"
Level 2 → "dot", "lot"
Level 3 → "dog", "log"
Level 4 → "cog" ✅

So we have:

hit → hot → dot → dog → cog
hit → hot → lot → log → cog
