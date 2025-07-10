package TdaABB;

import java.util.Comparator;

import Auxiliar.Entrada;
import Auxiliar.Entry;
import Exceptions.InvalidKeyException;
import TdaLista.ListaDoblementeEnlazada;
import TdaLista.PositionList;
import TdaMapeo.Map;

public class MapABB<K, V> implements Map<K, V> {
    private NodoABB<K, V> root;
    private int size;
    private Comparator<K> comp;

    public MapABB() {
        root = null;
        size = 0;
        comp = new DefaultComparator<>();
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
        if (key == null) throw new InvalidKeyException("Llave invalida o nula");
        NodoABB<K, V> nodo = getNode(root, key);
        return nodo == null ? null : nodo.getElement().getValue();
    }

    private NodoABB<K, V> getNode(NodoABB<K, V> nodo, K key) {
        if (nodo == null) return null;
        int cmp = comp.compare(key, nodo.getElement().getKey());
        if (cmp < 0) return getNode(nodo.getLeft(), key);
        else if (cmp > 0) return getNode(nodo.getRight(), key);
        else return nodo;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new InvalidKeyException("Llave invalida o nula");
        V oldValue = get(key);
        root = insert(root, key, value, null);
        if (oldValue == null) size++;
        return value;
    }

    private NodoABB<K, V> insert(NodoABB<K, V> nodo, K key, V value, NodoABB<K, V> padre) {
        if (nodo == null) {
            NodoABB<K, V> nuevo = new NodoABB<>(new Entrada<>(key, value));
            nuevo.setPadre(padre);
            return nuevo;
        }
        int cmp = comp.compare(key, nodo.getElement().getKey());
        if (cmp < 0) {
            NodoABB<K, V> izq = insert(nodo.getLeft(), key, value, nodo);
            nodo.setLeft(izq);
        } else if (cmp > 0) {
            NodoABB<K, V> der = insert(nodo.getRight(), key, value, nodo);
            nodo.setRight(der);
        } else {
        	Entrada<K,V>entrada=(Entrada<K,V>)nodo.getElement();
        	entrada.setValue(value);
        }
        return nodo;
    }

    @Override
    public V remove(K key) {
        if (key == null) throw new InvalidKeyException("Llave invalida o nula");
        NodoABB<K, V> nodo = getNode(root, key);
        if (nodo == null) return null;
        V value = nodo.getElement().getValue();
        removeAux(nodo);
        size--;
        return value;
    }

    private void removeAux(NodoABB<K, V> nodo) {
        if (nodo.getLeft() == null) {
            transplantar(nodo, nodo.getRight());
        } else if (nodo.getRight() == null) {
            transplantar(nodo, nodo.getLeft());
        } else {
            NodoABB<K, V> sucesor = nodo.getRight();
            while (sucesor.getLeft() != null)
                sucesor = sucesor.getLeft();
            if (sucesor.getPadre() != nodo) {
                transplantar(sucesor, sucesor.getRight());
                sucesor.setRight(nodo.getRight());
                if (sucesor.getRight() != null) sucesor.getRight().setPadre(sucesor);
            }
            transplantar(nodo, sucesor);
            sucesor.setLeft(nodo.getLeft());
            if (sucesor.getLeft() != null) sucesor.getLeft().setPadre(sucesor);
        }
    }

    private void transplantar(NodoABB<K, V> u, NodoABB<K, V> v) {
        if (u.getPadre() == null) {
            root = v;
        } else if (u == u.getPadre().getLeft()) {
            u.getPadre().setLeft(v);
        } else {
            u.getPadre().setRight(v);
        }
        if (v != null) {
            v.setPadre(u.getPadre());
        }
    }

    @Override
    public Iterable<K> keys() {
        PositionList<K> list = new ListaDoblementeEnlazada<>();
        inOrderKey(root, list);
        return list;
    }

    private void inOrderKey(NodoABB<K, V> nodo, PositionList<K> list) {
        if (nodo != null) {
            inOrderKey(nodo.getLeft(), list);
            list.addLast(nodo.getElement().getKey());
            inOrderKey(nodo.getRight(), list);
        }
    }

    @Override
    public Iterable<V> values() {
        PositionList<V> list = new ListaDoblementeEnlazada<>();
        inOrderValue(root, list);
        return list;
    }

    private void inOrderValue(NodoABB<K, V> nodo, PositionList<V> list) {
        if (nodo != null) {
            inOrderValue(nodo.getLeft(), list);
            list.addLast(nodo.getElement().getValue());
            inOrderValue(nodo.getRight(), list);
        }
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        PositionList<Entry<K, V>> list = new ListaDoblementeEnlazada<>();
        inOrderEntry(root, list);
        return list;
    }

    private void inOrderEntry(NodoABB<K, V> nodo, PositionList<Entry<K, V>> list) {
        if (nodo != null) {
            inOrderEntry(nodo.getLeft(), list);
            list.addLast(nodo.getElement());
            inOrderEntry(nodo.getRight(), list);
        }
    }
}
