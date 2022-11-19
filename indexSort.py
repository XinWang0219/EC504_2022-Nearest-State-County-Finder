import numpy

from main import County, NearestStateCountyFinder, calc_disctance
import numpy as np

class IndexSortFinder:
    def __init__(self):
        self.Counties = []  # County List
        self.county_dict = {}
        self.la_dict = {}
        self.la_sorted = []
        self.la_index_sorted = []
        self.lo_dict = {}
        self.lo_sorted = []
        self.lo_index_sorted = []
        self.size = 0

    def load(self, Counties):
        for county in Counties:
            self.add(county)

    def add(self, county):
        if (county.name, county.state) not in self.county_dict.keys():
            self.Counties.append(county)
            self.county_dict[(county.name, county.state)] = self.size
            self.la_dict[self.size] = county.la
            self.lo_dict[self.size] = county.lo
            self.size += 1
        else:
            index = self.county_dict[(county.name, county.state)]
            self.la_dict = county.la
            self.lo_dict = county.lo

    def build(self):
        la_nparray = np.array(self.la_dict)
        self.la_index_sorted = numpy.argsort(la_nparray)
        print(self.la_index_sorted)


if __name__ == "__main__":
    app = NearestStateCountyFinder()
    app.load_data("data/uscounties.csv")

    solution = IndexSortFinder()
    for county in app.countyList[:10]:
        print(county)
    solution.load(app.countyList[:10])
    print(solution.la_dict)
    print(app.countyList[0].dist(app.countyList[1]))
