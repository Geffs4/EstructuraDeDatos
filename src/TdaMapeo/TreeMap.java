package TdaMapeo;

import Auxiliar.Entry;
import Auxiliar.Entrada; // supondremos que Entrada<K, V> es la implementación de Entry<K, V>
import java.util.LinkedList;
import Exceptions.InvalidKeyException;

public class TreeMap<K, V> implements Map<K, V> {

	private class Node {
		Entry<K, V> entry;
		Node left, right;

		Node(Entry<K, V> entry) {
			this.entry = entry;
		}
	}

	private Node root;
	private int size;

	public TreeMap() {
		root = null;
		size = 0;
	}

	private int compare(K k1, K k2) throws InvalidKeyException {
		if (k1 == null || k2 == null)
			throw new InvalidKeyException("Clave nula");
		return ((Comparable<K>) k1).compareTo(k2);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V get(K key) {
		if (key == null) {
			throw new InvalidKeyException("clave nula");
		}
		Node node = getNode(root, key);
		return node == null ? null : node.entry.getValue();
	}

	private Node getNode(Node node, K key) throws InvalidKeyException {
		if (node == null)
			return null;
		int cmp = compare(key, node.entry.getKey());
		if (cmp < 0)
			return getNode(node.left, key);
		else if (cmp > 0)
			return getNode(node.right, key);
		else
			return node;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Clave nula");
		Result res = insert(root, key, value);
		if (root == null)
			root = res.node;
		if (res.existing != null)
			return res.existing.getValue();
		size++;
		return null;
	}

	private class Result {
		Node node;
		Entry<K, V> existing;

		Result(Node node, Entry<K, V> existing) {
			this.node = node;
			this.existing = existing;
		}
	}

	private Result insert(Node node, K key, V value) throws InvalidKeyException {
		if (node == null)
			return new Result(new Node(new Entrada<>(key, value)), null);

		int cmp = compare(key, node.entry.getKey());
		if (cmp < 0) {
			Result res = insert(node.left, key, value);
			node.left = res.node;
			return new Result(node, res.existing);
		} else if (cmp > 0) {
			Result res = insert(node.right, key, value);
			node.right = res.node;
			return new Result(node, res.existing);
		} else {
			Entry<K, V> old = node.entry;
			node.entry = new Entrada<>(key, value);
			return new Result(node, old);
		}
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("clave nula");
		}
		ResultRemove res = delete(root, key);
		root = res.node;
		if (res.deleted != null) {
			size--;
			return res.deleted.getValue();
		}
		return null;
	}

	private class ResultRemove {
		Node node;
		Entry<K, V> deleted;

		ResultRemove(Node node, Entry<K, V> deleted) {
			this.node = node;
			this.deleted = deleted;
		}
	}

	private ResultRemove delete(Node node, K key) throws InvalidKeyException {
		if (node == null)
			return new ResultRemove(null, null);

		Entry<K, V> deleted = null;
		int cmp = compare(key, node.entry.getKey());
		if (cmp < 0) {
			ResultRemove res = delete(node.left, key);
			node.left = res.node;
			deleted = res.deleted;
		} else if (cmp > 0) {
			ResultRemove res = delete(node.right, key);
			node.right = res.node;
			deleted = res.deleted;
		} else {
			deleted = node.entry;
			if (node.left == null)
				return new ResultRemove(node.right, deleted);
			if (node.right == null)
				return new ResultRemove(node.left, deleted);
			Node min = getMin(node.right);
			node.entry = min.entry;
			ResultRemove res = delete(node.right, min.entry.getKey());
			node.right = res.node;
		}
		return new ResultRemove(node, deleted);
	}

	private Node getMin(Node node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	@Override
	public Iterable<K> keys() {
		LinkedList<K> list = new LinkedList<>();
		inorderKeys(root, list);
		return list;
	}

	private void inorderKeys(Node node, LinkedList<K> list) {
		if (node != null) {
			inorderKeys(node.left, list);
			list.add(node.entry.getKey());
			inorderKeys(node.right, list);
		}
	}

	@Override
	public Iterable<V> values() {
		LinkedList<V> list = new LinkedList<>();
		inorderValues(root, list);
		return list;
	}

	private void inorderValues(Node node, LinkedList<V> list) {
		if (node != null) {
			inorderValues(node.left, list);
			list.add(node.entry.getValue());
			inorderValues(node.right, list);
		}
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		LinkedList<Entry<K, V>> list = new LinkedList<>();
		inorderEntries(root, list);
		return list;
	}

	private void inorderEntries(Node node, LinkedList<Entry<K, V>> list) {
		if (node != null) {
			inorderEntries(node.left, list);
			list.add(node.entry);
			inorderEntries(node.right, list);
		}
	}
}
