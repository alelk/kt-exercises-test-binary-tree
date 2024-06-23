/** Бинарное дерево
 *
 * @param data данные текущего узла дерева
 * @param left левая ветка
 * @param right правая ветка
 */
data class Tree(val data: Int, val left: Tree? = null, val right: Tree? = null) {

    val isLeaf: Boolean = left == null && right == null

    /** Добавить заданное значение в дерево. Возвращает копию текущего дерева */
    fun add(newData: Int): Tree =
        if (newData == data)
            this
        else if (newData < data)
            this.copy(left = left?.add(newData) ?: Tree(newData))
        else
            this.copy(right = right?.add(newData) ?: Tree(newData))

    /** Удаляет заданное значение из дерева. Возвращает копию текущего дерева */
    fun remove(dataToRemove: Int): Tree? =
        if (dataToRemove < data)
            this.copy(left = left?.remove(dataToRemove))
        else if (dataToRemove > data)
            this.copy(right = right?.remove(dataToRemove))
        else if (isLeaf) null
        else if (left == null) right
        else right?.joinLeft(left) ?: left

    private fun joinLeft(t: Tree): Tree =
        this.copy(left = left?.joinLeft(t) ?: t)

}