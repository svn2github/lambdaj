/**
 * // Modified or written by Ex Machina SAGL for inclusion with lambdaj.
 * // Copyright (c) 2008 Mario Fusco, Luca Marrocco.
 * // Licensed under the Apache License, Version 2.0 (the "License")
 */
package ch.lambdaj.group;

import java.util.*;

public interface Group<T> {
	
	Set<String> keySet();
	
	Group<T> findGroup(String key);
	
	Iterable<T> find(String key);
	
	Iterable<T> findAll();

}
