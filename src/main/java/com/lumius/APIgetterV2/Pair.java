package com.lumius.APIgetterV2;

import java.util.Objects;

/**
 * Pair -- A basic container for two objects
 * @author Razvan Rotundu
 * @param <K> the first element of the tuple
 * @param <V> the second element of the tuple
 */
public class Pair<K, V> {
	private K e1;
	private V e2;

	
	public Pair(K e1, V e2){
		this.e1 = e1;
		this.e2 = e2;

	}
	
	//getters
	public K getK() {
		return this.e1;
	}
	
	public V getV() {
		return this.e2;
	}
	
	//Equality
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Pair)) {
			return false;
		} else {
			Pair other = (Pair)o;
			if(this.e1.equals(other.getK()) && this.e2.equals(other.getV())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	//Hashing
	@Override
	public int hashCode() {
		return Objects.hash(e1, e2);
	}
}

