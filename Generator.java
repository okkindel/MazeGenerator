/*Copywright by Hajduczek & Maku*/

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Generator
{
	private ArrayList <Cell> cells = new ArrayList<Cell>(); 
	protected int size, loop_counter, numberOfRow = 1;
	public String[][] table;

	public Generator(int width, int height)
	{
		loop_counter = height;
		size = width;
		table = new String[30*width][30*height];

		/*filling array with '#'*/
		for(int i = 0; i < 30*width; i++){
			for(int j = 0; j < 30*height; j++){
				table[i][j] = "###";
			}
		}

		/*adding cells to array list*/
		for (int i = 1; i <= size+1; i++){ 
			Cell cell = new Cell(i);
			cells.add(cell);
		}
	}

	public void launcher()
	{
		/*first row*/
		row_generete();
		show();

		/*Char output current*/
		System.out.print(" ");
		for(int i = 0; i <= (3*size)-2; i++){
			System.out.print("_");
		}

		for (int i=1; i<=loop_counter; i++){
			write_down();
			//show();
			alternative_out();
			next_row();
			numberOfRow+=2;
			row_generete();
		}

		System.out.print("\n ");

		for(int i = 0; i <= (3*size)-2; i++){
			System.out.print("-");	
		}

		System.out.println("\n");

		/*Text output from array*/
		for(int i = 0; i <= size; i++){
			System.out.print(table[0][i]);
		}

		numberOfRow=1;

		for (int i=1; i<=loop_counter; i++){
			print();
			numberOfRow+=2;
		}

		System.out.println();

		for(int i = 0; i <= size; i++){
			System.out.print(table[0][i]);
		}

		System.out.println();
	}

	public void row_generete()
	{
		Random setGenerator = new Random();

		/*random vertical walls*/
		for (int i = 1; i <= size - 1; i++){
			if (cells.get(i).set==cells.get(i+1).set)
				cells.get(i).wall = true;
			if (setGenerator.nextInt(2) == 0){
				cells.get(i).wall = true;
			}
			else{
				cells.get(i+1).set = cells.get(i).set;
			}
		}

		/*random horizontal walls*/
		boolean was_break = false;
		try {

		for (int i=1; i<=size-1; i++)
		{
			if (cells.get(i+1).set==cells.get(i).set)
			{
				if (setGenerator.nextInt(2)==1){
					cells.get(i).bottom = false;
					was_break = true;
				}
				else
					cells.get(i).bottom = true;
			}
			if (cells.get(i+1).set!=cells.get(i).set)
			{
				if (was_break==false){
					cells.get(i).bottom = false;
					was_break = false;
				}
				else{
					if (setGenerator.nextInt(2)==1)
					cells.get(i).bottom = false;
					else
					cells.get(i).bottom = true;
				was_break = false;
				}
			}
		}
		}
		catch (Exception e){
			System.out.println( "Something wrong with " + e );
		}

	}

	public void write_down(){
		try{
		for(int i = 1; i <= size-1; i++)
		{
			if (i != size)
			{
				if(cells.get(i).wall == false){
					table[numberOfRow][i] = "   ";
				}
				else if(cells.get(i).wall == true){
					table[numberOfRow][i] = " | ";
				}
			}
			else{
				table[numberOfRow+1][i] = "___";}
		}

		for(int i = 1; i <= size-1; i++)
		{
			if (i != size-1)
			{
				if (cells.get(i).bottom == false){
					if (table[numberOfRow][i] == " | "){
						if (cells.get(i+1).bottom == true)
							table[numberOfRow+1][i] = " | ";
						else if (cells.get(i+1).bottom == false)
							table[numberOfRow+1][i] = " | ";}
					else
						table[numberOfRow+1][i] = "   ";
				}
				else if(cells.get(i).bottom == true){
					table[numberOfRow+1][i] = "___";
				}
			}
			else{
				table[numberOfRow+1][i] = "___";}
		}
		}
		catch(Exception e){
			System.out.println( "Something wrong with " + e );
		}
	}

	public void next_row(){
		Random setGenerator = new Random();
		System.out.print("");

		/*clearing array list for next row*/
		for (int i = 1; i<=size; i++)
		{
			cells.get(i).wall = false;
			if (cells.get(i).bottom==true){
				cells.get(i).set=setGenerator.nextInt(10000);
				cells.get(i).bottom=false;
			}
		}
	}

	public void show()
	{
		System.out.println();

		/*printing vertical walls*/
		System.out.print("|");
		for (int i = 1; i <=size; i++)
		{
			if (cells.get(i).wall == false)
				System.out.print(cells.get(i).set + "");
			else if(cells.get(i).wall == true)
				System.out.print(cells.get(i).set + "|");
			if (i == size)
				System.out.print("|\n");
		}

		/*printing horizontal walls*/
		System.out.print(" ");
		for(int i = 1; i <=size-1; i++)
		{
			if (cells.get(i).set < 10)
			{
				if (cells.get(i).bottom == true)
					System.out.print("-");
				else
					System.out.print(" ");
				if (cells.get(i+1).set!=cells.get(i).set)
					System.out.print(" ");
			}
			else
			{
				if (cells.get(i).bottom == true)
					System.out.print("--");
				else
					System.out.print("  ");
				if (cells.get(i+1).set!=cells.get(i).set)
					System.out.print(" ");
			}
		}
		System.out.println("\n");
	}

	public void print()
	{
		System.out.println();
		for(int i = 0; i <= size; i++){
			System.out.print(table[numberOfRow][i]);
		}
		System.out.println();
		for(int i = 0; i <= size; i++){
			System.out.print(table[numberOfRow+1][i]);
		}
		System.out.print("");
	}

	public void alternative_out ()
	{
		try{
		System.out.print("\n |");
		for (int i=1; i <=size-1; i++)
		{
			if (cells.get(i).wall == false && cells.get(i).bottom == true)
				System.out.print("___");
			if (cells.get(i).wall == false && cells.get(i).bottom == false)
				System.out.print("   ");
			if (cells.get(i).wall == true && cells.get(i).bottom == false)
				System.out.print(" | ");
			if (cells.get(i).wall == true && cells.get(i).bottom == true){
				if (cells.get(i+1).bottom == true)
					System.out.print("_| ");
				else if (cells.get(i+1).bottom == false)
					System.out.print(" |_");
			}		
		}
		System.out.print("|");
		}
		catch (Exception e){
			System.out.println( "Something wrong with " + e );
		}
	}
}