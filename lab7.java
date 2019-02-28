class lab7 {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };

        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);
        // System.out.println(matC_r);
        // System.out.println(matC_c);
        //

        Thread[][] child = new Thread[matC_r][matC_c];

        for (int i = 0; i < matC_r; i++) {

            for (int j = 0; j < matC_c; j++) {
                child[i][j] = new Child(i, j, matA, matB, matC);
                child[i][j].start();
            }
        }
        for (int i = 0; i < matC_r; i++) {

            for (int j = 0; j < matC_c; j++) {
                try {
                    child[i][j].join();
                } catch (InterruptedException ie) {
                }

            }
        }
        matC.show();
    }

}

class Child extends Thread {
    int row, col;
    MyData datA, datB, datC;

    Child(int processing_row, int processing_col, MyData a, MyData b, MyData c) {
        row = processing_row;
        col = processing_col;
        datA = a;
        datB = b;
        datC = c;

    }

    public void run() {
        int sum = 0;
        for (int k = 0; k < 3; k++) {

            sum += datA.data[row][k] * datB.data[k][col];
            // System.out.print(datA.data[row][k] + " ");
            // System.out.print(datB.data[k][col] + " ");
            // System.out.println();
        }
        datC.data[row][col] = sum;

    }
}

class MyData {
    int[][] data;

    MyData(int r, int c) {
        data = new int[r][c];

    }

    MyData(int[][] a) {
        data = a;
    }

    void show() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j] + " ");

            }
            System.out.println();
        }
    }
}