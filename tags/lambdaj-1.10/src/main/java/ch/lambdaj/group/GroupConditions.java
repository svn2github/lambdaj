// Modified or written by Ex Machina SAGL for inclusion with lambdaj.
// Copyright (c) 2009 Mario Fusco, Luca Marrocco.
// Licensed under the Apache License, Version 2.0 (the "License")

package ch.lambdaj.group;

import java.util.*;

/**
 * @author Mario Fusco
 */
public class GroupConditions extends LinkedList<StringGroupCondition> {

	private static final long serialVersionUID = 1L;

	public void by(String by) {
		add(new StringGroupCondition(by));
	}

	public void as(String alias) {
		getLast().as(alias);
	}

	public void head(String property) {
		getLast().head(property);
	}

	public void head(String property, String alias) {
		getLast().head(alias, property);
	}
}
