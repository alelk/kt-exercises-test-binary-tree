const val createCmd = "create"
const val addCmd = "add"
const val removeCmd = "remove"

fun String.parseIntList(): List<Int> = split(',').mapNotNull { it.trim().toIntOrNull() }

fun main() {

    val helpText = """
        | Справка:
        |   help - вывод справки
        |   $createCmd [el1], [el2], ... - создать новое дерево
        |   $addCmd [el1], [el2], ... - добавить элементы
        |   $removeCmd [el1], [el2], ... - удалить элементы
        |   exit - выйти из программы
        |""".trimMargin()

    var tree: Tree? = null

    fun printTree() {
        if (tree != null) {
            val textBox = TextBox(tree!!)
            textBox.text.forEach { l ->
                println(l)
            }
        } else println("Дерево не содержит элементов")
    }

    println(helpText)

    while (true) {
        println("Введите команду")
        val cmd = readlnOrNull()?.trimStart()
        if (cmd == null) {
            System.err.println("Вы не ввели команду!!!")
            continue
        }
        when {
            cmd.trim() == "help" -> println(helpText)
            cmd.trim() == "exit" -> break
            cmd.startsWith(createCmd) -> {
                val numbers = cmd.drop(createCmd.length).parseIntList()
                if (numbers.isEmpty()) {
                    System.err.println("Введите хотя бы одно число!!!")
                    continue
                }
                tree = numbers.fold(null as Tree?) { acc, e -> acc?.add(e) ?: Tree(e) }
                printTree()
            }

            cmd.startsWith(addCmd) -> {
                val numbers = cmd.drop(addCmd.length).parseIntList()
                if (numbers.isEmpty()) {
                    System.err.println("Введите хотя бы одно число!!!")
                    continue
                }
                tree = numbers.fold(tree) { acc, e -> acc?.add(e) ?: Tree(e) }
                printTree()
            }

            cmd.startsWith(removeCmd) -> {
                val numbers = cmd.drop(removeCmd.length).parseIntList()
                if (numbers.isEmpty()) {
                    System.err.println("Введите хотя бы одно число!!!")
                    continue
                }
                tree = numbers.fold(tree) { acc, e -> acc?.remove(e) }
                printTree()
            }
        }
    }
}