package com.coding;

public class BinaryHeap {

	private int[] heapArr;
	private int heapCapacity;	//Capacity of heap
	private int curHeapSize;	//Current size of heap
	
	public BinaryHeap(int cap){
		curHeapSize = 0;
		heapCapacity = cap;
		heapArr = new int[heapCapacity];
	}
	
	public int getParent(int index){
		return (index-1) / 2;
	}
	
	public int getLeftChild(int index){
		return (2 * index) + 1;
	}
	
	public int getRightChild(int index){
		return (2 * index) + 2;
	}
	
	//Swap the heapArr elements for given two indices
	private void swap(int i, int j){
		int temp = heapArr[i];
		heapArr[i] = heapArr[j];
		heapArr[j] = temp;
	}
	
	public void insertKey(int k){
		
		if(curHeapSize == heapCapacity){
			System.out.println("Overflow! Can not insert more elements.");
			return;
		}
		
		//Insert the new heap element
		curHeapSize++;
		int index = curHeapSize - 1;
		heapArr[index] = k;
		
		//Correct the heapArr if min heap property is violated.
		while(index != 0 && heapArr[getParent(index)] > heapArr[index]){
			swap(index, getParent(index));
			index = getParent(index);
		}		
	}
	
	//Decrease the value at given index
	public void decreseKey(int index, int newVal){
		heapArr[index] = newVal;
		
		//Correct the heapArr if min heap property is violated.
		while(index != 0 && heapArr[getParent(index)] > heapArr[index]){
			swap(index, getParent(index));
			index = getParent(index);
		}
	}
	
	public void minHeapify(int index){
		
		int left = getLeftChild(index);
		int right = getRightChild(index);
		int smallest = index;
		
		if(left < curHeapSize && heapArr[left] < heapArr[index])
			smallest = left;
		
		if(right < curHeapSize && heapArr[right] < heapArr[smallest])
			smallest = right;
		
		if(smallest != index){
			swap(smallest, index);
			minHeapify(smallest);
		}
	}
	
	//Get the min element in the heap (Root)
	public int extractMin(){
		if(curHeapSize <= 0)
			return Integer.MAX_VALUE;
		
		if(curHeapSize == 1){
			curHeapSize--;
			return heapArr[0];
		}
		
		int root = heapArr[0];
		heapArr[0] = heapArr[curHeapSize-1];
		curHeapSize--;
		
		//Heapify the root to maintain heap property
		minHeapify(0);
		
		return root;
	}
	
	//Delete the key at index i
	public void deleteKey(int i){
		decreseKey(i, Integer.MIN_VALUE);
		extractMin();
	}
	
	//Prints the heap
	public void printHeap(){
		
		for(int i = 0; i < curHeapSize; i++)
			System.out.print(heapArr[i] + " ");
	}
	
	public static void main(String[] args) {

		BinaryHeap heap = new BinaryHeap(3);
		heap.insertKey(5);
		heap.insertKey(3);
		heap.insertKey(2);
		
		heap.printHeap();
	}

}
