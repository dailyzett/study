/*
 * Copyright 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.batch.integration.aot;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.batch.integration.chunk.ChunkResponse;

/**
 * AOT hints for Spring Batch integration module.
 *
 * @author Mahmoud Ben Hassine
 * @since 5.0.1
 */
public class IntegrationRuntimeHints implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		// reflection hints
		hints.reflection().registerType(ChunkRequest.class, MemberCategory.values());
		hints.reflection().registerType(ChunkResponse.class, MemberCategory.values());

		// serialization hints
		hints.serialization().registerType(ChunkRequest.class);
		hints.serialization().registerType(ChunkResponse.class);
	}

}
