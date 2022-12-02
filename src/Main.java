import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[][] a = new double[3][4];
        double[][] temp = new double[3][4];
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if(j != 3){
                    System.out.println("Print a_" + i + j + " coefficient");
                }
                else{
                    System.out.println("Print b_" + i + " coefficient");
                }
                a[i][j] = in.nextDouble();
                temp[i][j] = a[i][j];
            }
        }

        double q = Counter.getNorm(a);
        double epsilon = 0.000001;
        double stopCond = Math.abs(((1 - q)/q) * epsilon);

        Counter.transpose(temp);
        temp =  Counter.getAlgebraicAdditionsMatrix(temp);
        double[][] reversed = Counter.getReversed(temp, Counter.getDeterminant(a));
        double conditionNumber = Counter.getConditionNumber(a, reversed);
        double[] equation = new double[4];
        Counter.getCharacteristicEquationCoefficients(equation, a);


        double oldX1 = 1.2;
        double oldX2 = 1.2;
        double oldX3 = 1.2;

        double newX1 = (1 / a[0][0]) * (a[0][3] - a[0][1] * oldX2 - a[0][2] * oldX3);
        double newX2 = (1 / a[1][1]) * (a[1][3] - a[1][0] * oldX1 - a[1][2] * oldX3);
        double newX3 = (1 / a[2][2]) * (a[2][3] - a[2][0] * oldX1 - a[2][1] * oldX2);

        while ((Math.abs(newX1 - oldX1) > stopCond) || (Math.abs(newX2 - oldX2) > stopCond) || (Math.abs(newX3 - oldX3) > stopCond)){
            oldX1 = newX1;
            oldX2 = newX2;
            oldX3 = newX3;
            newX1 = (1 / a[0][0]) * (a[0][3] - a[0][1] * oldX2 - a[0][2] * oldX3);
            newX2 = (1 / a[1][1]) * (a[1][3] - a[1][0] * oldX1 - a[1][2] * oldX3);
            newX3 = (1 / a[2][2]) * (a[2][3] - a[2][0] * oldX1 - a[2][1] * oldX2);

        }

        System.out.println("x1 = " + newX1);
        System.out.println("x2 = " + newX2);
        System.out.println("x3 = " + newX3);
        System.out.println("Matrix norm = " + q);
        System.out.println("Matrix condition number = " + conditionNumber);
        for(int i = 0; i < 4; i++)
            System.out.println("Characteristic equation coefficients: " + equation[i]);

    }
}
