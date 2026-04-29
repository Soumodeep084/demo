import java.util.*;

public class exp7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 5 events in the belief network:");
        String Bname = sc.next();
        String Ename = sc.next();
        String Aname = sc.next();
        String Jname = sc.next();
        String Mname = sc.next();

        System.out.print("\nEnter Prior Probabilities:");
        System.out.print("P(" + Bname + "): ");
        double P_B = sc.nextDouble();
        System.out.print("P(" + Ename + "): ");
        double P_E = sc.nextDouble();
        System.out.println("\nEnter Alarm Probabilities:");
        System.out.print("P(A|B,E): ");
        double P_A_BE = sc.nextDouble();
        System.out.print("P(A|B,~E): ");
        double P_A_BnE = sc.nextDouble();
        System.out.print("P(A|~B,E): ");
        double P_A_nBE = sc.nextDouble();
        System.out.print("P(A|~B,~E): ");
        double P_A_nBnE = sc.nextDouble();
        System.out.println("\nEnter Call Probabilities:");
        System.out.print("P(J|A): ");
        double P_J_A = sc.nextDouble();
        System.out.print("P(J|~A): ");
        double P_J_nA = sc.nextDouble();
        System.out.print("P(M|A): ");
        double P_M_A = sc.nextDouble();
        System.out.print("P(M|~A): ");
        double P_M_nA = sc.nextDouble();

        System.out.print("\nEnter number of queries: ");
        int q = sc.nextInt();

        for(int i=1;i<=q;i++){
            System.out.println("\nEnter query "+i+" (B E A J M) using 1/0:");
            int B = sc.nextInt();
            int E = sc.nextInt();
            int A = sc.nextInt();
            int J = sc.nextInt();
            int M = sc.nextInt();

            double pB = (B==1)? P_B : (1-P_B);
            double pE = (E==1)? P_E : (1-P_E);
            double pA;
            if(B==1 && E==1)
                pA = P_A_BE;
            else if(B==1 && E==0)
                pA = P_A_BnE;
            else if(B==0 && E==1)
                pA = P_A_nBE;
            else
                pA = P_A_nBnE;
            if(A==0)
                pA = 1-pA;

            double pJ = (A==1)? P_J_A : P_J_nA;
            if(J==0)
                pJ = 1-pJ;
            double pM = (A==1)? P_M_A : P_M_nA;
            if(M==0)
                pM = 1-pM;
            double result = pB * pE * pA * pJ * pM;
            String b = (B==1) ? "B" : "~B";
            String e = (E==1) ? "E" : "~E";
            String a = (A==1) ? "A" : "~A";
            String j = (J==1) ? "J" : "~J";
            String m = (M==1) ? "M" : "~M";
            System.out.printf("P(%s,%s,%s,%s,%s) = %.8f\n", b,e,a,j,m,result);
        }
    }
}
