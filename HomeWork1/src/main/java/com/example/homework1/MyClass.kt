package com.example.homework1

fun main() {
    val numbers = listOf<Int>()
    val pass = "NiceParadise29999!"
    println("\nTAsk1:")
    analyzeIntList(numbers)
    println("\nTAsk2:")
    print(passwordAnalize(pass))
    println()

}

fun analyzeIntList(list: List<Int>) {
    if (list.isEmpty()) {
        println("List is empty")
        return
    }
    var min = list[0]
    var max = list[0]
    var totalSum = 0
    var chet = 0
    var nechet = 0

    for (num in list) {
        totalSum += num

        if (num < min) {
            min = num
        }
        if (num > max) {
            max = num
        }
        if (num % 2 == 0) {
            chet++
        } else {
            nechet++
        }
    }



    print("Minimal number: " + min + "\n" +
            "Maximal number: " + max + "\n" +
            "Sum of all numbers: " + totalSum + "\n" +
            "Even count: " + chet + "\n" +
            "Odd count: " + nechet + "\n"
    )

}

fun passwordAnalize(password: String) : String {
    var conditions = 0;

    if (password.length >= 8) {
        conditions++
    }
    if (password.any { it.isDigit() }) {
        conditions++
    }
    if(password.any {
        it.isUpperCase()}) {
        conditions++
    }
    if (password.any(){it.isLowerCase()}) {
        conditions++
    }
    if(password.any(){!it.isLetter() && !it.isDigit()}) {
        conditions++
    }

    if (conditions == 5) {
        return "Password: NICE"
    }
    if (conditions == 4) {
        return "Password: GOOD"
    }
    if(conditions >= 2) {
        return "Password: NORMAL"
    }
    return "Password is NOT NICE"
}

