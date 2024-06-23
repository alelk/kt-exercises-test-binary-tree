fun main() {

    val helpText = """
        | Справка:
        |   help - вывод справки
        |   create [el1], [el2], ... - создать новое дерево
        |   add - добавить элементы
        |   remove - удалить элемент
        |   exit - выйти из программы
        |""".trimMargin()

    var tree: Tree? = null

    fun printTree() {
        if (tree != null) {
            val textBox = TextBox(tree!!)
            textBox.text.forEach { l ->
                println(l)
            }
        }
    }

    println(helpText)

    while (true) {
        println("Введите команду")
        val cmd = readlnOrNull()
        if (cmd == null) {
            System.err.println("Вы не ввели команду!!!")
            continue
        }
        if (cmd.trim().startsWith("create")) {
            val numbers = cmd.replace("create", "").split(',').mapNotNull { it.trim().toIntOrNull() }
            if (numbers.isEmpty()) {
                System.err.println("Введите хотя бы одно число!!!")
                continue
            }
            tree = numbers.fold(null as Tree?) { acc, e -> acc?.add(e) ?: Tree(e) }
            printTree()
        }
    }
}