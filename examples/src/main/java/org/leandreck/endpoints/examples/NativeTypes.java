/**
 * Copyright © 2016 Mathias Kowalzik (Mathias.Kowalzik@leandreck.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.leandreck.endpoints.examples;

public class NativeTypes {
	private AsBooleanType booleanField;
	private AsStringType stringField;
	private AsNumberType numberField;

	public AsBooleanType getBooleanField() {
		return booleanField;
	}

	public NativeTypes setBooleanField(AsBooleanType booleanField) {
		this.booleanField = booleanField;
		return this;
	}

	public AsStringType getStringField() {
		return stringField;
	}

	public NativeTypes setStringField(AsStringType stringField) {
		this.stringField = stringField;
		return this;
	}

	public AsNumberType getNumberField() {
		return numberField;
	}

	public NativeTypes setNumberField(AsNumberType numberField) {
		this.numberField = numberField;
		return this;
	}
}
