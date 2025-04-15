package com.lumius.APIgetterV2;

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
	

	// equality
	public boolean equals(Pair p) {
		return (this.e1.equals(p.getK())) && (this.e2.equals(p.getV()));
	}
}

