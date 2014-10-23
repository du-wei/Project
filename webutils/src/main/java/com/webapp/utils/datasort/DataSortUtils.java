package com.webapp.utils.datasort;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.ArrayUtils;

/**
* @ClassName: DataSortUtils.java
* @Package com.webapp.utils.datasort
* @Description: Data Sort Utils
* @author  king king
* @date 2014年10月15日 下午11:49:38
* @version V1.0
*/
public class DataSortUtils {

	public enum OrderType {
		desc("desc", "降序"), asce("asce", "升序"), //
		shell_desc("shell_desc", "插入排序->Shell排序->稳定"), //
		insert_desc("insert_desc", "插入排序->直接插入排序->稳定"), //
		quick_desc("quick_desc", "交换排序->快速排序->不稳定"), //
		bubble_desc("bubble_desc", "交换排序->冒泡排序->稳定"), //
		heap_desc("heap_desc", "选择排序 ->堆排序->不稳定"), //
		select_desc("select_desc", "选择排序 ->直接选择排序->已改进->不稳定"), //
		binaryInsert_desc("binaryInsert_desc", "插入排序->折半插入排序->稳定"), //
		shell_asce("shell_asce", "插入排序->Shell排序->稳定"), //
		insert_asce("insert_asce", "插入排序->直接插入排序->稳定"), //
		quick_asce("quick_asce", "交换排序->快速排序->不稳定"), //
		bubble_asce("bubble_asce", "交换排序->冒泡排序->稳定"), //
		heap_asce("heap_asce", "选择排序 ->堆排序->不稳定"), //
		select_asce("select_asce", "选择排序 ->直接选择排序->已改进->不稳定"), //
		binaryInsert_asce("binaryInsert_asce", "插入排序->折半插入排序->稳定"); //

		private String key;
		private String comment;

		OrderType(String key, String comment) {
			this.key = key;
			this.comment = comment;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
	}

