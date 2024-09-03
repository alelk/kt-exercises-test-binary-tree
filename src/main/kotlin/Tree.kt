/** Бинарное дерево
 *
 * @param data данные текущего узла дерева
 * @param left левая ветка
 * @param right правая ветка
 */
data class Tree(val data: Int, val left: Tree? = null, val right: Tree? = null) {

    val isLeaf: Boolean = left == null && right == null

    /** Добавить заданное значение в дерево. Возвращает копию текущего дерева */
    fun add(data: Int): Tree =
        if (data == this.data) this
        else if (data < this.data) this.copy(left = left?.add(data) ?: Tree(data))
        else this.copy(right = right?.add(data) ?: Tree(data))

    /** Удаляет заданное значение из дерева. Возвращает копию текущего дерева */
    fun remove(data: Int): Tree? =
        if (data < this.data) this.copy(left = left?.remove(data))
        else if (data > this.data) this.copy(right = right?.remove(data))
        else if (isLeaf) null
        else if (left == null) right
        else right?.joinLeft(left) ?: left

    private fun joinLeft(t: Tree): Tree =
        this.copy(left = left?.joinLeft(t) ?: t)

    operator fun plus(data: Int): Tree = add(data)
    operator fun minus(data: Int): Tree? = remove(data)
}