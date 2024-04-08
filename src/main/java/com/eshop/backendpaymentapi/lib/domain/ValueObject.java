package com.mach.machorderrestapi.common.domain;

public abstract class ValueObject {
	/*
	* O método hashCode() é usado para gerar um código hash que identifica exclusivamente um objeto.
	* Esse código é usado em estruturas de dados baseadas em hash, como HashMaps, para indexar e buscar
	*  objetos de forma eficiente. Se dois objetos são iguais de acordo com o método equals(), então
	* seus códigos hash devem ser iguais. No entanto, a igualdade de códigos hash não garante igualdade
	* de objetos, ou seja, se dois objetos têm o mesmo código hash, eles podem ou não ser iguais.
	* */
	public abstract int hashCode();

	/*
	* O método equals() é usado para comparar se dois objetos são iguais. É uma forma de comparar
	* o estado interno dos objetos para determinar se eles representam o mesmo valor ou instância.
	* A implementação padrão do método equals() na classe Object compara apenas referências de objeto,
	* o que significa que dois objetos são considerados iguais apenas se eles referem-se à mesma
	* instância na memória. No entanto, na maioria dos casos, queremos comparar objetos com base no
	* seu estado interno, e é por isso que sobrescrevemos o método equals() em classes específicas
	* para realizar essa comparação personalizada.
	* */
	public abstract boolean equals(Object o);
}
