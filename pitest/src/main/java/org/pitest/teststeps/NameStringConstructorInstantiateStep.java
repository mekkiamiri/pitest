/*
 * Copyright 2010 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest.teststeps;

import org.pitest.CanNotCreateTestClassException;
import org.pitest.Description;
import org.pitest.extension.TestStep;
import org.pitest.internal.IsolationUtils;

/**
 * @author henry
 * 
 */
public final class NameStringConstructorInstantiateStep implements TestStep {

  private final Class<?> clazz;

  public NameStringConstructorInstantiateStep(final Class<?> clazz) {
    this.clazz = clazz;
  }

  public Object execute(final ClassLoader loader,
      final Description testDescription, final Object target) {
    try {
      final Class<?> c = IsolationUtils.convertForClassLoader(loader,
          this.clazz);
      final Class<?> parameterTypes[] = { String.class };
      final Object arglist[] = { testDescription.getName() };
      return c.getConstructor(parameterTypes).newInstance(arglist);

    } catch (final Throwable e) {
      throw new CanNotCreateTestClassException(e);
    }
  }

  public Class<?> getClazz() {
    return this.clazz;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((this.clazz == null) ? 0 : this.clazz.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NameStringConstructorInstantiateStep other = (NameStringConstructorInstantiateStep) obj;
    if (this.clazz == null) {
      if (other.clazz != null) {
        return false;
      }
    } else if (!this.clazz.equals(other.clazz)) {
      return false;
    }
    return true;
  }

}
