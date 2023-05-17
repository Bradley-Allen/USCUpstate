# Bradley Allen
# CSCI236
# February 26, 2022
# Project 2 -- (DNA.py)
# 3 hours
# Grade Version A
# Getting CodePost to grade it correctly and getting the correct formatting in the output document
# Works as intended.

# Initialization of constants
MIN_NUMBER_OF_CODONS = 5  # Minimum number of codons for a protein to be valid
C_AND_G_MINMASS_PERCENT = 30  # Minimum percent of C and G required for a protein to be valid
NUM_OF_NUCLEOTIDES = 4  # Number of nucleotides, A C G T
LENGTH_OF_CODON = 3  # Nucleotides per codon
A_MASS = 135.128
C_MASS = 111.103
G_MASS = 151.128
T_MASS = 125.107
JUNK_MASS = 100.000


def main():
    print("This program reports information about DNA\n"
          "nucleotide sequences that may encode proteins.")
    # Reading .in file method of opening files
    # readinput = open(input())
    # readlinesinput = readinput.read().splitlines()
    # inputFile = readlinesinput[0]
    # outputFile = readlinesinput[1]

    # User input method of opening files
    inputFile = input("Input file name? ")
    outputFile = input("Output file name? ")

    dnaFile = open(inputFile)
    unsortedFile = dnaFile.readlines()
    unsortedFile = remove_newline(unsortedFile)
    regionName, nucleotide = split_file(unsortedFile)
    output(regionName, nucleotide, outputFile)
    dnaFile.close()


# takes information in the file and splits them into either regionName or nucleotide lists
def split_file(file):
    regionName = []
    nucleotide = []
    for index in range(len(file)):
        if index % 2 == 0:
            regionName.insert(index, file[index])
        else:
            nucleotide.insert(index, file[index])
    list(regionName)
    list(nucleotide)
    return regionName, nucleotide


# remove the newline character created by readlines
def remove_newline(lst):
    tempList = []
    for insideList in lst:
        tempList.append(insideList.replace("\n", ""))
    return tempList


# count the number of ACTG in each sequence
def nuc_count(acgtSequence):
    a_counter = 0
    c_counter = 0
    g_counter = 0
    t_counter = 0
    dash_counter = 0
    nucCount = []
    for char in acgtSequence:
        if char == "A":
            a_counter += 1
        elif char == "C":
            c_counter += 1
        elif char == "G":
            g_counter += 1
        elif char == "T":
            t_counter += 1
        else:
            dash_counter += 1
    nucCount.append(a_counter)
    nucCount.append(c_counter)
    nucCount.append(g_counter)
    nucCount.append(t_counter)
    nucCount.append(dash_counter)
    return nucCount


# compute mass information for ACGT
def mass_raw(nucCount):
    a_count, c_count, g_count, t_count, dash_count = nucCount
    a_totalmass = a_count * A_MASS
    c_totalmass = c_count * C_MASS
    g_totalmass = g_count * G_MASS
    t_totalmass = t_count * T_MASS
    dash_totalmass = dash_count * JUNK_MASS
    massTotal = a_totalmass + c_totalmass + g_totalmass + t_totalmass + dash_totalmass
    return massTotal


# compute percentages from the raw ACGT
def mass_percentage(nucCount, massTotal):
    percentages = []
    a_count, c_count, g_count, t_count, dash_count = nucCount
    a_percent = ((a_count * A_MASS) / massTotal ) * 100
    c_percent = ((c_count * C_MASS) / massTotal ) * 100
    g_percent = ((g_count * G_MASS) / massTotal ) * 100
    t_percent = ((t_count * T_MASS) / massTotal) * 100
    percentages.append(round(a_percent, 1))
    percentages.append(round(c_percent, 1))
    percentages.append(round(g_percent, 1))
    percentages.append(round(t_percent, 1))
    return percentages


# compute the nucleotide sequence into blocks of 3 (ACGT excluding the -)
def codon_list(acgtSequence):
    codonlist = []
    counter = 0
    temp = ''
    for char in acgtSequence:
        if char == "A":
            temp = temp + "A"
            counter += 1
            if counter == LENGTH_OF_CODON:
                codonlist.append(temp)
                counter = 0
                temp = ''
        elif char == "C":
            temp = temp + "C"
            counter += 1
            if counter == LENGTH_OF_CODON:
                codonlist.append(temp)
                counter = 0
                temp = ''
        elif char == "G":
            temp = temp + "G"
            counter += 1
            if counter == LENGTH_OF_CODON:
                codonlist.append(temp)
                counter = 0
                temp = ''
        elif char == "T":
            temp = temp + "T"
            counter += 1
            if counter == LENGTH_OF_CODON:
                codonlist.append(temp)
                counter = 0
                temp = ''

    return codonlist


# check requirements if it is a protein
def protein_check(massPercentage, codonList):
    if codonList[0] != "ATG":
        return False
    if codonList[len(codonList)-1] != "TAA":
        if codonList[len(codonList)-1] != "TAG":
            if codonList[len(codonList)-1] != "TGA":
                return False
    if len(codonList) < MIN_NUMBER_OF_CODONS:
        return False

    a_percent, c_percent, g_percent, t_percent = massPercentage
    if c_percent + g_percent < C_AND_G_MINMASS_PERCENT:
        return False

    return True


# format/write the output to an output file
def output(names, nucleotides, outputFile):
    file = (open(outputFile, "w"))
    for each in range(len(names)):
        name = names[each]
        nucleotide = nucleotides[each]
        nucCount = nuc_count(nucleotide.upper())
        mass_total = mass_raw(nucCount)
        mass_percents = mass_percentage(nucCount, mass_raw(nucCount))
        codonlist = codon_list(nucleotide.upper())
        del nucCount[NUM_OF_NUCLEOTIDES]

        str1 = "Region Name: " + name
        str2 = "Nucleotides: " + nucleotide.upper()
        str3 = "Nuc. Counts: "
        str4 = "Total Mass%: "
        str5 = "Codons List: "
        str6 = "Is Protein?: "
        file.write(str1 + "\n")
        file.write(str2 + "\n")
        file.write(str3)
        file.write(str(nucCount))
        file.write("\n" + str4)
        file.write(str(mass_percents))
        file.write(" of ")
        file.write(str(round(mass_total, 1)))
        file.write("\n" + str5)
        file.write(str(codonlist))
        file.write("\n" + str6)
        if protein_check(mass_percents, codonlist):
            file.write("YES" + "\n")
        else:
            file.write("NO" + "\n")
        file.write("\n")
        file.close()


if __name__ == '__main__':
    main()
