# Bradley Allen
# CSCI236
# April 23, 2022
# Project 4 -- (FractionCalculator.py)
# 3 hours
# Grade Version A
# Handling user input with buttons from GUI and building fractions
# Works as intended.
from tkinter import *


class Fraction:
    # Constructor for Fraction objects
    def __init__(self, top, bottom):
        self.num = top
        self.den = bottom

    # Simplifies the fraction
    # Changes the Fraction object to be in a simplified form
    def simplify(self):
        common = gcd(self.num, self.den)
        self.num = self.num // common
        self.den = self.den // common

    # Override for + operator
    # Returns a Fraction object after adding
    def __add__(self, other):
        newnum = self.num * other.den + self.den * other.num
        newden = self.den * other.den
        f = Fraction(newnum, newden)
        f.simplify()
        return f

    # Override for - operator
    # Returns a Fraction object after subtracting
    def __sub__(self, other):
        newnum = self.num * other.den - self.den * other.num
        newden = self.den * other.den
        f = Fraction(newnum, newden)
        f.simplify()
        return f

    # Override for * operator
    # Returns a Fraction object after multiplying
    def __mul__(self, other):
        newnum = self.num * other.num
        newden = self.den * other.den
        f = Fraction(newnum, newden)
        f.simplify()
        return f

    # Override for / operator
    # Returns a Fraction object after dividing
    def __truediv__(self, other):
        newnum = self.num * other.den
        newden = self.den * other.num
        f = Fraction(newnum, newden)
        f.simplify()
        return f

    # Override for float
    # Returns float value of fraction
    def __float__(self):
        return self.num / self.den

    # Override for toString
    # Returns a string representation of the fraction
    def __str__(self):
        return str(self.num) + "/" + str(self.den)


# Calculates the greatest common denominator
# Used for Fraction simplification
def gcd(m, n):
    while m % n != 0:
        oldm = m
        oldn = n

        m = oldn
        n = oldm % oldn

    return n


# Unit testing to test Fraction operations
def main():
    testFraction1 = Fraction(7, 6)
    testFraction2 = Fraction(12, 4)
    testFraction3 = Fraction(29, 32)
    assert str(testFraction1 + testFraction2) == "25/6"
    assert str(testFraction1 + testFraction3) == "199/96"
    assert str(testFraction1 - testFraction2) == "-11/6"
    assert str(testFraction1 - testFraction3) == "25/96"
    assert str(testFraction1 * testFraction2) == "7/2"
    assert str(testFraction1 * testFraction3) == "203/192"
    assert str(testFraction1 / testFraction2) == "7/18"
    assert str(testFraction1 / testFraction3) == "112/87"


# Creates the GUI window
win = Tk()
win.geometry("312x324")
win.resizable(False, False)
win.title("Fraction Calculator")
expression = ""
is_on = False


# Button click event, adds input to screen
def button_click(item):
    global expression
    expression = expression + str(item)
    input_text.set(expression)


# Clear button event, clears all input from screen
def button_clear():
    global expression
    expression = ""
    input_text.set("")


# Equal button event, calculates and prints on screen
def button_equal():
    global expression
    output = ""
    operators = ["+", "-", "*", "%"]
    operation = ""
    expressList = expression.split("/")

    # Searching which operator was used
    for operator in operators:
        operatorIndex = expressList[1].find(operator)
        if operatorIndex != -1:
            operation = expressList[1][operatorIndex]
    entry = [expressList[0], expressList[1].split(operation)[0], expressList[1].split(operation)[1], expressList[2]]

    # Creates the two Fraction objects
    fracA = Fraction(int(entry[0]), int(entry[1]))
    fracB = Fraction(int(entry[2]), int(entry[3]))

    # Computes based on operation
    if operation == "+":
        output = fracA + fracB
    elif operation == "-":
        output = fracA - fracB
    elif operation == "*":
        output = fracA * fracB
    elif operation == "%":
        output = fracA / fracB

    # Used for toggling between decimal and fraction notation
    if is_on:
        result = str(float(output))
    else:
        result = str(output)

    # Prints to screen
    input_text.set(result)


# Toggles between fraction and decimal notation
def toggle():
    global is_on

    if is_on:
        is_on = False
        button_equal()
    else:
        is_on = True
        button_equal()


# Formats calculator
input_text = StringVar()
inputFrame = Frame(win, width=312, height=50, bd=0, highlightbackground="black", highlightcolor="black",
                   highlightthickness=2)
