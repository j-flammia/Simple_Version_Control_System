import org.hyperskill.hstest.dynamic.DynamicTest
import org.hyperskill.hstest.exception.outcomes.WrongAnswer
import org.hyperskill.hstest.stage.StageTest
import org.hyperskill.hstest.testcase.CheckResult
import org.hyperskill.hstest.testing.TestedProgram
import java.io.File
import kotlin.random.Random


class TestStage2 : StageTest<String>() {
    @DynamicTest(order = 1)
    fun helpPageTest(): CheckResult {
        try {
            checkHelpPageOutput(TestedProgram().start())
            checkHelpPageOutput(TestedProgram().start("--help"))
        } finally {
            deleteVcsDir()
        }
        return CheckResult.correct()
    }

    @DynamicTest(order = 2)
    fun configTest(): CheckResult {
        try {
            checkOutputString(TestedProgram().start("config"), "Please, tell me who you are.")
            checkOutputString(TestedProgram().start("config", "Max"), "The username is Max.")
            checkOutputString(TestedProgram().start("config"), "The username is Max.")
            checkOutputString(TestedProgram().start("config", "John"), "The username is John.")
            checkOutputString(TestedProgram().start("config"), "The username is John.")
        } finally {
            deleteVcsDir()
        }

        return CheckResult.correct()
    }

    @DynamicTest(order = 3)
    fun addTest(): CheckResult {
        val fileName1 = "file${Random.nextInt(0, 1000)}.txt"
        val fileName2= "file${Random.nextInt(0, 1000)}.txt"

        val file1 = File(fileName1)
        val file2 = File(fileName2)
        file1.createNewFile()
        file2.createNewFile()

        try {
            checkOutputString(TestedProgram().start("add"), "Add a file to the index.")
            checkOutputString(TestedProgram().start("add", fileName1), "The File '$fileName1' is tracked.")
            checkOutputString(TestedProgram().start("add"), "Tracked files:\n$fileName1")
            checkOutputString(TestedProgram().start("add", fileName2), "The File '$fileName2' is tracked.")
            checkOutputString(TestedProgram().start("add"), "Tracked files:\n$fileName1\n$fileName2")

            val notExistsFileName = "file${Random.nextInt(0, 1000)}.txt"
            checkOutputString(
                    TestedProgram().start("add", notExistsFileName),
                    "Can't found '$notExistsFileName'."
            )
        } finally {
            deleteVcsDir()
            file1.delete()
            file2.delete()
        }

        return CheckResult.correct()
    }

    @DynamicTest(order = 4)
    fun logTest(): CheckResult {
        try {
            checkOutputString(TestedProgram().start("log"), "Show commit logs.")
        } finally {
            deleteVcsDir()
        }
        return CheckResult.correct()
    }

    @DynamicTest(order = 5)
    fun commitTest(): CheckResult {
        try {
            checkOutputString(TestedProgram().start("commit"), "Save changes.")
        } finally {
            deleteVcsDir()
        }
        return CheckResult.correct()
    }

    @DynamicTest(order = 6)
    fun checkoutTest(): CheckResult {
        try {
            checkOutputString(TestedProgram().start("checkout"), "Restore a file.")
        } finally {
            deleteVcsDir()
        }
        return CheckResult.correct()
    }

    @DynamicTest(order = 7)
    fun wrongArgTest(): CheckResult {
        try {
            val suffix = Random.nextInt(0,1000)
            checkOutputString(TestedProgram().start("wrongArg$suffix"), "'wrongArg$suffix' is not a SVCS command.")
        } finally {
            deleteVcsDir()
        }
        return CheckResult.correct()
    }

    private fun prepareString(s: String) = s.trim().split(" ").filter { it.isNotBlank() }.joinToString(" ")

    private fun checkHelpPageOutput(got: String) {
        val helpPage = "These are SVCS commands:\n" +
                "config     Get and set a username.\n" +
                "add        Add a file to the index.\n" +
                "log        Show commit logs.\n" +
                "commit     Save changes.\n" +
                "checkout   Restore a file."

        if (got.isBlank()) {
            throw WrongAnswer(
                    "Your program should output:\n$helpPage\n\n" +
                            "But printed nothing"
            )
        } else if (!prepareString(got).equals(prepareString(helpPage), true)) {
            throw WrongAnswer(
                    "Your program should output:\n$helpPage\n\n" +
                            "But printed:\n$got"
            )
        }
    }

    private fun checkOutputString(got: String, want: String) {
        if (got.isBlank()) {
            throw WrongAnswer(
                    "Your program should output \"$want\",\n" +
                            "but printed nothing"
            )
        } else if (!prepareString(got).equals(want, true)) {
            throw WrongAnswer(
                    "Your program should output \"$want\",\n" +
                            "but printed: \"$got\""
            )
        }
    }

    private fun deleteVcsDir() {
        val dir = File("vcs")
        if (dir.exists()) {
            dir.deleteRecursively()
        }
    }

}
