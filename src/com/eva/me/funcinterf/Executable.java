/**
 * 
 */
package com.eva.me.funcinterf;

import org.hibernate.Session;

/**
 * @author phoen_000
 *
 */
@FunctionalInterface
public interface Executable<T, F> {
	public T execute(Session session, F toExecute);
}