	public static byte[] sort(byte[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static char[] sort(char[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static double[] sort(double[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static float[] sort(float[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static int[] sort(int[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static long[] sort(long[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static short[] sort(short[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static byte[] sort(byte[] data, OrderType type) {
		return ArrayUtils.toPrimitive(getOrderSort(ArrayUtils.toObject(data), type));
	}

	public static char[] sort(char[] data, OrderType type) {
		return ArrayUtils.toPrimitive(getOrderSort(ArrayUtils.toObject(data), type));
	}

	public static double[] sort(double[] data, OrderType type) {
		return ArrayUtils.toPrimitive(getOrderSort(ArrayUtils.toObject(data), type));
	}

	public static float[] sort(float[] data, OrderType type) {
		return ArrayUtils.toPrimitive(getOrderSort(ArrayUtils.toObject(data), type));
	}

	public static int[] sort(int[] data, OrderType type) {
		return ArrayUtils.toPrimitive(getOrderSort(ArrayUtils.toObject(data), type));
	}

	public static long[] sort(long[] data, OrderType type) {
		return ArrayUtils.toPrimitive(getOrderSort(ArrayUtils.toObject(data), type));
	}

	public static short[] sort(short[] data, OrderType type) {
		return ArrayUtils.toPrimitive(getOrderSort(ArrayUtils.toObject(data), type));
	}

	public static <T extends Comparable<T>> T[] sort(T[] data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static <T extends Comparable<T>> T[] sort(T[] data, OrderType type) {
		return getOrderSort(data, type);
	}

	public static <T extends Comparable<T>> List<T> sort(List<T> data) {
		return sort(data, OrderType.binaryInsert_asce);
	}

	public static <T extends Comparable<T>> List<T> sort(List<T> data, OrderType type) {
		return sort(data, type, null);
	}

	public static <T> T[] sort(T[] data, OrderType type, Comparator<T> comp) {
		return getOrderSort(data, type, comp);
	}

	public static <T> List<T> sort(List<T> data, OrderType type, Comparator<T> comp) {
		T[] tArr = null;
		tArr = data.toArray(tArr);
		getOrderSort(tArr, type, comp);
		ListIterator<T> i = data.listIterator();
		for (int j = 0; j < tArr.length; j++) {
			i.next();
			i.set(tArr[j]);
		}
		return data;
	}

	// 插入排序->Shell排序 稳定
	private static <T> T[] shellSort(T[] data, Comparator<T> comp) {
		int length = data.length;
		int h = 1;
		while (h <= length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			for (int i = h; i < length; i++) {
				T tmp = data[i];
				// if (comp.compare(data[i], data[i - h]) < 0) {
				if (compare(data[i], data[i - h], comp) < 0) {
					int j = i - h;
					// for (; j >= 0 && comp.compare(data[i], tmp) > 0; j -= h)
					// {
					for (; j >= 0 && compare(data[i], tmp, comp) > 0; j -= h) {
						data[j + h] = data[j];
					}
					data[j + h] = tmp;
				}
			}
			h = (h - 1) / 3;
		}
		return data;
	}

	// 插入排序->折半插入排序 稳定
	private static <T> T[] binaryInsertSort(T[] data, Comparator<T> comp) {
		for (int i = 1; i < data.length; i++) {
			T temp = data[i];
			int low = 0;
			int high = i - 1;
			while (low <= high) {
				int mid = (low + high) / 2;
				if (compare(temp, data[mid], comp) > 0) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
			for (int j = i; j > low; j--) {
				data[j] = data[j - 1];
			}
			data[low] = temp;
		}
		return data;
	}

	// 插入排序->直接插入排序 稳定
	private static <T> T[] insertSort(T[] data, Comparator<T> comp) {
		for (int i = 1; i < data.length; i++) {
			T temp = data[i];
			// if (comp.compare(data[i], data[i - 1]) < 0) {
			if (compare(data[i], data[i - 1], comp) < 0) {
				int j = i - 1;
				// for (; j >= 0 && comp.compare(data[j], temp) > 0; j--) {
				for (; j >= 0 && compare(data[j], temp, comp) > 0; j--) {
					data[j + 1] = data[j];
				}
				data[j + 1] = temp;
			}
		}
		return data;
	}

	// 交换排序->快速排序 不稳定
	private static <T> T[] quickSort(T[] data, Comparator<T> comp) {
		return quickSort(data, 0, data.length - 1, comp);
	}

	// 交换排序->冒泡排序 稳定
	private static <T> T[] bubbleSort(T[] data, Comparator<T> comp) {
		for (int i = 0; i < data.length - 1; i++) {
			boolean flag = false;
			for (int j = 0; j < data.length - 1 - i; j++) {
				// if (comp.compare(data[j], data[j + 1]) > 0) {
				if (compare(data[j], data[j + 1], comp) > 0) {
					swap(data, j, j + 1);
					flag = true;
				}
			}
			if (!flag) break;
		}
		return data;
	}

	// 选择排序 ->直接选择排序->已改进 不稳定
	private static <T> T[] selectSort(T[] data, Comparator<T> comp) {
		int length = data.length;
		int minIndex;
		for (int i = 0; i < length - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < length; j++) {
				if (compare(data[j], data[minIndex], comp) < 0) minIndex = j;
			}
			if (minIndex != i) swap(data, minIndex, i);
		}
		return data;
	}

	// 选择排序 ->堆排序 不稳定
	private static <T> T[] heapSort(T[] data, Comparator<T> comp) {
		int length = data.length;
		for (int i = 0; i < length; i++) {
			buildMaxHeap(data, length - i, comp);
			swap(data, 0, length - i - 1);
		}
		return data;
	}

	private static <T> T[] buildMaxHeap(T[] data, int lastIndex, Comparator<T> comp) {
		int lastNode = lastIndex / 2 - 1;
		for (int i = lastNode; i >= 0; i--) {
			int k = i;
			while (k * 2 + 1 < lastIndex) {
				int bigIndex = k * 2 + 1;
				if (bigIndex < lastIndex - 1) {
					// bigIndex += comp.compare(data[bigIndex],
					// data[bigIndex+1]) < 0 ? 1 : 0;
					bigIndex += compare(data[bigIndex], data[bigIndex + 1], comp) < 0 ? 1 : 0;
				}
				// if(comp.compare(data[k], data[bigIndex]) < 0){
				if (compare(data[k], data[bigIndex], comp) < 0) {
					swap(data, k, bigIndex);
					k = bigIndex;
				} else {
					break;
				}
			}
		}
		return data;
	}

	private static <T> T[] quickSort(T[] data, int start, int end, Comparator<T> comp) {
		if (start < end) {
			// T base = data[start];
			int i = start;
			int j = end + 1;
			while (true) {
				// while (i < end && compare(data[++i], base, comp) <= 0);
				// while (j > start && compare(data[--j], base, comp) >= 0);
				if (i < j) {
					swap(data, i, j);
				} else {
					break;
				}
			}
			swap(data, start, j);
			quickSort(data, start, j - 1, comp);
			quickSort(data, j + 1, end, comp);
		}
		return data;
	}

	private static <T> void swap(T[] data, int i, int j) {
		T temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	private static <T extends Comparable<T>> T[] getOrderSort(T[] data, OrderType type) {
		return getOrderSort(data, type, null);
	}

	private static <T> T[] getOrderSort(T[] data, OrderType type, Comparator<T> comp) {
		if (type == OrderType.binaryInsert_asce || type == OrderType.binaryInsert_desc) {
			binaryInsertSort(data, comp);
		} else if (type == OrderType.bubble_asce || type == OrderType.bubble_desc) {
			bubbleSort(data, comp);
		} else if (type == OrderType.heap_asce || type == OrderType.heap_desc) {
			heapSort(data, comp);
		} else if (type == OrderType.quick_asce || type == OrderType.quick_desc) {
			quickSort(data, comp);
		} else if (type == OrderType.insert_asce || type == OrderType.insert_desc) {
			insertSort(data, comp);
		} else if (type == OrderType.select_asce || type == OrderType.select_desc) {
			selectSort(data, comp);
		} else if (type == OrderType.shell_asce || type == OrderType.shell_desc) {
			shellSort(data, comp);
		} else {
			binaryInsertSort(data, comp);
		}
		if (type.getKey().endsWith(OrderType.desc.getKey())) {
			ArrayUtils.reverse(data);
		}
		return data;
	}

	private static <T> int compare(T comp1, T comp2, Comparator<T> comparator) {
		if (comparator == null) {
			@SuppressWarnings("unchecked")
			Comparable<T> comp = (Comparable<T>) comp1;
			return comp.compareTo(comp2);
		}
		return comparator.compare(comp1, comp2);
	}
}
