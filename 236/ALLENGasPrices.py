# Bradley Allen
# CSCI236
# April 22, 2022
# Programming Exam #3 -- (GasPrices.py)
# 5 hours
# Grade Version A
# The difficult part of this was getting the data from the file to split into what needed to be used for calculations.
# Works as intended.

from flask import Flask

app = Flask(__name__)


def main():
    # Opens file, splits and calculates averages, highest, and lowests
    inputFile = open("GasPrices.txt")
    unsortedFile = inputFile.readlines()
    unsortedFile = remove_newline(unsortedFile)
    yearprice, linesplit = split_file(unsortedFile)
    avgYearPrice = avgPricePerYear(yearprice)
    avgMonthPrice = avgPricePerMonth(linesplit)
    yearlyHighestPrices = highestpriceyearly(yearprice)
    yearlyLowestPrices = lowestpriceyearly(yearprice)

    # Sets flask pages up
    @app.route('/')
    def home():
        return HOME_HTML

    @app.route('/avgyearprice')
    def avgyearprice():
        return avgyearprice_html

    @app.route('/avgmonthprice')
    def avgmonthprice():
        return avgmonthprice_html

    @app.route('/highestyearlyprice')
    def highestyearlyprice():
        return highestyearlyprice_html

    @app.route('/lowestyearlyprice')
    def lowestyearlyprice():
        return lowestyearlyprice_html

    HOME_HTML = """
        <html><body>
            <h3>Click <a href="/avgyearprice">here</a> for the average yearly price.</h3>
            <h3>Click <a href="/avgmonthprice">here</a> for the average monthly price.</h3>
            <h3>Click <a href="/highestyearlyprice">here</a> for the highest yearly prices.</h3>
            <h3>Click <a href="/lowestyearlyprice">here</a> for the lowest yearly prices.</h3>
        </body></html>
        """

    # Builds the html files
    avgyearprice_html = buildAvgYearPriceOutput(avgYearPrice)
    avgmonthprice_html = buildAvgMonthPriceOutput(avgMonthPrice)
    highestyearlyprice_html = buildHighestYearlyPriceOutput(yearlyHighestPrices)
    lowestyearlyprice_html = buildLowestYearlyPriceOutput(yearlyLowestPrices)
    app.run(host="localhost", debug=True)
    inputFile.close()


# Builds the output string to be used in html page
def buildHighestYearlyPriceOutput(yearlyHighestPrices):
    output = "<h3>The highest price per year is:</h3>"
    for key in yearlyHighestPrices:
        output += ("<p>" + str(key) + " " + str(yearlyHighestPrices.get(key)) + "</p>")
    return output


# Builds the output string to be used in html page
def buildLowestYearlyPriceOutput(yearlyLowestPrices):
    output = "<h3>The lowest price per year is:</h3>"
    for key in yearlyLowestPrices:
        output += ("<p>" + str(key) + " " + str(yearlyLowestPrices.get(key)) + "</p>")
    return output


# Builds the output string to be used in html page
def buildAvgYearPriceOutput(avgYearPrice):
    output = "<h3>Average price per year is:</h3>"
    for key in avgYearPrice:
        output += ("<p>" + str(key) + " " + str(avgYearPrice.get(key)[2]) + "</p>")
    return output


# Builds the output string to be used in html page
def buildAvgMonthPriceOutput(avgMonthPrice):
    output = "<h3>Average price per month is:</h3>"
    for month in range(len(avgMonthPrice)):
        output += ("<p>" + str(month+1) + " " + str(avgMonthPrice[month-1]) + "</p>")
    return output


# Calculates lowest price yearly
def lowestpriceyearly(data):
    lowestprices = {}
    for entry in data:
        year = entry[0]
        price = entry[1]
        if year in lowestprices:
            if lowestprices[year] > price:
                lowestprices[year] = price
        else:
            lowestprices[year] = price
    return lowestprices


# Calculates highest price yearly
def highestpriceyearly(data):
    highestprices = {}
    for entry in data:
        year = entry[0]
        price = entry[1]
        if year in highestprices:
            if highestprices[year] < price:
                highestprices[year] = price
        else:
            highestprices[year] = price
    return highestprices


# Calculates monthly averages with counters and prices
def avgPricePerMonth(data):
    monthsum = {}
    counters = {}
    for entry in data:
        month = entry[0]
        price = float(entry[3])
        if month not in monthsum:
            monthsum[month] = price
            counters[month] = 1
        else:
            monthsum[month] += price
            counters[month] += 1
    return calcMonthAverage(monthsum, counters)


# Calculates monthly averages
def calcMonthAverage(monthsum, counts):
    monthavgs = []
    monthsumlist = list(monthsum)
    monthsumlist.sort()
    for key in monthsumlist:
        monthavgs.append(round(monthsum[key] / counts[key], 5))
    return monthavgs


# Calculates yearly average using counts and adding price
def avgPricePerYear(data):
    yearsum = {}
    currentyear = data[0][0]
    pricesum = 0
    count = 0
    for entry in data:
        year = entry[0]
        price = entry[1]
        if currentyear != year:
            yearsum[currentyear] = (count, pricesum)
            count = 0
            pricesum = 0
            currentyear = year
        pricesum += float(price)
        count += 1
    yearAvg = calcYearAverage(yearsum)
    return yearAvg


# Calculates yearly average
def calcYearAverage(dict):
    for item in dict:
        test = dict[item]
        count, num = test
        avg = num / count
        dict[item] = count, round(num, 3), round(avg, 5)
    return dict


# Separates date and price of each line into a list
def split_file(file):
    yearprices = []
    linesplits = []
    for line in range(len(file)):
        linesplit = []
        datesplit = file[line].split("-")
        yearprice = datesplit[2]
        yearprice = yearprice.split(":")
        linesplit.append(datesplit[0])
        linesplit.append(datesplit[1])
        linesplit.append(yearprice[0])
        linesplit.append(yearprice[1])
        yearprices.append(yearprice)
        linesplits.append(linesplit)
    return yearprices, linesplits


# Removes /n character from file reading
def remove_newline(lst):
    tempList = []
    for insideList in lst:
        tempList.append(insideList.replace("\n", ""))
    return tempList


main()
