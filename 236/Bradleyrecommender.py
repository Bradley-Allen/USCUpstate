# Bradley Allen
# CSCI236
# April 2, 2022
# Project #3 (recommender.py)
# 8 hours
# Grade Version A
# Overall difficult, understanding how get_dictionary was written was the hardest
# Works as intended.

import sys


# main method
def main():
    if len(sys.argv) == 2:
        data = readFile()
        books = get_books(data)
        users = get_users(data)
        dictionary = get_dictionary(data)
        average(dictionary, books)
        average_scores = average(dictionary, books)
        task = input().lower()
        while task != "quit":
            if task == "averages":
                for book, score in average_scores:
                    print(book, score)
            elif task == "recommend":
                target = input("")
                if target not in users:
                    for book, score in average_scores:
                        print(book, score)
                else:
                    recommends = recommendations(dictionary, target, books)
                    for book, rating in recommends:
                        if rating > 0:
                            print(book, rating)
            task = input("")
    else:
        print("Pass only one file in the command line")


# Reads file from command line, inputs lines into list "data"
def readFile():
    dataFile = open(sys.argv[1], 'r')
    dataFile = dataFile.readlines()
    dataFile = remove_newline(dataFile)
    data = []
    for index in dataFile:
        data.append(index)
    return data


# Used while reading file, removes "\n"
def remove_newline(lst):
    tempList = []
    for insideList in lst:
        tempList.append(insideList.replace("\n", ""))
    return tempList


# go through data and get a list of book titles
# create a books list and pull out the book names
# use the set method to eliminate duplicates
# return a set of unique books
def get_books(data):
    books = set()
    for line in range(len(data)):
        if line % 3 == 1:
            books.add(data[line])
    books = list(books)
    books.sort()
    return books


# go through data and get a list of users
# return a set of unique users
def get_users(data):
    users = set()
    for line in range(len(data)):
        if line % 3 == 0:
            users.add(data[line])
    users = list(users)
    users.sort()
    return users


# get dictionary with users and ratings
# return a dictionary of users and ratings
def get_dictionary(data):
    books = get_books(data)
    users = get_users(data)
    sortedRatings = {}
    for user in users:
        userRatingList = []
        for book in range(len(books)):
            userRatingList.append(0)
        for book in range(len(books)):
            for line in range(0, len(data), 3):
                if data[line] == user:
                    if data[line+1] == books[book]:
                        userRatingList[book] = int(data[line+2])
        sortedRatings[user] = userRatingList

    return sortedRatings


# task: average
# return a list of averages
def average(dictionary, books):
    averageList = []
    for book in range(len(books)):
        currentAverageRating = 0
        counter = 0
        for user in dictionary:
            if dictionary.get(user)[book] != 0:
                currentAverageRating += dictionary.get(user)[book]
                counter += 1
        if counter != 0:
            currentAverageRating = currentAverageRating / counter
        else:
            currentAverageRating = currentAverageRating
        combinedAvgBook = books[book], currentAverageRating
        averageList.append(combinedAvgBook)
    averageList.sort(reverse=True, key=lambda a: a[1])
    return averageList


# task: recommendations
# returns recommendations based on the dot product between other users
def recommendations(dictionary, target, books):
    similarityList = []
    for user in dictionary:
        if user != target:
            simNum = 0
            for index in range(len(dictionary[user])):
                simNum += (dictionary[target][index]) * (dictionary[user][index])
            simCombined = simNum, user
            similarityList.append(simCombined)
    similarityList.sort(reverse=True)
    similarityList = similarityList[0:3]

    recommendList = []
    for book in range(len(books)):
        recommendList.append(0)
    for index in range(len(recommendList)):
        counter = 0
        for rating, user in similarityList:
            if dictionary[user][index] != 0:
                counter += 1
                recommendList[index] += dictionary[user][index]
        if counter != 0:
            recommendList[index] = recommendList[index] / counter
        else:
            recommendList[index] = recommendList[index]
    finalBookRec = []
    for index in range(len(recommendList)):
        ratingBookCombine = books[index], recommendList[index]
        finalBookRec.append(ratingBookCombine)
    finalBookRec.sort(reverse=True, key=lambda a: a[1])
    return finalBookRec


main()
