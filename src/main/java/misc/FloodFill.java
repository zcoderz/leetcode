package misc;

public class FloodFill {


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] != newColor) {
            dfs(image, sr, sc, image[sr][sc], newColor);
        }
        return image;
    }

    void dfs(int[][] image, int sr, int sc, int color, int newColor) {
        if (image[sr][sc] == color) {
            image[sr][sc] = newColor;

            if (sr > 0) {
                dfs(image, sr-1, sc, color, newColor);
            }
            if (sc > 0) {
                dfs(image, sr, sc-1, color, newColor);
            }
            if (sr+1 < image.length) {
                dfs(image,sr+1, sc, color, newColor);
            }
            if (sc+1 < image[0].length) {
                dfs(image, sr, sc+1, color, newColor);
            }
        }
    }
}
