package hgp.classload; /**
 *   Copyright (c) Rich Hickey. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *   which can be found in the file epl-v10.html at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 * 	 the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

/* rich Aug 21, 2007 */

import clojure.lang.Compiler;
import clojure.lang.DynamicClassLoader;
import clojure.lang.Util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ActiveDynamicClassLoader extends DynamicClassLoader {

    private final static URL [] EMPTY_URLS = new URL[0];
public ActiveDynamicClassLoader(){
	super(Compiler.class.getClassLoader());
}

public ActiveDynamicClassLoader(ClassLoader parent){
	super(parent);
}


}
