import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> results = new ArrayList<>();

        if (!wordSet.contains(endWord)) return results;

        // Step 1: BFS - find shortest distance for each word
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        bfs(beginWord, endWord, wordSet, graph, distance);

        // Step 2: DFS - build all shortest paths
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, graph, distance, path, results);

        return results;
    }

    private void bfs(String beginWord, String endWord, Set<String> wordSet,
                     Map<String, List<String>> graph, Map<String, Integer> distance) {

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        distance.put(beginWord, 0);

        for (String w : wordSet) graph.put(w, new ArrayList<>());
        graph.put(beginWord, new ArrayList<>());

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int currDist = distance.get(word);

            for (String next : getNeighbors(word, wordSet)) {
                graph.get(word).add(next);
                if (!distance.containsKey(next)) {
                    distance.put(next, currDist + 1);
                    queue.add(next);
                }
            }
        }
    }

    private void dfs(String word, String endWord, Map<String, List<String>> graph,
                     Map<String, Integer> distance, List<String> path, List<List<String>> results) {

        if (word.equals(endWord)) {
            results.add(new ArrayList<>(path));
            return;
        }

        for (String next : graph.get(word)) {
            if (distance.get(next) == distance.get(word) + 1) {
                path.add(next);
                dfs(next, endWord, graph, distance, path, results);
                path.remove(path.size() - 1);
            }
        }
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) neighbors.add(newWord);
            }
            chars[i] = original;
        }
        return neighbors;
    }

    public static void main(String[] args) {
        WordLadderII solver = new WordLadderII();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        List<List<String>> ladders = solver.findLadders(beginWord, endWord, wordList);
        for (List<String> path : ladders) {
            System.out.println(path);
        }
    }
}
