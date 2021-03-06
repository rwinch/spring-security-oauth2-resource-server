/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.oauth2.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A class for using a single key to verify JWT signatures
 *
 * @since 5.1
 * @author Josh Cummings
 */
public interface SingleKeyProvider<T extends Key> extends KeyProvider<T> {

	@Override
	default List<T> provide(Map<String, Object> header) {
		return Arrays.asList(this.key());
	}

	T key();
}
