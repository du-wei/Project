package com.webapp.utils.collections;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import com.webapp.utils.random.RandomUtils;

public class CollectionsUtils {

	// list
	@Test
	public void testArrayList() {
		List<String> list = RandomUtils.arrayList(10);
		viewList(list);
	}

	@Test
	public void testLinkedList() {
		List<String> list = RandomUtils.linkedList(10);
		viewList(list);
	}

	@Test
	public void testCopyOnWriteArrayList() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	}

	// set
	@Test
	public void testHashSet() {
		Set<String> set = RandomUtils.hashSet(10);
		viewSet(set);
	}

	@Test
	public void testCopyOnWriteArraySet() {
		CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
	}

	@Test
	public void testTreeSet() {
		TreeSet<String> set = RandomUtils.treeSet(10);
		viewSet(set);
	}

	@Test
	public void testConcurrentSkipListSet() {
		ConcurrentSkipListSet<String> set = RandomUtils.curSkipListSet(10);

		viewSet(set);
	}

	// map
	@Test
	public void testTreeMap() {
		SortedMap<Integer, String> map = RandomUtils.treeMap(10);
		viewMapKey(map);
		viewMapVal(map);
		viewMapKeyVal(map);
	}

	@Test
	public void testConcurrentSkipListMap() {
		ConcurrentSkipListMap<Integer, String> map = RandomUtils.curSkipListMap(10);

		viewMapKey(map);
		viewMapVal(map);
		viewMapKeyVal(map);
	}

	@Test
	public void testHashMap() {
		Map<Integer, String> map = RandomUtils.hashMap(5);

		viewMapKey(map);
		viewMapVal(map);
		viewMapKeyVal(map);
	}

	@Test
	public void testConcurrentHashMap() {
		ConcurrentHashMap<Integer, String> map = RandomUtils.curHashMap(10);

		viewMapKey(map);
		viewMapVal(map);
		viewMapKeyVal(map);
	}

	// queue
	@Test
	public void test2ArrayList() {
		List<String> list = RandomUtils.arrayList(10);
		viewList(list);
	}

	@Test
	public void testPriorityQueue() throws Exception {
		PriorityQueue<String> queue = new PriorityQueue<>();
	}

	@Test
	public void testDelayQueue() throws Exception {
		DelayQueue<Delayed> queue = new DelayQueue<>();
	}

	@Test
	public void testLinkedBlockingQueue() {
		// FIFO
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
	}

	@Test
	public void testArrayBlockingQueue() {
		// FIFO
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
	}

	@Test
	public void testPriorityBlockingQueue() {
		// 按优先级顺序排序
		PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
	}

	// deque
	@Test
	public void test1ArrayList() {
		List<String> list = RandomUtils.arrayList(10);
		viewList(list);
	}

	@Test
	public void testArrayDeque() {
		ArrayDeque<String> deque = new ArrayDeque<>();
	}

	@Test
	public void testLinkedBlockingDeque() {
		LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();
	}

	public void testConcurrentLinkedDeque() {
		ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
	}

	public <V> void viewList(List<V> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public <V> void viewSet(Set<V> set) {
		Iterator<V> iterator = set.iterator();
		for (; iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}

	public <K, V> void viewMapKey(Map<K, V> map) {
		Iterator<K> iterator = map.keySet().iterator();
		for (; iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}

	public <K, V> void viewMapVal(Map<K, V> map) {
		Collection<V> temp = map.values();
		for (Iterator<V> i = temp.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}
	}

	public <K, V> void viewMapKeyVal(Map<K, V> map) {
		Iterator<Entry<K, V>> entry = map.entrySet().iterator();
		for (; entry.hasNext();) {
			Entry<K, V> temp = entry.next();
			System.out.println(temp.getKey() + "-" + temp.getValue());
		}
	}

}
