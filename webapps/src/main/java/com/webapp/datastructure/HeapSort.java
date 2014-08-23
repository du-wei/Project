package com.webapp.datastructure;

public class HeapSort {
	public static void main(String[] args) {
		int[] data = { 2, 8, 10, 51, 3, 7, 20 };
		System.out.println("前" + java.util.Arrays.toString(data));
		heapSort(data);
		System.out.println("后" + java.util.Arrays.toString(data));
	}

	public static void heapSort(int[] data) {
		int length = data.length;
		for (int i = 0; i < length; i++) {
			buildMaxHeap(data, length - i); // 找出前 length-1个数中最大的一个数放在堆顶
			swap(data, 0, length - i - 1); // 将最大的那个数放到 length-i-1的位置上
			System.out.println(java.util.Arrays.toString(data));
		}
	}

	private static void buildMaxHeap(int[] data, int lastIndex) {
		int lastNode = lastIndex / 2 - 1; // 在lastIndex范围内，最后一个非叶子节点为
		// lastIndex/2-1
		for (int i = lastNode; i >= 0; i--) { // 遍历lastNode以及以前的所有几点
			int k = i; // 保存当前正在判断的节点
			while (k * 2 + 1 < lastIndex) { // 如果当前k节点的子节点存在
				int bigIndex = k * 2 + 1; // k节点的左子节点的索引
				if (bigIndex < lastIndex - 1) { // 代表k节点的右子节点存在
					if (data[bigIndex] < data[bigIndex + 1]) { // 如果右节点的值较大
						bigIndex++; // 总数记录较大节点的索引
					}
				}
				if (data[k] < data[bigIndex]) {
					swap(data, k, bigIndex);
					k = bigIndex; // 为了重新保证bigIndex节点的值大于其左右子节点的值，就是下一次循环要设置的
				} else {
					break;
				}
			}
		}
	}

	private static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}
