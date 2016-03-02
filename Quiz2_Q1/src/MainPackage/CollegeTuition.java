package MainPackage;

import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Test;
import org.apache.poi.ss.formula.functions.Finance;

//Adam Caulfield

public class CollegeTuition{
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please Enter your intial cost for tuition per semester: ");
		double semestercost = input.nextDouble();
		
		System.out.print("Please Enter the total years of college you plan to attend: ");
		int n = input.nextInt();
		
		System.out.print("Please Enter your fixed APR: ");
		double r = input.nextDouble();
		
		System.out.print("Please enter the percentage increase of your tuition every year AS A DECIMAL: ");
		double increment = input.nextDouble();
		
		double totalCollegeCost = getTotalCost(semestercost,n,increment);
	
		double monthlypay = getPMT(r,n,totalCollegeCost,0.0,1);
	
		System.out.print("You must make monthly payments of $");
		System.out.printf("%.2f%n", monthlypay);
		System.out.print(" for "+n+" years.");
	}
	
	public static double getPMT(double rate,int numyears,double cost,double f,int t){
		
		/**Multiply by the -1 because this function will return a negative number
		 * due to the fact that the person is paying the money, and the future value is 
		 * smaller than the initial value (losing the money over time)
		 */
		
		double PMT = -1*(Finance.pmt(rate, numyears, cost, f, t));
		return(PMT);
	}
	
	public static double getTotalCost(double semestercost,int period, double percentage){
		double totalcost = 2*semestercost;
		for(int i = 0; i<period;i++){
			totalcost = totalcost+totalcost*(1+percentage);
		}
		return(totalcost);
	}
	

	//TESTS
	
	@Test
	public void test1forGetPMT() {
			double cost = getPMT(0.05,4,getTotalCost(13000,4,0.01),0,1);
			assertTrue(cost-113981.8<0.1); //cannot compare exact doubles, so this shows the values are very close
		}
	@Test
	public void test2forGetPMT(){
		double cost = getPMT(0.07,3,getTotalCost(12500,3,0.02),0,1);
		assertTrue(cost-73382.7<0.1); //cannot compare exact doubles, so this shows the values are very close
	}
	@Test
	public void test1forGetTotalCost(){
		double c = getTotalCost(13500,4,0.005);
		assertTrue(c-436336.22<0.01); //cannot compare exact doubles, so this shows the values are very close
	}
	
	@Test
	public void test2forGetTotalCost(){
		double c = getTotalCost(14250,2,0.0075);
		assertTrue(c-114856.6031<0.0001); //cannot compare exact doubles, so this shows the values are very close
	}
	
}