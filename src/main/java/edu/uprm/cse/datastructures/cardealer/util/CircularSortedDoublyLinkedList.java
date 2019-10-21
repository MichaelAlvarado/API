package edu.uprm.cse.datastructures.cardealer.util;


import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;

import edu.uprm.cse.datastructures.cardealer.util.SortedList;

class Node<E>{
	private E element;
	private Node<E> prev;
	private Node<E> next;

	public Node (E element) {
		this.element = element;
		this.prev = null;
		this.next = null;
	}
	public Node(E element, Node<E> prev, Node<E> next) {
		this.element = element;
		this.prev = prev;
		this.next = next;
	}
	public void setPrev(Node<E> node) {
		this.prev = node;
	}
	public void setNext(Node<E> node) {
		this.next = node;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public Node<E> getPrev() {
		return this.prev;
	}
	public Node<E> getNext() {
		return this.next;
	}
	public E getElement() {
		return this.element;
	}
	public void clear() {
		this.element = null;
		this.next = null;
		this.prev = null;
	}
}
class CSDLLIterator<E> implements Iterator<E>{
	Node <E> current;
	Node <E> head;
	public CSDLLIterator (CircularSortedDoublyLinkedList<E> obj){
		current = obj.getHead();
		head = obj.getHead();
	}
	@Override
	public boolean hasNext() {
		return !current.getNext().equals(head);
	}

	@Override
	public E next() {
		current = current.getNext();
		return current.getElement();
	}
	
}

public class CircularSortedDoublyLinkedList<E> implements SortedList<E>, Iterable<E>{

	private Node<E> head;
	private int size;
	private Comparator<E> comparator;

	public CircularSortedDoublyLinkedList(Comparator<E> comparator) {
		this.head = new Node<E> (null, this.head, this.head);
		this.size = 0;
		this.comparator = comparator;
	}
	
	//Este codigo se puede arreglar
	@Override
	public boolean add(E obj) {
		if(this.isEmpty()) {
			Node<E> node = new Node<E>(obj, this.head, this.head);
			this.head.setNext(node);
			this.head.setPrev(node);
			size ++;
			return true;
		}		
		Node<E> current = this.head.getNext();
		while(current != this.head) {
			if (comparator.compare(obj,  current.getElement()) <= 0) {
				Node<E> node = new Node<E>(obj, current.getPrev(), current);
				current.getPrev().setNext(node);
				current.setPrev(node);
				size++;
				return true;
			}
			
			current = current.getNext();
		}
		Node<E> node = new Node<E>(obj, current.getPrev(), current);
		current.getPrev().setNext(node);
		current.setPrev(node);
		size++;
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(E obj) {
		int i = this.firstIndex(obj);
		if(i >= 0)
			return this.remove(this.firstIndex(obj));
		return false;
	}

	@Override
	public boolean remove(int index) {
		Node<E> deleter = this.getNode(index);
		if(deleter == null) 
			throw new IndexOutOfBoundsException();

		deleter.getPrev().setNext(deleter.getNext());
		deleter.getNext().setPrev(deleter.getPrev());
		deleter.clear();
		this.size--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while(this.remove(obj)) {
			counter ++;
		}
		return counter;
	}

	@Override
	public E first() {
		return (E) head.getNext().getElement();
	}

	@Override
	public E last() {
		return (E) head.getPrev().getElement();
	}

	@Override
	public E get(int index) {
		return this.getNode(index).getElement();
	}

	@Override
	public void clear() {
		head.setNext(null);
		head.setPrev(null);
		this.size = 0;
	}

	@Override
	public boolean contains(E e) {
		return this.firstIndex(e) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int firstIndex(E e) {
		Node<E> current = this.head.getNext();
		int index = 0;
		while (current != this.head) {
			if(comparator.compare(e, current.getElement()) == 0)
				return index;
			index ++;
			current = current.getNext();
		}
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		Node<E> current = this.head.getPrev();
		int index = size - 1;
		while (current != this.head) {
			if(comparator.compare(e, current.getElement()) == 0)
				return index;
			index --;
			current = current.getPrev();
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return new CSDLLIterator<E>(this);
	}

	private Node<E> getNode(int index){
		if(index >= size || index < 0)
			return null;
		int counter = 0;
		Node<E> current = this.head.getNext();
		while(counter != index) {
			current = current.getNext();
			counter++;
		}
		return current;
	}
	public Node<E> getHead(){
		return this.head;
	}

	

}
