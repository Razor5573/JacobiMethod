public class Counter {
    public static double[][] matrixMultiplication(double[][] a, double[][] b){
        double[][] result = new double[3][3];
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                for(int k = 0; k < 3; k++)
                {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static void getCharacteristicEquationCoefficients(double[] equation, double[][] a){
        double[][] b = new double[3][3];
        double[][] result = new double[3][3];
        equation[0] = 1;
        equation[1] = a[0][0] + a[1][1] + a[2][2];

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(i == j)
                    b[i][j] = a[i][j] - equation[1];
                else
                    b[i][j] = a[i][j];
            }
        }
        result = Counter.matrixMultiplication(a, b);
        equation[2] = (result[0][0] + result[1][1] + result[2][2]) / 2;
        equation[3] = Counter.getDeterminant(a);
    }

    public static double[][] getAlgebraicAdditionsMatrix(double[][] a){
        double[][] minor = new double[2][2];
        double[][] additionalMatrix = new double[3][3];
        int i = 2;
        minor[0][0] = a[1][1]; minor[0][1] = a[1][2]; minor[1][0] = a[2][1]; minor[1][1] = a[2][2]; additionalMatrix[0][0] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[1][0]; minor[0][1] = a[1][2]; minor[1][0] = a[2][0]; minor[1][1] = a[2][2]; additionalMatrix[0][1] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[1][0]; minor[0][1] = a[1][1]; minor[1][0] = a[2][0]; minor[1][1] = a[2][1]; additionalMatrix[0][2] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[0][1]; minor[0][1] = a[0][2]; minor[1][0] = a[2][1]; minor[1][1] = a[2][2]; additionalMatrix[1][0] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[0][0]; minor[0][1] = a[0][2]; minor[1][0] = a[2][0]; minor[1][1] = a[2][2]; additionalMatrix[1][1] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[0][0]; minor[0][1] = a[0][1]; minor[1][0] = a[2][0]; minor[1][1] = a[2][1]; additionalMatrix[1][2] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[0][1]; minor[0][1] = a[0][2]; minor[1][0] = a[1][1]; minor[1][1] = a[1][2]; additionalMatrix[2][0] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[0][0]; minor[0][1] = a[0][2]; minor[1][0] = a[1][0]; minor[1][1] = a[1][2]; additionalMatrix[2][1] = Math.pow(-1, i) * getMinorDeterminate(minor); i++;
        minor[0][0] = a[0][0]; minor[0][1] = a[0][1]; minor[1][0] = a[1][0]; minor[1][1] = a[1][1]; additionalMatrix[2][2] = Math.pow(-1, i) * getMinorDeterminate(minor);

        return additionalMatrix;
    }


    public static double getNorm(double[][] a) {
        double max = Double.MIN_VALUE;
        double sum = 0.0;

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                sum += a[i][j];
                if(sum > max){
                    max = sum;
                }
            }
            sum = 0;
        }

        return max;
    }

    public static double getDeterminant(double[][] a) {
        return a[0][0] * a[1][1] * a[2][2] + a[0][1] * a[1][2] * a[2][0] + a[0][2] * a[1][0] * a[2][1] -
                a[0][2] * a[1][1] * a[2][0] - a[0][0] * a[1][2] * a[2][1] - a[0][1] * a[1][0] * a[2][2];
    }
    public static double getMinorDeterminate(double[][] a){
        return a[0][0] * a[1][1] - a[0][1] * a[1][0];
    }
    public static void transpose(double [][] a){
        double tmp;

        tmp = a[0][1];
        a[0][1] = a[1][0];
        a[1][0] = tmp;

        tmp = a[0][2];
        a[0][2] = a[2][0];
        a[2][0] = tmp;

        tmp = a[1][2];
        a[1][2] = a[2][1];
        a[2][1] = tmp;

    }

    public static double[][] getReversed (double [][] a_T, double det) {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                a_T[i][j] = a_T[i][j] / det;
            }
        }
        return a_T;
    }

    public static double getConditionNumber(double[][] a, double [][] a_reversed){
        return getNorm(a) * getNorm(a_reversed);
    }
}

