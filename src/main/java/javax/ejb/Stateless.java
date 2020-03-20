/*
 *  JBoss, Home of Professional Open Source
 *  Copyright 2010 Red Hat, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package javax.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A stateless session bean must be annotated with the Stateless annotation or
 * denoted in the deployment descriptor as a stateless session bean. If other
 * annotations are applied to the bean class or its members, the Stateless
 * annotation must be used. The bean class does not implement the
 * javax.ejb.SessionBean interface.
 * 
 * @author <a href="mailto:bill@jboss.org">Bill Burke</a>
 * @version $Revision: 107909 $
 */
@Target(ElementType.TYPE) @Retention(RetentionPolicy.RUNTIME)
   public @interface Stateless
{
   String name() default "";
   String mappedName() default "";
   String description() default "";
}