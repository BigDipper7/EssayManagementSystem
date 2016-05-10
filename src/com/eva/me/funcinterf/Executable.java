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
public interface Executable<R, T> {
	public R execute(Session session, T toExecute);
}
