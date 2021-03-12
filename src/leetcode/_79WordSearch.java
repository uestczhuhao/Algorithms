package leetcode;

/**
 * @author mizhu
 * @date 2021/1/27 10:15
 */
public class _79WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean result;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                result = findWord(board, visited, word, 0, i, j);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 回溯法，从word的chIndex和数组的row行，column开始，是否能找到匹配的记录
     */
    public boolean findWord(char[][] board, boolean[][] visited, String word, int chIndex, int row, int column) {
        if (chIndex == word.length()) {
            return true;
        }

        // 越界了
        if (row > board.length - 1 || row < 0
            || column > board[0].length - 1 || column < 0
            || visited[row][column]) {
            return false;
        }

        boolean result = false;
        if (word.charAt(chIndex) == board[row][column]) {
            visited[row][column] = true;
            result = findWord(board, visited, word, chIndex + 1, row + 1, column)
                || findWord(board, visited, word, chIndex + 1, row, column + 1)
                || findWord(board, visited, word, chIndex + 1, row - 1, column)
                || findWord(board, visited, word, chIndex + 1, row, column - 1);
            visited[row][column] = false;
        }

        return result;

    }

    public static void main(String[] args) {
        _79WordSearch t = new _79WordSearch();
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        char[][] board1 = {
            {'C', 'A', 'A'},
            {'A', 'A', 'A'},
            {'B', 'C', 'D'}
        };
        String word = "AAB";
        System.out.println(t.exist(board1, word));
    }
}
