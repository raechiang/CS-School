public class Driver {

    public static void main(String[] args)
    {
        // tests given base cases
        //Tester baseTester = new Tester(0);
        
        // tests randomly generated doubles for different sizes
        for (int i = 2; i < 128; i *= 2)
        {
            System.out.println("SIZE: " + i);
            Tester genTester = new Tester(i);
        }
    }
}