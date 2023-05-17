# Bradley Allen
# CSCI236
# February 4, 2022
# Project 1 -- (averages.py)
# 40 minutes
# Grade Version A
# Kept mixing up userinput and usernum, input is a string and cannot be handled as a number...
# Works as intended.

def main():
    # Defining variables
    allnum = 0
    count_allnum = 0
    pos = 0
    count_pos = 0
    neg = 0
    count_neg = 0
    odd = 0
    count_odd = 0
    even = 0
    count_even = 0
    whole = 0
    count_whole = 0
    real = 0
    count_real = 0
    prime = 0
    count_prime = 0
    all_avg = 0
    pos_avg = 0
    neg_avg = 0
    odd_avg = 0
    even_avg = 0
    whole_avg = 0
    real_avg = 0
    prime_avg = 0
    sentinelval = -10101010
    usernum = 0
    errorcheck = 0
    control = 1

    # Takes input and adds towards categories
    while usernum != sentinelval:
        userinput = input()
        try:
            usernum = float(userinput)
        except ValueError:
            errorcheck = 1  # Input value was not a number
        if usernum == sentinelval:
            break

        # Number valid, continue...
        allnum += usernum
        if errorcheck == 0:     # Ignores invalid numbers from being added to the counter
            count_allnum += 1
        else:
            errorcheck = 0
        if usernum >= 0:
            pos += usernum
            count_pos += 1
        else:
            neg += usernum
            count_neg += 1
        if usernum % 2 == 1:
            odd += usernum
            count_odd += 1
        else:
            even += usernum
            count_even += 1
        if usernum == int(usernum):
            whole += usernum
            count_whole += 1
        else:
            real += usernum
            count_real += 1
        if int(usernum) > 1:
            for i in range(2, int(usernum)):
                if (usernum % i) != 0:
                    prime += usernum
                    count_prime += 1

    # Calculates averages (sum / count)
    if count_allnum != 0:
        all_avg = allnum / count_allnum
    if count_pos != 0:
        pos_avg = pos / count_pos
    if count_neg != 0:
        neg_avg = neg / count_neg
    if count_odd != 0:
        odd_avg = odd / count_odd
    if count_even != 0:
        even_avg = even / count_even
    if count_whole != 0:
        whole_avg = whole / count_whole
    if count_real != 0:
        real_avg = real / count_real
    if count_prime != 0:
        prime_avg = prime / count_prime

    # Prints commands
    print("ALL")
    print("POS")
    print("NEG")
    print("ODD")
    print("EVEN")
    print("WHOLE")
    print("REAL")
    print("PRIME")
    print("STOP")

    # Takes input and prints averages of requested category; ignores invalid commands
    while control == 1:
        userinput = input().upper()
        if userinput == "ALL":
            print(all_avg)
        elif userinput == "POS":
            print(pos_avg)
        elif userinput == "NEG":
            print(neg_avg)
        elif userinput == "ODD":
            print(odd_avg)
        elif userinput == "EVEN":
            print(even_avg)
        elif userinput == "WHOLE":
            print(whole_avg)
        elif userinput == "REAL":
            print(real_avg)
        elif userinput == "PRIME":
            print(prime_avg)
        elif userinput == "STOP":
            control = 0
            # Control to zero stops the loop and finishes the program


main()
