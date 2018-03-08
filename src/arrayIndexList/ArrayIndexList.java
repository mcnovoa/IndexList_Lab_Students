package arrayIndexList;

import java.lang.reflect.Array;
import java.util.Iterator;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 


	public void add(int index, E e) throws IndexOutOfBoundsException {
		if(index < 0 || index > size) 
			throw new IndexOutOfBoundsException("Invalid index = " + index);

		//If true add a space via changeCapacity method to the array
		if(size == element.length) 
			changeCapacity(CAPTOAR);

		moveDataOnePositionTR(index, size - 1 );
		element[index] = e;
		size++;
	}


	public void add(E e) {
		//If true add a space via changeCapacity method to the array
		if(size == element.length) 
			changeCapacity(CAPTOAR);

		element[element.length-1] = e;
		size++;

	}


	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size-1) 
			throw new IndexOutOfBoundsException("Invalid index = " + index);

		return element[index]; 
	}


	public boolean isEmpty() {
		return size == 0;
	}


	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size-1) 
			throw new IndexOutOfBoundsException("Invalid index = " + index);

		E etr = element[index]; //Old value
		element[index] = null;
		moveDataOnePositionTL(index + 1, size-1);
		size--;

		//If true eliminate a space via changeCapacity method of the array
		if((element.length - size) > MAXEMPTYPOS)
			changeCapacity(-CAPTOAR);

		return etr;
	}


	public E set(int index, E e) throws IndexOutOfBoundsException {
		if(index < 0 || index > size-1) 
			throw new IndexOutOfBoundsException("Invalid index = " + index);

		E etr = element[index]; //Old Value
		element[index] = e; //New Value
		return etr;
	}


	public int size() {
		return size;
	}	



	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED

	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING

	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}

	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}


	// The following two methods are to be implemented as part of an exercise
	public Object[] toArray() {
		Object[] newArr = new Object[this.size()];

		for (int i = 0; i < this.size(); i++) 
			newArr[i] = element[i];

		return newArr;
	}


	@Override
	public <T1> T1[] toArray(T1[] array) {
		if (array.length < this.size()) { 
			//Create a new instance of the array with specified type and size
			//Casting is needed for the type of array to return (T1[])
			array = (T1[]) Array.newInstance(array.getClass().getComponentType(), this.size());
		} 
		else if (array.length > this.size()) 
			//Assign null to values on the array that aren't necessary 
			for (int i = this.size(); i< array.length; i++) 
				array[i] = null;


		for (int k = 0; k < this.size(); k++) {
			//Cast each element of element[] as T1 
			array[k] = (T1) element[k];   
		}

		return array; 
	}

	@Override
	public int capacity() {
		return element.length; 
	}

}

