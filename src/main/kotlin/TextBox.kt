data class TextBox(val t: Tree) {

    val left: TextBox? = if (t.left != null) TextBox(t.left) else null
    val right: TextBox? = if (t.right != null) TextBox(t.right) else null

    private val maxChildrenHeight = listOfNotNull(left?.text?.size, right?.text?.size, 0).max()

    private val leftWidth: Int = left?.width ?: 0
    private val rightWidth: Int = right?.width ?: 0

    val width: Int =
        if (t.isLeaf) t.data.toString().length
        else leftWidth + t.data.toString().length + rightWidth

    val height: Int = maxChildrenHeight + 1

    val text: List<String> =
        if (t.isLeaf)
            listOf(t.data.toString())
        else {
            val header = " ".repeat(leftWidth) + t.data.toString() + " ".repeat(rightWidth)
            val rest =
                List(maxChildrenHeight) { index: Int ->
                    (left?.text?.getOrNull(index) ?: " ".repeat(leftWidth)) +
                            " ".repeat(t.data.toString().length) +
                            (right?.text?.getOrNull(index) ?: " ".repeat(rightWidth))
                }
            listOf(header) + rest
        }
}