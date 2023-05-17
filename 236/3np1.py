# Bradley Allen
# CSCI236
# February 4, 2022
# Programming Exam #1 (3n+1)
# 1 hour
# Grade Version A
# Getting the code to return the number that has the highest, not the amount of the highest
# Works as intended.


# Computes the value for the 3n+1 sequence
def collatz(n):
    counter = 1
    while n != 1:
        if n % 2 == 0:
            n = n//2
            counter = counter + 1
        else:
            n = 3 * n + 1
            counter = counter + 1
    return counter


# Compares the number of values for each of the sequences before the value (inclusive)
def comparison(n):
    highest = 1
    highestcollatz = 1
    for i in range(1, n+1):
        if collatz(i) > highest:
            highest = collatz(i)
            highestcollatz = i
    return highestcollatz


# Handles user input
def main():
    n = 1
    while n != 0:
        n = int(input())
        if n == 0:
            exit()
        print(comparison(n))


main()
