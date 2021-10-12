package algorithm.prim;

public class HoneyComb {
    private int [][]map;
    private int mapMaxX;
    private int mapMaxY;
    private int length;
    private boolean rotate = false;

    private int []dirX = {1, 2, 1, -1, -2, -1};
    private int []dirY = {1, 0, -1, -1, 0, 1};


    public void setLength(int length) {
        if (length > 3) {
            mapMaxX  = length *3+(length-3);
        } else {
            mapMaxX = length *3;
        }

        mapMaxY = length*2;
        this.length = length;
    }

    public boolean checkNextStep(int row, int col) {
        if (row >= mapMaxX || row < 0 || col >= mapMaxY || col < 0) {
            return false;
        } else {
            return true;
        }
    }

    public void solve(int x, int y,int cnt, int num, int dir) {

        map[x][y] = num;

        // 방향 전환
        if (cnt == length) {
            cnt = 1;
            dir = (dir + 1) % dirX.length;
        }

        // 대각선 방향 위로 진행 될때 이미 값이 있는 경우는
        // 대각선 아래 방향밖에 없다.
        if(dir == 5 && map[x+dirX[dir]][y+dirY[dir]] != 0) {
            cnt = 1;
            dir = (dir + 1) % dirX.length;
            rotate =true;
        }

        // 회전 했을 경우 아래로 진행될때
        // 최대 길이를 1 줄인다
        if (rotate && dir == 1) {
            length--;
            rotate = false;
        }


        if (checkNextStep(x+dirX[dir], y+dirY[dir])) {
            if (map[x+dirX[dir]][y+dirY[dir]] == 0) {
                solve(x+dirX[dir],  y+dirY[dir], cnt+1, num+1, dir);
            }
        }
    }

    public void solution() {
        map = new int[mapMaxX][mapMaxY];

        solve(0,mapMaxY/2,1,1,0);
    }

    public void printMap() {
        for(int i = 0; i < mapMaxX; i++) {
            for(int j = 0; j < mapMaxY; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        HoneyComb honeyComb = new HoneyComb();
        try {

            honeyComb.setLength(3);
            honeyComb.solution();
            honeyComb.printMap();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
