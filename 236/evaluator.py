# Bradley Allen
# CSCI236
# March 25, 2022
# Programming Exam #2 (evaluator.py)
# 1.5 hours
# Grade Version A
# Had problems with IndexError when I modified the evaluate function
# Works as intended.

import math
import random


# Checks if token is a float number
def is_float(s):
    try:
        float(s)
        return True
    except ValueError:
        return False


# Calculates result based on operator, operand1, and if supplied, operand2
def apply(operator, operand1, operand2):
    if operator == "+":
        return operand1 + operand2
    elif operator == "-":
        return operand1 - operand2
    elif operator == "*":
        return operand1 * operand2
    elif operator == "/":
        return operand1 / operand2
    elif operator == "%":
        return operand1 % operand2
    elif operator == ">":
        if operand1 > operand2:
            return True
        else:
            return False
    elif operator == "<":
        if operand1 < operand2:
            return True
        else:
            return False
    elif operator == "==":
        if operand1 == operand2:
            return True
        else:
            return False
    elif operator == "<>":
        if operand1 != operand2:
            return True
        else:
            return False
    elif operator == "**":
        return operand1 ** operand2
    elif operator == "rand":
        random.seed(0)
        return random.randrange(int(operand1), int(operand2))
    elif operator == "sin":
        return math.sin(operand1)
    elif operator == "cos":
        return math.cos(operand1)
    elif operator == "round":
        return round(operand1)
    elif operator == "sqrt":
        return math.sqrt(operand1)
    elif operator == "abs":
        return abs(operand1)
    else:
        raise ValueError


# Recursion to handle each token input
def evaluate(tokens):
    first = tokens.pop(0)
    # Checks if the operator spot is a float number
    if is_float(first):
        return float(first)
    else:
        operand1 = evaluate(tokens)
        # Checks if there is a second operand
        try:
            operand2 = evaluate(tokens)
            return apply(first, operand1, operand2)
        except IndexError:
            return apply(first, operand1, None)


def main():
    print("This program evaluates prefix expressions.")
    print()

    expr = input("Expression? ")
    # Continues to prompt input until "quit" is entered
    while expr != "quit":
        tokens = expr.split()

        try:
            value = evaluate(tokens)
            if type(value) == bool:
                print("value =", value)
            else:
                print("value =", round(value, 2))
        except ValueError:
            operator = expr[0]
            print("bad operator:", operator)

        expr = input("Expression? ")
    print("Exiting.")


main()
