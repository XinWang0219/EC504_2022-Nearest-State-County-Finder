import math as m
import pandas as pd

data_file_addr = "data/uscounties.csv"
pd.options.display.max_rows = 9999

class County:
    def __init__(self, name, state, la, lo):
        self.name = name
        self.state = state
        self.la = la
        self.lo = lo

    def dist(self, la, lo):
        return calc_disctance(self.la, self.lo, la, lo)


class NearestStateCountyFinder:
    def __init__(self):
        self.built_success = False
        self.countyList = {}

    def load_data(self, file_addr):
        f = open(file_addr)
        data = f.readlines()
        for line in data[1:]:
            data_line = line.split(",")
            county = data_line[0]
            state = data_line[4]
            latitude = data_line[6]
            longitude = data_line[7]
        print(data[0])

        self.built_success = True
        return 1

    def search_nearest(self, latitude, longitude, k=5):
        print("To be finished")
        return 1


def calc_disctance(la1, lo1, la2, lo2):
    R = 6371
    x = (lo2 - lo1) * m.cos((la1 + la2) / 2)
    y = (la1 + la2)
    distance = R * m.sqrt(x*x + y*y)
    return distance


if __name__ == "__main__":
    app = NearestStateCountyFinder()

    welcome_text = """
    Welcome to use Nearest State/County Finder System
    Please excute following commands:
    -load
    -search (latitude) (longitude) (K)
    -exit
    """

    while 1:
        print(welcome_text)
        cmd = input()
        print(cmd)
        if cmd == '-load':
            app.load_data(data_file_addr)

        elif cmd[:7] == '-search':
            if not app.built_success:
                print("DataError: No System is built. Please load data to build search system")
                continue
            else:
                cmd, latitude, longitude, k = cmd.split(' ')
                print(cmd)
                print(latitude)
                print(longitude)
                print(k)
                result = app.search_nearest(latitude, longitude, k)
                print(result)
                continue
        if cmd == '-exit':
            break

