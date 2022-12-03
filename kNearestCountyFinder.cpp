#define _USE_MATH_DEFINES

#include <iostream>
#include <fstream>
#include <sstream>
#include <stdio.h>
#include <string.h>
#include <list>
#include <vector>
#include <queue>
#include <math.h>
#include <map>
#include <time.h>
#include <algorithm>


using namespace std;
const float R = 6371.0;
const char* data_file_addr = "data/uscounties.csv";

float calc_distance(float la1, float lo1, float la2, float lo2);

class County{
    public:
        string name;
        string state;
        float la;
        float lo;
    
    public:
        void init(string county, string state_id, float latitude, float longitude)
        {
            name = county;
            state = state_id;
            la = latitude;
            lo = longitude;
            return;
        }

        string toString()
        {
            stringstream ss;
            ss << "County: " << name << " State: " << state << " latitude: " << to_string(la) << " longitude: " << to_string(lo) << "\n";
            return ss.str();
        }
};

class CountyDistance{
    public:
        float distance;
        County county;

    public:
        CountyDistance(float distance, County county)
        {
            this->distance = distance;
            this->county = county;
        }
};

class CountyDistanceComparator
{
    public:
        int operator() (const CountyDistance& c1, const CountyDistance& c2)
        {
            return c1.distance > c2.distance;
        }
};


class NearestStateCountyFinder{
    public:
        bool load_success;
        bool built_success;
        vector<County> countyList;

    public:
        void load_data(const char* filename)
        {
            fstream fin;
            fin.open(filename, ios::in);
            vector<string> row;
            string county, state, temp, line, word;
            float latitude;
            float longitude;

            //cout << "check in load_data function" << endl;
            getline(fin, line);
            while(getline(fin, line))
            {
                row.clear();
                // getline(fin, line);
                // cout << line << endl;
                stringstream s(line);
                while(getline(s, word, ','))
                {
                    row.push_back(word);
                }
                county = row[0];
                state = row[4];
                string a;
                a = row[6];
                a.erase(remove(a.begin(), a.end(), '\"'), a.end());
                latitude = stof(a);

                a = row[7];
                a.erase(remove(a.begin(), a.end(), '\"'), a.end());
                longitude = stof(a);

                County new_county;
                //cout << "County: " << county << " State: " << state << " latitude: " << to_string(latitude) << " longitude: " << to_string(longitude) << "\n";
                new_county.init(county, state, latitude, longitude);
                countyList.push_back(new_county);
            }
            load_success = true;
        }

        void search_nearest(string method, float la, float lo, int k, County* k_nearest_counties)
        {
            // cout<< "in search function: " << method << la << lo << k << endl;
            if(method.compare("knn") == 0)
            {
                // cout<< "identity method as knn" << endl;
                float distance;

                priority_queue <CountyDistance, vector<CountyDistance>, CountyDistanceComparator> closest_counties;
                for(auto county : countyList)
                {
                    distance = calc_distance(county.la, county.lo, la, lo);
                    // cout << county.name << ": " << distance << endl;
                    closest_counties.push(CountyDistance(distance, county));
                }

                for(int i = 0; i < k; i++)
                {
                    County county = closest_counties.top().county;
                    // cout << county.name << ": " << distance << endl;
                    closest_counties.pop();
                    k_nearest_counties[i] = county;
                }
            }
            else if(method.compare("kdt") == 0)
            {
                cout << "run command kdt" << endl;
                string KD_program_name = "main";
                string cmd = "-s";
                string la_str = to_string(la);
                string lo_str = to_string(lo);
                string k_str = to_string(k);
                string str = "java " + KD_program_name + " " + la_str + " " + lo_str + " " + k_str;
                const char *command = str.c_str();
                system(command);
            }
            else
            {
                cout << "Input method " << method << " is not implemented." << endl; 
            }

        }
};

float calc_distance(float la1, float lo1, float la2, float lo2)
{
    la1 = la1 * M_PI / 180;
    la2 = la2 * M_PI / 180;
    lo1 = lo1 * M_PI / 180;
    lo2 = lo2 * M_PI / 180;

    float x = (lo2 - lo1) * cos((la1 + la2) / 2);
    float y = (la2 - la1);
    float distance = R * sqrt(x*x + y*y);
    return distance;
}

int main(int argc, char** argv)
{
    const char* menu_text = 
    "Welcome to use Nearest State/County Finder System\n\
    Please excute following commands:\n\
    -load                               # load data\n\
    -disp load                          # display load data\n\
    -search (latitude) (longitude) (K)  # search for nearest k number of counties by input latitude and longitude\n\
    -exit                               # exit application\n";
    int input_cmd_f = 0;
    string input_cmd;

    NearestStateCountyFinder app;

    while(1)
    {
        cout << menu_text << endl;
        getline(cin, input_cmd);
        cout << input_cmd << endl;
        if(input_cmd.compare("-load") == 0)
        {
            // cout<<"check -load cmd works"<<endl;
            app.load_data((char*)data_file_addr);
        }
        else if(input_cmd.compare("-disp") == 0)
        {
            // cout<<"check -disp load cmd works"<<endl;
            for(int i = 0; i < app.countyList.size(); i++)
            {
                cout << app.countyList[i].toString() << endl;
            }
        }
        else if (input_cmd.compare("-search") == 0)
        {
            string method, latitude, longitude, k;
            County result[10];
            cout << "Enter 'method', 'latitude', 'longitude', 'k'" << endl;
            cin >> method;
            cin >> latitude;
            cin >> longitude;
            cin >> k;
            clock_t startt, endt;
            double period_time;
            startt = clock();
            app.search_nearest(method, stof(latitude), stof(longitude), stoi(k), result);
            endt = clock();
            period_time = (double)(endt - startt) / CLOCKS_PER_SEC;
            cout << "Search Time Cost: " << period_time << endl;
            for(int i = 0; i < stoi(k); i++)
            {
                cout<< i << ": county: " << result[i].name << " state: " << result[i].state << endl;
            }

        }
        else if(input_cmd.compare("-exit") == 0)
        {
            break;
        }
    }
    return 0;
}