inputFrame.pack(side=TOP)
inputScreen = Entry(inputFrame, font=('arial', 18, 'bold'), textvariable=input_text, width=50, bg="#eee", bd=0,
                    justify=RIGHT)
inputScreen.grid(row=0, column=0)
inputScreen.pack(ipady=10)
buttonFrame = Frame(win, width=312, height=272.5, bg="grey")
buttonFrame.pack()

# First row
clear = Button(buttonFrame, text="C", fg="black", width=10, height=3, bd=0, bg="#eee",
               command=lambda: button_clear())
clear.grid(row=0, column=0, padx=1, pady=1)

divide = Button(buttonFrame, text="%", fg="black", width=10, height=3, bd=0, bg="#eee",
                command=lambda: button_click("%"))
divide.grid(row=0, column=1, padx=1, pady=1)

equals = Button(buttonFrame, text="=", fg="black", width=10, height=3, bd=0, bg="#eee",
                command=lambda: button_equal())
equals.grid(row=0, column=2, padx=1, pady=1)

fraction = Button(buttonFrame, text="/", fg="black", width=10, height=3, bd=0, bg="#eee",
                  command=lambda: button_click("/"))
fraction.grid(row=0, column=3, padx=1, pady=1)

# Second row
seven = Button(buttonFrame, text="7", fg="black", width=10, height=3, bd=0, bg="powder blue",
               command=lambda: button_click(7))
seven.grid(row=1, column=0, padx=1, pady=1)

eight = Button(buttonFrame, text="8", fg="black", width=10, height=3, bd=0, bg="powder blue",
               command=lambda: button_click(8))
eight.grid(row=1, column=1, padx=1, pady=1)

nine = Button(buttonFrame, text="9", fg="black", width=10, height=3, bd=0, bg="powder blue",
              command=lambda: button_click(9))
nine.grid(row=1, column=2, padx=1, pady=1)

multiply = Button(buttonFrame, text="*", fg="black", width=10, height=3, bd=0, bg="#eee",
                  command=lambda: button_click("*"))
multiply.grid(row=1, column=3, padx=1, pady=1)

# Third row
four = Button(buttonFrame, text="4", fg="black", width=10, height=3, bd=0, bg="powder blue",
              command=lambda: button_click(4))
four.grid(row=2, column=0, padx=1, pady=1)

five = Button(buttonFrame, text="5", fg="black", width=10, height=3, bd=0, bg="powder blue",
              command=lambda: button_click(5))
five.grid(row=2, column=1, padx=1, pady=1)

six = Button(buttonFrame, text="6", fg="black", width=10, height=3, bd=0, bg="powder blue",
             command=lambda: button_click(6))
six.grid(row=2, column=2, padx=1, pady=1)

minus = Button(buttonFrame, text="-", fg="black", width=10, height=3, bd=0, bg="#eee",
               command=lambda: button_click("-"))
minus.grid(row=2, column=3, padx=1, pady=1)

# Fourth row
one = Button(buttonFrame, text="1", fg="black", width=10, height=3, bd=0, bg="powder blue",
             command=lambda: button_click(1))
one.grid(row=3, column=0, padx=1, pady=1)

two = Button(buttonFrame, text="2", fg="black", width=10, height=3, bd=0, bg="powder blue",
             command=lambda: button_click(2))
two.grid(row=3, column=1, padx=1, pady=1)

three = Button(buttonFrame, text="3", fg="black", width=10, height=3, bd=0, bg="powder blue",
               command=lambda: button_click(3))
three.grid(row=3, column=2, padx=1, pady=1)

plus = Button(buttonFrame, text="+", fg="black", width=10, height=3, bd=0, bg="#eee",
              command=lambda: button_click("+"))
plus.grid(row=3, column=3, padx=1, pady=1)

# Fifth row
zero = Button(buttonFrame, text="0", fg="black", width=21, height=3, bd=0, bg="powder blue",
              command=lambda: button_click(0))
zero.grid(row=4, column=0, columnspan=2, padx=1, pady=1)

toggleOutput = Button(buttonFrame, text="Fraction/Decimal", fg="black", width=21, height=3, bd=0, bg="#eee",
                      command=lambda: toggle())
toggleOutput.grid(row=4, column=2, columnspan=2, padx=1, pady=1)

# Starts GUI
win.mainloop()

main()
